package ch.hslu.pren.team8.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by gebs on 3/20/17.
 */
public class DebuggerBoadcaster implements Runnable {
    private static final String DISCOVER_MESSAGE = "LOF_DISCOVER"; //Message for receiving broadcast from GameFinder (same as in Class GameFinder)
    private static final String RESPONSE_MESSAGE = "LOF_RESPONSE"; //Message for sending broadcast to GameFinder (same as in Class GameFinder)
    private static final String BROADCAST_IP = "255.255.255.255"; //Broadcast IP
    private static final int BYTE_DATA_SIZE = 1024; //size of the UDP packets

    Thread thread;
    private int port; //port for datagram socket
    private byte[] sendData = new byte[BYTE_DATA_SIZE]; //data of the UDP packet (sending)
    private byte[] receiveData = new byte[BYTE_DATA_SIZE]; //data of the UDP packet (receiving)
    private InetAddress broadcastIP; //IP variable
    private InetAddress myIP; //IP of the own client
    private DatagramPacket sendPacket; //Packet to send
    private DatagramPacket receivePacket; //Packet to receive
    private boolean running = true; //variable to stop the running process

    public DebuggerBoadcaster(int port){

        this.port = port;
        try {
            broadcastIP = InetAddress.getByName(BROADCAST_IP);
        }
        catch (UnknownHostException ex) {
            System.out.println("Could not resolve Broadcast: " + ex);
        }
        sendData = (RESPONSE_MESSAGE.trim() + ";" + "MyCoolHost" ).getBytes();
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        if (this.thread == null) {
            this.thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * This method handles the Thread. The routine waits for a request from a client in the LAN and answers with the details.
     */
    @Override
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            socket.setBroadcast(true);
            System.out.println("Wait for requests from client...");
            while (running) {
                socket.receive(receivePacket);
                String s = new String(receivePacket.getData());
                //Only answers to requests with the correct statement.
                System.out.println(s.trim());
                if (s.trim().equals(DISCOVER_MESSAGE)) {
                    System.out.println("Got " + DISCOVER_MESSAGE + " from Client " + receivePacket.getAddress().getHostAddress());
                    sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                    socket.send(sendPacket);
                    System.out.println("Sent " + RESPONSE_MESSAGE + " to Client " + receivePacket.getAddress().getHostAddress());
                    running = false;
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Error while starting socket: " + ex);
        }
    }
    public void stop() {
        this.running = false;
    }

}

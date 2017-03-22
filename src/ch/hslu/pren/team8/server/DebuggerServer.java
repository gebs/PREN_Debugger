package ch.hslu.pren.team8.server;

import ch.hslu.pren.team8.gui.DebuggerGui;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by gebs on 3/17/17.
 */
public class DebuggerServer implements Runnable {

    private static int PORT =6955;
    private  static final boolean SAVEIMAGE = false;
    private Thread thread;
    private ServerSocket serverSocket;
    Socket server;
    private DebuggerGui frame;

    public DebuggerServer(DebuggerGui frame) {

        this.frame = frame;
        if (thread == null) {
            System.out.println("start thread");
            thread = new Thread(this);
        }
        new DebuggerBoadcaster(PORT);
        thread.start();

    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                server = serverSocket.accept();
                new DebuggerServerHandler(server,frame);
            }
        } catch (Exception e) {

        }

    }
}

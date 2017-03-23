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
    private Socket server;
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
                // @TODO: Adi, ich habe hier den dritte Parameter (false) hinzugefügt, weil der Konstruktor diesen offenbar erwartet...
                // Falls das nicht korrekt ist bzw. dynamisch(er) gestaltet werden soll: bitte ruhig ändern :-)
                new DebuggerServerHandler(server,frame,SAVEIMAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

package ch.hslu.pren.team8.server;

import ch.hslu.pren.team8.common.LogMessageBase;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by gebs on 3/17/17.
 */
public class DebuggerServerHandler implements Runnable {
    private Thread thread;
    private Socket client;

    public DebuggerServerHandler(final Socket client){
        this.client = client;

        if (thread == null){
            thread = new Thread(this);
        }
        thread.start();


    }

    @Override
    public void run() {
        try (ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {
            LogMessageBase base = (LogMessageBase) in.readObject();
            switch (base.getType()){
                case ImageMessage:
                    break;
                case LogMessage:
                    break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

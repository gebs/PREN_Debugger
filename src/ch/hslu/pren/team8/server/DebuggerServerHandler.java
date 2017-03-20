package ch.hslu.pren.team8.server;

import ch.hslu.pren.team8.common.LogMessageBase;
import ch.hslu.pren.team8.common.LogMessageImage;
import ch.hslu.pren.team8.common.LogMessageText;
import ch.hslu.pren.team8.gui.DebuggerGui;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by gebs on 3/17/17.
 */
public class DebuggerServerHandler implements Runnable {
    private Thread thread;
    private Socket client;
    DebuggerGui gui;

    public DebuggerServerHandler(final Socket client, final DebuggerGui gui){
        this.client = client;
        this.gui = gui;
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
                    gui.LogImageMessage((LogMessageImage)base);
                    break;
                case LogMessage:
                    gui.LogTextMessage((LogMessageText)base);
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

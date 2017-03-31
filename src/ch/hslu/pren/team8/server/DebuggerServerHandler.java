package ch.hslu.pren.team8.server;

import ch.hslu.pren.team8.debugger.ImageType;
import ch.hslu.pren.team8.debugger.LogMessageBase;
import ch.hslu.pren.team8.debugger.LogMessageImage;
import ch.hslu.pren.team8.debugger.LogMessageText;
import ch.hslu.pren.team8.gui.DebuggerGui;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by gebs on 3/17/17.
 */
public class DebuggerServerHandler implements Runnable {
    private Thread thread;
    private Socket client;
    private boolean saveImage;
    private boolean saveEditImage;
    private DebuggerGui gui;

    public DebuggerServerHandler(final Socket client, final DebuggerGui gui, final boolean saveImage, final boolean saveeditImage){
        this.client = client;
        this.gui = gui;
        this.saveImage = saveImage;
        this.saveEditImage = saveeditImage;
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
                    if ((saveImage && ((LogMessageImage) base).getImageType() == ImageType.ORIGINAL)
                            || saveEditImage && ((LogMessageImage) base).getImageType() == ImageType.EDITED){
                        File file = new File(base.getLogDate() + "_" + ((LogMessageImage) base).getImageType() + "_Image.png");
                        ImageIO.write(((LogMessageImage) base).getImage(),"png",file);
                    }
                    break;
                case LogMessage:
                    gui.LogTextMessage((LogMessageText)base);
                    break;
            }
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

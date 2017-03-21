package ch.hslu.pren.team8.gui;

import ch.hslu.pren.team8.debugger.ImageType;
import ch.hslu.pren.team8.debugger.LogMessageImage;
import ch.hslu.pren.team8.debugger.LogMessageText;
import ch.hslu.pren.team8.server.DebuggerServer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gebs on 3/17/17.
 */
public class DebuggerGui {
    private JList lTextLog;
    private JPanel pImageO;
    private JPanel pImageEdit;
    public JPanel mainPanel;
    private JPanel ImagePanel;
    private JPanel TextPanel;
    ImageIcon oicon = new ImageIcon();
    JLabel olabel = new JLabel();
    DefaultListModel<LogMessageText> listModel;
    private int index;


    public DebuggerGui() {
        //this.setSize(800, 600);
        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);


        listModel = new DefaultListModel<LogMessageText>();
        lTextLog.setModel(listModel);
        pImageO.setLayout(new GridLayout(1, 1));
        pImageEdit.setLayout(new GridLayout(1, 1));
        pImageO.add(olabel);
        new DebuggerServer(this);
    }

    public synchronized void LogTextMessage(LogMessageText msg) {
        listModel.insertElementAt(msg, 0);
        lTextLog.ensureIndexIsVisible(index);
    }

    public synchronized void LogImageMessage(LogMessageImage msg) {

        SwingUtilities.invokeLater(() -> {

            if (msg.getImageType() == ImageType.ORIGINAL) {
               olabel.setIcon(new ImageIcon(msg.getImage()));
                //pImageO.removeAll();
                //)  pImageO.add(label);
            } else {
                //pImageEdit.removeAll();
               // pImageEdit.add(label);
            }


        });
    }
}

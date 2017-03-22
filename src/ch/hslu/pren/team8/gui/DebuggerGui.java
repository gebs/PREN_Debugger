package ch.hslu.pren.team8.gui;

import ch.hslu.pren.team8.debugger.ImageType;
import ch.hslu.pren.team8.debugger.LogMessageImage;
import ch.hslu.pren.team8.debugger.LogMessageText;
import ch.hslu.pren.team8.server.DebuggerServer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
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
    JLabel elabel = new JLabel();
    DefaultListModel<LogMessageText> listModel;
    private int index;


    public DebuggerGui() {
        listModel = new DefaultListModel<LogMessageText>();
        lTextLog.setModel(listModel);
        pImageO.setLayout(new GridLayout(1, 1));
        pImageEdit.setLayout(new GridLayout(1, 1));
        Border border = olabel.getBorder();
        Border margin = new EmptyBorder(10,30,10,30);
        olabel.setBorder(new CompoundBorder(border,margin));
        pImageO.add(olabel);

        Border border1 = elabel.getBorder();
        Border margin1 = new EmptyBorder(10,30,10,30);
        elabel.setBorder(new CompoundBorder(border1,margin1));
        pImageEdit.add(elabel);
        new DebuggerServer(this);
    }

    public synchronized void LogTextMessage(LogMessageText msg) {
        listModel.insertElementAt(msg, 0);
    }

    public synchronized void LogImageMessage(LogMessageImage msg) {

        SwingUtilities.invokeLater(() -> {

            if (msg.getImageType() == ImageType.ORIGINAL) {
               olabel.setIcon(new ImageIcon(msg.getImage()));
            } else {
                elabel.setIcon(new ImageIcon(msg.getImage()));
            }


        });
    }
}

package ch.hslu.pren.team8.server;

import ch.hslu.pren.team8.gui.DebuggerGui;

import javax.swing.*;

/**
 * Created by gebs on 3/17/17.
 */
public class Main {

    public static void main(String[] args) {
        // write your code here
        JFrame frame = new JFrame("DebuggerGui");
        frame.setContentPane(new DebuggerGui().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

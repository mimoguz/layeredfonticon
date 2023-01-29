package io.github.mimoguz.layeredfonticon.demo;

import javax.swing.UIManager;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // pass
        }
        SwingUtilities.invokeLater(() -> new Frame().setVisible(true));
    }
}


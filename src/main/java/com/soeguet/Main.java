package com.soeguet;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.soeguet.design.frame.CustomFrameImpl;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            FlatDarculaLaf.setup();
            JFrame frame = new CustomFrameImpl();
            frame.pack();
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

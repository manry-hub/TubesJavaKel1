package com.tubespjmfkel2;

import javax.swing.SwingUtilities;

import com.tubespjmfkel2.gui.AppWindow;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppWindow::new);
    }
}

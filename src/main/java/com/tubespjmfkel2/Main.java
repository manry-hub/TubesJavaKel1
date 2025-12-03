package com.tubespjmfkel2;

import javax.swing.SwingUtilities;

import com.tubespjmfkel2.view.GUI;

public class Main {

    public static void main(String[] args) {
        // Menjalankan GUI pada Event Dispatch Thread
        SwingUtilities.invokeLater(GUI::new);
    }
}

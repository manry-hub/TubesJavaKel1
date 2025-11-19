package com.tubespjmfkel2.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.tubespjmfkel2.controller.GraphController;
import com.tubespjmfkel2.dto.DijkstraResult;

public class AppWindow extends JFrame {

    private GraphController gm = new GraphController();

    public AppWindow() {
        super("Pencarian Rute Terpendek Menuju Bengkel");

        JButton btnAddNode = new JButton("Tambah Titik Tempat");
        JButton btnAddEdge = new JButton("Tambah Jarak antar Titik");
        JButton btnFindPath = new JButton("Cari Rute Terpendek");
        JButton btnReset = new JButton("Reset Semua");

        btnAddNode.addActionListener(e -> addNode());
        btnAddEdge.addActionListener(e -> addEdge());
        btnFindPath.addActionListener(e -> findPath());
        btnReset.addActionListener(e -> resetAll());

        JPanel top = new JPanel();
        top.add(btnAddNode);
        top.add(btnAddEdge);
        top.add(btnFindPath);
        top.add(btnReset);

        add(top, BorderLayout.NORTH);
        add(new GraphPanel(gm), BorderLayout.CENTER);

        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addNode() {
        String name = JOptionPane.showInputDialog("Nama Tempat:");

        String result = gm.addNode(name);
        if (result != null) {
            JOptionPane.showMessageDialog(this, result);
        }
    }

    private void addEdge() {
        String from = JOptionPane.showInputDialog("Dari:");
        String to = JOptionPane.showInputDialog("Menuju:");
        int w = Integer.parseInt(JOptionPane.showInputDialog("Jarak (km):"));

        String result = gm.addEdge(from, to, w);
        if (result != null) {
            JOptionPane.showMessageDialog(this, result);
        }

    }

    private void findPath() {
        String start = JOptionPane.showInputDialog("Dari:");
        String end = JOptionPane.showInputDialog("Menuju:");

        DijkstraResult result = gm.runDijkstra(start, end);

        if (result == null) {
            JOptionPane.showMessageDialog(this, "Rute tidak ditemukan!");
            return;
        }

        List<String> path = result.getPath();
        int distance = result.getDistance();

        PathHighlighter.highlight(gm.getGraph(), gm, path);

        StringBuilder sb = new StringBuilder();
        sb.append("Rute Terpendek\n")
                .append("Dari: ").append(start).append("\n")
                .append("Menuju: ").append(end).append("\n")
                .append("Total Jarak: ").append(distance).append(" km\n\n")
                .append("Urutan Rute:\n");

        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i < path.size() - 1) {
                sb.append(" → ");
            }
        }

        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void resetAll() {
        int konfirmasi = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus semua seperti semula?",
                "Konfirmasi Reset",
                JOptionPane.YES_NO_OPTION);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            gm.resetGraph();
            repaint();
        }
    }

}

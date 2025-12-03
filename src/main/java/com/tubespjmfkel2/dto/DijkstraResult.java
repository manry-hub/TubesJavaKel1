package com.tubespjmfkel2.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) yang digunakan untuk menyimpan hasil
 * perhitungan algoritma Dijkstra.
 *
 * <p>
 * Class ini merepresentasikan hasil berupa:
 * <ul>
 * <li>List urutan vertex yang membentuk rute terpendek</li>
 * <li>Total jarak (distance) yang ditempuh dari vertex awal ke akhir</li>
 * </ul>
 *
 * <p>
 * Objek ini merupakan immutable sehingga nilai hanya dapat diisi
 * melalui constructor dan tidak dapat dimodifikasi kembali.
 * </p>
 */
public class DijkstraResult {

    private List<String> path;

    private int distance;

    public DijkstraResult(List<String> path, int distance) {
        this.path = path;
        this.distance = distance;
    }

    public List<String> getPath() {
        return path;
    }

    public int getDistance() {
        return distance;
    }
}

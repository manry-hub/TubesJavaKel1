package com.tubespjmfkel2.dto;

import java.util.List;

public class DijkstraResult {
    private final List<String> path;
    private final int distance;

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

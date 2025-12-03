package com.tubespjmfkel2.domain;

import java.util.ArrayList;

import java.util.List;


public class Vertex {

    private String name;
    private List<Edge> neighbors = new ArrayList<>();
    private List<Vertex> shortestPath = new ArrayList<>();
    private Integer distance = Integer.MAX_VALUE;


    public void addNeighbor(Edge edge) {
        neighbors.add(edge);
    }

    public List<Edge> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Edge> neighbors) {
        this.neighbors = neighbors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Vertex> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Vertex> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}

package com.tubespjmfkel2.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mxgraph.view.mxGraph;
import com.tubespjmfkel2.dto.DijkstraResult;
import com.tubespjmfkel2.model.Dijkstra;
import com.tubespjmfkel2.model.Graph;
import com.tubespjmfkel2.model.Node;

public class GraphController {

    private Graph coreGraph = new Graph();
    private mxGraph uiGraph = new mxGraph();
    private Map<String, Object> edgeMap = new HashMap<>();

    public mxGraph getGraph() {
        return uiGraph;
    }

    public Map<String, Object> getEdgeMap() {
        return edgeMap;
    }

    private void applyDefaultStyle() {
        Map<String, Object> style = uiGraph.getStylesheet().getDefaultVertexStyle();
        style.put("shape", "ellipse");
        style.put("verticalAlign", "middle");
        style.put("align", "center");
    }

    // ==========================
    // ADD NODE
    // ==========================
    public String addNode(String name) {

        // Validasi nama kosong/null
        if (name == null || name.trim().isEmpty()) {
            return "Nama node tidak boleh kosong!";
        }

        // Validasi duplikasi
        if (findNode(name) != null) {
            return "Node dengan nama '" + name + "' sudah ada!";
        }

        // CORE graph
        Node node = new Node(name);
        coreGraph.addNode(node);

        // UI graph
        uiGraph.getModel().beginUpdate();
        try {
            uiGraph.insertVertex(
                    uiGraph.getDefaultParent(),
                    null,
                    name,
                    50, 50,
                    60, 60,
                    "shape=ellipse");
        } finally {
            uiGraph.getModel().endUpdate();
        }

        return null; // sukses
    }

    // ==========================
    // ADD EDGE
    // ==========================
    public String addEdge(String from, String to, int weight) {

        // Validasi node harus ada
        Node nFrom = findNode(from);
        Node nTo = findNode(to);

        if (nFrom == null)
            return "Node asal '" + from + "' tidak ditemukan!";
        if (nTo == null)
            return "Node tujuan '" + to + "' tidak ditemukan!";

        // Tidak boleh edge ke diri sendiri
        if (from.equals(to))
            return "Tidak boleh membuat edge dari node ke dirinya sendiri!";

        // Bobot harus positif
        if (weight <= 0)
            return "Bobot edge harus lebih besar dari 0!";

        // Cek duplikasi edge
        if (edgeMap.containsKey(from + "->" + to))
            return "Edge dari '" + from + "' ke '" + to + "' sudah ada!";

        // Update model
        nFrom.addDestination(nTo, weight);

        // Update UI
        uiGraph.getModel().beginUpdate();
        try {
            Object vFrom = findVertex(from);
            Object vTo = findVertex(to);

            Object edge = uiGraph.insertEdge(
                    uiGraph.getDefaultParent(),
                    null,
                    weight,
                    vFrom,
                    vTo);

            edgeMap.put(from + "->" + to, edge);

        } finally {
            uiGraph.getModel().endUpdate();
        }

        return null; // sukses
    }

    // ==========================
    // FIND NODE (CORE)
    // ==========================
    public Node findNode(String name) {
        return coreGraph.getNodes()
                .stream()
                .filter(n -> n.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    // ==========================
    // FIND NODE (UI)
    // ==========================
    private Object findVertex(String name) {
        for (Object cell : uiGraph.getChildVertices(uiGraph.getDefaultParent())) {
            if (name.equals(uiGraph.getLabel(cell))) {
                return cell;
            }
        }
        return null;
    }

    // ==========================
    // RUN DIJKSTRA
    // ==========================
    public DijkstraResult runDijkstra(String start, String end) {

        if (start == null || end == null)
            return null;

        Node startNode = findNode(start);
        Node endNode = findNode(end);

        if (startNode == null || endNode == null)
            return null;

        if (start.equals(end))
            return new DijkstraResult(
                    Arrays.asList(start),
                    0);

        coreGraph.resetAllNodes();

        Dijkstra.calculateShortestPathFromSource(coreGraph, startNode);

        if (endNode.getDistance() == Integer.MAX_VALUE)
            return null;

        List<String> path = new ArrayList<>();
        for (Node n : endNode.getShortestPath())
            path.add(n.getName());

        if (!path.contains(endNode.getName()))
            path.add(endNode.getName());

        return new DijkstraResult(
                path,
                endNode.getDistance());
    }

    public void resetGraph() {
        // Reset graph core
        coreGraph = new Graph();

        // Reset edge map
        edgeMap.clear();

        // Clear UI graph (remove all cells from default parent)
        uiGraph.getModel().beginUpdate();
        try {
            Object parent = uiGraph.getDefaultParent();
            Object[] cells = uiGraph.getChildCells(parent, true, true); // all children
            if (cells != null && cells.length > 0) {
                uiGraph.removeCells(cells, true);
            }
        } finally {
            uiGraph.getModel().endUpdate();
        }

        // (Re-)apply style if needed
        applyDefaultStyle();
    }

}

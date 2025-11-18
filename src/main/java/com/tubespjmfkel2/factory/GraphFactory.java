package com.tubespjmfkel2.factory;

import com.tubespjmfkel2.algorithm.graph.Graph;
import com.tubespjmfkel2.algorithm.graph.Node;

import java.util.HashMap;
import java.util.Map;

public class GraphFactory {

    public static class BuildResult {
        public final Graph graph;
        public final Map<String, Node> nodes;

        public BuildResult(Graph graph, Map<String, Node> nodes) {
            this.graph = graph;
            this.nodes = nodes;
        }
    }

    public static BuildResult build(Map<String, Map<String, Integer>> adj) {

        Graph graph = new Graph();
        Map<String, Node> nodes = new HashMap<>();

        // Buat node
        for (String name : adj.keySet()) {
            nodes.put(name, new Node(name));
        }

        // Sambungkan edge
        for (String u : adj.keySet()) {
            Node from = nodes.get(u);

            for (String v : adj.get(u).keySet()) {
                from.addDestination(nodes.get(v), adj.get(u).get(v));
            }

            graph.addNode(from);
        }

        return new BuildResult(graph, nodes);
    }
}

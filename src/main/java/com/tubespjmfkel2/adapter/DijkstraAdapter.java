package com.tubespjmfkel2.adapter;

import com.tubespjmfkel2.algorithm.graph.Node;
import com.tubespjmfkel2.factory.GraphFactory;
import com.tubespjmfkel2.algorithm.dijkstra.Dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DijkstraAdapter {

    public static class Result {
        public final List<String> path;
        public final int distance;

        public Result(List<String> path, int distance) {
            this.path = path;
            this.distance = distance;
        }
    }

    public static Result run(Map<String, Map<String, Integer>> adj,
            String startName,
            String endName) {

        GraphFactory.BuildResult br = GraphFactory.build(adj);

        Node start = br.nodes.get(startName);
        Node end = br.nodes.get(endName);

        if (start == null || end == null)
            return null;

        Dijkstra.calculateShortestPathFromSource(br.graph, start);

        if (end.getDistance() == Integer.MAX_VALUE)
            return null;

        List<String> path = new ArrayList<>();
        for (Node n : end.getShortestPath())
            path.add(n.getName());
        path.add(endName);

        return new Result(path, end.getDistance());
    }
}

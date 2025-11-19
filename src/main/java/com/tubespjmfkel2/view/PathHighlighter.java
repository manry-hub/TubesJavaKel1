package com.tubespjmfkel2.view;

import com.mxgraph.view.mxGraph;
import com.tubespjmfkel2.controller.GraphController;

import java.util.List;

public class PathHighlighter {

    public static void highlight(mxGraph graph, GraphController gm, List<String> path) {
        graph.getModel().beginUpdate();
        try {
            gm.getEdgeMap().forEach((k, e) -> {
                graph.setCellStyle("strokeColor=black;strokeWidth=1", new Object[] { e });
            });

            for (int i = 0; i < path.size() - 1; i++) {
                colorEdge(graph, gm, path.get(i), path.get(i + 1));
                colorEdge(graph, gm, path.get(i + 1), path.get(i)); // jika bidirectional
            }

        } finally {
            graph.getModel().endUpdate();
        }
    }

    private static void colorEdge(mxGraph graph, GraphController gm, String from, String to) {
        Object e = gm.getEdgeMap().get(from + "->" + to);
        if (e != null) {
            graph.setCellStyle("strokeColor=green;strokeWidth=3", new Object[] { e });
        }
    }

}

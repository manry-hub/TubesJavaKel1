package com.tubespjmfkel2.view;

import com.mxgraph.swing.mxGraphComponent;
import com.tubespjmfkel2.controller.GraphController;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class GraphPanel extends JPanel {

    public GraphPanel(GraphController gm) {
        setLayout(new BorderLayout());
        add(new mxGraphComponent(gm.getGraph()), BorderLayout.CENTER);
    }
}

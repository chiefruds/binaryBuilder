package com.company.view;

import com.company.NodeGenerator;
import com.company.model.DrawnNode;
import com.company.model.nodes.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class FlowGeneratorView {
    private final Pane flowContent;
    private final NodeView nodeView;
    private final ArrayList<DrawnNode> drawnNodes;

    public FlowGeneratorView(Pane flowContent) {
        this.flowContent = flowContent;
        this.nodeView = new NodeView();
        this.drawnNodes = new ArrayList<DrawnNode>();
    }

    public void generateFlow(ArrayList<Node> nodes) {
        this.drawAllNodes(nodes);
        this.connectAllNodes();
    }

    private void drawAllNodes(ArrayList<Node> nodes) {
        for(Node node : nodes) {
            Text text = nodeView.createText(node.getName());
            Circle circle = nodeView.encircle(text);
            drawnNodes.add(
                    new DrawnNode(node).withCircle(circle)
                            .withText(text)
                            .build()
            );
            flowContent.getChildren().addAll(circle, text);
        }
    }

    private void connectAllNodes() {
        int offsetX = 100;
        int offsetY = 100;
        int index = 0;

        for(DrawnNode node : drawnNodes) {
            List<Node> edges = node.getNode().getEdges();
            nodeView.setPosition(node.getCircle(), node.getText(), offsetX, offsetY);
            if((index + 1) < drawnNodes.size() && edges != null) {
                for(Node edge : edges) {
                    for(DrawnNode n: drawnNodes) {
                        if(n.getNode() == edge) {
                            flowContent.getChildren().add(nodeView.connectNodes(node.getCircle(), n.getCircle()));
                        }
                    }
                }
            }
            if(index % 5 == 0) {
                offsetY += 250;
                offsetX = 0;
            }
            offsetX += 300;
            index++;
        }
    }
}

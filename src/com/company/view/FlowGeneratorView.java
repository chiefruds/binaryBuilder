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
        mapNodes(nodes);
        for(Node node : nodes) {
            Text text = nodeView.createText(node.getName());
            Circle circle = nodeView.encircle(node);
            drawnNodes.add(
                    new DrawnNode(node).withCircle(circle)
                            .withText(text)
                            .build()
            );
            flowContent.getChildren().addAll(circle, text);
        }
    }

    private void mapNodes(ArrayList<Node> nodes) {
        ArrayList<Node> toMapNodes = new ArrayList<Node>();
        for(Node node : nodes){
            System.out.println(node.getClass().getName());
            if(node.getClass().getName().matches(".*ProbeNode.*")) {

                toMapNodes.add(node);
            }
        }
        toMapNodes.forEach(nodes::remove);
        nodes.addAll(toMapNodes);
    }

    private void connectAllNodes() {
        int offsetX = 500;
        int offsetY = 100;
        int index = 0;

        for(DrawnNode node : drawnNodes) {
            List<Node> edges = node.getNode().getEdges();
            setPositions(node, offsetX, offsetY);
            if((index + 1) < drawnNodes.size() && edges != null) {
                for(Node edge : edges) {
                    for(DrawnNode n: drawnNodes) {
                        if(n.getNode() == edge) {
                            flowContent.getChildren().add(nodeView.connectNodes(node.getCircle(), n.getCircle()));
                        }
                    }
                }
            }
            if(index % 2 == 0) {
                offsetY += 250;
                offsetX = 500;
            }
            offsetX += 300;
            index++;
        }
    }

    private void setPositions(DrawnNode node, int offsetX, int offsetY) {
        String className = node.getNode().getClass().getName();
        //System.out.println(className);
        if(className.matches(".*InputNode.*")) {
            System.out.println("matches input!");
            offsetY = 100;
            offsetX += 100;
        } else if (className.matches(".*ProbeNode.*")) {
            offsetY += 200;
        }
        nodeView.setPosition(node.getCircle(), node.getText(), offsetX, offsetY);
    }
}

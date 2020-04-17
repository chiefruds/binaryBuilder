package com.company.view;

import com.company.model.nodes.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class NodeView {
    public NodeView() {

    }

    public Arrow connectNodes(Circle node1, Circle node2) {
        Arrow arrow = new Arrow();

        // bind ends of line:
        arrow.startXProperty().bind(node1.centerXProperty().add(node1.translateXProperty()));
        arrow.startYProperty().bind(node1.centerYProperty().add(node1.translateYProperty()));
        arrow.endXProperty().bind(node2.centerXProperty().add(node2.translateXProperty()));
        arrow.endYProperty().bind(node2.centerYProperty().add(node2.translateYProperty()));

        return arrow;
    }

    public void setPosition(Circle circle, Text text, int xpos, int ypos) {
        circle.setTranslateX(xpos);
        circle.setTranslateY(ypos);
        text.setTranslateX(xpos - 70);
        text.setTranslateY(ypos + 20);
    }

    public Text createText(String string) {
        Text text = new Text(string);
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setStyle(
                "-fx-font-family: \"Times New Roman\";" +
                        "-fx-font-style: italic;" +
                        "-fx-font-size: 48px;"
        );
        return text;
    }
    public Circle encircle(Node node) {
        String className = node.getClass().getName();
        Circle circle = new Circle();
        if(className.matches(".*InputNode.*")) {
            circle.setFill(Color.GREEN);
        } else if(className.matches(".*ProbeNode.*")) {
            circle.setFill(Color.GREY);
        } else {
            circle.setFill(Color.ORCHID);
        }
        final double PADDING = 50;
        circle.setRadius(100);

        return circle;
    }
}

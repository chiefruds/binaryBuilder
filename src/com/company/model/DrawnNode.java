package com.company.model;

import com.company.model.nodes.Node;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class DrawnNode {
    private Text text;
    private Circle circle;
    private Node node;

    public DrawnNode(Node node) {
        this.node = node;
    }

    public DrawnNode withText(Text text) {
        this.text = text;
        return this;
    }

    public DrawnNode withCircle(Circle circle) {
        this.circle = circle;
        return this;
    }

    public Node getNode() {
        return node;
    }

    //builder pattern
    public DrawnNode build() {
        DrawnNode node = new DrawnNode();
        node.circle = this.circle;
        node.text = this.text;
        node.node = this.node;
        return node;
    }

    private DrawnNode() {

    }

    public Text getText() {
        return text;
    }
    public Circle getCircle() {
        return circle;
    }


}

package com.company.model.nodes;

import java.util.List;

public class Node {

    private String name;
    private String type;
    private List<Node> edges;
    private boolean inputHigh;

    public Node(String name) {
        this.name = name;
    }

    public Node withName(String name) {
        this.name = name;
        return this;
    }

    public Node withType(String type) {
        this.type = type;
        return this;
    }

    public Node withHighInput(Boolean inputHigh) {
        this.inputHigh = inputHigh;
        return this;
    }

    public Node build() {
        Node node = new Node();
        node.name = this.name;
        node.type = this.type;
        node.inputHigh = this.inputHigh;
        return node;
    }

    private Node() {

    }

    public void sendCurrent(Boolean current) {
        //send current.
    }

    //setters and getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<Node> getEdges() { return edges; }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEdges(List<Node> edges) { this.edges = edges; }
}

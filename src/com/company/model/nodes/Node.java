package com.company.model.nodes;

import java.util.List;

public class Node {

    private String name;
    private String type;
    private List<Node> edges;

    public Node(String name, String type) {
        this.name = name;
        this.type = type;
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

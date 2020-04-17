package com.company.model.nodes;

public class InputNode extends Node {

    private boolean highInput;

    public InputNode(String name) {
        super(name);
    }

    @Override
    public Node withHighInput(Boolean inputHigh) {
        this.highInput = highInput;
        return this;
    }

    @Override
    public Node build() {
        InputNode node = new InputNode(getName());
        node.highInput = highInput;
        return node;
    }
}

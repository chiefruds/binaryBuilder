package com.company.model.nodes;

public class InputNode extends Node {

    private boolean highInput;

    public InputNode(String name) {
        super(name);
    }

    @Override
    public Node withHighInput(Boolean inputHigh) {
        this.highInput = inputHigh;
        return this;
    }

    @Override
    public Node build() {
        InputNode node = new InputNode(getName());
        System.out.println("input is: " + highInput);
        node.highInput = this.highInput;
        return node;
    }

    @Override
    public boolean isInputHigh() {
        return this.highInput;
    }

    @Override
    public void setInputHigh(boolean inputHigh) {
        this.highInput = inputHigh;
    }
}

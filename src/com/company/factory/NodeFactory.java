package com.company.factory;

import com.company.factory.AbstractFactory;
import com.company.model.nodes.*;

public class NodeFactory extends AbstractFactory {
    @Override
    public Node createNode(String type, String name) {
        Node node;
        switch (type) {
            case "INPUT": node = new InputNode(name);
            break;
            case "PROBE": node = new ProbeNode(name);
            break;
            case "NAND": node = new NandNode(name);
            break;
            case "AND": node = new AndNode(name);
            break;
            case "XOR": node = new XorNode(name);
            break;
            case "OR": node = new OrNode(name);
            break;
            case "NOR": node = new NorNode(name);
            break;
            case "NOT": node = new NotNode(name);
            break;
            default: node = new Node("unknown");
        }
        return node;
    }
}

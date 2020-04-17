package com.company.factory;

import com.company.model.nodes.Node;

public abstract class AbstractFactory {
    public static final NodeFactory NODE_FACTORY = new NodeFactory();

    public static AbstractFactory getFactory(Architecture architecture) {
        AbstractFactory factory = null;
        switch (architecture) {
            case NODE:
                factory = NODE_FACTORY;
                break;
        }
        return factory;
    }

    public abstract Node createNode(String type, String name);
}

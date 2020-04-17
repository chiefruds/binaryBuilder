package com.company;

import com.company.factory.AbstractFactory;
import com.company.factory.Architecture;
import com.company.model.nodes.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NodeGenerator {

    public AbstractFactory factory;

    public NodeGenerator() {
        factory = AbstractFactory.getFactory(Architecture.NODE);
    }

    public ArrayList<Node> getNodes(String input) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Pattern nodesPattern =  Pattern.compile("[a-zA-Z_\\d]+:.*?(AND|OR|XOR|NOT|INPUT_HIGH|INPUT_LOW|PROBE|NAND|NOR)");
        Pattern nodeIndexPattern = Pattern.compile("[a-zA-Z_\\d]+(?=:)");
        Pattern nodeTypePattern = Pattern.compile("AND|OR|XOR|NOT|INPUT_HIGH|INPUT_LOW|PROBE|NAND|NOR");

        Matcher matcher = nodesPattern.matcher(input);
        Matcher nodeIndex;
        Matcher nodeType;
        String key = "";
        String type = "";

        while (matcher.find()) {
            nodeIndex = nodeIndexPattern.matcher(matcher.group());
            nodeType = nodeTypePattern.matcher(matcher.group());
            while(nodeIndex.find()) {
                key =nodeIndex.group();
            }
            while(nodeType.find()) {
                type = nodeType.group();
            }

            Node newNode;
            if(!key.equals("") && !type.equals(""))
                if(type.equals("INPUT_HIGH")) {
                    nodes.add(factory.createNode("INPUT", key)
                    .withHighInput(true)
                    .build());
                } else if(type.equals("INPUT_LOW")) {
                    nodes.add(factory.createNode("INPUT", key)
                    .withHighInput(false)
                    .build());
                } else {
                    nodes.add(factory.createNode(type, key));
                }
                //nodes.add(new Node(key, type));
        }
        input = input.replaceAll(nodesPattern.toString(), "");
        nodes = setNodeEdges(input, nodes);
        return nodes;
    }

    public ArrayList<Node> setNodeEdges(String input, ArrayList<Node> nodes) {
        ArrayList<Node> nodeList = nodes;
        Pattern nodeEdgesPattern =  Pattern.compile("[a-zA-Z_\\d]+:.*");
        Pattern nodeKeyPattern = Pattern.compile("[a-zA-Z_\\d]+?(?=:)");
        Pattern nodeValuePattern = Pattern.compile("(?<=:.)[a-zA-Z_\\d,]+");

        Matcher keyIndex;
        Matcher valueIndex;

        String key = "";
        List<Node> value = new ArrayList<Node>();


        Matcher matcher = nodeEdgesPattern.matcher(input);

        while(matcher.find()) {
            keyIndex = nodeKeyPattern.matcher(matcher.group());
            valueIndex = nodeValuePattern.matcher(matcher.group());

            while(keyIndex.find()) {
                key = keyIndex.group();
            }
            while(valueIndex.find()) {
                List<Node> list = new ArrayList<Node>();
                if(valueIndex.group().matches(".*[,].*")) {
                    ArrayList<String> tempList = new ArrayList<String>(Arrays.asList(valueIndex.group().split(",")));
                    for (String s : tempList) {
                        for (Node node : nodeList) {
                            if(node.getName().equals(s)) {
                                list.add(node);
                            }
                        }
                    }
                } else {
                    String edge = valueIndex.group();
                    for (Node node : nodeList) {
                        if(node.getName().equals(edge)) {
                            list.add(node);
                        }
                    }
                }
                value = list;
            }

            if(!key.equals("")) {
                int index = 0;
                for (Node node : nodeList) {
                    if(node.getName().equals(key)) {
                        nodeList.get(index).setEdges(value);
                    }
                    index++;
                }
            }
        }
        return nodeList;
    }


}

package com.company.view;

import com.company.model.DrawnNode;
import com.company.model.nodes.Node;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

import java.util.ArrayList;
import java.util.List;

public class FlowView {
    final private Stage primaryStage;
    final private FileReaderView fileReaderView;
    final private NodeView nodeView;

    public Pane root;
    private Pane flowContent;
    private CodeArea codeArea;

    public FlowView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.fileReaderView = new FileReaderView(this);
        this.nodeView = new NodeView();
    }

    public void drawView() {
        //all pane elements
        flowContent = new Pane();
        ScrollPane scrollPane = new ScrollPane(flowContent);
        root = new Pane(scrollPane);

        //title
        Label title = new Label("Voer een bestand in om de binary counter te starten!");
        title.setStyle(getTitleStyling());

        //button with event to open file
        Button button = new Button("Choose a file");
        fileReaderView.setFileListener(button, primaryStage);
        BorderPane.setAlignment(button, Pos.CENTER);
        BorderPane.setMargin(button, new Insets(10));

        //code area containing code of current circuit
        codeArea = new CodeArea();
        codeArea.replaceText(0, 0, "Dit werkt!");
        StackPane codeWrapper = new StackPane(new VirtualizedScrollPane<>(codeArea));

        Scene scene = new Scene(new BorderPane(root, title, button, codeWrapper, null), 700, 500);

        primaryStage.setScene(scene);
        setPreferredSizes(codeWrapper, scrollPane);
        primaryStage.show();

    }

    private String getTitleStyling() {
        return  "-fx-font-family: \"Times New Roman\";" +
                "-fx-font-style: italic;" +
                "-fx-font-size: 48px;" +
                "-fx-text-alignment: center;";
    }

    private void setPreferredSizes(StackPane codeWrapper, ScrollPane scrollPane) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());
        codeWrapper.setPrefSize(primaryScreenBounds.getWidth(), 500);
        scrollPane.setPrefSize(primaryScreenBounds.getWidth(), 500);
    }

    public void replaceText(String text) {
        codeArea.replaceText(0, 0, text);
    }

    public void drawFlow(ArrayList<Node> nodes) {
        ArrayList<DrawnNode> drawnNodes = new ArrayList<DrawnNode>();

        //draw the nodes.
        drawAllNodes(nodes, drawnNodes);
        //connect the nodes.
        connectAllNodes(drawnNodes);
    }

    private void drawAllNodes(ArrayList<Node> nodes, ArrayList<DrawnNode> drawnNodes) {
        for(Node node : nodes) {
            Text text = nodeView.createText(node.getName());
            Circle circle = nodeView.encircle(text);
            drawnNodes.add(
                    new DrawnNode(node).withCircle(circle)
                            .withText(text)
                            .build()
            );
            flowContent.getChildren().addAll(circle, text);
        }
    }

    private void connectAllNodes(ArrayList<DrawnNode> drawnNodes) {
        int offsetX = 100;
        int offsetY = 100;
        int index = 0;

        for(DrawnNode node : drawnNodes) {
            List<Node> edges = node.getNode().getEdges();
            nodeView.setPosition(node.getCircle(), node.getText(), offsetX, offsetY);
            if((index + 1) < drawnNodes.size() && edges != null) {
                for(Node edge : edges) {
                    for(DrawnNode n: drawnNodes) {
                        if(n.getNode() == edge) {
                            flowContent.getChildren().add(nodeView.connectNodes(node.getCircle(), n.getCircle()));
                        }
                    }
                }
            }
            if(index % 5 == 0) {
                offsetY += 250;
                offsetX = 0;
            }
            offsetX += 300;
            index++;
        }
    }
}

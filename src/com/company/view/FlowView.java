package com.company.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class FlowView {
    final private Stage primaryStage;
    final private FileReaderView fileReaderView;
    final private Pane flowContent;
    final private CodeView codeView;
    final private FlowGeneratorView flowGenerator;


    public FlowView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.flowContent = new Pane();
        this.flowGenerator = new FlowGeneratorView(flowContent);
        this.codeView = new CodeView();
        this.fileReaderView = new FileReaderView(flowGenerator, codeView);
    }

    public void drawView() {
        ScrollPane scrollPane = new ScrollPane(flowContent);
        Pane root = new Pane(scrollPane);

        Label title = new Label("Voer een bestand in om de binary counter te starten!");
        title.setStyle(getTitleStyling());

        Button button = setFileButton();
        StackPane codeWrapper = codeView.generateCodeView();
        Scene scene = new Scene(new BorderPane(root, title, button, codeWrapper, null), 700, 500);

        primaryStage.setScene(scene);
        setPreferredSizes(codeWrapper, scrollPane);
        primaryStage.show();
    }

    private Button setFileButton() {
        Button button = new Button("Choose a file");
        fileReaderView.setFileListener(button, primaryStage);
        BorderPane.setAlignment(button, Pos.CENTER);
        BorderPane.setMargin(button, new Insets(10));
        return button;
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
}

package com.company;
import com.company.model.nodes.Node;
import com.company.view.FlowView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowView flowView = new FlowView(primaryStage);
        flowView.drawView();
    }

}

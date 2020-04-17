package com.company.view;

import com.company.NodeGenerator;
import com.company.model.nodes.Node;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FileReaderView {

    private ArrayList<Node> nodes;
    private NodeGenerator nodeGenerator;
    private FlowGeneratorView flowGenerator;
    private CodeView codeView;

    public FileReaderView(FlowGeneratorView flowGenerator, CodeView codeView) {
        this.nodeGenerator = new NodeGenerator();
        this.flowGenerator = flowGenerator;
        this.codeView = codeView;
    }

    public void setFileListener(Button button, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        button.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = fileChooser.showOpenDialog(stage);
                        if (file != null) {
                            openFile(file);
                        }
                    }
                });
    }

    public void openFile(File file) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(file.getPath()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException ex) {
            Logger.getLogger(
                    FlowView.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
        nodes = nodeGenerator.getNodes(contentBuilder.toString());
        codeView.editText(contentBuilder.toString());
        this.flowGenerator.generateFlow(nodes);
    }
}

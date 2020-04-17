package com.company.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class NodeView {
    public NodeView() {

    }

    public Arrow connectNodes(Circle node1, Circle node2) {
        Line line = new Line();
        Arrow arrow = new Arrow();

        // bind ends of line:
        arrow.startXProperty().bind(node1.centerXProperty().add(node1.translateXProperty()));
        arrow.startYProperty().bind(node1.centerYProperty().add(node1.translateYProperty()));
        arrow.endXProperty().bind(node2.centerXProperty().add(node2.translateXProperty()));
        arrow.endYProperty().bind(node2.centerYProperty().add(node2.translateYProperty()));

        return arrow;
    }

    public void setPosition(Circle circle, Text text, int xpos, int ypos) {
        circle.setTranslateX(xpos);
        circle.setTranslateY(ypos);
        text.setTranslateX(xpos - 70);
        text.setTranslateY(ypos + 20);
    }

    public Text createText(String string) {
        Text text = new Text(string);
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setStyle(
                "-fx-font-family: \"Times New Roman\";" +
                        "-fx-font-style: italic;" +
                        "-fx-font-size: 48px;"
        );
        return text;
    }
    public Circle encircle(Text text) {
        Circle circle = new Circle();
        circle.setFill(Color.ORCHID);
        final double PADDING = 50;
        circle.setRadius(100);

        return circle;
    }

//    private void animateNodes(Button button, Circle circle1, Circle circle2) {
//        TranslateTransition circle1Animation = new TranslateTransition(Duration.seconds(1), circle1);
//        circle1Animation.setByY(150);
//        TranslateTransition circle2Animation = new TranslateTransition(Duration.seconds(1), circle2);
//        circle2Animation.setByX(150);
//
//        ParallelTransition animation = new ParallelTransition(circle1Animation, circle2Animation);
//
//        animation.setAutoReverse(true);
//        animation.setCycleCount(2);
//        button.disableProperty().bind(animation.statusProperty().isEqualTo(Animation.Status.RUNNING));
//        button.setOnAction(e -> animation.play());
//    }
//
//    private double getWidth(Text text) {
//        new Scene(new Group(text));
//        text.applyCss();
//
//        return text.getLayoutBounds().getWidth();
//    }

}

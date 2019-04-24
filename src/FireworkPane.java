// Assignment #: Arizona State University CSE205 #12
//         Name: Zhengjun Li
//    StudentID: 1216057583
//      Lecture: T/Th 1:30
//  Description: FireworkPane displays a firework on a black background

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class FireworkPane extends Pane { //declare members of class
    private double centerX;
    private double centerY;
    private double radius = 25.0;
    private double radiusLimit;
    private Color color;
    private int beamNum;
    private double step = 2.0;
    private double angleSize;
    private Timeline timeline1;

    public FireworkPane(Color initColor, int width) { //constructor of Firework Pane
        this.setStyle("-fx-background-color: black;");
        color = initColor;
        centerX = width/2;
        centerY = width/2;
        radiusLimit = (width-10)/4.0;
        beamNum = 8;
        angleSize = 360/(beamNum*2);

        for (int currentAngle=0; currentAngle <= 360; currentAngle += 2*angleSize) //creates arcs based on initial attributes
        {
            Arc arc1 = new Arc(centerX, centerY, radius, radius, currentAngle, angleSize);
            arc1.setFill(color);
            arc1.setStroke(color);
            arc1.setType(ArcType.ROUND);
            this.getChildren().add(arc1);
        }



        KeyFrame keyframe1 = new KeyFrame(Duration.millis(500), new FireworkHandler()); //creates the keyframe to be used in a timeline
        timeline1 = new Timeline();
        timeline1.getKeyFrames().addAll(keyframe1);
        timeline1.setCycleCount(Timeline.INDEFINITE);       //timeline animation is also started
        timeline1.setRate(20);
        timeline1.play();

    }

    public void resume() { //resumes timeline
        timeline1.play();

    }

    public void suspend() { //pauses timeline
        timeline1.pause();

    }

    public void changeColor(Color input) { //changes beam color
        color = input;

    }

    public void setBeamNumber(int a) { //changes beam count
        beamNum = a;
        angleSize = 360/(beamNum*2);

    }

    public void setRate(int a) { //changes timeline rate
        timeline1.setRate(a);

    }

    private class FireworkHandler implements EventHandler<ActionEvent> { //listener that is executed everytime a keyframe is finished in the timeline
        public void handle(ActionEvent event) {
            getChildren().clear();
            angleSize = 360/(beamNum*2);
            radius = radius+step;
            for (int currentAngle=0; currentAngle <= 360; currentAngle += 2*angleSize) //creates new arcs with new 'radius' based on 'step' value
            {
                Arc arc1 = new Arc(centerX, centerY, radius, radius, currentAngle, angleSize);
                arc1.setFill(color);
                arc1.setStroke(color);
                arc1.setType(ArcType.ROUND);
                getChildren().add(arc1);
            }
            if (radius >= radiusLimit) {
                radius = 0;
            }
        }

    }

}



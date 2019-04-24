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

public class FireworkPane extends Pane {
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

        color = initColor;
        centerX = width/2;
        centerY = width/2;
        radiusLimit = (width-10)/4.0;
        beamNum = 8;
        angleSize = 360/(beamNum*2);
        //background should be black

        for (int currentAngle=0; currentAngle <= 360; currentAngle += 2*angleSize)
        {
            Arc arc1 = new Arc(centerX, centerY, radius, radius, currentAngle, angleSize);
            arc1.setFill(color);
            arc1.setStroke(color);
            arc1.setType(ArcType.ROUND);
            this.getChildren().add(arc1);
        }

        KeyFrame keyframe1 = new KeyFrame(Duration.millis(500), new FireworkHandler());

        timeline1.setCycleCount(Timeline.INDEFINITE);
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
        angleSize = 360.0/(beamNum*2);

    }

    public void setRate(int a) { //changes timeline rate
        timeline1.setRate(a);

    }

    private class FireworkHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            getChildren().clear();
            angleSize = 360/(beamNum*2);
            for (int currentAngle=0; currentAngle <= 360; currentAngle += 2*angleSize)
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



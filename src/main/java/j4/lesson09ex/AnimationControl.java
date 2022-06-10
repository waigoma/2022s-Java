package j4.lesson09ex;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationControl  extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage Stage) throws Exception {
        BorderPane bp = new BorderPane();
        HBox hb = new HBox();
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();

        Image img = new Image(this.getClass().getResourceAsStream("/icon2.png"));
        ImageView imgv = new ImageView(img);
        imgv.setFitHeight(40);
        imgv.setFitWidth(40);

        Rectangle rect = new Rectangle(40, 40);
        rect.setFill(Color.GREEN);

        Button buttonSL = new Button("Sleep");
        Button buttonSS = new Button("Stop");
        Button button1 = new Button("Horizontal");
        Button button2 = new Button("Slow");

        hb.getChildren().addAll(buttonSL, buttonSS);
        hb.setAlignment(Pos.CENTER);

        hb1.getChildren().addAll(imgv, rect);
        hb2.getChildren().addAll(button1, button2);

        hb1.setSpacing(80);
        hb2.setSpacing(80);

        hb1.setAlignment(Pos.CENTER);
        hb2.setAlignment(Pos.CENTER);

        bp.setTop(hb);
        bp.setCenter(hb1);
        bp.setBottom(hb2);

        Scene scene = new Scene(bp, 300, 200);
        Stage.setScene(scene);
        Stage.show();

        // two animation threads
        final TranslateTransition[] anim1 = {new TranslateTransition(Duration.seconds(1), imgv)};
        anim1[0].setAutoReverse(true);
        anim1[0].setCycleCount(TranslateTransition.INDEFINITE);

        final RotateTransition[] anim2 = {new RotateTransition(Duration.seconds(1), rect)};
        anim2[0].setInterpolator(Interpolator.LINEAR);
        anim2[0].setCycleCount(RotateTransition.INDEFINITE);

        // button event handle
        buttonSL.setOnAction(new ButtonEventHandler());
        buttonSS.addEventHandler(ActionEvent.ACTION, event -> {
            if (buttonSS.getText().equals("Stop")) {
                buttonSS.setText("Start");
                anim1[0].pause();
                anim1[0].stop();
                anim2[0].pause();
                anim2[0].stop();
            } else {
                buttonSS.setText("Stop");
                anim1[0].play();
                anim2[0].play();
            }
        });

        button1.addEventHandler(ActionEvent.ACTION, e -> {
            anim1[0] = new TranslateTransition(Duration.seconds(1), imgv);
            anim1[0].setAutoReverse(true);
            anim1[0].setCycleCount(TranslateTransition.INDEFINITE);
            if (button1.getText().equals("Horizontal")) {
                anim1[0].setFromY(-50);
                anim1[0].setToY(50);
                anim1[0].setFromX(0);
                anim1[0].setToX(0);
                button1.setText("Vertical");
            } else {
                anim1[0].setFromY(0);
                anim1[0].setToY(0);
                anim1[0].setFromX(-50);
                anim1[0].setToX(50);
                button1.setText("Horizontal");
            }
            anim1[0].play();
        });

        button2.addEventHandler(ActionEvent.ACTION,
                e -> {
                    //180 degrees per second
                    if (button2.getText().equals("Slow")) {
                        anim2[0] = new RotateTransition(Duration.seconds(2), rect);
                        button2.setText("Fast");
                    } else {
                        anim2[0] = new RotateTransition(Duration.seconds(.5), rect);
                        button2.setText("Slow");
                    }
                    anim2[0].setInterpolator(Interpolator.LINEAR);
                    anim2[0].setCycleCount(RotateTransition.INDEFINITE);
                    anim2[0].setByAngle(180); //180 degrees per second
                    anim2[0].play();
                });
    }
    class ButtonEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            try {
                Thread.sleep(3000); //3 seconds
            }
            catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
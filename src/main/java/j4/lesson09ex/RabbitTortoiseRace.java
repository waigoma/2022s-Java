package j4.lesson09ex;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Arrays;

public class RabbitTortoiseRace  extends Application {
    private static final int CANVAS_WIDTH = 400;
    private static final int CANVAS_HEIGHT = 50;

    private static final String RABBIT = "Rabbit";
    private static final String TORTOISE = "Tortoise";

    private final Image rabbitImg = new Image(this.getClass().getResourceAsStream("/rabbit.png"), -1, 40, true, true);
    private final Image tortoiseImg = new Image(this.getClass().getResourceAsStream("/tortoise.png"), -1, 40, true, true);

    private final Canvas rabbitCanvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    private final Canvas tortoiseCanvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);

    private final Label title = new Label("Rabbit vs Tortoise");
    private final Label result = new Label();

    private final Button start = new Button("Start Count");

    private Count ct1, ct2;
    private Thread rabbitThread, tortoiseThread;
    private final int maxStep = 100;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 240);

        Arrays.asList(title, result).forEach(label -> label.setFont(Font.font("Century", FontWeight.BOLD, 20)));

        start.setOnAction(new ButtonEventHandler());

        initCanvas(rabbitCanvas, Color.BLUE, rabbitImg);
        initCanvas(tortoiseCanvas, Color.YELLOW, tortoiseImg);

        ct1 = new Count(RABBIT, rabbitCanvas);
        ct2 = new Count(TORTOISE, tortoiseCanvas);

        HBox buttonHB = new HBox();
        buttonHB.getChildren().addAll(start);
        buttonHB.setAlignment(Pos.CENTER);

        VBox contentVB = new VBox();
        contentVB.getChildren().addAll(title, rabbitCanvas, tortoiseCanvas, buttonHB, result);
        contentVB.setAlignment(Pos.CENTER);
        contentVB.setSpacing(5);

        root.setCenter(contentVB);

        stage.setScene(scene);
        stage.setTitle("ProgressThreadFX");
        stage.show();
    }

    private void initCanvas(Canvas canvas, Color color, Image img) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(img, 0, 0);
    }

    private void moveImage(Canvas canvas, Color color, Image img, int move) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(color);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(img, .9 * move * CANVAS_WIDTH / maxStep, 0);
    }

    private class ButtonEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Button tmp = (Button) e.getSource();

            if (tmp.getText().equals("Start Count")) {
                rabbitThread = new Thread(ct1);
                tortoiseThread = new Thread(ct2);

                rabbitThread.start();
                tortoiseThread.start();

                start.setDisable(true);
            }
        }
    }

    public class Count implements Runnable {
        private final String s_tmp;
        private final Canvas canvas;
        private int i = 0;
        private long ms;

        public Count(String s, Canvas canvas) {
            s_tmp = s;
            this.canvas = canvas;
        }

        @Override
        public void run() {
            ms = System.currentTimeMillis();
            for (i = 0; i < maxStep; i++) {
                try {
                    if (s_tmp.equals(RABBIT)) {
                        int rand = (int) (Math.random() * 100);
                        int sleep;
                        if (rand < 10) sleep = (int) (Math.random() * 1000);
                        else sleep = (int) (Math.random() * 100);
                        Thread.sleep(sleep);
                    } else {
                        int sleep = (int) (Math.random() * 200);
                        Thread.sleep(sleep);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    long ms_tmp = System.currentTimeMillis() - ms;
                    if (s_tmp.equals(RABBIT)) {
                        moveImage(canvas, Color.BLUE, rabbitImg, i);
                    } else {
                        moveImage(canvas, Color.YELLOW, tortoiseImg, i);
                    }

                    if (i == maxStep) {
                        rabbitThread.stop();
                        tortoiseThread.stop();

                        result.setText("Winner: " + s_tmp + "   Cost: " + ms_tmp / 1000 + "s " + ms_tmp % 1000 + "ms");

                        start.setDisable(false);
                    }
                });
            }
        }
    }

}

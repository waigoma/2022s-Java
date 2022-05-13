package j4.lesson05ex;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class RandNumGenerator_FX  extends Application {
    private Canvas canvas;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        // 基礎パネル
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 500, 500);

        // タイトル
        Label topLb = new Label("Random Number Generator");
        topLb.setFont(Font.font("Century", 24));

        // タイトルレイアウト
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.getChildren().add(topLb);

        // Canvas を追加
        canvas = new Canvas(450, 400);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.strokeRect(0, 0, 450, 400);

        // ボタンを追加
        Button normalButton = new Button("Normal Distribution");
        normalButton.setFont(Font.font("Century", 18));
        normalButton.setOnMouseClicked(new NormalButtonEvent());

        Button uniformButton = new Button("Uniform Distribution");
        uniformButton.setFont(Font.font("Century", 18));
        uniformButton.setOnMouseClicked(new UniformButtonEvent());

        // ボタンレイアウト
        HBox hb = new HBox();
        hb.setPrefSize(250,50);
        hb.setSpacing(50);
        hb.getChildren().add(normalButton);
        hb.getChildren().add(uniformButton);
        hb.setAlignment(Pos.CENTER);

        // パメルにタイトル追加
        root.setTop(hBox);

        // パネルにキャンバス追加
        root.setCenter(canvas);

        // パネルにボタン追加
        root.setBottom(hb);

        stage.setScene(scene);
        stage.setTitle("RandNumGenerator_FX");
        stage.show();
    }

    private int[] getNormal() {
        Random r = new Random();
        int x = (int) (r.nextGaussian() * 50 + 225);
        int y = (int) (r.nextGaussian() * 50 + 200);
        return new int[]{x, y};
    }

    private int[] getUniform() {
        int x = (int) (Math.random() * 450);
        int y = (int) (Math.random() * 400);
        return new int[]{x, y};
    }

    private int[] getRGB() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        return new int[]{r, g, b};
    }

    private class NormalButtonEvent implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, 450, 400);
            gc.strokeRect(0, 0, 450, 400);

            for (int i = 0; i < 1000; i++) {
                int[] rgb = getRGB();
                int[] xy = getNormal();

                gc.setFill(Color.rgb(rgb[0], rgb[1], rgb[2], 1.0));
                gc.fillOval(xy[0], xy[1], 5, 5);
            }
        }
    }

    private class UniformButtonEvent implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, 450, 400);
            gc.strokeRect(0, 0, 450, 400);

            for (int i = 0; i < 1000; i++) {
                int[] rgb = getRGB();
                int[] xy = getUniform();

                gc.setFill(Color.rgb(rgb[0], rgb[1], rgb[2], 1.0));
                gc.fillOval(xy[0], xy[1], 5, 5);
            }
        }
    }
}

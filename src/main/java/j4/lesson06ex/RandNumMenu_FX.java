package j4.lesson06ex;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RandNumMenu_FX extends Application {
    private static final int CANVAS_WIDTH = 420;
    private static final int CANVAS_HEIGHT = 100;
    public static final double FONT_SIZE = 18;

    private Canvas canvas;
    private final ArrayList<Integer> points = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 500, 150);

        // メニューバー
        MenuBar menuBar = new MenuBar();
        Menu operation = new Menu("Operation");
        Menu generation = new Menu("Generation");
        MenuItem exit = new MenuItem("Exit");
        Arrays.asList(
                new MenuItem("20 Points"),
                new MenuItem("40 Points")
        ).forEach(opItem -> {
            opItem.setOnAction(new OperationEventListener());
            generation.getItems().add(opItem);
        });
        operation.getItems().addAll(generation, exit);

        Menu function = new Menu("Function");
        Arrays.asList(
                new MenuItem("Minimum"),
                new MenuItem("Maximum"),
                new MenuItem("Average")
        ).forEach(funcItem -> {
            funcItem.setOnAction(new FunctionEventListener());
            function.getItems().add(funcItem);
        });

        menuBar.getMenus().add(operation);
        menuBar.getMenus().add(function);

        // Canvas
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        drawNumberLine();
        drawTitle("Random Number Generator");


        root.setTop(menuBar);
        root.setCenter(canvas);

        stage.setScene(scene);
        stage.setTitle("RandomNumberMenu_FX");
        stage.show();
    }

    private void drawNumberLine() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // キャンバスを消す
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        gc.setStroke(Color.BLACK);
        // 座標軸を描画
        gc.strokeLine(0, CANVAS_HEIGHT * 0.85, CANVAS_WIDTH, CANVAS_HEIGHT * 0.85);
        gc.strokeLine(0, CANVAS_HEIGHT * 0.85, 0, CANVAS_HEIGHT * 0.8);
        gc.strokeLine(CANVAS_WIDTH / 2f, CANVAS_HEIGHT * 0.85, CANVAS_WIDTH / 2f, CANVAS_HEIGHT * 0.8);
        gc.strokeLine(CANVAS_WIDTH, CANVAS_HEIGHT * 0.85, CANVAS_WIDTH, CANVAS_HEIGHT * 0.8);
        // 座標軸のラベルを描画
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("System", 12));
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("0", 0, CANVAS_HEIGHT);
        gc.fillText("50", CANVAS_WIDTH * 0.48, CANVAS_HEIGHT);
        gc.fillText("100", CANVAS_WIDTH * 0.95, CANVAS_HEIGHT);
    }

    private void drawTitle(String text) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT * 0.5);
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font("Century", FONT_SIZE));
        gc.fillText(text, CANVAS_WIDTH / 2f, FONT_SIZE * 1.5f);
    }

    private void drawPoint(double x, double y, Color color) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(color);
        gc.fillOval(x, y, 5, 5);
    }

    private Color getRandomColor() {
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        return Color.rgb(red, green, blue, 1.0);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class OperationEventListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String menuName = ((MenuItem) event.getSource()).getText();
            switch (menuName) {
                case "20 Points":
                    draw(20);
                    break;
                case "40 Points":
                    draw(40);
                    break;
                case "Exit":
                    System.exit(0);
                    break;
            }
        }

        private void draw(int num) {
            drawNumberLine();
            drawTitle("Generated " + num + " random points");
            points.clear();
            drawRandomPoints(num);
        }

        private void drawRandomPoints(int num) {
            for (int i = 0; i < num; i++) {
                int x = (int) (Math.random() * CANVAS_WIDTH * 0.95);
                points.add(x);
                drawPoint(x, CANVAS_HEIGHT * 0.825, getRandomColor());
            }
        }
    }

    private class FunctionEventListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String menuName = ((MenuItem) event.getSource()).getText();
            switch (menuName) {
                case "Minimum":
                    drawTitle("Minimum Number: " + getMinimum());
                    break;
                case "Maximum":
                    drawTitle("Maximum Number: " + getMaximum());
                    break;
                case "Average":
                    drawTitle("Average Number: " + getAverage());
                    break;
            }
        }

        private int getPoint(int num) {
            return (int) (num / (CANVAS_WIDTH * 0.95) * 100);
        }

        private int getMinimum() {
            return getPoint(Collections.min(points));
        }

        private int getMaximum() {
            return getPoint(Collections.max(points));
        }

        private int getAverage() {
            int sum = 0;
            for (int x : points) {
                sum += getPoint(x);
            }
            return sum / points.size();
        }
    }
}

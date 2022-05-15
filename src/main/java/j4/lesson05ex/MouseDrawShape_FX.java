package j4.lesson05ex;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MouseDrawShape_FX extends Application {
    private static final Image squareFill = new Image("file:img/squareFill.png");
    private static final Image squareNoFill = new Image("file:img/squareNoFill.png");

    private Canvas canvas;
    private boolean isFilled = false;
    private Color color = Color.BLUE;

    @Override
    public void start(Stage stage) {
        // パネル作成
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 500, 500);

        // ラベル定義
        Label label = new Label("Click mouse to draw a square");
        label.setFont(Font.font("Century", 24));
        label.setTextFill(Color.BLUE);

        // Canvas定義
        canvas = new Canvas(500, 400);
        MouseEventHandler eventHandler = new MouseEventHandler();
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, eventHandler);
        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, eventHandler);

        // 選択可能項目定義
        Button fillButton = createButton(squareFill, "Fill");
        fillButton.setOnAction(e -> isFilled = true);

        Button noFillButton = createButton(squareNoFill, "No Fill");
        noFillButton.setOnAction(e -> isFilled = false);

        ComboBox<String> colorComboBox = new ComboBox<>();
        ObservableList<String> ol = FXCollections.observableArrayList("Blue", "Black", "Red");
        colorComboBox.setItems(ol);
        colorComboBox.setValue("Blue");
        colorComboBox.setOnAction(e -> {
            switch (colorComboBox.getValue()) {
                case "Blue":
                    color = Color.BLUE;
                    break;
                case "Black":
                    color = Color.BLACK;
                    break;
                case "Red":
                    color = Color.RED;
                    break;
            }
        });

        // ラベル中央揃え用
        HBox labelHBox = new HBox();
        labelHBox.getChildren().add(label);
        labelHBox.setAlignment(Pos.CENTER);

        // ボタン配置用
        GridPane grid = new GridPane();
        grid.setHgap(50);
        grid.setVgap(10);
        grid.add(fillButton, 0, 0);
        grid.add(noFillButton, 1, 0);
        grid.add(colorComboBox, 2, 0);

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(grid);
        buttonHBox.setAlignment(Pos.CENTER);

        // パネルに配置
        root.setTop(labelHBox);
        root.setCenter(canvas);
        root.setBottom(buttonHBox);

        // ステージに追加
        stage.setScene(scene);
        stage.setTitle("MouseDrawShape_FX");
        stage.show();
    }

    // ボタンにイメージと機能を持たせた状態で作成
    private Button createButton(Image image, String text) {
        Button btn = new Button(text);
        btn.setGraphic(createScaledImageView(image, 50));
        return btn;
    }

    // イメージを指定サイズにリサイズしてImageViewを作成
    private ImageView createScaledImageView(Image img, int height) {
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(height);
        imgView.setFitWidth(img.getWidth() * height / img.getHeight());
        return imgView;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class MouseEventHandler implements EventHandler<MouseEvent> {
        private int x1, y1, x2, y2;

        @Override
        public void handle(MouseEvent event) {
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                x1 = (int) event.getX();
                y1 = (int) event.getY();
            } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                x2 = (int) event.getX();
                y2 = (int) event.getY();
                draw();
            }
        }

        private void draw() {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setFill(color);
            gc.setStroke(color);

            int width;
            int height;
            if (x2 > x1) {
                width = x2 - x1;
            } else {
                width = x1 - x2;
                this.x1 = x2;
            }

            if (y2 > y1) {
                height = y2 - y1;
            } else {
                height = y1 - y2;
                this.y1 = y2;
            }

            if (isFilled) gc.fillRect(x1, y1, width, height);
            else gc.strokeRect(x1, y1, width, height);
        }
    }
}

package j4.lesson05ex;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Multiplication99Table_FX extends Application {
    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 380, 400);

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(10, 10, 10, 10));

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                Label label = new Label(String.valueOf(i * j));
                label.setFont(Font.font("Century", FontWeight.BOLD, 24));
                grid.add(label, i - 1, j - 1);
            }
        }

        root.getChildren().add(grid);

        stage.setScene(scene);
        stage.setTitle("Multiplication99Table_FX");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

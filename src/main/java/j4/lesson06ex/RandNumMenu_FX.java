package j4.lesson06ex;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class RandNumMenu_FX extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 500, 150);

        // メニューバー
        MenuBar menuBar = new MenuBar();
        Menu operation = new Menu("Operation");
        Menu generation = new Menu("Generation");
        MenuItem twenty = new MenuItem("20 Points");
        MenuItem forty = new MenuItem("40 Points");
        MenuItem exit = new MenuItem("Exit");

        generation.getItems().addAll(twenty, forty);
        operation.getItems().addAll(generation, exit);

        Menu function = new Menu("Function");
        Arrays.asList(
                new MenuItem("Minimum"),
                new MenuItem("Maximum"),
                new MenuItem("Average")
        ).forEach(funcItem -> {
            function.getItems().add(funcItem);
        });

        menuBar.getMenus().add(operation);
        menuBar.getMenus().add(function);

        //
        VBox vBox = new VBox();




        root.setTop(menuBar);

        stage.setScene(scene);
        stage.setTitle("RandomNumberMenu_FX");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package j4.lesson05ex;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class PhoneCallPanelEvt_FX extends Application {
    private Label label;
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 260, 300);

        label = new Label("");

        GridPane grid = new GridPane();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class PhoneButton implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            System.out.println(e.getEventType());
        }
    }
}

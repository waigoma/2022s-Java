package j4.lesson06ex;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;

public class ChatBox_FX extends Application {
    private BorderPane root;
    private TextArea display;
    private TextField inputA;
    private TextField inputB;

    @Override
    public void start(Stage stage) {
        // root panel
        root = new BorderPane();
        Scene scene = new Scene(root, 600, 500);

        // メニューバー
        MenuBar menuBar = new MenuBar();
        Menu bgColor = new Menu("Background Color");
        Arrays.asList(
                new MenuItem("White"),
                new MenuItem("Yellow"),
                new MenuItem("Gray")
        ).forEach(menuItem -> {
            menuItem.setOnAction(new MenuEventHandler());
            bgColor.getItems().add(menuItem);
        });
        menuBar.getMenus().add(bgColor);

        // テキストエリア
        HBox area = new HBox();
        ScrollPane scrollPane = new ScrollPane();
        display = new TextArea();
        display.setPrefSize(550, 380);
        display.setEditable(false);
        display.setFont(Font.font("Century", 24));
        scrollPane.setContent(display);
        area.getChildren().add(scrollPane);
        area.setAlignment(Pos.CENTER);
        area.setPadding(new Insets(10, 0, 0, 0));

        // テキストフィールド
        inputA = new TextField();
        inputA.setPrefSize(290, 30);
        inputA.setOnAction(new InputEventHandler());
        inputB = new TextField();
        inputB.setPrefSize(290, 30);
        inputB.setOnAction(new InputEventHandler());

        HBox field = new HBox();
        BorderPane inputPane = new BorderPane();
        inputPane.setTop(area);
        inputPane.setLeft(inputA);
        inputPane.setRight(inputB);
        BorderPane.setMargin(inputA, new Insets(10, 5, 0, 5));
        BorderPane.setMargin(inputB, new Insets(10, 5, 0, 5));
        field.getChildren().add(inputPane);
        field.setAlignment(Pos.CENTER);
        field.setSpacing(50);

        // ボタン
        HBox buttonPane = new HBox();

        ToggleGroup groupColor = new ToggleGroup();
        Arrays.asList(
                new RadioButton("Black"),
                new RadioButton("Blue"),
                new RadioButton("Red")
        ).forEach(button -> {
            button.setFont(Font.font(16));
            button.setOnAction(new ColorEventHandler());
            groupColor.getToggles().add(button);
            if (button.getText().equals("Black")) groupColor.selectToggle(button);
            buttonPane.getChildren().add(button);
        });

        ToggleGroup groupFont = new ToggleGroup();
        Arrays.asList(
                new RadioButton("Small"),
                new RadioButton("Medium"),
                new RadioButton("Large")
        ).forEach(button -> {
            button.setFont(Font.font(16));
            button.setOnAction(new FontEventHandler());
            groupFont.getToggles().add(button);
            if (button.getText().equals("Medium")) groupFont.selectToggle(button);
            buttonPane.getChildren().add(button);
        });

        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(25);

        // シーンにパネル登録
        root.setTop(menuBar);
        root.setCenter(field);
        root.setBottom(buttonPane);

        stage.setScene(scene);
        stage.setTitle("ChatBox_FX");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class MenuEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String text = ((MenuItem) event.getSource()).getText();
            switch (text) {
                case "White":
                    root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
                case "Yellow":
                    root.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
                case "Gray":
                    root.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
                    break;
            }
        }
    }

    private class InputEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String text = ((TextField) event.getSource()).getText();
            if (text.isEmpty()) return;
            if (event.getSource() == inputA) {
                display.appendText("A: " + text + "\n");
                inputA.clear();
            } else {
                display.appendText("B: " + text + "\n");
                inputB.clear();
            }
        }
    }

    private class ColorEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String text = ((RadioButton) event.getSource()).getText();
            switch (text) {
                case "Black":
                    display.setStyle("-fx-text-fill: black;");
                    break;
                case "Blue":
                    display.setStyle("-fx-text-fill: blue;");
                    break;
                case "Red":
                    display.setStyle("-fx-text-fill: red;");
                    break;
            }
        }
    }

    private class FontEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String text = ((RadioButton) event.getSource()).getText();
            switch (text) {
                case "Small":
                    display.setFont(Font.font(18));
                    break;
                case "Medium":
                    display.setFont(Font.font(24));
                    break;
                case "Large":
                    display.setFont(Font.font(32));
                    break;
            }
        }
    }
}

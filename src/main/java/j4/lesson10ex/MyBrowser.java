package j4.lesson10ex;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MyBrowser extends Application {
    private final Button loadButton = new Button("Load");
    private final Button backButton = new Button("Back");
    private final Button forwardButton = new Button("Forward");

    private final TextField urlField = new TextField();
    private final WebView webView = new WebView();

    private final ArrayList<String> history = new ArrayList<>();
    private int historyIndex = 0;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        //ペインの作成
        BorderPane root = new BorderPane();

        HBox hbox = new HBox();
        hbox.getChildren().addAll(loadButton, backButton, forwardButton);

        backButton.setDisable(true);
        forwardButton.setDisable(true);

        VBox vb = new VBox();
        vb.getChildren().addAll(hbox, urlField);

        root.setTop(vb);
        root.setCenter(webView);

        //イベントハンドラの登録
        URLEventHandler urlHandler = new URLEventHandler();
        loadButton.setOnAction(urlHandler);
        backButton.setOnAction(urlHandler);
        forwardButton.setOnAction(urlHandler);

        history.add("");
        historyIndex = 0;

        //シーンの作成
        Scene sc = new Scene(root, 600, 600);

        //ステージへの追加
        stage.setScene(sc);

        //ステージの表示
        stage.setTitle("MyBrowser");
        stage.show();
    }

    //イベントハンドラクラス
    class URLEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Button tmp = (Button) e.getSource();
            switch (tmp.getText()) {
            case "Load":
                historyAdd();
                break;
            case "Back":
                back();
                break;
            case "Forward":
                forward();
                break;
            }
            load();
            buttonUpdate();
        }

        private void historyAdd() {
            historyIndex++;
            if (historyIndex < history.size()) {
                history.set(historyIndex, urlField.getText());
            } else {
                history.add(urlField.getText());
            }
        }

        private void load() {
            webView.getEngine().load(urlField.getText());
            backButton.setDisable(false);
        }

        private void back() {
            if (historyIndex > 0) {
                urlField.setText(history.get(--historyIndex));
                forwardButton.setDisable(false);
            }
        }

        private void forward() {
            if (historyIndex < history.size() - 1) {
                urlField.setText(history.get(++historyIndex));
                backButton.setDisable(false);
            }
        }

        private void buttonUpdate() {
            if (historyIndex == 0) {
                backButton.setDisable(true);
            }

            if (historyIndex == history.size() - 1) {
                forwardButton.setDisable(true);
            }
        }
    }
}

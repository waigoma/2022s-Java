package j4.lesson05ex;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Arrays;

public class PhoneCallPanelEvt_FX extends Application {
    private Label label;
    private boolean calling = false;
    private String numberStr = "";

    @Override
    public void start(Stage stage) {
        // パネル作成
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 260, 300);

        // ラベル初期化
        label = new Label("");
        label.setFont(Font.font("Century", FontWeight.BOLD, 24));

        // ボタンレイアウト用パネル作成
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        // ボタンを作成しパネルに追加
        int num = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = new Button(num++ + "");
                initializeButton(btn);
                btn.setOnAction(event -> {
                    if (calling) return;
                    numberStr += btn.getText();
                    label.setText(numberStr);
                });
                grid.add(btn, j, i);
            }
        }

        // 残りのボタンを作成
        Button btn0 = new Button("0");
        Button callBtn = new Button("");
        Button hangupBtn = new Button("");

        // ボタンの大きさ設定
        Arrays.asList(btn0, callBtn, hangupBtn)
                .forEach(this::initializeButton);

        // ボタンにイベントハンドラを追加
        btn0.setOnAction(event -> {
            if (calling) return;
            numberStr += btn0.getText();
            label.setText(numberStr);
        });
        callBtn.setOnAction(event -> {
            if (calling) return;
            label.setText("Calling " + numberStr);
            calling = true;
        });
        hangupBtn.setOnAction(event -> {
            label.setText("HangUp");
            calling = false;
            numberStr = "";
        });

        // ボタンの画像生成
        ImageView callImgView = createImg("img/phoneCall.png");
        ImageView hangupImgView = createImg("img/phoneHangUp.png");

        // ボタンに画像適用
        callBtn.setGraphic(callImgView);
        hangupBtn.setGraphic(hangupImgView);

        // ボタンをパネルに追加
        grid.add(btn0, 0, 3);
        grid.add(callBtn, 1, 3);
        grid.add(hangupBtn, 2, 3);

        // レイアウト用パネル
        HBox labelHb = new HBox();
        labelHb.setPrefSize(260,50);
        labelHb.setSpacing(50);
        labelHb.getChildren().add(label);
        labelHb.setAlignment(Pos.CENTER);

        HBox gridHb = new HBox();
        gridHb.setPrefSize(260, 250);
        gridHb.setSpacing(50);
        gridHb.getChildren().add(grid);
        gridHb.setAlignment(Pos.CENTER);

        // パネルに追加
        root.setTop(labelHb);
        root.setCenter(gridHb);

        // ステージにシーンを追加
        stage.setScene(scene);
        stage.setTitle("PhoneCallPanelEvt_FX");
        stage.show();
    }

    private void initializeButton(Button btn) {
        btn.setFont(Font.font("Century", FontWeight.BOLD, 24));
        btn.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    private ImageView createImg(String path) {
        Image img = new Image("file:" + path);
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(40);
        imgView.setFitWidth(40);
        return imgView;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

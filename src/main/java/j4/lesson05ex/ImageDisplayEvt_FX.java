package j4.lesson05ex;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ImageDisplayEvt_FX extends Application {
    private static final Image sakura1 = new Image("file:img/sakura1.jpg");
    private static final Image sakura2 = new Image("file:img/sakura2.jpg");
    private static final Image sakura3 = new Image("file:img/sakura3.png");

    private HBox imageHBox = new HBox();

    @Override
    public void start(Stage stage) {
        // パネル作成
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 500);

        // ボタンを作成
        Button sakura1Button = createButton(sakura1);
        Button sakura2Button = createButton(sakura2);
        Button sakura3Button = createButton(sakura3);

        // ボタンをパネルに追加
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(sakura1Button, 0, 0);
        grid.add(sakura2Button, 1, 0);
        grid.add(sakura3Button, 2, 0);

        // ボタンレイアウト用パネル
        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().add(grid);
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setPadding(new Insets(10));

        // イメージレイアウト用パネル
        imageHBox = new HBox();
        imageHBox.getChildren().add(createScaledImageView(sakura1));
        imageHBox.setAlignment(Pos.CENTER);

        root.setTop(imageHBox);
        root.setBottom(buttonHBox);

        stage.setScene(scene);
        stage.setTitle("ImageDisplayEvt_FX");
        stage.show();
    }

    // ボタンにイメージと機能を持たせた状態で作成
    private Button createButton(Image image) {
        Button btn = new Button("");
        btn.setGraphic(createScaledImageView(image, 100));
        btn.setOnAction(e -> {
            imageHBox.getChildren().clear();
            imageHBox.getChildren().add(createScaledImageView(image, 375));
        });
        return btn;
    }

    // イメージを指定サイズにリサイズしてImageViewを作成
    private ImageView createScaledImageView(Image img, int height) {
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(height);
        imgView.setFitWidth(img.getWidth() * height / img.getHeight());
        return imgView;
    }

    private ImageView createScaledImageView(Image img) {
        return createScaledImageView(img, (int) img.getHeight());
    }

    public static void main(String[] args) {
        launch(args);
    }
}

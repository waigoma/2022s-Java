package j4.lesson10ex;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.InetAddress;

public class HostAccessRTT extends Application {
    private Label lb1, lb2, lb3, lb4, rtt;
    private TextField tf1, tf2, tf3, tf4;
    private Button bt;

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) throws Exception {
        //コントロールの作成
        lb1 = new Label("Input Website");
        lb2 = new Label("Host Name");
        lb3 = new Label("Canonical Host Name");
        lb4 = new Label("IP Address");
        rtt = new Label("");
        tf1 = new TextField("localhost");
        tf2 = new TextField();
        tf3 = new TextField();
        tf4 = new TextField();
        bt = new Button("Access");

        //ペインの作成
        BorderPane bp = new BorderPane();
        VBox vb = new VBox();

        //ペインへの追加
        vb.getChildren().add(lb1);
        vb.getChildren().add(tf1);
        vb.getChildren().add(lb2);
        vb.getChildren().add(tf2);
        vb.getChildren().add(lb3);
        vb.getChildren().add(tf3);
        vb.getChildren().add(lb4);
        vb.getChildren().add(tf4);
        vb.getChildren().add(rtt);

        bp.setCenter(vb);
        bp.setBottom(bt);

        //イベントハンドラの登録
        bt.setOnAction(new HostInfoEventHandler());

        //シーンの作成
        Scene sc = new Scene(bp, 300, 250);

        //ステージへの追加
        stage.setScene(sc);

        //ステージの表示
        stage.setTitle("HostAccessRTT");
        stage.show();
    }

    //イベントハンドラクラス
    class HostInfoEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            try {
                long start = System.currentTimeMillis();
                InetAddress ia = InetAddress.getByName(tf1.getText());
                long end = System.currentTimeMillis();
                tf2.setText(ia.getHostName());
                tf3.setText(ia.getCanonicalHostName());
                tf4.setText(ia.getHostAddress());
                rtt.setText("Round To Trip: " + (end - start) + "ms");
            } catch(Exception ex) {
                rtt.setText("Host cannot be reachable. Check URL!");
//                ex.printStackTrace();
            }
        }
    }
}


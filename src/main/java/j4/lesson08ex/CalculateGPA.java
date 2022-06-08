package j4.lesson08ex;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateGPA extends Application {
    private Label lb;
    private Button bt1, bt2;
    private TextField tf;
    private TextArea ta;

    private String defaultMessage = "";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        //コントロールの作成
        lb = new Label(" File Name");
        bt1 = new Button("Read");
        bt2 = new Button("Calculate GPA");
        tf = new TextField();
        ta = new TextArea();

        //ペインの作成
        BorderPane bp = new BorderPane();
        HBox hb = new HBox();
        hb.setSpacing(10);

        //ペインへの追加
        hb.getChildren().add(lb);
        hb.getChildren().add(tf);
        hb.getChildren().add(bt1);
        hb.getChildren().add(bt2);

        bp.setTop(hb);
        bp.setCenter(ta);

        //イベントハンドラの登録
        bt1.setOnAction(new ChooseEventHandler());
        bt2.setOnAction(new ChooseEventHandler());

        //シーンの作成
        Scene sc = new Scene(bp, 500, 300);

        //ステージへの追加
        stage.setScene(sc);

        //ステージの表示
        stage.setTitle("Calculate GPA");
        stage.show();
    }

    //イベントハンドラクラス
    class ChooseEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("./MyfileEx"));
            if (e.getSource() == bt1) {
                try {
                    ta.clear();
                    File flr = fc.showOpenDialog(new Stage());
                    if (flr != null) {
                        lb.setText(" Open File");
                        tf.setText(flr.getName());
                        BufferedReader br = new BufferedReader(new FileReader(flr));
                        String str;
                        while((str = br.readLine()) != null)
                            ta.appendText(str+ "\n");
                        br.close();
                        defaultMessage = ta.getText();
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == bt2) {
                ta.clear();
                ta.appendText(defaultMessage + "\nGPA: " + calculateGPA());
            }
        }

        private double calculateGPA() {
            ArrayList<String[]> list = new ArrayList<>();
            String[] strs = defaultMessage.split("\n");

            for (String str : strs) list.add(str.split(","));
            list.remove(0);

            int unitTotal = 0;
            double scoreTotal = 0;

            for (String[] str : list) {
                int unit = Integer.parseInt(str[2]);
                double score = 0;
                switch (str[1]) {
                    case "A+":
                        score = 4.0 * unit;
                        break;
                    case "A":
                        score = 3.0 * unit;
                        break;
                    case "B":
                        score = 2.0 * unit;
                        break;
                    case "C":
                        score = 1.0 * unit;
                        break;
                }
                scoreTotal += score;
                unitTotal += unit;
            }

            return Math.round(scoreTotal / unitTotal * 100) / 100.0;
        }
    }
}

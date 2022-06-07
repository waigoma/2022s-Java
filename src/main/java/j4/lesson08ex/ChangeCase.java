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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeCase extends Application {
    private Label lb;
    private Button bt1, bt2, bt3;
    private TextField tf;
    private TextArea ta;

    private boolean changedToCapital = false;

    private String defaultMessage = "";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage)throws Exception {
        //コントロールの作成
        lb = new Label(" File Name");
        bt1 = new Button("Read");
        bt2 = new Button("Write");
        bt3 = new Button("Uppercase");
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
        hb.getChildren().add(bt3);

        bp.setTop(hb);
        bp.setCenter(ta);

        //イベントハンドラの登録
        bt1.setOnAction(new ChooseEventHandler());
        bt2.setOnAction(new ChooseEventHandler());
        bt3.setOnAction(new ChooseEventHandler());

        //シーンの作成
        Scene sc = new Scene(bp, 500, 300);

        //ステージへの追加
        stage.setScene(sc);

        //ステージの表示
        stage.setTitle("Choose File");
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
                try {
                    File flw = fc.showSaveDialog(new Stage());
                    if (flw != null) {
                        lb.setText(" Save File");
                        tf.setText(flw.getName());
                        BufferedWriter bw = new BufferedWriter(new FileWriter(flw));
                        String str = ta.getText();
                        bw.write(str);
                        bw.close();
                    } else lb.setText(" File name?");
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == bt3) {
                bt3.setText(changedToCapital ? "Uppercase" : "Lowercase");
                changedToCapital = !changedToCapital;
                Pattern pt = Pattern.compile("\\w+");
                Matcher mt = pt.matcher(defaultMessage);
                int count = 0;
                int changedCount = 0;
                StringBuffer sb = new StringBuffer();
                while (mt.find()) {
                    String match = mt.group();
                    char first = changedToCapital ? match.toUpperCase().charAt(0) : match.toLowerCase().charAt(0);
                    if (first != match.charAt(0)) changedCount++;
                    String rest = match.substring(1);
                    mt.appendReplacement(sb, first + rest);
                    count++;
                }
                mt.appendTail(sb);
                ta.clear();
                ta.appendText(sb + "\n\n==============================\nTotal words: " + count + "\nChanged words: " + changedCount);
            }
        }
    }
}

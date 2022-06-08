package j4.lesson08ex;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadDrawCSVOption extends Application {
    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = 300;

    private final Label lb = new Label(" CSV File");
    private final Button bt1 = new Button("Read");
    private final Button bt2 = new Button("Display");
    private final TextField tf = new TextField();
    private final TextField yScaleField = new TextField();
    private final TextArea ta = new TextArea();
    private final Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    private final ArrayList<String[]> sourceList = new ArrayList<>();

    private String defaultText = "";
    private int maxX = 0;
    private int maxY = 0;
    private boolean isOpenFile = false;

    private float xScale = 0;
    private float yScale = 0;

    public void start(Stage stage) throws Exception
    {
        //ペインの作成
        BorderPane bp = new BorderPane();
        HBox hb = new HBox();
        hb.setSpacing(10);

        //ペインへの追加
        yScaleField.setPrefWidth(50);
        hb.getChildren().addAll(lb, tf, bt1, bt2, yScaleField);

//        vb.setStyle("-fx-background-color: #336699;");

        bp.setTop(hb);
        bp.setCenter(ta);
        bp.setBottom(canvas);

        //イベントハンドラの登録
        bt1.setOnAction(new ChooseEventHandler());
        bt2.setOnAction(new ChooseEventHandler());

        //シーンの作成
        Scene sc = new Scene(bp, 500, 500);

        //ステージへの追加
        stage.setScene(sc);

        //ステージの表示
        stage.setTitle("CSV File");
        stage.show();
    }

    private void drawNumberLine() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // キャンバスを消す
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        gc.setStroke(Color.BLACK);

        // 座標軸の大きさ
        xScale = CANVAS_WIDTH * 0.95f - CANVAS_WIDTH * 0.05f;
        yScale = CANVAS_HEIGHT * 0.95f - CANVAS_HEIGHT * 0.05f;

        // 座標軸を描画

        // 横軸
        gc.strokeLine(CANVAS_WIDTH * 0.05f, CANVAS_HEIGHT * 0.95f, CANVAS_WIDTH * 0.95f, CANVAS_HEIGHT * 0.95f);
        // 横軸矢印
        gc.strokeLine(CANVAS_WIDTH * 0.95f, CANVAS_HEIGHT * 0.95f, CANVAS_WIDTH * 0.93f, CANVAS_HEIGHT * 0.93f);
        gc.strokeLine(CANVAS_WIDTH * 0.95f, CANVAS_HEIGHT * 0.95f, CANVAS_WIDTH * 0.93f, CANVAS_HEIGHT * 0.97f);

        // 縦軸
        gc.strokeLine(CANVAS_WIDTH * 0.05f, CANVAS_HEIGHT * 0.05f, CANVAS_WIDTH * 0.05f, CANVAS_HEIGHT * 0.95f);
        // 縦軸矢印
        gc.strokeLine(CANVAS_WIDTH * 0.05f, CANVAS_HEIGHT * 0.05f, CANVAS_WIDTH * 0.04f, CANVAS_HEIGHT * 0.08f);
        gc.strokeLine(CANVAS_WIDTH * 0.05f, CANVAS_HEIGHT * 0.05f, CANVAS_WIDTH * 0.06f, CANVAS_HEIGHT * 0.08f);

        // 座標軸のラベルの初期化
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("System", 12));
        gc.setTextAlign(TextAlignment.LEFT);

        // 横軸のラベル
        gc.fillText("0", CANVAS_WIDTH * 0.025f, CANVAS_HEIGHT * 0.975f); // 原点
        gc.fillText(String.valueOf(maxX), CANVAS_WIDTH * 0.93f, CANVAS_HEIGHT * 0.995f); // 横軸
        gc.fillText(String.valueOf(maxY), 0, CANVAS_HEIGHT * 0.05f); // 縦軸
    }

    private void draw() {
        drawNumberLine();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ArrayList<ArrayList<String>> list = new ArrayList<>();

        // 扱いやすいようにデータ改変
        for (String[] source : sourceList) {
            // 初期化
            ArrayList<String> line = new ArrayList<>();
            for (String s : source) {
                if (s.equals(" ") || s.equals("")) continue;
                line.add(s);
            }
            line.remove(0);
            list.add(line);
        }

        // 色をランダムで変更できるようにリスト作成
        ArrayList<Color> colorList = new ArrayList<>();
        colorList.add(Color.BLACK);
        int colorIndex = list.get(0).size();
        for (int i = 0; i < colorIndex - 1; i++) {
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            colorList.add(Color.rgb(red, green, blue));
        }

        // 全データを x 軸毎に分割
        for (int i = 1; i < list.size(); i++) {
            // 前回の点と今回の点を使える形にする
            ArrayList<String> prevList = (ArrayList<String>) list.get(i - 1).clone();
            ArrayList<String> currList = (ArrayList<String>) list.get(i).clone();

            // それぞれの線描写
            for (int j = 0; j < prevList.size(); j++) {
                // 座標を求める
                if (prevList.get(j).equals("") || currList.get(j).equals("")) continue;
                float x1 = (i - 1) * xScale / maxX + CANVAS_WIDTH * 0.05f;
                float y1 = (maxY - Float.parseFloat(prevList.get(j))) * yScale / maxY + CANVAS_HEIGHT * 0.05f;
                float x2 = i * xScale / maxX + CANVAS_WIDTH * 0.05f;
                float y2 = (maxY - Float.parseFloat(currList.get(j))) * yScale / maxY + CANVAS_HEIGHT * 0.05f;

                // 線描写
                gc.setStroke(colorList.get(j));
                gc.strokeLine(x1, y1, x2, y2);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    //イベントハンドラクラス
    class ChooseEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("./MyfileEx"));
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("csv file", "*.csv");
            fc.getExtensionFilters().add(extensionFilter);
            if (e.getSource() == bt1) {
                try {
                    File flr = fc.showOpenDialog(new Stage());
                    if(flr != null){
                        ta.clear();
                        lb.setText(" Read CSV");
                        tf.setText(flr.getName());
                        BufferedReader br = new BufferedReader(new FileReader(flr));
                        String str;
                        while((str = br.readLine()) != null) {
                            ta.appendText(str+ "\n");
                        }
                        br.close();
                        defaultText = ta.getText();
                        isOpenFile = true;
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == bt2) {
                if (!isOpenFile || yScaleField.getText().equals("")) return;
                String[] strs = defaultText.split("\n");

                for (String str : strs) sourceList.add(str.split(","));
                maxX = Integer.parseInt(sourceList.get(sourceList.size() - 1)[0]);
                maxY = Integer.parseInt(yScaleField.getText());
                draw();
            }
        }
    }
}

package j4.lesson09ex;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.Arrays;

public class ProgressThreadFX extends Application {
    private static final String MA = "Ma";
    private static final String GUO = "Guo";
    private static final String YUKI = "Yuki";

    private static int aliveThreadCount;

    private final Label result = new Label();

    private final Button start = new Button("Start Count");
    private final Button stop = new Button("Stop Count");
    private final Button suspend  = new Button("Suspend");
    private final Button resume  = new Button("Resume");

    private final ProgressBar bar1 = new ProgressBar();
    private final ProgressBar bar2 = new ProgressBar();
    private final ProgressBar bar3 = new ProgressBar();

    private final Count ct1 = new Count(MA, bar1);
    private final Count ct2 = new Count(GUO, bar2);
    private final Count ct3  = new Count(YUKI, bar3);

    private Thread ct1Thread, ct2Thread, ct3Thread;



    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 240);

        result.setFont(Font.font("Century", FontWeight.BOLD, 20));

        Arrays.asList(bar1, bar2, bar3).forEach(bar -> {
            bar.setProgress(0);
            bar.setMinWidth(280);
        });

        start.setOnAction(new ButtonEventHandler());
        stop.setOnAction(new ButtonEventHandler());
        suspend.setOnAction(new ButtonEventHandler());
        resume.setOnAction(new ButtonEventHandler());

        stop.setDisable(true);
        suspend.setDisable(true);
        resume.setDisable(true);

        HBox bar1Hb = createLabelField(MA, bar1);
        HBox bar2Hb = createLabelField(GUO, bar2);
        HBox bar3Hb = createLabelField(YUKI, bar3);

        VBox contentVB = new VBox();
        contentVB.getChildren().addAll(bar1Hb, bar2Hb, bar3Hb, result);
        contentVB.setAlignment(Pos.CENTER);
        contentVB.setSpacing(20);

        HBox buttonHB = new HBox();
        buttonHB.getChildren().addAll(start, stop, suspend, resume);
        buttonHB.setSpacing(20);
        buttonHB.setAlignment(Pos.CENTER);

        root.setTop(buttonHB);
        root.setCenter(contentVB);

        stage.setScene(scene);
        stage.setTitle("ProgressThreadFX");
        stage.show();
    }

    private HBox createLabelField(String labelText, Node... nodes) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.setPadding(new javafx.geometry.Insets(0, 40, 0, 0));

        Label label = new Label(labelText + ": ");
        label.setFont(Font.font("Century", 18));
        hbox.getChildren().addAll(label);

        for (Node node : nodes) {
            hbox.getChildren().add(node);
        }

        return hbox;
    }

    private class ButtonEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Button tmp = (Button) e.getSource();

            if (tmp.getText().equals("Start Count")) {
                bar1.setProgress(0);
                bar2.setProgress(0);
                bar3.setProgress(0);

                ct1Thread = new Thread(ct1);
                ct2Thread = new Thread(ct2);
                ct3Thread = new Thread(ct3);

                ct1Thread.start();
                ct2Thread.start();
                ct3Thread.start();

                aliveThreadCount = 3;

                start.setDisable(true);
                stop.setDisable(false);
                suspend.setDisable(false);

            } else if (tmp.getText().equals("Stop Count")) {
                ct1Thread.stop();
                ct2Thread.stop();
                ct3Thread.stop();

                stop.setDisable(true);
                start.setDisable(false);
                suspend.setDisable(true);
                resume.setDisable(true);

            } else if (tmp.getText().equals("Suspend")) {
                ct1Thread.suspend();
                ct2Thread.suspend();
                ct3Thread.suspend();

                suspend.setDisable(true);
                resume.setDisable(false);
            } else {
                ct1Thread.resume();
                ct2Thread.resume();
                ct3Thread.resume();

                suspend.setDisable(false);
                resume.setDisable(true);
            }

        }
    }

    public class Count implements Runnable {
        private final String s_tmp;
        private final ProgressBar bar;
        private int i = 0;
        private long ms;

        public Count(String s, ProgressBar bar) {
            s_tmp = s;
            this.bar = bar;
        }

        @Override
        public void run() {
            ms = System.currentTimeMillis();
            for (i = 0; i < 10; i++) {
                try {
                    Thread.sleep((int) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Platform.runLater(() -> {
                    double progress = ((double) i) / 10;
                    bar.setProgress(progress);
                    long ms_tmp = System.currentTimeMillis() - ms;

                    if (i == 10) {
                        ct1Thread.stop();
                        ct2Thread.stop();
                        ct3Thread.stop();

                        result.setText("Winner: " + s_tmp + "   Cost: " + ms_tmp / 1000 + "s " + ms_tmp % 1000 + "ms");
                        aliveThreadCount = 0;

                        start.setDisable(false);
                        stop.setDisable(true);
                        suspend.setDisable(true);
                    }
                });
            }
        }
    }
}
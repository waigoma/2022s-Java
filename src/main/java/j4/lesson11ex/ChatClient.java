package j4.lesson11ex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ChatClient  extends Application implements Runnable {
    public static final String HOST = "localhost";
    public int PORT = ChatServer.PORT;
    private Thread th;

    private TextField tf;
    private TextArea ta;
    private Button bt1, bt2;

    private Socket sc;
    private BufferedReader br;
    private PrintWriter pw;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        ta = new TextArea();
        ta.setEditable(false);
        bt1 = new Button("Connect");
        bt2 = new Button("Disonnect");
        bt2.setDisable(true);
        tf = new TextField("Text field");
        tf.setPrefWidth(200);

        HBox hb = new HBox();
        hb.getChildren().add(bt1);
        hb.getChildren().add(tf);
        hb.getChildren().add(bt2);
        hb.setSpacing(5);
        hb.setPadding(new Insets(5, 0, 0, 3));

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(5, 5, 5, 5));

        bp.setCenter(ta);
        bp.setBottom(hb);

        bt1.setOnAction(new ConnectEventHandler());
        bt2.setOnAction(new DisconnectEventHandler());
        tf.setOnAction(new MsgEventHandler());
        th = new Thread(this);

        Scene sc = new Scene(bp, 400, 400);
        stage.setScene(sc);
        stage.setTitle("ThreadClient");
        stage.show();
    }

    //Create a thread
    public void createThread() {
        th = new Thread(this);
    }

    public void run() {
        try {
            sc = new Socket(HOST, PORT);
            ta.appendText("Connect to server ("+HOST+":"+PORT+")\n");
            br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())), true);

            while (true) {
                try {
                    String str = br.readLine();
                    ta.appendText(str + "\n");
                } catch(Exception e) {
                    br.close();
                    pw.close();
                    sc.close();
                    break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //イベントハンドラクラス
    class ConnectEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            //接続
            if (!th.isAlive()) {
                createThread();
                th.start();
            }
            bt1.setDisable(true);
            bt2.setDisable(false);
        }
    }

    class DisconnectEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            tf.setText("Text");
            pw.println("Disconnect");

            try {
                br.close();
                pw.close();
                sc.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            bt1.setDisable(false);
            bt2.setDisable(true);
        }
    }

    class MsgEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            if (th.isAlive()) {
                try {
                    String str = tf.getText();
                    pw.println(str);
                    tf.setText("");
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            } else ta.appendText("Not connect to server! Click <Connect>\n");
        }
    }
}


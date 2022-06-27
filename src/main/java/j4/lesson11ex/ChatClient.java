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

    private TextArea textArea;
    private final TextField chatField = new TextField("Text field");
    private final TextField nameField = new TextField("YourName");
    private final Button joinButton = new Button("Join");
    private final Button leaveButton = new Button("Leave");

    private Socket sc;
    private BufferedReader br;
    private PrintWriter pw;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        textArea = new TextArea();
        textArea.setEditable(false);

        chatField.setPrefWidth(200);
        nameField.setPrefWidth(75);

        leaveButton.setDisable(true);

        HBox hb = new HBox();
        hb.getChildren().addAll(chatField, nameField, joinButton, leaveButton);
        hb.setSpacing(5);
        hb.setPadding(new Insets(5, 0, 0, 3));

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5, 5, 5, 5));

        root.setCenter(textArea);
        root.setBottom(hb);

        joinButton.setOnAction(new ConnectEventHandler());
        leaveButton.setOnAction(new DisconnectEventHandler());
        chatField.setOnAction(new MsgEventHandler());

        th = new Thread(this);

        Scene sc = new Scene(root, 400, 400);
        stage.setScene(sc);
        stage.setTitle("ThreadClient");
        stage.show();
    }

    //Create a thread
    public void createThread() {
        th = new Thread(this);
    }

    @Override
    public void run() {
        try {
            sc = new Socket(HOST, PORT);
            textArea.appendText("$$Connect to server ("+HOST+":"+PORT+")\n");
            br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())), true);
            pw.println(nameField.getText());

            while (true) {
                try {
                    String str = br.readLine();
                    textArea.appendText(str + "\n");
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
            joinButton.setDisable(true);
            leaveButton.setDisable(false);
        }
    }

    class DisconnectEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            chatField.setText("Text");
            pw.println("");

            try {
                br.close();
                pw.close();
                sc.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            joinButton.setDisable(false);
            leaveButton.setDisable(true);
        }
    }

    class MsgEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            if (th.isAlive()) {
                try {
                    String str = chatField.getText();
                    pw.println(str);
                    chatField.setText("");
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                textArea.appendText("Not connect to server! Click <Connect>\n");
            }
        }
    }
}


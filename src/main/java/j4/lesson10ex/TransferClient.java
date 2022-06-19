package j4.lesson10ex;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TransferClient  extends Application {
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    private TextArea ta;
    private final Button connectButton = new Button("Connect");
    private final Button closeButton = new Button("Close");
    private final Button readFileButton = new Button("ReadFile");
    private final Button sendFileButton = new Button("SendFile");
    private TextField tf;
    private File file = null;
    private String path = "";

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        //コントロールの作成
        ta = new TextArea("##Click <Connect> to connect to server \n");
        ta.appendText("##Click <Send> or Press <EnterKey> in TextField to send its text to server\n");
        ta.appendText("##Close the connection when typing text <end> and sending it server\n");

        ta.setEditable(false);

        closeButton.setDisable(true);
        readFileButton.setDisable(true);
        sendFileButton.setDisable(true);

        tf = new TextField("Type text or show a file name");
        tf.setPrefColumnCount(15);

        HBox hb = new HBox();
        hb.getChildren().addAll(connectButton, closeButton, tf, readFileButton, sendFileButton);
        hb.setSpacing(5);

        BorderPane bp = new BorderPane();
        bp.setCenter(ta);
        bp.setBottom(hb);
        bp.setPadding(new Insets(5, 5, 5, 5));
        hb.setPadding(new Insets(5, 0, 0, 3));


        //イベントハンドラの登録
        connectButton.setOnAction(new ConnectEventHandler());
        closeButton.setOnAction(new ConnectEventHandler());
        tf.setOnAction(new ConnectEventHandler());
        readFileButton.setOnAction(new ConnectEventHandler());
        sendFileButton.setOnAction(new ConnectEventHandler());

        Scene sc = new Scene(bp, 550, 300);
        stage.setScene(sc);
        stage.setTitle("TransferClient");
        stage.show();
    }

    //イベントハンドラクラス
    class ConnectEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            try {
                if (event.getSource().equals(connectButton)) {
                    try {
                        socket = new Socket("localhost", TransferServer.PORT);
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                        connectButton.setDisable(true);
                        closeButton.setDisable(false);
                        readFileButton.setDisable(false);

                        ta.appendText("\n==Client has connected to the server==");
                    } catch (Exception e) {
                        ta.appendText("\n==Check if server is running or not?==");
                    }
                } else if (event.getSource().equals(closeButton)) {
                    try {
                        out.println("Eoc");

                        if (in.readLine().equals("BYE")) {
                            ta.appendText("\nS>C: EOC\nS>C: BYE");
                        }

                        connectButton.setDisable(false);
                        closeButton.setDisable(true);
                        readFileButton.setDisable(true);
                        sendFileButton.setDisable(true);

                        socket.close();
                        in.close();
                        out.close();

                        ta.appendText("\n**The connection has been closed**");
                    } catch (Exception e) {
                        ta.appendText("\n==Error in closing the connection==");
                    }
                } else if (event.getSource().equals(readFileButton)) {
                    FileChooser fc = new FileChooser();
                    fc.setInitialDirectory(new File("./Myfile_client"));
                    try {
                        file = fc.showOpenDialog(new Stage());

                        path = file.getAbsolutePath();
                        sendFileButton.setDisable(false);

                        ta.appendText("\n~~" + '"' + file.getName() + '"' + " is selected. Send it to server by clicking <SendFile>~~");
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (event.getSource().equals(sendFileButton)) {
                    Scanner scanner = new Scanner(file);

                    out.println("File");
                    ta.appendText("\nC>S: File");
                    ta.appendText("\nS>C: " + in.readLine());
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        out.println(line);
                        String str = in.readLine();
                        ta.appendText("\nS>C: " + str);
                    }
                    out.println("Eof");
                    ta.appendText("\nS>C: " + in.readLine());

                    sendFileButton.setDisable(true);

                    ta.appendText("\n**" + '"' + file.getName() + '"' + " has been sent to server.**");
                } else {
                    if (socket.isClosed()) return;

                    out.println(tf.getText());
                    ta.appendText("\nC>S: " + tf.getText());
                    ta.appendText("\nS>C: " + in.readLine());
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
package j4.lesson11ex;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
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

public class FTPClient  extends Application {
    public static final String HOST = "localhost";
    private Socket sc = null;
    private BufferedReader br = null;
    private PrintWriter pw = null;

    private final TextArea textArea = new TextArea("Click <Connect>to connect to Server \n");
    private final Button connectButton = new Button("Connect");
    private final Button uploadButton = new Button("Upload");
    private final Button downloadButton = new Button("Download");
    private final TextField textField = new TextField("Type text and press <EnterKey>");

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        textArea.setEditable(false);
        textField.setPrefColumnCount(15);

        HBox hb = new HBox();
        hb.getChildren().addAll(connectButton, uploadButton, downloadButton, textField);
        hb.setSpacing(5);

        BorderPane root = new BorderPane();

        root.setCenter(textArea);
        root.setBottom(hb);
        root.setPadding(new Insets(5, 5, 5, 5));
        hb.setPadding(new Insets(5, 0, 0, 3));

        Arrays.asList(connectButton, uploadButton, downloadButton).forEach(bt -> bt.setOnAction(new ConnectEventHandler()));
        textField.setOnAction(new ConnectEventHandler());

        Scene sc = new Scene(root, 500, 300);
        stage.setScene(sc);
        stage.setTitle("FTPClient");
        stage.show();
    }

    //イベントハンドラクラス
    class ConnectEventHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            try {
                if(event.getSource().equals(connectButton) && connectButton.getText().equalsIgnoreCase("Connect")) {
                    sc = new Socket(HOST, FTPServer.PORT);
                    br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
                    pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())), true);
                    pw.println("Connect");
                    connectButton.setText("Close");
                    String str = br.readLine();
                    textArea.appendText("\n" + str);
                    str = "C>S: Hello server*" + sc.getPort() + " from client*" + sc.getLocalPort();
                    pw.println(str);
                    textArea.appendText("\n" + str);
                    textArea.appendText("\n" + br.readLine());
                } else if (event.getSource().equals(connectButton) && connectButton.getText().equalsIgnoreCase("Close")) {
                    textArea.appendText("\nDisconnected! Click <Connect> to reconnect.\n");
                    pw.println("Close");
                    pw.close();
                    br.close();
                    sc.close();
                    connectButton.setText("Connect");
                } else if (event.getSource().equals(uploadButton)) {
                    FileChooser fc = new FileChooser();
                    fc.setInitialDirectory(new File("./Myfile_client"));
                    try {
                        File file = fc.showOpenDialog(new Stage());
                        textField.setText(file.getName());

                        pw.println("UpFile");
                        Scanner scanner = new Scanner(file);

                        textArea.appendText("\n## Uploading " + '"' + file.getName() + '"' + " to server...");
                        pw.println(file.getName());

                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            pw.println(line);
                        }

                        pw.println("EOF");
                        String str = br.readLine();
                        textArea.appendText("\n" + str);

                        textArea.appendText("\n## " + '"' + file.getName() + '"' + " is uploaded to server.##");
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (event.getSource().equals(downloadButton)) {
                    FileChooser fc = new FileChooser();
                    fc.setInitialDirectory(new File("./Myfile_server"));
                    try {
                        File file = fc.showOpenDialog(new Stage());
                        pw.println("DwFile");
                        pw.println(file.getName() + "," + file.getAbsolutePath());

                        textArea.appendText("\n%% Downloading " + '"' + file.getName() + '"');
                        textField.setText(file.getName());
                        while (true) {
                            String str = br.readLine();
                            if (str.equals("EOF")) {
                                break;
                            }
                            textArea.appendText("\n" + str);
                        }
                        textArea.appendText("\n%% Downloaded " + '"' + file.getName() + '"');
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (event.getSource().equals(textField)) {
                    if(!sc.isClosed()){
                        pw.println("Text");
                        pw.println("C>S: " + textField.getText());
                        textArea.appendText("\nC>S: " + textField.getText());
                    }
                } else {
                    textArea.appendText("\n Something happens");
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}



package j4.lesson07ex;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Register_FX extends Application {
    private final ComboBox<Integer> yearComboBox = new ComboBox<>();
    private final ComboBox<Integer> monthComboBox = new ComboBox<>();
    private final ComboBox<Integer> dayComboBox = new ComboBox<>();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 650, 400);

        // 左側 vbox
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: #336699;");

        Label label = new Label("Registration List");

        HBox name = createLabelField("Name: ", new TextField());
        HBox password = createLabelField("Password: ", new PasswordField());
        HBox confirm = createLabelField("Confirm: ", new PasswordField());
        HBox email = createLabelField("Email: ", new TextField());

        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadioButton = new RadioButton("Male");
        maleRadioButton.setToggleGroup(genderGroup);
        RadioButton femaleRadioButton = new RadioButton("Female");
        femaleRadioButton.setToggleGroup(genderGroup);
        HBox gender = createLabelField("", maleRadioButton, femaleRadioButton);

        initComboBox(yearComboBox, 1990, 2009);
        initComboBox(monthComboBox, 1, 12);
        initComboBox(dayComboBox, 1, 31);
        HBox birthday = createLabelField("Birthday: ", yearComboBox, monthComboBox, dayComboBox);

        CheckBox sports = new CheckBox("Sports");
        CheckBox music = new CheckBox("Music");
        CheckBox game = new CheckBox("Game");
        HBox hobby = createLabelField("Hobby: ", sports, music, game);

        TextArea textArea = new TextArea();
        textArea.setPrefSize(220, 100);
        HBox textAreaBox = createLabelField("Biography: ", false, textArea);

        Button confirmButton = new Button("Confirm");
        Button clearButton = new Button("Clear");
        HBox buttonBox = createLabelField("", confirmButton, clearButton);

        vbox.getChildren().addAll(label, name, password, confirm, email, gender, birthday, hobby, textAreaBox, buttonBox);

        // 右側 vbox
        VBox vbox2 = new VBox();
        vbox2.setAlignment(Pos.CENTER);
        Label rightTopLabel = new Label("Registration Result");
        rightTopLabel.setFont(Font.font("Century", FontWeight.BOLD, 24));

        // 上のラベル
        Label topLabel = new Label("Register & Display");
        topLabel.setFont(Font.font("Century", FontWeight.BOLD, 32));
        HBox hbox = createLabelField("", topLabel);

        // 最後のレイアウト
        root.setTop(hbox);
        root.setLeft(vbox);
        root.setCenter(vbox2);

        stage.setScene(scene);
        stage.setTitle("Register_FXOption");
        stage.show();
    }

    private void initComboBox(ComboBox<Integer> cb, int startNum, int endNum) {
        for (int i = startNum; i <= endNum; i++)
            cb.getItems().add(i);
    }

    private HBox createLabelField(String labelText, boolean isSetSize, Node... nodes) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        if (isSetSize) hbox.setPrefWidth(220);
//        hbox.setMaxSize(1000, 1000);
        Label label = new Label(labelText);
        label.setFont(Font.font("Century", 12));
        hbox.getChildren().addAll(label);

        for (Node node : nodes)
            hbox.getChildren().add(node);

        return hbox;
    }

    private HBox createLabelField(String labelText, Node... nodes) {
        return createLabelField(labelText, true, nodes);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

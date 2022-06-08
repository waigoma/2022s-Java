package j4.lesson08ex;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.regex.Pattern;

public class GenerateInfo extends Application {
    private final TextField nameField = new TextField();
    private final TextField emailField = new TextField();
    private final ToggleGroup genderGroup = new ToggleGroup();
    private final TextField addressField = new TextField();
    private final TextField organizationField = new TextField();
    private final TextField studentIdField = new TextField();
    private final TextField ageField = new TextField();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 350, 300);

        // 上のラベル
        Label topLabel = new Label("Fill in Information");
        topLabel.setFont(Font.font("Century", FontWeight.BOLD, 32));
        HBox hbox = createLabelField("", topLabel);

        // 左側 vbox
        VBox leftVBox = leftVBox();

        // 最後のレイアウト
        root.setTop(hbox);
        root.setCenter(leftVBox);

        stage.setScene(scene);
        stage.setTitle("GenerateInfo");
        stage.show();
    }

    // 左側のボックス
    private VBox leftVBox() {
        HBox name = leftFieldLayout(createLabelField("Name: ", nameField));
        HBox email = leftFieldLayout(createLabelField("Email: ", emailField));

        RadioButton maleRadioButton = createRadioButtonWithGroup("Male", genderGroup);
        RadioButton femaleRadioButton = createRadioButtonWithGroup("Female", genderGroup);
        HBox gender = createLabelField("", maleRadioButton, femaleRadioButton);
        gender.setSpacing(40);
        gender.setPadding(new Insets(0, 0, 0, 10));

        HBox address = leftFieldLayout(createLabelField("Address: ", addressField));
        HBox organization = leftFieldLayout(createLabelField("Organization: ", organizationField));

        HBox studentAge = doubleFieldLayout(createLabelField("Student ID: ", false, studentIdField), createLabelField("Age: ", false, ageField));
        studentIdField.setPrefWidth(100);
        ageField.setPrefWidth(50);

        Button confirmButton = new Button("Generate");
        confirmButton.setOnAction(e -> generateFile());

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearField());

        HBox buttonBox = createLabelField("", clearButton, confirmButton);
        buttonBox.setSpacing(40);
        buttonBox.setPadding(new Insets(0, 0, 0, 10));

        // レイアウト
        VBox vbox = new VBox();
//        vbox.setStyle("-fx-background-color: #336699;");
        vbox.getChildren().addAll(name, email, gender, address, organization, studentAge, buttonBox);
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }

    // 左側のフィールドのレイアウト用
    private HBox leftFieldLayout(HBox hbox) {
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.setPadding(new Insets(0, 50, 0, 0));
        return hbox;
    }

    private HBox doubleFieldLayout(HBox hbox, HBox hbox2) {
        HBox box = new HBox();
        box.getChildren().addAll(hbox, hbox2);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(5);
        return box;
    }

    // ラジオボタンを生成してグループに追加
    private RadioButton createRadioButtonWithGroup(String text, ToggleGroup group) {
        RadioButton radioButton = new RadioButton(text);
        radioButton.setToggleGroup(group);
        return radioButton;
    }

    // サイズをセットするかどうか
    private HBox createLabelField(String labelText, boolean isSetSize, Node... nodes) {
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);

        if (isSetSize) hbox.setPrefWidth(220);

        hbox.setMaxSize(1000, 1000);
        Label label = new Label(labelText);
        label.setFont(Font.font("Century", 12));
        hbox.getChildren().addAll(label);

        for (Node node : nodes) {
            if (node instanceof TextField) ((TextField) node).setPrefWidth(200);
            hbox.getChildren().add(node);
        }

        return hbox;
    }

    // ラベルとノードを並べた HBox を作成
    private HBox createLabelField(String labelText, Node... nodes) {
        return createLabelField(labelText, true, nodes);
    }

    // フィールドの値をクリアする
    private void clearField() {
        nameField.clear();
        emailField.clear();
        genderGroup.selectToggle(null);
        addressField.clear();
        organizationField.clear();
        studentIdField.clear();
        ageField.clear();
    }

    // Registration Result を表示する
    private void generateFile() {
        try {
            FileChooser fc = new FileChooser();
            fc.setInitialDirectory(new File("./MyfileEx"));
            File flw = fc.showSaveDialog(new Stage());
            if (flw != null) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(flw));
                String name = nameField.getText() + ",";
                String email = emailField.getText() + ",";
                RadioButton gen = (RadioButton) genderGroup.getSelectedToggle();
                String gender = (gen != null ? gen.getText() + "," : ",");
                String address = addressField.getText() + ",";
                String organization = organizationField.getText() + ",";
                String studentId = studentIdField.getText() + ",";
                String age = ageField.getText();
                String str = "Name,Email,Gender,Address,Organization,Student ID,Age" +
                             "\n" +
                             name + email + gender + address + organization + studentId + age;
                bw.write(str);
                bw.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

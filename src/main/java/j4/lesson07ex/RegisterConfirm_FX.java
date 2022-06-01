package j4.lesson07ex;

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
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class RegisterConfirm_FX extends Application {
    private final ComboBox<Integer> yearComboBox = new ComboBox<>();
    private final ComboBox<Integer> monthComboBox = new ComboBox<>();
    private final ComboBox<Integer> dayComboBox = new ComboBox<>();

    private final TextField nameField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final PasswordField confirmPasswordField = new PasswordField();
    private final TextField emailField = new TextField();
    private final ToggleGroup genderGroup = new ToggleGroup();
    private final TextArea displayArea = new TextArea();
    private final CheckBox sports = new CheckBox("Sports");
    private final CheckBox music = new CheckBox("Music");
    private final CheckBox game = new CheckBox("Game");
    private final TextArea biographyArea = new TextArea();

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 650, 400);

        // 上のラベル
        Label topLabel = new Label("Register & Display");
        topLabel.setFont(Font.font("Century", FontWeight.BOLD, 32));
        HBox hbox = createLabelField("", topLabel);

        // 左側 vbox
        VBox leftVBox = leftVBox();

        // 右側 vbox
        VBox rightBox = rightBox();

        // 最後のレイアウト
        root.setTop(hbox);
        root.setLeft(leftVBox);
        root.setCenter(rightBox);

        stage.setScene(scene);
        stage.setTitle("Register_FXOption");
        stage.show();
    }

    // 左側のボックス
    private VBox leftVBox() {
        Label label = new Label("Registration List");
        label.setFont(Font.font("Century", FontWeight.BOLD, 18));

        HBox name = leftFieldLayout(createLabelField("Name: ", nameField));
        HBox password = leftFieldLayout(createLabelField("Password: ", passwordField));
        HBox confirm = leftFieldLayout(createLabelField("Confirm: ", confirmPasswordField));
        HBox email = leftFieldLayout(createLabelField("Email: ", emailField));

        RadioButton maleRadioButton = createRadioButtonWithGroup("Male", genderGroup);
        RadioButton femaleRadioButton = createRadioButtonWithGroup("Female", genderGroup);
        HBox gender = createLabelField("", maleRadioButton, femaleRadioButton);
        gender.setSpacing(40);
        gender.setPadding(new Insets(0, 0, 0, 10));

        initComboBox(yearComboBox, 1990, 2009);
        initComboBox(monthComboBox, 1, 12);
        initComboBox(dayComboBox, 1, 31);
        HBox birthday = createLabelField("Birthday: ", yearComboBox, monthComboBox, dayComboBox);
        birthday.setSpacing(5);
        yearComboBox.setValue(1990);
        monthComboBox.setValue(1);
        dayComboBox.setValue(1);

        HBox hobby = createLabelField("Hobby: ", sports, music, game);
        hobby.setSpacing(5);

        biographyArea.setPrefSize(220, 100);
        HBox textAreaBox = createLabelField("Biography: ", false, biographyArea);
        textAreaBox.setPadding(new Insets(0, 5, 0, 10));

        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> showMessage());

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearField());

        HBox buttonBox = createLabelField("", confirmButton, clearButton);
        buttonBox.setSpacing(40);
        buttonBox.setPadding(new Insets(0, 0, 0, 10));

        // レイアウト
        VBox vbox = new VBox();
//        vbox.setStyle("-fx-background-color: #336699;");
        vbox.getChildren().addAll(label, name, password, confirm, email, gender, birthday, hobby, textAreaBox, buttonBox);
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }

    // 右側のボックス
    private VBox rightBox() {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        Label rightTopLabel = new Label("Registration Result");
        rightTopLabel.setFont(Font.font("Century", FontWeight.BOLD, 18));

        displayArea.setPrefSize(220d, 290d);
        displayArea.setEditable(false);

        vbox.getChildren().addAll(rightTopLabel, displayArea);
        vbox.setPadding(new Insets(0, 15, 15, 10));
        vbox.setSpacing(10);

        return vbox;
    }

    // 左側のフィールドのレイアウト用
    private HBox leftFieldLayout(HBox hbox) {
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.setPadding(new Insets(0, 10, 0, 0));
        return hbox;
    }

    // ラジオボタンを生成してグループに追加
    private RadioButton createRadioButtonWithGroup(String text, ToggleGroup group) {
        RadioButton radioButton = new RadioButton(text);
        radioButton.setToggleGroup(group);
        return radioButton;
    }

    // 年月日のコンボボックスの初期化
    private void initComboBox(ComboBox<Integer> cb, int startNum, int endNum) {
        for (int i = startNum; i <= endNum; i++)
            cb.getItems().add(i);
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
        passwordField.clear();
        confirmPasswordField.clear();
        emailField.clear();
        genderGroup.selectToggle(null);
        yearComboBox.setValue(1990);
        monthComboBox.setValue(1);
        dayComboBox.setValue(1);
        sports.setSelected(false);
        music.setSelected(false);
        game.setSelected(false);
        biographyArea.clear();
    }

    // 数字だけかどうか
    private boolean isNumeric(String str) {
        Pattern p = Pattern.compile("[0-9]*");
        return p.matcher(str).matches();
    }

    // 範囲内の文字列の長さかどうか
    private boolean isInRange(String str) {
        return 4 >= str.length() || str.length() >= 16;
    }

    // 文字列と文字列が同じかどうか
    private boolean isSame(String str1, String str2) {
        return str1.equals(str2);
    }

    // メールアドレスの形式かどうか
    private boolean isCorrectMail(String str) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        return p.matcher(str).matches();
    }

    // Registration Result を表示する
    private void showMessage() {
        boolean isError = false;
        displayArea.setText("");

        // 名前欄のエラー検出
        if (isNumeric(nameField.getText())) {
            displayArea.appendText("User Name can't be numeric.\n");
            isError = true;
        }

        // 文字列の長さのエラー検出
        if (isInRange(nameField.getText()) || isInRange(passwordField.getText())) {
            displayArea.appendText("User Name or Password over length or too short!\n");
            isError = true;
        }

        // パスワード入力のエラー検出
        if (!isSame(String.valueOf(passwordField.getText()), String.valueOf(confirmPasswordField.getText()))) {
            displayArea.appendText("Password don't match!\n");
            isError = true;
        }

        // メールアドレスのエラー検出
        if (!isCorrectMail(emailField.getText())) {
            displayArea.appendText("Email does not meet the specification!\n");
            isError = true;
        }

        // エラーがなければ OK
        if (!isError) {
            String name = "Name: " + nameField.getText();
            String email = "Email: " + emailField.getText();
            RadioButton gen = (RadioButton) genderGroup.getSelectedToggle();
            String gender = "Gender: " + gen.getText();
            String birthday = "Birthday: " + yearComboBox.getValue() + "." + monthComboBox.getValue() + "." + dayComboBox.getValue();
            String hobby = "Hobby: " + (sports.isSelected() ? "Sports " : "") + (music.isSelected() ? "Music " : "") + (game.isSelected() ? "Game " : "");

            displayArea.appendText(
                    "Registration Succeed!\n" +
                            "\n" +
                            name + "\n" +
                            email + "\n" +
                            gender + "\n" +
                            birthday + "\n" +
                            hobby + "\n" +
                            "\n" +
                            "Biography: " + "\n" +
                            biographyArea.getText() + "\n"
            );
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

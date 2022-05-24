package j4.lesson06ex;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class UserInfoListandTable_FX extends Application {
    private Label label;
    private ListView<String> listView;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 200);

        // ラベル初期化
        label = new Label("Select list to show result.");

        // テーブル作成
        TableView<RowData> tableView = new TableView<>();
        TableColumn<RowData, String> tc1 = new TableColumn<>("Name");
        TableColumn<RowData, String> tc2 = new TableColumn<>("ID");
        TableColumn<RowData, String> tc3 = new TableColumn<>("Gender");
        TableColumn<RowData, String> tc4 = new TableColumn<>("Entrance Year");
        TableColumn<RowData, String> tc5 = new TableColumn<>("Hobby");

        tc1.setCellValueFactory(new PropertyValueFactory<>("name"));
        tc2.setCellValueFactory(new PropertyValueFactory<>("id"));
        tc3.setCellValueFactory(new PropertyValueFactory<>("gender"));
        tc4.setCellValueFactory(new PropertyValueFactory<>("entranceYear"));
        tc5.setCellValueFactory(new PropertyValueFactory<>("hobby"));

        ObservableList<RowData> ol = FXCollections.observableArrayList();
        ol.add(new RowData("GUO Ao", "16T9802", "Male", 2016, "Music"));
        ol.add(new RowData("Yuki Miyazawa", "17T0022", "Male", 2016, "Game"));

        tableView.getColumns().add(tc1);
        tableView.getColumns().add(tc2);
        tableView.getColumns().add(tc3);
        tableView.getColumns().add(tc4);
        tableView.getColumns().add(tc5);

        tableView.setItems(ol);
        tableView.getSelectionModel().selectedItemProperty().addListener(new TableChangeListener());
        tableView.setPrefWidth(400);

        // リスト作成
        listView = new ListView<>();
        listView.getSelectionModel().selectedItemProperty().addListener(new ListChangeListener());
        listView.setPrefSize(150, 200);

        // レイアウト
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(listView, tableView);

        root.setTop(label);
        root.setCenter(hbox);

        stage.setScene(scene);
        stage.setTitle("UserInfoListandTable_FX");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class RowData {
        private final SimpleStringProperty name;
        private final SimpleStringProperty id;
        private final SimpleStringProperty gender;
        private final SimpleIntegerProperty entranceYear;
        private final SimpleStringProperty hobby;

        public RowData(String name, String id, String gender, int entrance, String hobby) {
            this.name = new SimpleStringProperty(name);
            this.id = new SimpleStringProperty(id);
            this.gender = new SimpleStringProperty(gender);
            this.entranceYear = new SimpleIntegerProperty(entrance);
            this.hobby = new SimpleStringProperty(hobby);
        }

        public StringProperty nameProperty() {
            return name;
        }
        public StringProperty idProperty() {
            return id;
        }
        public StringProperty genderProperty() {
            return gender;
        }
        public IntegerProperty entranceYearProperty() {
            return entranceYear;
        }
        public StringProperty hobbyProperty() {
            return hobby;
        }
    }

    private class TableChangeListener implements ChangeListener<RowData> {
        @Override
        public void changed(ObservableValue<? extends RowData> observable, RowData oldValue, RowData newValue) {
            listView.setItems(FXCollections.observableArrayList(
                    "Name: " + newValue.nameProperty().getValue(),
                    "ID: " + newValue.idProperty().getValue(),
                    "Gender: " + newValue.genderProperty().getValue(),
                    "Entrance Year: " + newValue.entranceYearProperty().getValue().toString(),
                    "Hobby: " + newValue.hobbyProperty().getValue()
            ));
        }
    }

    private class ListChangeListener implements ChangeListener<String>
    {
        public void changed(ObservableValue ob, String bs, String as)
        {
            label.setText(as);
        }
    }
}

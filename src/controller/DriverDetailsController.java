package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DriverDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class DriverDetailsController implements Initializable {
    @FXML
    private TableView<DriverDetails> driverTable;

    @FXML
    private TableColumn<DriverDetails, String> nameCol;

    @FXML
    private TableColumn<DriverDetails, String> NICCol;

    @FXML
    private TableColumn<DriverDetails, String> LicenseNoCol;

    @FXML
    private TableColumn<DriverDetails, String> addressCol;

    @FXML
    private TableColumn<DriverDetails, String> contactCol;

    @FXML
    private JFXButton windowCloseBtn;

    @FXML
    void windowClose(ActionEvent event) {
        Stage stage = (Stage) windowCloseBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        NICCol.setCellValueFactory(new PropertyValueFactory<>("NICNo"));
        LicenseNoCol.setCellValueFactory(new PropertyValueFactory<>("licenseNo"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        driverTable.setItems(driverList);
    }

    public static ObservableList<DriverDetails> driverList = FXCollections.observableArrayList(
            new DriverDetails("Ajith","299164037445","B1211342","Colombo","074212121"),
            new DriverDetails("Kumara","149165738463","B1451675","Kaluthara","0742193931"),
            new DriverDetails("Sadun","229174125918","B8921442","Colombo","0753426341"),
            new DriverDetails("Veere","342274659221","B9023532","Colombo","0782623721"),
            new DriverDetails("Amal","443236324344","B9552207","Rathnapura","0712738292"),
            new DriverDetails("Siril","292072226661","B9292663","Kandy","0742193931"),
            new DriverDetails("Luvis","200954235210","B3233002","Kandy","0763372281")
    );
}

package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.VehicleDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class VehicleDetailsController implements Initializable {

    @FXML
    private TableView<VehicleDetails> vehicleDetailsTbl;

    @FXML
    private TableColumn<VehicleDetails, String> vehicleNumberCol;

    @FXML
    private TableColumn<VehicleDetails, String> vehicleTypeCol;

    @FXML
    private TableColumn<VehicleDetails, String> maxWeightCol;

    @FXML
    private TableColumn<VehicleDetails, String> noOfPassCol;

    @FXML
    private JFXButton closeWindowBtn;

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) closeWindowBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vehicleNumberCol.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        vehicleTypeCol.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        maxWeightCol.setCellValueFactory(new PropertyValueFactory<>("maxWeight"));
        noOfPassCol.setCellValueFactory(new PropertyValueFactory<>("passengerCount"));
        vehicleDetailsTbl.setItems(observableList);
    }

    public static ObservableList<VehicleDetails> observableList = FXCollections.observableArrayList(
            new VehicleDetails("CBC 1097","Van","1200","12"),
            new VehicleDetails("ACB 3217","Van","1200","12"),
            new VehicleDetails("NP 9324","Van","1200","12"),
            new VehicleDetails("RCB 3217","Van","1200","12"),
            new VehicleDetails("KD 9324","Van","1200","12"),

            new VehicleDetails("BCB 1393","Bus","2200","24"),


            new VehicleDetails("LP 1132","Cargo Lorry","4200","2"),
            new VehicleDetails("PC 1229","Cargo Lorry","3500","2"),
            new VehicleDetails("LB 9929","Cargo Lorry","3500","2")
    );

}

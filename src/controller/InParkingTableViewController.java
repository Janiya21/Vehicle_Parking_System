package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.InParkedVehicleDetails;
import model.VehicleDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class InParkingTableViewController implements Initializable {

    @FXML
    private TableView<InParkedVehicleDetails> vehicleParkingDetailsTbl;

    @FXML
    private TableColumn<InParkedVehicleDetails, String> colVehicleNo;

    @FXML
    private TableColumn<InParkedVehicleDetails, String> colVehicleType;

    @FXML
    private TableColumn<InParkedVehicleDetails, String> colParkingSlot;

    @FXML
    private TableColumn<InParkedVehicleDetails, String> colParkedTime;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNO"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colParkingSlot.setCellValueFactory(new PropertyValueFactory<>("parkingSlot"));
        colParkedTime.setCellValueFactory(new PropertyValueFactory<>("parkedTime"));
        vehicleParkingDetailsTbl.setItems(observableParkedVehicleList);
    }

    public static ObservableList<InParkedVehicleDetails> observableParkedVehicleList = FXCollections.observableArrayList();
}

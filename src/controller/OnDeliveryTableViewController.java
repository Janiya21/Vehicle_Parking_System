package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.InParkedVehicleDetails;
import model.OnDeliveryDetails;

import java.net.URL;
import java.util.ResourceBundle;

public class OnDeliveryTableViewController implements Initializable {

    @FXML
    private TableView<OnDeliveryDetails> onDeliveryDetailsTable;

    @FXML
    private TableColumn<OnDeliveryDetails, String> colVehicleNo;

    @FXML
    private TableColumn<OnDeliveryDetails, String> colVehicleType;

    @FXML
    private TableColumn<OnDeliveryDetails, String> colDriverName;

    @FXML
    private TableColumn<OnDeliveryDetails, String> colLeftTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        colDriverName.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        colLeftTime.setCellValueFactory(new PropertyValueFactory<>("leftTime"));
        onDeliveryDetailsTable.setItems(observableOnDeliveryVehicleList);
    }

    public static ObservableList<OnDeliveryDetails> observableOnDeliveryVehicleList = FXCollections.observableArrayList();
}

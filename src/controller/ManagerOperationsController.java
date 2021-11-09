package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerOperationsController implements Initializable {

    @FXML
    private AnchorPane changingRoot;

    @FXML
    private JFXComboBox<String> cmbVehicleIn;

    @FXML
    private JFXButton addVehicleBtn;

    @FXML
    private JFXButton addDriverBtn;

    @FXML
    private JFXButton btnBack;

    @FXML
    void addDriver(ActionEvent event) throws Exception{
        loadStage("AddDriverForm");
    }

    @FXML
    void addVehicle(ActionEvent event) throws Exception{
        loadStage("AddVehicleForm");
    }

    public void goBack(ActionEvent event) throws Exception{
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();

        loadStage("DriverPanel");
    }

    @FXML
    void comboAction(ActionEvent event) throws Exception {
        if(cmbVehicleIn.getValue().equals("On Delivery")){
            loadUI("OnDeliveryTableView");
        }else{
            loadUI("InParkingTableView");
        }
    }

    @FXML
    void showAllDrivers(ActionEvent event) throws Exception{
        loadStage("DriverDetails");
    }

    @FXML
    void showAllVehicles(ActionEvent event) throws Exception {
        loadStage("VehicleDetails");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbVehicleIn.getItems().addAll(
                "In Parking",
                "On Delivery"
        );

        try {
            loadUI("InParkingTableView");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUI(String root) throws Exception{
        URL resource = getClass().getResource("../view/"+root+".fxml");
        Parent load = FXMLLoader.load(resource);
        changingRoot.getChildren().clear();
        changingRoot.getChildren().add(load);
    }

    public void loadStage(String fxml) throws Exception{

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/"+fxml+".fxml"));

        primaryStage.setTitle("Manager Operations");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
}

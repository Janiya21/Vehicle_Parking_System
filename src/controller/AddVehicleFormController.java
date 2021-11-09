package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.VehicleDetails;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddVehicleFormController implements Initializable {

    @FXML
    private JFXTextField vehicleNumberTxt;

    @FXML
    private JFXTextField maxWeightTxt;

    @FXML
    private JFXTextField noOfPassTxt;

    @FXML
    private JFXComboBox<String> vehicleTypeCmb;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXButton addVehicleBtn;

    static int vanCount=0;
    static int lorryCount=0;
    static int busCount=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vehicleTypeCmb.getItems().addAll(
                "Van",
                "Cargo Lorry",
                "Bus"
        );
    }

    @FXML
    void addVehicle(ActionEvent event) throws IOException {

        if(vehicleNumberTxt.getText()!=null && vehicleTypeCmb.getValue()!=null && maxWeightTxt.getText()!=null && noOfPassTxt.getText()!=null){

            if( Pattern.matches("[0-9]{4}",maxWeightTxt.getText())  && Pattern.matches("[0-9]{2}",noOfPassTxt.getText())) {

                // Check Limit of the each vehicle type and increase them..
                if (vehicleTypeCmb.getValue().equals("Van")) {
                    if (vanCount < 3) {
                        addingVehicle();
                        vanCount++;
                    } else {
                        displayModalPopup(Alert.AlertType.WARNING, "Error", "Van storage exceeded..");
                    }
                } else if (vehicleTypeCmb.getValue().equals("Cargo Lorry")) {
                    if (lorryCount < 4) {
                        addingVehicle();
                        lorryCount++;
                    } else {
                        displayModalPopup(Alert.AlertType.WARNING, "Error", "Lorry storage exceeded..");
                    }
                } else if (vehicleTypeCmb.getValue().equals("Bus")) {
                    displayModalPopup(Alert.AlertType.WARNING, "Error", "Bus storage exceeded..");
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING,"Wrong Input. Please Input again...");
                alert.show();
            }

            vehicleNumberTxt.clear();
            maxWeightTxt.clear();
            noOfPassTxt.clear();
            vehicleTypeCmb.setValue(null);

        }else{
            DriverPanelController.displayModalPopup(Alert.AlertType.ERROR,"Error","Please check the form again and fill all the fields...");
        }
    }

    @FXML
    void cancel(ActionEvent event) throws Exception{
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    void addingVehicle() throws IOException {
        VehicleDetailsController.observableList.add(new VehicleDetails(vehicleNumberTxt.getText(), (String) vehicleTypeCmb.getValue(),maxWeightTxt.getText(),noOfPassTxt.getText()));

        boolean res = displayModalPopup(Alert.AlertType.CONFIRMATION,"Added Done","Go to Vehicle Details ?");

        Stage stage = (Stage) addVehicleBtn.getScene().getWindow();
        stage.close();

        if(res){
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../view/VehicleDetails.fxml"));

            primaryStage.setTitle("Manager Operations");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        }
    }

    public boolean displayModalPopup(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.getDialogPane().setPrefSize(600, 200);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
}

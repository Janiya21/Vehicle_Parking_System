package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import model.DriverDetails;
import model.VehicleDetails;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddDriverFormController implements Initializable {

    @FXML
    private JFXTextField driverNameTxt;

    @FXML
    private JFXTextField NICTxt;

    @FXML
    private JFXTextField LicenseNoTxt;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXButton addDriverBtn;

    @FXML
    private JFXTextField contactNoTxt;

    @FXML
    private JFXTextArea addressTxt;

    static int driverCount=0;

    @FXML
    void addDriver(ActionEvent event) throws Exception{

        if(driverNameTxt.getText() != null && NICTxt.getText() != null && LicenseNoTxt.getText() != null && contactNoTxt.getText() != null && addressTxt.getText() != null && driverCount<7) {

            if(Pattern.matches("B.......",LicenseNoTxt.getText()) && Pattern.matches("............",NICTxt.getText())  && Pattern.matches("[0-9]{10}",contactNoTxt.getText())) {

                DriverDetailsController.driverList.add(new DriverDetails(driverNameTxt.getText(), NICTxt.getText(), LicenseNoTxt.getText(), addressTxt.getText(), contactNoTxt.getText()));
                driverCount++;

                boolean res = displayModalPopup(Alert.AlertType.CONFIRMATION, "Added Done", "Go to Driver Details ?");

                Stage stage = (Stage) addDriverBtn.getScene().getWindow();
                stage.close();

                if (res) {
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("../view/DriverDetails.fxml"));

                    primaryStage.setTitle("Manager Operations");
                    primaryStage.setScene(new Scene(root));
                    primaryStage.setResizable(false);
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    primaryStage.show();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING,"Wrong Input. Please Input again...");
                alert.show();
            }
        }else{
            DriverPanelController.displayModalPopup(Alert.AlertType.ERROR,"Error","Please check the form again and fill all the fields...");
        }
    }

    @FXML
    void cancel(ActionEvent event) throws Exception{
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public boolean displayModalPopup(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.getDialogPane().setPrefSize(600, 200);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

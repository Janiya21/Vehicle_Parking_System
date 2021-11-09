package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManagementLoginPanelController {

    @FXML
    private JFXTextField userNameTxt;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private JFXButton cancelWindowBtn;

    @FXML
    private JFXButton logInBtn;

    @FXML
    void cancelWindowBtn(ActionEvent event) {

    }

    @FXML
    void logIn(ActionEvent event) throws Exception{
        Stage stage = (Stage) logInBtn.getScene().getWindow();
        stage.close();

        DriverPanelController.stageOfDriverPanel.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/ManagerOperations.fxml"));

        primaryStage.setTitle("Blue Ocean v-1.0.0");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}

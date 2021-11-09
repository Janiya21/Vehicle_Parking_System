package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class DriverPanelController implements Initializable {

    @FXML
    private Label lblTime;

    @FXML
    private JFXComboBox<String> cmbVehicleNo;

    @FXML
    private JFXComboBox<String> cmbVehicleOwner;

    @FXML
    private Label lblAllocatedSlot;

    @FXML
    private JFXButton parkVehicleBtn;

    @FXML
    private JFXButton deliveryShiftBtn;

    @FXML
    private JFXTextField vehicleTypeTxt;

    @FXML
    private JFXButton managersLogInBtn;

    public static Stage stageOfDriverPanel;

    // This is for store parked vehicles objects and relevant slots...
    public static Object[][] parkedVehicleContainer = new Object[2][14];
    static int parkedCount = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initClock();

        for (VehicleDetails v: VehicleDetailsController.observableList) { // add vehicle Numbers to the comboBox through VehicleDetail obsList
            cmbVehicleNo.getItems().add(v.getVehicleNo());
        }

        for (DriverDetails v: DriverDetailsController.driverList) {  // add driver names to the comboBox through DriverDetails obsList
            cmbVehicleOwner.getItems().add(v.getDriverName());
        }
    }

    @FXML
    VehicleDetails getVehicle(ActionEvent event) {

        for (VehicleDetails v: VehicleDetailsController.observableList) { // getting VehicleDetail obj(v) s from vehicle ObsList
            if(v.getVehicleNo().equals(cmbVehicleNo.getValue())){         // Check that obj's vehicle no equals to the current cmbVehicle value
                vehicleTypeTxt.setText("  "+v.getVehicleType());          // set vehicle type to the textField

                // Search current cmbVehicle value in parkedVehicleContainer [check current cmbVehicleNo is included in parked vehicles]
                for(int k = 0; k< parkedCount; k++){
                    // Otherwise parkVehicleButton is enable
                    if(parkedVehicleContainer[1][k] != null){  // When it runs first there are no any values in parkedVehicleContainer array, the moment check by this if condition
                        if(((VehicleDetails) parkedVehicleContainer[1][k]).getVehicleNo().equals(cmbVehicleNo.getValue())){ // check current VehicleNo(cmb) is already parked
                            System.out.println("\n"+parkedVehicleContainer[1][k]+"\n");
                            deliveryShiftBtn.setDisable(false); // if ir is already parked... set deliverShiftBtn enable
                            parkVehicleBtn.setDisable(true); // if it is already parked... set parkVehicleButton disable
                            break;
                        }
                    }
                    // When we looking for first parked vehicle object , and it is null......
                    deliveryShiftBtn.setDisable(true); // deliverShiftBtn is disable
                    parkVehicleBtn.setDisable(false); // parkVehicleButton is enable
                }
                return v;
            }
        }
        return null;
    }

    @FXML
    void parkVehicle(ActionEvent event) throws InterruptedException {
        if(cmbVehicleNo.getValue()!= null  &&  cmbVehicleOwner.getValue()!= null){ // Check both cmbBoxes had selected values or not
            VehicleDetails v =  getVehicle(event);  // call to the getVehicle() and it returns VehicleDetails obj of current cmbVehicleValue
            String slot = null; // Each of slot numbers are assigned to this slot variable

            if(v == null){
                lblAllocatedSlot.setText("\t ✘"); // If the cmbVehicle value is't match with any of object of the VehicleDetails table obs array
            } else if(v.getVehicleType().equals("Bus")){
                Bus bus = new Bus();
                bus.park(v.getVehicleNo());
                slot = bus.slotNo;
                lblAllocatedSlot.setText("\t 14");
            }else if(v.getVehicleType().equals("Van")){
                Van van = new Van();
                van.park(v.getVehicleNo()); // Call and say, search a slot for the current van and save this van number in relevant slot
                slot = van.slotNo;
                lblAllocatedSlot.setText("\t "+slot);
            }else if(v.getVehicleType().equals("Cargo Lorry")){
                CargoLorry cargoLorry = new CargoLorry();
                cargoLorry.park(v.getVehicleNo()); // Call and say, search a slot for the current lorry and save this lorry number in relevant slot
                slot = cargoLorry.slotNo;
                lblAllocatedSlot.setText("\t "+ slot);
            }

            // store slot and current object in the array
            parkedVehicleContainer[0][parkedCount]= slot;
            parkedVehicleContainer[1][parkedCount]= v;
            System.out.println("\n@ " + parkedCount + " - > " + ((VehicleDetails)parkedVehicleContainer[1][parkedCount]).getVehicleNo()+"\n");

            // Throw new inParked vehicle details to the obsList which hold parked vehicle values
            assert v != null;
            InParkingTableViewController.observableParkedVehicleList.add(
                    new InParkedVehicleDetails(v.getVehicleNo(), v.getVehicleType(), (String) parkedVehicleContainer[0][parkedCount], lblTime.getText())
            );

            // If there is a same vehicle in onDelivery List, we need to remove it when parking it again.... ( next round)
            OnDeliveryTableViewController.observableOnDeliveryVehicleList.removeIf(d -> d.getVehicleNo().equals(v.getVehicleNo()));

            deliveryShiftBtn.setDisable(false);
            parkedCount++;
            System.out.println(parkedCount);

        }else{
            displayModalPopup(Alert.AlertType.ERROR,"Error","Please check weather the all fields are filled!!");
        }

        //Sleeping Thread
        Thread.sleep(1300);

        cmbVehicleNo.setValue(null);
        cmbVehicleOwner.setValue(null);
        vehicleTypeTxt.setText(null);
    }

    @FXML
    void deliveryShift(ActionEvent event) throws InterruptedException {
        if(cmbVehicleNo.getValue()!=null && cmbVehicleOwner.getValue()!=null){

            // get parked vehicle objects from obsParkedList
            for(InParkedVehicleDetails p : InParkingTableViewController.observableParkedVehicleList){
                if(cmbVehicleNo.getValue().equals(p.getVehicleNO())){   //check, if the current cmbValue is already parked or not
                    // If it is parked, add it to DeliverShift list, otherWise it will not add...
                    OnDeliveryTableViewController.observableOnDeliveryVehicleList.add(
                            new OnDeliveryDetails(cmbVehicleNo.getValue(),vehicleTypeTxt.getText(),cmbVehicleOwner.getValue(),lblTime.getText())
                    );
                    InParkingTableViewController.observableParkedVehicleList.remove(p);// Then, remove it from obsParkedList


                    lblAllocatedSlot.setText("\t ✘");

                    // search that cmbValue in the parkedVehicle array
                    for(int i=0; i<parkedCount ; i++){
                        if(parkedVehicleContainer[1][i] != null) { // At the very first time it is null

                            // search that cmbValue in the parkedVehicle array
                            if (p.getVehicleNO().equals(((VehicleDetails) parkedVehicleContainer[1][i]).getVehicleNo())) {

                                // If cmbValue is a Van type ->
                                if(p.getVehicleType().equals("Van")){
                                    Van van = new Van();
                                    van.leavePark(p.getVehicleNO()); // throw it as leaved van
                                }else if(p.getVehicleType().equals("Cargo Lorry")){
                                    CargoLorry cargoLorry = new CargoLorry();
                                    cargoLorry.leavePark(p.getVehicleNO()); // throw it as leaved lorry
                                }else if(p.getVehicleType().equals("Bus")){
                                    Bus bus = new Bus();
                                    bus.leavePark(p.getVehicleNO()); // throw it as leaved Bus
                                }

                                parkedVehicleContainer[0][i]=null;   // do the array's relevant positions null, because it is not exists
                                parkedVehicleContainer[1][i] = null; // do the array's relevant positions null, because it is not exists

                                System.out.println("count "+parkedCount);

                                deliveryShiftBtn.setDisable(false);
                                parkVehicleBtn.setDisable(false);
                                break;
                            }
                        }

                    }
                    break;
                }
            }
        }

        Thread.sleep(1300);

        cmbVehicleNo.setValue(null);
        cmbVehicleOwner.setValue(null);
        vehicleTypeTxt.setText(null);
    }

    @FXML
    void managersLogIn(ActionEvent event) throws Exception{
        stageOfDriverPanel = (Stage) managersLogInBtn.getScene().getWindow();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/ManagementLoginPanel.fxml"));
        primaryStage.setTitle("Blue Ocean v-1.0.0");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    void getVehicleOwner(ActionEvent event) {
    }

    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("\t   HH : mm : ss     yyyy-MM-dd ");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public static void displayModalPopup(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.getDialogPane().setPrefSize(600, 200);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        result.get();
    }
}

package model;

public interface Vehicle {
    String vehicleNumber = null;
    String vehicleType = null;
    int maxWeight = 0;
    int numOfPassengers = 0;
    String NIC = null;

    void park(String vehicleNumber);

    void leavePark(String vehicleNumber);
}

package model;

public class Bus implements Vehicle{
    public static Object[][] reservedSlotBus = {
            {"14"},
            {null}
    };

    public String slotNo = null;

    @Override
    public void park(String vehicleNumber) {
        if(reservedSlotBus[1][0]==(null)){
            reservedSlotBus[1][0] = vehicleNumber;
            slotNo = (String) reservedSlotBus[0][0];
        }
    }

    @Override
    public void leavePark(String vehicleNumber) {
        if(reservedSlotBus[1][0].equals(vehicleNumber)){
            reservedSlotBus[1][0] = null;
        }
    }
}

package model;

public class CargoLorry implements Vehicle{

    static String[][] reservedSlotCargoLorry = {
            {"5","6","7","8","9","10","11"},
            {null,null,null,null,null,null}
    };

    public String slotNo = null;

    @Override
    public void park(String vehicleNumber) {
        for(int i=0; i<reservedSlotCargoLorry[0].length; i++){
            if(reservedSlotCargoLorry[1][i]==(null)){
                reservedSlotCargoLorry[1][i] = vehicleNumber;
                System.out.println("\n++" + reservedSlotCargoLorry[1][i] + "++");
                slotNo = reservedSlotCargoLorry[0][i];
                System.out.println("Slot : "+slotNo);
                break;
            }
        }
    }

    @Override
    public void leavePark(String vehicleNumber) {
        for(int i=0; i<reservedSlotCargoLorry[0].length; i++){
            if(reservedSlotCargoLorry[1][i].equals(vehicleNumber)){
                reservedSlotCargoLorry[1][i] = null;
                System.out.println("**" + reservedSlotCargoLorry[1][i]+"**" );
                break;
            }
        }
    }
}

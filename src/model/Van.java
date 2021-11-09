package model;

public class Van implements Vehicle{

    static String[][] reservedSlotVan = {
            {"1","2","3","4","12","13"},
            {null,null,null,null,null,null}
    };

    public String slotNo = null;

    /* This method does is, getVehicle number and store it in reservedSlotVan Array's
       free position and assign that vehicle number to the relevant slot */
    @Override
    public void park(String vehicleNumber) {
        for(int i=0; i<reservedSlotVan[0].length; i++){
            if(reservedSlotVan[1][i]==(null)){
                reservedSlotVan[1][i] = vehicleNumber;
                System.out.println("\n++" + reservedSlotVan[1][i] + "++");
                slotNo = reservedSlotVan[0][i];
                System.out.println("Slot : "+slotNo);
                break;
            }
        }
    }

    // This method does is, when parked vehicle goes to delivery shift, the relevant slot rotate to null value
    @Override
    public void leavePark(String vehicleNumber) {
        for(int i=0; i<reservedSlotVan[0].length; i++){
            if(reservedSlotVan[1][i].equals(vehicleNumber)){
                System.out.println("**" + reservedSlotVan[1][i]+"**" );
                reservedSlotVan[1][i] = null;
                break;
            }
        }
    }
}

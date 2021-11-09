package model;

public class DriverDetails {

    private String driverName;
    private String NICNo;
    private String licenseNo;
    private String address;
    private String contactNo;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getNICNo() {
        return NICNo;
    }

    public void setNICNo(String NICNo) {
        this.NICNo = NICNo;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public DriverDetails(String driverName, String NICNo, String licenseNo, String address, String contactNo) {
        this.driverName = driverName;
        this.NICNo = NICNo;
        this.licenseNo = licenseNo;
        this.address = address;
        this.contactNo = contactNo;
    }
}

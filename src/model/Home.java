package model;

public class Home {
    private String address;
    private String addressNumber;
    private int floor;
    private double sqrm;

    public Home() {
    }

    public Home(String address, String addressNumber, int floor, double sqrm) {
        this.address = address;
        this.addressNumber = addressNumber;
        this.floor = floor;
        this.sqrm = sqrm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public double getSqrm() {
        return sqrm;
    }

    public void setSqrm(double sqrm) {
        this.sqrm = sqrm;
    }
}

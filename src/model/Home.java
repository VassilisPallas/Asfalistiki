package model;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class Home {
    private SimpleStringProperty address = new SimpleStringProperty();
    private SimpleStringProperty addressNumber = new SimpleStringProperty();
    private int floor;
    private double sqrm;
    private List<User> inhabitants;

    public Home() {
        inhabitants = new ArrayList<>();
    }

    public Home(String address, String addressNumber, int floor, double sqrm) {
        super();
        this.address.set(address);
        this.addressNumber.set(addressNumber);
        this.floor = floor;
        this.sqrm = sqrm;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getAddressNumber() {
        return addressNumber.get();
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber.set(addressNumber);
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

    public List<User> getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(List<User> inhabitant) {
        this.inhabitants = inhabitant;
    }

}

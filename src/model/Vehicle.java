package model;

import insurance.vehicle.VehicleType;

public class Vehicle {

    private VehicleType type;
    private String brand;
    private String model;
    private User owner;

    public Vehicle() {
    }

    public Vehicle(VehicleType type, String brand, String model, User owner) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.owner = owner;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

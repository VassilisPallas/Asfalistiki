package model;

import insurance.vehicle.VehicleType;

public class Vehicle {

    private VehicleType type;
    private User owner;

    public Vehicle() {
    }

    public Vehicle(VehicleType type, User owner) {
        this.type = type;
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

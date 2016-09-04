package model;

import insurance.vehicle.VehicleType;

public class Vehicle {
    private VehicleType type;
    private String brand;
    private String model;

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }
}

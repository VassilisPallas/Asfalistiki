package insurance.vehicle;

import model.User;
import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleInsurance {

    private User owner;

    private Vehicle vehicle;

    private List<VehicleCoverage> vehicleCoverages = new ArrayList<>();

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<VehicleCoverage> getVehicleCoverages() {
        return vehicleCoverages;
    }

    public void setVehicleCoverages(List<VehicleCoverage> vehicleCoverages) {
        this.vehicleCoverages = vehicleCoverages;
    }

    public void addCoverage(VehicleCoverage coverage) {
        vehicleCoverages.add(coverage);
    }

    public List<VehicleCoverage> getCoverages() {
        return vehicleCoverages;
    }

    public double calculatePrice() {
        double monthly = 0.0;

        for (VehicleCoverage vehicleCoverage : vehicleCoverages) {
            monthly += vehicleCoverage.getPrice();

            if (owner.getAge() < 35) {
                monthly = monthly + (monthly * 0.02);
            }
        }

        return monthly * 12;
    }
}

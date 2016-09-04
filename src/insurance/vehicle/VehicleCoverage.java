package insurance.vehicle;

public enum VehicleCoverage {
    ROADSIDE_ASSISTANCE(100), THEFT(300);

    private double price;

    VehicleCoverage(double price) {
        this.price = price;
    }

    public double getPrice(){
        return price;
    }
}

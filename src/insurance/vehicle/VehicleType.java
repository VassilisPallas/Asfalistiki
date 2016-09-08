package insurance.vehicle;

public enum VehicleType {
    CAR("Αυτοκινήτου"), MOTORCYCLE("Μοτοσικλέτας"), FARM_MACHINERY("Αγροτικών Μηχανημάτων");

    private String label;

    VehicleType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}

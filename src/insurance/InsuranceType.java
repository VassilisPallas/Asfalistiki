package insurance;

public enum InsuranceType {
    HEALTH("Υγείας"), VEHICLE("Οχήματος"), HOME("Κατοικίας");

    private String label;

    InsuranceType(String label) {
        this.label = label;
    }

    public String toString() {
        return label;
    }
}

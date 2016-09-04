package insurance.health;

public enum HealthCoverage {
    CIVIL_LIABILITY(600), HOSPITAL_CARE(120);

    double price;

    HealthCoverage(double price) {
        this.price = price;
    }

    public double getPrice(){
        return price;
    }
}

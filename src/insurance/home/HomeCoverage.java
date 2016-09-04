package insurance.home;

public enum HomeCoverage {
    FIRE(200), EARTHQUAKE(300);

    double price;

    HomeCoverage(double price) {
        this.price = price;
    }

    public double getPrice(){
        return price;
    }
}

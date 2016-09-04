package insurance.health;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class HealthInsurance {

    private List<User> members;

    private List<HealthCoverage> healthCoverages = new ArrayList<>();


    public List<HealthCoverage> getHealthInsurances() {
        return healthCoverages;
    }

    public void setHealthInsurances(List<HealthCoverage> healthCoverages) {
        this.healthCoverages = healthCoverages;
    }

    public void addHealthInsurances(HealthCoverage healthCoverages) {
        this.healthCoverages.add(healthCoverages);
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public double calculatePrice(int members) {
        double total = 0.0;

        for (HealthCoverage healthInsurances : healthCoverages) {
            total += healthInsurances.getPrice();
        }

        return total * members;
    }

}

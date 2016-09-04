package insurance.home;

import model.Home;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class HomeInsurance {
    private Home home;
    private List<User> inhabitants;
    private List<HomeCoverage> homeCoverages = new ArrayList<>();

    public List<User> getInhabitants() {
        return inhabitants;
    }

    public void setInhabitants(List<User> inhabitant) {
        this.inhabitants = inhabitant;
    }

    public List<HomeCoverage> getHomeCoverages() {
        return homeCoverages;
    }

    public void setHomeCoverages(List<HomeCoverage> homeCoverages) {
        this.homeCoverages = homeCoverages;
    }

    public void addHomeCoverages(HomeCoverage homeCoverage) {
        this.homeCoverages.add(homeCoverage);
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public double calculatePrice(int members) {
        double monthly = 0.0;

        for (HomeCoverage homeCoverage : homeCoverages) {
            monthly += homeCoverage.getPrice();

            if (home.getSqrm() > 100) {
                monthly = monthly + (monthly * 0.01);
            }
        }

        return monthly * 12;
    }
}


package insurance.home;

import insurance.Insurance;
import insurance.coverage.Coverage;
import model.Home;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class HomeInsurance extends Insurance {
    private Home home;

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    @Override
    public Object get() {
        return home;
    }

    @Override
    public void set(Object o) {
        if (o instanceof Home)
            home = (Home) o;
        else home = null;
    }

    @Override
    public double calculateAmount() {
        double monthly = 0.0;

        for (Coverage homeCoverage : coverages) {
            monthly += homeCoverage.getPrice();

            if (home.getSqrm() > 100) {
                monthly = monthly + (monthly * 0.01);
            }
        }

        return (monthly * 12) * home.getInhabitants().size();
    }
}


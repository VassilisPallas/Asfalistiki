package insurance.health;

import insurance.Insurance;
import insurance.coverage.Coverage;
import model.User;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class HealthInsurance extends Insurance {

    private List<User> members;


    @Override
    public Object get() {
        return members;
    }

    @Override
    public void set(Object o) {
        if (o instanceof List<?>)
            members = (List<User>) o;
        else members = null;
    }

    @Override
    public double calculateAmount(int... args) {
        double total = 0.0;

        for (Coverage healthInsurances : coverages) {
            total += healthInsurances.getPrice();
        }

        return (total * 12) * args[0];
    }
}

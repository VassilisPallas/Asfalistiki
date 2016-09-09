package new_contract_screen;

import helpers.AnimationHelper;
import insurance.Insurance;
import insurance.InsuranceType;
import insurance.coverage.Coverage;
import insurance.coverage.InsuranceCoverages;
import insurance.health.HealthInsurance;
import insurance.home.HomeInsurance;
import insurance.vehicle.VehicleInsurance;
import insurance.vehicle.VehicleType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Home;
import model.User;
import model.Vehicle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

import static insurance.InsuranceType.HEALTH;
import static insurance.InsuranceType.HOME;
import static insurance.InsuranceType.VEHICLE;
import static insurance.vehicle.VehicleType.CAR;
import static insurance.vehicle.VehicleType.FARM_MACHINERY;
import static insurance.vehicle.VehicleType.MOTORCYCLE;

public class NewContractController implements Initializable {

    private User user;
    private Insurance insurance;
    private Stack st = new Stack();
    private Object object;

    @FXML
    private ScrollPane scroll;

    @FXML
    private ComboBox insuranceType;

    @FXML
    private ComboBox vehicleType;

    @FXML
    private VBox vehicleTypeBox;

    @FXML
    private GridPane grid;

    private ObservableList<InsuranceType> insuranceOptions =
            FXCollections.observableArrayList(
                    HEALTH,
                    VEHICLE,
                    HOME
            );

    private ObservableList<VehicleType> vehicleOptions =
            FXCollections.observableArrayList(
                    CAR,
                    MOTORCYCLE,
                    FARM_MACHINERY
            );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        initilalizeComboboxes();
    }

    private void initilalizeComboboxes() {
        insuranceType.setItems(insuranceOptions);
        insuranceType.getSelectionModel().selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    InsuranceType insuranceType = (InsuranceType) newValue;

                    switch (insuranceType) {
                        case HEALTH:
                            AnimationHelper.fadeOut(vehicleTypeBox);
                            generateCoverages(InsuranceCoverages.healthCoverages());
                            insurance = new HealthInsurance();
                            object = new ArrayList<User>();
                            break;
                        case VEHICLE:
                            AnimationHelper.fadeIn(vehicleTypeBox);
                            generateCoverages(InsuranceCoverages.vehicleCoverages());
                            insurance = new VehicleInsurance();
                            object = new Vehicle();
                            break;
                        case HOME:
                            AnimationHelper.fadeOut(vehicleTypeBox);
                            generateCoverages(InsuranceCoverages.homeCoverages());
                            insurance = new HomeInsurance();
                            object = new Home();
                            break;
                    }
                }));


        vehicleType.setItems(vehicleOptions);
        vehicleType.getSelectionModel().selectedItemProperty()
                .addListener(((observable, oldValue, newValue) -> {
                    VehicleType vehicleType = (VehicleType) newValue;

                    switch (vehicleType) {
                        case CAR:
                            ((Vehicle) object).setType(CAR);
                            break;
                        case MOTORCYCLE:
                            ((Vehicle) object).setType(MOTORCYCLE);
                            break;
                        case FARM_MACHINERY:
                            ((Vehicle) object).setType(FARM_MACHINERY);
                            break;
                    }
                    ((Vehicle) object).setOwner(user);
                }));
    }

    private void generateCoverages(List<Coverage> coverages) {
        if (!st.empty()) {
            grid.getChildren().remove(st.get(0));
            st.pop();
        }

        VBox mainLayout = new VBox(15);


        for (Coverage coverage : coverages) {
            VBox child = new VBox(10);
            CheckBox checkBox = new CheckBox(coverage.getName());
            Label price = new Label(coverage.getPrice() + "€/μήνα");
            checkBox.setOnAction(checkBoxAction);

            Label label = new Label(coverage.getDescription());
            child.getChildren().addAll(checkBox, price, label);
            mainLayout.getChildren().add(child);
        }

        grid.add(mainLayout, 0, 2);

        st.push(mainLayout);
    }


    private EventHandler checkBoxAction = (EventHandler<ActionEvent>) event -> {
        if (event.getSource() instanceof CheckBox) {
            CheckBox chk = (CheckBox) event.getSource();
            List<Coverage> coverages = null;
            Coverage selectedCoverage = null;
            if (insurance instanceof HealthInsurance) {
                coverages = InsuranceCoverages.healthCoverages();
            } else if (insurance instanceof VehicleInsurance) {
                coverages = InsuranceCoverages.vehicleCoverages();
            } else if (insurance instanceof HomeInsurance) {
                coverages = InsuranceCoverages.homeCoverages();
            }

            assert coverages != null;
            for (Coverage coverage : coverages) {
                if (coverage.getName().equals(chk.getText())) {
                    selectedCoverage = coverage;
                }
            }

            if (chk.isSelected()) {
                insurance.addCoverage(selectedCoverage);
            } else {
                insurance.removeCoverage(selectedCoverage);
            }
        }
    };

    public void getUser(User user) {
        this.user = user;
    }
}

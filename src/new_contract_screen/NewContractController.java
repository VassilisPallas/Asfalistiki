package new_contract_screen;

import helpers.*;
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
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Home;
import model.User;
import model.Vehicle;

import java.io.IOException;
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

    private ObservableList<User> users = FXCollections.observableArrayList();

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

    @FXML
    private VBox addMember;

    @FXML
    private Button addMemberButton;

    @FXML
    private Label addMessage;

    @FXML
    private Button confirmButton;

    @FXML
    private Label vehicleTypeError;

    private TableView table = new TableView();

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

    private Label insuranceErrorLabel = new Label("Πρέπει να επιλέξετε τουλάχιστον μια ασφάλεια.");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        usersTable();
        initilalizeComboboxes();

        addMemberButton.setOnAction(event -> {
            User u = new AddUserBoxHelper().addUser(addMessage.getText());

            if (u != null)
                addUserToList(u);
        });

        confirmButton.setOnAction(event -> {
            if (validate()) {
                try {
                    openConfirmPage();
                } catch (IOException | IllegalStateException | NullPointerException e) {
                    AlertBoxHelper.display("Error", "An error has occurred: Internal Error");
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean validate() {
        boolean result = true;
        if (insurance.getCoverages().size() == 0) {
            insuranceErrorLabel.setVisible(true);
            result = false;
        }
        if (insurance.get() instanceof Vehicle) {
            if (((Vehicle) insurance.get()).getType() == null) {
                vehicleTypeError.setVisible(true);
                result = false;
            }
        }

        return result;
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
                            addMessage.setText("Προσθέστε μέλος ασφάλισης");
                            addMember.setVisible(true);
                            setTableVisibility(true);
                            confirmButton.setVisible(true);
                            break;
                        case VEHICLE:
                            AnimationHelper.fadeIn(vehicleTypeBox);
                            generateCoverages(InsuranceCoverages.vehicleCoverages());
                            insurance = new VehicleInsurance();
                            object = new Vehicle();
                            addMember.setVisible(false);
                            setTableVisibility(false);
                            break;
                        case HOME:
                            AnimationHelper.fadeOut(vehicleTypeBox);
                            generateCoverages(InsuranceCoverages.homeCoverages());
                            insurance = new HomeInsurance();
                            object = new Home();
                            addMessage.setText("Προσθέστε κάτοικο");
                            addMember.setVisible(true);
                            setTableVisibility(true);
                            confirmButton.setVisible(true);
                            break;
                    }
                    addUserToList(user);
                    insurance.set(object);
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
                    confirmButton.setVisible(true);
                    ((Vehicle) object).setOwner(user);
                }));
    }

    private void addUserToList(User usr) {
        if (object instanceof ArrayList<?>) {
            if (!((ArrayList<User>) object).contains(usr))
                ((ArrayList<User>) object).add(usr);
        } else if (object instanceof Home) {
            if (!((ArrayList<User>) object).contains(usr))
                ((Home) object).getInhabitants().add(usr);
        }

        if (!users.contains(usr))
            users.addAll(usr);
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

        insuranceErrorLabel.setTextFill(Paint.valueOf("#FF0000"));
        mainLayout.getChildren().add(insuranceErrorLabel);
        grid.add(mainLayout, 0, 2);

        insuranceErrorLabel.setVisible(false);

        st.push(mainLayout);
    }

    private void usersTable() {
        table.setPlaceholder(new Label("Δεν υπάρχουν νέοι χρήστες"));
        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("Όνομα");
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<User, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Επώνυμο");
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<User, String>("lastName"));

        TableColumn ageCol = new TableColumn("Ηλικία");
        ageCol.setCellValueFactory(
                new PropertyValueFactory<User, Integer>("age"));

        table.setItems(users);

        table.getColumns().addAll(firstNameCol, lastNameCol, ageCol);

        grid.add(table, 0, 4);

        table.setVisible(false);
    }

    private void setTableVisibility(boolean visibility) {
        table.setVisible(visibility);
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

    private void openConfirmPage() throws IOException {
        GotoOtherPage.confirmPage(insurance, getClass(), (Stage) confirmButton.getScene().getWindow());
    }
}

package main_screen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button loginBtn;

    @FXML
    private TextField firstName;

    @FXML
    private Label firstNameError;

    @FXML
    private TextField lastName;

    @FXML
    private Label lastNameError;

    @FXML
    private DatePicker age;

    @FXML
    private Label ageError;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginBtn.setOnAction((event) -> {
            if (validate()) {
                User user = new User(firstName.getText().trim(), lastName.getText().trim(), Calendar.getInstance().get(Calendar.YEAR) - age.getValue().getYear());
            }
        });
    }

    private boolean validate() {
        boolean result = true;
        if (firstName.getText().length() == 0) {
            firstNameError.setVisible(true);
            result = false;
        } else {
            firstNameError.setVisible(false);
        }

        if (lastName.getText().length() == 0) {
            lastNameError.setVisible(true);
            result = false;
        } else {
            lastNameError.setVisible(false);
        }

        if (age.getValue() == null) {
            ageError.setVisible(true);
            result = false;
        } else {
            ageError.setVisible(false);
        }

        return result;
    }

    private void openNewContractScreen(User user) throws IOException {
    }
}

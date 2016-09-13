package helpers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;

import java.util.Calendar;

public class AddUserBoxHelper {

    private TextField nameTextField = new TextField();
    private Label nameError = new Label("Δεν δώσατε όνομα.");
    private TextField surnameTextField = new TextField();
    private Label surnameError = new Label("Δεν δώσατε επίθετο.");
    private DatePicker agePicker = new DatePicker();
    private Label ageError = new Label("Δεν δώσατε ηλικία.");
    private User user = null;
    private Stage stage = new Stage();

    public User addUser(String title) {

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinHeight(300);
        stage.setMinWidth(450);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Label name = new Label("Όνομα");
        gridPane.add(name, 0, 0);
        Label surname = new Label("Επώνυμο");
        gridPane.add(surname, 0, 2);
        Label age = new Label("Ημερομηνία Γέννησης");
        gridPane.add(age, 0, 3);

        VBox nameBox = new VBox(10);

        nameError.setVisible(false);
        nameError.setTextFill(Paint.valueOf("#FF0000"));
        nameBox.getChildren().addAll(nameTextField, nameError);
        nameBox.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.add(nameBox, 1, 0);

        VBox surnameBox = new VBox(10);
        surnameError.setVisible(false);
        surnameError.setTextFill(Paint.valueOf("#FF0000"));
        surnameBox.getChildren().addAll(surnameTextField, surnameError);
        surnameBox.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.add(surnameBox, 1, 2);

        VBox ageBox = new VBox(10);
        ageError.setVisible(false);
        ageError.setTextFill(Paint.valueOf("#FF0000"));
        ageBox.getChildren().addAll(agePicker, ageError);
        ageBox.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.add(ageBox, 1, 3);

        HBox buttonBox = new HBox(10);
        Button button = new Button("Προσθήκη");
        buttonBox.getChildren().addAll(button);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.add(buttonBox, 1, 4);

        button.setOnAction(event -> {
            if (validate()) {
                user = new User();
                user.setFirstName(nameTextField.getText().trim());
                user.setLastName(surnameTextField.getText().trim());
                user.setAge(Calendar.getInstance().get(Calendar.YEAR) - agePicker.getValue().getYear());
                stage.close();
            }
        });

        Scene scene = new Scene(gridPane, 450, 300);
        scene.getStylesheets().addAll("/resources/stylesheets/main.css");
        stage.setScene(scene);
        stage.showAndWait();


        return user;
    }

    private boolean validate() {
        boolean result = true;
        if (nameTextField.getText().length() == 0) {
            nameError.setVisible(true);
            result = false;
        } else {
            nameError.setVisible(false);
        }

        if (surnameTextField.getText().length() == 0) {
            surnameError.setVisible(true);
            result = false;
        } else {
            surnameError.setVisible(false);
        }

        if (agePicker.getValue() == null) {
            ageError.setVisible(true);
            result = false;
        } else {
            ageError.setVisible(false);
        }

        return result;
    }
}

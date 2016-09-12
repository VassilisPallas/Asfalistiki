package helpers;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;

public class AddUserBoxHelper {

    public static User addUser(String title) {
        User user = new User();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinHeight(350);
        stage.setMinWidth(380);

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
        TextField nameTextField = new TextField();
        Label nameError = new Label("Δεν δώσατε όνομα.");
        nameBox.getChildren().addAll(nameTextField, nameError);
        nameBox.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.add(nameBox, 1, 0);

        VBox surnameBox = new VBox(10);
        TextField surnameTextField = new TextField();
        Label surnameError = new Label("Δεν δώσατε επίθετο.");
        surnameBox.getChildren().addAll(surnameTextField, surnameError);
        surnameBox.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.add(surnameBox, 1, 2);

        VBox ageBox = new VBox(10);
        DatePicker agePicker = new DatePicker();
        Label ageError = new Label("Δεν δώσατε την ηλικία σας.");
        ageBox.getChildren().addAll(agePicker, ageError);
        ageBox.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.add(ageBox, 1, 3);

        HBox buttonBox = new HBox(10);
        Button button = new Button("Προσθήκη");
        buttonBox.getChildren().addAll(button);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.add(buttonBox, 1, 4);


        Scene scene = new Scene(gridPane);

        stage.setScene(scene);
        stage.showAndWait();

        return user;
    }
}

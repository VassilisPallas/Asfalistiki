package helpers;

import javafx.scene.control.Alert;

public class AlertBoxHelper {

    public static void display(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}

package helpers;

import confirm_page.ConfirmController;
import insurance.Insurance;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main_screen.MainController;
import model.User;
import new_contract_screen.NewContractController;

import java.io.IOException;

public class GotoOtherPage {

    public static void main(Class clazz, Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(clazz.getResource("mainScreen.fxml"));
        primaryStage.setTitle("Ασφαλιστική - Είσοδος");
        Scene scene = new Scene(root, 1000, 675);
        scene.getStylesheets().add("resources/stylesheets/main.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void mainPage(Class clazz, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(clazz.getResource("/main_screen/mainScreen.fxml"));
        Parent sceneMain = loader.load();

        stage.setTitle("Ασφαλιστική - Είσοδος");
        Scene scene = new Scene(sceneMain, 1000, 675);
        scene.getStylesheets().add("resources/stylesheets/main.css");
        stage.setScene(scene);
    }

    public static void newContract(User user, Class clazz, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(clazz.getResource("/new_contract_screen/newContractScreen.fxml"));
        Parent sceneMain = loader.load();
        NewContractController controller = loader.getController();
        controller.getUser(user);

        stage.setTitle("Δημιουργία νέου συμβολαίου");
        Scene scene = new Scene(sceneMain, 1000, 675);
        scene.getStylesheets().add("resources/stylesheets/main.css");
        stage.setScene(scene);
    }

    public static void confirmPage(Insurance insurance, Class clazz, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(clazz.getResource("/confirm_page/confirmScreen.fxml"));
        Parent sceneMain = loader.load();
        ConfirmController controller = loader.getController();
        controller.getInsurance(insurance);

        stage.setTitle("Επικύρωση και Πληρωμή");
        Scene scene = new Scene(sceneMain, 1000, 675);
        scene.getStylesheets().add("resources/stylesheets/main.css");
        stage.setScene(scene);
    }
}

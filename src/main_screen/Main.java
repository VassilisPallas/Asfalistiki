package main_screen;

import helpers.GotoOtherPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        GotoOtherPage.main(getClass(), primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

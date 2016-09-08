package helpers;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationHelper {

    public static void fadeIn(Node element){
        FadeTransition ft = new FadeTransition(Duration.millis(500), element);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        element.setVisible(true);
        ft.playFromStart();
    }

    public static void fadeOut(Node element){
        FadeTransition ft = new FadeTransition(Duration.millis(500), element);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.playFromStart();

        ft.setOnFinished(event -> element.setVisible(false));
    }
}

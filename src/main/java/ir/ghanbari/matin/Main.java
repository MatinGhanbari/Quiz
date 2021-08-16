package ir.ghanbari.matin;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static Quiz quiz;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        setStageSettings(stage);

        quiz = new Quiz(stage);
        quiz.start();

        stage.show();
    }

    private void setStageSettings(Stage stage) {
        stage.setFullScreen(true);
        stage.setTitle("Quiz App");
        stage.setFullScreenExitHint("");
        stage.getIcons().add(new Image("images/icon.png"));
    }
}

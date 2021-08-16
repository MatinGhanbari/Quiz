package ir.ghanbari.matin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Quiz {
    private final Stage stage;
    private QuizController quizController;
    private Scanner scanner;

    public Quiz(Stage stage) throws FileNotFoundException {
        this.stage = stage;
    }

    public void start() {
        Parent pane = null;
        try {
            pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("quiz.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pane != null;
        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            }
        });
        stage.setScene(scene);
        stage.setFullScreen(true);
    }

    public void setQuizController(QuizController quizController) {
        this.quizController = quizController;
        setQuiz();
    }

    private void setQuiz() {
        try {
            scanner = new Scanner(new File("D:/quizzes.txt"));
            quizController.setQuiz(scanner.nextLine());
            String[] buttons = {scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextLine()};
            quizController.setButtons(buttons);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void next() {
        if (scanner.hasNextLine()) {
            quizController.setQuiz(scanner.nextLine());
            String[] buttons = {scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextLine()};
            quizController.setButtons(buttons);
        } else {
            quizController.finish();
        }
    }
}

package ir.ghanbari.matin;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import javax.print.attribute.standard.Media;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class QuizController implements Initializable {
    public Label label_quiz;
    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Rectangle rect;
    public Button btn_rect;
    public Label label_rect;
    public AnchorPane pane;
    public AnchorPane first_pane;
    public Button btn_start_test;
    public Button btn_exit;
    public Label label_matin_ghanbari;
    private Button answer;
    private int counter = 0;
    private int correctAnswers = 0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Main.quiz.setQuizController(this);
        pane.getChildren().removeAll(rect, btn_rect, label_rect);
        label_rect.setWrapText(true);
        label_quiz.setWrapText(true);
    }

    public void setQuiz(String quiz) {
        label_quiz.setText(quiz);
    }

    public void setButtons(String[] buttonsTexts) {
        Random rand = new Random();
        ArrayList<Button> notSelected = new ArrayList<>();
        notSelected.add(btn1);
        notSelected.add(btn2);
        notSelected.add(btn3);
        notSelected.add(btn4);
        int i = 0;
        while (notSelected.size() > 0) {
            int random = rand.nextInt(100) % (4 - i);
            if (i == 0) {
                answer = notSelected.get(random);
            }
            notSelected.get(random).setText(buttonsTexts[i]);
            notSelected.remove(random);
            i++;
        }
    }

    public void select(MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(answer)) {
            counter++;
            correctAnswers++;
            pane.getChildren().addAll(rect, btn_rect, label_rect);
            label_rect.setStyle(label_rect.getStyle() + "\n-fx-text-fill: green;");
            answer.setStyle(answer.getStyle() + "\n-fx-background-color: green;");
            label_rect.setStyle(label_rect.getStyle() + "\n-fx-text-fill: green;");
            label_rect.setText("?????????? ?????????? ???????? ??????\n???? ???????? " + correctAnswers + " ???????? ???? " + counter + " ???????? ????\n???????? ???????? ????????!");
        } else {
            counter++;
            ((Button) mouseEvent.getSource()).setStyle(((Button) mouseEvent.getSource()).getStyle() + "\n-fx-background-color: red;");
            answer.setStyle(answer.getStyle() + "\n-fx-background-color: green;");
            pane.getChildren().addAll(rect, btn_rect, label_rect);
            label_rect.setStyle(label_rect.getStyle() + "\n-fx-text-fill: red;");
            label_rect.setText("???????????????? ?????????? ?????? ??????\n???? ???????? " + correctAnswers + " ???????? ???? " + counter + " ???????? ????\n???????? ???????? ????????!");
        }
    }

    public void next(MouseEvent mouseEvent) {
        if (btn_rect.getText().equalsIgnoreCase("????????")) {
            System.exit(0);
        }
        btn1.setStyle("");
        btn2.setStyle("");
        btn3.setStyle("");
        btn4.setStyle("");
        pane.getChildren().removeAll(rect, btn_rect, label_rect);
        Main.quiz.next();
    }

    public void finish() {
        pane.getChildren().addAll(rect, btn_rect, label_rect);
        btn_rect.setText("????????");
        label_rect.setStyle(label_rect.getStyle() + "\n-fx-text-fill: gold;");
        label_rect.setText("???????????? ???????? ????!\n?????? ???? " + correctAnswers + " ???????? ???? " + counter + " ????????\n???????? ???????? ??????????!");
    }

    public void startTest(MouseEvent mouseEvent) {
        pane.getChildren().removeAll(first_pane);
    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }
}

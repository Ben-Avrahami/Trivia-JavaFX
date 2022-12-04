import java.util.ArrayList;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class TriviaController {

	@FXML
	private Label ScoreBox;

	@FXML
	private Button answer1;

	@FXML
	private Button answer2;

	@FXML
	private Button answer3;

	@FXML
	private Button answer4;

	@FXML
	private Text questionBox;

	private TriviaLogic game; 
	private int score=0;

	public void initialize() {
		ReadFile.openFile();
		game=new TriviaLogic();
		setScore();
		setGameRound();	
	}

	private void setGameRound(){
		questionBox.setText(ReadFile.nextLine());
		game.setCorrectAnswer(ReadFile.nextLine());
		ArrayList<String> answers = new ArrayList<String>();
		answers.add(game.getCorrectAnswer());
		answers.add(ReadFile.nextLine());
		answers.add(ReadFile.nextLine());
		answers.add(ReadFile.nextLine());
		Collections.shuffle(answers);//shuffles the answers
		answer1.setText(answers.get(0));
		answer2.setText(answers.get(1));
		answer3.setText(answers.get(2));
		answer4.setText(answers.get(3));
	}

	private void setScore() {
		ScoreBox.setText("Score: "+score);
	}

	@FXML
	void answerSelected(ActionEvent event) {
		Button btn=(Button) event.getSource();//gets the button the was pressed
		/*
		if(game.checkAnswer(btn.getText())) {
			score+=10;
			questionBox.setText("Correct Answer!");
		}
		else {
			score-=5;
			questionBox.setText("Incorrect Answer!");
		}
		 */ 
		changeQuestionBox(game.checkAnswer(btn.getText()));
		pause();
		setScore();
		setGameRound();
	}

	private void changeQuestionBox(boolean correct) {
		if (correct) {
			score+=10;
			questionBox.setText("Correct Answer!");
		}
		else {
			score-=5;
			questionBox.setText("Incorrect Answer!");
		}
	}

	private void pause() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}   
	}
}

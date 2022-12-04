import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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
	
	/*
	 * This method initializes a game
	 */
	public void initialize() {
		ReadFile.openFile();//opens the questions and answers file
		game=new TriviaLogic();//creates a game of triviaLogic
		setScore();//set the score to 0;
		setGameRound();//sets the game for the round	
	}
	
	/*
	 * This method sets a next round of the game
	 * changes the question and the possible four answers
	 */
	private void setGameRound(){
		String question=ReadFile.nextLine();
		if (question.equals("Game Ended")) {
			Alert alert = new Alert(AlertType.CONFIRMATION); 
			alert.setTitle("Game Ended");
			alert.setHeaderText("You have reached the end of the game");
			alert.setContentText("You have reached the end of the game\nYou got "+ game.get_score() + " Points \nThanks for playing \nIf you wish to play again press: OK \nIf you wish to close the game press: CANCEL");
			Optional<ButtonType> option = alert.showAndWait();
			if(option.get()==ButtonType.OK) {
				restart();
				question=ReadFile.nextLine();
			}
			else if (option.get() == ButtonType.CANCEL){
				ReadFile.closeInput();//closes the Text file
				System.exit(0);//stops the game from running
			}
		}
		questionBox.setText(question);
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
	
	/*
	 * This method sets the score
	 */
	private void setScore() {
		ScoreBox.setText("Score: "+game.get_score());
	}
	
	/*
	 * This method checks if the chosen answers is correct and sets the game for next round by calling setGameRound method
	 */
	@FXML
	void answerSelected(ActionEvent event) {
		Button btn=(Button) event.getSource();//gets the button the was pressed
		displayMessage(game.checkAnswer(btn.getText()));
		setScore();
		setGameRound();
	}
	
	/*
	 * This method displays a message if the answer was correct or incorrect and allows the player to choose to close the game or continue for the next question
	 */
	private void displayMessage(boolean correct) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("The answer was: " + game.getCorrectAnswer()+ "\nIf you wish to try another question press: OK \nIf you wish to close the game press: CANCEL ");
		if (correct) {
			alert.setTitle("Correct Answer");
			alert.setHeaderText("+10 Points");
			game.setscore(game.get_score()+10);
		}
		else {
			alert.setTitle("Incorrect Answer");
			alert.setHeaderText("-5 Points");
			game.setscore(game.get_score()-5);
		}
		Optional<ButtonType> option = alert.showAndWait();
		if (option.get() == ButtonType.CANCEL){
			ReadFile.closeInput();//closes the Text file
			System.exit(0);//stops the game from running
		}
	}
	
	/*
	 * This method restarts the game if all questions in the TriviaQuestions file were answered
	 */
	private void restart() {
		game.setscore(0);
		ReadFile.openFile();
	}
	
}

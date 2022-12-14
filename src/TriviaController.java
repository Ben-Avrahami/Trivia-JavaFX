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
/**
 * This Class is the controller for the Trivia Game
 * @author BenA
 *
 */
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

	private ArrayList<Answers> answers;

	private int indexOfNextQuestion=0;

	private Answers currQuestion;

	/*
	 * This method initializes a game
	 */
	public void initialize() {
		ReadFile.openFile();//opens the questions and answers file
		game=new TriviaLogic();//creates a game of triviaLogic
		setScore();//set the score to 0;
		answers = new ArrayList<Answers>();
		setQuestionsAndAnswers();
		setGameRound();//sets the game for the round
	}

	/*
	 * This method sets a next round of the game
	 * changes the question and the possible four answers
	 */
	private void setGameRound(){
		if (indexOfNextQuestion==answers.size()) {//if all questions were played already
			Alert alert = new Alert(AlertType.CONFIRMATION); 
			alert.setTitle("Game Ended");
			alert.setHeaderText("You have reached the end of the game");
			alert.setContentText("You have reached the end of the game\nYou got "+ game.get_score() + " Points \nThanks for playing \nIf you wish to play again press: OK \nIf you wish to close the game press: CANCEL");
			Optional<ButtonType> option = alert.showAndWait();
			if(option.get()==ButtonType.OK) {
				restart();
			}
			else if (option.get() == ButtonType.CANCEL){
				ReadFile.closeInput();//closes the Text file
				System.exit(0);//stops the game from running
			}
		}
		currQuestion=answers.get(indexOfNextQuestion);//gets the next Answers from answers class
		indexOfNextQuestion++;
		questionBox.setText(currQuestion.getQuestion());
		game.setCorrectAnswer(currQuestion.getCorrectAnswer());
		answer1.setText(currQuestion.getAnswers().get(0));
		answer2.setText(currQuestion.getAnswers().get(1));
		answer3.setText(currQuestion.getAnswers().get(2));
		answer4.setText(currQuestion.getAnswers().get(3));
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
	 * This method sets all the questions and answers which are returned by ReadFile into answers arrayList and shuffles them so they will be in a random order
	 */
	private void setQuestionsAndAnswers() {
		String question="";
		while(!question.equals("Game Ended")) {
			question=ReadFile.nextLine();
			if (question.equals("Game Ended")) {
				Collections.shuffle(answers);//shuffles the answers so they will be in a random order	
				return;
			}
			answers.add(new Answers(question, ReadFile.nextLine(), ReadFile.nextLine(), ReadFile.nextLine(), ReadFile.nextLine()));
		}


	}

	/*
	 * This method restarts the game if all questions in the TriviaQuestions file were answered
	 */
	private void restart() {
		game.setscore(0);
		ReadFile.openFile();
		indexOfNextQuestion=0;
		answers=new ArrayList<Answers>();//creates the arrayList again, so will delete all the previous Answers stored in the arrayList
		setQuestionsAndAnswers();//i could have just shuffle the answers arrayList again because i use the same file for the questions if the player chooses to play again, but i did it this way so it will be possible to change to a different file more easily and then change the questions and answers 
	}

}

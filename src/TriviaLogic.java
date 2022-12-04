
public class TriviaLogic {
	private String _correctAnswer;
	private int _score;

	/*
	 * This method sets _correctAnswer variable to contain the correct answer
	 */
	public void setCorrectAnswer(String correctAnswer) {
		_correctAnswer = correctAnswer;
	}

	/*
	 * This method returns the correct answer 
	 */
	public String getCorrectAnswer() {
		return _correctAnswer;
	}

	/*
	 * This method checks if the answer given as parameter is equal to the correct answer, if it is it means that the answer provided as parameter is the correct answer for the question
	 */
	public boolean checkAnswer(String answer) {
		return (_correctAnswer.equals(answer));
	}

	/*
	 * This method sets _score variable
	 */
	public void setscore(int score) {
		_score=score;
	}

	/*
	 * This method returns _score variable
	 */
	public int get_score() {
		return _score;
	}
	
}

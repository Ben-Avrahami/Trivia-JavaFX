
public class TriviaLogic {
private String _correctAnswer;

public void setCorrectAnswer(String correctAnswer) {
	_correctAnswer = correctAnswer;
}
public String getCorrectAnswer() {
	return _correctAnswer;
}

public boolean checkAnswer(String answer) {
	return (_correctAnswer.equals(answer));
}
}

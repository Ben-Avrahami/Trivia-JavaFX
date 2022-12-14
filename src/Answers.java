import java.util.ArrayList;
import java.util.Collections;
/**
 * This Class will contain a question, correct answer and 4 possible answers in a String arrayList (one of them is the correct one)
 * @author BenA
 *
 */
public class Answers {
	private String _correctAnswer;
	private String _question;
	private ArrayList<String> possibleAnswers = new ArrayList<String>();
	/*
	 * Constructor
	 */
	public Answers(String question,String answer1,String answer2,String answer3,String answer4 ) {
		_question=question;
		_correctAnswer=answer1;
		possibleAnswers.add(answer1);
		possibleAnswers.add(answer2);
		possibleAnswers.add(answer3);
		possibleAnswers.add(answer4);	
		Collections.shuffle(possibleAnswers);//shuffles the answers
	}
	/*
	 * returns _question
	 */
	public String getQuestion() {
		return _question;
	}
	/*
	 * returns _correctAnswer
	 */
	public String getCorrectAnswer() {
		return _correctAnswer;
	}
	/*
	 * returns possibleAnswers 
	 */
	public ArrayList<String> getAnswers() {
		return possibleAnswers;
	}

}


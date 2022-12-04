import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ReadFile {
	private static Scanner input;
	private static File TriviaQuestions=new File("TriviaQuestions.txt");

	/*
	 * This Method opens the file and counts how many words are in the file to know what is the limit for the randomly generated number
	 */
	public static void openFile() {
		try {
			input = new Scanner(TriviaQuestions);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error! \nWas unable to open the file");
			System.exit(0);
		}
	}

	/*
	 * This method returns the next line if there is one and still the file end was not reached  
	 */
	public static String nextLine() {
		if (input.hasNext()) {
			return input.nextLine();
		}
		else {
			return("Game Ended");

		}
	}

	/*
	 * This method closes the file
	 */
	public static void closeInput() {
		input.close();
	}

}

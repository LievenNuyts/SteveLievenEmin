import javax.swing.SwingUtilities;

import controller.ChangeQuizController;
import view.ChangeQuizView;
import model.Quiz;


public class ChangeQuizMain {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				runQuiz();
			}
		});

	}
	public static void runQuiz() {
		Quiz quiz = new Quiz();
		ChangeQuizView changeView = new ChangeQuizView(quiz);
		ChangeQuizController changeController = new ChangeQuizController(changeView, quiz);
	}
}

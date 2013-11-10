import javax.swing.SwingUtilities;

import controller.ChangeQuizController;
import view.ChangeQuizView;
import model.ExerciseCatalog;
import model.QuizCatalog;


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
		QuizCatalog quizCatalog = new QuizCatalog();
		ExerciseCatalog exCatalog = new ExerciseCatalog();
		ChangeQuizView changeView = new ChangeQuizView(quizCatalog, exCatalog);
		ChangeQuizController changeController = new ChangeQuizController(changeView, quizCatalog, exCatalog);
	}
}

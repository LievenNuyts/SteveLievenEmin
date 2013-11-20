import model.ExerciseCatalog;
import model.QuizCatalog;
import view.ChangeQuizView;
import controller.ChangeQuizController;

/**
 * 
 * @author Steve
 * @version 16/11/2013
 *
 */

public class RunChangeView {
	
	public static void main(String[] args){

	ChangeQuizView changeView = new ChangeQuizView();

	QuizCatalog quizModel = new QuizCatalog();
	ExerciseCatalog exModel = new ExerciseCatalog();
	
	

	ChangeQuizController controller = new ChangeQuizController(changeView, quizModel, exModel);

	}

} 

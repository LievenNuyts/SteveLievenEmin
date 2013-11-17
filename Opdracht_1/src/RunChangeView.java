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

	ChangeQuizView changeView = new ChangeQuizView();

	QuizCatalog model = new QuizCatalog();

	ChangeQuizController theController = new ChangeQuizController(changeView,model);

	//changeview.setVisible(true);

} 

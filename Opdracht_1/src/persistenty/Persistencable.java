/**
 * 
 */
package persistenty;

import java.util.List;

import controller.ChangeQuizController;
import controller.DeleteQuizController;
import model.Exercise;
import model.ExerciseCatalog;
import model.Quiz;
import model.QuizCatalog;
import sun.security.util.PermissionFactory;
import view.ChangeQuizView;
import view.CreateQuizView;
import view.DeleteQuizView;

/**
 * 
 * @author Emin
 *
 */
public interface Persistencable {
	
	public void load(ExerciseCatalog exModel, QuizCatalog quModel);
	public void addQuiz(CreateQuizView view, ExerciseCatalog exModel, QuizCatalog quModel);
	public void deleteQuiz(DeleteQuizView window, DeleteQuizController controller, ExerciseCatalog exerciseCatalog, QuizCatalog quizCatalog);
	public void updateQuiz(ChangeQuizView view, ExerciseCatalog exerciseModel, QuizCatalog quizModel,
			List<Quiz> tempQuizzes, List<Exercise> tempExercises);
	public void deleteFromQuiz(ChangeQuizView view, List<Quiz> tempQuizzes, List<Exercise> tempExercises);
	public void addToQuiz(ChangeQuizView view, List<Quiz> tempQuizzes, List<Exercise> tempExercises, 
			ChangeQuizController controller);

}

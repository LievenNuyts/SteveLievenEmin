/**
 * 
 */
package persistenty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import controller.ChangeQuizController;
import controller.DeleteQuizController;
import model.Exercise;
import model.ExerciseCatalog;
import model.Quiz;
import model.QuizCatalog;
import view.ChangeQuizView;
import view.CreateQuizView;
import view.DeleteQuizView;

/**
 * 
 * @author Emin
 *
 */
public class MysqlPersistenty implements Persistencable {
	
	public static Connection getConnection() throws Exception {
		String driver = "org.gjt.mm.mysql.Driver";
		String url = "jdbc:mysql://localhost/quizdb";
		String username = "root";
		String password = "root";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	@Override
	public void addQuiz(CreateQuizView view, ExerciseCatalog exModel,
			QuizCatalog quModel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteQuiz(DeleteQuizView window,
			DeleteQuizController controller, ExerciseCatalog exerciseCatalog,
			QuizCatalog quizCatalog) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateQuiz(ChangeQuizView view, ExerciseCatalog exerciseModel,
			QuizCatalog quizModel, List<Quiz> tempQuizzes,
			List<Exercise> tempExercises) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFromQuiz(ChangeQuizView view, List<Quiz> tempQuizzes,
			List<Exercise> tempExercises) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToQuiz(ChangeQuizView view, List<Quiz> tempQuizzes,
			List<Exercise> tempExercises, ChangeQuizController controller) {
		// TODO Auto-generated method stub
		
	}
	
}

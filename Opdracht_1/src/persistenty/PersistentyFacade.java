/**
 * 
 */
package persistenty;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.ChangeQuizController;
import controller.DeleteQuizController;
import model.EnumerationExercise;
import model.Exercise;
import model.ExerciseCatalog;
import model.MultipleChoiceExercise;
import model.Quiz;
import model.QuizCatalog;
import model.SimpleExercise;
import model.Teacher;
import model.Exercise.ExerciseCategory;
import utils.DateGC;
import view.ChangeQuizView;
import view.CreateQuizView;
import view.DeleteQuizView;

/**
 * @author java
 *
 */
public class PersistentyFacade {
	public String persistenty;
	public Persistencable persistencable;
	
	public PersistentyFacade() {
		setPersistenty();
		
		if (persistenty == "TextPersistenty") {
			persistencable = new TextPersistenty();
		}
		else{
			persistencable = new MysqlPersistenty();
		}
	}
	
	public void load(ExerciseCatalog exModel, QuizCatalog quModel){
		persistencable.load(exModel, quModel);
	}
	
	public void addQuiz(CreateQuizView view, ExerciseCatalog exModel, QuizCatalog quModel){
		persistencable.addQuiz(view, exModel, quModel);
	}

	public void deleteQuiz(DeleteQuizView window, DeleteQuizController controller, ExerciseCatalog exerciseCatalog, QuizCatalog quizCatalog){
		persistencable.deleteQuiz(window, controller, exerciseCatalog, quizCatalog);
	}
	
	public void updateQuiz(ChangeQuizView view, ExerciseCatalog exerciseModel, QuizCatalog quizModel,
			List<Quiz> tempQuizzes, List<Exercise> tempExercises){
		persistencable.updateQuiz(view, exerciseModel, quizModel, tempQuizzes, tempExercises);
	}
	
	public void deleteFromQuiz(ChangeQuizView view, List<Quiz> tempQuizzes, List<Exercise> tempExercises){
		persistencable.deleteFromQuiz(view, tempQuizzes, tempExercises);
	}
	
	public void addToQuiz(ChangeQuizView view, List<Quiz> tempQuizzes, List<Exercise> tempExercises,
			ChangeQuizController controller){
		persistencable.addToQuiz(view, tempQuizzes, tempExercises, controller);
	}
	
	public void setPersistenty(){
		// Get init.dat file
		File file = new File("src" + File.separator + "files" + File.separator + "init.dat");
	 
		try{
			// Scan through file
			Scanner scanner = new Scanner(file);
			
			List<String> tempExercises = new ArrayList<String>();
			
			// Add each line as String object to tempExercises list
			while (scanner.hasNextLine()){
			  tempExercises.add(scanner.nextLine());
			}
			
			if (scanner!=null){
				scanner.close();
			}
			
			Scanner scanner2 = new Scanner(tempExercises.get(2));
			scanner2.skip("persistentyMethod=");
			String temp = scanner2.next();
			if (temp.equals("Text")){
				this.persistenty = "TextPersistenty";
			}
			else{
				this.persistenty = "MysqlPersistenty";
			}
			
		} catch(FileNotFoundException ex){
			System.out.println("Bestand niet gevonden!");
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		PersistentyFacade d = new PersistentyFacade();
		d.setPersistenty();
		System.out.println(d.persistenty);
	}
}

package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

public class PersistenceFacade {
	
	public IPersistencable iPersistencable;
	
	// Constructors
	
	/**
	 * Default constructor
	 */
	public PersistenceFacade() {
		setPersistenty();
	}
	
	//Modifiers
	
	public void load(ExerciseCatalog exModel, QuizCatalog quModel){
		iPersistencable.load(exModel, quModel);
	}
	public void addQuiz(CreateQuizView view, ExerciseCatalog exModel, QuizCatalog quModel){
		iPersistencable.addQuiz(view, exModel, quModel);
	}
	public void deleteQuiz(DeleteQuizView window, DeleteQuizController controller, ExerciseCatalog exerciseCatalog, QuizCatalog quizCatalog){
		iPersistencable.deleteQuiz(window, controller, exerciseCatalog, quizCatalog);
	}
	public void updateQuiz(ChangeQuizView view, ExerciseCatalog exerciseModel, QuizCatalog quizModel,
			List<Quiz> tempQuizzes, List<Exercise> tempExercises){
		iPersistencable.updateQuiz(view, exerciseModel, quizModel, tempQuizzes, tempExercises);
	}
	public void deleteFromQuiz(ChangeQuizView view, List<Quiz> tempQuizzes, List<Exercise> tempExercises){
		iPersistencable.deleteFromQuiz(view, tempQuizzes, tempExercises);
	}
	public void saveAndClose(DeleteQuizView window, DeleteQuizController controller, ExerciseCatalog exerciseCatalog, QuizCatalog quizCatalog){
		iPersistencable.saveAndClose(window, controller, exerciseCatalog, quizCatalog);
	}
	public void addToQuiz(ChangeQuizView view, List<Quiz> tempQuizzes, List<Exercise> tempExercises,
			ChangeQuizController controller){
		iPersistencable.addToQuiz(view, tempQuizzes, tempExercises, controller);
	}
	
	/**
	 * Method to set correct persistence based on info in init.txt file
	 */
	public void setPersistenty(){
		// Get init.dat file
		File file = new File("src" + File.separator + "files" + File.separator + "init.dat");
	 
		try{
			// Scan through file
			Scanner scanner = new Scanner(file);
			
			List<String> tempLine = new ArrayList<String>();
			
			// Add each line as String object to tempLine list
			while (scanner.hasNextLine()){
				tempLine.add(scanner.nextLine());
			}
			
			if (scanner!=null){
				scanner.close();
			}
			
			// Find className
			Scanner scanner2 = new Scanner(tempLine.get(2));
			scanner2.skip("persistentyMethod=");
			String className = scanner2.next();
			while(scanner2.hasNext()){
				className += scanner2.next();
			}
			
			if (scanner2!=null){
				scanner2.close();
			}
			
			// Set instance of the chosen persistence
			iPersistencable = (IPersistencable) Class.forName("persistence." + className).newInstance();
		} catch(FileNotFoundException ex){
			System.out.println("Bestand niet gevonden!");
		} catch(ClassNotFoundException ex){
			System.out.println(ex.getMessage());
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}

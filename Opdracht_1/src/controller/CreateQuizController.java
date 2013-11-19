/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import model.EnumerationExercise;
import model.Exercise;
import model.ExerciseCatalog;
import model.MultipleChoiceExercise;
import model.Quiz;
import model.QuizCatalog;
import model.QuizExercise;
import model.SimpleExercise;
import view.CreateQuizView;

/**
 * @author java
 *
 */
public class CreateQuizController {
	
	private CreateQuizView view;
	private ExerciseCatalog exModel;
	private QuizCatalog quModel;
	
	/**
	 * Constructor with 3 params
	 * 
	 * @param view
	 * @param exModel
	 * @param quModel
	 * @throws IllegalArgumentException
	 */
	public CreateQuizController(CreateQuizView view, ExerciseCatalog exModel,QuizCatalog quModel) 
			throws IllegalArgumentException{
		this.view = view;
		this.exModel = exModel;
		this.quModel = quModel;
		
		// Load exercises and quizzes
		this.exModel.readExercisesFromFile();
		this.quModel.readQuizzesFromFile();
		this.exModel.createQuizExercises(exModel.getExercises(), quModel.getQuizCatalogs());
		
		// Add exercises to exercisesList (JList)
		this.view.setExercisesList(exModel.getExercises());
		
		// Assigning listeners
		this.view.addAddToQuizButtonListener(new AddToQuizButtonListener());
		this.view.addRemoveFromQuizButtonListener(new RemoveFromQuizButtonListener());
	}
	
	// Listeners
	
	/**
	 * 
	 * AddToQuizButtonListener
	 *
	 */
	public class AddToQuizButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			try{
				AddToAddedExercisesList(view.getSelectedValueToList());
			}
			catch(IllegalArgumentException ex){
				System.out.println(ex);
				view.displayErrorMessage(ex.getMessage());
			}
		}
	}
	
	/**
	 * 
	 * @author RemoveFromQuizButton
	 *
	 */
	public class RemoveFromQuizButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try{
				removeFromAddedExercisesList(view.getSelectedValueFromList());
			}
			catch(IllegalArgumentException ex){
				System.out.println(ex);
				view.displayErrorMessage(ex.getMessage());
			}
		}
	}
	
	// Selectors
	
	/**
	 * get addedExercisesList
	 * 
	 * @return
	 */
	public List<String> getAddedExercisesList(){
		List<String> exercises = new ArrayList<String>();
		
		for (int i = 0; i < view.getListModel2().size(); i++) {
			exercises.add(String.valueOf(view.getListModel2().get(i)));
		}
		
		return exercises;
	}
	
	// Modifiers
	
	/**
	 * Add exercises to addedExercisesList
	 * 
	 * @param exercise
	 * @throws IllegalArgumentException
	 */
	public void AddToAddedExercisesList(String exercise) throws IllegalArgumentException{
		for (String q : getAddedExercisesList()){
			if (q.equals(exercise))throw new IllegalArgumentException("Opdracht is al toegevoegd");
		}
		view.getListModel2().addElement(exercise);
	}
	
	/**
	 * Remove exercises from addedExercisesList
	 * 
	 * @param exercise
	 * @throws IllegalArgumentException
	 */
	public void removeFromAddedExercisesList(String exercise) throws IllegalArgumentException{
		if (exercise == "null")throw new IllegalArgumentException("Selecteer Opdracht");
		
		view.getListModel2().removeElement(exercise);
	}

	public static void main(String[] args) {
        
		ExerciseCatalog ec = new ExerciseCatalog();
		QuizCatalog qc = new QuizCatalog();
		
		CreateQuizView view = new CreateQuizView();
		
		CreateQuizController controller = new CreateQuizController(view, ec, qc);
		
		view.setVisible(true);
    }
	
}

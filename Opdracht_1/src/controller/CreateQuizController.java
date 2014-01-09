package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;

import model.Exercise;
import model.ExerciseCatalog;
import model.QuizCatalog;
import view.CreateQuizView;
import persistence.PersistenceFacade;

/**
 * @author java
 *
 */
public class CreateQuizController {
	
	private CreateQuizView view;
	private ExerciseCatalog exModel;
	private QuizCatalog quModel;
	private PersistenceFacade perFacade;
	
	// Constructors
	
	/**
	 * Constructor with 3 parameters
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
		this.perFacade = new PersistenceFacade();
		
		this.perFacade.load(exModel, quModel);
		
		// Load exercises in exercisesList (JList)
		loadPerCategory(exModel.getExercises());
		
		// Assigning listeners
		this.view.addAddToQuizButtonListener(new AddToQuizButtonListener());
		this.view.addAddQuizButtonListener(new AddQuizButtonListener());
		this.view.addRemoveFromQuizButtonListener(new RemoveFromQuizButtonListener());
		this.view.addMoveUpButtonListener(new MoveUpButtonListener());
		this.view.addCategoriesComboBoxListener(new CategoriesComboBoxListener());
		this.view.addSortExercisesComboBoxListener(new SortExercisesComboBox());
	}
	
	// Listeners
	
	/**
	 * 
	 * addQuizButtonListener
	 *
	 */
	public class AddQuizButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			perFacade.addQuiz(view, exModel, quModel);
			perFacade.load(exModel, quModel);
		}
	}
	
	/**
	 * 
	 * addToQuizButtonListener
	 *
	 */
	public class AddToQuizButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			try{
				AddToAddedExercisesTable(view.getSelectedValueToList());
			}
			catch(IllegalArgumentException ex){
				System.out.println(ex);
				view.displayErrorMessage(ex.getMessage());
			}
		}
	}
	
	/**
	 * 
	 * removeFromQuizButton
	 *
	 */
	public class RemoveFromQuizButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try{
				removeFromAddedExercisesTable(view.getSelectedExerciseValueFromTable());
			}
			catch(IllegalArgumentException ex){
				System.out.println(ex);
				view.displayErrorMessage(ex.getMessage());
			}
		}
	}
	
	/**
	 * 
	 * moveUpButton
	 *
	 */
	public class MoveUpButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try{
				moveUpExercise(view.getSelectedRow());
			}
			catch(IllegalArgumentException ex){
				System.out.println(ex);
				view.displayErrorMessage(ex.getMessage());
			}
		}
	}
	
	/**
	 * 
	 * categoriesComboBox
	 *
	 */
	public class CategoriesComboBoxListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try{
				loadPerCategory(exModel.getExercises());
			}
			catch(IllegalArgumentException ex){
				System.out.println(ex);
				view.displayErrorMessage(ex.getMessage());
			}
		}
	}
	
	/**
	 * 
	 * sortExercisesComboBox
	 *
	 */
	public class SortExercisesComboBox implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try{
				sortExercises(exModel.getExercises());
			}
			catch(IllegalArgumentException ex){
				System.out.println(ex);
				view.displayErrorMessage(ex.getMessage());
			}
		}
	}
	
	// Selectors

	/**
	 * get addedExercises
	 * 
	 * @return
	 */
	public List<String> getAddedExercises(){
		List<String> exercises = new ArrayList<String>();
		
		for (int i = 0; i < view.getDataModel().getRowCount(); i++) {
			exercises.add(String.valueOf(view.getDataModel().getValueAt(i, 0)));
		}
		
		return exercises;
	}
	
	// Modifiers
	
	/**
	 * Add exercises to addedExercisesRable
	 * 
	 * @param exercise
	 * @throws IllegalArgumentException
	 */
	public void AddToAddedExercisesTable(String exercise) throws IllegalArgumentException{
		if (exercise == "null")throw new IllegalArgumentException("Selecteer een opdracht");
		for (String q : getAddedExercises()){
			if (q.equals(exercise))throw new IllegalArgumentException("Opdracht is al toegevoegd");
		}
		
		view.getDataModel().addRow(new String[]{exercise, ""});
		
		// Change addedExerciseLabel (JLabel)
		view.setAmountAddedExercises(String.valueOf(view.getDataModel().getRowCount()));
	}
	
	/**
	 * Remove exercises from addedExercisesTable
	 * 
	 * @param exercise
	 * @throws IllegalArgumentException
	 */
	public void removeFromAddedExercisesTable(String exercise) throws IllegalArgumentException{
		if (view.getSelectedRow() == -1)throw new IllegalArgumentException("Selecteer Opdracht");
		
		view.getDataModel().removeRow(view.getSelectedRow());
		
		// Change addedExerciseLabel (JLabel)
		view.setAmountAddedExercises(String.valueOf(view.getDataModel().getRowCount()));
	}
	
	/**
	 * Move up exercise
	 * 
	 * @param exerciseIndex
	 */
	public void moveUpExercise(int exerciseIndex){
		if (view.getSelectedRow() == 0){ 
			String tempRow[] = {String.valueOf(view.getDataModel().getValueAt(view.getSelectedRow(), 0)), 
					String.valueOf(view.getDataModel().getValueAt(view.getSelectedRow(), 1))};
			view.getDataModel().removeRow(view.getSelectedRow());
			view.getDataModel().addRow(tempRow);
			tempRow = null;
		}
		else{
			view.getDataModel().moveRow(view.getSelectedRow(), view.getSelectedRow(), view.getSelectedRow() - 1);
		}
	}
	
	/**
	 * Change loaded list by corresponding category
	 */
	public void loadPerCategory(List<Exercise> exercises){
		exercises = exModel.getExercises();
		
		if (view.getSelectedCategory() == "Alle"){
			this.view.setExercisesList(exercises);
		}
		else{
			List<Exercise> tempExs = new ArrayList<Exercise>();
			
			for (Exercise ex : exercises){
				if (view.getSelectedCategory() == String.valueOf(ex.getCategory())){
					tempExs.add(ex);
				}
			}
			this.view.setExercisesList(tempExs);
		}
	}
	
	/**
	 * Sort exercises
	 */
	public void sortExercises(List<Exercise> exercises){
		try {
			ExerciseCatalog tempEX = exModel.clone();
			exercises = tempEX.getExercises();
			
			if (view.getSelectedSortByValue() == "categorie"){
				Collections.sort(exercises, CreateQuizController.ExerciseCategoryComparator);
				this.view.setExercisesList(exercises);
			}
			else if (view.getSelectedSortByValue() == "vraag"){
				Collections.sort(exercises);
				this.view.setExercisesList(exercises);
			}
			else{
				loadPerCategory(exercises);
			}
		} catch (CloneNotSupportedException ex) {
			view.displayErrorMessage(ex.getMessage());
		}
	}
	
	/**
	 * Comparator to compare by category in Exercise class
	 */
	public static Comparator<Exercise> ExerciseCategoryComparator = new Comparator<Exercise>() {
		public int compare(Exercise exercise1, Exercise exercise2) {
			String exericseCategory1 = String.valueOf(exercise1.getCategory()).toUpperCase();
			String exericseCategory2 = String.valueOf(exercise2.getCategory()).toUpperCase();
			
			//ascending order
			return exericseCategory1.compareTo(exericseCategory2);
		}
	};

	public static void main(String[] args) {
        
		ExerciseCatalog ec = new ExerciseCatalog();
		QuizCatalog qc = new QuizCatalog();
		
		CreateQuizView view = new CreateQuizView();
		
		CreateQuizController controller = new CreateQuizController(view, ec, qc);
		
		view.setVisible(true);
    }
	
}

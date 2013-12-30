package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import persistenty.PersistentyFacade;
import model.Exercise;
import model.ExerciseCatalog;
import model.Quiz;
import model.QuizExercise;
import model.QuizCatalog;
import view.ChangeQuizView;

/**
 * 
 * @author Steve
 * @version 4/11/2013
 * 
 */

public class ChangeQuizController {

	private ChangeQuizView view;
	private QuizCatalog quizModel;
	private ExerciseCatalog exerciseModel;
	private List<Quiz> resultList;
	private List<Quiz> tempQuizzes;
	private List<Exercise> tempExercises;
	private PersistentyFacade perFacade;

	public ChangeQuizController(ChangeQuizView view, QuizCatalog quizModel,
			ExerciseCatalog exerciseModel) {
		this.view = view;
		this.quizModel = quizModel;
		this.exerciseModel = exerciseModel;
		
		this.tempQuizzes = quizModel.getQuizCatalogs();
		this.tempExercises = exerciseModel.getExercises();
		this.perFacade = new PersistentyFacade();
		// Load exercises and quizzes
		
		this.perFacade.load(exerciseModel, quizModel);

		// Load exercises & quizzes in exercisesList & quizList (JList)
				loadExercisesPerCategory(exerciseModel.getExercises());
				loadQuizzes(quizModel.getQuizCatalogs());

		// Add listeners
		
		this.view.addAddListener(new QuizListener());
		this.view.addUpdateListener(new UpdateListener());
		this.view.addDeleteListener(new DeleteListener());
		this.view.addSearchListener(new SearchListener());
		this.view.addShowListener(new ShowListener());
	}

	class QuizListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			perFacade.addToQuiz(view, tempQuizzes, tempExercises, ChangeQuizController.this);
		}
	}
	
	class UpdateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			perFacade.updateQuiz(view, exerciseModel, quizModel, tempQuizzes, tempExercises);
		}
	}

	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			perFacade.deleteFromQuiz(view, tempQuizzes, tempExercises);
		}
	}

		class SearchListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					System.out.println("Searchbutton");
					Search(quizModel.getQuizCatalogs());
				}

				catch (Exception ex) {

					System.out.println(ex);

					view.displayErrorMessage("Error.");

				}
			}
		}

		class ShowListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					
					view.getSelectedCategory();
					System.out.println("Showbutton");
					
				}

				catch (Exception ex) {

					System.out.println(ex);

					view.displayErrorMessage("Error.");

				}
			}
		}
		
		
		public void loadExercisesPerCategory(List<Exercise> exercises){
			exercises = exerciseModel.getExercises();
			
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
		
		public void loadQuizzes(List<Quiz> quizzes){
			quizzes = quizModel.getQuizCatalogs();
			this.view.setQuizList(quizzes);
		}
		
		public void addToQuiz(Quiz quiz, Exercise exercise) throws IllegalArgumentException{
			
			
		}
		
		public int getIndexPosition(Quiz quiz) {
			  return quizModel.getQuizCatalogs().indexOf(quiz);
		}
		
		//Searchmethod
		
		public void Search(List<Quiz> quizList) throws IllegalArgumentException{

			String searchString = view.getQuizTitle().toLowerCase();

			if (searchString.isEmpty()){
				
				this.view.setQuizList(quizList);
				JOptionPane.showMessageDialog(null, "Niets ingegeven. \nVolledige lijst.");
			}
			else
			{

				for (Quiz curVal : quizList){
					if (curVal.getSubject() != null && curVal.getSubject().toLowerCase().contains(searchString)){

						resultList.add(curVal);					  
					}
				}

				this.view.setQuizList(resultList);
				resultList.clear();
			}

	}




		

		public static void main(String[] args) {
	        
			ExerciseCatalog em = new ExerciseCatalog();
			QuizCatalog qm = new QuizCatalog();
			ChangeQuizView view = new ChangeQuizView();
			
			
			ChangeQuizController controller = new ChangeQuizController(view, qm, em);
			
			view.setVisible(true);
	    }
		
}

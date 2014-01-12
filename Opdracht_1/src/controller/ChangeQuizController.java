package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Exercise;
import model.ExerciseCatalog;
import model.Quiz;
import model.QuizCatalog;
import persistence.PersistenceFacade;
import statePattern.StateContext;
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
	private PersistenceFacade perFacade;
	
	private StateContext stateContext;

	public ChangeQuizController(ChangeQuizView view, QuizCatalog quizModel,
			ExerciseCatalog exerciseModel) {
		this.view = view;
		this.quizModel = quizModel;
		this.exerciseModel = exerciseModel;
		
		this.tempQuizzes = quizModel.getQuizCatalogs();
		this.tempExercises = exerciseModel.getExercises();
		this.perFacade = new PersistenceFacade();
		this.resultList = quizModel.getQuizCatalogs();

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
		this.view.addSelectListener(new SelectListener());
		
		stateContext = new StateContext();
		
		stateContext.setStateBehavior(quizModel.getQuizCatalogs().get(0));
		stateContext.behavior(ChangeQuizController.this, view);
	}

	class QuizListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			perFacade.addToQuiz(view, tempQuizzes, tempExercises, ChangeQuizController.this);
			//perFacade.load(exerciseModel, quizModel);
			view.UpdateExerciseInQuiz();
		}
	}
	
	class UpdateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			updateQuiz();
			perFacade.updateQuiz(view, exerciseModel, quizModel, tempQuizzes, tempExercises);
			//perFacade.load(exerciseModel, quizModel);
			view.UpdateExerciseInQuiz();
		}
	}

	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			perFacade.deleteFromQuiz(view, tempQuizzes, tempExercises);
			//perFacade.load(exerciseModel, quizModel);
			view.UpdateExerciseInQuiz();

		}
	}

	class SearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {				
				Search(quizModel.getQuizCatalogs());
			}

			catch (NullPointerException ex) {

				System.out.println(ex);

				view.displayErrorMessage("Zoekterm is NULL.");
			}
		}
	}
	
	class SelectListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {

			if (!event.getValueIsAdjusting()){
				JList<Quiz> source = (JList)event.getSource();

				Quiz selected = (Quiz)source.getSelectedValue();	            

				view.getExercisesInQuiz(selected.getQuizExercises());
				
				//update Quiz properties
				
				view.getComboAuthor().setSelectedItem(selected.getTeacher());
				view.getComboStatus().setSelectedItem(selected.getStatus());
				view.getComboGrade().setSelectedItem(selected.getLeerJaren());
				view.getTitleTextField().setText(selected.getSubject());
				
				stateContext.setStateBehavior(quizModel.getQuizCatalogs().get(getIndexPosition(selected)));
				stateContext.behavior(ChangeQuizController.this, view);
			}
		}
	}

	public void loadExercisesPerCategory(List<Exercise> exercises){
		exercises = exerciseModel.getExercises();
		
			List<Exercise> tempExs = new ArrayList<Exercise>();

			for (Exercise ex : exercises){				
					tempExs.add(ex);
			}
			this.view.setExercisesList(tempExs);
		
	}

	public void loadQuizzes(List<Quiz> quizzes){
		quizzes = quizModel.getQuizCatalogs();
		this.view.setQuizList(quizzes);
	}

	public void updateQuiz() throws IllegalArgumentException{
		view.getSelectedQuizValueFromList().setTeacher(view.getAuthor());
		view.getSelectedQuizValueFromList().setStatus(view.getStatus());
		view.getSelectedQuizValueFromList().setLeerJaren(view.getGrade());
		view.getSelectedQuizValueFromList().setSubject(view.getQuizTitle());
	}

	public int getIndexPosition(Quiz quiz) {
		return quizModel.getQuizCatalogs().indexOf(quiz);
	}

	//Searchmethod

	public void Search(List<Quiz> quizList) throws IllegalArgumentException{
		
		String searchString = view.getSearchText().toLowerCase();
		System.out.println(searchString + "searchMethod Value");	
		if (searchString.isEmpty() || searchString == null || searchString == ""){
			this.view.setQuizList(quizList);
			JOptionPane.showMessageDialog(null, "Niets ingegeven. \nVolledige lijst.");
		}
		else if (searchString.length() >= 1)
		{
			resultList.clear();
			
			for (Quiz curVal : quizList){
				if (curVal.getSubject().toLowerCase().contains(searchString)){

					System.out.println(curVal.getSubject());
					resultList.add(curVal);					  
				}
			}
			view.setQuizList(resultList);
			resultList.clear();
		}
		else {
			this.view.setQuizList(quizList);
			JOptionPane.showMessageDialog(null, "De zoekterm is leeg. \nVolledige lijst wordt weergegeven.");
		}
	}
}

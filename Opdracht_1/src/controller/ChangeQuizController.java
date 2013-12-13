package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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

	public ChangeQuizController(ChangeQuizView view, QuizCatalog quizModel,
			ExerciseCatalog exerciseModel) {
		this.view = view;
		this.quizModel = quizModel;
		this.exerciseModel = exerciseModel;

		// Load exercises and quizzes
		
		this.exerciseModel.readExercisesFromFile();
		this.quizModel.readQuizzesFromFile();
		
		this.exerciseModel.createQuizExercises(exerciseModel.getExercises(),
				quizModel.getQuizCatalogs());

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

			// dataMembers

			try {

				System.out.println("Addbutton");
				addToQuiz(view.getSelectedQuizValueFromList(), view.getSelectedExerciseValueFromList());

				
				//reload list
				//loadExercisesPerCategory(exerciseModel.getExercises());

			} 

			catch(IllegalArgumentException ex){
				System.out.println(ex);
				view.displayErrorMessage(ex.getMessage());
			}
		}

	}
	
	class UpdateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// dataMembers

			try {

				System.out.println("Updatebutton");
			

				quizModel.writeQuizzesToFile();
				
				view.displayErrorMessage("Quiz is geüpdatet");

			} 

			catch(IllegalArgumentException ex){
				System.out.println(ex);
				view.displayErrorMessage(ex.getMessage());
			}
		}

	}

	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// dataMembers

			try {

				System.out.println("Deletebutton");
				removeFromQuiz(view.getSelectedQuizValueFromList(), view.getSelectedQuizExerciseValueFromList());
				
				//reload list
				//loadExercisesPerCategory(exerciseModel.getExercises());
			}
			catch(IllegalArgumentException ex){
				System.out.println(ex);
				view.displayErrorMessage(ex.getMessage());
			}
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
		
		public void removeFromQuiz(Quiz quiz, QuizExercise quizExercise) throws IllegalArgumentException{

			//remove from list
			quiz.getQuizExercises().remove(quizExercise);
			
			//test
			System.out.println(quizExercise);
			System.out.println(quiz);
			
		}
		
		public void addToQuiz(Quiz quiz, Exercise exercise) throws IllegalArgumentException{
			
			//add to quiz
			int maxScore = 5;   //Integer.parseInt(view.getMaxScore());

			QuizExercise qe = new QuizExercise(maxScore, quiz, exercise);
			quiz.addQuizExercise(qe);
			
			//test
			for(int index = 0; index < quizModel.getQuizCatalogs().size(); index++){
			      System.out.println(quizModel.getQuizCatalogs().get(index));   			      
			}
			
			for(Quiz q : quizModel.getQuizCatalogs()){
				if(q.getSubject().equalsIgnoreCase(quiz.getSubject())){
					
					int indexPos = getIndexPosition(q);
					System.out.println(indexPos);
					quizModel.getQuizCatalogs().set(indexPos, quiz);
				}
				
			}	
			quizModel.writeQuizzesToFile();
		}
		
		private int getIndexPosition(Quiz quiz) {
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

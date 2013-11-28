package controller;

/**
 *  
 * @author Lieven
 * @version 23/11/2013
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Exercise;
import model.ExerciseCatalog;
import model.Quiz;
import model.QuizCatalog;
import model.QuizExercise;
import view.CreateQuizView;
import view.DeleteQuizView;

public class DeleteQuizController {

	private DeleteQuizView window;
	private QuizCatalog quizCatalog;
	private ExerciseCatalog exerciseCatalog;
	
	
	//CLASS CONSTRUCTORS
	
	public DeleteQuizController(){	
		window = new DeleteQuizView();
	}
	
	public DeleteQuizController(QuizCatalog quizCatalog, ExerciseCatalog exerciseCatalog){
	
		this.setQuizCatalog(quizCatalog);
		this.quizCatalog.readQuizzesFromFile();
		this.setExerciseCatalog(exerciseCatalog);
		this.exerciseCatalog.readExercisesFromFile();
		
		this.window = new DeleteQuizView(quizCatalog);
		this.window.setQuizCatalog(quizCatalog);
		this.window.addDeleteQuizListener(new DeleteQuizListener());
		this.window.addCloseWindowListener(new CloseWindowListener());
		this.window.addButtonUpListener(new ButtonUpListener());
		this.window.addButtonDownListener(new ButtonDownListener());
		this.window.addSaveAndCloseListener(new SaveAndCloseWindowListener());
	}

	//METHODS
	//zien dat de relaties ook verwijderd zijn (in exercise en quizcatalog klasses
	
	
	//make window visible or invisible
	public void makeWindowVisible(){
		this.window.setVisible(true);
	}
	
	public void hideWindow(){
		this.window.setVisible(false);
	}
	
	//GETTERS & SETTERS
	
	public void setQuizCatalog(QuizCatalog quizCatalog){
		this.quizCatalog = quizCatalog;
	}
	
	public QuizCatalog getQuizCatalog(){
		return this.quizCatalog;	
	}	

	public void setExerciseCatalog(ExerciseCatalog exerciseCatalog){
		this.exerciseCatalog = exerciseCatalog;
	}
	
	public ExerciseCatalog getExerciseCatalog(){
		return this.exerciseCatalog;	
	}
	
	//EVENT LISTENERS
	
	
	//EUUUUJ BEZIET DEES EENS MANNEN ! NO IDEA OF DA GA WERKEN
	class DeleteQuizListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		
			try {
				String quizIDtoDelete = (String) window.getJTable().getValueAt(window.getJTable().getSelectedRow(), 0);
					
				for(Quiz quiz : window.getQuizCatalog().getQuizCatalogs()){
					
					if(quiz.getQuizId() == Integer.parseInt(quizIDtoDelete)){
						
						//loop through all QuizExercises in the quiz that will be deleted
						for(QuizExercise qE : quiz.getQuizExercises()){						
							//loop through all exercises in ExerciseCatalog
							for(Exercise exercise : exerciseCatalog.getExercises()){
								//lookup the exercise which is linked to the QuizExercise
								//and look for the same exercise in the ExerciseCatalog
								if(qE.getExercise().equals(exercise)){
									//loop through QuizExercise list of the exercise
									for(QuizExercise qE2 : exercise.getQuizExercises()){						
										//look for QuizExercise that matches the above QuizExercise
										if(qE.equals(qE2)){
											//matching QuizExercise can be deleted
											exercise.removeQuizExercise(qE2);
											break;//to stop the loop if found, no need to check the others
										}
									}	
									break;//to stop the loop if found, no need to check the others
								}
							}								
						}						
						
						window.getQuizCatalog().deleteQuiz(quiz);				
						break; //to stop the loop if found, no need to check the others
					}
				}
				window.resetTable();
				window.loadJTable();
			}

			catch (Exception exc) {
				System.out.println(exc);
			}
		}
	}

	class SaveAndCloseWindowListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		
			try {
				quizCatalog.writeQuizzesToFile();
				window.dispose();	
			}

			catch (Exception exc) {
				System.out.println(exc);
			}
		}
	}
	
	class CloseWindowListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		
			try {
				window.dispose();
			}

			catch (Exception exc) {
				System.out.println(exc);
			}
		}
	}

	class ButtonUpListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		
			try {
				int rowIndex = window.getJTable().getSelectedRow();
				
				if(rowIndex > 0){
					rowIndex--;
					window.getJTable().setRowSelectionInterval(rowIndex, rowIndex);		
				}
			}

			catch (Exception exc) {
				System.out.println(exc);
			}
		}
	}

	class ButtonDownListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		
			try {
				int rowIndex = window.getJTable().getSelectedRow();
				
				if(rowIndex < window.getJTable().getRowCount()-1){
					rowIndex++;
					window.getJTable().setRowSelectionInterval(rowIndex, rowIndex);
				}		
			}

			catch (Exception exc) {
				System.out.println(exc);
			}
		}
	}
	
	/*
	// STATIC VOID MAIN
	
	public static void main(String[] args) {
	    
		QuizCatalog qCatalog = new QuizCatalog();
		ExerciseCatalog eCatalog = new ExerciseCatalog();
		DeleteQuizController controller = new DeleteQuizController(qCatalog, eCatalog);	
		controller.window.setVisible(true);
	}*/
	
}




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
		this.exerciseCatalog.createQuizExercises(this.exerciseCatalog.getExercises(), this.quizCatalog.getQuizCatalogs());
		
		this.window = new DeleteQuizView(quizCatalog);
		this.window.setQuizCatalog(quizCatalog);
		this.window.addDeleteQuizListener(new DeleteQuizListener());
		this.window.addCloseWindowListener(new CloseWindowListener());
		this.window.addButtonUpListener(new ButtonUpListener());
		this.window.addButtonDownListener(new ButtonDownListener());
		this.window.addSaveAndCloseListener(new SaveAndCloseWindowListener());
	}

	//METHODS
	
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
	
	class DeleteQuizListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) throws IllegalArgumentException{
		
			try {
				
				if(window.getJTable().getRowCount() != 0){
				
					String quizIDtoDelete = (String) window.getJTable().getValueAt(window.getJTable().getSelectedRow(), 0);	
				
					for(Quiz quiz : getQuizCatalog().getQuizCatalogs()){
					
						if(quiz.getQuizId() == Integer.parseInt(quizIDtoDelete)){
							System.out.println("0");	
							
							//check the status of the quiz that will be deleted
							if(quiz.getStatus().toString() == "Under Construction" || quiz.getStatus().toString() == "Completed"){
							
								//loop through all QuizExercises in the quiz that will be deleted
								for(QuizExercise qE : quiz.getQuizExercises()){	
									System.out.println("1");
									//loop through all exercises in ExerciseCatalog
									for(Exercise exercise : exerciseCatalog.getExercises()){
										System.out.println("2");
										//lookup the exercise which is linked to the QuizExercise
										//and look for the same exercise in the ExerciseCatalog
										if(qE.getExercise().equals(exercise)){
											System.out.println("3");
											//loop through QuizExercise list of the exercise
											for(QuizExercise qE2 : exercise.getQuizExercises()){
												System.out.println("4");
												//look for QuizExercise that matches the above QuizExercise
												if(qE.equals(qE2)){
													System.out.println("5");
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
								window.showPopup("Quiz \"" + quiz.getSubject() + "\" verwijderd");
								break; //to stop the loop if found, no need to check the others
							}
							else{
								window.showPopup("!! Deze quiz kan niet meer verwijderd worden !!\n"
										+ "Enkel een quiz met status 'Under Construction of 'Completed' kan verwijderd worden.");
							}		
						}
					}
							
					//reload both JTables
					window.resetTable();
					window.resetExTable();
				}
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
				exerciseCatalog.writeExercisesToFile();
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
				
				if(rowIndex == 0){	
					rowIndex = window.getJTable().getRowCount();
					System.out.println(rowIndex);
					window.getJTable().setRowSelectionInterval(rowIndex-1, rowIndex-1);	
				}			
				else{
					rowIndex--;
					window.getJTable().setRowSelectionInterval(rowIndex, rowIndex);		
				}
				window.resetExTable();
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
				else{
					window.getJTable().setRowSelectionInterval(0, 0);
				}
				window.resetExTable();
			}

			catch (Exception exc) {
				System.out.println(exc);
			}
		}
	}

	// STATIC VOID MAIN
	
	public static void main(String[] args) {
	    
		QuizCatalog qCatalog = new QuizCatalog();
		ExerciseCatalog eCatalog = new ExerciseCatalog();
		DeleteQuizController controller = new DeleteQuizController(qCatalog, eCatalog);
		controller.window.setVisible(true);
	}
}




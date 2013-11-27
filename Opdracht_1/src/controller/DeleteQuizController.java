package controller;

/**
 *  
 * @author Lieven
 * @version 23/11/2013
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ExerciseCatalog;
import model.Quiz;
import model.QuizCatalog;
import view.CreateQuizView;
import view.DeleteQuizView;

public class DeleteQuizController {

	private DeleteQuizView window;
	private QuizCatalog quizCatalog;
	
	
	//CLASS CONSTRUCTORS
	
	public DeleteQuizController(){	
		window = new DeleteQuizView();
	}
	
	public DeleteQuizController(QuizCatalog quizCatalog){
	
		this.setQuizCatalog(quizCatalog);
		this.quizCatalog.readQuizzesFromFile();
		
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

	
	//EVENT LISTENERS
	
	class DeleteQuizListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		
			try {
				String quizIDtoDelete = (String) window.getJTable().getValueAt(window.getJTable().getSelectedRow(), 0);
					
				for(Quiz quiz : window.getQuizCatalog().getQuizCatalogs()){
					
					if(quiz.getQuizId() == Integer.parseInt(quizIDtoDelete)){
						window.getQuizCatalog().deleteQuiz(quiz);
						
						break;
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
	
	
	// STATIC VOID MAIN
	
	public static void main(String[] args) {
	    
		QuizCatalog qCatalog = new QuizCatalog();	
		DeleteQuizController controller = new DeleteQuizController(qCatalog);	
		controller.window.setVisible(true);
	}
	
}




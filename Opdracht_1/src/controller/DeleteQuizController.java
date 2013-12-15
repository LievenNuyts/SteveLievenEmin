package controller;

/**
 *  
 * @author Lieven
 * @version 23/11/2013
 *
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	
		try{
			
			this.setQuizCatalog(quizCatalog);
			this.quizCatalog.readQuizzesFromFile();
			this.setExerciseCatalog(exerciseCatalog);
			this.exerciseCatalog.readExercisesFromFile();
			this.exerciseCatalog.createQuizExercises(this.exerciseCatalog.getExercises(), this.quizCatalog.getQuizCatalogs());
			
			this.window = new DeleteQuizView();
			
			this.window.addDeleteQuizListener(new DeleteQuizListener());
			this.window.addCloseWindowListener(new CloseWindowListener());
			this.window.addButtonUpListener(new ButtonUpListener());
			this.window.addButtonDownListener(new ButtonDownListener());
			this.window.addSaveAndCloseListener(new SaveAndCloseWindowListener());
			
			//load JTables in window
			this.loadQuizTable();
			this.loadExTable();
			
			//set column width of JTABLE for quizzes
			this.window.setColumnWidth();
		}
		catch (Exception exc) {
			System.out.println(exc);
			window.showPopup(exc.getMessage());
		}
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
	
	//METHODS
	//make window visible or invisible
	public void makeWindowVisible(){
		this.window.setVisible(true);
	}
	
	public void hideWindow(){
		this.window.setVisible(false);
	}
	
	
	//JTABLE METHODS
	
	//Load quizJTable from window with quiz elements: ID, Teacher, Subject, Grade, Status (per row)
	public void loadQuizTable(){
		
		try{
		
			for(Quiz quiz : quizCatalog.getQuizCatalogs()){
		
				String[] dataBuilder = new String[5];
				
				dataBuilder[0] = Integer.toString(quiz.getQuizId());
				dataBuilder[1] = quiz.getTeacher().toString();
				dataBuilder[2] = quiz.getSubject();
				dataBuilder[3] = Integer.toString(quiz.getLeerJaren());
				dataBuilder[4] = quiz.getStatus().toString();
				
				window.getQModel().addRow(dataBuilder);
			}
			
			window.getJTableQuiz().setModel(window.getQModel());
			window.getJTableQuiz().setAutoCreateRowSorter(true);
			
			if(window.getJTableQuiz().getRowCount() != 0){
				window.getJTableQuiz().setRowSelectionInterval(0, 0);
			}
		}
		catch (Exception exc) {
			System.out.println(exc);
			window.showPopup(exc.getMessage());
		}
	}
	
	//Load exerciseJTable from window with exercises of the selected quiz
	public void loadExTable(){
		
		try{
			if(window.getJTableQuiz().getRowCount() != 0){
			
				//Select QuizID from the JTABLE
				String quizIDtoLookup = (String) window.getJTableQuiz().getValueAt(window.getJTableQuiz().getSelectedRow(), 0);	
				//loop through the quizzes
				for(Quiz quiz : getQuizCatalog().getQuizCatalogs()){
					//Find the quiz object via the ID of the selected JTable row
					if(quiz.getQuizId() == Integer.parseInt(quizIDtoLookup)){
								
						for(QuizExercise qe : quiz.getQuizExercises()){
							
							String[] dataBuilder = new String[1];
							
							dataBuilder[0] = qe.getExercise().getQuestion();
							
							window.getEModel().addRow(dataBuilder);
						}	
					}
				}		
			}
			window.getJTableExercises().setModel(window.getEModel());
		}
		catch (Exception exc) {
			System.out.println(exc);
			window.showPopup(exc.getMessage());
		}
	}
	
	
	//methods to reset the DefaultTableModels and reload data
	public void resetTable(){
		try{
			window.getQModel().setRowCount(0);
			this.loadQuizTable();
		}
		catch (Exception exc) {
			System.out.println(exc);
			window.showPopup(exc.getMessage());
		}
	}
		
	public void resetExTable(){
		try{
			window.getEModel().setRowCount(0);
			this.loadExTable();
		}
		catch (Exception exc) {
			System.out.println(exc);
			window.showPopup(exc.getMessage());
		}
	}
	
	
	//EVENT LISTENERS
	
	class DeleteQuizListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) throws IllegalArgumentException{
		
			try {
				
				if(window.getJTableQuiz().getRowCount() != 0){
				
					String quizIDtoDelete = (String) window.getJTableQuiz().getValueAt(window.getJTableQuiz().getSelectedRow(), 0);	
				
					for(Quiz quiz : getQuizCatalog().getQuizCatalogs()){
					
						if(quiz.getQuizId() == Integer.parseInt(quizIDtoDelete)){	
							
							//check the status of the quiz that will be deleted. Only 'under constr' and 'completed' can be deleted
							if(quiz.getStatus().toString() == "Under Construction" || quiz.getStatus().toString() == "Completed"){				
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
								getQuizCatalog().deleteQuiz(quiz);
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
					resetTable();
					resetExTable();
				}
			}
			catch (Exception exc) {
				System.out.println(exc);
				window.showPopup(exc.getMessage());
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
				int rowIndex = window.getJTableQuiz().getSelectedRow();
				
				if(rowIndex == 0){	
					rowIndex = window.getJTableQuiz().getRowCount();
					System.out.println(rowIndex);
					window.getJTableQuiz().setRowSelectionInterval(rowIndex-1, rowIndex-1);	
				}			
				else{
					rowIndex--;
					window.getJTableQuiz().setRowSelectionInterval(rowIndex, rowIndex);		
				}
				resetExTable();
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
				int rowIndex = window.getJTableQuiz().getSelectedRow();
				
				if(rowIndex < window.getJTableQuiz().getRowCount()-1){
					rowIndex++;
					window.getJTableQuiz().setRowSelectionInterval(rowIndex, rowIndex);
				}	
				else{
					window.getJTableQuiz().setRowSelectionInterval(0, 0);
				}
				resetExTable();
			}

			catch (Exception exc) {
				System.out.println(exc);
			}
		}
	}

	// STATIC VOID MAIN
	
	public static void main(String[] args) {
	    
		try{
			QuizCatalog qCatalog = new QuizCatalog();
			ExerciseCatalog eCatalog = new ExerciseCatalog();
			DeleteQuizController controller = new DeleteQuizController(qCatalog, eCatalog);
			controller.window.setVisible(true);
		}
		catch(Exception exc){
			System.out.println(exc);
		}
	}
}




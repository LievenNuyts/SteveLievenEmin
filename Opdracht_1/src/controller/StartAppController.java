package controller;

import javax.swing.JFrame;

import model.ExerciseCatalog;
import model.QuizCatalog;
import view.ChangeQuizView;
import view.CreateQuizView;
import view.Menu;

/**
 * 
 * @author Steve
 * @version 11/11/2013
 *
 */

public class StartAppController extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private Menu startMenu;
	
	private CreateQuizController createQuizController;
	private ChangeQuizController changeQuizController;
	private DeleteQuizController deleteQuizController;
	
	CreateQuizView createView;
	ChangeQuizView changeView;
	
	private QuizCatalog quizCatalog;
	private ExerciseCatalog exerciseCatalog;
	
	public StartAppController() {
		
		startMenu = new Menu("Add quiz to Database" ,"Add quiz to TextFile", "Update quiz","Delete quiz");
	}
	
	public static void main(String[] args) throws Exception {
        new StartAppController().startApp();	
	}
	
	public void startApp(){
		
		quizCatalog = new QuizCatalog();
		exerciseCatalog = new ExerciseCatalog();
	
		int choice = startMenu.getMenuKeuze();
		
		switch (choice) {
		case 1:
			//Voeg quiz toe aan DB
			createView = new CreateQuizView();
			createQuizController = new CreateQuizController(createView, exerciseCatalog, quizCatalog);
			createView.setVisible(true);
			break;
			
		case 2:
			//Voeg quiz toe aan textfile
			createView = new CreateQuizView();
			createQuizController = new CreateQuizController(createView, exerciseCatalog, quizCatalog);
			createView.setVisible(true);
			break;
		
		case 3:
			//Update quiz
			changeView = new ChangeQuizView();
			changeQuizController = new ChangeQuizController(changeView, quizCatalog, exerciseCatalog);
			changeView.setVisible(true);
			break;
		
		case 4:
			//Delete quiz
			deleteQuizController = new DeleteQuizController(quizCatalog, exerciseCatalog);
			deleteQuizController.makeWindowVisible();
			break;

		default:
			break;
		}
	}
	

}

package controller;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import model.ExerciseCatalog;
import model.QuizCatalog;
import persistence.ManagementInitFile;
import persistence.TextToSql;
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
	private ManagementInitFile initFile;
	
	CreateQuizView createView;
	ChangeQuizView changeView;
	
	private QuizCatalog quizCatalog;
	private ExerciseCatalog exerciseCatalog;
	
	public StartAppController() {
		
		startMenu = new Menu("Voeg quiz to", "Update quiz", "Verwijder quiz", "Kies persistentie", "Kopieer tekstfile naar SQL");
	}
	
	public static void main(String[] args) throws Exception {

		new StartAppController().startPersistency();
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
			//Update quiz
			changeView = new ChangeQuizView();
			changeQuizController = new ChangeQuizController(changeView, quizCatalog, exerciseCatalog);
			changeView.setVisible(true);
			break;
		
		case 3:
			//Delete quiz
			deleteQuizController = new DeleteQuizController(quizCatalog, exerciseCatalog);
			deleteQuizController.makeWindowVisible();
			break;
			
		case 4:
			//Kies persistency
			startPersistency();
			startApp();
			break;
			
		case 5:
			//copy text to database

			TextToSql SQL = new TextToSql();
			try {

				SQL.SendToSql(quizCatalog, exerciseCatalog);
				startApp();
				
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Database error.");
				ex.printStackTrace();
				startApp();
			}
			break;

		default:
			System.exit(0);
			break;
		}
	}
	
	public void startPersistency() {
		initFile = new ManagementInitFile();
		initFile.choosePersistetyMethod();
	}
}

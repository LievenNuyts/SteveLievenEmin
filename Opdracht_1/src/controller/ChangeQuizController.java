package controller;

import model.ExerciseCatalog;
import model.QuizCatalog;
import view.ChangeQuizView;


/**
 * 
 * @author Steve
 * @version 4/11/2013
 *
 */

public class ChangeQuizController {

	private ChangeQuizView changeView;
	private QuizCatalog quizCatalog;
	private ExerciseCatalog exCatalog;
	
	public ChangeQuizController(ChangeQuizView changeView, QuizCatalog quizCatalog, ExerciseCatalog exCatalog) {

		this.changeView = changeView;
		this.quizCatalog = quizCatalog;
		this.exCatalog = exCatalog;

	}
	
	
}

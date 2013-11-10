package controller;

import model.Quiz;
import view.ChangeQuizView;


/**
 * 
 * @author Steve
 * @version 4/11/2013
 *
 */

public class ChangeQuizController {

	private ChangeQuizView changeView;
	private Quiz quiz;
	
	public ChangeQuizController(ChangeQuizView changeView, Quiz quiz) {

		this.changeView = changeView;
		this.quiz = quiz;
	}
	
	
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.ExerciseCatalog;
import model.QuizCatalog;
import view.ChangeQuizView;


/**
 * 
 * @author Steve
 * @version 4/11/2013
 *
 */

public class ChangeQuizController implements ActionListener {

	private ChangeQuizView changeView;
	private QuizCatalog quizCatalog;
	private ExerciseCatalog exCatalog;
	
	public ChangeQuizController() {
		/*
		this.changeView = changeView;
		this.quizCatalog = quizCatalog;
		this.exCatalog = exCatalog;
		 */
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
				System.out.println("Controller: acting on Model");
				quizCatalog.readQuizzesFromFile();
			} //actionPerformed()

	public void addQuizCatalog(QuizCatalog q){
		System.out.println("Controller: adding model");
		this.quizCatalog = q;
	} //addModel()

	public void addView(ChangeQuizView v){
		System.out.println("Controller: adding view");
		this.changeView = v;
	} //addView()

	public void initQuizCatalog(){
		quizCatalog.getQuizCatalogs();
	} //initModel()
	
}

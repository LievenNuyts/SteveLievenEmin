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

public class ChangeQuizController {

	private ChangeQuizView view;
	private QuizCatalog dataModel;

	public ChangeQuizController(ChangeQuizView view, QuizCatalog dataModel) {
		this.view = view;
		this.dataModel = dataModel;

		// Tell the View that when ever the calculate button
		// is clicked to execute the actionPerformed method
		// in the CalculateListener inner class

		this.view.addListener(new QuizListener());
	}

	class QuizListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// dataMembers

			String quizTitle;
			String status;
			Integer grade;
			String category;

			// Surround interactions with the view with
			// a try block in case numbers weren't
			// properly entered

			try {

				quizTitle = view.getQuizTitle();
				status = view.getStatus();
				grade = view.getGrade();
				category = view.getCategory();

				dataModel.writeQuizzesToFile();

				// view.(dataModel.getQuizCatalogs());

			}

			catch (NumberFormatException ex) {

				System.out.println(ex);

				view.displayErrorMessage("You Need to Enter 2 Integers");

			}

		}

	}
}

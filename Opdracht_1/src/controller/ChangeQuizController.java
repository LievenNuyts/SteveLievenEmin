package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		this.view.addUpdateListener(new QuizListener());
		this.view.addDeleteListener(new DeleteListener());
		this.view.addEditListener(new EditListener());
		this.view.addSearchListener(new SearchListener());
	}

	class QuizListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// dataMembers

			String quizTitle;
			String status;
			Integer grade;
			String category;

			try {

				System.out.println("Updatebutton");

				quizTitle = view.getQuizTitle();
				status = view.getStatus();
				grade = view.getGrade();
				category = view.getCategory();

				dataModel.writeQuizzesToFile();

				// view.(dataModel.getQuizCatalogs());

			}

			catch (Exception ex) {

				System.out.println(ex);

				view.displayErrorMessage("Error.");

			}

		}

	}

	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// dataMembers

			try {

				System.out.println("Deletebutton");

			}

			catch (Exception ex) {

				System.out.println(ex);

				view.displayErrorMessage("Error.");

			}

		}
	}

	class EditListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// dataMembers

			try {

				System.out.println("Editbutton");

			}

			catch (Exception ex) {

				System.out.println(ex);

				view.displayErrorMessage("Error.");

			}

		}
	}

	class SearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// dataMembers

			try {

				System.out.println("Searchbutton");

			}

			catch (Exception ex) {
cc
				System.out.println(ex);

				view.displayErrorMessage("Error.");

			}

		}
	}
}

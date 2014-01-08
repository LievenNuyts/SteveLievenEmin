package persistence;

import java.util.List;

import controller.ChangeQuizController;
import controller.DeleteQuizController;
import view.ChangeQuizView;
import view.CreateQuizView;
import view.DeleteQuizView;
import model.Exercise;
import model.ExerciseCatalog;
import model.Quiz;
import model.QuizCatalog;
import model.QuizExercise;
import model.QuizStatus;
import model.Teacher;

/**
 * 
 * @author Emin
 * 
 */
public class TextPersistence implements IPersistencable {

	/**
	 * Method to load exercises and quizzes
	 */
	@Override
	public void load(ExerciseCatalog exModel, QuizCatalog quModel) {
		// Load exercises and quizzes
		exModel.readExercisesFromFile();
		quModel.readQuizzesFromFile();
		exModel.createQuizExercises(exModel.getExercises(),
				quModel.getQuizCatalogs());
	}

	/**
	 * Method to add a quiz
	 */
	@Override
	public void addQuiz(CreateQuizView view, ExerciseCatalog exModel,
			QuizCatalog quModel) {
		try {
			if (view.getStatus().equals("Ready")) {
				if (view.getDataModel().getRowCount() == 0)
					throw new IllegalArgumentException(
							"Voegtoe ten minste één opdracht aan de quiz!");
			}

			for (Quiz quizCheck : quModel.getQuizCatalogs()) {
				if (quizCheck.getSubject().toLowerCase()
						.equals(view.getSubject().toLowerCase()))
					throw new IllegalArgumentException("Quiz bestaat al!");
			}
			if (view.getSubject() == null)
				throw new IllegalArgumentException("Onderwerp is null!");
			if (view.getSubject().isEmpty())
				throw new IllegalArgumentException("Onderwerp is leeg!");

			// Iterate through each row to check maxScore
			for (int i = 0; i < view.getDataModel().getRowCount(); i++) {
				if (!isNumeric(String.valueOf(view.getDataModel().getValueAt(i,
						1))))
					throw new NumberFormatException(
							"MaxScore moet een numeriek waarde zijn!");
				if (String.valueOf(view.getDataModel().getValueAt(i, 1))
						.isEmpty())
					throw new IllegalArgumentException("MaxScore is leeg!");
			}

			Quiz quiz = new Quiz(view.getSubject());
			quiz.setLeerJaren(Integer.parseInt(view.getGrade()));
			quiz.setTeacher(Teacher.valueOf(view.getAuthor().toUpperCase()));
			quiz.setStatus(QuizStatus.valueOf(view.getStatus().toUpperCase()
					.replaceAll("\\s+", "")));

			// Iterate through each row and add QuizExercises to corresponding
			// quizzes and exercises
			for (int i = 0; i < view.getDataModel().getRowCount(); i++) {
				for (Exercise ex : exModel.getExercises()) {
					if (ex.getQuestion()
							.equals(String.valueOf(
									view.getDataModel().getValueAt(i, 0))
									.substring(6))) {

						QuizExercise tempQE = new QuizExercise(
								Integer.parseInt(String.valueOf(view
										.getDataModel().getValueAt(i, 1))),
								quiz, ex);

						quiz.addQuizExercise(tempQE);
						ex.addQuizExercise(tempQE);
					}
				}
			}
			quModel.addQuiz(quiz);

			exModel.writeExercisesToFile();
			quModel.writeQuizzesToFile();

			view.displayErrorMessage("Quiz is toegevoegd");

			view.reset();
		} catch (NumberFormatException ex) {
			view.displayErrorMessage(ex.getMessage());
		} catch (IllegalArgumentException ex) {
			System.out.println(ex);
			view.displayErrorMessage(ex.getMessage());
		} catch (Exception ex) {
			view.displayErrorMessage(ex.getMessage());
		}
	}

	/**
	 * Method to delete a quiz
	 */
	@Override
	public void deleteQuiz(DeleteQuizView window,
			DeleteQuizController controller, ExerciseCatalog exerciseCatalog,
			QuizCatalog quizCatalog) {
		try {
			if (window.getJTableQuiz().getRowCount() != 0) {

				String quizIDtoDelete = (String) window.getJTableQuiz()
						.getValueAt(window.getJTableQuiz().getSelectedRow(), 0);

				for (Quiz quiz : controller.getQuizCatalog().getQuizCatalogs()) {

					if (quiz.getQuizId() == Integer.parseInt(quizIDtoDelete)) {

						// check the status of the quiz that will be deleted.
						// Only 'under constr' and 'completed' can be deleted
						if (quiz.getStatus().toString() == "Under Construction"
								|| quiz.getStatus().toString() == "Completed") {
							// loop through all QuizExercises in the quiz that
							// will be deleted
							for (QuizExercise qE : quiz.getQuizExercises()) {
								// loop through all exercises in ExerciseCatalog
								for (Exercise exercise : exerciseCatalog
										.getExercises()) {
									// lookup the exercise which is linked to
									// the QuizExercise
									// and look for the same exercise in the
									// ExerciseCatalog
									if (qE.getExercise().equals(exercise)) {
										// loop through QuizExercise list of the
										// exercise
										for (QuizExercise qE2 : exercise
												.getQuizExercises()) {
											// look for QuizExercise that
											// matches the above QuizExercise
											if (qE.equals(qE2)) {
												// matching QuizExercise can be
												// deleted
												exercise.removeQuizExercise(qE2);
												break;// to stop the loop if
														// found, no need to
														// check the others
											}
										}
										break;// to stop the loop if found, no
												// need to check the others
									}
								}
							}
							controller.getQuizCatalog().deleteQuiz(quiz);
							window.showPopup("Quiz \"" + quiz.getSubject()
									+ "\" verwijderd");
							break; // to stop the loop if found, no need to
									// check the others
						} else {
							window.showPopup("!! Deze quiz kan niet meer verwijderd worden !!\n"
									+ "Enkel een quiz met status 'Under Construction of 'Completed' kan verwijderd worden.");
						}
					}
				}

				// reload both JTables
				controller.resetTable();
				controller.resetExTable();
			}
		} catch (Exception exc) {
			System.out.println(exc);
			window.showPopup(exc.getMessage());
		}

	}

	/**
	 * Method to update a quiz
	 */
	@Override
	public void updateQuiz(ChangeQuizView view, ExerciseCatalog exerciseModel,
			QuizCatalog quizModel, List<Quiz> tempQuizzes,
			List<Exercise> tempExercises) {
		try {
			System.out.println("Updatebutton");

			quizModel.setQuizCatalogs(tempQuizzes);
			exerciseModel.setExercises(tempExercises);

			quizModel.writeQuizzesToFile();
			exerciseModel.writeExercisesToFile();

			view.displayErrorMessage("Quiz is geüpdatet");
		} catch (IllegalArgumentException ex) {
			System.out.println(ex);
			view.displayErrorMessage(ex.getMessage());
		}
	}

	/**
	 * Method to delete an exercise(s) from a quiz
	 */
	@Override
	public void deleteFromQuiz(ChangeQuizView view, List<Quiz> tempQuizzes,
			List<Exercise> tempExercises) {
		try {
			int tempQuId = view.getSelectedQuizValueFromList().getQuizId();
			int tempExId = view.getSelectedQuizExerciseValueFromList()
					.getExercise().getExerciseId();

			for (Quiz q : tempQuizzes) {
				if (q.getQuizId() == tempQuId)
					q.getQuizExercises().remove(
							view.getSelectedQuizExerciseValueFromList());
			}

			for (Exercise ex : tempExercises) {
				if (ex.getExerciseId() == tempExId)
					ex.getQuizExercises().remove(
							view.getSelectedQuizExerciseValueFromList());
			}
		} catch (IllegalArgumentException ex) {
			System.out.println(ex);
			view.displayErrorMessage(ex.getMessage());
		}
	}

	/**
	 * Method to save and close ChangeQuizView window
	 */
	@Override
	public void saveAndClose(DeleteQuizView window,
			DeleteQuizController controller, ExerciseCatalog exerciseCatalog,
			QuizCatalog quizCatalog) {
		quizCatalog.writeQuizzesToFile();
		exerciseCatalog.writeExercisesToFile();
		window.dispose();
	}

	/**
	 * Method to add an exercise to a quiz
	 */
	@Override
	public void addToQuiz(ChangeQuizView view, List<Quiz> tempQuizzes,
			List<Exercise> tempExercises, ChangeQuizController controller) {
		try {
			// add to quiz
			int maxScore = 5; // Integer.parseInt(view.getMaxScore());

			QuizExercise qe = new QuizExercise(maxScore,
					view.getSelectedQuizValueFromList(),
					view.getSelectedExerciseValueFromList());

			for (QuizExercise qE : view.getSelectedQuizValueFromList()
					.getQuizExercises()) {
				if (qE.getQuiz().getQuizId() == view
						.getSelectedQuizValueFromList().getQuizId()
						&& qE.getExercise().getExerciseId() == view
								.getSelectedExerciseValueFromList()
								.getExerciseId())
					throw new IllegalArgumentException("Opdracht bestaat al.");
			}

			view.getSelectedQuizValueFromList().addQuizExercise(qe);

			for (Exercise ex : tempExercises) {
				if (ex.getExerciseId() == view
						.getSelectedExerciseValueFromList().getExerciseId()) {
					ex.addQuizExercise(qe);
				}
			}

			// test
			for (int index = 0; index < tempQuizzes.size(); index++) {
				System.out.println(tempQuizzes.get(index));
			}

			for (Quiz q : tempQuizzes) {
				if (q.getSubject().equalsIgnoreCase(
						view.getSelectedQuizValueFromList().getSubject())) {

					int indexPos = controller.getIndexPosition(q);
					System.out.println(indexPos);
					tempQuizzes.set(indexPos,
							view.getSelectedQuizValueFromList());
				}
			}
		} catch (IllegalArgumentException ex) {
			System.out.println(ex);
			view.displayErrorMessage(ex.getMessage());
		}
	}

	/**
	 * Check numeric input
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str) {
		try {
			@SuppressWarnings("unused")
			int i = Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
}

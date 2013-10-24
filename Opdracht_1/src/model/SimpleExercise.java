/**
 * 
 */
package model;

import java.util.List;

import utils.DateGC;

/**
 * 
 * @author Emin
 * 17/10/2013
 *
 */
public class SimpleExercise extends Exercise {

	/**
	 * Default Constructor
	 * 
	 * @throws IllegalArgumentException
	 */
	public SimpleExercise() throws IllegalArgumentException {
	}

	/**
	 * Constructor with 9 params
	 * 
	 * @param question
	 * @param correctAnswer
	 * @param answerHints
	 * @param maxNumberOfAttempts
	 * @param maxAnswerTime
	 * @param category
	 * @param author
	 * @param quizzes
	 * @param dateRegistration
	 * @throws IllegalArgumentException
	 */
	public SimpleExercise(int excerciseId, String question, String correctAnswer,
			String[] answerHints, int maxNumberOfAttempts, int maxAnswerTime,
			ExerciseCategory category, Teacher author, List<QuizExercise> quizExercises,
			DateGC dateRegistration, char discriminator) throws IllegalArgumentException {
		super(excerciseId, question, correctAnswer, answerHints, maxNumberOfAttempts,
				maxAnswerTime, category, author, quizExercises, dateRegistration, discriminator);
	}
}

package strategyPattern;

import java.util.List;

import model.Quiz;
import model.QuizExercise;

/**
 * 
 * @author java
 *
 */
public class BasicScore implements IScoreable {

	/**
	 * Method to calculate score
	 */
	@Override
	public double calculateScore(Quiz quiz, List<String> antwoorden) {
		// Check length of answers
		if (antwoorden.size() != quiz.getQuizExercises().size()) 
			throw new IllegalArgumentException("Aantal antwoorden klopt niet!");
		
		double totalScore = 0.0;
		
		// Check answers and add scores of exercises to total score
		for(QuizExercise qE : quiz.getQuizExercises()){
			for (int i = 0; i < antwoorden.size(); i++) {
				if (qE.getExercise().isCorrectAnswer(antwoorden.get(i))){
					totalScore += qE.getMaxScore();
				}
			}
		}
		
		return totalScore;
	}
}

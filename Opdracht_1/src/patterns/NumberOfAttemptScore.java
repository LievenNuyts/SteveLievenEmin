/**
 * 
 */
package patterns;

import java.util.List;

import model.Quiz;
import model.QuizExercise;

/**
 * @author java
 *
 */
public class NumberOfAttemptScore implements IScoreable {
	
	@Override
	public double calculateScore(Quiz quiz, List<String> antwoorden) {
		if (antwoorden.size() != quiz.getQuizExercises().size()) throw new IllegalArgumentException("ERROR");
		
		double totalScore = 0.0;
		
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

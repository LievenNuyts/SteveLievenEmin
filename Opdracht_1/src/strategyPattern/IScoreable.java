package strategyPattern;

import java.util.List;

import model.Quiz;

/**
 * 
 * @author java
 *
 */
public interface IScoreable {
	
	public double calculateScore(Quiz quiz, List<String> antwoorden);
	
}

package decoratorPattern;

import java.util.List;

import model.Quiz;
import model.QuizExercise;

/**
 * 
 * @author java
 *
 */
public class QuizScoreDecorator extends QuizDecorator{

	// Constructors
	
	/**
	 * Constructor with 1 parameter
	 * 
	 * @param quiz
	 */
	public QuizScoreDecorator(Quiz quiz){
		super(quiz);
	}

	// Selectors

	/**
	 * Method to show info about quiz score
	 * 
	 * @return
	 */
	public String showReport(List<Double> scores) {
		String info = "";
		double total = 0.0;
		double maxTotal = 0.0;
		double procentScore = 0;
		
		for(QuizExercise qE : this.getQuizExercises()){
			maxTotal += qE.getMaxScore();
		}
		
		for(double d : scores){
			total += d;
		}
		
		procentScore =  (100 / maxTotal) * total;
		
		info += "Gemiddelde score quiz: " + total / scores.size();
		
		if(procentScore < 50){
			info += "GEBUISD";
		}
		else if(procentScore < 70){
			info += "Voldoening";
		}
		else{
			info += "Zeer goed!!!";
		}
		
		return info;
	}
}

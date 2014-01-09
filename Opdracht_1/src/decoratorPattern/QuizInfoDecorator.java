package decoratorPattern;

import model.Quiz;

/**
 * 
 * @author java
 *
 */
public class QuizInfoDecorator extends QuizDecorator{
	
	// Constructors
	
	/**
	 * Constructor with 1 parameter
	 * 
	 * @param quiz
	 */
	public QuizInfoDecorator(Quiz quiz) {
		super(quiz);
	}
	
	// Selectors

	/**
	 * Method to show info about quiz
	 * 
	 * @return
	 */
	public String showReport() {
		String info = "";
		
		info += "Onderwerp: " + this.getSubject()
				+ "Datum: " + this.getDate();
		
		return info;
	}	
}

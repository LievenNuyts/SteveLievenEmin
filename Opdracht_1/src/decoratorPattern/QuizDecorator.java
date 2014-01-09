package decoratorPattern;

import model.Quiz;

/**
 * 
 * @author java
 *
 */
public abstract class QuizDecorator extends Quiz{
	
	private Quiz quizToDecorate;
	
	// Constructors
	
	/**
	 * Constructor with 1 parameter
	 * 
	 * @param quiz
	 */
	public QuizDecorator(Quiz quiz){
		this.setQuizToDecorate(quiz);
	}

	// Selectors
	
	/**
	 * @return
	 */
	public Quiz getQuizToDecorate() {
		return quizToDecorate;
	}

	// Modifiers
	
	/**
	 * Set quizToDecorate
	 * 
	 * @param quizToDecorate
	 */
	public void setQuizToDecorate(Quiz quizToDecorate) {
		this.quizToDecorate = quizToDecorate;
	}	
}

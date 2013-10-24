/**
 * 
 */
package model;

/**
 * 
 * @author Emin
 * @version 16/10/2013
 *
 */
public class QuizExercise implements Comparable<QuizExercise>, Cloneable{
	
	private int maxScore;
	private Quiz quiz;
	private Exercise exercise;
	
	/**
	 * Default Constructor
	 * 
	 * @throws IllegalArgumentException
	 */
	public QuizExercise() throws IllegalArgumentException{
		this.setMaxScore(1);
		this.setQuiz(new Quiz());
		this.setExercise(new SimpleExercise());
	}
	
	/**
	 * Constructor with 3 params
	 * 
	 * @param maxScore
	 * @param quiz
	 * @param exercise
	 * @throws IllegalArgumentException
	 */
	public QuizExercise(int maxScore,Quiz quiz,Exercise exercise) throws IllegalArgumentException{
		this.setMaxScore(maxScore);
		this.setQuiz(quiz);
		this.setExercise(exercise);
	}
	
	// Selectors

	/**
	 * @return
	 */
	public int getMaxScore() {
		return maxScore;
	}

	/**
	 * @return
	 */
	public Quiz getQuiz() {
		return quiz;
	}
	
	/**
	 * @return
	 */
	public Exercise getExercise() {
		return exercise;
	}
	
	// Modifier
	
	/**
	 * Set maxScore
	 * 
	 * @param maxScore
	 */
	public void setMaxScore(int maxScore) throws IllegalArgumentException{
		if (maxScore < 1)throw new IllegalArgumentException("maxScore is verkeerd ingevuld");
		this.maxScore = maxScore;
	}

	/**
	 * Set quiz
	 * 
	 * @param quiz
	 */
	public void setQuiz(Quiz quiz) throws IllegalArgumentException{
		if (quiz == null)throw new IllegalArgumentException("Quiz is null");
		this.quiz = quiz;
	}

	/**
	 * Set exercise
	 * 
	 * @param exercise
	 */
	public void setExercise(Exercise exercise) {
		if (exercise == null)throw new IllegalArgumentException("Exercise is null");
		this.exercise = exercise;
	}
	
	// Comparisons
	
	public int compareTo(QuizExercise quizExercise) {
		return this.getExercise().compareTo(quizExercise.getExercise())
				+ this.getQuiz().compareTo(quizExercise.getQuiz());
	}
	
	// Cloneable
	
	/**
	 * Method to clone this object
	 * 
	 * @return
	 */
	@Override
	public QuizExercise clone() throws CloneNotSupportedException{
		Exercise ex = new SimpleExercise();
		ex = exercise;
		
		Quiz q = new Quiz();
		q = quiz;
		
		QuizExercise qz = new QuizExercise(maxScore, q, ex);
		
		return qz;
	}
	
	// Overrides

	@Override
	public String toString() {
		return "QuizExercise [maxScore=" + maxScore + ", quiz=" + quiz
				+ ", exercise=" + exercise + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((exercise == null) ? 0 : exercise.hashCode());
		result = prime * result + maxScore;
		result = prime * result + ((quiz == null) ? 0 : quiz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizExercise other = (QuizExercise) obj;
		if (exercise == null) {
			if (other.exercise != null)
				return false;
		} else if (!exercise.equals(other.exercise))
			return false;
		if (maxScore != other.maxScore)
			return false;
		if (quiz == null) {
			if (other.quiz != null)
				return false;
		} else if (!quiz.equals(other.quiz))
			return false;
		return true;
	}
}

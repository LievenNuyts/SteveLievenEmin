/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.DateGC;

/**
 * 
 * @author Emin
 * @author Lieven
 * @version 16/10/2013
 *
 */
public class Exercise implements Comparable<Exercise>{
	
	public enum ExerciseCategory {
		REKENEN,
		NEDERLANDSENAAM,
		FRANSETAAL,
		ALGEMENEKENNIS
	}
	
	private String question;
	private String correctAnswer;
	private String[] answerHints;
	private int maxNumberOfAttempts;
	private int maxAnswerTime;
	private ExerciseCategory category;
	private Teacher author;
	private List<Quiz> quizzes;
	private DateGC dateRegistration;
	
	// Constructors
	
	/**
	 * Default constructor
	 */
	public Exercise() throws IllegalArgumentException{
		this.setQuestion("leeg");
		this.setCorrectAnswer("leeg");
		this.setAnswerHints(new String[]{});
		this.setMaxNumberOfAttempts(1);
		this.setMaxAnswerTime(0);
		this.setCategory(Exercise.ExerciseCategory.ALGEMENEKENNIS);
		this.setAuthor(Teacher.BAKKER);
		this.setQuizzes(new ArrayList<Quiz>());
		this.setDateRegistration(new DateGC());
	}
	
	/**
	 * Constructor with 9 params
	 * 
	 * @param question
	 * @param correctAnswer
	 * @param answerHints
	 * @param maxNumberOfAttempts
	 * @param maxAnswerTime
	 */
	public Exercise(String question,String correctAnswer,String[] answerHints,
			int maxNumberOfAttempts,int maxAnswerTime,ExerciseCategory category,
			Teacher author,List<Quiz> quizzes,DateGC dateRegistration) throws IllegalArgumentException{
		this.setQuestion(question);
		this.setCorrectAnswer(correctAnswer);
		this.setAnswerHints(answerHints);
		this.setMaxNumberOfAttempts(maxNumberOfAttempts);
		this.setMaxAnswerTime(maxAnswerTime);
		this.setCategory(category);
		this.setAuthor(author);
		this.setQuizzes(quizzes);
		this.setDateRegistration(dateRegistration);
	}

	// Selectors
	
	/**
	 * @return question
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * @return correctAnswer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * @return answerHints
	 */
	public String[] getAnswerHints() {
		return answerHints;
	}

	/**
	 * @return maxNumberOfAttempts
	 */
	public int getMaxNumberOfAttempts() {
		return maxNumberOfAttempts;
	}

	/**
	 * @return maxAnswerTime
	 */
	public int getMaxAnswerTime() {
		return maxAnswerTime;
	}

	/**
	 * @return
	 */
	public ExerciseCategory getCategory() {
		return category;
	}

	/**
	 * @return
	 */
	public Teacher getAuthor() {
		return author;
	}

	/**
	 * @return
	 */
	public List<Quiz> getQuizzes() {
		return quizzes;
	}

	/**
	 * @return
	 */
	public DateGC getDateRegistration() {
		return dateRegistration;
	}

	// Modifiers
	
	/**
	 * Set question
	 * 
	 * @param question
	 */
	public void setQuestion(String question) throws IllegalArgumentException{
		if (question == null)throw new IllegalArgumentException("Vraag is null!");
		if (question.isEmpty())throw new IllegalArgumentException("Gelieve een vraag in te vullen!");
		this.question = question;
	}
	
	/**
	 * Set correctAnswer
	 * 
	 * @param correctAnswer
	 */
	public void setCorrectAnswer(String correctAnswer) throws IllegalArgumentException{
		if (correctAnswer == null)throw new IllegalArgumentException("Juiste antwoord is null!");
		if (correctAnswer.isEmpty())throw new IllegalArgumentException("Gelieve een juiste antwoord in te vullen!");
		this.correctAnswer = correctAnswer;
	}

	/**
	 * Set answerHits
	 * 
	 * @param answerHints
	 */
	public void setAnswerHints(String[] answerHints) throws IllegalArgumentException{
		if (answerHints == null)throw new IllegalArgumentException("Antwoord hints verzameling is null");
		this.answerHints = answerHints;
	}

	/**
	 * Set maxNumberOfAttempts
	 * 
	 * @param maxNumberOfAttempts
	 */
	public void setMaxNumberOfAttempts(int maxNumberOfAttempts) throws IllegalArgumentException{
		if (maxNumberOfAttempts <= 0)throw new IllegalArgumentException("Aantal pogingen kunnen niet 0 of negatief zijn!");
		this.maxNumberOfAttempts = maxNumberOfAttempts;
	}

	/**
	 * Set maxAnswerTime
	 * 
	 * @param maxAnswerTime
	 */
	public void setMaxAnswerTime(int maxAnswerTime) throws IllegalArgumentException{
		if (maxAnswerTime < 0)throw new IllegalArgumentException("Max antwoord tijd kan niet 0 of negatief zijn!");
		this.maxAnswerTime = maxAnswerTime;
	}

	/**
	 * Set category
	 * 
	 * @param category
	 */
	public void setCategory(ExerciseCategory category) throws IllegalArgumentException{
		if (category == null)throw new IllegalArgumentException("Categorie is null!");
		this.category= category;
	}

	/**
	 * Set author
	 * 
	 * @param auteur
	 */
	public void setAuthor(Teacher author) throws IllegalArgumentException{
		if (author == null)throw new IllegalArgumentException("Auteur is null!");
		this.author = author;
	}

	/**
	 * Set quizzes
	 * 
	 * @param quizzes
	 */
	public void setQuizzes(List<Quiz> quizzes) throws IllegalArgumentException{
		if (quizzes == null)throw new IllegalArgumentException("Quizzen verzameling is null!");
		this.quizzes = quizzes;
	}

	/**
	 * Set dateRegistration
	 * 
	 * @param dateRegistration
	 */
	public void setDateRegistration(DateGC dateRegistration) throws IllegalArgumentException{
		if (dateRegistration == null)throw new IllegalArgumentException("Datum is null");
		this.dateRegistration = dateRegistration;
	}
	
	// Comparisons
	
	/**
	 * Compares input answer with correct answer
	 * 
	 * @param answer
	 * @return
	 */
	public boolean isCorrectAnswer(String answer){
		if (this.correctAnswer == answer)
			return true;
		return false;
	}
	
	/**
	 * Comparable
	 * 
	 * @param opdracht
	 * @return
	 */
	public int compareTo(Exercise exercise){
		return this.getQuestion().compareTo(exercise.getQuestion());
	}
	
	//Overrides
	
	@Override
	public String toString() {
		return "Opdracht [question=" + question + ", correctAnswer="
				+ correctAnswer + ", answerHints="
				+ Arrays.toString(answerHints) + ", maxNumberOfAttempts="
				+ maxNumberOfAttempts + ", maxAnswerTime=" + maxAnswerTime
				+ ", category=" + category + ", author=" + author
				+ ", quizzen=" + quizzes + ", dateRegistration="
				+ dateRegistration + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(answerHints);
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((correctAnswer == null) ? 0 : correctAnswer.hashCode());
		result = prime * result + maxAnswerTime;
		result = prime * result + maxNumberOfAttempts;
		result = prime * result
				+ ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((quizzes == null) ? 0 : quizzes.hashCode());
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
		Exercise other = (Exercise) obj;
		if (!Arrays.equals(answerHints, other.answerHints))
			return false;
		if (author != other.author)
			return false;
		if (category != other.category)
			return false;
		if (correctAnswer == null) {
			if (other.correctAnswer != null)
				return false;
		} else if (!correctAnswer.equals(other.correctAnswer))
			return false;
		if (maxAnswerTime != other.maxAnswerTime)
			return false;
		if (maxNumberOfAttempts != other.maxNumberOfAttempts)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (quizzes == null) {
			if (other.quizzes != null)
				return false;
		} else if (!quizzes.equals(other.quizzes))
			return false;
		return true;
	}
}
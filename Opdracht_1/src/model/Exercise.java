/**
 * 
 */
package model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import utils.DateGC;

/**
 * 
 * @author Emin
 * @author Lieven
 * @version 16/10/2013
 *
 */
public abstract class Exercise implements Comparable<Exercise>, Cloneable{
	
	public enum ExerciseCategory {
		AARDRIJKSKUNDE ("Aardrijkskunde"),
		NEDERLANDS ("Nederlands"),
		WETENSCHAPPEN ("Wetenschappen"),
		WISKUNDE ("Wiskunde"); 
		
		private final String name;

		ExerciseCategory(final String name) {
		      this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}
	
	private int exerciseId;
	private String question;
	protected String correctAnswer;
	private String[] answerHints;
	private int maxNumberOfAttempts;
	private int maxAnswerTime;
	private ExerciseCategory category;
	private Teacher author;
	private List<QuizExercise> quizExercises;
	private DateGC dateRegistration;
	private char discriminator;
	
	// Constructors
	
	/**
	 * Default constructor
	 */
	public Exercise() throws IllegalArgumentException{
		this.setExerciseId(1);
		this.setQuestion("leeg");
		this.setCorrectAnswer("leeg");
		this.setAnswerHints(new String[]{});
		this.setMaxNumberOfAttempts(1);
		this.setMaxAnswerTime(0);
		this.setCategory(Exercise.ExerciseCategory.AARDRIJKSKUNDE);
		this.setAuthor(Teacher.BAKKER);
		this.setQuizExercises(new ArrayList<QuizExercise>());
		this.setDateRegistration(new DateGC());
		this.setDiscriminator('S');
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
			Teacher author,DateGC dateRegistration,char discriminator) throws IllegalArgumentException{
		this.setQuestion(question);
		this.setCorrectAnswer(correctAnswer);
		this.setAnswerHints(answerHints);
		this.setMaxNumberOfAttempts(maxNumberOfAttempts);
		this.setMaxAnswerTime(maxAnswerTime);
		this.setCategory(category);
		this.setAuthor(author);
		this.setDateRegistration(dateRegistration);
		this.setDiscriminator(discriminator);
		
		this.setExerciseId(1);
		this.setQuizExercises(new ArrayList<QuizExercise>());
	}
	
	/**
	 * Constructor with 11 params
	 * 
	 * @param question
	 * @param correctAnswer
	 * @param answerHints
	 * @param maxNumberOfAttempts
	 * @param maxAnswerTime
	 */
	public Exercise(int exerciseId, String question,String correctAnswer,String[] answerHints,
			int maxNumberOfAttempts,int maxAnswerTime,ExerciseCategory category,
			Teacher author,List<QuizExercise> quizExercises,DateGC dateRegistration,char discriminator) throws IllegalArgumentException{
		this.setExerciseId(exerciseId);
		this.setQuestion(question);
		this.setCorrectAnswer(correctAnswer);
		this.setAnswerHints(answerHints);
		this.setMaxNumberOfAttempts(maxNumberOfAttempts);
		this.setMaxAnswerTime(maxAnswerTime);
		this.setCategory(category);
		this.setAuthor(author);
		this.setQuizExercises(quizExercises);
		this.setDateRegistration(dateRegistration);
		this.setDiscriminator(discriminator);
	}

	// Selectors

	/**
	 * @return
	 */
	public int getExerciseId() {
		return exerciseId;
	}
	
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
	public List<QuizExercise> getQuizExercises() {
		return quizExercises;
	}

	/**
	 * @return
	 */
	public DateGC getDateRegistration() {
		return dateRegistration;
	}

	/**
	 * @return
	 */
	public char getDiscriminator() {
		return discriminator;
	}

	// Modifiers

	/**
	 * Set exerciseId
	 * 
	 * @param exerciseId
	 */
	public void setExerciseId(int exerciseId) throws IllegalArgumentException{
		if (exerciseId < 1) throw new IllegalArgumentException("Chekck exerciseId!");
		this.exerciseId = exerciseId;
	}
	
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
		if (correctAnswer.isEmpty())throw new IllegalArgumentException("Gelieve een antwoord in te vullen!");
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
	public void setQuizExercises(List<QuizExercise> quizExercises) throws IllegalArgumentException{
		if (quizExercises == null)throw new IllegalArgumentException("Quizzen verzameling is null!");
		this.quizExercises = quizExercises;
	}

	/**
	 * Set dateRegistration
	 * 
	 * @param dateRegistration
	 */
	public void setDateRegistration(DateGC dateRegistration) throws IllegalArgumentException{
		if (dateRegistration == null)throw new IllegalArgumentException("Datum is null!");
		this.dateRegistration = dateRegistration;
	}

	/**
	 * Set discriminator
	 * 
	 * @param discriminator
	 */
	public void setDiscriminator(char discriminator) throws IllegalArgumentException{
		if (discriminator != 'S' && discriminator != 'E' 
				&& discriminator != 'M')throw new IllegalArgumentException("Discriminator is verkeerd ingevuld!");
		this.discriminator = discriminator;
	}
	
	/**
	 * Add quizExercise to list
	 * 
	 * @param quizExercise
	 * @throws IllegalArgumentException
	 */
	public void addExercise(QuizExercise quizExercise) throws IllegalArgumentException{
		if (quizExercise == null)throw new IllegalArgumentException("quizExercise is null!");
		for (QuizExercise qE: quizExercises) {
			if (qE.equals(quizExercise))throw new IllegalArgumentException("quizExercise bestaat al!");
		}
		quizExercises.add(quizExercise);
	}
	
	/**
	 * Remove quizExercise from list
	 * 
	 * @param quizExercise
	 * @throws IllegalArgumentException
	 */
	public void removeExercise(QuizExercise quizExercise) throws IllegalArgumentException{
		if (quizExercise == null)throw new IllegalArgumentException("Opdracht is null!");
		quizExercises.remove(quizExercise);
	}
	
	// Comparisons
	
	/**
	 * Compares input answer with correct answer
	 * 
	 * @param answer
	 * @return
	 */
	public boolean isCorrectAnswer(String answer){
		if (this.getCorrectAnswer() == answer)
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
	
	// Cloneable
	
	/**
	 * Method to clone this object
	 * 
	 * @return
	 */
	@Override
	public Exercise clone() throws CloneNotSupportedException{
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(getDateRegistration().getGregCal().get(Calendar.YEAR), 
				getDateRegistration().getGregCal().get(Calendar.MONTH), 
				getDateRegistration().getGregCal().get(Calendar.DATE));
		
		DateGC date = new DateGC();
		date.setGregCal(gc);
		
		Exercise exercise = new SimpleExercise(getExerciseId(), getQuestion(), getCorrectAnswer(), 
				getAnswerHints(), getMaxNumberOfAttempts(), getMaxAnswerTime(), getCategory(), 
				getAuthor(), getQuizExercises(), date, getDiscriminator());
		
		return exercise;
	}
		
	//Overrides
	
	@Override
	public String toString() {
		return "Exercise [getExerciseId()=" + getExerciseId()
				+ ", getQuestion()=" + getQuestion() + ", getCorrectAnswer()="
				+ getCorrectAnswer() + ", getAnswerHints()="
				+ Arrays.toString(getAnswerHints())
				+ ", getMaxNumberOfAttempts()=" + getMaxNumberOfAttempts()
				+ ", getMaxAnswerTime()=" + getMaxAnswerTime()
				+ ", getCategory()=" + getCategory() + ", getAuthor()="
				+ getAuthor() + ", getQuizExercises()=" + getQuizExercises()
				+ ", getDateRegistration()=" + getDateRegistration()
				+ ", getDiscriminator()=" + getDiscriminator()
				+ ", hashCode()=" + hashCode() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(getAnswerHints());
		result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
		result = prime * result
				+ ((getCategory() == null) ? 0 : getCategory().hashCode());
		result = prime * result
				+ ((getCorrectAnswer() == null) ? 0 : getCorrectAnswer().hashCode());
		result = prime
				* result
				+ ((getDateRegistration() == null) ? 0 : getDateRegistration().hashCode());
		result = prime * result + getDiscriminator();
		result = prime * result + getExerciseId();
		result = prime * result + getMaxAnswerTime();
		result = prime * result + getMaxNumberOfAttempts();
		result = prime * result
				+ ((getQuestion() == null) ? 0 : getQuestion().hashCode());
		result = prime * result
				+ ((getQuizExercises() == null) ? 0 : getQuizExercises().hashCode());
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
		if (!Arrays.equals(getAnswerHints(), other.getAnswerHints()))
			return false;
		if (getAuthor() != other.getAuthor())
			return false;
		if (getCategory() != other.getCategory())
			return false;
		if (getCorrectAnswer() == null) {
			if (other.getCorrectAnswer() != null)
				return false;
		} else if (!getCorrectAnswer().equals(other.getCorrectAnswer()))
			return false;
		if (getDateRegistration() == null) {
			if (other.getDateRegistration() != null)
				return false;
		} else if (!getDateRegistration().equals(other.getDateRegistration()))
			return false;
		if (getDiscriminator() != other.getDiscriminator())
			return false;
		if (getExerciseId() != other.getExerciseId())
			return false;
		if (getMaxAnswerTime() != other.getMaxAnswerTime())
			return false;
		if (getMaxNumberOfAttempts() != other.getMaxNumberOfAttempts())
			return false;
		if (getQuestion() == null) {
			if (other.getQuestion() != null)
				return false;
		} else if (!getQuestion().equals(other.getQuestion()))
			return false;
		if (getQuizExercises() == null) {
			if (other.getQuizExercises() != null)
				return false;
		} else if (!getQuizExercises().equals(other.getQuizExercises()))
			return false;
		return true;
	}
}
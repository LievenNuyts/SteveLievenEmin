package model;

import java.util.ArrayList;

import model.Teacher;
import utils.DateGC;
import utils.DateQuiz;
/**
 * 
 * @author Steve
 * @version 13/10/2013
 *
 */

enum QuizStatus {

	underConstruction(1), 
	completed(2), 
	ready(3),
	lastChance(4), 
	closed(5);

	private final int StatusID;

	QuizStatus(int StatusID) { 
		this.StatusID = StatusID; 
	}
	public int getValue() { 
		return StatusID; 
	}

}

public class Quiz implements Comparable<Quiz>{
	
	private String subject = "";
	private int leerJaren = 0;
	private QuizStatus status;
	private Teacher teacher;
	private DateQuiz date;
	private boolean isUniqueParticipation;
	private boolean isTest;
	private ArrayList<QuizExercise> quizExercises = new ArrayList<QuizExercise>();
	
	
	//default constructor
	
	public Quiz()throws IllegalArgumentException
	{
		this.subject = "subject";	
	}
	
	public Quiz(String subject)throws IllegalArgumentException
	{
		this.subject =  subject;
	}
	
	//constructor with 4 parameters
	
	public Quiz(String subject, int leerJaren, boolean uniqueParticipation, boolean isTest)throws IllegalArgumentException
	{
		this.subject = subject;
		this.leerJaren = leerJaren;
		this.isUniqueParticipation = uniqueParticipation;
		this.isTest = isTest;
	}
	
	//@overrides
	
	@Override
	public int compareTo(Quiz o) {
		return this.getSubject().compareTo(o.getSubject());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isTest ? 1231 : 1237);
		result = prime * result + (isUniqueParticipation ? 1231 : 1237);
		result = prime * result + leerJaren;
		result = prime * result
				+ ((subject == null) ? 0 : subject.hashCode());
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
		Quiz other = (Quiz) obj;
		if (isTest != other.isTest)
			return false;
		if (isUniqueParticipation != other.isUniqueParticipation)
			return false;
		if (leerJaren != other.leerJaren)
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

	@Override
    public String toString() {
            String quizText = "";
            if (isTest) {
            	quizText = "\nThis is a test.";
            } 
            else {
            	quizText = "\nThis is not a test.";
            }
            
            if (isUniqueParticipation) {
            	quizText += "\nUnique participation.";
            } 
            else {
                quizText += "\nNo unique participation.";
            }
            
            quizText += "\nStatus: ";
            
            String quizSummary = "";
            quizSummary += "\nQuiz : " + subject
            				+ "\nYears: " + leerJaren
                            + "\nDate: " + date.getDateInEuropeanFormat()
                            + "\nCreated by: " + teacher 
                            + "\n" + quizText 
                            + "\nStatus: " + status;
            
            return quizSummary;
    }
	
	//get & set

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getLeerJaren() {
		return leerJaren;
	}

	public void setLeerJaren(int leerJaren) {
		this.leerJaren = leerJaren;
	}

	public QuizStatus getStatus() {
		return status;
	}

	public void setStatus(QuizStatus status) {
		this.status = status;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public DateQuiz getDate() {
		return date;
	}

	public void setDate(DateQuiz date) {
		this.date = date;
	}

	public boolean isUniqueParticipation() {
		return isUniqueParticipation;
	}

	public void setUniqueParticipation(boolean isUniqueParticipation) {
		this.isUniqueParticipation = isUniqueParticipation;
	}

	public boolean isTest() {
		return isTest;
	}

	public void setTest(boolean isTest) {
		this.isTest = isTest;
	}

	public ArrayList<QuizExercise> getQuizExercises() {
		return quizExercises;
	}

	public void setQuizExercises(ArrayList<QuizExercise> quizExercises) {
		this.quizExercises = quizExercises;
	}
	
	//methods

	public void addQuizExercise(QuizExercise quizExercise) throws Exception {

		try
		{
			for(int i = 0; i < this.quizExercises.size(); i++) 
			{
				if (quizExercises.get(i).equals(quizExercise)) 
				{
					throw new Exception("Exercise already exists.");
				}
			}
			quizExercises.add(quizExercise);
		}
		catch (Exception ex){ throw new Exception(ex.getMessage());
		}
	}


	public void deleteQuizExercise(QuizExercise quizExercise) throws Exception {
		
		try
		{	
			for(int i = 0; i < this.quizExercises.size(); i++)
			{
				if (quizExercises.get(i).equals(quizExercise)) 
				{
					throw new Exception("No such exercise.");
				}
			}
			quizExercises.remove(quizExercise);
		}
		catch (Exception ex){ throw new Exception(ex.getMessage());
		}
	}
}

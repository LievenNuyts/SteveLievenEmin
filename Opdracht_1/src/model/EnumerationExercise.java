package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.Exercise.ExerciseCategory;
import utils.DateGC;

public class EnumerationExercise extends Exercise implements Validatable{
	
	/**
	 *  
	 * @author Lieven
	 * @version 19/10/2013
	 *
	 */
	
	private List<String> splitCorrectAnswer;
	private List<String> splitStudentAnswer;
	private int numberOfElements;
	private boolean inCorrectOrder;
	
	public EnumerationExercise()throws IllegalArgumentException{	
	}
	
	public EnumerationExercise(String question, String correctAnswer, String[] answerHints, int maxNumberOfAttempts, int maxAnswerTime,
		   ExerciseCategory category, Teacher author, List<Quiz> quizzes, DateGC dateRegistration, boolean inCorrectOrder) 
		   throws IllegalArgumentException {
		super(question, correctAnswer, answerHints, maxNumberOfAttempts,
			  maxAnswerTime, category, author, quizzes, dateRegistration);
		
		//convert correct answer string to ArrayList of strings (removing the ";")
		this.splitCorrectAnswer = Arrays.asList(this.getCorrectAnswer().split(";"));
		this.numberOfElements = splitCorrectAnswer.size();
		this.inCorrectOrder = inCorrectOrder;
	}
	
	/*
	public List getSplitCorrectAnswer(){
		return splitCorrectAnswer;
	}
	
	public List getSplitStudentAnswer(){
		return splitStudentAnswer;
	}*/
	
	public int getNumberOfElements(){
		return numberOfElements;
	}
	
	public boolean getInCorrectOrder(){
		return inCorrectOrder;
	}
	
	public void setInCorrectOrder(boolean trueOrFalse){
		this.inCorrectOrder = trueOrFalse;
	}
	
	@Override
	public boolean isCorrectAnswer(String answer) throws IllegalArgumentException{
		
		//convert student answer string to ArrayList of strings (removing the ";")
		splitStudentAnswer = Arrays.asList(answer.split(";"));
		
		int count = 0;
		
		for(String toTest : splitStudentAnswer){
			
			if(splitCorrectAnswer.contains(toTest)){
				
				//check if student added same element more than once in the answer			
				boolean duplicateAnswer = Collections.frequency(splitStudentAnswer, toTest) > 1;
				
				if(!duplicateAnswer){
					count++;
				}
			}
		}
					
		if(count == numberOfElements){
			return true;
		}
		
		return false;	
	}
	
	
	public boolean inCorrectOrderCheck(String answer) throws IllegalArgumentException{
			
		int count = 0;
				
		//convert student answer string to ArrayList of strings (removing the ";")
		splitStudentAnswer = Arrays.asList(answer.split(";"));
				
		if(splitCorrectAnswer.size() == splitStudentAnswer.size()){
					
			for(int i = 0; i < splitCorrectAnswer.size(); i++){
						
				if(splitStudentAnswer.get(i) == splitCorrectAnswer.get(i)){
							
					count++;
				}
			}
					
			if(count == numberOfElements){			
				return true;
			}
		}
							
		return false;	
	}

	
	@Override
	public boolean isValide(String answer) throws IllegalArgumentException{
				
		//check for invalid characters
		if(answerContainsInvalidChar(answer, "_") || answerContainsInvalidChar(answer, ",") || 
				answerContainsInvalidChar(answer, ".") || answerContainsInvalidChar(answer, "-") || 
				answerContainsInvalidChar(answer, "/") || answerContainsInvalidChar(answer, "\\")){
			return false;
		}
			
		//if answer does not contain ";"
		if(!answer.contains(";")){
			
			//check for single element correct answer
			if(splitCorrectAnswer.contains(answer)){
				return true;
			}
				
			return false;
		}
		
		return true;
	}
	
	//method returns true if answer contains a char that is not in of the elements
	private boolean answerContainsInvalidChar(String answer, CharSequence x){
		
		boolean test = false;
		
		if(answer.contains(x)){
			
			test = true;
			
			//check to see if special char is part of correct element
			for(String toTest : this.splitCorrectAnswer){
				
				if(toTest.contains(x)){
					test = false; //the char is valid in one of the elements
				}			
			}
		}			
		return test;
	}

	
	@Override
	public String getValidateText() {
		
		return "Gelieve de antwoorden in de juiste volgorde en gescheiden door een ; in te geven.";
	}
	
}

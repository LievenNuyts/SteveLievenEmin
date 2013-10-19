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
	
	
	public EnumerationExercise(String question, String correctAnswer, String[] answerHints, int maxNumberOfAttempts, int maxAnswerTime,
		   ExerciseCategory category, Teacher author, List<Quiz> quizzes, DateGC dateRegistration) 
		   throws IllegalArgumentException {
		super(question, correctAnswer, answerHints, maxNumberOfAttempts,
			  maxAnswerTime, category, author, quizzes, dateRegistration);
		
		//convert correct answer string to ArrayList of strings (removing the ";")
		splitCorrectAnswer = Arrays.asList(this.getCorrectAnswer().split(";"));
		numberOfElements = splitCorrectAnswer.size();
	}
	
	
	@Override
	public boolean isCorrectAnswer(String answer){
		
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
	
	public boolean inJuisteVolgorde(String answer){
			
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
	public boolean isValide(String answer) {
			
		//check if ; is used in the answer
		if(!answer.contains(";")){
	
			//if not, check if they have only give one correct answer of the enumeration
			if(splitCorrectAnswer.contains(answer)){
				return true;
			}		
			return false;			
		}	
		return true;
	}

	@Override
	public String getValidateText() {
		
		return "Gelieve de antwoorden in de juiste volgorde en gescheiden door een ; in te geven.";
	}
	
}

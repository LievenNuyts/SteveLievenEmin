package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.DateGC;


/**
 * 
 * @author Steve
 * @version 25/10
 *
 */

public class MultipleChoiceExercise extends Exercise implements Validatable {
        
        String multipleChoice;
        private List<String> splitCorrectAnswer;
    	private List<String> splitStudentAnswer;
    	private int numberOfElements;
    	private boolean inCorrectOrder;
        
        //Constructor with multiple parameters

        public MultipleChoiceExercise(int excerciseId, String question, String correctAnswer, 
    			String[] answerHints, int maxNumberOfAttempts, int maxAnswerTime,
    			   ExerciseCategory category, Teacher author, List<QuizExercise> quizExercises, 
    			   DateGC dateRegistration, char discriminator, boolean inCorrectOrder) 
     				   throws IllegalArgumentException {
        	super(excerciseId, question, correctAnswer, answerHints, maxNumberOfAttempts,
      			  maxAnswerTime, category, author, quizExercises, dateRegistration, discriminator);
        	
        	//convert correct answer string to ArrayList of strings (removing the ";")
    		this.splitCorrectAnswer = Arrays.asList(this.getCorrectAnswer().split(";"));
    		this.numberOfElements = splitCorrectAnswer.size();
    		this.inCorrectOrder = inCorrectOrder;
        }        

        public void setMultipleChoice(String multipleChoice) {
                this.multipleChoice = multipleChoice;
        }
        
        public String getMultipleChoice() {
                return multipleChoice;
        }

        public Map<Integer, String>  getListMultipleChoice(){
                String[] fields = this.multipleChoice.split(";");
                
                Map <Integer, String> list = new HashMap <Integer, String>();
                
                int i = 0;
                
                for (String multipleChoice : fields) {
                        i++;
                        list.put(i, multipleChoice);
                }
                
                return list;
        }
        
        @Override
    	public void setCorrectAnswer(String correctAnswer) throws IllegalArgumentException{
    		if (correctAnswer == null){
    			throw new IllegalArgumentException("Juiste antwoord is null!");
    		}
    		if (correctAnswer.isEmpty()){
    			throw new IllegalArgumentException("Gelieve een antwoord in te vullen!");
    		}
    		
    		this.correctAnswer = correctAnswer;
    		this.splitCorrectAnswer = Arrays.asList(this.getCorrectAnswer().split(";"));
    		this.numberOfElements = splitCorrectAnswer.size();	
    	}
    	
    	
    	
    	@Override
    	public boolean isCorrectAnswer(String answer) throws IllegalArgumentException{
    		
    		if(answer == null){
    			throw new IllegalArgumentException("Geen antwoord gegeven!");
    		}
    		
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
    		
    		return "Kies het correcte antwoord.";
    	}
    	
    	
    	@Override
    	public String toString() {
    		return "Exercise [getExerciseId()=" + getExerciseId()
    				+ ", getQuestion()=" + getQuestion() 
    				+ ", getCorrectAnswer()=" + getCorrectAnswer() 
    				+ ", getAnswerHints()=" + Arrays.toString(getAnswerHints())
    				+ ", getMaxNumberOfAttempts()=" + getMaxNumberOfAttempts()
    				+ ", getMaxAnswerTime()=" + getMaxAnswerTime()
    				+ ", getCategory()=" + getCategory() 
    				+ ", getAuthor()=" + getAuthor() 
    				+ ", getQuizExercises()=" + getQuizExercises()
    				+ ", getDateRegistration()=" + getDateRegistration()
    				+ ", getDiscriminator()=" + getDiscriminator()
    				+ ", hashCode()=" + hashCode() 
    				//+ ", inCorrectOrder()=" + getInCorrectOrder()
    				+ "]";
    	}

    	
    	@Override
    	public int hashCode() {
    		final int prime = 31;
    		int result = super.hashCode();
    		result = prime * result + (inCorrectOrder ? 1231 : 1237);
    		result = prime * result + numberOfElements;
    		result = prime
    				* result
    				+ ((splitCorrectAnswer == null) ? 0 : splitCorrectAnswer
    						.hashCode());
    		result = prime
    				* result
    				+ ((splitStudentAnswer == null) ? 0 : splitStudentAnswer
    						.hashCode());
    		return result;
    	}

    	
    	@Override
    	public boolean equals(Object obj) {
    		if (this == obj)
    			return true;
    		if (!super.equals(obj))
    			return false;
    		if (getClass() != obj.getClass())
    			return false;
    		MultipleChoiceExercise other = (MultipleChoiceExercise) obj;
    		if (inCorrectOrder != other.inCorrectOrder)
    			return false;
    		if (numberOfElements != other.numberOfElements)
    			return false;
    		if (splitCorrectAnswer == null) {
    			if (other.splitCorrectAnswer != null)
    				return false;
    		} else if (!splitCorrectAnswer.equals(other.splitCorrectAnswer))
    			return false;
    		if (splitStudentAnswer == null) {
    			if (other.splitStudentAnswer != null)
    				return false;
    		} else if (!splitStudentAnswer.equals(other.splitStudentAnswer))
    			return false;
    		return true;
    	}

}
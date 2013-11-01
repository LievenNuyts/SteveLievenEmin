package model;

import java.util.Arrays;
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
        
        //Constructor with multiple parameters

        public MultipleChoiceExercise(int excerciseId, String question, String correctAnswer, 
    			String[] answerHints, int maxNumberOfAttempts, int maxAnswerTime,
    			   ExerciseCategory category, Teacher author, List<QuizExercise> quizExercises, 
    			   DateGC dateRegistration, char discriminator, String multipleChoice) 
     				   throws IllegalArgumentException {
        	super(excerciseId, question, correctAnswer, answerHints, maxNumberOfAttempts,
      			  maxAnswerTime, category, author, quizExercises, dateRegistration, discriminator);
        	
        	this.multipleChoice = multipleChoice;
        }   
        
        //getters & setters

        public void setMultipleChoice(String multipleChoice) {
                this.multipleChoice = multipleChoice;
        }
        
        public String getMultipleChoice() {
                return multipleChoice;
        }
        
        //methods

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
    	}
    	
    	
    	
    	@Override
    	public boolean isCorrectAnswer(String answer) throws IllegalArgumentException{
    		
    		if(answer == null){
    			throw new IllegalArgumentException("Geen antwoord gegeven!");
    		}
    		return false;
    	}
    	
    	@Override
    	public boolean isValide(String answer) throws IllegalArgumentException{

    			return false;
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
    				+ ", MultipleChoice()=" + getMultipleChoice()
    				+ "]";
    	}

    	


    	
    	

}
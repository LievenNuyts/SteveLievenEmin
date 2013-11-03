package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Exercise.ExerciseCategory;
import utils.DateGC;
import utils.DateQuiz;

/**
 * 
 * @author Steve
 * @version 25/10
 *
 */

public class MultipleChoiceExercise extends Exercise {
        
        String multipleChoice;
        
        //Default constructor
    	public MultipleChoiceExercise() throws IllegalArgumentException {
    	}
        
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

}
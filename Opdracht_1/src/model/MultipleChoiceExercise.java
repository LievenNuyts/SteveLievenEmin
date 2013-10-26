package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.DateQuiz;

/**
 * 
 * @author Steve
 * @version 25/10
 *
 */

public class MultipleChoiceExercise extends Exercise {
        
        String multipleChoice;
        
        //Constructor with multiple parameters

        public MultipleChoiceExercise(String question, String correctAnswer, String[] answerHints, int maxNumberOfAttempts, int maxAnswerTime,
     		   ExerciseCategory category, Teacher author, List<Quiz> quizzes, DateQuiz dateRegistration, boolean inCorrectOrder) 
     				   throws IllegalArgumentException {
    		super(question, correctAnswer, answerHints, maxNumberOfAttempts,
    				  maxAnswerTime, category, author, quizzes, dateRegistration);
    		
                this.multipleChoice = multipleChoice; 
        }        

        public void setMultipleChoice(String multipleChoice) {
                this.multipleChoice = multipleChoice;
        }
        
        public String getmultipleChoice() {
                return multipleChoice;
        }

        public Map<Integer, String>  getLijstMeerkeuze(){
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
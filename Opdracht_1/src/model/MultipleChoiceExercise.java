package model;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import utils.DateGC;
import utils.DateQuiz;
import model.Teacher;
import model.ExerciseCatalog;
import model.Exercise.ExerciseCategory;

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
     		   ExerciseCategory category, Teacher author, List<Quiz> quizzes, DateGC dateRegistration, boolean inCorrectOrder) {
                super(question, correctAnswer, answerHints, maxNumberOfAttempts, maxAnswerTime, category, author);
                this.multipleChoice = multipleChoice; 
        }        

        public void setMultipleChoice(String multipleChoice) {
                this.multipleChoice = multipleChoice;
        }
        
        public String getmultipleChoice() {
                return multipleChoice;
        }

        public Map<Integer, String>  getLijstMeerkeuze(){
                String[] velden = this.meerkeuze.split(";");
                Map <Integer, String> lijstMeerkeuze = new HashMap <Integer, String>();
                int i = 0;
                for (String multipleChoice : velden) {
                        i++;
                        lijstMeerkeuze.put(i, meerkeuze);
                }
                return lijstMeerkeuze;
        }

        @Override
        public boolean isValide(String answer) {
                StringTokenizer binnenkomend = new StringTokenizer(answer, ";");
                StringTokenizer origineel = new StringTokenizer(this.getAnswer(), ";");
                if (binnenkomend.countTokens() == origineel.countTokens()){
                        return true;
                }else{
                        getValideerTekst();
                        return false;
                }
        }

        
        @Override
        public String getValideerTekst() {
                return "Kies één van de getallen die in de opgave staan";
        }

}
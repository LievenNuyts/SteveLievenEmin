package model;

import java.util.ArrayList;


/**
 * @author Steve
 * @version 25/10
 * 
 */
public class QuizCatalog {
        private ArrayList<Quiz> quizCatalogs = new ArrayList<Quiz>();        

        public ArrayList<Quiz> getQuizCatalogs(){
                return quizCatalogs;
        }
        
        //toevoegen
        
        public void addQuiz(Quiz quiz) throws Exception{
                try{
                        
                        for(int i = 0; i < this.quizCatalogs.size(); i++){
                                if((this.quizCatalogs.get(i).getSubject()).equals(quiz.getSubject())){ throw new Exception("Existing quiz!");
                                }
                        }
                        quizCatalogs.add(quiz);
                }
                catch (Exception ex){ throw new Exception(ex.getMessage());
                }
        }
        
        //verwijderen
        
        public void deleteQuiz(Quiz quiz) {
        	quizCatalogs.remove(quiz);
        }

        @Override
        public String toString() {
                String catalog = "";
                for (Quiz quiz : quizCatalogs) {
                        catalog += quiz + "\n";
                }
                return catalog;
        }
}

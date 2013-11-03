package model;

import java.util.ArrayList;


/**
 * @author Steve
 * @version 25/10
 * 
 */

public class QuizCatalog implements Comparable<QuizCatalog>, Cloneable{
	
        private ArrayList<Quiz> quizCatalogs = new ArrayList<Quiz>();        
        
        
        public QuizCatalog() throws IllegalArgumentException{
    		this.setQuizCatalogs(new ArrayList<Quiz>());
    	}
    	
    	public QuizCatalog(ArrayList<Quiz> quizCatalogs) throws IllegalArgumentException{
    		this.setQuizCatalogs(quizCatalogs);
    	}
    	
    	//get & set
    	
    	public ArrayList<Quiz> getQuizCatalogs(){
            return quizCatalogs;
    }
        
        public void setQuizCatalogs(ArrayList<Quiz> quizCatalogs) throws IllegalArgumentException{
        	if (quizCatalogs == null)throw new IllegalArgumentException("Quiz Catalog is null");
			this.quizCatalogs = quizCatalogs;
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((quizCatalogs == null) ? 0 : quizCatalogs.hashCode());
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
			QuizCatalog other = (QuizCatalog) obj;
			if (quizCatalogs == null) {
				if (other.quizCatalogs != null)
					return false;
			} else if (!quizCatalogs.equals(other.quizCatalogs))
				return false;
			return true;
		}

		@Override
		public int compareTo(QuizCatalog o) {
			// TODO Auto-generated method stub
			return 0;
		}
        
}

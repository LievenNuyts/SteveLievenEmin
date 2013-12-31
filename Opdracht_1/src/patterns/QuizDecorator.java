package patterns;

import model.Quiz;

public abstract class QuizDecorator extends Quiz{
	
	private Quiz quizToDecorate;
	
	public QuizDecorator(Quiz quiz){
		this.setQuizToDecorate(quiz);
	}

	public Quiz getQuizToDecorate() {
		return quizToDecorate;
	}

	public void setQuizToDecorate(Quiz quizToDecorate) {
		this.quizToDecorate = quizToDecorate;
	}	
}

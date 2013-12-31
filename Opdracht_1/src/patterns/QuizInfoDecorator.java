package patterns;

import model.Quiz;
import model.QuizExercise;

public class QuizInfoDecorator extends QuizDecorator{

	public QuizInfoDecorator(Quiz quiz) {
		super(quiz);
	}

	public String showReport() {
		String info = "";
		
		info += "Onderwerp: " + this.getSubject()
				+ "Datum: " + this.getDate();
		
		return info;
	}	
}

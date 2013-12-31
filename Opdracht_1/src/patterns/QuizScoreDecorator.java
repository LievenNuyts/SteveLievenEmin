package patterns;

import java.util.List;

import model.Quiz;
import model.QuizExercise;

public class QuizScoreDecorator extends QuizDecorator{

	public QuizScoreDecorator(Quiz quiz){
		super(quiz);
	}


	public String showReport(List<Double> scores) {
		String info = "";
		double total = 0.0;
		double maxTotal = 0.0;
		double procentScore = 0;
		
		for(QuizExercise qE : this.getQuizExercises()){
			maxTotal += qE.getMaxScore();
		}
		
		for(double d : scores){
			total += d;
		}
		
		procentScore =  (100 / maxTotal) * total;
		
		info += "Gemiddelde score quiz: " + total / scores.size();
		
		if(procentScore < 50){
			info += "GEBUISD";
		}
		else if(procentScore < 70){
			info += "Voldoening";
		}
		else{
			info += "Zeer goed!!!";
		}
		
		return info;
	}
}

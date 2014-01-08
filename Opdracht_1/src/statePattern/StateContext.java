package statePattern;

import view.CreateQuizView;
import view.DeleteQuizView;
import controller.ChangeQuizController;
import controller.CreateQuizController;
import controller.DeleteQuizController;
import model.Quiz;

public class StateContext  {

	private QuizBehavior quizState;

	public void setQuizState(Quiz quiz){

		String t = quiz.getStatus().toString();
		
		
		
		if (quiz.getStatus().toString().equals("Ready")){
			quizState = new Ready();
		}
		else if (quiz.getStatus().toString().equals("Under Construction")){
			quizState = new UnderConstruction();
		}
		else if (quiz.getStatus().toString().equals("Completed")){
			quizState = new Completed();
		}
		else if (quiz.getStatus().toString().equals("Last Chance")){
			quizState = new LastChance();
		}
		else if (quiz.getStatus().toString().equals("Closed")){
			quizState = new Closed();
		}
		
	}

	public void behavior(DeleteQuizController dC, DeleteQuizView dV) {
		this.quizState.behavior(dC, dV);
	}
	
	public void behavior(CreateQuizController cC, CreateQuizView cV){
		this.quizState.behavior(cC, cV);
	};
	
	public void behavior(ChangeQuizController uC, CreateQuizView uV){
		this.quizState.behavior(uC, uV);
	};

}

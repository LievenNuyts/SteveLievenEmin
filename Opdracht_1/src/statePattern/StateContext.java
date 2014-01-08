package statePattern;

import view.CreateQuizView;
import view.DeleteQuizView;
import controller.ChangeQuizController;
import controller.CreateQuizController;
import controller.DeleteQuizController;
import model.Quiz;

/**
 * 
 * @author java
 *
 */
public class StateContext {

	private StateBehavior stateBehavior;

	// Modifiers
	
	public void behavior(DeleteQuizController dC, DeleteQuizView dV) {
		this.stateBehavior.behavior(dC, dV);
	}
	
	public void behavior(CreateQuizController cC, CreateQuizView cV){
		this.stateBehavior.behavior(cC, cV);
	}
	
	public void behavior(ChangeQuizController uC, CreateQuizView uV){
		this.stateBehavior.behavior(uC, uV);
	}

	/**
	 * Method to set correct state class based on info in initState.txt file
	 */
	public void setStateBehavior(Quiz quiz){
		try {
			String className = quiz.getStatus().toString().replaceAll("\\s+","");
		
			// Set instance of corresponding state
			stateBehavior = (StateBehavior) Class.forName("statePattern." + className).newInstance();
		} catch (InstantiationException ex) {
			System.out.println(ex.getMessage());
		} catch (IllegalAccessException ex) {
			System.out.println(ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	}
}

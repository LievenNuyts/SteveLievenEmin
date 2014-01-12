package statePattern;

import view.ChangeQuizView;
import view.CreateQuizView;
import view.DeleteQuizView;
import controller.ChangeQuizController;
import controller.CreateQuizController;
import controller.DeleteQuizController;

/**
 * 
 * @author java
 *
 */
public abstract class StateBehavior {
	
	public void behavior(DeleteQuizController dC, DeleteQuizView dV){};
	public void behavior(CreateQuizController cC, CreateQuizView cV){};
	public void behavior(ChangeQuizController uC, ChangeQuizView uV){};
	
}

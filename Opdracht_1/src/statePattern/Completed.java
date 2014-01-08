/**
 * 
 */
package statePattern;

import view.DeleteQuizView;
import controller.ChangeQuizController;
import controller.CreateQuizController;
import controller.DeleteQuizController;

/**
 * @author java
 *
 */
public class Completed extends QuizBehavior {
	
	@Override
	public void behavior(DeleteQuizController dC, DeleteQuizView dV){
		
		dV.getDeleteButton().setEnabled(false);;
	}

}

/**
 * 
 */
package state;

import view.DeleteQuizView;
import controller.DeleteQuizController;

/**
 * @author java
 *
 */
public class UnderConstruction extends QuizBehavior {

	@Override
	public void behavior(DeleteQuizController dC, DeleteQuizView dV){
		
		dV.getDeleteButton().setEnabled(true);;
	}
	

}

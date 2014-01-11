package statePattern;

import view.DeleteQuizView;
import controller.DeleteQuizController;

/**
 * @author java
 *
 */
public class UnderConstruction extends StateBehavior {

	//enable delete button in DeleteQuizView
	@Override
	public void behavior(DeleteQuizController dC, DeleteQuizView dV){
		dV.getDeleteButton().setEnabled(true);
	}
}

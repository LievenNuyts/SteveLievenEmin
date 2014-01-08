package statePattern;

import view.DeleteQuizView;
import controller.DeleteQuizController;

/**
 * 
 * @author java
 *
 */
public class Ready extends StateBehavior {
	
	@Override
	public void behavior(DeleteQuizController dC, DeleteQuizView dV){
		
		dV.getDeleteButton().setEnabled(false);;
	}
}

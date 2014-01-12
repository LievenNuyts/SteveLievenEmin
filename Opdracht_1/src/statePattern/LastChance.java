package statePattern;

import view.ChangeQuizView;
import view.DeleteQuizView;
import controller.ChangeQuizController;
import controller.DeleteQuizController;

/**
 * 
 * @author Lieven
 * @author Steve
 * @author Emin
 *
 */
public class LastChance extends StateBehavior {
	
	//disable delete button in DeleteQuizView
	@Override
	public void behavior(DeleteQuizController dC, DeleteQuizView dV){		
		dV.getDeleteButton().setEnabled(false);;
	}
	public void behavior(ChangeQuizController uC, ChangeQuizView uV){		
		uV.getUpdateButton().setEnabled(true);
		System.out.println("Last Chance reached");
	}
}

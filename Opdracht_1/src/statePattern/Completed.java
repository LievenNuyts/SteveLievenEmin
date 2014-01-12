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
public class Completed extends StateBehavior {
	
	//enable delete button in DeleteQuizView
	@Override
	public void behavior(DeleteQuizController dC, DeleteQuizView dV){
		dV.getDeleteButton().setEnabled(true);;
	}
	public void behavior(ChangeQuizController uC, ChangeQuizView uV){		
		uV.getUpdateButton().setEnabled(true);
		System.out.println("Completed reached");
	}
}

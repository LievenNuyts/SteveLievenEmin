/**
 * 
 */
package observer;

import model.Quiz;

/**
 * @author Emin
 * @author Steve
 * @author Lieven
 *
 */
public interface IObserver {

	public void update(ISubject subject, Quiz quiz);
	
}

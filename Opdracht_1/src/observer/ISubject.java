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
public interface ISubject {

	public void addObserver(IObserver observer);
	public void removeObserver(IObserver observer);
	public void notifyObservers(Quiz quiz);
}

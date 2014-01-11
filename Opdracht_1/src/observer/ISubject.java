/**
 * 
 */
package observer;

/**
 * @author Emin
 * @author Steve
 * @author Lieven
 *
 */
public interface ISubject {

	public void addObserver(IObserver observer);
	public void removeObserver(IObserver observer);
	public void notifyObservers();
	
	/*
	//create ArrayList 'observers' in the subject class
	
	//implementation method addObserver(Iobserver observer)
	public void addObserver(IObserver observer){
		observers.add(observer);
	}
	
	//implementation method removeObserver(Iobserver observer)
	public void removeObserver(IObserver observer){
		observers.remove(observer);
	}
		
	//implementation method notifyObservers() method:
	public void notifyObservers(){
		Iterator i = observers.iterator();
		while (i.hasNext()){
			IObserver o = (IObserver) i.next();
			o.update(this);
		}
	}*/
}

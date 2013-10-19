package model;

/**
 * 
 * @author Lieven
 * @version 19/10/2013
 *
 */

public interface Validatable {

	public boolean isValide(String answer);
	
	public String getValidateText();	
}

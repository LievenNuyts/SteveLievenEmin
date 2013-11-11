package view;

/**
 * 
 * @author Steve
 * @version 11/11/2013
 *
 */

public class Menu {

	private String menu;
	String[] choices = new String[6];

	public Menu(String[] choices) {

		this.choices = choices;

		int i = 0;

		for (i = 0; i < choices.length; i++){
			menu = menu + i++ + " - " + choices[i] + "\n";

		}
	}
	public int getChoice() {
		return 0; //method geeft keuze als int terug
	}
}


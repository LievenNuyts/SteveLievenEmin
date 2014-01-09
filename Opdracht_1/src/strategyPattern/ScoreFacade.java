package strategyPattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Quiz;

/**
 * @author java
 *
 */
public class ScoreFacade {
	
	public IScoreable iScoreable;
	
	// Constructors
	
	/**
	 * Default constructor
	 */
	public ScoreFacade() {
		setIScoreable();
	}
	
	//Modifiers
	
	public double calculateScore(Quiz quiz, List<String> antwoorden){
		return iScoreable.calculateScore(quiz, antwoorden);
	}
	
	/**
	 * Method to set correct score class based on info in initScore.txt file
	 */
	public void setIScoreable(){
		// Get initScore.dat file
		File file = new File("src" + File.separator + "files" + File.separator + "initScore.dat");
	 
		try{
			// Scan through file
			Scanner scanner = new Scanner(file);
			
			List<String> tempLine = new ArrayList<String>();
			
			// Add each line as String object to tempLine list
			while (scanner.hasNextLine()){
				tempLine.add(scanner.nextLine());
			}
			
			if (scanner!=null){
				scanner.close();
			}
			
			// Find className
			Scanner scanner2 = new Scanner(tempLine.get(2));
			scanner2.skip("persistentyMethod=");
			String className = scanner2.next();
			
			if (scanner2!=null){
				scanner2.close();
			}
			
			// Set instance of the chosen persistence
			iScoreable = (IScoreable) Class.forName("strategyPattern." + className).newInstance();
		} catch(FileNotFoundException ex){
			System.out.println("Bestand niet gevonden!");
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}

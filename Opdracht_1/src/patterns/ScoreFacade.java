/**
 * 
 */
package patterns;

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
	public String persistenty;
	public IScoreable iScoreable;
	
	public ScoreFacade() {
		setPersistenty();
		
		if (persistenty == "BasicScore") {
			iScoreable = new BasicScore();
		}
		else if (persistenty == "NumberOfAttemptScore"){
			iScoreable = new NumberOfAttemptScore();
		}
		else{
			iScoreable = new TimeBasedScore();
		}
	}
	
	public double calculateScore(Quiz quiz, List<String> antwoorden){
		return iScoreable.calculateScore(quiz, antwoorden);
	}
	
	public void setPersistenty(){
		// Get initScore.dat file
		File file = new File("src" + File.separator + "files" + File.separator + "initScore.dat");
	 
		try{
			// Scan through file
			Scanner scanner = new Scanner(file);
			
			List<String> tempLine = new ArrayList<String>();
			
			// Add each line as String object to tempExercises list
			while (scanner.hasNextLine()){
				tempLine.add(scanner.nextLine());
			}
			
			if (scanner!=null){
				scanner.close();
			}
			
			Scanner scanner2 = new Scanner(tempLine.get(2));
			scanner2.skip("persistentyMethod=");
			String temp = scanner2.next();
			if (temp.equals("TimeBasedScore")){
				this.persistenty = "TimeBasedScore";
			}
			else if (temp.equals("BasicScore")){
				this.persistenty = "BasicScore";
			}
			else{
				this.persistenty = "NumberOfAttemptScore";
			}
			
		} catch(FileNotFoundException ex){
			System.out.println("Bestand niet gevonden!");
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

}

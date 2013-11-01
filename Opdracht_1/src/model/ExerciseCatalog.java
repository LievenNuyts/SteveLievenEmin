/**
 * 
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.DateGC;

/**
 * 
 * @author Lieven
 * @author Emin
 * @version 10/10/2013
 *
 */
public class ExerciseCatalog implements Comparable<ExerciseCatalog>, Cloneable{
	
	private List<Exercise> exercises;
	
	/**
	 * Default constructor
	 * 
	 * @throws IllegalArgumentException
	 * @throws CloneNotSupportedException 
	 */
	public ExerciseCatalog() throws IllegalArgumentException{
		this.setExercises(new ArrayList<Exercise>());
	}
	
	/**
	 * Constructor with 1 param
	 * 
	 * @param opdrachten
	 * @throws IllegalArgumentException
	 * @throws CloneNotSupportedException 
	 */
	public ExerciseCatalog(List<Exercise> exercises) throws IllegalArgumentException{
		this.setExercises(exercises);
	}

	// Selectors
	
	/**
	 * @return
	 */
	public List<Exercise> getExercises() {
		return exercises;
	}

	// Modifiers
	
	/**
	 * Set exercises
	 * 
	 * @param exercises
	 * @throws IllegalArgumentException
	 * @throws CloneNotSupportedException 
	 */
	public void setExercises(List<Exercise> exercises) throws IllegalArgumentException{
		if (exercises == null)throw new IllegalArgumentException("Opdrachten verzameling is null");
		this.exercises = exercises;
	}
	
	/**
	 * Add exercise to list
	 * 
	 * @param exercise
	 * @throws IllegalArgumentException
	 */
	public void addExercise(Exercise exercise) throws IllegalArgumentException{
		if (exercise == null)throw new IllegalArgumentException("Opdracht is null!");
		for (Exercise opdrachtCheck : exercises) {
			if (opdrachtCheck.equals(exercise))throw new IllegalArgumentException("Opdracht bestaat al!");
		}
		
		//exercise.setExerciseId();
		exercises.add(exercise);
	}
	
	/**
	 * Remove exercise from list
	 * 
	 * @param exercise
	 * @throws IllegalArgumentException
	 */
	public void removeExercise(Exercise exercise) throws IllegalArgumentException{
		if (exercise == null)throw new IllegalArgumentException("Opdracht is null!");
		exercises.remove(exercise);
	}
	
	/**
	 * Update exercise from list
	 * 
	 * @param oldOpdracht
	 * @param newOpdracht
	 * @throws IllegalArgumentException
	 */
	public void updateExercise(Exercise oldExercise, Exercise newExercise) throws IllegalArgumentException{
		if (oldExercise == null)throw new IllegalArgumentException("oldOpdracht is null!");
		if (newExercise == null)throw new IllegalArgumentException("newOpdracht is null!");
		int index = exercises.lastIndexOf(oldExercise);
		exercises.set(index, newExercise);
	}
	
	public void writeExercisesToFile(){
		File file = new File("src" + File.separator + "files" + File.separator + "exercises.txt");
		
		try {
			PrintWriter writer = new PrintWriter(file);
			for (int i = 0;i < exercises.size();i++){
				Exercise exercise = exercises.get(i);
				String line = 
						exercise.toString();
				writer.println(line +"\n");
			}
			if (writer !=null)
				writer.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void readExercisesFromFile(){
		  String output ="";
		  File file = new File("src" + File.separator + "files" + File.separator + "exercises.txt");
		  try{
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()){
		      
			}
			if (scanner!=null){
			  scanner.close();
			}
			System.out.println(output);
		  }
		  catch(FileNotFoundException ex){
		    System.out.println("Bestand niet gevonden!");
		  }
		  catch(Exception ex){
		    System.out.println(ex.getMessage());
		  }
		}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		File file = new File("src" + File.separator + "files" + File.separator + "exercises.txt");
		try {
			
			Exercise exercise12 = new SimpleExercise(1, "Wat is mijn Voornaam","Emin",new String[]{"kort","4"},2,30,
					Exercise.ExerciseCategory.AARDRIJKSKUNDE,Teacher.BAKKER,new ArrayList<QuizExercise>(),
					new DateGC(2013,10,1), 'S');
			Exercise exercise22 = new SimpleExercise(2,"Wat is mijn Naam","Iandyrhanov",new String[]{"kort","4"},2,30,
					Exercise.ExerciseCategory.AARDRIJKSKUNDE,Teacher.BAKKER,new ArrayList<QuizExercise>(),
					new DateGC(2013,10,1), 'S');
			
			Quiz quiz1 = new Quiz("Namen",3,false,false);
			Quiz quiz2 = new Quiz("Landen",4,false,false);
			
			QuizExercise quizExercise = new QuizExercise(5,quiz1,exercise12);
			QuizExercise quizExerciseEqual = new QuizExercise(5,quiz1,exercise12);
			QuizExercise quizExerciseNotEqual = new QuizExercise(10,quiz2,exercise22);
			
			List<QuizExercise> qe = new ArrayList<QuizExercise>();
			qe.add(quizExercise);
			qe.add(quizExerciseNotEqual);
			
			Exercise exercise1 = new SimpleExercise(1, "Wat is mijn Voornaam","Emin",new String[]{"kort","4"},2,30,
					Exercise.ExerciseCategory.AARDRIJKSKUNDE,Teacher.BAKKER,qe,
					new DateGC(2013,10,1), 'S');
			Exercise exercise2 = new SimpleExercise(2, "Wat is mijn Naam","Iandyrhanov",new String[]{"kort","4"},2,30,
					Exercise.ExerciseCategory.AARDRIJKSKUNDE,Teacher.BAKKER,qe,
					new DateGC(2013,10,1), 'S');
			Exercise exercise3 = new SimpleExercise(3, "Hoofdstad van België?","Brussel",new String[]{"kort","4"},2,30,
					Exercise.ExerciseCategory.AARDRIJKSKUNDE,Teacher.BAKKER,qe,
					new DateGC(2013,10,1), 'S'); 
			
			ExerciseCatalog ec = new ExerciseCatalog();
			ec.addExercise(exercise1);
			ec.addExercise(exercise2);
			ec.addExercise(exercise3);
			
			ec.writeExercisesToFile();
			
		     Scanner s = new Scanner(file);
		     s.skip("Exercise [getExerciseId()="); //!!!!!!!!!! [
		     
		     System.out.println(s.next());
		     s.close();
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	// Comparisons
	
	/**
	 * Comparable
	 * 
	 * @param exerciseCatalog
	 * @return
	 */
	public int compareTo(ExerciseCatalog exerciseCatalog){
		return this.getExercises().size() - exerciseCatalog.getExercises().size();
	}
	
	// Cloneable
	
	/**
	 * Method to clone this object
	 * 
	 * @return
	 */
	@Override
	public ExerciseCatalog clone() throws CloneNotSupportedException{
		List<Exercise> ex = new ArrayList<Exercise>();
		for (Exercise e : exercises) {
			ex.add(e);
		}
		
		ExerciseCatalog ec = new ExerciseCatalog(ex);
		
		return ec;
	}
	
	// Overrides
	
	@Override
	public String toString() {
		return "ExerciseCatalog [exercises=" + exercises + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((exercises == null) ? 0 : exercises.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExerciseCatalog other = (ExerciseCatalog) obj;
		if (exercises == null) {
			if (other.exercises != null)
				return false;
		} else if (!exercises.equals(other.exercises))
			return false;
		return true;
	}
}
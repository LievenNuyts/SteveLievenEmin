/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Lieven
 * @author Emin
 * @version 10/10/2013
 *
 */
public class ExerciseCatalog implements Comparable<ExerciseCatalog>{
	
	private List<Exercise> exercises;
	
	/**
	 * Default constructor
	 * 
	 * @throws IllegalArgumentException
	 */
	public ExerciseCatalog() throws IllegalArgumentException{
		this.setExercises(new ArrayList<Exercise>());
	}
	
	/**
	 * Constructor with 1 param
	 * 
	 * @param opdrachten
	 * @throws IllegalArgumentException
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
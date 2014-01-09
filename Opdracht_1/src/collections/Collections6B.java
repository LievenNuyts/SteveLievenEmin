package collections;

import java.util.HashSet;

import model.Exercise;
import model.ExerciseCatalog;
import model.Quiz;
import model.QuizCatalog;

public class Collections6B {

	private HashSet<Exercise> hashSet;
	private QuizCatalog qz;
	private ExerciseCatalog ex;
	
	public Collections6B(){
		
		qz = new QuizCatalog();
		qz.readQuizzesFromFile();	
		ex = new ExerciseCatalog();
		ex.readExercisesFromFile();		
		ex.createQuizExercises(ex.getExercises(), qz.getQuizCatalogs());
		
		hashSet = new HashSet<Exercise>();
		
		for(Exercise exercise : ex.getExercises()){				
			hashSet.add(exercise);
		}
	}
	
	public HashSet<Exercise> getHashSet(){
		return this.hashSet;
	}
	
	public void setHashSet(HashSet<Exercise> hashSet){
		this.hashSet = hashSet;
	}

	public static void main(String[] args) {
		
		Collections6B mO = new Collections6B();
		
		System.out.println("Geselecteerde quiz: ");
		
		System.out.println("Quizzes met gemeenschappelijke opdrachten: ");	
		
		for(Exercise exercise : mO.getHashSet()){
			System.out.println("Quiz: " + exercise + "\n Opdracht: " + exercise.getQuizExercises());
		
			
		}
	}
}

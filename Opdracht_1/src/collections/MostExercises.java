package collections;

import java.util.Collections;
import java.util.TreeSet;

import model.ExerciseCatalog;
import model.Quiz;
import model.QuizCatalog;

/**
 * 
 * @author Lieven
 *
 */

//class that will sort the quizzes by number of exercises and alpabetical order when equal amount of exercises
//using treeset

public class MostExercises {

	private TreeSet<Quiz> treeSet;
	private QuizCatalog qCatalog;
	private ExerciseCatalog eCatalog;
	
	public MostExercises(){
		
		qCatalog = new QuizCatalog();
		qCatalog.readQuizzesFromFile();
		
		eCatalog = new ExerciseCatalog();
		eCatalog.readExercisesFromFile();
		
		eCatalog.createQuizExercises(eCatalog.getExercises(), qCatalog.getQuizCatalogs());
		
		treeSet = new TreeSet<Quiz>(new MyExerciseComparator());
		
		for(Quiz quiz : this.qCatalog.getQuizCatalogs()){	
			treeSet.add(quiz);
		}
	}
	
	public TreeSet<Quiz> getTreeSet(){
		return this.treeSet;
	}
	
	public void setTreeSet(TreeSet<Quiz> treeSet){
		this.treeSet = treeSet;
	}

	// using the TreeSet
	
	public static void main(String[] args) {
		
		MostExercises mO = new MostExercises();
		
		for(Quiz quiz : mO.getTreeSet()){
			System.out.println("#Exercises: " + quiz.getQuizExercises().size() + " - Subject: " + quiz.getSubject());
		}
	}
	
	// using the original ArrayList of the QuizCatalog
	/*
	public static void main(String[] args) {
		
		MostExercises mO = new MostExercises();
		
		Collections.sort(mO.qCatalog.getQuizCatalogs(), new MyExerciseComparator());
		
		for(Quiz quiz : mO.qCatalog.getQuizCatalogs()){
			System.out.println("#Exercises: " + quiz.getQuizExercises().size() + " - Subject: " + quiz.getSubject());
		}
	}*/
}


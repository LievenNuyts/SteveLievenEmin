package collections;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;

import model.Exercise;
import model.ExerciseCatalog;
import model.QuizCatalog;

public class Collections6A {

	private QuizCatalog qz;
	private ExerciseCatalog ex;
	private HashMap<String,Integer>  map_01;
	private Integer counter;
	private Set<String> keys;
	
	//constructor
	
	public Collections6A(){
		
		qz = new QuizCatalog();
		qz.readQuizzesFromFile();	
		ex = new ExerciseCatalog();
		ex.readExercisesFromFile();		
		ex.createQuizExercises(ex.getExercises(), qz.getQuizCatalogs());
		counter = 0;
		
		Hashtable<String,Integer> source = new Hashtable<String,Integer>();
		map_01 = new HashMap<String,Integer>(source);
		
		for(Exercise exercise : ex.getExercises()){					
			counter = map_01.get(exercise.getCategory().toString());
			if(counter == null){
				counter = 0;
			}
			map_01.put(exercise.getCategory().toString(),++counter);			
		}
	}
	
	public void outCome(){
		
		keys = map_01.keySet();		
		System.out.println("Beschikbare categorieën: ");		
		for(String category : keys ){
			System.out.println("Categorie: " + category + " | Aantal opdrachten: " + map_01.get(category));
		}
	}
	
	//properties
	
	public HashMap<String,Integer> getHashMap(){
		return this.map_01;
	}
	
	public void setHashMap(HashMap<String,Integer> map_01){
		this.map_01 = map_01;
	}
	
	//main

	public static void main(String[] args) {
		
		Collections6A myColl = new Collections6A();
		myColl.outCome();
		}
	
}

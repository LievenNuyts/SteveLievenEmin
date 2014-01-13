/**
 * 
 */
package collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import model.Exercise;
import model.ExerciseCatalog;
import model.Quiz;
import model.QuizCatalog;
import model.QuizExercise;

/**
 * 
 * @author Emin
 *
 */
public class CommonQuizzes {
	public List<Quiz> tempQuizzesList;
	private HashSet<Exercise> e1, e2;
	
	// Constructors
	
	/**
	 * Default constructor
	 */
	public CommonQuizzes(){
		tempQuizzesList = new ArrayList<>();
		e1 = new HashSet<Exercise>();
		e2 = new HashSet<Exercise>();
		
		QuizCatalog qC = new QuizCatalog();
		ExerciseCatalog eC = new ExerciseCatalog();
		
		eC.readExercisesFromFile();
		qC.readQuizzesFromFile();
		eC.createQuizExercises(eC.getExercises(), qC.getQuizCatalogs());
		 
		for (Quiz q : qC.getQuizCatalogs()) {
			tempQuizzesList.add(q);
			for (QuizExercise qE : q.getQuizExercises()){
				e2.add(qE.getExercise());
			}
		}
	}
	
	
	/**
	 * Get quizzes with common exercises
	 * 
	 * @param quiz
	 */
	public void getCommonQuizzes(Quiz quiz){
		for (QuizExercise qE : quiz.getQuizExercises()){
			e1.add(qE.getExercise());
		}
		
		e1.retainAll(e2);
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		CommonQuizzes cQ = new CommonQuizzes();
		int row = 1;
		
		// Display quizzes in console
		for (Quiz quiz : cQ.tempQuizzesList){
			System.out.println(row++ + ": " + quiz.getSubject());
		}
		System.out.print("\nSelect quiz: ");
		Scanner scan = new Scanner(System.in);
		int selectedRow = scan.nextInt();
		scan.close();
		
		// Pass selected number to getCommonQuizzes method
		cQ.getCommonQuizzes(cQ.tempQuizzesList.get(selectedRow - 1));
		
		
		// Display layout in console
		System.out.println("\nGeselecteerde quiz: " + cQ.tempQuizzesList.get(selectedRow - 1).getSubject());
		System.out.println("Quizen met gemeenschappelijke opdrachten:");
		for (Exercise ex : cQ.e1){
			for (Quiz q : cQ.tempQuizzesList){
				for (QuizExercise qE : q.getQuizExercises()){
					if (qE.getExercise().getQuestion().equals(ex.getQuestion())){
						if (!cQ.tempQuizzesList.get(selectedRow - 1).getSubject().equals(qE.getQuiz().getSubject())){
							System.out.println("\t" +qE.getQuiz().getSubject());
							System.out.println("\t\t" + ex.getQuestion());
						}
					}
				}
			}
		}
	}

}

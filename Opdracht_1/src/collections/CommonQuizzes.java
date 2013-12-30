/**
 * 
 */
package collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
	
	private HashSet<Quiz> quizzes;
	
	// Constructors
	
	/**
	 * Default constructor
	 */
	public CommonQuizzes(){
		QuizCatalog qC = new QuizCatalog();
		ExerciseCatalog eC = new ExerciseCatalog();
		
		eC.readExercisesFromFile();
		qC.readQuizzesFromFile();
		eC.createQuizExercises(eC.getExercises(), qC.getQuizCatalogs());
		
		HashSet<Quiz> tempQ = new HashSet<Quiz>(); 
		for (Quiz q : qC.getQuizCatalogs()) {
			tempQ.add(q);
		}
		this.setQuizzes(tempQ);
	}
	
	// Selectors
	
	/**
	 * @return
	 */
	public HashSet<Quiz> getQuizzes() {
		return quizzes;
	}
	
	// Modifiers
	
	/**
	 * Set quizzes
	 * @param quizzes
	 */
	public void setQuizzes(HashSet<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
	
	/**
	 * Get quizzes with common exercises
	 * 
	 * @param quiz
	 */
	public void getCommonQuizzes(Quiz quiz){
		System.out.println("\nQuizen met gemeenschappelijke opdrachten:\n");
		
		HashSet<Exercise> qE1 = new HashSet<Exercise>();
		
		
		for (QuizExercise qE : quiz.getQuizExercises()){
			qE1.add(qE.getExercise());
		}
		
		for (Quiz q : this.quizzes){
			HashSet<Exercise> qE2 = new HashSet<Exercise>();
			System.out.println(q.getSubject());
			
			for (QuizExercise qE : q.getQuizExercises()){
				qE2.add(qE.getExercise());
			}
			
			Set<Exercise> union = new HashSet<Exercise>(qE1);
			union.retainAll(qE2);
			
			for (Exercise ex : union){
				System.out.println("\t" + ex.getQuestion());
			}
		}
	}
	

//	for (Quiz q : this.quizzes){
//		String text = q.getSubject();
//		
//		for (QuizExercise qE : q.getQuizExercises()){
//			for (QuizExercise qE2 : quiz.getQuizExercises()){
//				
//				// Compare exercises
//				if (qE.getExercise().equals(qE2.getExercise())){
//					if (!q.equals(quiz)){
//						text += "\n" + qE.getExercise().getQuestion();
//						System.out.println(text);
//					}
//				}
//			}
//		}
//	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		CommonQuizzes cQ = new CommonQuizzes();
		
		int row = 1;
		
		List<Quiz> tempQuizzesList = new ArrayList<Quiz>();
		
		for (Quiz quiz : cQ.getQuizzes()){
			System.out.println(row++ + ": " + quiz.getSubject());
			tempQuizzesList.add(quiz);
		}
		
		System.out.println("\nSelect quiz: ");
		Scanner scan = new Scanner(System.in);
		int selectedRow = scan.nextInt();
		scan.close();
		cQ.getCommonQuizzes(tempQuizzesList.get(selectedRow - 1));
	}

}

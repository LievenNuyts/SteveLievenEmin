package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.DateQuiz;


/**
 * 
 * @author Steve
 * @version 25/10
 * 
 */

public class QuizCatalog implements Comparable<QuizCatalog>, Cloneable{

	private List<Quiz> quizCatalogs;        


	public QuizCatalog() throws IllegalArgumentException{
		this.setQuizCatalogs(new ArrayList<Quiz>());
	}

	public QuizCatalog(List<Quiz> quizCatalogs) throws IllegalArgumentException{
		this.setQuizCatalogs(quizCatalogs);
	}

	//get & set

	public List<Quiz> getQuizCatalogs(){
		return quizCatalogs;
	}

	public void setQuizCatalogs(List<Quiz> quizCatalogs) throws IllegalArgumentException{
		if (quizCatalogs == null)throw new IllegalArgumentException("Quiz Catalog is null");
		this.quizCatalogs = quizCatalogs;
	}


	//toevoegen

	public void addQuiz(Quiz quiz) throws IllegalArgumentException{
		if (quiz == null)throw new IllegalArgumentException("Quiz is null!");
		for (Quiz qCheck : this.quizCatalogs) {
			if (qCheck.equals(quiz))throw new IllegalArgumentException("Quiz bestaat al!");
		}
		// Set exerciseId
		if (quizCatalogs.size() >0) {
			Quiz tempQuiz = quizCatalogs.get(quizCatalogs.size()-1);
			quiz.setQuizId(tempQuiz.getQuizId()+1);
		}
		else {
			quiz.setQuizId(1);
		}

		quizCatalogs.add(quiz);
	}



	//verwijderen

	public void deleteQuiz(Quiz quiz) throws IllegalArgumentException{
		if (quiz == null)throw new IllegalArgumentException("Quiz is null!");
		quizCatalogs.remove(quiz);
	}

	@Override
	public String toString() {
		String catalog = "";
		for (Quiz quiz : quizCatalogs) {
			catalog += quiz + "\n";
		}
		return catalog;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((quizCatalogs == null) ? 0 : quizCatalogs.hashCode());
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
		QuizCatalog other = (QuizCatalog) obj;
		if (quizCatalogs == null) {
			if (other.quizCatalogs != null)
				return false;
		} else if (!quizCatalogs.equals(other.quizCatalogs))
			return false;
		return true;
	}

	@Override
	public int compareTo(QuizCatalog quizCatalog) {
		return this.getQuizCatalogs().size() - quizCatalog.getQuizCatalogs().size();
	}

	/**
	 * Create a new file named quizzes.txt and adds all quizzes from quizCatalogs list
	 */
	public void writeQuizzesToFile(){
		// Create new file
		File file = new File("src" + File.separator + "files" + File.separator + "quizzes.txt");

		try {
			if (quizCatalogs == null)throw new IllegalArgumentException("quizCatalogs lijst is leeg, er is niets om op te slaan!");

			// Create new writer
			PrintWriter writer = new PrintWriter(file);

			// Loop through exercises
			for (int i = 0;i < quizCatalogs.size();i++){
				Quiz quiz = quizCatalogs.get(i);

				// Line that will be saved in the file per(per exercises)
				String line = 
						quiz.getQuizId() +
						" > " + quiz.getSubject() + " > " + quiz.getLeerJaren() +
						" > " + quiz.getTeacher() + " > " + quiz.getStatus() +
						" > " + quiz.isTest() + " > " + quiz.isUniqueParticipation() +
						" > " + quiz.getDate().getYear() + " / " + quiz.getDate().getMonth() + 
						" / " + quiz.getDate().getDay() + " > ";

				if (quiz.getQuizExercises() != null){
					for (int j = 0; j < quiz.getQuizExercises().size(); j++) {
						line += quiz.getQuizExercises().get(j).getMaxScore() +
								" , " + quiz.getQuizExercises().get(j).getQuiz().getQuizId() +
								" , " + quiz.getQuizExercises().get(j).getExercise().getExerciseId() + " > ";
					}
				}

				writer.println(line);
			}

			// Clone writer
			if (writer !=null)
				writer.close();

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Read quizzes from file and put them in quizCatalogs list
	 */
	public void readQuizzesFromFile(){
		// Get exercises.txt file
		File file = new File("src" + File.separator + "files" + File.separator + "quizzes.txt");

		try{
			// Scan through file
			Scanner scanner = new Scanner(file);

			List<String> tempQuizzes = new ArrayList<String>();

			// Add each line as String object to tempQuizzes list
			while (scanner.hasNextLine()){
				tempQuizzes.add(scanner.nextLine());
			}

			if (scanner!=null){
				scanner.close();
			}

			// Loop through each String object in tempQuizzes
			for (int i = 0; i < tempQuizzes.size(); i++) {
				Scanner scanner2 = new Scanner(tempQuizzes.get(i));
				scanner2.useDelimiter("\\s*>\\s*");

				int quizId = scanner2.nextInt();

				Quiz qz = new Quiz();

				// Add parameters
				qz.setQuizId(quizId);
				qz.setSubject(scanner2.next());
				qz.setLeerJaren(scanner2.nextInt());
				qz.setTeacher(Teacher.valueOf(scanner2.next().toUpperCase()));
				qz.setStatus(QuizStatus.valueOf(scanner2.next().toUpperCase().replaceAll("\\s+","")));
				qz.setTest(scanner2.nextBoolean());
				qz.setUniqueParticipation(scanner2.nextBoolean());

				System.out.println();

				// Scan through scanner2.next() which is date
				Scanner scannerDate = new Scanner(scanner2.next());
				scannerDate.useDelimiter("\\s*/\\s*");
				int year = scannerDate.nextInt();
				int month = scannerDate.nextInt();
				int day = scannerDate.nextInt();
				if (scannerDate!=null){
					scannerDate.close();
				}
				// Add result to dateRegistration parameter
				qz.setDate(new DateQuiz(day, month, year));

				quizCatalogs.add(qz);
				if (scanner2!=null){
					scanner2.close();
				}
			}
		} catch(FileNotFoundException ex){
			System.out.println("Bestand niet gevonden!");
		} catch(IllegalArgumentException ex){
			System.out.println(ex.getMessage());
		}
	}
}

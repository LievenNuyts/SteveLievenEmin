package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import model.EnumerationExercise;
import model.Exercise;
import model.ExerciseCatalog;
import model.MultipleChoiceExercise;
import model.Quiz;
import model.QuizCatalog;
import model.QuizExercise;

/**
 * 
 * @author Steve
 * @version 4/12/2013
 * 
 */

public class TextToSql {

	private QuizCatalog quizModel;
	private ExerciseCatalog exerciseModel;

	/**
	 * Method to convert text based data to Sql data
	 * 
	 * @param quizModel
	 * @param exerciseModel
	 * @throws SQLException
	 */
	private void SendToSql(QuizCatalog quizModel, ExerciseCatalog exerciseModel) throws SQLException{

		this.quizModel = quizModel;
		this.exerciseModel = exerciseModel;

		this.exerciseModel.readExercisesFromFile();
		this.quizModel.readQuizzesFromFile();

		this.exerciseModel.createQuizExercises(exerciseModel.getExercises(),
				quizModel.getQuizCatalogs());

		PreparedStatement pstmt = null;

		Connection conn = null;

		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			//mapping input
			pstmt = conn.prepareStatement("insert into quiz(quiz_id, subject, teacher, date, is_test, is_unique_participation, grades, state) values (?,?,?,?,?,?,?,?)");

			for(Quiz q : quizModel.getQuizCatalogs()){

				pstmt.setInt(1, q.getQuizId());
				pstmt.setString(2, q.getSubject());
				pstmt.setObject(3, q.getTeacher().toString());
			
				String dateToConvert = q.getDate().getDateInEuropeanFormat();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			    java.util.Date date = formatter.parse(dateToConvert);
			    long dateToLong = date.getTime();
			    java.sql.Date finalDate = new Date(dateToLong);
				pstmt.setDate(4, finalDate);
	
				pstmt.setBoolean(5, q.isTest());		
				pstmt.setBoolean(6, q.isUniqueParticipation());
				pstmt.setInt(7, q.getLeerJaren());		
				pstmt.setObject(8, q.getStatus().toString());

				pstmt.executeUpdate();
				conn.commit();
			}
			
			pstmt.close();
			
			pstmt = conn.prepareStatement("insert into exercise(exercise_id, question, author, category, "
					+ "correct_answer, date_registration, descriminator, answer_hints, "
					+ "max_answer_time, max_number_of_attempts) values (?,?,?,?,?,?,?,?,?,?)");
			
			for(Exercise ex : exerciseModel.getExercises()){
				
				pstmt.setInt(1, ex.getExerciseId());
				pstmt.setString(2, ex.getQuestion());
				pstmt.setString(3, ex.getAuthor().toString());
				pstmt.setString(4, ex.getCategory().toString());
				pstmt.setString(5, ex.getCorrectAnswer());
				
				String dateToConvert = ex.getDateRegistration().getDateInEuropeanFormat();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			    java.util.Date date = formatter.parse(dateToConvert);
			    long dateToLong = date.getTime();
			    java.sql.Date finalDate = new Date(dateToLong);
				pstmt.setDate(6,finalDate);

				pstmt.setString(7, String.valueOf(ex.getDiscriminator()));
				
				String tempHints = Arrays.toString(ex.getAnswerHints()).replaceAll("\\[", "").replaceAll("\\]","");;
				pstmt.setString(8, tempHints);
				
				pstmt.setInt(9, ex.getMaxAnswerTime());
				pstmt.setInt(10, ex.getMaxNumberOfAttempts());
				
				pstmt.executeUpdate();
				conn.commit();
				
				if (ex instanceof EnumerationExercise) {
					PreparedStatement pstmt2 = conn.prepareStatement("insert into enumeration_exercise"
							+ "(exercise_id, in_correct_order, number_of_elements) values (?,?,?)");
					
					pstmt2.setInt(1, ex.getExerciseId());
					pstmt2.setBoolean(2, ((EnumerationExercise) ex).getInCorrectOrder());
					pstmt2.setInt(3, ((EnumerationExercise) ex).getNumberOfElements());
					
					pstmt2.executeUpdate();
					conn.commit();
					
					pstmt2.close();
				}
				
				if (ex instanceof MultipleChoiceExercise) {
					PreparedStatement pstmt2 = conn.prepareStatement("insert into multiple_choice_exercise"
							+ "(exercise_id, multiple_choice) values (?,?)");
					
					pstmt2.setInt(1, ex.getExerciseId());
					pstmt2.setString(2, ((MultipleChoiceExercise) ex).getMultipleChoice());
					
					pstmt2.executeUpdate();
					conn.commit();
					
					pstmt2.close();
				}
				
				if (ex.getQuizExercises() != null) {
					PreparedStatement pstmt2 = conn.prepareStatement("insert quiz_exercise"
							+ "(quiz_id, exercise_id, max_score) values (?,?,?)");
					
					
					for (QuizExercise qE : ex.getQuizExercises()) {
						if (qE != null) {
							pstmt2.setInt(1, qE.getQuiz().getQuizId());
							pstmt2.setInt(2, qE.getExercise().getExerciseId());
							pstmt2.setInt(3, qE.getMaxScore());
						}
					}
					
					pstmt2.executeUpdate();
					conn.commit();
					
					pstmt2.close();
				}
			}
			
			pstmt.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	/**
	 * Method to get a connection
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {

		String driver = "org.gjt.mm.mysql.Driver";
		String url = "jdbc:mysql://localhost/quizdb";
		String username = "root";
		String password = "root";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public static void main(String[] args)throws Exception {

		QuizCatalog qc = new QuizCatalog();
		ExerciseCatalog ec = new ExerciseCatalog();

		new TextToSql().SendToSql(qc, ec);
	}
}


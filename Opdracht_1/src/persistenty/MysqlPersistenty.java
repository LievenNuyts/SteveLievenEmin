/**
 * 
 */
package persistenty;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import controller.ChangeQuizController;
import controller.DeleteQuizController;
import model.Exercise;
import model.ExerciseCatalog;
import model.Quiz;
import model.QuizCatalog;
import model.QuizExercise;
import model.QuizStatus;
import model.Teacher;
import view.ChangeQuizView;
import view.CreateQuizView;
import view.DeleteQuizView;

/**
 * 
 * @author Emin
 *
 */
public class MysqlPersistenty implements Persistencable {
	
	public static Connection getConnection() throws Exception {
		String driver = "org.gjt.mm.mysql.Driver";
		String url = "jdbc:mysql://localhost/quizdb";
		String username = "root";
		String password = "root";

		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	@Override
	public void load(ExerciseCatalog exModel, QuizCatalog quModel) {
		try {
			Connection con = getConnection();
			con.setAutoCommit(false);
			
			PreparedStatement ps = con.prepareStatement("select * from exercise");
			ResultSet tempExsSet =  ps.executeQuery();
			
			List<Exercise> tempExs = new ArrayList();
			
			while (tempExsSet.next()) {
				
				
			}
			
//			for (Exercise ex : tempExs) {
//				System.out.println(ex.getQuestion());
//			}
			exModel.setExercises(tempExs);
			
			ps.close();
			
			PreparedStatement ps2 = con.prepareStatement("select * from quiz");
			List<Quiz> tempQus =  (List<Quiz>) ps2.executeQuery();
			quModel.setQuizCatalogs(tempQus);
			
			ps2.close();
			
			PreparedStatement ps3 = con.prepareStatement("select * from quiz_exercise");
			List<QuizExercise> tempQuExs =  (List<QuizExercise>) ps3.executeQuery();
			
			for (QuizExercise qE : tempQuExs) {
				for (Exercise ex : exModel.getExercises()) {
					if (ex.getExerciseId() == qE.getExercise().getExerciseId()) {
						ex.addQuizExercise(qE);
					}
				}
				for (Quiz qu : quModel.getQuizCatalogs()) {
					if (qu.getQuizId() == qE.getQuiz().getQuizId()) {
						qu.addQuizExercise(qE);
					}
				}
			}
			
			ps3.close();
			
		} catch (IllegalArgumentException ex) {
			System.out.println(ex);
		} catch (SQLException ex) {
			System.out.println(ex);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	
	}
	@Override
	public void addQuiz(CreateQuizView view, ExerciseCatalog exModel,
			QuizCatalog quModel) {
		try{
			Connection con = getConnection();
			con.setAutoCommit(false);
			
			if (view.getStatus().equals("Ready")){
				if (view.getDataModel().getRowCount() == 0) 
					throw new IllegalArgumentException("Voegtoe ten minste één opdracht aan de quiz!");
			}
			
			for (Quiz quizCheck : quModel.getQuizCatalogs()) {
				if (quizCheck.getSubject().toLowerCase().equals(view.getSubject().toLowerCase()))
					throw new IllegalArgumentException("Quiz bestaat al!");
			}
			if (view.getSubject() == null) throw new IllegalArgumentException("Onderwerp is null!");
			if (view.getSubject().isEmpty()) throw new IllegalArgumentException("Onderwerp is leeg!");
			
			// Iterate through each row to check maxScore
			for (int i = 0; i < view.getDataModel().getRowCount(); i++) {
				if(!isNumeric(String.valueOf(view.getDataModel().getValueAt(i, 1)))) throw new NumberFormatException("MaxScore moet een numeriek waarde zijn!");
				if(String.valueOf(view.getDataModel().getValueAt(i, 1)).isEmpty()) throw new IllegalArgumentException("MaxScore is leeg!");
			}
			
			Quiz quiz = new Quiz(view.getSubject());
			quiz.setLeerJaren(Integer.parseInt(view.getGrade()));
			quiz.setTeacher(Teacher.valueOf(view.getAuthor().toUpperCase()));
			quiz.setStatus(QuizStatus.valueOf(view.getStatus().toUpperCase().replaceAll("\\s+", "")));
			
			// Iterate through each row and add QuizExercises to corresponding quizzes and exercises
			for (int i = 0; i < view.getDataModel().getRowCount(); i++) {
					for (Exercise ex : exModel.getExercises()){
						if (ex.getQuestion().equals(String.valueOf(view.getDataModel().getValueAt(i, 0)).substring(6))){
							
							QuizExercise tempQE = new QuizExercise(
									Integer.parseInt(String.valueOf(view.getDataModel().getValueAt(i, 1))), 
									quiz, ex);
							
							PreparedStatement ps2 = con.prepareStatement("insert into quiz_exericse values(?,?,?)");
							
							ps2.setInt(1, tempQE.getQuiz().getQuizId());
							ps2.setInt(2, tempQE.getExercise().getExerciseId());
							ps2.setInt(3, tempQE.getMaxScore());
							
							ps2.executeUpdate();
				            con.commit();
				            ps2.close();
				            
							quiz.addQuizExercise(tempQE);
							ex.addQuizExercise(tempQE);
						}
					}
			}
        	
        	PreparedStatement ps = con.prepareStatement("insert into quiz values(?,?,?,?,?,?,?,?)");
        	ps.setInt(1, quiz.getQuizId());
			ps.setString(2, quiz.getSubject());
			ps.setObject(3, quiz.getTeacher().toString());
		
			String dateToConvert = quiz.getDate().getDateInEuropeanFormat();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    java.util.Date date = formatter.parse(dateToConvert);
		    long dateToLong = date.getTime();
		    java.sql.Date finalDate = new Date(dateToLong);
		    ps.setDate(4, finalDate);

		    ps.setBoolean(5, quiz.isTest());		
		    ps.setBoolean(6, quiz.isUniqueParticipation());
		    ps.setInt(7, quiz.getLeerJaren());		
		    ps.setObject(8, quiz.getStatus().toString());
            int i = ps.executeUpdate();
            con.commit();
            if (i != 0){
            	view.displayErrorMessage("Quiz is toegevoegd");
            } 
            else {
            	view.displayErrorMessage("Quiz is niet toegevoegd");
            }
            ps.close();
			
			view.reset();
		}
		catch (NumberFormatException ex) {
			view.displayErrorMessage(ex.getMessage());
		} catch(IllegalArgumentException ex){
			System.out.println(ex);
			view.displayErrorMessage(ex.getMessage());
		} catch (Exception ex) {
			view.displayErrorMessage(ex.getMessage());
		}
		
	}
	
	public void insertEmp(String code, String name, String city, int sal)
    {
        try {
        	Connection con = getConnection();
			con.setAutoCommit(false);
        	
        	PreparedStatement ps = con.prepareStatement("insert into Emp values(?,?,?,?)");
            ps.setString(1, code);
            ps.setString(2, name);
            ps.setString(3, city);
            ps.setInt(4, sal);
            int i = ps.executeUpdate();
            if (i != 0){
              System.out.println("Inserted");
            } 
            else {
              System.out.println("not Inserted");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void deleteQuiz(DeleteQuizView window,
			DeleteQuizController controller, ExerciseCatalog exerciseCatalog,
			QuizCatalog quizCatalog) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public void updateQuiz(ChangeQuizView view, ExerciseCatalog exerciseModel,
			QuizCatalog quizModel, List<Quiz> tempQuizzes,
			List<Exercise> tempExercises) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFromQuiz(ChangeQuizView view, List<Quiz> tempQuizzes,
			List<Exercise> tempExercises) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToQuiz(ChangeQuizView view, List<Quiz> tempQuizzes,
			List<Exercise> tempExercises, ChangeQuizController controller) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Check numeric input
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str)  
	{  
	  try{  
	    int i = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException ex){  
	    return false;  
	  }  
	  return true;  
	}
}

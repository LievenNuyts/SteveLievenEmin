/**
 * 
 */
package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Exercise;
import model.Quiz;
import model.QuizExercise;
import model.SimpleExercise;
import model.Teacher;

import org.junit.Before;
import org.junit.Test;

import utils.DateGC;

/**
 * 
 * @author Emin
 * @version 20/10/2013
 *
 */
public class QuizExerciseTest {
	
	private QuizExercise quizExercise, quizExerciseEqual, quizExerciseNotEqual;
	private Exercise exercise1, exercise2;  
	private Quiz quiz1, quiz2;
	
	@Before
	public void Setup() throws Exception{
		exercise1 = new SimpleExercise(1, "Wat is mijn Voornaam","Emin",new String[]{"kort","4"},2,30,
				Exercise.ExerciseCategory.AARDRIJKSKUNDE,Teacher.BAKKER,new ArrayList<QuizExercise>(),
				new DateGC(2013,10,1), 'S');
		exercise2 = new SimpleExercise(2,"Wat is mijn Naam","Iandyrhanov",new String[]{"kort","4"},2,30,
				Exercise.ExerciseCategory.AARDRIJKSKUNDE,Teacher.BAKKER,new ArrayList<QuizExercise>(),
				new DateGC(2013,10,1), 'S');
		
		quiz1 = new Quiz("Namen",3,false,false);
		quiz2 = new Quiz("Landen",4,false,false);
		
		quizExercise = new QuizExercise(5,quiz1,exercise1);
		quizExerciseEqual = new QuizExercise(5,quiz1,exercise1);
		quizExerciseNotEqual = new QuizExercise(10,quiz2,exercise2);
	}
	
	// Constructor
	
	@Test
	public void test_Constructor_Object_Is_Created() {
		assertEquals(5,quizExercise.getMaxScore());
		assertEquals(quiz1,quizExercise.getQuiz());
		assertEquals(exercise1,quizExercise.getExercise());
	}
	
	// setMaxScore
	
	@Test
	public void test_setMaxScore_Valid_Value_Is_Accepted() {
		quizExercise.setMaxScore(15);
		assertEquals(15,quizExercise.getMaxScore());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setMaxScore_Exception_If_Parameter_Is_Less_Then_1() {
		quizExercise.setMaxScore(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setMaxScore_Exception_If_Parameter_Is_Negative() {
		quizExercise.setMaxScore(-1);
	}
	
	// setQuiz
	
	@Test
	public void test_setQuiz_Valid_Value_Is_Accepted() {
		quizExercise.setQuiz(quiz2);
		assertEquals(quiz2,quizExercise.getQuiz());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setQuiz_Exception_If_Parameter_Is_Null() {
		quizExercise.setQuiz(null);
	}
	
	// setExercise 
	
	@Test
	public void test_setExercise_Valid_Value_Is_Accepted() {
		quizExercise.setExercise(exercise2);
		assertEquals(exercise2,quizExercise.getExercise());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setExercise_Exception_If_Paramter_Is_Null() {
		quizExercise.setExercise(null);
	}
	
	// Equals
	
		@Test
		public void test_Equals_True_If_QuizExercises_Equal() {
			assertTrue(quizExerciseEqual.equals(quizExercise));
			assertTrue(quizExercise.equals(quizExerciseEqual));
		}
		
		@Test
		public void test_Equals_False_If_QuizExercises_Not_Equal() {
			assertFalse(quizExerciseEqual.equals(quizExerciseNotEqual));
		}
		
		// hashCode
		
		@Test
		public void test_Equals_True_If_HashCode_Equal() {
			assertTrue(quizExercise.hashCode() == quizExerciseEqual.hashCode());
		}
		
		@Test
		public void test_Equals_False_If_HashCode_Not_Equal() {
			assertFalse(quizExerciseEqual.hashCode() == quizExerciseNotEqual.hashCode());
		}
		
		// compareTo
		
		@Test
		public void test_Equals_True_If_Quizzes_And_Exercises_Equal() {
			assertTrue(quizExercise.compareTo(quizExerciseEqual) == quizExerciseEqual.compareTo(quizExercise));
		}
		
		@Test
		public void test_Equals_False_If_Quizzes_And_Exercises_Not_Equal() {
			assertFalse(quizExerciseEqual.compareTo(quizExerciseNotEqual) == quizExerciseNotEqual.compareTo(quizExerciseEqual));
		}
}

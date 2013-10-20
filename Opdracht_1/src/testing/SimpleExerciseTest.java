package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utils.DateGC;
import model.SimpleExercise;
import model.Teacher;
import model.Exercise;
import model.Quiz;

/**
 * 
 * @author Emin
 * @version 9/10/2013
 *
 */
public class SimpleExerciseTest {
	
	private Exercise exercise, exerciseEqual, exerciseNotEqual;
	
	@Before
	public void setUp() throws Exception{
		List<Quiz> quizzesList = new ArrayList<Quiz>();
		quizzesList.add(new Quiz("whatabout",1,true,true));
		
		exercise = new SimpleExercise("Hoofdstad van België?","Brussel",new String[]{"Stad","Centrum"},
				1,30,Exercise.ExerciseCategory.AARDRIJKSKUNDE,Teacher.BAKKER,quizzesList,new DateGC(2013,10,1));
		exerciseEqual = new SimpleExercise("Hoofdstad van België?","Brussel",new String[]{"Stad","Centrum"},
				1,30,Exercise.ExerciseCategory.AARDRIJKSKUNDE,Teacher.BAKKER,quizzesList,new DateGC(2013,10,1));
		exerciseNotEqual = new SimpleExercise("Hoofdstad van Spanje?","Madrid",new String[]{"Stad","Centrum"},
				2,40,Exercise.ExerciseCategory.AARDRIJKSKUNDE,Teacher.BAKKER,quizzesList,new DateGC(2013,10,1));
	}
	
	// Constructor
	
	@Test
	public void test_Constructor_Object_Is_Created() {
		List<Quiz> quizzesList = new ArrayList<Quiz>();
		quizzesList.add(new Quiz("whatabout",1,true,true));
		
		assertEquals("Hoofdstad van België?",exercise.getQuestion());
		assertEquals("Brussel",exercise.getCorrectAnswer());
		assertArrayEquals(new String[]{"Stad","Centrum"},exercise.getAnswerHints());
		assertEquals(1,exercise.getMaxNumberOfAttempts());
		assertEquals(30,exercise.getMaxAnswerTime());
		assertEquals(Exercise.ExerciseCategory.AARDRIJKSKUNDE,exercise.getCategory());
		assertEquals(Teacher.BAKKER,exercise.getAuthor());
		assertEquals(quizzesList,exercise.getQuizzes());
		assertEquals(new DateGC(2013,10,1),exercise.getDateRegistration());
	}

	// setQuestion
	
	@Test
	public void test_setQuestion_Valid_Value_Is_Accepted() {
		exercise.setQuestion("Hoofdstad van Nederland?");
		assertEquals("Hoofdstad van Nederland?",exercise.getQuestion());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setQuestion_Exception_If_Parameter_Null() {
		exercise.setQuestion(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setQuestion_Exception_If_Parameter_Is_Empty() {
		exercise.setQuestion("");
	}
	
	// setCorrectAnswer
	
	@Test
	public void test_setCorrectAnswer_Valid_Value_Is_Accepted() {
		exercise.setCorrectAnswer("Amsterdam");
		assertEquals("Amsterdam",exercise.getCorrectAnswer());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setCorrectAnswer_Exception_If_Parameter_Null() {
		exercise.setCorrectAnswer(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setCorrectAnswer_Exception_If_Parameter_Is_Empty() {
		exercise.setCorrectAnswer("");
	}
	
	// setCategorie
	
	@Test
	public void test_setCategorie_Valid_Value_Is_accepted() {
		exercise.setCategory(Exercise.ExerciseCategory.WETENSCHAPPEN);
		assertEquals(Exercise.ExerciseCategory.WETENSCHAPPEN,exercise.getCategory());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setCategorie_Exception_If_Parameter_Is_Null() {
		exercise.setCategory(null);
	}
	
	// setAuteur
	
	@Test
	public void test_setAuthor_Valid_Value_Is_Accepted() {
		exercise.setAuthor(Teacher.JACOBS);
		assertEquals(Teacher.JACOBS,exercise.getAuthor());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setAuthor_Exception_If_Parameter_Is_Null() {
		exercise.setAuthor(null);
	}
	
	// setAnswerHints
	
	@Test
	public void test_setAnswerHints_Valid_Value_Is_Accepted() {
		exercise.setAnswerHints(new String[]{"Groot, Parlament"});
		assertArrayEquals(new String[]{"Groot, Parlament"},exercise.getAnswerHints());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setAnswerHints_Exception_If_Paramter_Is_Null() {
		exercise.setAnswerHints(null);
	}
	
	// setQuizzen
	
	@Test
	public void test_setQuizzes_Valid_Value_Is_Accepted() {
		exercise.setQuizzes(new ArrayList<Quiz>());
		assertEquals(new ArrayList<Quiz>(),exercise.getQuizzes());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setQuizzes_Exception_If_Parameter_Is_Null() {
		exercise.setQuizzes(null);
	}
	
	// setMaxNumberOfAttempts
	
	@Test
	public void test_setMaxNumberOfAttempts_Valid_Value_Is_Accepted() {
		exercise.setMaxNumberOfAttempts(2);
		assertEquals(2,exercise.getMaxNumberOfAttempts());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setMaxNumberOfAttempts_Exception_If_Parameter_Is_Negative() {
		exercise.setMaxNumberOfAttempts(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setMaxNumberOfAttempts_Exception_If_Parameter_Is_0() {
		exercise.setMaxNumberOfAttempts(0);
	}
	
	// setMaxAnswerTime
	
	@Test
	public void test_setMaxAnswerTime_Valid_Value_Is_Accepted() {
		exercise.setMaxAnswerTime(40);
		assertEquals(40,exercise.getMaxAnswerTime());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setMaxAnswerTime_Exception_If_Parameter_Is_Negative() {
		exercise.setMaxAnswerTime(-10);
	}
	
	// setDatumRegistratie
	
	@Test
	public void test_setDateRegistration_Valid_Value_Is_Accepted() {
		exercise.setDateRegistration(new DateGC());
		assertEquals(new DateGC(), exercise.getDateRegistration());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setDateRegistration_Exception_If_Parameter_Is_Null() {
		exercise.setDateRegistration(null);
	}
	
	// equals
	
	@Test
	public void test_Equals_True_If_Exercises_Equal() {
		assertTrue(exerciseEqual.equals(exercise));
		assertTrue(exercise.equals(exerciseEqual));
	}
	
	@Test
	public void test_Equals_False_If_Exercises_Not_Equal() {
		assertFalse(exerciseEqual.equals(exerciseNotEqual));
	}
	
	// hashCode
	
	@Test
	public void test_Equals_True_If_HashCodes_Equal() {
		assertTrue(exercise.hashCode() == exerciseEqual.hashCode());
	}
	
	@Test
	public void test_Equals_False_If_HashCodes_Not_Equal() {
		assertFalse(exerciseEqual.hashCode() == exerciseNotEqual.hashCode());
	}
	
	// compareTo
	
	@Test
	public void test_Equals_True_If_Questions_Equal() {
		assertTrue(exercise.compareTo(exerciseEqual) == exerciseEqual.compareTo(exercise));
	}
	
	@Test
	public void test_Equals_False_If_Questions_Not_Equal() {
		assertFalse(exerciseEqual.compareTo(exerciseNotEqual) == exerciseNotEqual.compareTo(exerciseEqual));
	}
	
	// isCorrectAnswer
	
	@Test
	public void test_Equals_True_If_Answers_Equal() {
		assertTrue(exercise.isCorrectAnswer("Brussel"));
	}
	
	@Test
	public void test_Equals_False_If_Answers_Not_Equal() {
		assertFalse(exercise.isCorrectAnswer("Leuven"));
	}
}

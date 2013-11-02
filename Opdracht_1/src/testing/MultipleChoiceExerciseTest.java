package testing;

/**
 * 
 * @author Steve
 * @version 1/11/2013
 * 
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.Exercise;
import model.MultipleChoiceExercise;
import model.Quiz;
import model.QuizExercise;
import model.Teacher;

import org.junit.Before;
import org.junit.Test;

import utils.DateGC;

public class MultipleChoiceExerciseTest {

	private MultipleChoiceExercise exercise;
	
	private String correctAnswer;
	private String incorrectAnswer;
	private String multipleChoice;

	
	@Before
	public void setUp() throws Exception{
		List<QuizExercise> quizExercisesList = new ArrayList<QuizExercise>();
		quizExercisesList.add(new QuizExercise(2, new Quiz(), new MultipleChoiceExercise()));
		
		exercise = new MultipleChoiceExercise(4, "Which day follows friday?","Saturday", new String[]{"First day of the weekend.","Named after Saturn."},
				1, 30, Exercise.ExerciseCategory.NEDERLANDS, Teacher.BAKKER, quizExercisesList,
				new DateGC(2013,10,20), 'E', "1.2.3.4");
		
		
		correctAnswer = "Saturday";
		incorrectAnswer = "Monday";
		multipleChoice = "1.2.3.4";
	}
	
	
	@Test
	public void test_Constructor_Object_Is_Created() {
		List<QuizExercise> quizExercisesList = new ArrayList<QuizExercise>();
		quizExercisesList.add(new QuizExercise(2, new Quiz(), new MultipleChoiceExercise()));
		
		assertEquals("Which day follows friday?", exercise.getQuestion());
		assertEquals("Saturday",exercise.getCorrectAnswer());
		assertArrayEquals(new String[]{"First day of the weekend.","Named after Saturn."}, exercise.getAnswerHints());
		assertEquals(1, exercise.getMaxNumberOfAttempts());
		assertEquals(30, exercise.getMaxAnswerTime());
		assertEquals(Exercise.ExerciseCategory.NEDERLANDS, exercise.getCategory());
		assertEquals(Teacher.BAKKER, exercise.getAuthor());
		assertEquals(quizExercisesList, exercise.getQuizExercises());
		assertEquals(new DateGC(2013,10,20), exercise.getDateRegistration());
		assertEquals('E', exercise.getDiscriminator());
		assertEquals(multipleChoice, exercise.getMultipleChoice());
	}

	//Setters
	
	//setExercise ID
	
	@Test
	public void test_setExerciseId_Valid_Value_Is_Accepted() {
		exercise.setExerciseId(5);
		assertEquals(5, exercise.getExerciseId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setExerciseId_Exception_If_Parameter_Is_Less_Than_1() {
		exercise.setExerciseId(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setExerciseId_Exception_If_Parameter_Is_Negative() {
		exercise.setExerciseId(-1);
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
	
	//setAnswerHints
	
	@Test
	public void test_setAnswerHints_Valid_Value_Is_Accepted() {
		exercise.setAnswerHints(new String[]{"Groot, Parlement"});
		assertArrayEquals(new String[]{"Groot, Parlement"},exercise.getAnswerHints());
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void test_setAnswerHints_Exception_If_Paramter_Is_Null() {
		exercise.setAnswerHints(null);
	}
	
	//setQuizen
	
	@Test
	public void test_setQuizExercises_Valid_Value_Is_Accepted() {
		exercise.setQuizExercises(new ArrayList<QuizExercise>());
		assertEquals(new ArrayList<QuizExercise>(),exercise.getQuizExercises());
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void test_setQuizExercises_Exception_If_Parameter_Is_Null() {
		exercise.setQuizExercises(null);
	}
		
	//setMaxNumberOfAttempts
		
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
		
	//setMaxAnswerTime
	@Test
	public void test_setMaxAnswerTime_Valid_Value_Is_Accepted() {
		exercise.setMaxAnswerTime(40);
		assertEquals(40,exercise.getMaxAnswerTime());
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void test_setMaxAnswerTime_Exception_If_Parameter_Is_Negative() {
		exercise.setMaxAnswerTime(-10);
	}
	
	//setDatumRegistratie
	@Test
	public void test_setDateRegistration_Valid_Value_Is_Accepted() {
		exercise.setDateRegistration(new DateGC());
		assertEquals(new DateGC(), exercise.getDateRegistration());
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void test_setDateRegistration_Exception_If_Parameter_Is_Null() {
		exercise.setDateRegistration(null);
	}
		
	//setDiscriminator	
	@Test
	public void test_setDiscriminator_Valid_Value_Is_Accepted() {
		exercise.setDiscriminator('E');
		assertEquals('E', exercise.getDiscriminator());
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void test_setDisciriminator_Exception_If_Parameter_Is_Invalide() {
		exercise.setDiscriminator('A');
	}
	
	//tests for isCorrectAnswer(String answer)
	@Test
	public void test_isCorrectAnswer_correctAnswer_returnsTrue(){
		assertTrue(exercise.isCorrectAnswer(this.correctAnswer));
	}
	
	@Test
	public void test_isCorrectAnswer_inCorrectAnswer_returnsFalse(){
		assertFalse(exercise.isCorrectAnswer(this.incorrectAnswer));
	}

	
	@Test (expected = IllegalArgumentException.class)
	public void test_isCorrectAnswer_invalidInput_throwsException(){
		exercise.isCorrectAnswer(null);
	}
	
	//test for isValide(String answer)
	public void test_isValide_validCorrectAnswer_returnsTrue(){
		assertTrue(exercise.isValide(this.correctAnswer));
	}
	
	public void test_isValide_validInCorrectAnswer_returnsTrue(){
		assertTrue(exercise.isValide(this.incorrectAnswer));
	}
}
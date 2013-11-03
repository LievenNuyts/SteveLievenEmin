package testing;

/**
 *  
 * @author Lieven
 * @version 19/10/2013
 * 
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.EnumerationExercise;
import model.Exercise;
import model.Quiz;
import model.QuizExercise;
import model.SimpleExercise;
import model.Teacher;

import org.junit.Before;
import org.junit.Test;

import utils.DateGC;


public class EnumerationExerciseTest {

	
	private EnumerationExercise exercise, exerciseEqual, exerciseNotEqual;
	private List<String> testSplitCorrectAnswer;
	
	private String correctAnswer;
	private String inCorrectAnswer;
	private String reverseOrderAnswer;
	private String onlyOneAnswer;
	@SuppressWarnings("unused")
	private String duplicatesAnswer;
	private String badFormattedAnswer;
	
	@Before
	public void setUp() throws Exception{
		List<QuizExercise> quizExercisesList = new ArrayList<QuizExercise>();
		quizExercisesList.add(new QuizExercise(2, new Quiz(), new EnumerationExercise()));
		
		//initializeren van de te testen enumExercises
		exercise = new EnumerationExercise(3, "Geef een opsomming van de dagen van de week.",
				"maandag;dinsdag;woensdag;donderdag;vrijdag;zaterdag;zondag",new String[]{"-dag","7"},
				1,30,Exercise.ExerciseCategory.NEDERLANDS,Teacher.BAKKER,quizExercisesList,
				new DateGC(2013,10,20), 'E', true);
		
		exerciseEqual = new EnumerationExercise(3, "Geef een opsomming van de dagen van de week.",
				"maandag;dinsdag;woensdag;donderdag;vrijdag;zaterdag;zondag",new String[]{"-dag","7"},
				1,30,Exercise.ExerciseCategory.NEDERLANDS,Teacher.BAKKER,quizExercisesList,
				new DateGC(2013,10,20), 'E', true);
		
		exerciseNotEqual = new EnumerationExercise(2, "Geef de maanden in een jaar.",
				"januari;februari;maart;april;mei;juni;juli;augustus;september;oktober;november;december",new String[]{"-dag","7"},
				1,30,Exercise.ExerciseCategory.NEDERLANDS,Teacher.BAKKER,quizExercisesList,
				new DateGC(2013,10,20), 'E', false);
		
		testSplitCorrectAnswer = Arrays.asList("maandag","dinsdag","woensdag","donderdag","vrijdag","zaterdag","zondag");
		
		//initializeren verschillende antwoorden
		correctAnswer = "maandag;dinsdag;woensdag;donderdag;vrijdag;zaterdag;zondag";
		inCorrectAnswer = "maandag;king kong;woensdag;vader Abraham;vrijdag;zaterdag;zondag";
		reverseOrderAnswer = "dinsdag;maandag;woensdag;donderdag;vrijdag;zondag;zaterdag";
		onlyOneAnswer = "dinsdag";
		duplicatesAnswer = "maandag;maandag;woensdag;donderdag;donderdag;zaterdag;zondag";
		badFormattedAnswer = "maandag,dinsdag,woensdag,donderdag,vrijdag,zaterdag,zondag";
	}
	
	
	@Test
	public void test_Constructor_Object_Is_Created() {
		List<QuizExercise> quizExercisesList = new ArrayList<QuizExercise>();
		quizExercisesList.add(new QuizExercise(2, new Quiz(), new EnumerationExercise()));
		
		assertEquals("Geef een opsomming van de dagen van de week.",exercise.getQuestion());
		assertEquals("maandag;dinsdag;woensdag;donderdag;vrijdag;zaterdag;zondag",exercise.getCorrectAnswer());
		assertArrayEquals(new String[]{"-dag","7"},exercise.getAnswerHints());
		assertEquals(1,exercise.getMaxNumberOfAttempts());
		assertEquals(30,exercise.getMaxAnswerTime());
		assertEquals(Exercise.ExerciseCategory.NEDERLANDS,exercise.getCategory());
		assertEquals(Teacher.BAKKER,exercise.getAuthor());
		assertEquals(quizExercisesList,exercise.getQuizExercises());
		assertEquals(new DateGC(2013,10,20),exercise.getDateRegistration());
		
		assertEquals(7, exercise.getNumberOfElements());
		assertEquals(testSplitCorrectAnswer, exercise.getSplitCorrectAnswer());
		
		assertEquals('E', exercise.getDiscriminator());
		assertEquals(true, exercise.getInCorrectOrder());
	}

	//TESTS FOR SETTERS
	// setExerciseId
	@Test
	public void test_setExerciseId_Valid_Value_Is_Accepted() {
		exercise.setExerciseId(3);
		assertEquals(3, exercise.getExerciseId());
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
		assertFalse(exercise.isCorrectAnswer(this.inCorrectAnswer));
	}
	
	@Test
	public void test_isCorrectAnswer_reverseOrderAnswer_returnsTrue(){
		assertTrue(exercise.isCorrectAnswer(this.reverseOrderAnswer));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_isCorrectAnswer_invalidInput_throwsException(){
		exercise.isCorrectAnswer(null);
	}
	
	//tests for isCorrectOrder(String answer)
	@Test
	public void test_isCorrectOrder_correctAnswer_returnsTrue(){
		assertTrue(exercise.inCorrectOrderCheck(this.correctAnswer));
	}
	
	@Test
	public void test_isCorrectOrder_inCorrectAnswer_returnsFalse(){
		assertFalse(exercise.inCorrectOrderCheck(this.inCorrectAnswer));
	}
		
	@Test
	public void test_isCorrectOrder_reverseOrderAnswer_returnsFalse(){
		assertFalse(exercise.inCorrectOrderCheck(this.reverseOrderAnswer));
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void test_isCorrectOrder_invalidInput_throwsException(){
		exercise.inCorrectOrderCheck(null);
	}
	
	//test for isValide(String answer)
	@Test
	public void test_isValide_validCorrectAnswer_returnsTrue(){
		assertTrue(exercise.isValide(this.correctAnswer));
	}
	
	@Test
	public void test_isValide_validInCorrectAnswer_returnsTrue(){
		assertTrue(exercise.isValide(this.inCorrectAnswer));
	}
	
	@Test
	public void test_isValide_inValidAnswer_returnsFalse(){
		assertFalse(exercise.isValide(this.badFormattedAnswer));
	}
	
	@Test
	public void test_isValide_singleAnswer_returnsFalse(){
		assertFalse(exercise.isValide(this.onlyOneAnswer));
	}	
	
	//test for equals
	
	@Test
	public void test_Equals_True_If_Exercises_Equal() {
		assertTrue(exerciseEqual.equals(exercise));
		assertTrue(exercise.equals(exerciseEqual));
	}
	
	@Test
	public void test_Equals_False_If_Exercises_Not_Equal() {
		assertFalse(exerciseEqual.equals(exerciseNotEqual));
	}
	
	
	//test for hashcode
	@Test
	public void test_Equals_True_If_HashCodes_Equal() {
		assertTrue(exercise.hashCode() == exerciseEqual.hashCode());
	}
	
	@Test
	public void test_Equals_False_If_HashCodes_Not_Equal() {
		assertFalse(exerciseEqual.hashCode() == exerciseNotEqual.hashCode());
	}
	
	
	//Test for getValidateText()
	@Test
	public void test_getValidateText_returns_correct_String(){
		
		String textToCheck = "Gelieve de antwoorden in de juiste volgorde en gescheiden door een ; in te geven. \nDit antwoord bevat 7 elementen.";
		
		assertEquals(textToCheck, exercise.getValidateText());
	}
		
	//test for clone
	
}

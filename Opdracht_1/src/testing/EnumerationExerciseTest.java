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

	
	private EnumerationExercise exercise;
	private List<String> testSplitCorrectAnswer;
	
	private String correctAnswer;
	private String inCorrectAnswer;
	private String reverseOrderAnswer;
	private String onlyOneAnswer;
	private String duplicatesAnswer;
	private String badFormattedAnswer;
	
	@Before
	public void setUp() throws Exception{
		List<QuizExercise> quizExercisesList = new ArrayList<QuizExercise>();
		quizExercisesList.add(new QuizExercise(2, new Quiz(), new SimpleExercise()));
		
		exercise = new EnumerationExercise(3, "Geef een opsomming van de dagen van de week.",
				"maandag;dinsdag;woensdag;donderdag;vrijdag;zaterdag;zondag",new String[]{"-dag","7"},
				1,30,Exercise.ExerciseCategory.NEDERLANDS,Teacher.BAKKER,quizExercisesList,
				new DateGC(2013,10,20), 'E', true);
		
		testSplitCorrectAnswer = Arrays.asList("maandag","dinsdag","woensdag","donderdag","vrijdag","zaterdag","zondag");
		
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

	//tests for isCorrectAnswer(String answer)
	@Test
	public void test_isCorrectAnswer_correctAnswer_returnsTrue(){
		assertTrue(exercise.isCorrectAnswer(this.correctAnswer));
	}
	
	@Test
	public void test_isCorrectAnswer_inCorrectAnswer_returnsFalse(){
		assertTrue(exercise.isCorrectAnswer(this.inCorrectAnswer));
	}
	
	@Test
	public void test_isCorrectAnswer_reverseOrderAnswer_returnsTrue(){
		assertTrue(exercise.isCorrectAnswer(this.reverseOrderAnswer));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_isCorrectAnswer_invalidInput_throwsException(){
		assertTrue(exercise.isCorrectAnswer(null));
	}
	
	//tests for isCorrectOrder(String answer)
	@Test
	public void test_isCorrectOrder_correctAnswer_returnsTrue(){
		assertTrue(exercise.inCorrectOrderCheck(this.correctAnswer));
	}
	
	@Test
	public void test_isCorrectOrder_inCorrectAnswer_returnsFalse(){
		assertTrue(exercise.inCorrectOrderCheck(this.inCorrectAnswer));
	}
		
	@Test
	public void test_isCorrectOrder_reverseOrderAnswer_returnsFalse(){
		assertTrue(exercise.inCorrectOrderCheck(this.reverseOrderAnswer));
	}
		
	@Test (expected = IllegalArgumentException.class)
	public void test_isCorrectOrder_invalidInput_throwsException(){
		assertTrue(exercise.inCorrectOrderCheck(null));
	}
	
	//test for isValide(String answer)
	public void test_isValide_validCorrectAnswer_returnsTrue(){
		assertTrue(exercise.isValide(this.correctAnswer));
	}
	
	public void test_isValide_validInCorrectAnswer_returnsTrue(){
		assertTrue(exercise.isValide(this.inCorrectAnswer));
	}
	
	public void test_isValide_inValidAnswer_returnsFalse(){
		assertTrue(exercise.isValide(this.badFormattedAnswer));
	}
	
	public void test_isValide_singleAnswer_returnsTrue(){
		assertTrue(exercise.isValide(this.onlyOneAnswer));
	}	
}

/**
 * 
 */
package testing;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import model.QuizExercise;
import model.SimpleExercise;
import model.Teacher;
import model.Exercise;
import model.ExerciseCatalog;

import org.junit.Before;
import org.junit.Test;

import utils.DateGC;

/**
 * 
 * @author Emin
 * @version 10/10/2013
 *
 */
public class ExerciseCatalogTest {

	private ExerciseCatalog catalog,catalogEqual,catalogNotEqual;
	private List<Exercise> exercises;
	private Exercise exercise1;
	private Exercise exercise2;
	private Exercise exercise3;
	
	@Before
	public void setUp() throws Exception {
		
		exercises = new ArrayList<Exercise>();
		
		exercise1 = new SimpleExercise(1, "Wat is mijn Voornaam","Emin",new String[]{"kort","4"},2,30,
				Exercise.ExerciseCategory.AARDRIJKSKUNDE,Teacher.BAKKER,new ArrayList<QuizExercise>(),
				new DateGC(2013,10,1), 'S');
		exercise2 = new SimpleExercise(2, "Wat is mijn Naam","Iandyrhanov",new String[]{"kort","4"},2,30,
				Exercise.ExerciseCategory.AARDRIJKSKUNDE,Teacher.BAKKER,new ArrayList<QuizExercise>(),
				new DateGC(2013,10,1), 'S');
		exercise3 = new SimpleExercise(3, "Hoofdstad van België?","Brussel",new String[]{"kort","4"},2,30,
				Exercise.ExerciseCategory.AARDRIJKSKUNDE,Teacher.BAKKER,new ArrayList<QuizExercise>(),
				new DateGC(2013,10,1), 'S');
		
		exercises.add(exercise1);
		
		catalog = new ExerciseCatalog(exercises);
		catalogEqual = new ExerciseCatalog(exercises);
		catalogNotEqual = new ExerciseCatalog();
	}

	// Constructor
	
	@Test
	public void test_Constructor_Object_Is_Created() {
		assertEquals(exercises,catalog.getExercises());
	}
	
	// setOpdrachten
	
	@Test
	public void test_setExercises_Valid_Value_Is_Accepted() {
		exercises = new ArrayList<Exercise>();
		exercises.add(exercise2);
		
		catalog.setExercises(exercises);
		assertEquals(exercises,catalog.getExercises());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setExercises_Exception_If_Parameter_Is_Null() {
		catalog.setExercises(null);
	}
	
	// addExercise
	
	@Test
	public void test_addExercise_Valid_Value_Is_Accepted() {
		catalog.addExercise(exercise3);
		
		assertEquals(exercises.size(),catalog.getExercises().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_addExercise_Exception_If_Parameter_Is_Null() {
		catalog.addExercise(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_addExercise_Exception_If_Parameter_Exists() {
		catalog.addExercise(exercise1);
	}
	
	// removeExercise
	
	@Test
	public void test_removeExercise_Valid_Value_Is_Accepted() {
		catalog.removeExercise(exercise1);
		
		assertEquals(exercises.size(),catalog.getExercises().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_removeExercise_Exception_If_Parameter_Is_Null() {
		catalog.removeExercise(null);
	}
	
	// updateExercise
	
	@Test
	public void test_updateExercise_Valid_Value_Is_Accepted() {
		catalog.updateExercise(exercise1,exercise2);
		
		assertEquals(catalog.getExercises().get(0),exercise2);
	}
	
	// writeExercisesToFile
	
	@Test(expected = IllegalArgumentException.class)
	public void test_writeExercisesToFile_If_Exercises_Is_Empty() {
		catalog.setExercises(null);
		catalog.writeExercisesToFile();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_updateExercise_Exception_If_oldExercise_Is_Null() {
		catalog.updateExercise(null,exercise2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_updateExercise_Exception_If_newExercise_Is_Null() {
		catalog.updateExercise(exercise1,null);
	}
	
	// equals
	
	@Test
	public void test_Equals_True_If_Catalog_Equal() {
		assertTrue(catalogEqual.equals(catalog));
		assertTrue(catalog.equals(catalogEqual));
	}
	
	@Test
	public void test_Equals_False_If_Catalog_Not_Equal() {
		assertFalse(catalogEqual.equals(catalogNotEqual));
	}
	
	// hashCode
	
	@Test
	public void test_Equals_True_If_HashCodes_Equal() {
		assertTrue(catalog.hashCode() == catalogEqual.hashCode());
	}
	
	@Test
	public void test_Equals_False_If_HashCodes_Not_Equal() {
		assertFalse(catalogEqual.hashCode() == catalogNotEqual.hashCode());
	}
	
	// compareTo
	
	@Test
	public void test_Equals_True_If_Questions_Equal() {
		assertTrue(catalog.compareTo(catalogEqual) == catalogEqual.compareTo(catalog));
	}
	
	@Test
	public void test_Equals_False_If_Questions_Not_Equal() {
		assertFalse(catalogEqual.compareTo(catalogNotEqual) == catalogNotEqual.compareTo(catalogEqual));
	}
}

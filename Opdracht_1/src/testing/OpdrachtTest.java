package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import utils.DatumGC;
import model.Leraar;
import model.Opdracht;
import model.Quiz;

/**
 * 
 * @author Emin
 * @version 9/10/2013
 *
 */
public class OpdrachtTest {
	
	private Opdracht opdracht, opdrachtGelijk, opdrachtNietGelijk;
	
	@Before
	public void setUp() throws Exception{
		opdracht = new Opdracht("Hoofdstad van België?","Brussel",new String[]{"Stad","Centrum"},
				1,30,Opdracht.OpdrachtCategorie.ALGEMENEKENNIS,Leraar.BAKKER,new ArrayList<Quiz>(),new DatumGC());
		opdrachtGelijk = new Opdracht("Hoofdstad van België?","Brussel",new String[]{"Stad","Centrum"},
				1,30,Opdracht.OpdrachtCategorie.ALGEMENEKENNIS,Leraar.BAKKER,new ArrayList<Quiz>(),new DatumGC());
		opdrachtNietGelijk = new Opdracht("Hoofdstad van Spanje?","Madrid",new String[]{"Stad","Centrum"},
				2,40,Opdracht.OpdrachtCategorie.ALGEMENEKENNIS,Leraar.BAKKER,new ArrayList<Quiz>(),new DatumGC());
	}
	
	// Constructor
	
	@Test
	public void test_Constructor_Object_Is_Created() {
		assertEquals("Hoofdstad van België?",opdracht.getQuestion());
		assertEquals("Brussel",opdracht.getCorrectAnswer());
		assertArrayEquals(new String[]{"Stad","Centrum"},opdracht.getAnswerHints());
		assertEquals(1,opdracht.getMaxNumberOfAttempts());
		assertEquals(30,opdracht.getMaxAnswerTime());
		assertEquals(Opdracht.OpdrachtCategorie.ALGEMENEKENNIS,opdracht.getCategorie());
		assertEquals(Leraar.BAKKER,opdracht.getAuteur());
	}

	// setQuestion
	
	@Test
	public void test_setQuestion_Valid_Value_Is_Accepted() {
		opdracht.setQuestion("Hoofdstad van Nederland?");
		assertEquals("Hoofdstad van Nederland?",opdracht.getQuestion());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setQuestion_Exception_If_Parameter_Null() {
		opdracht.setQuestion(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setQuestion_Exception_If_Parameter_Is_Empty() {
		opdracht.setQuestion("");
	}
	
	// setCorrectAnswer
	
	@Test
	public void test_setCorrectAnswer_Valid_Value_Is_Accepted() {
		opdracht.setCorrectAnswer("Amsterdam");
		assertEquals("Amsterdam",opdracht.getCorrectAnswer());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setCorrectAnswer_Exception_If_Parameter_Null() {
		opdracht.setCorrectAnswer(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setCorrectAnswer_Exception_If_Parameter_Is_Empty() {
		opdracht.setCorrectAnswer("");
	}
	
	// setCategorie
	
	@Test
	public void test_setCategorie_Valid_Value_Is_accepted() {
		opdracht.setCategorie(Opdracht.OpdrachtCategorie.FRANSETAAL);
		assertEquals(Opdracht.OpdrachtCategorie.FRANSETAAL,opdracht.getCategorie());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setCategorie_Exception_If_Parameter_Is_Null() {
		opdracht.setCategorie(null);
	}
	
	// setAuteur
	
	@Test
	public void test_setAuteur_Valid_Value_Is_Accepted() {
		opdracht.setAuteur(Leraar.JACOBS);
		assertEquals(Leraar.JACOBS,opdracht.getAuteur());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setAuteur_Exception_If_Parameter_Is_Null() {
		opdracht.setAuteur(null);
	}
	
	// setAnswerHints
	
	@Test
	public void test_setAnswerHints_Valid_Value_Is_Accepted() {
		opdracht.setAnswerHints(new String[]{"Groot, Parlament"});
		assertArrayEquals(new String[]{"Groot, Parlament"},opdracht.getAnswerHints());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setAnswerHints_Exception_If_Paramter_Is_Null() {
		opdracht.setAnswerHints(null);
	}
	
	// setQuizzen
	
	@Test
	public void test_setQuizzen_Valid_Value_Is_Accepted() {
		opdracht.setQuizzen(new ArrayList<Quiz>());
		assertEquals(new ArrayList<Quiz>(),opdracht.getQuizzen());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setQuizzen_Exception_If_Parameter_Is_Null() {
		opdracht.setQuizzen(null);
	}
	
	// setMaxNumberOfAttempts
	
	@Test
	public void test_setMaxNumberOfAttempts_Valid_Value_Is_Accepted() {
		opdracht.setMaxNumberOfAttempts(2);
		assertEquals(2,opdracht.getMaxNumberOfAttempts());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setMaxNumberOfAttempts_Exception_If_Parameter_Is_Negative() {
		opdracht.setMaxNumberOfAttempts(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setMaxNumberOfAttempts_Exception_If_Parameter_Is_0() {
		opdracht.setMaxNumberOfAttempts(0);
	}
	
	// setMaxAnswerTime
	
	@Test
	public void test_setMaxAnswerTime_Valid_Value_Is_Accepted() {
		opdracht.setMaxAnswerTime(40);
		assertEquals(40,opdracht.getMaxAnswerTime());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setMaxAnswerTime_Exception_If_Parameter_Is_Negative() {
		opdracht.setMaxAnswerTime(-10);
	}
	
	// setDatumRegistratie
	
	@Test
	public void test_setDatumRegistratie_Valid_Value_Is_Accepted() {
		opdracht.setDatumRegistratie(new DatumGC());
		assertEquals(new DatumGC(), opdracht.getDatumRegistratie());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setDatumRegistratie_Exception_If_Parameter_Is_Null() {
		opdracht.setDatumRegistratie(null);
	}
	
	// equals
	
	@Test
	public void test_Equals_True_If_Opdrachten_Equal() {
		assertTrue(opdrachtGelijk.equals(opdracht));
		assertTrue(opdracht.equals(opdrachtGelijk));
	}
	
	@Test
	public void test_Equals_False_If_Opdrachten_Not_Equal() {
		assertFalse(opdrachtGelijk.equals(opdrachtNietGelijk));
	}
	
	// hashCode
	
	@Test
	public void test_Equals_True_If_HashCodes_Equal() {
		assertTrue(opdracht.hashCode() == opdrachtGelijk.hashCode());
	}
	
	@Test
	public void test_Equals_False_If_HashCodes_Not_Equal() {
		assertFalse(opdrachtGelijk.hashCode() == opdrachtNietGelijk.hashCode());
	}
	
	// compareTo
	
	@Test
	public void test_Equals_True_If_Questions_Equal() {
		assertTrue(opdracht.compareTo(opdrachtGelijk) == opdrachtGelijk.compareTo(opdracht));
	}
	
	@Test
	public void test_Equals_False_If_Questions_Not_Equal() {
		assertFalse(opdrachtGelijk.compareTo(opdrachtNietGelijk) == opdrachtNietGelijk.compareTo(opdrachtGelijk));
	}
	
	// isCorrectAnswer
	
	@Test
	public void test_Equals_True_If_Answers_Equal() {
		assertTrue(opdracht.isCorrectAnswer("Brussel"));
	}
	
	@Test
	public void test_Equals_False_If_Answers_Not_Equal() {
		assertFalse(opdracht.isCorrectAnswer("Leuven"));
	}
}

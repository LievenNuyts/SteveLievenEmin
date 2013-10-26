package testing;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import utils.DateQuiz;

/**
 * 
 * @author Lieven
 * @author Steve
 *
 */

public class DateTest {

	private DateQuiz date;
	private int day = 24;
	private int month = 5;
	private int year = 2000;

	
//TESTS VOOR SET METHODE
	
	@Test
	public void test_setDatum_valid_value_accepted(){
			
		date = new DateQuiz(day, month, year);
		assertTrue(date != null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tooLargeDayValue_notAccepted(){

		day = 54;
		date = new DateQuiz(day, month, year);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tooSmallDayValue_notAccepted(){

		day = 0;
		date = new DateQuiz(day, month, year);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tooLargeMonthValue_notAccepted(){

		month = 13;
		date = new DateQuiz(day, month, year);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tooSmallMonthValue_notAccepted(){

		month = 0;
		date = new DateQuiz(day, month, year);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tooLargeYearValue_notAccepted(){

		year = 40000;
		date = new DateQuiz(day, month, year);		
	}
	
	
//TEST VOOR GET METHODES
	
	@Test
	public void test_getDateInAmericanFormat_successful(){
		
		date = new DateQuiz(day, month, year);	
		String american = Integer.toString(year) + "/" + Integer.toString(month) + "/" + Integer.toString(day);
		assertEquals(american, date.getDatumInAmerikaansFormaat(date));
	}
	
	@Test
	public void test_getDateInEuropeanFormat_successful(){
		
		date = new DateQuiz(day, month, year);	
		String european = Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);
		assertEquals(european, date.getDatumInEuropeesFormaat(date));
	}
	
	
//TEST VOOR TOSTRING METHODE
	
	@Test
	public void test_ToStringMethod_has_correct_representation(){
		
		date = new DateQuiz(day, month, year);	
		String valueToTest = "24 mei 2000";	
		assertEquals(valueToTest, date.toString2());
	}

	
//TEST KLEINER DAN METHODE
	
	@Test
	public void test_smallerThan_has_positive_result(){
		DateQuiz testDatum = new DateQuiz (23, 12, 2000);
		date = new DateQuiz (day, month, year);	
		assertTrue(date.smallerThan(testDatum));
	}
	
	@Test
	public void test_smallerThan_has_negative_result(){
		
		DateQuiz testDatum = new DateQuiz (25, 12, 2000);
		date = new DateQuiz (day, month, year);	
		assertTrue(date.smallerThan(testDatum));
	}
	
//TEST VERSCHIL IN JAREN
	
	@Test
	public void test_differenceInYears_output_correct(){
		date = new DateQuiz (day, month, year); //  24/12/2000	
		DateQuiz testDatum = new DateQuiz (25, 12, 1998);
		int controleGetal = 1;
		assertEquals(controleGetal, date.verschilInJaren(testDatum));
	}
		
//TEST VERSCHIL IN MAANDEN
	
	@Test
	public void test_differenceInMonths_output_correct(){
		date = new DateQuiz (day, month, year); //  24/12/2000	
		DateQuiz testDatum = new DateQuiz (25, 12, 1998);
		int controleGetal = 23;		
		assertEquals(controleGetal, date.verschilInMaanden(testDatum));
	}
	
	
//TEST VERSCHIL IN DAGEN
	
	@Test
	public void test_differenceInDays_output_correct(){
		date = new DateQuiz (day, month, year); //  24/12/2000	
		DateQuiz testDatum = new DateQuiz (25, 12, 1998);
		int controleGetal = 729;	
		assertEquals(controleGetal, date.verschilInDagen(testDatum));
	}

//TEST VERANDERDATUM
	
	@Test
	public void test_changeDate_outputCorrect_inputCorrect(){
		date = new DateQuiz (day, month, year); //  24/12/2000
		date.veranderDatum(3);	
		assertEquals("27/12/2000", date.getDatumInEuropeesFormaat());
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void test_changeDate_Exception_if_inputIncorrect(){
		date = new DateQuiz (day, month, year); //  24/12/2000
		//datum.veranderDatum(p);		
	}
	
//TEST NIEUWE DATUM

	public void test_dateChangeDate_outputCorrect(){
		date = new DateQuiz (day, month, year); //  24/12/2000
		DateQuiz testDate1 = date;
		DateQuiz testDate2 = date.veranderDatum2(3);
		
		assertTrue(date == testDate1 && date != testDate2);
	}
	
}

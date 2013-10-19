package utils;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;


public class DateTest {

	private Datum date;
	private int day = 24;
	private int month = 12;
	private int year = 2000;

	
//TESTS VOOR SET METHODE
	
	@Test
	public void test_setDatum_valid_value_accepted(){
			
		date = new Datum(day, month, year);
		assertTrue(date != null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tooLargeDayValue_notAccepted(){

		day = 32;
		date = new Datum(day, month, year);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tooSmallDayValue_notAccepted(){

		day = 0;
		date = new Datum(day, month, year);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tooLargeMonthValue_notAccepted(){

		month = 13;
		date = new Datum(day, month, year);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tooSmallMonthValue_notAccepted(){

		month = 0;
		date = new Datum(day, month, year);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tooLargeYearValue_notAccepted(){

		year = 40000;
		date = new Datum(day, month, year);		
	}
	
	
//TEST VOOR GET METHODES
	
	@Test
	public void test_getDateInAmericanFormat_successful(){
		
		date = new Datum(day, month, year);	
		String american = Integer.toString(year) + "/" + Integer.toString(month) + "/" + Integer.toString(day);
		assertEquals(american, date.getDateInAmericanFormat());
	}
	
	@Test
	public void test_getDateInEuropeanFormat_successful(){
		
		date = new Datum(day, month, year);	
		String european = Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);
		assertEquals(european, date.getDatumInEuropeesFormaat());
	}
	
	
//TEST VOOR TOSTRING METHODE
	
	@Test
	public void test_ToStringMethod_has_correct_representation(){
		
		String valueToTest = "24 december 2000";	
		assertEquals(valueToTest, date.ToString());
	}

	
//TEST KLEINER DAN METHODE
	
	@Test
	public void test_smallerThan_has_positive_result(){
		Datum testDatum = new Datum (23, 12, 2000);
		date = new Datum (day, month, year);	
		assertTrue(datum.kleinerDan(testDatum));
	}
	
	@Test
	public void test_smallerThan_has_negative_result(){
		
		Datum testDatum = new Datum (25, 12, 2000);
		datum = new Datum (dag, maand, jaar);	
		assertTrue(datum.kleinerDan(testDatum));
	}
	
//TEST VERSCHIL IN JAREN
	
	@Test
	public void test_differenceInYears_output_correct(){
		datum = new Datum (dag, maand, jaar); //  24/12/2000	
		Datum testDatum = new Datum (25, 12, 1998);
		int controleGetal = 1;
		assertEquals(controleGetal, datum.verschilInJaren(testDatum));
	}
		
//TEST VERSCHIL IN MAANDEN
	
	@Test
	public void test_differenceInMonths_output_correct(){
		datum = new Datum (dag, maand, jaar); //  24/12/2000	
		Datum testDatum = new Datum (25, 12, 1998);
		int controleGetal = 23;		
		assertEquals(controleGetal, datum.verschilInMaanden(testDatum));
	}
	
	
//TEST VERSCHIL IN DAGEN
	
	@Test
	public void test_differenceInDays_output_correct(){
		datum = new Datum (dag, maand, jaar); //  24/12/2000	
		Datum testDatum = new Datum (25, 12, 1998);
		int controleGetal = 729;	
		assertEquals(controleGetal, datum.verschilInDagen(testDatum));
	}

//TEST VERANDERDATUM
	
	@Test
	public void test_changeDate_outputCorrect_inputCorrect(){
		datum = new Datum (dag, maand, jaar); //  24/12/2000
		datum.veranderDatum(3);	
		assertEquals("27/12/2000", datum.getDatumInEuropessFormaat());
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void test_changeDate_Exception_if_inputIncorrect(){
		datum = new Datum (dag, maand, jaar); //  24/12/2000
		//datum.veranderDatum(p);		
	}
	
//TEST NIEUWE DATUM

	public void test_dateChangeDate_outputCorrect(){
		datum = new Datum (dag, maand, jaar); //  24/12/2000
		Datum testDate1 = datum;
		Datum testDate2 = datum.veranderDatum2(3);
		
		assertTrue(datum == testDate1 && datum != testDate2);
	}
	
}

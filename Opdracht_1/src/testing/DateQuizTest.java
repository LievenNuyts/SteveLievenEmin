package testing;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.InputMismatchException;

import org.junit.Before;
import org.junit.Test;

import utils.DateQuiz;

/**
 * 
 * @author Lieven Nuyts	
 * @version 01/11/2013
 *
 */


public class DateQuizTest {

	private DateQuiz date, earlierDate, laterDate, equalDate, nullDateQ;
	private Date nullDate;
	
	final private int validDay = 1;
	final private int aboveMaxDay = 32;
	final private int belowMinDay = 0;
	
	final private int validMonth = 8;
	final private int aboveMaxMonth = 13;
	final private int belowMinMonth = 0;
	
	final private int validYear = 2013;
	final private int aboveMaxYear = 3000;
	final private int belowMinYear = -1;
	
	final private String validEuropean = "01/08/2013";
	final private String validAmerican = "08/01/2013";
	final private String invalidDateString = "01/08/'13";
	
	private int nullValueInteger; //never initialised, simple 0
	
	@Before
	public void setUp() throws Exception{
		date = new DateQuiz(validDay,validMonth,validYear);
		equalDate = new DateQuiz(validDay,validMonth,validYear);
		earlierDate = new DateQuiz(1,7,2012);
		laterDate = new DateQuiz(1,9,2014);
		nullDate = null;
	}
	
	//TESTS FOR GETTERS
	@Test
	public void test_getDay_returns_correct_Value(){
		assertEquals(validDay, date.getDay());
	}
	
	@Test
	public void test_getMonth_returns_correct_Value(){
		assertEquals(validMonth, date.getMonth());
	}
	
	@Test
	public void test_getYear_returns_correct_Value(){
		assertEquals(validYear, date.getYear());
	}
	
	//TESTS FOR SETTERS
	//setDay(int)
	@Test
	public void test_setDay_valid_Value_Accepted(){
		date.setDay(10);
		assertEquals(10, date.getDay());
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void test_setDay_BelowMinimum_Value_Throws_Exception(){
		date.setDay(belowMinDay);
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void test_setDay_AboveMaximum_Value_Throws_Exception(){
		date.setDay(aboveMaxDay);
	}
		
	//setMonth(int)
	@Test
	public void test_setMonth_valid_Value_Accepted(){
		date.setMonth(10);
		assertEquals(10, date.getMonth());
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void test_setMonth_BelowMinimum_Value_Throws_Exception(){
		date.setMonth(belowMinMonth);
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void test_setMonth_AboveMaximum_Value_Throws_Exception(){
		date.setMonth(aboveMaxMonth);
	}
		
	//setYear(int)
	@Test
	public void test_setYear_valid_Value_Accepted(){
		date.setYear(2000);
		assertEquals(2000, date.getYear());
	}
			
	@Test(expected = IllegalArgumentException.class)
	public void test_setYear_BelowMinimum_Value_Throws_Exception(){
		date.setYear(belowMinYear);
	}
			
	@Test(expected = IllegalArgumentException.class)
	public void test_setYear_AboveMaximum_Value_Throws_Exception(){
		date.setYear(aboveMaxYear);
	}
		
	
	//TESTS FOR CLASS CONSTRUCTORS
	@Test
	public void test_ConstructorDateObject_Object_Is_Created(){
		
		assertEquals(validDay, date.getDay());
		assertEquals(validMonth, date.getMonth());
		assertEquals(validYear, date.getYear());
	}
	
	@Test(expected = NullPointerException.class)
	public void test_ConstructorDateObject_Null_Throws_Exception(){
		
		date = new DateQuiz(nullDate);
	}
	
	@Test
	public void test_ConstructorThreeInteger_Object_Is_Created(){
		
		assertEquals(validDay, date.getDay());
		assertEquals(validMonth, date.getMonth());
		assertEquals(validYear, date.getYear());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_ConstructorThreeInteger_February30th_throws_Exception(){	
		date = new DateQuiz(30, 02, 2013);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_ConstructorThreeInteger_April31st_throws_Exception(){	
		date = new DateQuiz(31, 04, 2013);
	}
	
	@Test
	public void test_ConstructorThreeInteger_LeapYear_does_not_throw_Exception(){
		
		int leapDay = 29;
		int leapMonth = 2;
		int leapYear = 2012;
		int i;
		
		//three leap year tests
		for(i = 0; i <3; i++){
		
			date = new DateQuiz(leapDay, leapMonth, leapYear);
			leapYear += 4;
		}
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void test_ConstructorThreeInteger_NullInteger_Throws_Exception(){
		
		date = new DateQuiz(validDay, nullValueInteger, validYear);
	}
	
	@Test
	public void test_ConstructorWithString_Object_Is_Created(){
		date = new DateQuiz(validEuropean);
		
		assertEquals(validDay, date.getDay());
		assertEquals(validMonth, date.getMonth());
		assertEquals(validYear, date.getYear());
	}
	
	@Test(expected = InputMismatchException.class)
	public void test_ConstructorWithString_invalidString_throws_Exception(){
		date = new DateQuiz(invalidDateString);
	}
	
	
	//Tests for  getDatum
	
	
	@Test
	public void test_getDateInAmericanFormat_Returns_Correct_Value(){
		assertEquals(validAmerican,date.getDateInAmericanFormat());
	}
	
	@Test
	public void test_getDateInEuropeanFormat_Returns_Correct_Value(){
		assertEquals(validEuropean,date.getDateInEuropeanFormat());
	}
	
	
	//Tests for 'smaller than' method
	@Test
	public void test_smallerThan_InputSmallerDate_Returns_True(){
		assertFalse(date.smallerThan(earlierDate));
	}
	
	@Test
	public void test_smallerThan_InputBiggerDate_Returns_False(){
		assertTrue(date.smallerThan(laterDate));
	}
	
	@Test
	public void test_smallerThan_InputEqualDate_Returns_False(){
		assertFalse(date.smallerThan(equalDate));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_smallerThan_InputNullDate_Throws_Exception(){
		date.smallerThan(nullDateQ);
	}
	
	//Tests for difference in days, months, years methodes
	//Difference for test calculated via http://www.kalender-365.nl/bereken/periode-tussen-twee-datums.html
	@Test
	public void test_DifferenceInYears_ValidEarlierDateInput_Returns_Correct_Value() throws Exception{
		assertEquals(1, date.differenceInYears(earlierDate));
	}
	
	@Test
	public void test_DifferenceInYears_ValidLaterDateInput_Returns_Correct_Value() throws Exception{
		assertEquals(1, date.differenceInYears(earlierDate));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_DifferenceInYears_InvalidInput_Throws_Exception(){
			date.differenceInYears(nullDateQ);
	}
	
	@Test
	public void test_DifferenceInMonths_ValidEarlierDateInput_Returns_Correct_Value() throws Exception{
		assertEquals(13, date.differenceInMonths(earlierDate));
	}
	
	@Test
	public void test_DifferenceInMonths_ValidLaterDateInput_Returns_Correct_Value() throws Exception{
		assertEquals(13, date.differenceInMonths(laterDate));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_DifferenceInMonths_InvalidInput_Throws_Exception() throws Exception{
		date.differenceInMonths(nullDateQ);
	}
	
	@Test
	public void test_DifferenceInDays_ValidEarlierDateInput_Returns_Correct_Value(){
		assertEquals(396, date.differenceInDays(earlierDate));
	}
	
	@Test
	public void test_DifferenceInDays_ValidLaterDateInput_Returns_Correct_Value(){
		assertEquals(396, date.differenceInDays(laterDate));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_DifferenceInDays_InvalidInput_Throws_Exception(){
		date.differenceInDays(nullDateQ);
	}
	
	//tests for changeDate(integer days)
	
	@Test
	public void test_changeDate_validPositiveInteger_calculates_correct_Values(){
		date.changeDate(458); //01/08/2013 --> 02/11/2014
		assertEquals(2, date.getDay());
		assertEquals(11,date.getMonth());
		assertEquals(2014,date.getYear());
	}
	
	@Test
	public void test_changeDate_validNegativeInteger_calculates_correct_Values(){
		date.changeDate(-272); //01/08/2013 --> 02/11/2012
		
		assertEquals(2, date.getDay());
		assertEquals(11,date.getMonth());
		assertEquals(2012,date.getYear());
	}
	
	@Test
	public void test_changeDate_validPositiveInteger_returns_correct_DateObject(){
		DateQuiz newDate = date.changeDate2(458); //01/08/2013 --> 02/11/2014
		
		assertEquals(2, newDate.getDay());
		assertEquals(11, newDate.getMonth());
		assertEquals(2014, newDate.getYear());
	}
	
	@Test
	public void test_changeDate_validNegativeInteger_returns_correct_DateObject(){
		DateQuiz newDate = date.changeDate2(-272); //01/08/2013 --> 02/11/2012
		assertEquals(2, newDate.getDay());
		assertEquals(11, newDate.getMonth());
		assertEquals(2012, newDate.getYear());
	}	
	
	//test for compareTo(date object)
	@Test
	public void test_compareTo_validDateObject_returns_correct_integer(){
		DateQuiz newDate = new DateQuiz(1,8,2013);
		assertTrue(newDate.compareTo(date) == date.compareTo(newDate));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_compareTo_invalidDateObject_throws_exception(){
		date.compareTo(nullDateQ);
	}
	
	//tests for equals
	@Test
	public void test_Equals_equalDate_returns_True(){
		assertTrue(date.equals(equalDate));
	}
	
	@Test
	public void test_Equals_differentDate_returns_False(){
		assertFalse(date.equals(laterDate));
	}	
}

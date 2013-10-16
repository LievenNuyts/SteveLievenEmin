package utils;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;


public class DateTest {

	private Datum datum;
	private int dag = 24;
	private int maand = 12;
	private int jaar = 2000;

	
//TESTS VOOR SET METHODE
	
	@Test
	public void test_setDatum_geldige_waarde_wordt_aanvaard(){
			
		datum = new Datum(dag, maand, jaar);
		assertTrue(datum != null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tegrote_dagwaarde_wordt_niet_aanvaard(){

		dag = 32;
		datum = new Datum(dag, maand, jaar);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tekleine_dagwaarde_wordt_niet_aanvaard(){

		dag = 0;
		datum = new Datum(dag, maand, jaar);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tegrote_maandwaarde_wordt_niet_aanvaard(){

		maand = 13;
		datum = new Datum(dag, maand, jaar);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tekleine_maandwaarde_wordt_niet_aanvaard(){

		maand = 0;
		datum = new Datum(dag, maand, jaar);	
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void test_setDatum_tegrote_jaarwaarde_wordt_niet_aanvaard(){

		jaar = 40000;
		datum = new Datum(dag, maand, jaar);	
	}
	
	
//TEST VOOR GET METHODES
	
	@Test
	public void test_getDatumInAmerikaansFormaat_is_succesvol(){
		
		datum = new Datum(dag, maand, jaar);
		String amerikaans = Integer.toString(jaar) + "/" + Integer.toString(maand) + "/" + Integer.toString(dag);
		assertEquals(amerikaans, datum.getDatumInAmerikaansFormaat());
	}
	
	@Test
	public void test_getDatumInEuropeesFormaat_is_succesvol(){
		
		datum = new Datum(dag, maand, jaar);
		String europees = Integer.toString(dag) + "/" + Integer.toString(maand) + "/" + Integer.toString(jaar);
		assertEquals(europees, datum.getDatumInEuropeesFormaat());
	}
	
	
//TEST VOOR TOSTRING METHODE
	
	@Test
	public void test_ToStringMethode_geeft_correcte_weergave(){
		
		String valueToTest = "24 december 2000";	
		assertEquals(valueToTest, datum.ToString());
	}

	
//TEST KLEINER DAN METHODE
	
	@Test
	public void test_kleinerDan_geeft_positief_resultaat(){
		Datum testDatum = new Datum (23, 12, 2000);
		datum = new Datum (dag, maand, jaar);	
		assertTrue(datum.kleinerDan(testDatum));
	}
	
	@Test
	public void test_kleinerDan_geeft_negatief_resultaat(){
		
		Datum testDatum = new Datum (25, 12, 2000);
		datum = new Datum (dag, maand, jaar);	
		assertTrue(datum.kleinerDan(testDatum));
	}
	
//TEST VERSCHIL IN JAREN
	
	@Test
	public void test_verschilInJaren_output_correct(){
		datum = new Datum (dag, maand, jaar); //  24/12/2000	
		Datum testDatum = new Datum (25, 12, 1998);
		int controleGetal = 1;
		assertEquals(controleGetal, datum.verschilInJaren(testDatum));
	}
		
//TEST VERSCHIL IN MAANDEN
	
	@Test
	public void test_verschilInMaanden_output_correct(){
		datum = new Datum (dag, maand, jaar); //  24/12/2000	
		Datum testDatum = new Datum (25, 12, 1998);
		int controleGetal = 23;		
		assertEquals(controleGetal, datum.verschilInMaanden(testDatum));
	}
	
	
//TEST VERSCHIL IN DAGEN
	
	@Test
	public void test_verschilInDagen_outputcorrect(){
		datum = new Datum (dag, maand, jaar); //  24/12/2000	
		Datum testDatum = new Datum (25, 12, 1998);
		int controleGetal = 729;	
		assertEquals(controleGetal, datum.verschilInDagen(testDatum));
	}

//TEST VERANDERDATUM
	
	@Test
	public void test_veranderDatum_outputCorrect_inputCorrect(){
		datum = new Datum (dag, maand, jaar); //  24/12/2000
		datum.veranderDatum(3);	
		assertEquals("27/12/2000", datum.getDatumInEuropessFormaat());
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void test_veranderDatum_Exception_als_inputIncorrect(){
		datum = new Datum (dag, maand, jaar); //  24/12/2000
		//datum.veranderDatum(p);		
	}
	
//TEST NIEUWE DATUM

	public void test_DatumVeranderDatum_outputCorrect(){
		datum = new Datum (dag, maand, jaar); //  24/12/2000
		Datum testDatum1 = datum;
		Datum testDatum2 = datum.veranderDatum2(3);
		
		assertTrue(datum == testDatum1 && datum != testDatum2);
	}
	
}

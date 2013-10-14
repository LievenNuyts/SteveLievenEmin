/**
 * 
 */
package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.Leraar;
import model.Opdracht;
import model.OpdrachtCatalogus;
import model.Quiz;

import org.junit.Before;
import org.junit.Test;

import utils.DatumGC;

/**
 * 
 * @author Emin
 * @version 10/10/2013
 *
 */
public class OpdrachtCatalogusTest {

	private OpdrachtCatalogus catalogus,catalogusGelijk,catalogusNietGelijk;
	private List<Opdracht> opdrachten;
	private Opdracht opdracht1;
	private Opdracht opdracht2;
	private Opdracht opdracht3;
	
	@Before
	public void setUp() throws Exception {
		
		opdrachten = new ArrayList<Opdracht>();
		
		opdracht1 = new Opdracht("Wat is mijn Voornaam","Emin",new String[]{"kort","4"},2,30,
				Opdracht.OpdrachtCategorie.ALGEMENEKENNIS,Leraar.BAKKER,new ArrayList<Quiz>(),
				new DatumGC(2013,10,1));
		opdracht2 = new Opdracht("Wat is mijn Naam","Iandyrhanov",new String[]{"kort","4"},2,30,
				Opdracht.OpdrachtCategorie.ALGEMENEKENNIS,Leraar.BAKKER,new ArrayList<Quiz>(),
				new DatumGC(2013,10,1));
		opdracht3 = new Opdracht("Hoofdstad van België?","Brussel",new String[]{"kort","4"},2,30,
				Opdracht.OpdrachtCategorie.ALGEMENEKENNIS,Leraar.BAKKER,new ArrayList<Quiz>(),
				new DatumGC(2013,10,1));
		
		opdrachten.add(opdracht1);
		
		catalogus = new OpdrachtCatalogus(opdrachten);
		catalogusGelijk = new OpdrachtCatalogus(opdrachten);
		catalogusNietGelijk = new OpdrachtCatalogus();
	}

	// Constructor
	
	@Test
	public void test_Constructor_Object_Is_Created() {
		assertEquals(opdrachten,catalogus.getOpdrachten());
	}
	
	// setOpdrachten
	
	@Test
	public void test_setOpdrachten_Valid_Value_Is_Accepted() {
		opdrachten = new ArrayList<Opdracht>();
		opdrachten.add(opdracht2);
		
		catalogus.setOpdrachten(opdrachten);
		assertEquals(opdrachten,catalogus.getOpdrachten());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setOpdrachten_Exception_If_Parameter_Is_Null() {
		catalogus.setOpdrachten(null);
	}
	
	// addOpdracht
	
	@Test
	public void test_addOpdracht_Valid_Value_Is_Accepted() {
		catalogus.addOpdracht(opdracht3);
		
		assertEquals(opdrachten.size(),catalogus.getOpdrachten().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_addOpdracht_Exception_If_Parameter_Is_Null() {
		catalogus.addOpdracht(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_addOpdracht_Exception_If_Parameter_Exists() {
		catalogus.addOpdracht(opdracht1);
	}
	
	// removeOpdracht
	
	@Test
	public void test_removeOpdracht_Valid_Value_Is_Accepted() {
		catalogus.removeOpdracht(opdracht1);
		
		assertEquals(opdrachten.size(),catalogus.getOpdrachten().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_removeOpdracht_Exception_If_Parameter_Is_Null() {
		catalogus.removeOpdracht(null);
	}
	
	// updateOpdracht
	
	@Test
	public void test_updateOpdracht_Valid_Value_Is_Accepted() {
		catalogus.updateOpdracht(opdracht1,opdracht2);
		
		assertEquals(catalogus.getOpdrachten().get(0),opdracht2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_updateOpdracht_Exception_If_oldOpdracht_Is_Null() {
		catalogus.updateOpdracht(null,opdracht2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_updateOpdracht_Exception_If_newOpdracht_Is_Null() {
		catalogus.updateOpdracht(opdracht1,null);
	}
	
	// equals
	
	@Test
	public void test_Equals_True_If_Catalogus_Equal() {
		assertTrue(catalogusGelijk.equals(catalogus));
		assertTrue(catalogus.equals(catalogusGelijk));
	}
	
	@Test
	public void test_Equals_False_If_Catalogus_Not_Equal() {
		assertFalse(catalogusGelijk.equals(catalogusNietGelijk));
	}
	
	// hashCode
	
	@Test
	public void test_Equals_True_If_HashCodes_Equal() {
		assertTrue(catalogus.hashCode() == catalogusGelijk.hashCode());
	}
	
	@Test
	public void test_Equals_False_If_HashCodes_Not_Equal() {
		assertFalse(catalogusGelijk.hashCode() == catalogusNietGelijk.hashCode());
	}
	
	// compareTo
	
	@Test
	public void test_Equals_True_If_Questions_Equal() {
		assertTrue(catalogus.compareTo(catalogusGelijk) == catalogusGelijk.compareTo(catalogus));
	}
	
	@Test
	public void test_Equals_False_If_Questions_Not_Equal() {
		assertFalse(catalogusGelijk.compareTo(catalogusNietGelijk) == catalogusNietGelijk.compareTo(catalogusGelijk));
	}
}

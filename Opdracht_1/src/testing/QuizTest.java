package testing;

import static org.junit.Assert.*;

import model.Quiz;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Steve
 * @version 31/10/2013
 *
 */
public class QuizTest {
	
        private Quiz quiz;
        
        @Before
        public void setUp() throws Exception {
                quiz = new Quiz("Testing subject");
        }
        
        //test constructor with subject parameter
        @Test
        public void testConstructorQuiz_Subject() {
                Quiz quiz = new Quiz("Testing subject");
                assertEquals("Testing subject", quiz.getSubject());
        }
        
        //test constructor with String int boolean boolean
        @Test
        public void testConstructorQuiz_String_Int_Boolean_Boolean() {
        	
                Quiz quiz = new Quiz("Testing subject",3, true,true);
                assertEquals("Testing subject", quiz.getSubject()); 		//parameter 1: subject
                assertEquals(3, quiz.getLeerJaren()); 						//parameter 2: leerjaar
                assertEquals(true, quiz.isUniqueParticipation());			//parameter 3: unieke deelname
                assertEquals(true, quiz.isTest());							//parameter 4: is test
        }
        
        @Test
        public void testSetSubject(){
                quiz.setSubject("Testing subject");
                assertEquals("Testing subject", quiz.getSubject());
        }
       
        @Test
        public void testSetLeerJaren(){
                quiz.setLeerJaren(3);
                assertEquals(3, quiz.getLeerJaren());
        }
        
        @Test
        public void testSetTest_True() {
                quiz.setTest(true);
                assertTrue(true);
        }
        @Test
        public void testSetTest_False() {
                quiz.setTest(false);
                assertFalse(false);
        }
        
        @Test
        public void setUniqueParticipation_True() {
                quiz.setUniqueParticipation(true);
                assertTrue(true);
        }
        @Test
        public void setUniqueParticipation_False() {
                quiz.setUniqueParticipation(false);
                assertFalse(false);
        }
}

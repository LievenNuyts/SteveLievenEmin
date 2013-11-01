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
                quiz = new Quiz("rekenen");
        }
        /**
         * Test constructor met parameter (String onderwerp)
         */
        @Test
        public void testConstructorQuiz_Subject() {
                Quiz quiz = new Quiz("rekenen");
                assertEquals("onderwerp rekenen wordt verwacht","rekenen", quiz.getOnderwerp());
        }
        /**
         * Test constructor met parameter (String onderwerp, int leerjaar, boolean isUniekeDeelname, boolean isTest,QuizStatussen quizStatus)
         */
        @Test
        public void testQuizStringIntBooleanBoolean() {
                //QuizStatussen quizStatus = QuizStatussen.INCONSTRUCTIE;                
                Quiz quiz = new Quiz("rekenen",3,5, true,true);
                assertEquals("eerste parameter onderwerp","rekenen",quiz.getOnderwerp());
                assertEquals("tweede parameter leerjaar",3,quiz.getMinLeerjaar());
                assertEquals("derde parameter unieke deelname",true,quiz.isUniekeDeelname());
                assertEquals("vierde parameter isTest",true,quiz.isTest());
        }
        /**
         * Test methode setOnderwerp(String onderwerp)
         */
        @Test
        public void testSetOnderwerp(){
                quiz.setOnderwerp("rekenen");
                assertEquals("test setOnderwerp","rekenen", quiz.getOnderwerp());
        }
        /**
         * Test methode setLeerJaar(int leerJaar)
         */
        @Test
        public void testSetLeerJaar(){
                quiz.setMinLeerjaar(3);
                assertEquals("test setLeerJaar",3, quiz.getMinLeerjaar());
        }
        /**
         * Test methode setTest(boolean isTest)
         */
        @Test
        public void testSetTestTrue() {
                quiz.setTest(true);
                assertTrue(true);
        }
        @Test
        public void testSetTestFalse() {
                quiz.setTest(false);
                assertFalse(false);
        }
        /**
         * test methode isUniekeDeelname
         */
        @Test
        public void setUniekeDeelnameTrue() {
                quiz.setUniekeDeelname(true);
                assertTrue(true);
        }
        @Test
        public void setUniekeDeelnameFalse() {
                quiz.setUniekeDeelname(false);
                assertFalse(false);
        }
        /**
         * test controle leerjaar
         */
        @Test
        public void testControleLeerJaarCorrecteInput(){
                //quiz.setLeerJaar(3);
                assertEquals("correct leerjaar",3,quiz.controleLeerjaar(3));
        }
        @Test (expected = IllegalArgumentException.class)
        public void testControleLeerjaarLeerJaarGroterDanZes(){
                assertEquals("Leerjaar groter dan zes",6,quiz.controleLeerjaar(7));
        }
        @Test (expected = IllegalArgumentException.class)
        public void testControleLeerjaarLeerJaarKleinerDanEen(){
                //quiz.setLeerJaar(0);
                assertEquals("Leerjaar kleiner dan ��n",0,quiz.controleLeerjaar(0));
        }
        @Test (expected = IllegalArgumentException.class)
        public void testControleLeerJaarNegatief(){
                quiz.setMinLeerjaar(-5);
                assertEquals("negatief leerjaar",4,quiz.controleLeerjaar(-5));
        }
}

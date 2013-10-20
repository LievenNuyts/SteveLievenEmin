package testing;

import static org.junit.Assert.*;
import model.Student;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Emin
 * @version 20/10/2013
 *
 */
public class StudentTest {
	
	private Student student, studentEqual, studentNotEqual;
	
	@Before
	public void setUp() throws Exception{
		student = new Student("Nico",2);
		studentEqual = new Student("Nico",2);
		studentNotEqual = new Student("Sven",5);
	}
	
	// Constructor
	
	@Test
	public void test_Constructor_Object_Is_Created() {
		assertEquals("Nico",student.getStudentName());
		assertEquals(2,student.getGrade());
	}
	
	// setStudentName
	
	@Test
	public void test_setStudentName_Valid_Value_Is_Accepted() {
		student.setStudentName("Tony");
		assertEquals("Tony",student.getStudentName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setStudentName_Exception_If_Parameter_Is_Null() {
		student.setStudentName(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setStudentName_Exception_If_Parameter_Is_Empty() {
		student.setStudentName("");
	}
	
	// setGrade
	
	@Test
	public void test_setGrade_Valid_Value_Is_Accepted() {
		student.setGrade(3);
		assertEquals(3,student.getGrade());
	}
	
	@Test
	public void test_setGrade_1_Is_Accepted() {
		student.setGrade(1);
		assertEquals(1,student.getGrade());
	}
	
	@Test
	public void test_setGrade_6_Is_Accepted() {
		student.setGrade(6);
		assertEquals(6,student.getGrade());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setGrade_Exception_If_Parameter_Is_Less_Than_1() {
		student.setGrade(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setGrade_Exception_If_Parameter_Is_Negative() {
		student.setGrade(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setGrade_Exception_If_Parameter_Is_Higher_Than_6() {
		student.setGrade(7);
	}
	
	// Equals
	
	@Test
	public void test_Equals_True_If_Students_Equal() {
		assertTrue(studentEqual.equals(student));
		assertTrue(student.equals(studentEqual));
	}
	
	@Test
	public void test_Equals_False_If_Students_Not_Equal() {
		assertFalse(studentEqual.equals(studentNotEqual));
	}
	
	// hashCode
	
	@Test
	public void test_Equals_True_If_HashCode_Equal() {
		assertTrue(student.hashCode() == studentEqual.hashCode());
	}
	
	@Test
	public void test_Equals_False_If_HashCode_Not_Equal() {
		assertFalse(studentEqual.hashCode() == studentNotEqual.hashCode());
	}
	
	// compareTo
	
	@Test
	public void test_Equals_True_If_StudentNames_Equal() {
		assertTrue(student.compareTo(studentEqual) == studentEqual.compareTo(student));
	}
	
	@Test
	public void test_Equals_False_If_StudentNames_Not_Equal() {
		assertFalse(studentEqual.compareTo(studentNotEqual) == studentNotEqual.compareTo(studentEqual));
	}
}

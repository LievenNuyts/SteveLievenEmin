package observer;

import java.util.ArrayList;

import persistence.IPersistencable;
import persistence.MysqlPersistence;
import persistence.TextPersistence;
import model.QuizCatalog;
import model.Student;

public class School {

	private Student s1;
	private Student s2;
	private Student s3;
	private Student s4;
	private Student s5;
	private Student s6;
	private Student s7;
	
	private ArrayList<Student> students;
	
	public School(){
		
		students = new ArrayList<Student>();
		
		s1 = new Student("Janneke",1);
		students.add(s1);
		s2 = new Student("Mieke",1);
		students.add(s2);
		s3 = new Student("Polleke",2);
		students.add(s3);
		s4 = new Student("Isibaldje",2);
		students.add(s4);
		s5 = new Student("Achmedje",3);
		students.add(s5);
		s6 = new Student("Ludoken",4);
		students.add(s6);
		s7 = new Student("Jefke",4);
		students.add(s7);
	}
		
	public Student getS1() {
		return s1;
	}

	public void setS1(Student s1) {
		this.s1 = s1;
	}

	public Student getS2() {
		return s2;
	}

	public void setS2(Student s2) {
		this.s2 = s2;
	}

	public Student getS3() {
		return s3;
	}

	public void setS3(Student s3) {
		this.s3 = s3;
	}

	public Student getS4() {
		return s4;
	}

	public void setS4(Student s4) {
		this.s4 = s4;
	}
	public Student getS5() {
		return s5;
	}
	public void setS5(Student s5) {
		this.s5 = s5;
	}
	public Student getS6() {
		return s6;
	}

	public void setS6(Student s6) {
		this.s6 = s6;
	}

	public Student getS7() {
		return s7;
	}

	public void setS7(Student s7) {
		this.s7 = s7;
	}	
	
	//Methode to add each student as an observer to QuizCatalog
	public void studentsToObservers(IPersistencable persistence){
			
			if(persistence instanceof TextPersistence){
				
				TextPersistence txtPersistence = (TextPersistence)persistence;
				
				for(Student student : students){			
					txtPersistence.addObserver(student);
				}
				
			}
			else{
				
				MysqlPersistence msqPersistence = (MysqlPersistence)persistence;
				
				for(Student student : students){
					msqPersistence.addObserver(student);
				}
			}		
	}
}

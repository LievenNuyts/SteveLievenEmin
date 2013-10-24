/**
 * 
 */
package model;

/**
 * 
 * @author Emin
 * @version 20/10/2013
 *
 */
public class Student implements Comparable<Student>, Cloneable{
	
	private String studentName;
	private int grade;
	
	// Constructors
	
	/**
	 * Default constructor
	 * 
	 * @throws IllegalArgumentException
	 */
	public Student() throws IllegalArgumentException{
		this.setStudentName("NoName");
		this.setGrade(1);
	}
	
	/**
	 * Constructor with 2 params
	 * 
	 * @param studentName
	 * @param grade
	 * @throws IllegalArgumentException
	 */
	public Student(String studentName, int grade) throws IllegalArgumentException{
		this.setStudentName(studentName);
		this.setGrade(grade);
	}
	
	// Selectors 
	
	/**
	 * @return
	 */
	public String getStudentName() {
		return studentName;
	}
	
	/**
	 * @return
	 */
	public int getGrade() {
		return grade;
	}
	
	// Modifiers
	
	/**
	 * Set studentName
	 * 
	 * @param studentName
	 */
	public void setStudentName(String studentName) throws IllegalArgumentException{
		if (studentName == null)throw new IllegalArgumentException("StudentName is null!");
		if (studentName.isEmpty())throw new IllegalArgumentException("Gelieve een naam in te vullen!");
		this.studentName = studentName;
	}
	
	/**
	 * Set grade
	 * 
	 * @param grade
	 */
	public void setGrade(int grade) throws IllegalArgumentException{
		if (grade < 1 || grade > 6)throw new IllegalArgumentException("Gelieve juiste leerjaar in te vullen!");
		this.grade = grade;
	}

	// Comparisons
	
	/**
	 * Comparable
	 * 
	 * @param student
	 * @return
	 */
	public int compareTo(Student student) {
		return this.getStudentName().compareTo(student.getStudentName());
	}
	
	// Cloneable
	
	/**
	 * Method to clone this object
	 * 
	 * @return
	 */
	@Override
	public Student clone() throws CloneNotSupportedException{
		return (Student)super.clone();
	}
		
	// Overrides

	@Override
	public String toString() {
		return "Student [studentName=" + studentName + ", grade=" + grade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + grade;
		result = prime * result
				+ ((studentName == null) ? 0 : studentName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (grade != other.grade)
			return false;
		if (studentName == null) {
			if (other.studentName != null)
				return false;
		} else if (!studentName.equals(other.studentName))
			return false;
		return true;
	}
}

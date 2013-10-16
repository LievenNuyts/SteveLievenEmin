package model;

/**
 * 
 * @author Steve
 * @version 13/10/2013
 *
 */

	enum QuizStatus {
	//statussen
	}

public class Quiz implements Comparable<Quiz>{
	
	String onderwerp = "";
	int leerJaren = 0;

	boolean isUniekeDeelname;
	boolean isTest;
	
	
	public Quiz()throws IllegalArgumentException
	{
		onderwerp = "subject";	
	}
	
	public Quiz(String onderwerp, int leerJaren, boolean uniekeDeelname, boolean isTest)throws IllegalArgumentException
	{
		this.onderwerp = onderwerp;
		this.leerJaren = leerJaren;
		this.isUniekeDeelname = uniekeDeelname;
		this.isTest = isTest;
	}
	
	@Override
	public int compareTo(Quiz o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isTest ? 1231 : 1237);
		result = prime * result + (isUniekeDeelname ? 1231 : 1237);
		result = prime * result + leerJaren;
		result = prime * result
				+ ((onderwerp == null) ? 0 : onderwerp.hashCode());
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
		Quiz other = (Quiz) obj;
		if (isTest != other.isTest)
			return false;
		if (isUniekeDeelname != other.isUniekeDeelname)
			return false;
		if (leerJaren != other.leerJaren)
			return false;
		if (onderwerp == null) {
			if (other.onderwerp != null)
				return false;
		} else if (!onderwerp.equals(other.onderwerp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Quiz [onderwerp=" + onderwerp + ", leerJaren=" + leerJaren
				+ ", isUniekeDeelname=" + isUniekeDeelname + ", isTest="
				+ isTest + "]";
	}
	
	/*public static void main(String[] args) {
		
		Quiz quiz = new Quiz("Craziness", 2, false, true);
		quiz.isTest = false;
		quiz.isUniekeDeelname = true;
		quiz.leerJaren = 2;
		quiz.onderwerp = "Craziness";
		
		System.out.println(quiz);

	}*/

	
	

}

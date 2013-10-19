package model;

public class EnumerationExercise extends Exercise implements Validatable{
	
	/**
	 *  
	 * @author Lieven
	 * @version 19/10/2013
	 *
	 */
	
	@Override
	public boolean isCorrectAnswer(String answer){
		
		if (this.getCorrectAnswer() == answer)
			return true;
		return false;
	}

	@Override
	public boolean isValide(String answer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getValidatedText() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

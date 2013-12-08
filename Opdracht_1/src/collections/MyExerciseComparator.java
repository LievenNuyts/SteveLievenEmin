package collections;

import java.util.Comparator;
import model.Quiz;

/**
 * 
 * @author Lieven
 *
 */

public class MyExerciseComparator implements Comparator<Quiz> {

	@Override
	public int compare(Quiz o1, Quiz o2) {
		
		int result = 0;
		
		if(o1.getQuizExercises().size() > o2.getQuizExercises().size()){
			result = -1; //reverse order, highest number goes first
		}
		else{
			
			if(o1.getQuizExercises().size() == o2.getQuizExercises().size()){		
				//compareTo method in quiz compares based on subject of the quiz		
				result = o1.compareTo(o2);	
			}
			else{
				result = 1; //reverse order, lowest number goes last
			}		
		}
		
		return result;	
	} 
}

/**
 * 
 */
package patterns;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * @author java
 *
 */
public class QuizScoreRulesFactory {
	
	private  Properties table;
	
	
	private static QuizScoreRulesFactory instance = null;
	private QuizScoreRulesFactory() {
	}
	public static QuizScoreRulesFactory getInstance() {
		if(instance == null) {
			instance = new QuizScoreRulesFactory();
		}
		
		return instance;
	}

	public void chooseScoreMethod(){
		table = new Properties();
		
		Object[] possibleScores = { "BasicScore", "NumberOfAttemptScore", "TimeBasedScore" };
		Object selection = null;
		selection = JOptionPane.showInputDialog(null,"Select score method",
                                       "Scroe", JOptionPane.INFORMATION_MESSAGE, null, 
                                       possibleScores, possibleScores[0]);
		if (selection !=null)
			setScroreMethod((String)selection);
	}
	
	public void setScroreMethod(String scoreMethod){
		try{
			FileOutputStream out = new FileOutputStream("src" + File.separator + "files" + File.separator + "initScore.dat");
			table.setProperty("scoreMethod", scoreMethod);
			table.store(out,"init quizzes value");
			out.close();
		}
		catch (IOException ex){ex.printStackTrace();}
	}
	
	public String getScoreMethod(){
		try{
			FileInputStream in = new FileInputStream("initScore.dat");
			table.load(in);
			in.close();
			Object value = table.getProperty("scoreMethod");
			if (value != null)
				return (String)value;
	
		}
		catch (IOException ex){ex.printStackTrace();}
		return null;
	}
	
	public static void main(String[] args) {
		QuizScoreRulesFactory mi = new QuizScoreRulesFactory();
		mi.chooseScoreMethod();
	}

}

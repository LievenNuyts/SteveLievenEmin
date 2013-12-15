/**
 * 
 */
package persistenty;

import java.util.Properties;

import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 
 * @author Emin
 *
 */
public class ManagementInitFile {
	
	private Properties table;
	
	public ManagementInitFile(){
		table = new Properties();
	}
	

	public void choosePersistetyMethod(){
		Object[] possiblePersistenties = { "Text persistenty", "MySQL persistenty" };
		Object selection = null;
		selection = JOptionPane.showInputDialog(null,"Select persistenty method",
                                       "Persistenty", JOptionPane.INFORMATION_MESSAGE, null, 
                                       possiblePersistenties, possiblePersistenties[0]);
		if (selection !=null)
			setPersistentyMethod((String)selection);
	}
	
	public void setPersistentyMethod(String persistentyMethod){
		try{
			FileOutputStream out = new FileOutputStream("src" + File.separator + "files" + File.separator + "init.dat");
			table.setProperty("persistentyMethod", persistentyMethod);
			table.store(out,"init quizzes value");
			out.close();
		}
		catch (IOException ex){ex.printStackTrace();}
	}
	
	public String getPersistentyMethod(){
		try{
			FileInputStream in = new FileInputStream("init.dat");
			table.load(in);
			in.close();
			Object value = table.getProperty("persistentyMethod");
			if (value != null)
				return (String)value;
	
		}
		catch (IOException ex){ex.printStackTrace();}
		return null;
	}
	
	public static void main(String[] args) {
		ManagementInitFile mi = new ManagementInitFile();
		mi.choosePersistetyMethod();
	}
}

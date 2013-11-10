package view;

import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.QuizCatalog;
import model.ExerciseCatalog;

/**
 * 
 * @author Steve
 * @version 6/11/2013
 * 
 */

public class ChangeQuizView extends JFrame {
	
		private static final long serialVersionUID = 1L;
		private QuizCatalog quizCatalog;
		private ExerciseCatalog exCatalog;
		private JPanel panel_01, panel_02;
		private JTextField txt_01, txt_02;
		private JLabel lb_01, lb_02, lb_03, lb_04;
		private JTable table;
		private JButton btn_update;
		
		public ChangeQuizView()
		{
			super("Change Quiz");
		}

		public ChangeQuizView(QuizCatalog quizCatalog, ExerciseCatalog exCatalog) throws HeadlessException {
			super("Change quiz");
			this.quizCatalog = quizCatalog;
			this.exCatalog = exCatalog;
			
			panel_01 = new JPanel(); 
			panel_02 = new JPanel();
		}
		
		

}

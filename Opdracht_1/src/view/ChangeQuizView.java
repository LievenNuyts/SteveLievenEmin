package view;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class ChangeQuizView extends JFrame implements ActionListener{
	
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
			
			setLayout(new FlowLayout());
			

			panel_01 = new JPanel(); 
			panel_02 = new JPanel();
			txt_01 = new JTextField("Find quiz", 25);
			txt_02 = new JTextField("Find exercise", 15);
			lb_01 = new JLabel("Status");
			lb_02 = new JLabel("Show category");
			lb_03 = new JLabel("Quiz");
			lb_04 = new JLabel("Exercise");
			table = new JTable();
			btn_update = new JButton("Search");
			
			//implements interface Actionlistener
			btn_update.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Update!");
					//uit te voeren code
					
				}
				
			});
			
			
			//layout
			
			panel_01.add(txt_01);
			panel_01.add(txt_02);
			panel_01.add(lb_01);
			
			panel_01.add(btn_update);
			
			panel_01.add(lb_02);
			
			panel_02.add(lb_03);
			panel_02.add(lb_04);
			panel_02.add(table);
			
			add(panel_01);
			add(panel_02);
			
			
			//window
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(750,750);
			this.setVisible(true);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			

			JButton source = (JButton)e.getSource();
			
			if(source == btn_update) {
				System.out.println("Update via cast e.getSource");
			}
			
		}
		
		
		

}

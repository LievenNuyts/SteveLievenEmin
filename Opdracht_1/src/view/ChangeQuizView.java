package view;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Quiz;
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
		private JButton btn_update, btn_edit, btn_delete;
		private JList listExercise;
        private JList listQuiz;
        private JComboBox<String> comboCategory;
        private JComboBox<String> comboStatus;
        private JComboBox<Integer> comboLeerjaar;
        //private final JScrollPane paneExercise;
        //private final JScrollPane paneQuiz;
		
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
			lb_01.setBounds(36, 24, 112, 15);
			getContentPane().add(lb_01);
			
			comboStatus = new JComboBox<String>();
			comboStatus.addItem("UNDER CONSTRUCTION");
			comboStatus.addItem("COMPLETED");
			comboStatus.addItem("READY");
			comboStatus.addItem("LAST CHANCE");
			comboStatus.addItem("CLOSED");
			
			comboLeerjaar = new JComboBox<Integer>();
			
			comboCategory = new JComboBox<String>();
			
			listExercise = new JList<>();
			listExercise.setVisibleRowCount(10);
			listExercise.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			listQuiz = new JList<>();
			listQuiz.setVisibleRowCount(10);
			listQuiz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
               
			lb_02 = new JLabel("Show category");
			lb_03 = new JLabel("Quiz");
			lb_04 = new JLabel("Exercise");
			table = new JTable();
			
			btn_update = new JButton("Search");
			btn_update.addActionListener(this);
			
			btn_edit = new JButton("Edit");
			btn_edit.addActionListener(this);
			
			btn_delete = new JButton("Delete");
			btn_delete.addActionListener(this);
			
			this.quizCatalog.readQuizzesFromFile();
			this.exCatalog.readExercisesFromFile();
			
			/*
			//implements interface Actionlistener
			btn_update.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Search!");
					//uit te voeren code					
				}
			});
			*/
			
			//layout
			
			panel_01.add(txt_01);
			panel_01.add(txt_02);
			panel_01.add(lb_01);
			panel_01.add(btn_update);
			panel_01.add(btn_edit);
			panel_01.add(btn_delete);
			panel_01.add(lb_02);
			panel_02.add(lb_03);
			panel_02.add(lb_04);
			panel_02.add(table);
			panel_02.add(comboStatus);
			panel_02.add(comboLeerjaar);
			panel_02.add(comboCategory);
			
			add(panel_01);
			add(panel_02);
			add(new JScrollPane(listQuiz));
			add(new JScrollPane(listExercise));
			
			listQuiz.addListSelectionListener(
					new ListSelectionListener() // anonymous inner class
					{
						// handle list selection events
						public void valueChanged( ListSelectionEvent event )
						{
							//getContentPane().setBackground(
							//		quizCatalog[listQuiz.getSelectedIndex()] );
						} // end method valueChanged
					} // end anonymous inner class
					); // end call to addListSelectionListener
			
			
			//window
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(750,750);
			this.setVisible(true);
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			

			JButton source = (JButton)e.getSource();
			
			if(source == btn_update) {
				System.out.println("Search through e.getSource");
				JOptionPane.showMessageDialog(ChangeQuizView.this, String.format("You pressed: %s", e.getActionCommand() ));
			}
			else if(source == btn_edit) {
				System.out.println("Edit through e.getSource");
				JOptionPane.showMessageDialog(ChangeQuizView.this, String.format("You pressed: %s", e.getActionCommand() ));
			}
			else if(source == btn_delete) {
				System.out.println("Delete through e.getSource");
				JOptionPane.showMessageDialog(ChangeQuizView.this, String.format("You pressed: %s", e.getActionCommand() ));
			}
		}
		
		
		

}

package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.ExerciseCatalog;
import model.QuizCatalog;
import model.Exercise.ExerciseCategory;
import model.QuizStatus;

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
		private JPanel panel_01, panel_02, panel_03, panel_04;
		private JTextField txt_01, txt_02;
		private JLabel lb_01, lb_02, lb_03, lb_04;
		private JTable table;
		private JButton btn_update, btn_edit, btn_delete;
		private JList listExercise;
        private JList listQuiz;
        private JComboBox comboCategory;
        private JComboBox comboStatus;
        private JComboBox<Integer> comboLeerjaar;
        //private final JScrollPane paneExercise;
        //private final JScrollPane paneQuiz;

		public ChangeQuizView() {
			super("Change quiz");

			
			setLayout(new FlowLayout());
			
			//Panel 01
			
			Dimension size_01 = getPreferredSize();
			size_01.width = 500;
			size_01.height = 200;
			
			panel_01 = new JPanel(); 
			panel_01.setPreferredSize(size_01);
			
			panel_01.setBorder(BorderFactory.createTitledBorder("Quiz"));
			
			//Panel 02
			
			Dimension size_02 = getPreferredSize();
			size_02.width = 500;
			size_02.height = 200;
			
			panel_02 = new JPanel();
			panel_02.setPreferredSize(size_02);
			
			panel_02.setBorder(BorderFactory.createTitledBorder("Exercise"));
			
			//Panel 03
			
			Dimension size_03 = getPreferredSize();
			size_03.width = 500;
			size_03.height = 350;
			
			panel_03 = new JPanel();
			panel_03.setPreferredSize(size_03);
			
			panel_03.setBorder(BorderFactory.createTitledBorder("Controls"));
			
			//Panel 04
			
			Dimension size_04 = getPreferredSize();
			size_04.width = 500;
			size_04.height = 350;
			
			panel_04 = new JPanel();
			panel_04.setPreferredSize(size_03);
			
			panel_04.setBorder(BorderFactory.createTitledBorder("Controls"));
			
			//Components
			
			txt_01 = new JTextField("Find quiz", 25);
			txt_02 = new JTextField("Find exercise", 15);
			
			lb_01 = new JLabel("Status");
			lb_01.setBounds(36, 24, 112, 15);
			getContentPane().add(lb_01);
			
			comboStatus = new JComboBox(QuizStatus.values());
			
			
			Integer[] leerJaren = {1, 2, 3, 4};
			comboLeerjaar = new JComboBox<Integer>(leerJaren);
			
			
			comboCategory = new JComboBox(ExerciseCategory.values());
			
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
			/*
			this.quizCatalog.readQuizzesFromFile();
			this.exCatalog.readExercisesFromFile();
			*/
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
			
			panel_03.add(new JScrollPane(listQuiz));
			panel_04.add(new JScrollPane(listExercise));
			
			add(panel_01);
			add(panel_02);
			add(panel_03);
			add(panel_04);
			
			GridBagConstraints gbc = new GridBagConstraints();
			
			/*

			
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
			*/
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(1200,750);
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
		
		public static void main(String[] args) {
	        
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	ChangeQuizView ex = new ChangeQuizView();
	                ex.setVisible(true);
	            }
	        });
	    }
		

}

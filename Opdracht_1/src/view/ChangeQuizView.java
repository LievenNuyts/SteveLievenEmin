package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
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

import model.Exercise.ExerciseCategory;
import model.ExerciseCatalog;
import model.QuizCatalog;
import model.QuizStatus;


/**
 * 
 * @author Steve
 * @version 6/11/2013
 * 
 */

public class ChangeQuizView extends JFrame implements ActionListener, Observer{
	
		private static final long serialVersionUID = 1L;
		private QuizCatalog quizCatalog;
		private ExerciseCatalog exCatalog;
		private JPanel panel_01, panel_02, panel_03, panel_04;
		private JTextField txt_01;
		private JLabel lb_01, lb_02, lb_03, lb_04, lb_05;
		private JTable table;
		private JButton btn_update, btn_edit, btn_delete, btn_search_quiz;

		private JList listExercise;
        private JList listQuiz;
        private JComboBox comboCategory;
        private JComboBox comboStatus;
        private JComboBox<Integer> comboLeerjaar;
		private DefaultListModel listModelEx;
		private DefaultListModel listModelQuiz;
        //private final JScrollPane paneExercise;
        //private final JScrollPane paneQuiz;

		public ChangeQuizView() {
			super("Change quiz");

			
			setLayout(new FlowLayout());
			
			//Panel 01
			
			Dimension size_01 = getPreferredSize();
			size_01.width = 700;
			size_01.height = 100;
			
			panel_01 = new JPanel(); 
			panel_01.setPreferredSize(size_01);
			
			panel_01.setBorder(BorderFactory.createTitledBorder("Search Controls"));
			
			//Panel 02
			
			Dimension size_02 = getPreferredSize();
			size_02.width = 300;
			size_02.height = 100;
			
			panel_02 = new JPanel();
			panel_02.setPreferredSize(size_02);
			
			panel_02.setBorder(BorderFactory.createTitledBorder("Update Controls"));
			
			//Panel 03
			
			Dimension size_03 = getPreferredSize();
			size_03.width = 500;
			size_03.height = 350;
			
			panel_03 = new JPanel();
			panel_03.setPreferredSize(size_03);
			
			panel_03.setBorder(BorderFactory.createTitledBorder("Quiz"));
			
			//Panel 04
			
			Dimension size_04 = getPreferredSize();
			size_04.width = 500;
			size_04.height = 350;
			
			panel_04 = new JPanel();
			panel_04.setPreferredSize(size_03);
			
			panel_04.setBorder(BorderFactory.createTitledBorder("Exercise"));
			
			//Components
			
			//panel 01
			
			txt_01 = new JTextField("Find quiz", 50);
			
			btn_search_quiz = new JButton("Search");
			btn_search_quiz.addActionListener(this);
			
			
			
			
			lb_01 = new JLabel("Status: ");
			lb_01.setBounds(36, 24, 112, 15);
			getContentPane().add(lb_01);
			
			comboStatus = new JComboBox(QuizStatus.values());
			
			
			Integer[] leerJaren = {1, 2, 3, 4};
			comboLeerjaar = new JComboBox<Integer>(leerJaren);
			
			
			comboCategory = new JComboBox(ExerciseCategory.values());
			
			listModelEx = new DefaultListModel();  
			
			 /*  
			public void actionPerformed(ActionEvent evt) {  
			    listModel.addElement("new");  
			  }  
			*/
			listExercise = new JList<>(listModelEx);
			listModelEx.addElement("1 - New exercise");  
			listModelEx.addElement("2 - New exercise");
			listModelEx.addElement("3 - New exercise");
			listModelEx.addElement("4 - New exercise");
			listModelEx.addElement("5 - New exercise");
			listExercise.setVisibleRowCount(10);
			listExercise.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			listModelQuiz = new DefaultListModel(); 
			
			listQuiz = new JList<>(listModelQuiz);
			listModelQuiz.addElement("1 - New Quiz");
			listModelQuiz.addElement("2 - New Quiz");
			listModelQuiz.addElement("3 - New Quiz");
			listModelQuiz.addElement("4 - New Quiz");
			listModelQuiz.addElement("5 - New Quiz");
			listQuiz.setVisibleRowCount(10);
			listQuiz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
              
			lb_02 = new JLabel("  Show category: ");
			lb_05 = new JLabel("  Grade: ");
			table = new JTable();
			
			
			btn_update = new JButton("Update");
			//btn_update.addActionListener(this);
			
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
			panel_01.add(btn_search_quiz);
			
			panel_01.add(lb_01);
			panel_01.add(comboStatus);
			
			panel_01.add(lb_05);
			panel_01.add(comboLeerjaar);
			
			panel_01.add(lb_02);
			panel_01.add(comboCategory);
			
			
			//exCatalog.readExercisesFromFile();
			
			
			
			panel_02.add(table);
			
			panel_02.add(btn_update);
			panel_02.add(btn_edit);
			panel_02.add(btn_delete);
			
			panel_03.add(new JScrollPane(listQuiz));
			
			panel_04.add(new JScrollPane(listExercise));
			
			add(panel_01);
			add(panel_02);
			add(panel_03);
			add(panel_04);
			
					
			//window
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(1100,550);
			this.setVisible(true);
			
			
		}
		
		public void update(Observable obs, Object obj) {

			//who called us and what did they send?
			System.out.println ("View      : Observable is " + obs.getClass() + ", object passed is " + obj.getClass());

			//model Push 

			//listQuiz.add(quizCatalog);	

	    	} //update()

		public void addController(ActionListener controller){
			System.out.println("View      : adding controller");
			btn_update.addActionListener(controller);	//need instance of controller before can add it as a listener 
		} //addController()
		
		
		    

		@Override
		public void actionPerformed(ActionEvent e) {
			

			JButton source = (JButton)e.getSource();
			
			if(source == btn_update) {
				System.out.println("Search through e.getSource");
				JOptionPane.showMessageDialog(ChangeQuizView.this, String.format("You pressed: %s", e.getActionCommand() ));
			}
			else if(source == btn_search_quiz) {
				System.out.println("Edit through e.getSource");
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

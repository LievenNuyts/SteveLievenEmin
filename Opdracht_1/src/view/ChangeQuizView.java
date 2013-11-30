package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.List;

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
import javax.swing.table.DefaultTableModel;

import model.Exercise;
import model.Exercise.ExerciseCategory;
import model.Quiz;
import model.QuizStatus;
import model.Teacher;


/**
 * 
 * @author Steve
 * @version 6/11/2013
 * 
 */

public class ChangeQuizView extends JFrame {
	
		private static final long serialVersionUID = 1L;


		private JPanel panel_01, panel_02, panel_03, panel_04, panel_05, panel_06;
		private JTextField txt_01;
		private JLabel lb_01, lb_02, lb_03, lb_04, lb_05;

		private JButton btn_update, btn_edit, btn_delete, btn_search_quiz;

		private JList listExercise;
        private JList listQuiz;
        
        private JComboBox comboCategory;
        private JComboBox comboStatus;
        private JComboBox comboAuthor;
        private JComboBox<Integer> comboLeerjaar;
		private JComboBox<String> comboSortExercises;
        
		private DefaultListModel<Exercise> listModelEx;
		private DefaultListModel<Quiz> listModelQuiz;	
		
		private DefaultTableModel tableExercisesModel;
		
		private JTable addedToTableExercises;
		
		private String col[] = {"Opdracht", "MaxScore"}; 
		
		private JScrollPane paneTable;
		
		private String[] sort;
		
        //private final JScrollPane paneExercise;
        //private final JScrollPane paneQuiz;

		public ChangeQuizView() {
			super("Change quiz");

			
			setLayout(new FlowLayout());
			
			//Panel 01
			
			Dimension size_01 = getPreferredSize();
			size_01.width = 750;
			size_01.height = 75;
			
			panel_01 = new JPanel(); 
			panel_01.setPreferredSize(size_01);
			
			panel_01.setBorder(BorderFactory.createTitledBorder("Search Controls"));
			
			//panel 05
			
			Dimension size_05 = getPreferredSize();
			size_05.width = 750;
			size_05.height = 100;
			
			panel_05 = new JPanel(); 
			panel_05.setPreferredSize(size_05);
			
			panel_05.setBorder(BorderFactory.createTitledBorder("Parameter Controls"));
			
			//Panel 02
			
			Dimension size_02 = getPreferredSize();
			size_02.width = 750;
			size_02.height = 75;
			
			panel_02 = new JPanel();
			panel_02.setPreferredSize(size_02);
			
			panel_02.setBorder(BorderFactory.createTitledBorder("User Controls"));
			
			//Panel 03
			
			Dimension size_03 = getPreferredSize();
			size_03.width = 750;
			size_03.height = 150;
			
			panel_03 = new JPanel();
			panel_03.setPreferredSize(size_03);
			
			panel_03.setBorder(BorderFactory.createTitledBorder("Quiz"));
			
			//Panel 04
			
			Dimension size_04 = getPreferredSize();
			size_04.width = 375;
			size_04.height = 300;
			
			panel_04 = new JPanel();
			panel_04.setPreferredSize(size_04);
			
			panel_04.setBorder(BorderFactory.createTitledBorder("Exercise"));
			
			//panel 06
			
			Dimension size_06 = getPreferredSize();
			size_06.width = 375;
			size_06.height = 300;
			
			panel_06 = new JPanel();
			panel_06.setPreferredSize(size_06);
			
			panel_06.setBorder(BorderFactory.createTitledBorder("Exercises in Quiz"));
			
			//Components
			
			//panel 01
			
			txt_01 = new JTextField("Find quiz", 35);
			
			btn_search_quiz = new JButton("Search");
			//btn_search_quiz.addActionListener(this);
			

			lb_01 = new JLabel(" Status: ");
			lb_01.setBounds(36, 24, 112, 15);
			getContentPane().add(lb_01);
			
			comboStatus = new JComboBox(QuizStatus.values());
			
			
			Integer[] leerJaren = {1, 2, 3, 4};
			comboLeerjaar = new JComboBox<Integer>(leerJaren);
			
			
			comboCategory = new JComboBox(ExerciseCategory.values());
			comboCategory.addItem("Alle");
			comboCategory.setSelectedIndex(comboCategory.getItemCount() - 1);
			
			lb_03 = new JLabel(" Author: ");
			getContentPane().add(lb_03);
			comboAuthor = new JComboBox(Teacher.values());
			
			//populate list exercises
			
			listModelEx = new DefaultListModel<Exercise>();
			
			listExercise = new JList<>(listModelEx);
			
			listExercise.setVisibleRowCount(5);
			
			Dimension size_list_ex = getPreferredSize();
			size_list_ex.width = 300;
			size_list_ex.height = 50;
			listExercise.setPreferredSize(size_list_ex);
			listExercise.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			sort = new String[]{ "Geen", "Categorie", "Vraag"};
			comboSortExercises = new JComboBox<String>(sort);
			
			//populate list quiz
			
			listModelQuiz = new DefaultListModel<Quiz>(); 
			
			listQuiz = new JList<>(listModelQuiz);
			
			listQuiz.setVisibleRowCount(6);
			
			Dimension size_list_quiz = getPreferredSize();
			size_list_quiz.width = 500;
			size_list_quiz.height = 40;
			listQuiz.setPreferredSize(size_list_quiz);
			listQuiz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
              
			lb_02 = new JLabel("  Show category: ");
			lb_05 = new JLabel("  Grade: ");
			
			//populate Ex in Quiz
			
			this.tableExercisesModel = new DefaultTableModel(col, 0);
			this.addedToTableExercises = new JTable(tableExercisesModel){
				@Override
				public boolean isCellEditable(int row, int col) {
				     switch (col) {
				         case 0:
				        	 return false;
				         case 1:
				             return true;
				         default:
				             return false;
				      }
				}
			};
			
			this.paneTable = new JScrollPane(addedToTableExercises);
			Dimension size_paneTable = getPreferredSize();
			size_list_ex.width = 200;
			size_list_ex.height = 45;
			paneTable.setPreferredSize(size_paneTable);
			
			
			btn_update = new JButton("Update");
			//btn_update.addActionListener(this);
			
			btn_edit = new JButton("Edit");
			//btn_edit.addActionListener(this);
			
			btn_delete = new JButton("Delete");
			//btn_delete.addActionListener(this);
			
			//layout
			
			panel_01.add(txt_01);
			panel_01.add(btn_search_quiz);
			
			panel_05.add(lb_01);
			panel_05.add(comboStatus);
			
			panel_05.add(lb_05);
			panel_05.add(comboLeerjaar);
			
			panel_05.add(lb_02);
			panel_05.add(comboCategory);
			
			panel_05.add(lb_03);
			panel_05.add(comboAuthor);
			
			panel_05.add(comboSortExercises);
			
			panel_02.add(btn_update);
			panel_02.add(btn_edit);
			panel_02.add(btn_delete);
			
			panel_03.add(new JScrollPane(listQuiz));
			
			panel_04.add(new JScrollPane(listExercise));
			panel_06.add(paneTable);
			
			add(panel_01);
			add(panel_05);
			add(panel_02);
			add(panel_03);
			add(panel_04);
			add(panel_06);
			
			
					
			//window
			
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(800,700);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			
			
		}
		
		//Selectors
		
		/**
		 * @return
		 */
		public String getAuthor(){
			return String.valueOf(comboAuthor.getSelectedItem());
		}
		
		/**
		 * @return
		 */
		public String getStatus(){
			return String.valueOf(comboStatus.getSelectedItem());
		}
		
		/**
		 * @return
		 */
		public String getSelectedValueToList(){
			return String.valueOf(listExercise.getSelectedValue());
		}
		
		/**
		 * @return
		 */
		public String getSelectedExerciseValueFromTable(){
			return String.valueOf(tableExercisesModel.getValueAt(addedToTableExercises.getSelectedRow(), 0));
		}

		/**
		 * @return
		 */
		public DefaultTableModel getTableExercisesModel(){
			return tableExercisesModel;
		}
		
		/**
		 * @return
		 */
		public int getSelectedRow(){
			return addedToTableExercises.getSelectedRow();
		}
		
		/**
		 * @return
		 */
		public String getSelectedCategory(){
			return String.valueOf(comboCategory.getSelectedItem());
		}
		
		/**
		 * @return
		 */
		public String getSelectedSortByValue(){
			return String.valueOf(comboSortExercises.getSelectedItem());
		}
		
		
		public String getQuizTitle() {
			return txt_01.getText();
		}
		
		public Integer getGrade(){
			return Integer.parseInt(comboLeerjaar.getSelectedItem().toString());
		}
		
		public String getCategory(){
			return comboCategory.getSelectedItem().toString();
		}
		
		public DefaultListModel<Quiz> getListQuizModel(){
			return listModelQuiz;
		}
		
		public DefaultListModel<Exercise> getListExModel(){
			return listModelEx;
		}
		
		
		//setExercises
		
		
		public void setExercisesList(List<Exercise> exerciseList){
			DefaultListModel listModel = new DefaultListModel();
			
			for (Exercise ex : exerciseList){

				listModel.addElement("("+ String.valueOf(ex.getCategory()).toUpperCase().substring(0, 3) + ") " 
						+ ex.getQuestion());
			}
			
			this.listExercise.setModel(listModel);
		}
		
		public void setQuizList(List<Quiz> quizList){
			DefaultListModel listModel = new DefaultListModel();
			
			for (Quiz q : quizList){
				listModel.addElement(String.valueOf(q.getSubject()) + " - (" + q.getDate()+ ")");
			}
			this.listQuiz.setModel(listModel);
		}
		


		
		
		// events
		
		public void addUpdateListener(ActionListener listenForUpdateButton) {

			btn_update.addActionListener(listenForUpdateButton);
		}
		
		public void addDeleteListener(ActionListener listenForDeleteButton) {
			
			btn_delete.addActionListener(listenForDeleteButton);
		}
		
		public void addEditListener(ActionListener listenForEditButton) {
			
			btn_edit.addActionListener(listenForEditButton);
		}

		public void addSearchListener(ActionListener listenForSearchButton) {
	
			btn_search_quiz.addActionListener(listenForSearchButton);
		}

		// Open a popup that contains the error message passed

		public void displayErrorMessage(String errorMessage) {

			JOptionPane.showMessageDialog(this, errorMessage);
		}

		/*

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

		
		*/

}

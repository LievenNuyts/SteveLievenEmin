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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Exercise;
import model.Exercise.ExerciseCategory;
import model.Quiz;
import model.QuizExercise;
import model.QuizStatus;
import model.Teacher;
import controller.StartAppController;


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

		private JButton btn_update, btn_delete, btn_search_quiz;

		private JList <Exercise> listExercise;
        private JList <Quiz> listQuiz;
        private JList <QuizExercise> listExercisesInQuiz;
        
        private JComboBox comboCategory;
        private JComboBox comboStatus;
        private JComboBox comboAuthor;
        private JComboBox<Integer> comboLeerjaar;
		private JComboBox<String> comboSortExercises;
        
		private DefaultListModel<Exercise> listModelEx;
		private DefaultListModel<Quiz> listModelQuiz;	
		private DefaultListModel<QuizExercise> listModelExercisesInQuiz;
		
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
			size_01.width = 550;
			size_01.height = 100;
			
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
			size_02.width = 200;
			size_02.height = 100;
			
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
			size_04.height = 215;
			
			panel_04 = new JPanel();
			panel_04.setPreferredSize(size_04);
			
			panel_04.setBorder(BorderFactory.createTitledBorder("Available Exercises"));
			
			//panel 06
			
			Dimension size_06 = getPreferredSize();
			size_06.width = 375;
			size_06.height = 215;
			
			panel_06 = new JPanel();
			panel_06.setPreferredSize(size_06);
			
			panel_06.setBorder(BorderFactory.createTitledBorder("Exercises in Quiz"));
			
			
			
			//Components
			
			//panel 01
			
			//Search
			
			txt_01 = new JTextField("Find quiz", 35);
			
			btn_search_quiz = new JButton("Search");

			//panel 02
			
			//Status

			lb_01 = new JLabel(" Status: ");
			getContentPane().add(lb_01);
			comboStatus = new JComboBox(QuizStatus.values());
			
			//Grades
			
			lb_05 = new JLabel("  Grade: ");
			getContentPane().add(lb_05);
			Integer[] grades = {1, 2, 3, 4};
			comboLeerjaar = new JComboBox<Integer>(grades);
			
			//Category
			
			lb_02 = new JLabel("  Show category: ");
			getContentPane().add(lb_02);
			comboCategory = new JComboBox(ExerciseCategory.values());
			comboCategory.addItem("Alle");
			comboCategory.setSelectedIndex(comboCategory.getItemCount() - 1);
			
			//Author
			
			lb_03 = new JLabel("               Author: ");
			getContentPane().add(lb_03);
			comboAuthor = new JComboBox(Teacher.values());
			
			//Sort
			
			lb_04 = new JLabel(" Sort: ");
			getContentPane().add(lb_04);
			sort = new String[]{ "Geen", "Categorie", "Vraag"};
			comboSortExercises = new JComboBox<String>(sort);
			
			
			//Panel 03
			
			btn_update = new JButton("Add");
			
			btn_delete = new JButton("Delete");
			
			
			//Panel 04
			
			//populate list quiz
			
			listModelQuiz = new DefaultListModel<Quiz>(); 
			
			listQuiz = new JList<>(listModelQuiz);
			
			listQuiz.setVisibleRowCount(6);
			
			Dimension size_list_quiz = getPreferredSize();
			size_list_quiz.width = 700;
			size_list_quiz.height = 40;
			listQuiz.setPreferredSize(size_list_quiz);
			listQuiz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
              
			
			//Panel 05
			
			//populate list exercises
			
			listModelEx = new DefaultListModel<Exercise>();
			
			listExercise = new JList<>(listModelEx);
			
			listExercise.setVisibleRowCount(10);
			
			Dimension size_list_ex = getPreferredSize();
			size_list_ex.width = 350;
			size_list_ex.height = 50;
			listExercise.setPreferredSize(size_list_ex);
			listExercise.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			
			

			//Panel 06
			
			//populate ex in selected Quiz
			
			listQuiz.addListSelectionListener(new ListSelectionListener() {
			    public void valueChanged(ListSelectionEvent event) {
			        if (!event.getValueIsAdjusting()){
			            JList<Quiz> source = (JList)event.getSource();
			            
			            Quiz selected = (Quiz)source.getSelectedValue();	            
			            
			            getExercisesInQuiz(selected.getQuizExercises());
			        }
			    }
			});
			
			listModelExercisesInQuiz = new DefaultListModel<QuizExercise>();
			
			listExercisesInQuiz = new JList<>(listModelExercisesInQuiz);
			
			listExercisesInQuiz.setVisibleRowCount(10);
			
			Dimension size_list_exInQuiz = getPreferredSize();
			size_list_exInQuiz.width = 350;
			size_list_exInQuiz.height = 50;
			listExercisesInQuiz.setPreferredSize(size_list_exInQuiz);
			listExercisesInQuiz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			//get exercise
			
			listExercise.addListSelectionListener(new ListSelectionListener() {
			    public void valueChanged(ListSelectionEvent event) {
			        if (!event.getValueIsAdjusting()){
			            JList<Exercise> source = (JList)event.getSource();
			            
			            Exercise selected = (Exercise)source.getSelectedValue();	

			            getExercise(selected);

			        }
			    }
			});
			
			
			//JTable: not used
			/*
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
			
			*/
			
			
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
			panel_05.add(lb_04);
			panel_05.add(comboSortExercises);
			
			panel_02.add(btn_update);
			panel_02.add(btn_delete);
			
			panel_03.add(new JScrollPane(listQuiz));
			
			panel_04.add(new JScrollPane(listExercise));
			
			panel_06.add(new JScrollPane(listExercisesInQuiz));
			//panel_06.add(paneTable);
			
			
			//sequence panels
			
			add(panel_01);
			add(panel_02);
			add(panel_05);
			add(panel_03);
			add(panel_04);
			add(panel_06);
			
			
					
			//window

			this.setSize(800,620);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			
			
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent evt)
			    {	
			    	new StartAppController().startApp();
			    }
			});
			
			
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

				listModel.addElement(ex);
			}
			
			this.listExercise.setModel(listModel);
		}
		
		//setQuizzes
		
		public void setQuizList(List<Quiz> quizList){
			DefaultListModel listModel = new DefaultListModel();
			
			for (Quiz q : quizList){
				listModel.addElement(q);
			}
			this.listQuiz.setModel(listModel);
		}
		
		//add exercises in quiz to list
		
		public void getExercisesInQuiz(List<QuizExercise> exInQuizList){
			DefaultListModel listModel = new DefaultListModel<>();
			
			for (QuizExercise ex : exInQuizList){

				listModel.addElement(ex.getExercise().getQuestion());
			}
			this.listExercisesInQuiz.setModel(listModel);
		}
		
		public void getExercise(Exercise ex){
			System.out.println(ex.getQuestion()); //test
			DefaultListModel listModel = new DefaultListModel<>();
			
			listModel.addElement(ex.getQuestion());
			this.listExercisesInQuiz.setModel(listModel);
		}
		

		// events
		
		public void addUpdateListener(ActionListener listenForUpdateButton) {

			btn_update.addActionListener(listenForUpdateButton);
		}
		
		public void addDeleteListener(ActionListener listenForDeleteButton) {
			
			btn_delete.addActionListener(listenForDeleteButton);
		}
		

		public void addSearchListener(ActionListener listenForSearchButton) {
	
			btn_search_quiz.addActionListener(listenForSearchButton);
		}
		
		

		// Open a popup that contains the error message passed

		public void displayErrorMessage(String errorMessage) {

			JOptionPane.showMessageDialog(this, errorMessage);
		}
}

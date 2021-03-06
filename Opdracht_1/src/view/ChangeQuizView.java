package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import statePattern.StateContext;
import model.Exercise;
import model.Quiz;
import model.QuizExercise;
import model.QuizStatus;
import model.Teacher;
import controller.ChangeQuizController;
import controller.StartAppController;


/**
 * 
 * @author Steve
 * @version 6/11/2013
 * 
 */

public class ChangeQuizView extends JFrame {
	
		private static final long serialVersionUID = 1L;

		private JTextField txt_01, txt_02;
		private JLabel lb_01, lb_03, lb_04, lb_05, lb_Quiz, lb_exInQuiz, lb_Ex;

		private JButton btn_add, btn_update, btn_delete, btn_search_quiz;

		private JList <Exercise> listExercise;
        private JList <Quiz> listQuiz;
        private JList <QuizExercise> listExercisesInQuiz;
        
        private JComboBox <QuizStatus> comboStatus;
        private JComboBox <Teacher> comboAuthor;
        private JComboBox<Integer> comboLeerjaar;
        
		private DefaultListModel<Exercise> listModelEx;
		private DefaultListModel<Quiz> listModelQuiz;	
		private DefaultListModel<QuizExercise> listModelExercisesInQuiz;
		
        private final JScrollPane paneExercise;
        private JScrollPane paneQuiz;
        private final JScrollPane paneExInQuiz;


		public ChangeQuizView() {
			super("Quiz wijzigen");
			setResizable(false); //niet resizable
			
			Toolkit tk = Toolkit.getDefaultToolkit();
			
			Dimension dim = tk.getScreenSize();
			
			int x = (dim.width / 2) - (this.getWidth() / 2);
			int y = (dim.height / 2) - (this.getHeight() / 2);
						
			this.setLocation(x, y);
			
			this.setSize(800,600);

			this.setVisible(true); 
			this.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent evt)
			    {	
			    	ChangeQuizView.this.dispose();
			    	new StartAppController().startApp();
			    }
			});
			
			final JPanel mainPanel = new JPanel();

			mainPanel.setBorder(BorderFactory.createTitledBorder("Quiz Control"));
			
			setLocationRelativeTo(null); //positie zetten
								
			//Components
			
			//Zoekfunctie
			
			txt_01 = new JTextField("Zoek quiz", 20);			
			btn_search_quiz = new JButton("Zoek");
			
			//Buttons
			
			btn_add = new JButton("Voeg toe");			
			btn_delete = new JButton("Verwijder");			
			btn_update = new JButton("Update");
			
			//Status

			lb_01 = new JLabel("Status: ");
			comboStatus = new JComboBox <QuizStatus>(QuizStatus.values());
			
			//Grades
			
			lb_05 = new JLabel("Leerjaar: ");
			Integer[] grades = {1, 2, 3, 4};
			comboLeerjaar = new JComboBox<Integer>(grades);
			
			//Title
			
			lb_04 = new JLabel("Quiztitel: ");
			txt_02 = new JTextField();
			
			//Author
			
			lb_03 = new JLabel("Leraar: ");
			comboAuthor = new JComboBox <Teacher>(Teacher.values());

			//populate list quiz
			
			lb_Quiz = new JLabel("Beschikbare quizzes:");			
			listModelQuiz = new DefaultListModel<Quiz>(); 			
			listQuiz = new JList<>(listModelQuiz);
			listQuiz.setVisibleRowCount(10);
			listQuiz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			
			paneQuiz = new JScrollPane(listQuiz);
			
			//populate ex & parameters in selected Quiz
			
			lb_exInQuiz = new JLabel("Vragen in quiz:");			
			listModelExercisesInQuiz = new DefaultListModel<QuizExercise>();			
			listExercisesInQuiz = new JList<>(listModelExercisesInQuiz);			
			listExercisesInQuiz.setVisibleRowCount(10);
			listExercisesInQuiz.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			
			paneExInQuiz = new JScrollPane(listExercisesInQuiz);
			
			//populate list exercises
			
			lb_Ex = new JLabel("Beschikbare vragen:");			
			listModelEx = new DefaultListModel<Exercise>();
			listExercise = new JList<>(listModelEx);			
			listExercise.setVisibleRowCount(10);
			listExercise.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);			
			paneExercise = new JScrollPane(listExercise);
			
			//Layout Manager
			
			mainPanel.setLayout(new GridBagLayout());
			
			GridBagConstraints gbc = new GridBagConstraints();

			//Default values
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.gridwidth = 4;
			gbc.gridheight = 1;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.insets = new Insets(2,2,2,2);
			gbc.anchor = GridBagConstraints.CENTER;
			gbc.fill = GridBagConstraints.BOTH;
			
			//update constraints
			
			mainPanel.add(txt_01, gbc);
			gbc.gridx = 5;
			gbc.gridwidth = 2;
			mainPanel.add(btn_search_quiz, gbc);
			gbc.gridy = 2;
			gbc.gridx = 1;
			gbc.gridwidth = 1;
			mainPanel.add(lb_05, gbc);
			gbc.gridx = 2;
			gbc.gridwidth = 2;
			mainPanel.add(comboLeerjaar, gbc);
			gbc.gridx = 4;
			gbc.gridwidth = 1;
			mainPanel.add(lb_04, gbc);
			gbc.gridx = 5;
			gbc.gridwidth = 2;
			mainPanel.add(txt_02, gbc);
			gbc.gridy = 3;
			gbc.gridx = 1;
			gbc.gridwidth = 1;
			mainPanel.add(lb_03, gbc);
			gbc.gridx = 2;
			gbc.gridwidth = 2;
			mainPanel.add(comboAuthor, gbc);
			gbc.gridx = 4;
			gbc.gridwidth = 1;
			mainPanel.add(lb_01, gbc);
			gbc.gridx = 5;
			gbc.gridwidth = 2;
			mainPanel.add(comboStatus, gbc);
			gbc.gridy = 4;
			gbc.gridx = 1;
			gbc.gridwidth = 2;
			mainPanel.add(btn_add, gbc);
			gbc.gridx = 3;
			mainPanel.add(btn_delete, gbc);
			gbc.gridx = 5;
			mainPanel.add(btn_update, gbc);
			gbc.gridy = 5;
			gbc.gridx = 1;
			gbc.gridwidth = 1;
			mainPanel.add(lb_Quiz, gbc);
			gbc.gridx = 4;
			mainPanel.add(lb_exInQuiz, gbc);
			gbc.gridy = 6;
			gbc.gridx = 1;
			gbc.gridwidth = 3;
			mainPanel.add(paneQuiz, gbc);
			gbc.gridx = 4;
			mainPanel.add(paneExInQuiz, gbc);
			gbc.gridy = 7;
			gbc.gridx = 1;
			gbc.gridwidth = 1;
			mainPanel.add(lb_Ex, gbc);
			gbc.gridy = 8;
			gbc.gridx = 1;
			gbc.gridwidth = 6;
			mainPanel.add(paneExercise, gbc);
			gbc.gridwidth = 1;

			add(mainPanel);

		}
		
		//Selectors
		
		/**
		 * @return
		 */
		public Teacher getAuthor(){
			return (Teacher) comboAuthor.getSelectedItem();
		}

		/**
		 * @return
		 */
		public QuizStatus getStatus(){
			return (QuizStatus) comboStatus.getSelectedItem();
		}
		
		/**
		 * @return
		 */
		public Integer getGrade(){
			return Integer.parseInt(comboLeerjaar.getSelectedItem().toString());
		}
		
		/**
		 * @return
		 */
		public String getQuizTitle() {
			return txt_02.getText();
		}
		
		/**
		 * @return
		 */
		public String getSearchText() {
			if (txt_01 == null){
				System.out.println(txt_01.getText() + "has null value");
				return "";
			}
			else {
				System.out.println(txt_01.getText() + "has value");
				return txt_01.getText();
			}
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
		public Exercise getSelectedExerciseValueFromList(){
			return listExercise.getSelectedValue(); 
		}
		
		/**
		 * @return
		 */
		public Quiz getSelectedQuizValueFromList(){
			System.out.println(listQuiz.getSelectedValue().getSubject());
			return listQuiz.getSelectedValue();
		}

		/**
		 * @return
		 */
		public QuizExercise getSelectedQuizExerciseValueFromList(){
			return listExercisesInQuiz.getSelectedValue();
		}
			
		/**
		 * @return
		 */
		public DefaultListModel <Quiz> getListQuizModel(){
			return listModelQuiz;
		}
		
		/**
		 * @return
		 */
		public DefaultListModel <Exercise> getListExModel(){
			return listModelEx;
		}
		
		
		//setExercises
		
		/**
		 * @return
		 */
		public void setExercisesList(List<Exercise> exerciseList){
			DefaultListModel <Exercise> listModel = new DefaultListModel<Exercise>();
			
			for (Exercise ex : exerciseList){

				listModel.addElement(ex);
			}
			
			this.listExercise.setModel(listModel);
		}
		
		/**
		 * @return
		 */
		public JList<Exercise> getExercisesList() {
			return listExercise;
		}
		
		//setQuizzes
		
		/**
		 * @return
		 */
		public void setQuizList(List<Quiz> quizList){
			DefaultListModel<Quiz> listModel = new DefaultListModel<Quiz>();
			
			for (Quiz q : quizList){
				listModel.addElement(q);
			}
			this.listQuiz.setModel(listModel);
		}
		
		//add exercises in quiz to list
		
		/**
		 * @return
		 */
		public void getExercisesInQuiz(List<QuizExercise> exInQuizList){
			DefaultListModel<QuizExercise>listModel = new DefaultListModel<QuizExercise>();
			
			for (QuizExercise ex : exInQuizList){

				listModel.addElement(ex);
			}
			this.listExercisesInQuiz.setModel(listModel);
		}
		
		/**
		 * @return
		 */
		public JButton getUpdateButton(){
			return this.btn_update;
		}
		
		public void UpdateExerciseInQuiz(){
			int index = listQuiz.getSelectedIndex();
			if(index == 0){
				listQuiz.setSelectedIndex(1);
			}
			else{
				listQuiz.setSelectedIndex(0);
			}
			listQuiz.setSelectedIndex(index);
			JOptionPane.showMessageDialog(null, "De quiz werd geüpdatet.");
		}
		
		public JComboBox <Teacher> getComboAuthor(){
			 return this.comboAuthor;
		}
			 
		public JComboBox <Integer> getComboGrade(){
			return this.comboLeerjaar;
		}
		
		public JComboBox <QuizStatus> getComboStatus(){
			 return this.comboStatus;
		}
			 
		public JTextField getTitleTextField(){
			return this.txt_02;
		}
			
		
			 

		// events
		
		public void addAddListener(ActionListener listenForAddButton) {
			
			btn_add.addActionListener(listenForAddButton);
		}
		
		public void addUpdateListener(ActionListener listenForUpdateButton) {

			btn_update.addActionListener(listenForUpdateButton);
		}
		
		public void addDeleteListener(ActionListener listenForDeleteButton) {
			
			btn_delete.addActionListener(listenForDeleteButton);
		}
		
		public void addSearchListener(ActionListener listenForSearchButton) {
	
			btn_search_quiz.addActionListener(listenForSearchButton);
		}
		
		public void addSelectListener(ListSelectionListener listenForSelectAction) {
			
			listQuiz.addListSelectionListener(listenForSelectAction);
		}

		// Open a popup that contains the error message passed

		public void displayErrorMessage(String errorMessage) {

			JOptionPane.showMessageDialog(null, errorMessage);
		}
}

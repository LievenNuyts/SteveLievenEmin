/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale.Category;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import model.Exercise;
import model.Exercise.ExerciseCategory;
import model.QuizStatus;
import model.Teacher;

/**
 * @author java
 *
 */
public class CreateQuizView extends JFrame {
	
	// Elements of top panel
	private String[] grades;
	private JLabel subjectLabel;
	private JLabel authorLabel;
	private JLabel gradeLabel;
	private JLabel stateLabel;
	private JTextField subjectTextField;
	private JComboBox gradesComboBox;
	private JComboBox authorsComboBox;
	private JComboBox stateComboBox;
	private JButton addQuizButton;
	
	// Elements of bottom panel
	private String[] sort;
	private JLabel showExerciseLabel;
	private JLabel sortExerciseLabel;
	private JLabel addedExerciseLabel;
	private JLabel amountExercisesLabel;
	private JComboBox categoriesComboBox;
	private JComboBox sortExercisesComboBox;
	private JButton moveUpButton;
	private JButton addToQuizButton;
	private JButton removeFromQuizButton;
	private JList exercisesList;
	private JList addedExercisesList;
	private DefaultListModel listModel2;
	
	private DefaultTableModel dataModel;
	private JTable addedExercisesTable;
	private String col[] = {"Opdracht", "MaxScore"}; 
	private JScrollPane paneTable;
	
	
	public CreateQuizView() {  

		// Elements of top panel
		this.grades = new String[]{ "1", "2", "3", "4"};
		this.subjectLabel = new JLabel("Onderwerp: ");
		this.authorLabel = new JLabel("Auteur: ");
		this.gradeLabel = new JLabel("Klas: ");
		this.stateLabel = new JLabel("Status: ");
		this.subjectTextField = new JTextField(10);
		this.gradesComboBox = new JComboBox(grades);
		this.authorsComboBox = new JComboBox(Teacher.values());
		this.stateComboBox = new JComboBox(QuizStatus.values());
		this.addQuizButton = new JButton("Registreer nieuwe quiz");
		
		// Elements of bottom panel
		this.sort = new String[]{ "geen", "categorie", "vraag"};
		this.showExerciseLabel = new JLabel("Toon opdrachten van categorie: ");
		this.sortExerciseLabel = new JLabel("Sorteer opdrachten op: ");
		this.addedExerciseLabel = new JLabel("Aantal toegevoegde opdrachten: ");
		this.amountExercisesLabel = new JLabel("0");
		this.categoriesComboBox = new JComboBox(ExerciseCategory.values());
		this.categoriesComboBox.addItem("Alle");
		this.categoriesComboBox.setSelectedIndex(categoriesComboBox.getItemCount() - 1);
		this.sortExercisesComboBox = new JComboBox(sort);
		this.moveUpButton = new JButton("^^^^");
		this.addToQuizButton = new JButton("--->");
		this.removeFromQuizButton = new JButton("<---");

		this.listModel2 = new DefaultListModel();
		this.exercisesList = new JList();
		this.addedExercisesList = new JList(listModel2);
		
		this.dataModel = new DefaultTableModel(col, 0);
		this.addedExercisesTable = new JTable(dataModel){
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
		
		this.paneTable = new JScrollPane(addedExercisesTable);
		
		initUI();
    }
	
	/**
	 * UI
	 */
	private void initUI() {
		JPanel panelMain = new JPanel();
		JPanel panelTop = new JPanel();
		JPanel panelBottom = new JPanel();
		
		panelMain.add(panelTop);
		panelMain.add(panelBottom);
		getContentPane().add(panelMain);
		
		panelTop.setBorder(BorderFactory.createLineBorder(Color.black));
		panelBottom.setBorder(BorderFactory.createLineBorder(Color.black));
		
		panelMain.setLayout(new GridBagLayout());
		panelTop.setLayout(new GridBagLayout());
		panelBottom.setLayout(new GridBagLayout());
		
		
		// Layout of the main table
		GridBagConstraints gcM = new GridBagConstraints();
		
		gcM.fill = GridBagConstraints.HORIZONTAL;
		gcM.weightx = 0.5;
		gcM.weighty = 0.25;
		gcM.gridx = 0;
		gcM.gridy = 0;
		panelMain.add(panelTop, gcM);
		
		gcM.fill = GridBagConstraints.BOTH;
		gcM.weightx = 0.5;
		gcM.weighty = 1;
		gcM.gridx = 0;
		gcM.gridy = 1;
		panelMain.add(panelBottom, gcM);
		
		// Layout of the bottom panel
		GridBagConstraints gcB = new GridBagConstraints();
		
		gcB.fill = GridBagConstraints.HORIZONTAL;
		gcB.weightx = 0.5;
		gcB.gridx = 0;
		gcB.gridy = 0;
		panelBottom.add(showExerciseLabel, gcB);
		
		gcB.fill = GridBagConstraints.HORIZONTAL;
		gcB.weightx = 0.5;
		gcB.gridx = 1;
		gcB.gridy = 0;
		panelBottom.add(categoriesComboBox, gcB);
		
		gcB.fill = GridBagConstraints.HORIZONTAL;
		gcB.weightx = 0.5;
		gcB.gridx = 3;
		gcB.gridy = 0;
		panelBottom.add(addedExerciseLabel, gcB);
		
		gcB.fill = GridBagConstraints.HORIZONTAL;
		gcB.weightx = 0.5;
		gcB.gridx = 4;
		gcB.gridy = 0;
		panelBottom.add(amountExercisesLabel, gcB);
		
		gcB.fill = GridBagConstraints.HORIZONTAL;
		gcB.weightx = 0.5;
		gcB.gridx = 0;
		gcB.gridy = 1;
		panelBottom.add(sortExerciseLabel, gcB);
		
		gcB.fill = GridBagConstraints.HORIZONTAL;
		gcB.weightx = 0.5;
		gcB.gridx = 1;
		gcB.gridy = 1;
		panelBottom.add(sortExercisesComboBox, gcB);
		
		gcB.fill = GridBagConstraints.HORIZONTAL;
		gcB.weightx = 0.5;
		gcB.gridwidth = 2;
		gcB.gridx = 3;
		gcB.gridy = 1;
		panelBottom.add(moveUpButton, gcB);//
		
		
		gcB.fill = GridBagConstraints.HORIZONTAL;
		gcB.weightx = 0.5;

		gcB.gridwidth = 1;
		gcB.gridx = 2;
		gcB.gridy = 2;
		panelBottom.add(addToQuizButton, gcB);
		
		gcB.fill = GridBagConstraints.HORIZONTAL;
		gcB.weightx = 0.5;
		gcB.gridwidth = 1;
		gcB.gridx = 2;
		gcB.gridy = 3;
		panelBottom.add(removeFromQuizButton, gcB);
		
		gcB.fill = GridBagConstraints.BOTH;
		gcB.weightx = 0.0;
		gcB.weighty = 1;
		gcB.gridwidth = 2;
		gcB.gridheight = 4;
		gcB.gridx = 0;
		gcB.gridy = 2;
		panelBottom.add(exercisesList, gcB);
		
		gcB.fill = GridBagConstraints.BOTH;
		gcB.weightx = 0.0;
		gcB.weighty = 1;
		gcB.gridwidth = 2;
		gcB.gridheight = 4;
		gcB.gridx = 3;
		gcB.gridy = 2;
		panelBottom.add(paneTable, gcB);/////////////////////////////
		
		// Layout of the top panel
		GridBagConstraints gcT = new GridBagConstraints();
		
		gcT.anchor = GridBagConstraints.LINE_END;
		gcT.weightx = 0.5;
		gcT.gridx = 0;
		gcT.gridy = 0;
		panelTop.add(subjectLabel, gcT);
		
		gcT.anchor = GridBagConstraints.CENTER;
		gcT.weightx = 0.5;
		gcT.gridx = 1;
		gcT.gridy = 0;
		panelTop.add(subjectTextField, gcT);
		
		gcT.anchor = GridBagConstraints.CENTER;
		gcT.weightx = 0.5;
		gcT.gridx = 2;
		gcT.gridy = 0;
		panelTop.add(gradeLabel, gcT);
		
		gcT.anchor = GridBagConstraints.LINE_START;
		gcT.weightx = 0.5;
		gcT.gridx = 3;
		gcT.gridy = 0;
		panelTop.add(gradesComboBox, gcT);
		
		gcT.anchor = GridBagConstraints.CENTER;
		gcT.weightx = 0.5;
		gcT.gridx = 4;
		gcT.gridy = 0;
		panelTop.add(authorLabel, gcT);
		
		gcT.anchor = GridBagConstraints.LINE_START;
		gcT.weightx = 0.5;
		gcT.gridx = 5;
		gcT.gridy = 0;
		panelTop.add(authorsComboBox, gcT);
		
		gcT.anchor = GridBagConstraints.CENTER;
		gcT.weightx = 0.5;
		gcT.gridx = 6;
		gcT.gridy = 0;
		panelTop.add(stateLabel, gcT);
		
		gcT.anchor = GridBagConstraints.LINE_START;
		gcT.weightx = 0.5;
		gcT.gridx = 7;
		gcT.gridy = 0;
		panelTop.add(stateComboBox, gcT);
		
		gcT.fill = GridBagConstraints.HORIZONTAL;
		gcT.anchor = GridBagConstraints.CENTER;
		gcT.weightx = 0.0;
		gcT.gridwidth = 8;
		gcT.gridx = 0;
		gcT.gridy = 1;
		panelTop.add(addQuizButton, gcT);
		
		
		setTitle("Aanmaken nieuwe quiz");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);      
	}
	
	// Selectors
	
	/**
	 * @return
	 */
	public String getSubject(){
		return subjectTextField.getText();
	}
	
	/**
	 * @return
	 */
	public String getGrade(){
		return String.valueOf(gradesComboBox.getSelectedItem());
	}
	
	/**
	 * @return
	 */
	public String getAuthor(){
		return String.valueOf(authorsComboBox.getSelectedItem());
	}
	
	/**
	 * @return
	 */
	public String getStatus(){
		return String.valueOf(stateComboBox.getSelectedItem());
	}
	
	/**
	 * @return
	 */
	public String getSelectedValueToList(){
		return String.valueOf(exercisesList.getSelectedValue());
	}
	
	/**
	 * @return
	 */
	public String getSelectedExerciseValueFromTable(){
		return String.valueOf(dataModel.getValueAt(addedExercisesTable.getSelectedRow(), 0));
	}
	
	/**
	 * @return
	 */
	public int getSelectedIndexFromList(){
		return addedExercisesList.getSelectedIndex();
	}
	
	/**
	 * @return
	 */
	public DefaultListModel getListModel2(){
		return listModel2;
	}

	/**
	 * @return
	 */
	public DefaultTableModel getDataModel(){
		return dataModel;
	}
	
	/**
	 * @return
	 */
	public int getSelectedRow(){
		return addedExercisesTable.getSelectedRow();
	}
	
	/**
	 * @return
	 */
	public String getSelectedCategory(){
		return String.valueOf(categoriesComboBox.getSelectedItem());
	}
	
	/**
	 * @return
	 */
	public String getSelectedSortByValue(){
		return String.valueOf(sortExercisesComboBox.getSelectedItem());
	}
	
	// Modifiers
	
	/**
	 * Set exercisesList
	 * 
	 * @param exerciseList
	 */
	public void setExercisesList(List<Exercise> exerciseList){
		DefaultListModel listModel = new DefaultListModel();
		
		for (Exercise ex : exerciseList){
			//String tempCat = ex.getQuestion().s
			listModel.addElement("("+ String.valueOf(ex.getCategory()).toUpperCase().substring(0, 3) + ") " 
					+ ex.getQuestion());
		}
		
		this.exercisesList.setModel(listModel);
	}
	
	/**
	 * Set amount of added exercises
	 */
	public void setAmountAddedExercises(String text){ 
		amountExercisesLabel.setText(text);;
	}
	
	/**
	 * Add listener to addQuizButton
	 * 
	 * @param listenForAddQuizButton
	 */
	public void addAddQuizButtonListener(ActionListener listenForAddQuizButton){
		addQuizButton.addActionListener(listenForAddQuizButton);
	}
	
	/**
	 * Add listener to addToQuizButton
	 * 
	 * @param listenForAddToQuizButton
	 */
	public void addAddToQuizButtonListener(ActionListener listenForAddToQuizButton){
		addToQuizButton.addActionListener(listenForAddToQuizButton);
	}
	
	/**
	 * Add listener to removeFromQuizButton
	 * 
	 * @param listenForRemoveFromQuizButton
	 */
	public void addRemoveFromQuizButtonListener(ActionListener listenForRemoveFromQuizButton){
		removeFromQuizButton.addActionListener(listenForRemoveFromQuizButton);
	}
	
	/**
	 * Add listener to moveUpButton
	 * 
	 * @param listenForMoveUpButton
	 */
	public void addMoveUpButtonListener(ActionListener listenForMoveUpButton){
		moveUpButton.addActionListener(listenForMoveUpButton);
	}
	
	/**
	 * Add listener to categoriesComboBox
	 * 
	 * @param listenForCategoriesComboBox
	 */
	public void addCategoriesComboBoxListener(ActionListener listenForCategoriesComboBox){
		categoriesComboBox.addActionListener(listenForCategoriesComboBox);
	}
	
	/**
	 * Add listener to sortExercisesComboBox
	 * 
	 * @param listenForSortExercisesComboBox
	 */
	public void addSortExercisesComboBoxListener(ActionListener listenForSortExercisesComboBox){
		sortExercisesComboBox.addActionListener(listenForSortExercisesComboBox);
	}
	
    /**
     * Pop up for errors
     * 
     * @param errorMessage
     */
	public void displayErrorMessage(String errorMessage){
       JOptionPane.showMessageDialog(this, errorMessage);
    }
}

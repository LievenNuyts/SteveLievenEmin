package view;

/**
 *  
 * @author Lieven
 * @version 17/11/2013
 *
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.StartAppController;
import model.Exercise;
import model.ExerciseCatalog;
import model.Quiz;
import model.QuizCatalog;
import model.QuizExercise;


public class DeleteQuizView extends JFrame{
	
	private QuizCatalog catalog;
	
	private JButton btn_up, btn_down, btn_delete, btn_exit, btn_save;
	
	private JTable table, exerciseTable;
	private DefaultTableModel model, model2;
	
	private JScrollPane pane, pane2;
	private JPanel pnl_one, pnl_two;
	
	private String[] columnNames = {"QuizID","Author","Subject","Grade","Status"};
	private String[] columnNames2 = {"Exercises from selected quiz:"};
	
	public DeleteQuizView(){	
		super("Delete quiz");
		this.defineLayout();
	}
	
	public DeleteQuizView(QuizCatalog catalog){
		
		super("Delete quiz");
		this.catalog = catalog;
		
		this.defineLayout();
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent evt)
		    {	
		    	new StartAppController().startApp();
		    }
		});
	}
	
	//method to reset the DefaultTableModel
	public void resetTable(){
		model.setRowCount(0);
		loadJTable(); //adj
	}
	
	public void resetExTable(){
		model2.setRowCount(0);
		loadExTable(); //adj
	}
	
	
	public void loadJTable(){
		
		if(table == null){
			table = new JTable();
			
			//Remove mouse click possibility on JTABLE
			MouseListener[] listeners = table.getMouseListeners();
			for (MouseListener l : listeners)
			{
			    table.removeMouseListener(l);
			}		
		}
	
		if(model == null){
			model = new DefaultTableModel();
			model.setColumnIdentifiers(columnNames);	
		}
		
		for(Quiz quiz : catalog.getQuizCatalogs()){
	
			String[] dataBuilder = new String[5];
			
			dataBuilder[0] = Integer.toString(quiz.getQuizId());
			dataBuilder[1] = quiz.getTeacher().toString();
			dataBuilder[2] = quiz.getSubject();
			dataBuilder[3] = Integer.toString(quiz.getLeerJaren());
			dataBuilder[4] = quiz.getStatus().toString();
			
			model.addRow(dataBuilder);
		}
		
		table.setModel(model);
		table.setAutoCreateRowSorter(true);
		
		if(table.getRowCount() != 0){
			table.setRowSelectionInterval(0, 0);
		}
	}
	
	public void loadExTable(){
		
		if(exerciseTable == null){
			exerciseTable = new JTable();
		}
	
		if(model2 == null){
			model2 = new DefaultTableModel();
			model2.setColumnIdentifiers(columnNames2);
		}
		
		if(this.table.getRowCount() != 0){
		
			//Select QuizID from the JTABLE
			String quizIDtoLookup = (String) this.getJTable().getValueAt(this.getJTable().getSelectedRow(), 0);	
			//loop through the quizzes
			for(Quiz quiz : getQuizCatalog().getQuizCatalogs()){
				//Find the quiz object via the ID of the selected JTable row
				if(quiz.getQuizId() == Integer.parseInt(quizIDtoLookup)){
							
					for(QuizExercise qe : quiz.getQuizExercises()){
						
						String[] dataBuilder = new String[1];
						
						dataBuilder[0] = qe.getExercise().getQuestion();
						
						model2.addRow(dataBuilder);
					}	
				}
			}		
		}
		exerciseTable.setModel(model2);
	}
	
	private void defineLayout(){
		
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//PANEL ONE
    	pnl_one = new JPanel();
		Dimension d_one = getPreferredSize();
		d_one.height = 500;
		d_one.width = 150;
		pnl_one.setPreferredSize(d_one);
		pnl_one.setBorder(BorderFactory.createTitledBorder("Controls"));
				
		//PANEL TWO
		pnl_two = new JPanel();
		Dimension d_two = getPreferredSize();
		d_two.height = 500;
		d_two.width = 550;
		pnl_two.setPreferredSize(d_two);
		pnl_two.setBorder(BorderFactory.createTitledBorder("Quiz List"));
				
		//TABLE
		loadJTable();
			
		table.setPreferredScrollableViewportSize(new Dimension(500,200));
		table.setFillsViewportHeight(true);
		table.setFocusable(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(160);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(160);
		
		pane = new JScrollPane(table);
			
		//EXERCISETABLE
		loadExTable();
		
		exerciseTable.setPreferredScrollableViewportSize(new Dimension(500,200));
		exerciseTable.setFillsViewportHeight(true);	
		exerciseTable.setFocusable(false);
		exerciseTable.setRowSelectionAllowed(false);
		pane2 = new JScrollPane(exerciseTable);
		
		//BUTTONS
	    btn_up = new JButton("↑");
	    btn_down = new JButton("↓");	    
	    btn_delete = new JButton("Delete quiz");    
	    btn_exit = new JButton("Cancel");
	    btn_save = new JButton("Save & close");
			            
			    
	    //ADD BUTTONS TO PANEL ONE
	    pnl_one.setLayout(new GridBagLayout());      
		GridBagConstraints panelConstraints = new GridBagConstraints();
					
		panelConstraints.weightx = 0.5;
		panelConstraints.weighty = 0.5;
					
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 0;
		pnl_one.add(btn_up, panelConstraints);
				
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 1;
		pnl_one.add(btn_down, panelConstraints);
					
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 2;
		pnl_one.add(btn_delete, panelConstraints);
				
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 3;
		pnl_one.add(btn_save, panelConstraints);
		
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 4;
		pnl_one.add(btn_exit, panelConstraints);
		
		//ADD PANE(WITH TABLE) AND EXERCISE JLIST TO PANEL TWO	
		
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 1;
		pnl_two.add(pane, panelConstraints);
		
		
		panelConstraints.gridx = 0;
		panelConstraints.gridy = 2;
		pnl_two.add(pane2, panelConstraints);
			
		
		//ADD PANELS TO JFRAME
		setLayout(new GridBagLayout());
		GridBagConstraints frameConstraints = new GridBagConstraints();
		frameConstraints.weightx = 0.5;
		frameConstraints.weighty = 0.5;	
				
		add(pnl_one,frameConstraints);
		add(pnl_two,frameConstraints);
	}

	
	// GETTERS & SETTERS
	
	public void setQuizCatalog(QuizCatalog catalog){	
		this.catalog = catalog;
	}
	
	public QuizCatalog getQuizCatalog(){	
		return this.catalog;
	}
	
	public JTable getJTable(){
		return this.table;
	}
	
	public JTable getJTableExercises(){
		return this.exerciseTable;
	}
	
	//ADD LISTENERES TO BUTTONS
	public void addDeleteQuizListener(ActionListener listener){

		btn_delete.addActionListener(listener);
	}
	
	public void addCloseWindowListener(ActionListener listener){

		btn_exit.addActionListener(listener);
	}
	
	public void addButtonUpListener(ActionListener listener){

		btn_up.addActionListener(listener);
	}
	
	public void addButtonDownListener(ActionListener listener){

		btn_down.addActionListener(listener);
	}	
	
	public void addSaveAndCloseListener(ActionListener listener){

		btn_save.addActionListener(listener);
	}
	
	public void showPopup(String text){
	       JOptionPane.showMessageDialog(this, text);
	}
}
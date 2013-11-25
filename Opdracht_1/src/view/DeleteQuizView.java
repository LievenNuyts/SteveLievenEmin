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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Quiz;
import model.QuizCatalog;


public class DeleteQuizView extends JFrame{
	
	private QuizCatalog catalog;
	
	private JButton btn_up, btn_down, btn_delete, btn_exit;
	
	private JTable table;
	private DefaultTableModel model;
	
	private JScrollPane pane;
	private JPanel pnl_one, pnl_two;
	
	private String[] columnNames = {"QuizID","Author","Subject","Grade","Status"};
	
	public DeleteQuizView(){	
		super("Delete quiz");
		this.defineLayout();
	}
	
	public DeleteQuizView(QuizCatalog catalog){
		
		super("Delete quiz");
		this.catalog = catalog;
		this.defineLayout();
	}
	
	//method to reset the DefaultTableModel
	public void resetTable(){
		model.setRowCount(0);
	}
	
	
	public void loadJTable(){
		
		if(table == null){
			table = new JTable();
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
			
		table.setPreferredScrollableViewportSize(new Dimension(400,450));
		table.setFillsViewportHeight(true);	
		pane = new JScrollPane(table);
				     
		//BUTTONS
	    btn_up = new JButton("↑");
	    btn_down = new JButton("↓");	    
	    btn_delete = new JButton("Delete");    
	    btn_exit = new JButton("Cancel");
			            
			    
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
		pnl_one.add(btn_exit, panelConstraints);
	
		//ADD PANE(WITH TABLE) TO PANEL TWO	
		pnl_two.add(pane);	
				
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
}
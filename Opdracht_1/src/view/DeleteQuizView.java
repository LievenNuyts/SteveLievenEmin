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
	public JTable table;
	private JScrollPane pane;
	private JPanel pnl_one, pnl_two;
	
	private DefaultTableModel model;
	private String[] columnNames = {"QuizID","Author","Subject","Grade","Status"};
	private Object [][] data = {//example is maar om wa mee te foefelen
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"}, 
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"}, 	
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"}, 	
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"},
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"},
    		{"3","Paul","Taal","2","Active"}, 	
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"},
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"},
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"}, 	
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"},
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"},
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"}, 
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"3","Paul","Taal","2","Active"}, 
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"}, 
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"}, 	
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"1","Jan","Aardrijkskunde","1","New"},
    		{"2","Karel","Wiskunde","1","New"},
    		{"3","Paul","Taal","2","Active"} 	
    }; //example is maar om wa mee te foefelen
	
	
	public DeleteQuizView(){	
		super("Delete quiz");
		this.setUp();
	}
	
	public DeleteQuizView(QuizCatalog catalog){
		
		super("Delete quiz");
		this.catalog = catalog;
		this.setUp();
	}
	
	private void setUp(){	
		this.defineLayout();
		this.table.setRowSelectionInterval(0, 0);
	}
	
	//method to reset the DefaultTableModel
	public void resetTable(){
		//model.fireTableDataChanged();
		//model = null;
	}
	
	
	public void loadJTable(){
			
		int numberOfQuizzesInCatalog = catalog.getQuizCatalogs().size();
			
		//creates array with 5 columns and a row per quiz in the catalog
		String[][] dataBuilder = new String[numberOfQuizzesInCatalog][5]; 
			
		int rowCounter = 0;	
			
		for(Quiz quiz : catalog.getQuizCatalogs()){
		    					
			dataBuilder[rowCounter][0] = Integer.toString(quiz.getQuizId());
			dataBuilder[rowCounter][1] = quiz.getTeacher().toString();
			dataBuilder[rowCounter][2] = quiz.getSubject();
			dataBuilder[rowCounter][3] = Integer.toString(quiz.getLeerJaren());
			dataBuilder[rowCounter][4] = quiz.getStatus().toString();
			   		
		   	rowCounter++;	
	    }
			
		model = new DefaultTableModel(dataBuilder, columnNames);
			
		table = new JTable(model);	

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
		if(catalog == null){		
			table = new JTable(data, columnNames);
		}
		else{
			loadJTable();
		}
		
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
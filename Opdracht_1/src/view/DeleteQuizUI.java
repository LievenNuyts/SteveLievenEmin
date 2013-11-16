package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.Quiz;
import model.QuizCatalog;


public class DeleteQuizUI extends JFrame{
	
	private QuizCatalog catalog;
	
	private JButton btn_up, btn_down, btn_delete, btn_exit;
	private JTable table;
	private JScrollPane pane;
	private JPanel pnl_one, pnl_two;
	
	private String[] columnNames = {"QuizID","Author","Subject","Grade","Status"};
	Object [][] data = {
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
    }; //example
	
	
	public DeleteQuizUI(){
		
		super("Delete quiz");
		defineLayout();
		eventsToButtons();
		table.setRowSelectionInterval(0, 0);
	}
	
	public DeleteQuizUI(QuizCatalog catalog){
		
		super("Delete quiz");
		this.catalog = catalog;	
		defineLayout();
		eventsToButtons();
		table.setRowSelectionInterval(0, 0);	
	}
	
	public void loadJTable(){
	
		String[][] dataBuilder = null; 
		DefaultTableModel model = new DefaultTableModel(dataBuilder, columnNames);
		int counter = 0;	
		
		for(Quiz quiz : catalog.getQuizCatalogs()){
	    	
			String quizID = Integer.toString(quiz.getQuizId());
			String author = quiz.getTeacher().toString();
			String subject = quiz.getSubject();
			String grade = Integer.toString(quiz.getLeerJaren());
			String status = quiz.getStatus().toString();		
			
	    	model.insertRow(counter, new Object[] {quizID, author, subject, grade, status});   	
	    	counter++;
	    }
		
		table = new JTable(model);	
	}
	
	public void defineLayout(){
		
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
	
	
	public void eventsToButtons(){
		
		//btn_up
		
		btn_up.addActionListener(new ActionListener(){
		
			public void actionPerformed(ActionEvent e){
				
				int rowIndex = table.getSelectedRow();
				rowIndex--;
				table.setRowSelectionInterval(rowIndex, rowIndex);		
			}		
		});
				
		
		//btn_down 
		
		btn_down.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				int rowIndex = table.getSelectedRow();
				rowIndex++;
				table.setRowSelectionInterval(rowIndex, rowIndex);			
			}		
		});
		
		
		//btn_delete
		
		btn_delete.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				int quizIDtoDelete = (int) table.getValueAt(table.getSelectedRow(), 0);
				
				for(Quiz quiz : catalog.getQuizCatalogs()){
					
					if(quiz.getQuizId() == quizIDtoDelete){
						
						catalog.deleteQuiz(quiz);
					}
				}
			}		
		});
		
		
		//btn_exit
		
		btn_exit.addActionListener(new ActionListener(){		
			public void actionPerformed(ActionEvent e){
				dispose();		
			}		
		});	
	}
	
	
	
	
	public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	DeleteQuizUI ex = new DeleteQuizUI();
                ex.setVisible(true);
            }
        });
    }	
}
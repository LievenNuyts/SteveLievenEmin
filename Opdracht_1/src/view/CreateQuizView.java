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
import java.util.Locale.Category;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import model.Exercise.ExerciseCategory;
import model.Teacher;

/**
 * @author java
 *
 */
public class CreateQuizView extends JFrame {
	
	public CreateQuizView() {  
		initUI();
    }
	
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
		
		// Elements of top panel
		String[] grades = { "1", "2", "3", "4"};
		JLabel subjectLabel = new JLabel("Onderwerp: ");
		JLabel authorLabel = new JLabel("Auteur: ");
		JLabel gradeLabel = new JLabel("Klas: ");
		JLabel stateLabel = new JLabel("Status: ");
		JTextField subjectTextField = new JTextField(10);
		JComboBox gradesComboBox = new JComboBox(grades);
		JComboBox authorsComboBox = new JComboBox(Teacher.values());
		JComboBox stateComboBox = new JComboBox();
		JButton addQuizButton = new JButton("Registreer nieuwe quiz");
		
		// Elements of bottom panel
		String[] sort = { "geen", "categorie", "vraag"};
		JLabel showExerciseLabel = new JLabel("Toon opdrachten van categorie: ");
		JLabel sortExerciseLabel = new JLabel("Sorteer opdrachten op: ");
		JLabel addedExerciseLabel = new JLabel("Aantal toegevoegde opdrachten: ");
		JLabel amountExercisesLabel = new JLabel("0");
		JComboBox categoriesComboBox = new JComboBox(ExerciseCategory.values());
		JComboBox sortExercisesComboBox = new JComboBox(sort);
		JButton moveUpButton = new JButton("^^^^");
		JButton addToQuizButton = new JButton("--->");
		JButton removeFromQuizButton = new JButton("<---");
		JList exercisesList = new JList();
		JList addedExercisesList = new JList();
		
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
		panelBottom.add(addedExercisesList, gcB);
		
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
		
		
		
		
		addQuizButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		setTitle("Aanmaken nieuwe quiz");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);      
	}

	public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	CreateQuizView ex = new CreateQuizView();
                ex.setVisible(true);
            }
        });
    }
}

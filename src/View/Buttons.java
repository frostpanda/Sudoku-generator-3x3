package View;

import Controller.ControllingButtons;
import Model.Actions;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Buttons extends JPanel implements Observer {
    JButton buttonNewSudokuEasy, buttonNewSudokuIntermediate, buttonNewSudokuHard, buttonShowSolution, buttonHideSolution; 

    /**
     * Constructor, creating panels, buttons and positions these elements 
     */
    public Buttons() {
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.PAGE_AXIS));
        add(mainContainer, BorderLayout.NORTH);

        JPanel sudokuLevelsPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        sudokuLevelsPanel.setBorder(BorderFactory.createTitledBorder("Dostępne poziomy trudności: "));
        mainContainer.add(sudokuLevelsPanel);
        
        buttonNewSudokuEasy = new JButton("Łatwy");
        buttonNewSudokuEasy.setFocusable(false);
        sudokuLevelsPanel.add(buttonNewSudokuEasy);
        
        buttonNewSudokuIntermediate = new JButton("Średnio-zaawansowany");
        buttonNewSudokuIntermediate.setFocusable(false);
        sudokuLevelsPanel.add(buttonNewSudokuIntermediate);
        
        buttonNewSudokuHard = new JButton("Trudny");
        buttonNewSudokuHard.setFocusable(false);
        sudokuLevelsPanel.add(buttonNewSudokuHard);        

        JPanel solutionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        solutionPanel.setBorder(BorderFactory.createTitledBorder("Rozwiązanie: "));
        mainContainer.add(solutionPanel);
        
        buttonShowSolution = new JButton("Pokaż");
        buttonShowSolution.setFocusable(false);
        solutionPanel.add(buttonShowSolution);
        
        buttonHideSolution = new JButton("Ukryj");
        buttonHideSolution.setFocusable(false);
        solutionPanel.add(buttonHideSolution);  
    }

    /**
     * Method called when model sends update notification.
     */
    @Override
    public void update(Observable obs, Object arg) {
        switch ((Actions)arg) {
            case NEW_SUDOKU:
            case SHOW_SOLUTION:
        }
    }

    /**
     * Adds controller to all components.
     */
    public void setController(ControllingButtons buttonController) {
        buttonNewSudokuEasy.addActionListener(buttonController);
        buttonNewSudokuIntermediate.addActionListener(buttonController);
        buttonNewSudokuHard.addActionListener(buttonController);
        buttonShowSolution.addActionListener(buttonController);
        buttonHideSolution.addActionListener(buttonController);
    }
}
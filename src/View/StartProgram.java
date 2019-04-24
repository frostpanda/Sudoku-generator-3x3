package View;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import Controller.ControllingButtons;
import Model.GenerateSudoku;

public class StartProgram extends JFrame {
    public StartProgram() {
        super("Generator Sudoku 3x3");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        GenerateSudoku newSudoku = new GenerateSudoku();

        ControllingButtons newController = new ControllingButtons(newSudoku);
        Buttons buttonPanel = new Buttons();
        buttonPanel.setController(newController);
        add(buttonPanel, BorderLayout.BEFORE_FIRST_LINE);

        SudokuBoard sudoku = new SudokuBoard();
        sudoku.showSudoku(newSudoku);
        add(sudoku, BorderLayout.CENTER);

        newSudoku.addObserver(buttonPanel);
        newSudoku.addObserver(sudoku);
        
        pack();  
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);        
    }

    public static void main(String[] args) {
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        }
        catch (Exception ex) { 
            ex.printStackTrace(); 
        }
        new StartProgram();      
    }
}
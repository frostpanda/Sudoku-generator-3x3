package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import Model.GenerateSudoku;
import Model.Actions;

public class SudokuBoard extends JPanel implements Observer {
    private final SudokuFields[][] fields;       
    private final JPanel[][] containers;      

    /**
     * Constructor, making panels, adds sub panels and adds fields to these sub panels.
     */
    public SudokuBoard() {
        super(new GridLayout(3, 3));

        containers = new JPanel[3][3];
        for (int x1 = 0; x1 < 3; x1++) {
            for (int x2 = 0; x2 < 3; x2++) {
                containers[x1][x2] = new JPanel(new GridLayout(3, 3));
                containers[x1][x2].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                add(containers[x1][x2]);
            }
        }

        fields = new SudokuFields[9][9];
        for (int x1 = 0; x1 < 9; x1++) {
            for (int x2 = 0; x2 < 9; x2++) {
                fields[x1][x2] = new SudokuFields(x1, x2);
                containers[x1/3][x2/3].add(fields[x1][x2]);
            }
        }
    }

     /**
     * This method is invoke, when model is sending update information
     */
    public void update(Observable o, Object arg) {
        switch ((Actions)arg) {
            case NEW_SUDOKU:
                showSudoku((GenerateSudoku)o);
                break;
            case SHOW_SOLUTION:
                showSudokuSolution((GenerateSudoku)o);
                break;
            case HIDE_SOLUTION:
                showSudoku((GenerateSudoku)o);
                break;
        }
    }

      /**
     * This two methods will fill fields on the sudoku board with numbers,
     * number is taken from board array and solution array, using to this 
     * purpose following methods: sudokuPosition and solutionPosition
     */

    public void showSudoku(GenerateSudoku sudoku) {
        for (int x1 = 0; x1 < 9; x1++) {
            for (int x2 = 0; x2 < 9; x2++) {
                fields[x1][x2].setBackground(Color.LIGHT_GRAY);
                fields[x1][x2].settingNumberForSpecificField(sudoku.sudokuPosition(x1, x2));
            }
        }
    }
    
   public void showSudokuSolution(GenerateSudoku sudoku) {
        for (int x1 = 0; x1 < 9; x1++) {
            for (int x2 = 0; x2 < 9; x2++) {
                if (sudoku.board[x1][x2] == 0){
                    fields[x1][x2].settingNumberForSpecificField(sudoku.solutionPosition(x1, x2));
                    fields[x1][x2].setBackground(Color.orange); 
                }
            }
        }
   }
}
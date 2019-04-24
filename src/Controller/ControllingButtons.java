package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.GenerateSudoku;
import View.Buttons;
        
public class ControllingButtons implements ActionListener {
    private final GenerateSudoku sudoku;
Buttons buttons;
    /**
     * Constructor, sets sudoku
     *
     * @param sudoku
     */
    public ControllingButtons(GenerateSudoku sudoku) {
        this.sudoku = sudoku;
    }

    /**
     * Actions, which are taken after pressing specific button
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Łatwy":
                sudoku.newBoard(1);
                break;
            case "Średnio-zaawansowany":
                sudoku.newBoard(2);
                break;
            case "Trudny":  
                sudoku.newBoard(3);
                break;
            case "Pokaż":
                sudoku.solutionForSudoku();
                break;
            case "Ukryj":
                sudoku.hideSudokuSolution();
                break;
        }
    }
}
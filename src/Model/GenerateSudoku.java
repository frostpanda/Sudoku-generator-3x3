package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class GenerateSudoku extends Observable {
    public int[][] solution = new int[9][9];     
    public int[][] board = new int[9][9];         
    private int[][] solutionHolder = new int[9][9];    
    private int[][] boardHolder = new int[9][9];
    private int difficult;
    
    /**
    * 2 Constructors, one without parameters, another with parameter,
    * which set difficult for specific sudoku
    * @param difficult
    */
    public GenerateSudoku() {
        newBoardDefault();
    }
    
    public GenerateSudoku(int difficult) {
        newBoard(difficult);
    }

    /**
    * Generates default Sudoku, for maximum blank fields in sudoku
    * Informating obserwers, NEW_SUDOKU action
    */
    public void newBoardDefault() {
        difficult = 1;
        solution = generateBoardSolution(new int[9][9], 0);
        board = generateBoard(copy(solution));
        setChanged();
        notifyObservers(Actions.NEW_SUDOKU);
    }
    
    /**
    * Creating new Sudoku, with specific difficult.
    * Informating obserwers, NEW_SUDOKU action
    * @param sudokuDifficult - parameter, which setting difficult 
    */
    public void newBoard(int sudokuDifficult) {
        difficult = sudokuDifficult;
        solution = generateBoardSolution(new int[9][9], 0);
        board = generateBoard(copy(solution));
        setChanged();
        notifyObservers(Actions.NEW_SUDOKU);
    }
    
    /**
    * Solution holder for specific board.
    */
    public void solutionForSudoku(){
        solutionHolder = solution;
        setChanged();
        notifyObservers(Actions.SHOW_SOLUTION);
    }
    
    /**
    * Board holder current board.
    */
    public void hideSudokuSolution(){
        boardHolder = board;
        setChanged();
        notifyObservers(Actions.HIDE_SOLUTION);
    }
    
    /**
    * Checking, if specific number can be set on the axis x, for current board
    */
    private boolean checkIfXPossible(int[][] board, int x2, int number) {
        for (int x1 = 0; x1 < 9; x1++) {
            if (board[x2][x1] == number)
                return false;
        }
        return true;
    }

    /**
    * Checking, if specific number can be set on the axis y, for current board
    */
    private boolean checkIfYPossible(int[][] board, int x1, int number) {
        for (int x2 = 0; x2 < 9; x2++) {
            if (board[x2][x1] == number)
                return false;
        }
        return true;
    }

    /**
    * Checking, if specific number can be set in the block, for current board
    */
    private boolean checkIfBlockPossible(int[][] board, int x, int y, int number) {
        int x1 = x < 3 ? 0 : x < 6 ? 3 : 6;
        int y1 = y < 3 ? 0 : y < 6 ? 3 : 6;
        for (int yy = y1; yy < y1 + 3; yy++) {
            for (int xx = x1; xx < x1 + 3; xx++) {
                if (board[yy][xx] == number)
                    return false;
            }
        }
        return true;
    }

    /**
    * is empty.
    * This method takes matheds checkIfXPossible, checkIfYPossible,
    * checkIfBLockPossible and when these 3 methods will return 3 times true, 
    * method will return number from list, for specific position
    */
    private int returnPossibleNumber(int[][] board, int x1, int x2, List<Integer> numbers) {
        while (numbers.size() > 0) {
            int number = numbers.remove(0);
            if (checkIfXPossible(board, x2, number) && checkIfYPossible(board, x1, number) && checkIfBlockPossible(board, x1, x2, number))
                return number;
        }
        return -1;
    }

    /**
    * is empty.
    * This method set difficult of the board
    */
    private int sudokuLevel(int difficult) {
        switch (difficult) {
            case 1:
                return 32;
            case 2:
                return 43;
            case 3:
                return 55;
            default:
                return 81;
       }
    }   
    
    /**
    * This method Sudoku board solution.
    */
    private int[][] generateBoardSolution(int[][] board, int index) {
        if (index > 80)
            return board;
        
        int x = index % 9;
        int y = index / 9;

        List<Integer> listOfNumbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) listOfNumbers.add(i);
        Collections.shuffle(listOfNumbers);

        while (listOfNumbers.size() > 0) {
            int number = returnPossibleNumber(board, x, y, listOfNumbers);
            if (number == -1)
                return null;

            board[y][x] = number;
            int[][] holder = generateBoardSolution(board, index + 1);
            if (holder != null)
                return holder;
            board[y][x] = 0;
        
        }
        return null;
    }

    /**
    * This method is generating Sudoku board from solution, to be more specific 
    * method is removing numbers from specific position. Method will stop 
    * execution, when 
    */
    private int[][] generateBoard(int[][] board) {
        List<Integer> numberOfPosition = new ArrayList<>();
        for (int i = 0; i < 81; i++)
            numberOfPosition.add(i);
        Collections.shuffle(numberOfPosition);
        return generateBoard(board,numberOfPosition);
    }

    /**
    * This method is generating Sudoku board from solution, to be more specific 
    * method is removing numbers from specific position. Method will stop 
    * execution, when board will be not valid anymore and will be brought back to
    * the previous state
   */
    private int[][] generateBoard(int[][] board, List<Integer> numberOfPosition) {
        int position, x, y,holder;
        int blanks = 0;
        
        while (numberOfPosition.size() > 0) {
            position = numberOfPosition.remove(0);
            
            x = position % 9;
            y = position / 9;
            
            holder = board[y][x];
            board[y][x] = 0;
            blanks++;
            
            if (!checkIfSudokuIsValid(board)){
                board[y][x] = holder;
                blanks--;
            } else if (blanks == sudokuLevel(difficult))
                break;
        }

        return board;
    }

    /**
    * Checks, if board is valid
    *
    */
    private boolean checkIfSudokuIsValid(int[][] board) {
        return checkIfSudokuIsValid(board, 0, new int[] { 0 });
    }

    /**
     * Checks, if board is valid and have one solution.
     */
    private boolean checkIfSudokuIsValid(int[][] board, int index, int[] numberOfSolutions) {
        if (index > 80)
            return ++numberOfSolutions[0] == 1;

        int x = index % 9;
        int y = index / 9;

        if (board[y][x] == 0) {
            List<Integer> numbers = new ArrayList<>();
            for (int i = 1; i <= 9; i++)
                numbers.add(i);

            while (numbers.size() > 0) {
                int number = returnPossibleNumber(board, x, y, numbers);
                if (number == -1)
                    break;
                board[y][x] = number;

                if (!checkIfSudokuIsValid(board, index + 1, numberOfSolutions)) {
                    board[y][x] = 0;
                    return false;
                }
                board[y][x] = 0;
            }
        } else if (!checkIfSudokuIsValid(board, index + 1, numberOfSolutions))
            return false;

        return true;
    }

    /**
     * This method is making copy of the board, in purpose of holding solution
     * for specific sudoku board with blank space
     */
    private int[][] copy(int[][] board) {
        int[][] copy = new int[9][9];
        for (int y = 0; y < 9; y++) {
            System.arraycopy(board[y], 0, copy[y], 0, 9);
        }
        return copy;
    }

     /**
     * This and next method returtn position from board array
     */
    public int sudokuPosition(int x, int y) {
        return board[x][y];
    }
    
    public int solutionPosition(int x, int y){
        return solution[x][y];
    }  
}
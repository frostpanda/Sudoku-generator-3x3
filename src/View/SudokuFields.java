package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class SudokuFields extends JLabel {
    private int x1;      
    private int x2;      

    /**
     * Constructs the label and sets x and y positions in game.
     */
    public SudokuFields(int x, int y) {
        super("", CENTER);
        this.x1 = x1;
        this.x2 = x2;
        
        setPreferredSize(new Dimension(45, 45));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        setOpaque(true);
    }

    /**
     * THis method will set number for specific field
     */
    public void settingNumberForSpecificField(int number) {
        setText(number > 0 ? number + "" : ""); 
    }
}
import javax.swing.*;
import java.awt.*;

public class TicTacToeTile extends JButton
{
    private int row;
    private int col;

    public TicTacToeTile(int row, int col)
    {
        super();
        //System.out.println(row + " " + col);
        this.row = row;
        this.col = col;
    }

    public int getRow() {return row;}

    public int getCol() {return col;}
}

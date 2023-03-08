import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeFrame extends JFrame
{
    JPanel wholePanel;
    JPanel buttonPanel;
    JPanel bottomPanel;
    JTextField turnTF;
    JButton quitButton;

    TicTacToeTile[][] board = new TicTacToeTile[3][3];

    String player = "X";
    private static final int ROW = 3;
    private static final int COL = 3;
    int winMoves = 5;
    int tieMoves = 7;
    int moveCount = 0;

    public TicTacToeFrame()
    {
        wholePanel = new JPanel();
        wholePanel.setLayout(new BorderLayout());
        createButtons();
        buttonPanel.setPreferredSize(new Dimension(300,300));
        wholePanel.setBackground(Color.white);
        wholePanel.add(buttonPanel);
        createBottomPanel();
        wholePanel.add(bottomPanel, BorderLayout.SOUTH);
        add(wholePanel);
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createButtons()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].setText("");
                board[row][col].setFont(new Font(null, Font.BOLD, 50));
                board[row][col].setBackground(Color.white);

                buttonPanel.add(board[row][col]);
                int selectedRow = row;
                int selectedCol = col;

                board[row][col].addActionListener((ActionEvent ae) ->
                {
                    if (isValidMove(selectedRow, selectedCol))
                    {
                        board[selectedRow][selectedCol].setText(player);
                        moveCount++;

                        if (moveCount >= winMoves) {if(isWin(player)) {gameOverPopUp("Win", player);}}
                        if (moveCount >= tieMoves) {if (isTie()) {gameOverPopUp("Tie", player);}}

                        player = togglePlayer(player);
                        turnTF.setText("Turn: " + player);
                    }
                    else {JOptionPane.showMessageDialog(null, "Invalid Move");}
                });
            }
        }
    }

    public void gameOverPopUp(String outcomeString, String player)
    {
        int popUpGameOver;
        String gameOutcomeString = "";

        if (outcomeString == "Win") {gameOutcomeString = "Player " + player + " wins.\n\nPlay again?";}
        if (outcomeString == "Tie") {gameOutcomeString = "It's a tie.\n\nPlay again?";}

        popUpGameOver = JOptionPane.showConfirmDialog(null, gameOutcomeString, "Game over", JOptionPane.YES_NO_OPTION);

        if (popUpGameOver == JOptionPane.YES_OPTION) {clearBoard();}
        else {System.exit(0);}
    }


    public void clearBoard()
    {
        for( int row = 0; row < 3; row++) {for(int col= 0; col < 3; col++) {board[row][col].setText("");}}
        player = "O";
    }

    public boolean isValidMove(int row, int col)
    {
        boolean validMove;
        if(board[row][col].getText().equals("")){validMove = true;}
        else{validMove = false;}
        return validMove;
    }

    public boolean isWin(String player) {return(isColWin(player) || isRowWin(player) || isDiagonalWin(player));}

    public boolean isColWin(String player)
    {
        boolean hasWon = false;
        for(int x = 0; x < COL; x++) {if(board[0][x].getText().equals(player) && board[1][x].getText().equals(player) && board[2][x].getText().equals(player)) {hasWon = true;}}
        return hasWon;
    }

    public boolean isRowWin(String player)
    {
        boolean hasWon = false;
        for(int x = 0; x < COL; x++) {if(board[x][0].getText().equals(player) && board[x][1].getText().equals(player) && board[x][2].getText().equals(player)) {hasWon = true;}}
        return hasWon;
    }

    public boolean isDiagonalWin(String player)
    {
        boolean hasWon = false;
        if(board[0][0].getText().equals(player) && board[1][1].getText().equals(player) && board[2][2].getText().equals(player)) {hasWon = true;}
        if(board[0][2].getText().equals(player) && board[1][1].getText().equals(player) && board[2][0].getText().equals(player)) {hasWon = true;}
        return hasWon;
    }

    public boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].getText().equals("X") || board[row][1].getText().equals("X") || board[row][2].getText().equals("X")) {xFlag = true;}
            if(board[row][0].getText().equals("O") || board[row][1].getText().equals("O") || board[row][2].getText().equals("O")) {oFlag = true;}
            if(! (xFlag && oFlag) ) {return false;}
            xFlag = oFlag = false;

        }
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].getText().equals("X") || board[1][col].getText().equals("X") || board[2][col].getText().equals("X")) {xFlag = true;}
            if(board[0][col].getText().equals("O") || board[1][col].getText().equals("O") || board[2][col].getText().equals("O")) {oFlag = true;}
            if(! (xFlag && oFlag) ) {return false;}
            xFlag = oFlag = false;
        }

        if(board[0][0].getText().equals("X") || board[1][1].getText().equals("X") || board[2][2].getText().equals("X")) {xFlag = true;}
        if(board[0][0].getText().equals("O") || board[1][1].getText().equals("O") || board[2][2].getText().equals("O")) {oFlag = true;}
        if(! (xFlag && oFlag) ) {return false;}
        xFlag = oFlag = false;

        if(board[0][2].getText().equals("X") || board[1][1].getText().equals("X") || board[2][0].getText().equals("X")) {xFlag =  true;}
        if(board[0][2].getText().equals("O") || board[1][1].getText().equals("O") || board[2][0].getText().equals("O")) {oFlag =  true;}
        if(! (xFlag && oFlag) ) {return false;}

        return true;
    }

    public String togglePlayer(String player)
    {
        if(player.equals("X")) {return "O";}
        else {return "X";}
    }

    public void createBottomPanel()
    {
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.white);
        bottomPanel.setLayout(new GridLayout(1,2));
        turnTF = new JTextField();
        turnTF.setEditable(false);
        turnTF.setText("Turn: " + player);
        turnTF.setBackground(Color.white);
        quitButton = new JButton("Quit");
        quitButton.setBackground(Color.white);
        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));

        bottomPanel.add(turnTF);
        bottomPanel.add(quitButton);
    }
}
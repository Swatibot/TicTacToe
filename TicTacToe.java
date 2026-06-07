import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;
    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton[][] buttons = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;

    TicTacToe() {
        // Frame setup
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top label
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        // Board setup
        boardPanel.setLayout(new GridLayout(3, 3));
        frame.add(boardPanel, BorderLayout.CENTER);

        // Buttons (tiles)
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c] = new JButton();
                buttons[r][c].setBackground(Color.darkGray);
                buttons[r][c].setForeground(Color.white);
                buttons[r][c].setFont(new Font("Arial", Font.BOLD, 120));
                buttons[r][c].setFocusPainted(false);
                boardPanel.add(buttons[r][c]);

                buttons[r][c].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (!tile.getText().equals("")) return; 

                        tile.setText(currentPlayer);
                        checkWinner();

                        if (!gameOver) {
                            currentPlayer = (currentPlayer.equals(playerX)) ? playerO : playerX;
                            textLabel.setText(currentPlayer + "'s turn");
                        }
                    }
                });
            }
        }
    }

    void checkWinner() {
        // Rows & columns
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") &&
                buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText())) {
                endGame(buttons[i][0].getText() + " wins!");
                return;
            }

            if (!buttons[0][i].getText().equals("") &&
                buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText())) {
                endGame(buttons[0][i].getText() + " wins!");
                return;
            }
        }

        if (!buttons[0][0].getText().equals("") &&
            buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText())) {
            endGame(buttons[0][0].getText() + " wins!");
            return;
        }

        if (!buttons[0][2].getText().equals("") &&
            buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText())) {
            endGame(buttons[0][2].getText() + " wins!");
            return;
        }

        boolean draw = true;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (buttons[r][c].getText().equals("")) {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) endGame("It's a draw!");
    }

    void endGame(String message) {
        gameOver = true;
        textLabel.setText(message);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
    


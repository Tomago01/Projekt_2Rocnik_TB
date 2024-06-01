package LightsOut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class tha handle the logic for button clicks in the Lights Out game on the board.
 */
public class ButtonsClickLogic implements ActionListener {
    private int row;
    private int column;
    private int[][] board;
    private JButton[][] buttons;
    private int size;

    /**
     * Constructor to initialize the button click logic.
     *
     * @param row     the row in the board of the buttons
     * @param column  the column in the board of the buttons
     * @param board   the game board represented as a two-dimensional array
     * @param buttons the buttons on the game board
     * @param size    the size of the game board(x or y)
     */
    public ButtonsClickLogic(int row, int column, int[][] board, JButton[][] buttons, int size) {
        this.row = row;
        this.column = column;
        this.board = board;
        this.buttons = buttons;
        this.size = size;
    }

    /**
     * Handles the action event when some button is clicked.
     *
     * @param e action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (board[row][column] == Color.BLACK.getRGB()) {
            board[row][column] = Color.WHITE.getRGB();
        } else {
            board[row][column] = Color.BLACK.getRGB();
        }
        buttons[row][column].setBackground(new Color(board[row][column]));
        toggleColorsAround(row, column);
    }

    /**
     * Toggles the color of the button at the specified row and column
     * if the position is valid (within the bounds of the board).
     *
     * @param row the row of the button to toggle
     * @param col the column of the button to toggle
     */
    public void toggleColorIfValid(int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            if (board[row][col] == Color.BLACK.getRGB()) {
                board[row][col] = Color.WHITE.getRGB();
            } else {
                board[row][col] = Color.BLACK.getRGB();
            }
            buttons[row][col].setBackground(new Color(board[row][col]));
        }
    }

    /**
     * toggles the buttons by one on each side around the specified button
     *
     * @param row the row of the center button
     * @param col the column of the center button
     */
    private void toggleColorsAround(int row, int col) {
        toggleColorIfValid(row - 1, col);
        toggleColorIfValid(row + 1, col);
        toggleColorIfValid(row, col - 1);
        toggleColorIfValid(row, col + 1);
    }
}

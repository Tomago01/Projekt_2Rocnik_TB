package LightsOut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsClickLogic implements ActionListener {
        private int row;
        private int column;
        private int[][] board;
        private JButton[][] buttons;
        private int size;

        public ButtonsClickLogic(int row, int column, int[][] board, JButton[][] buttons, int size) {
            this.row = row;
            this.column = column;
            this.board = board;
            this.buttons = buttons;
            this.size = size;
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (board[row][column] == Color.BLACK.getRGB()) {
            board[row][column] = Color.WHITE.getRGB();
        } else {
            board[row][column] = Color.BLACK.getRGB();
        }
        buttons[row][column].setBackground(new Color(board[row][column]));
        toggleColorsAround(row,column);

    }

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

        private void toggleColorsAround(int row, int col) {
            toggleColorIfValid(row - 1, col);
            toggleColorIfValid(row + 1, col);
            toggleColorIfValid(row, col - 1);
            toggleColorIfValid(row, col + 1);
        }


}

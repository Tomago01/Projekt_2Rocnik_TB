import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogicButtonsClick implements ActionListener {
        private int row;
        private int column;
        private int[][] board;
        private JButton[][] buttons;
        private int size;

        public LogicButtonsClick(int row, int column, int[][] board, JButton[][] buttons, int size) {
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
    }
}

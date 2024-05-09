import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BoardLightsOut extends JFrame {
        private int[][] board;
        private int size;
        private JButton[][] buttons;

    public BoardLightsOut(int size) {
        this.size = size;
        this.board = new int[size][size];
        this.buttons = new JButton[size][size];

        }
    private void initializeBoard() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (random.nextBoolean()) {
                    board[i][j] = Color.BLACK.getRGB();
                } else {
                    board[i][j] = Color.WHITE.getRGB();
                }
                }
            }
        }








}

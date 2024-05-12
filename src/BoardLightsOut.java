import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BoardLightsOut extends JFrame {
        private int[][] board;
        private int size;
        private JButton[][] buttons;

        private LogicButtonsClick logicButtonsClick;



    public BoardLightsOut(int size) {
        this.logicButtonsClick = new LogicButtonsClick(0, 0, board, buttons, size);
        this.size = size;
        this.board = new int[size][size];
        this.buttons = new JButton[size][size];
        initializeBoard();
        initializeGame();

        }


    private void shuffleBoard() {
        Random rand = new Random();
        int numShuffles = rand.nextInt(1000);

        for (int i = 0; i < numShuffles; i++) {
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            logicButtonsClick.toggleColorIfValid(row, col);
            logicButtonsClick.toggleColorIfValid(row - 1, col); // Up
            logicButtonsClick.toggleColorIfValid(row + 1, col); // Down
            logicButtonsClick.toggleColorIfValid(row, col - 1); // Left
            logicButtonsClick.toggleColorIfValid(row, col + 1); // Right
        }
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

    private void initializeGame() {
        setTitle("Lights Out Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int windowSize = 1000;
        setSize(windowSize, windowSize);

        JPanel boardPanel = new JPanel(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = new JButton();
                button.setBackground(new Color(board[i][j]));
                button.addActionListener(new LogicButtonsClick(i, j,board,buttons,size));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }

        getContentPane().add(boardPanel);
        setVisible(true);
        setResizable(true);
    }









}

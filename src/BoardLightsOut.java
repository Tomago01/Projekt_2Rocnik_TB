import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BoardLightsOut extends JFrame {
    private int[][] board;
    private int size;
    private JButton[][] buttons;

    private ButtonsClickLogic buttonsClickLogic;


    public BoardLightsOut(int size) {
        this.size = size;
        this.board = new int[size][size];
        this.buttons = new JButton[size][size];
        this.buttonsClickLogic = new ButtonsClickLogic(0, 0, board, buttons, size);
        initializeBoard();
        initializeGame();

    }


    private void shuffleBoard() {
        Random rand = new Random();
        int numShuffles = rand.nextInt(1000);

        for (int i = 0; i < numShuffles; i++) {
            int row = rand.nextInt(size);
            int col = rand.nextInt(size);
            buttonsClickLogic.toggleColorIfValid(row, col);
            buttonsClickLogic.toggleColorIfValid(row - 1, col);
            buttonsClickLogic.toggleColorIfValid(row + 1, col);
            buttonsClickLogic.toggleColorIfValid(row, col - 1);
            buttonsClickLogic.toggleColorIfValid(row, col + 1);
            if (areAllLightsOff()) {
                shuffleBoard();
            }
        }
    }

    public boolean areAllLightsOff() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != Color.BLACK.getRGB()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void handleClick(int row, int col) {
        buttonsClickLogic.toggleColorIfValid(row, col);
        buttonsClickLogic.toggleColorIfValid(row - 1, col);
        buttonsClickLogic.toggleColorIfValid(row + 1, col);
        buttonsClickLogic.toggleColorIfValid(row, col - 1);
        buttonsClickLogic.toggleColorIfValid(row, col + 1);
        if (areAllLightsOff()) {
            System.out.println("WINNER");
            dispose();
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

    public void initializeGame() {
        setTitle("Lights Out Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 950);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = new JButton();
                button.setBackground(new Color(board[i][j]));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();
                        int row = -1, col = -1;
                        for (int i = 0; i < size; i++) {
                            for (int j = 0; j < size; j++) {
                                if (buttons[i][j] == clickedButton) {
                                    row = i;
                                    col = j;
                                    break;
                                }
                            }
                        }
                        if (row != -1 && col != -1) {
                            handleClick(row, col);
                        }
                    }
                });
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }

        JPanel newGamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton newGameButton = new JButton("Shuffle");
        newGamePanel.add(newGameButton);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuffleBoard();
            }
        });

        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(newGamePanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        setResizable(false);
        setVisible(true);
    }


}

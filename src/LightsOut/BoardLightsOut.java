package LightsOut;

import LightsOut.ButtonsClickLogic;
import LightsOut.TimeCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * The BoardLightsOut class represents the main game board for the Lights Out game.
 * It initializes the game board, handles user interactions, and manages the game state.
 */
public class BoardLightsOut extends JFrame {
    private int[][] board;
    private int size;
    public JButton[][] buttons;

    private ButtonsClickLogic buttonsClickLogic;
    private TimeCounter timeCounter;

    public int moveCounter;

    /**
     * Constructs a new BoardLightsOut game with the specified board size.
     *
     * @param size the size of the game board
     */
    public BoardLightsOut(int size) {
        this.size = size;
        this.moveCounter = 0;
        this.board = new int[size][size];
        this.buttons = new JButton[size][size];
        this.buttonsClickLogic = new ButtonsClickLogic(0, 0, board, buttons, size);
        initializeBoard();
        initializeGame();
    }

    /**
     * Shuffles the game board to create a randomized starting configuration.
     * Ensures that the board is not already solved after shuffling.
     */
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

    /**
     * Checks if all the lights on the board are turned off.
     *
     * @return true if all lights are off, false otherwise
     */
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

    /**
     * Handles the logic for when a button on the board is clicked.
     * Toggles the color of the clicked button and its adjacent buttons.
     *
     * @param row the row of the clicked button
     * @param col the column of the clicked button
     */
    private void handleClick(int row, int col) {
        buttonsClickLogic.toggleColorIfValid(row, col);
        buttonsClickLogic.toggleColorIfValid(row - 1, col);
        buttonsClickLogic.toggleColorIfValid(row + 1, col);
        buttonsClickLogic.toggleColorIfValid(row, col - 1);
        buttonsClickLogic.toggleColorIfValid(row, col + 1);
        if (areAllLightsOff()) {
            int finalCountMoves = moveCounter + 1;
            JOptionPane.showMessageDialog(
                    this,
                    "Congratulations!\nYou solved the puzzle in " + finalCountMoves + " moves.\nTime taken: " + timeCounter.getCurrentTime(),
                    "You Win!",
                    JOptionPane.INFORMATION_MESSAGE
            );
            dispose();
        }
    }

    /**
     * Initializes the game board with a random configuration of lights (on/off).
     */
    public void initializeBoard() {
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

    /**
     * Initializes the game window and user interface components.
     * Sets up the board, buttons, shuffle button, move counter, and timer.
     */
    public void initializeGame() {
        setTitle("Lights Out Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 950);

        JLabel counterLabel = new JLabel("Moves: 0");

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel boardPanel = new JPanel(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JButton button = new JButton();
                button.setBackground(new Color(board[i][j]));
                JPanel moveCounterPanel = new JPanel();
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton clickedButton = (JButton) e.getSource();
                        int row = -1, col = -1;
                        for (int k = 0; k < size; k++) {
                            for (int l = 0; l < size; l++) {
                                if (buttons[k][l] == clickedButton) {
                                    row = k;
                                    col = l;
                                    break;
                                }
                            }
                            if (row != -1 && col != -1) {
                                break;
                            }
                        }
                        handleClick(row, col);
                        moveCounter++;
                        counterLabel.setText("Moves: " + moveCounter);
                    }
                });
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }

        JPanel settingPanel = new JPanel(new BorderLayout());

        JPanel timerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel timerLabel = new JLabel();
        timerPanel.add(timerLabel);
        this.timeCounter = new TimeCounter(timerLabel);

        JButton SuffleButton = new JButton("Shuffle");
        SuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shuffleBoard();
                moveCounter = 0;
                counterLabel.setText("Moves: 0");
            }
        });

        JPanel counterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        counterPanel.add(counterLabel);

        settingPanel.add(SuffleButton, BorderLayout.CENTER);
        settingPanel.add(timerPanel, BorderLayout.WEST);
        settingPanel.add(counterPanel, BorderLayout.EAST);

        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(settingPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        setResizable(false);
        setVisible(true);

        TimeCounter timer = new TimeCounter(timerLabel);
        new Thread(timer).start();
    }
}

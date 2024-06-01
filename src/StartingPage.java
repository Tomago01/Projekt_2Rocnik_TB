import LightsOut.BoardLightsOut;
import Sokoban.BoardSokoban;
import Sokoban.Levels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Represents the starting page for the Puzzle Games project.
 * Allows the user to choose between different puzzle games, right now it is Lights Out and Sokoban.
 */
public class StartingPage extends JFrame {
    private BoardLightsOut gameLightsOutWindow = null;

    private JFrame newWindow;
    private JFrame sokobanWindow;
    private boolean windowIsVisible = false;
    private BoardSokoban boardSokoban;

    /**
     * Constructs a StartingPage object and initializes the starting page for whole project the Puzzle Games.
     */
    public StartingPage() {
        super("Puzzle Games - Game hub");
        createAndShowGamehub();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creates and displays the hub or starting page with buttons for choosing between different puzzle games.
     */
    private void createAndShowGamehub() {
        getContentPane().setBackground(new Color(255, 112, 112));
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

        JButton button1 = new JButton("Lights Out");
        button1.setBackground(new Color(0xFFED00));
        button1.setForeground(Color.BLACK);
        button1.setPreferredSize(new Dimension(200, 50));
        button1.setFont(new Font("Arial", Font.BOLD, 16));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!windowIsVisible && (newWindow == null || !newWindow.isVisible())) {
                    newWindow = new JFrame("Choose Difficulty");
                    difficultyChooserForLO();
                    newWindow.setSize(280, 400);
                    newWindow.setVisible(true);
                    windowIsVisible = true;
                } else {
                    newWindow.toFront();
                }
            }
        });
        add(button1);

        JButton button2 = new JButton("Sokoban");
        button2.setBackground(new Color(0, 216, 255));
        button2.setForeground(Color.BLACK);
        button2.setPreferredSize(new Dimension(200, 50));
        button2.setFont(new Font("Arial", Font.BOLD, 16));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newWindow == null || !newWindow.isVisible()) {
                    newWindow = new JFrame("Choose Difficulty");
                    levelChooserForSokoban();
                    newWindow.setSize(280, 400);
                    newWindow.setVisible(true);
                } else {
                    newWindow.toFront();
                }
            }
        });
        add(button2);
        pack();
    }

    /**
     * Creates a window for choosing difficulty levels for the game Lights Out .
     */
    private void difficultyChooserForLO() {
        newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                windowIsVisible = false;
            }
        });
        newWindow.setLayout(new GridLayout(6, 1));
        newWindow.setResizable(true);

        String[] buttonLabels = {"3*3", "5*5", "7*7", "9*9", "11*11", "13*13"};
        String[] difficultyLabels = {"Difficulty: Easy", "Difficulty: Medium", "Difficulty: Hard", "Difficulty: Very Hard", "Difficulty: Extreme", "Difficulty: Impossible"};
        int[] boardSizes = {3, 5, 7, 9, 11, 13};

        Font buttonFont = new Font("default", Font.BOLD, 30);
        Dimension buttonSize = new Dimension(200, 100);

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = createButton(buttonLabels[i], buttonFont, buttonSize, new Color(0xFF962F));
            JLabel label = new JLabel(difficultyLabels[i], JLabel.CENTER);
            JPanel panel = createPanel(button, label);
            newWindow.add(panel);

            int boardSize = boardSizes[i];
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createGameWindow(boardSize);
                }
            });
        }

        newWindow.pack();
        newWindow.setLocationRelativeTo(null);
        newWindow.setVisible(true);
        newWindow.setResizable(false);
    }

    /**
     * Creates a JButton with specified text, font, size, and background color.
     *
     * @param text text to display on the button
     * @param font font of the button text
     * @param size size of the button
     * @param color background color of the button
     * @return returns the created JButton
     */
    private JButton createButton(String text, Font font, Dimension size, Color color) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setPreferredSize(size);
        button.setBackground(color);
        return button;
    }

    /**
     * Creates a JPanel containing the specified button and label.
     *
     * @param button the button to add to the panel
     * @param label  the label to add to the panel
     * @return returns the created JPanel
     */
    private JPanel createPanel(JButton button, JLabel label) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(button, BorderLayout.CENTER);
        panel.add(label, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * Creates a window for the Lights Out game with the specified board size.
     *
     * @param size size of the Lights Out game board.
     */
    private void createGameWindow(int size) {
        if (gameLightsOutWindow == null || !gameLightsOutWindow.isVisible()) {
            gameLightsOutWindow = new BoardLightsOut(size);
        }
    }

    /**
     * Opens a window for the Sokoban game with the specified level number and with supportive buttons Check, Restart and Quit.
     *
     * @param levelNumber is number of the Sokoban game level to open
     */
    private void openSokobanWindow(int levelNumber) {
        sokobanWindow = new JFrame("Sokoban - Level " + levelNumber);
        boardSokoban = new BoardSokoban(Levels.getLevel(levelNumber), levelNumber);


        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        Color buttonColor = new Color(0, 216, 255);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(200, 50));
        restartButton.setFont(buttonFont);
        restartButton.setBackground(buttonColor);
        JButton checkButton = new JButton("Check");
        checkButton.setPreferredSize(new Dimension(200, 50));
        checkButton.setFont(buttonFont);
        checkButton.setBackground(buttonColor);
        JButton quitButton = new JButton("Quit");
        quitButton.setPreferredSize(new Dimension(200, 50));
        quitButton.setFont(buttonFont);
        quitButton.setBackground(buttonColor);


        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardSokoban.restartLevel();
                boardSokoban.requestFocusInWindow();
            }
        });

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean allBoxesOnPlace = boardSokoban.checkAllBoxesOnPlace();
                if (allBoxesOnPlace) {
                    JOptionPane.showMessageDialog(null, "Congrats! All boxes are on on place!");
                    sokobanWindow.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Not all boxes are properly placed. Keep trying!");
                    sokobanWindow.requestFocusInWindow();
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sokobanWindow.dispose();
            }
        });

        buttonPanel.add(restartButton);
        buttonPanel.add(checkButton);
        buttonPanel.add(quitButton);
        sokobanWindow.add(boardSokoban, BorderLayout.CENTER);
        sokobanWindow.add(buttonPanel, BorderLayout.SOUTH);

        switch (levelNumber) {
            case 1:
                sokobanWindow.setSize(new Dimension(800, 800));
                break;
            case 2:
                sokobanWindow.setSize(new Dimension(1000, 900));
                break;
            case 3:
                sokobanWindow.setSize(new Dimension(700, 800));
                break;
            case 4:
                sokobanWindow.setSize(new Dimension(800, 1000));
                break;
            case 5:
                sokobanWindow.setSize(new Dimension(800, 800));
                break;
            case 6:
                sokobanWindow.setSize(new Dimension(800, 800));
                break;
            case 7:
                sokobanWindow.setSize(new Dimension(800, 800));
                break;
            case 8:
                sokobanWindow.setSize(new Dimension(1100, 1000));
                break;
            case 9:
                sokobanWindow.setSize(new Dimension(700, 800));
                break;
            case 10:
                sokobanWindow.setSize(new Dimension(800, 800));
                break;
            default:
                sokobanWindow.setSize(new Dimension(800, 800));
                break;
        }
        sokobanWindow.setResizable(false);

        sokobanWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sokobanWindow.setLocationRelativeTo(null);
        sokobanWindow.setVisible(true);

        boardSokoban.requestFocusInWindow();
    }

    /**
     * Creates a window for user to choose Sokoban game level.
     */

    private void levelChooserForSokoban() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));

        String[] levels = {
                "Level 1", "Level 2", "Level 3", "Level 4", "Level 5",
                "Level 6", "Level 7", "Level 8", "Level 9", "Level 10"
        };

        for (int i = 0; i < levels.length; i++) {
            int levelNumber = i + 1;
            JButton levelButton = new JButton(levels[i]);
            levelButton.setPreferredSize(new Dimension(200, 50));
            levelButton.setFont(new Font("Arial", Font.BOLD, 23));
            levelButton.setFocusPainted(false);
            levelButton.setBackground(new Color(0, 216, 255));
            levelButton.setForeground(Color.black);

            levelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (sokobanWindow != null && sokobanWindow.isVisible()) {
                        sokobanWindow.toFront();
                    } else {
                        openSokobanWindow(levelNumber);
                    }
                }
            });

            panel.add(levelButton);
        }

        newWindow = new JFrame();
        newWindow.setLayout(new BorderLayout());
        newWindow.setSize(400, 400);
        newWindow.setLocationRelativeTo(null);
        newWindow.add(panel, BorderLayout.CENTER);

        newWindow.setVisible(true);
    }


}

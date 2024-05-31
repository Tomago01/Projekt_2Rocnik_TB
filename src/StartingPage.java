import LightsOut.BoardLightsOut;
import Sokoban.BoardSokoban;
import Sokoban.Levels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartingPage extends JFrame {
    private BoardLightsOut gameLightsOutWindow = null;

    private JFrame chooserLightsOutWindow;
    private JFrame sokobanWindow;
    private boolean windowIsVisible = false;
    private BoardSokoban boardSokoban;

    public StartingPage() {
        super("Game hub - Game hub");
        createAndShowGamehub();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

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
                if (!windowIsVisible && (chooserLightsOutWindow == null || !chooserLightsOutWindow.isVisible())) {
                    chooserLightsOutWindow = new JFrame("Choose Difficulty");
                    difficultyChooserForLO();
                    chooserLightsOutWindow.setSize(280, 400);
                    chooserLightsOutWindow.setVisible(true);
                    windowIsVisible = true;
                } else {
                    chooserLightsOutWindow.toFront();
                }
            }
        });
        add(button1);
        pack();
    }

    private void difficultyChooserForLO() {
        chooserLightsOutWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        chooserLightsOutWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                windowIsVisible = false;
            }
        });
        chooserLightsOutWindow.setLayout(new GridLayout(4, 1));
        chooserLightsOutWindow.setResizable(true);

        String[] buttonLabels = {"3*3", "4*4", "5*5", "6*6"};
        String[] difficultyLabels = {"Difficulty: Easy", "Difficulty: Medium", "Difficulty: Hard", "Difficulty: Expert"};
        int[] boardSizes = {3, 4, 5, 6};

        Font buttonFont = new Font("default", Font.BOLD, 30);
        Dimension buttonSize = new Dimension(200, 100);

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = createButton(buttonLabels[i], buttonFont, buttonSize, new Color(0xFF962F));
            JLabel label = new JLabel(difficultyLabels[i], JLabel.CENTER);
            JPanel panel = createPanel(button, label);
            chooserLightsOutWindow.add(panel);

            int boardSize = boardSizes[i];
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createGameWindow(boardSize);
                }
            });
        }


        chooserLightsOutWindow.pack();
        chooserLightsOutWindow.setLocationRelativeTo(null);
        chooserLightsOutWindow.setVisible(true);
        chooserLightsOutWindow.setResizable(false);
    }

    private JButton createButton(String text, Font font, Dimension size, Color color) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setPreferredSize(size);
        button.setBackground(color);
        return button;
    }


    private JPanel createPanel(JButton button, JLabel label) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(button, BorderLayout.CENTER);
        panel.add(label, BorderLayout.SOUTH);
        return panel;
    }

    private void createGameWindow(int size) {
        if (gameLightsOutWindow == null || !gameLightsOutWindow.isVisible()) {
            gameLightsOutWindow = new BoardLightsOut(size);
        }
    }

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

        sokobanWindow.requestFocusInWindow();
    }


}

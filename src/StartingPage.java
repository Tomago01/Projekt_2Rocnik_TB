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




}

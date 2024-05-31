import LightsOut.BoardLightsOut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartingPage extends JFrame {
    private BoardLightsOut gameLightsOutWindow = null;

    private JFrame newWindow;
    private boolean windowIsVisible = false;

    public StartingPage() {
        super("Puzzle Games - game hub");
        difficultyChooserForLO();
    }

    private void createAndShowGamehub() {
        JButton button = new JButton("Lights Out Game");
        button.setBackground(Color.YELLOW);
        button.setForeground(Color.BLACK);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!windowIsVisible) {
                    newWindow = new JFrame("Choose Difficulty");
                    difficultyChooserForLO();
                    newWindow.setVisible(true);
                    windowIsVisible = true;
                } else {
                    newWindow.toFront();
                }
            }
        });
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(button);
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void difficultyChooserForLO() {
        newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                windowIsVisible = false;
            }
        });
        newWindow.setLayout(new GridLayout(4, 1));
        newWindow.setResizable(true);

        String[] buttonLabels = {"3*3", "4*4", "5*5", "6*6"};
        String[] difficultyLabels = {"Difficulty: Easy", "Difficulty: Medium", "Difficulty: Hard", "Difficulty: Expert"};
        int[] boardSizes = {3, 4, 5, 6};

        Font buttonFont = new Font("default", Font.BOLD, 30);
        Dimension buttonSize = new Dimension(200, 100);

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = createButton(buttonLabels[i], buttonFont, buttonSize, new Color(0xFF962F)); // Pass the desired color
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

    private JButton createButton(String text, Font font, Dimension size, Color color) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setPreferredSize(size);
        button.setBackground(color); // Set background color
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingPage extends JFrame{


    public StartingPage() {
        super("Game hub");
        createHubWindow();
    }


    private void createHubWindow() {
        JButton button = new JButton("Lights Out Game");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewWindow();
            }
        });
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(button);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void createNewWindow() {
        JFrame w2 = new JFrame("Choose Difficulty");
        w2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        w2.setLayout(new GridLayout(4, 1));
        w2.setResizable(true);

        JButton button1 = new JButton("3*3");
        JButton button2 = new JButton("4*4");
        JButton button3 = new JButton("5*5");
        JButton button4 = new JButton("6*6");
        Dimension buttonSize = new Dimension(200, 100);
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);


        JLabel label1 = new JLabel("Difficulty: Easy", JLabel.CENTER);
        JLabel label2 = new JLabel("Difficulty: Medium", JLabel.CENTER);
        JLabel label3 = new JLabel("Difficulty: Hard", JLabel.CENTER);
        JLabel label4 = new JLabel("Difficulty: Expert", JLabel.CENTER);

        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(button1, BorderLayout.CENTER);
        panel1.add(label1, BorderLayout.SOUTH);

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(button2, BorderLayout.CENTER);
        panel2.add(label2, BorderLayout.SOUTH);

        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.add(button3, BorderLayout.CENTER);
        panel3.add(label3, BorderLayout.SOUTH);

        JPanel panel4 = new JPanel(new BorderLayout());
        panel4.add(button4, BorderLayout.CENTER);
        panel4.add(label4, BorderLayout.SOUTH);

        w2.add(panel1);
        w2.add(panel2);
        w2.add(panel3);
        w2.add(panel4);


        button1.addActionListener(e -> new BoardLightsOut(3));
        button2.addActionListener(e -> new BoardLightsOut(4));
        button3.addActionListener(e -> new BoardLightsOut(5));
        button4.addActionListener(e -> new BoardLightsOut(6));

        w2.pack();
        w2.setLocationRelativeTo(null);
        w2.setVisible(true);
        w2.setResizable(false);
    }








}

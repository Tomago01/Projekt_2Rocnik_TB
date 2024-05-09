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



            }
        });
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(button);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



}

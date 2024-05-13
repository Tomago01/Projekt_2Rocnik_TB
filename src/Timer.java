import javax.swing.*;

public class Timer implements Runnable {

    private JLabel timerLabel;
    private long startTime;

    public Timer(JLabel timerLabel) {
        this.timerLabel = timerLabel;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {

    }
}


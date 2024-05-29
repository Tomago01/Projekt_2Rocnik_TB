package LightsOut;

import javax.swing.*;

public class TimeCounter implements Runnable {

    private JLabel timerLabel;
    private long startTime;

    public TimeCounter(JLabel timerLabel) {
        this.timerLabel = timerLabel;
        this.startTime = System.currentTimeMillis();
    }


    public String getCurrentTime() {
        return currentTime();
    }
    public String currentTime(){
        long elapsedTime = System.currentTimeMillis() - startTime;
        long hours = (elapsedTime / (1000 * 60 * 60)) % 24;
        long minutes = (elapsedTime / (1000 * 60)) % 60;
        long seconds = (elapsedTime / 1000) % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return timeString;
    }

    @Override
    public void run() {
        while (true) {
            SwingUtilities.invokeLater(() -> {
                timerLabel.setText(currentTime());
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


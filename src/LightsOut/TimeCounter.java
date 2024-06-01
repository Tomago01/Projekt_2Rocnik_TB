package LightsOut;

import javax.swing.*;

/**
 * Class to control the time counter in the Lights Out, which is in the bottom left corner.
 */
public class TimeCounter implements Runnable {

    private JLabel timerLabel;
    private long startTime;

    /**
     * Constructor on a TimeCounter object with timer label.
     *
     * @param timerLabel label that displays elapsed time
     */
    public TimeCounter(JLabel timerLabel) {
        this.timerLabel = timerLabel;
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Gets the current elapsed time formatted as a string.
     *
     * @return the current elapsed time as a string
     */
    public String getCurrentTime() {
        return currentTime();
    }

    /**
     * this method calculates current elapsed time and formats it as a string (00:00:00).
     *
     * @return returns current time formatted as a string
     */
    public String currentTime() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        long hours = (elapsedTime / (1000 * 60 * 60)) % 24;
        long minutes = (elapsedTime / (1000 * 60)) % 60;
        long seconds = (elapsedTime / 1000) % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        return timeString;
    }

    /**
     * Runs the time counter in another thread.
     * Updates timer label with method currentTime() every second (1000 ms).
     */
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

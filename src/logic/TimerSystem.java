package logic;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class TimerSystem {
    private int seconds = 0;
    private final Label timerLabel;
    private Thread timerThread;
    private boolean isRunning = false;

    public TimerSystem() {
        timerLabel = new Label("Time: 00:00:00");
        timerLabel.setFont(new Font("Impact", 20));
    }

    public void start() {
        isRunning = true;
        timerThread = new Thread(() -> {
            while (isRunning) {
                try {
                    Thread.sleep(1000);
                    seconds++;
                    Platform.runLater(this::updateLabel);
                } catch (InterruptedException e) {
                    isRunning = false;
                    break;
                }
            }
        });
        timerThread.setDaemon(true);
        timerThread.start();
    }

    public void stop() {
        isRunning = false;
        if (timerThread != null) {
            timerThread.interrupt();
            timerThread = null;
        }
        seconds = 0;
        Platform.runLater(this::updateLabel);
    }

    private void updateLabel() {
    	int hours = seconds / 3600;
        int minutes = (seconds / 60) % 60;
        int secs = seconds % 60;
        timerLabel.setText(String.format("Time: %02d:%02d:%02d" ,hours , minutes, secs));
    }

    public Label getTimerLabel() {
        return timerLabel;
    }
}
package app;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


public class TimeDisplayApp {
    public TimeDisplayApp() {
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Healthy Sleep");
            TimePanel timePanel = new TimePanel();
            frame.add(timePanel, "First");
            JPanel buttonPanel = new JPanel(new FlowLayout());
            JButton relaxingSoundsButton = new JButton("Relaxing Sounds too fall asleep");
            Dimension buttonSize = new Dimension(300, 30);
            relaxingSoundsButton.setPreferredSize(buttonSize);
            buttonPanel.add(relaxingSoundsButton);
            frame.add(buttonPanel, "Center");
            relaxingSoundsButton.addActionListener((e) -> {
                JFrame soundsFrame = new JFrame("Relaxing Sounds too fall asleep");
                soundsFrame.setDefaultCloseOperation(2);
                soundsFrame.setSize(400, 200);
                soundsFrame.add(new RelaxingSounds());
                soundsFrame.setLocationRelativeTo((Component)null);
                soundsFrame.setVisible(true);
            });
            JButton sleepAnalysisButton = new JButton("Sleep Environment Analysis");
            Dimension buttonSize3 = new Dimension(300, 30);
            sleepAnalysisButton.setPreferredSize(buttonSize3);
            sleepAnalysisButton.addActionListener((e) -> {
                JFrame sleepAnalysisFrame = new JFrame("Sleep Environment Analysis");
                sleepAnalysisFrame.setDefaultCloseOperation(2);
                sleepAnalysisFrame.setSize(1000, 830);
                sleepAnalysisFrame.setResizable(false);
                sleepAnalysisFrame.add(new SleepEnvironmentAnalysis());
                sleepAnalysisFrame.setLocationRelativeTo((Component)null);
                sleepAnalysisFrame.setVisible(true);
            });
            buttonPanel.add(sleepAnalysisButton);
            frame.pack();
            frame.setSize(750, 830);
            frame.setLocationRelativeTo((Component)null);
            frame.setVisible(true);
        });
    }
}
class TimePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JLabel timeLabel = new JLabel();
    private final Timer timer;
    private final AlarmPanel alarmPanel = new AlarmPanel();
    private Clip currentClip;


    public TimePanel() {
        this.timeLabel.setFont(new Font("Arial", 0, 92));
        this.updateCurrentTime();
        this.timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TimePanel.this.updateCurrentTime();
            }
        });
        this.timer.start();
        this.setLayout(new BorderLayout());
        this.add(this.timeLabel, "First");
        this.add(this.alarmPanel, "Last");
        this.timeLabel.setHorizontalAlignment(0);
    }


    private void updateCurrentTime() {
        ZonedDateTime cetTime = ZonedDateTime.now(ZoneId.of("CET"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = cetTime.format(formatter);
        this.timeLabel.setText(formattedTime);
        if (this.alarmPanel.isAlarmActive() && LocalTime.now(ZoneId.of("CET")).truncatedTo(ChronoUnit.SECONDS).equals(this.alarmPanel.getAlarmTime())) {
            this.playAlarmSound();
            int result = JOptionPane.showConfirmDialog(this, "Alarm! Time's up!", "Alarm", -1, 1);
            if (result == 0) {
                this.stopAlarmSound();
                this.alarmPanel.toggleAlarm();
            }
        }
    }
    private void playAlarmSound() {
        try {
            File audioFile = new File("birds-19624.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            this.currentClip = clip;
        } catch (Exception var4) {
            System.err.println("Error playing alarm sound: " + var4.getMessage());
        }
    }
    private void stopAlarmSound() {
        if (this.currentClip != null) {
            this.currentClip.stop();
            this.currentClip.flush();
            this.currentClip.close();
        }


    }
}


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
            File audioFile = new File("soundFiles/birds-19624.wav");
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

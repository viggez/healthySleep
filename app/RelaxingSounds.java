package app;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class RelaxingSounds extends JPanel implements ActionListener {
    private JComboBox<String> comboBox;
    private JButton startButton;
    private JButton stopButton;
    private JLabel label = new JLabel("Pick a sound: ");
    Instant endTime;
    Clip clip;


    public RelaxingSounds() {
        this.label.setFont(new Font("Arial", 1, 20));
        this.setBounds(100, 300, 100, 30);
        this.label.setOpaque(true);
        this.add(this.label);
        String[] soundOptions = new String[]{"Rain and thunderstorm", "Rain on window", "Relaxing piano"};
        this.comboBox = new JComboBox(soundOptions);
        this.setBounds(70, 300, 300, 30);
        this.comboBox.addActionListener(this);
        this.add(this.comboBox);
        this.startButton = new JButton("Play sound");
        this.startButton.setFocusable(false);
        this.setBounds(50, 20, 30, 30);
        this.startButton.addActionListener(this);
        this.add(this.startButton);
        this.stopButton = new JButton("Stop sound");
        this.stopButton.setFocusable(false);
        this.stopButton.addActionListener(this);
        this.stopButton.setEnabled(false);
        this.add(this.stopButton);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.comboBox) {
            if (this.clip != null && this.clip.isRunning()) {
                this.clip.stop();
            }


            this.startButton.setEnabled(true);
        } else if (e.getSource() == this.startButton) {
            String selectedSound = (String)this.comboBox.getSelectedItem();
            if (selectedSound != null) {
                try {
                    File audioFile = new File(this.getAudioFilePath(selectedSound));
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                    AudioFormat format = audioStream.getFormat();
                    DataLine.Info info = new DataLine.Info(Clip.class, format);
                    this.clip = (Clip)AudioSystem.getLine(info);
                    this.clip.open(audioStream);
                    this.clip.loop(-1);
                    this.endTime = Instant.now().plus(3L, ChronoUnit.HOURS);
                    this.startButton.setEnabled(false);
                    this.stopButton.setEnabled(true);
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException var7) {
                    var7.printStackTrace();
                }
            }
        } else if (e.getSource() == this.stopButton) {
            this.stopAudio();
        }
    }

    private void stopAudio() {
        if (this.clip != null && this.clip.isRunning()) {
            this.clip.stop();
            this.startButton.setEnabled(true);
            this.stopButton.setEnabled(false);
        }
    }

    private String getAudioFilePath(String soundName) {
        String filePath = "";
        switch (soundName) {
            case "Rain and thunderstorm":
                filePath = "soundFiles/rain-and-thunder-nature-sounds-7803.wav";
                break;
            case "Rain on window":
                filePath = "soundFiles/rain-on-roof-or-window-nature-sounds-8312.wav";
                break;
            case "Relaxing piano":
                filePath = "soundFiles/the-last-piano-112677.wav";
        }

        return filePath;
    }
}

package app;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.time.DateTimeException;
import java.time.LocalTime;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


// This class handles the Alarm panel
public class AlarmPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public final JTextField hourField = new JTextField(2);
    public final JTextField minuteField = new JTextField(2);
    public final JTextField secondField = new JTextField(2);
    private final JButton alarmButton;
    private boolean alarmActive = false;
    private LocalTime alarmTime = null;


    // This method adds the frame and position of the alarm panel
    public AlarmPanel() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(650, 150));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = 2;
        this.alarmButton = new JButton("Set Alarm");
        this.alarmButton.addActionListener((e) -> {
            this.toggleAlarm();
        });
        c.gridx = 0;
        c.gridy = 0;
        this.add(new JLabel("Hour:"), c);
        c.gridx = 1;
        c.gridy = 0;
        this.add(this.hourField, c);
        c.gridx = 2;
        c.gridy = 0;
        this.add(new JLabel("Minute:"), c);
        c.gridx = 3;
        c.gridy = 0;
        this.add(this.minuteField, c);
        c.gridx = 4;
        c.gridy = 0;
        this.add(new JLabel("Second:"), c);
        c.gridx = 5;
        c.gridy = 0;
        this.add(this.secondField, c);
        c.gridx = 6;
        c.gridy = 0;
        this.add(this.alarmButton, c);
    }


    // This method handles the users input for when to trigger the alarm
    public void toggleAlarm() {
        if (!this.alarmActive) {
            try {
                int hour = Integer.parseInt(this.hourField.getText());
                int minute = Integer.parseInt(this.minuteField.getText());
                int second = Integer.parseInt(this.secondField.getText());
                this.alarmTime = LocalTime.of(hour, minute, second);
                this.alarmActive = true;
                this.alarmButton.setText("Cancel Alarm");
            } catch (DateTimeException | NumberFormatException var4) {
                JOptionPane.showMessageDialog(this, "Invalid time format. Please enter valid values.", "Error", 0);
            }
        } else {
            this.alarmActive = false;
            this.alarmButton.setText("Set Alarm");
        }
    }

    public boolean isAlarmActive() {
        return this.alarmActive;
    }

    public LocalTime getAlarmTime() {
        return this.alarmTime;
    }
}

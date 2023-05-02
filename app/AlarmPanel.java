package app;

import javax.swing.*;
import java.awt.Dimension;
import java.time.*;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;



public class AlarmPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public final JTextField hourField;
    public final JTextField minuteField;
    public final JTextField secondField;
    private final JButton alarmButton;
    private boolean alarmActive = false;
    private LocalTime alarmTime = null;

    public AlarmPanel() {
        // Create input fields for setting the alarm
        hourField = new JTextField(2);
        minuteField = new JTextField(2);
        secondField = new JTextField(2);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(650, 150));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        // Create the alarm activation button
        alarmButton = new JButton("Set Alarm");
        alarmButton.addActionListener(e -> toggleAlarm());

        // Add input fields and button to the panel
        c.gridx = 0;
        c.gridy = 0;
        add(new JLabel("Hour:"), c);

        c.gridx = 1;
        c.gridy = 0;
        add(hourField, c);

        c.gridx = 2;
        c.gridy = 0;
        add(new JLabel("Minute:"), c);
        c.gridx = 3;
        c.gridy = 0;
        add(minuteField, c);
    
        c.gridx = 4;
        c.gridy = 0;
        add(new JLabel("Second:"), c);
    
        c.gridx = 5;
        c.gridy = 0;
        add(secondField, c);
    
        c.gridx = 6;
        c.gridy = 0;
        add(alarmButton, c);
    }

    public void toggleAlarm() {
        if (!alarmActive) {
            try {
                int hour = Integer.parseInt(hourField.getText());
                int minute = Integer.parseInt(minuteField.getText());
                int second = Integer.parseInt(secondField.getText());
                alarmTime = LocalTime.of(hour, minute, second);
                alarmActive = true;
                alarmButton.setText("Cancel Alarm");
            } catch (NumberFormatException | DateTimeException e) {
                JOptionPane.showMessageDialog(this, "Invalid time format. Please enter valid values.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            alarmActive = false;
            alarmButton.setText("Set Alarm");
        }
    }

    public boolean isAlarmActive() {
        return alarmActive;
    }

    public LocalTime getAlarmTime() {
        return alarmTime;
    }
}

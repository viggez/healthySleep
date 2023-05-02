package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.AlarmPanel;

import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class AlarmPanelTest {

    private AlarmPanel alarmPanel;

    @BeforeEach
    void setUp() {
        alarmPanel = new AlarmPanel();
    }

    @Test
    void testToggleAlarm() {
        // Test that the alarm is initially inactive
        assertFalse(alarmPanel.isAlarmActive());

        // Set a valid alarm time
        alarmPanel.hourField.setText("12");
        alarmPanel.minuteField.setText("00");
        alarmPanel.secondField.setText("00");

        // Test that the alarm becomes active after toggling
        alarmPanel.toggleAlarm();
        assertTrue(alarmPanel.isAlarmActive());

        // Test that the alarm becomes inactive after toggling again
        alarmPanel.toggleAlarm();
        assertFalse(alarmPanel.isAlarmActive());
    }

    @Test
    void testValidAlarmTime() {
        // Set a valid alarm time
        alarmPanel.hourField.setText("12");
        alarmPanel.minuteField.setText("00");
        alarmPanel.secondField.setText("00");
        alarmPanel.toggleAlarm();

        // Test that the alarm time is set correctly
        LocalTime expectedAlarmTime = LocalTime.of(12, 0, 0);
        assertEquals(expectedAlarmTime, alarmPanel.getAlarmTime());
    }
}

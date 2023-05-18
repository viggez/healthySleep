package tests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import app.RelaxingSounds;

import static org.junit.jupiter.api.Assertions.*;

class RelaxingSoundsTest {
    private JFrame frame;
    private RelaxingSounds relaxingSounds;

    @BeforeEach
    void setUp() {
        frame = new JFrame();
        relaxingSounds = new RelaxingSounds(frame);
        frame.add(relaxingSounds);
        frame.pack();
        frame.setVisible(true);
    }

    @AfterEach
    void tearDown() {
        frame.dispose();
    }

    @Test
    void testComboBoxSelection() {
        JComboBox<String> comboBox = relaxingSounds.getComboBox();
        comboBox.setSelectedItem("Rain and thunderstorm");
        assertEquals("Rain and thunderstorm", comboBox.getSelectedItem());
    }

    @Test
    void testStartButtonActionPerformed() {
        relaxingSounds.getComboBox().setSelectedItem("Rain and thunderstorm");
        assertTrue(relaxingSounds.getStartButton().isEnabled());
        assertFalse(relaxingSounds.getStopButton().isEnabled());
    }

    @Test
    void testStopButtonActionPerformed() {
        relaxingSounds.getComboBox().setSelectedItem("Rain and thunderstorm");
        JButton startButton = relaxingSounds.getStartButton();
        JButton stopButton = relaxingSounds.getStopButton();
        startButton.doClick();
        stopButton.doClick();
        assertTrue(startButton.isEnabled());
        assertFalse(stopButton.isEnabled());
    }
}

package app;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.*;

// Main class to run the application
public class TimeDisplay {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Creating a new JFrame for the application window
            JFrame frame = new JFrame("Healthy Sleep");
            // Stops the code from running when exiting the window
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Creating a new TimePanel and adding it to the frame
            frame.add(new TimePanel());
            // Adjust the size of the frame to fit its content
            frame.pack();
            // Set the size of the JFrame
            frame.setSize(750, 830);
            // Position the frame in the center of the screen
            frame.setLocationRelativeTo(null);
            // Make the frame visible
            frame.setVisible(true);
        });
    }
}
// Custom JPanel to display the current time
class TimePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final JLabel timeLabel; // JLabel to display the time
    private final Timer timer; // Timer to update the time every second

    public TimePanel() {
        timeLabel = new JLabel(); // Creating a new JLabel for the time
        // Set the font for the timelabel
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 92));
        // Update the timeLabel with the current time
        updateCurrentTime();

        // Create a new Timer that triggers an ActionEvent every second
        timer = new Timer(1000, new ActionListener() {
            // Override the actionPerformed method to handle the timer event
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the timelabel with the current time
                updateCurrentTime();
            }
        });
        timer.start(); // Start the timer

        // Set the layout manager for the TimePanel
        setLayout(new BorderLayout());
        // Add the timeLabel to the top of the window
        add(timeLabel, BorderLayout.PAGE_START);
        // Center the timeLabel horizontally
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    // Method to update the current time displayed in the timeLabel
    private void updateCurrentTime() {
        // Get the current time in CET timezone
        ZonedDateTime cetTime = ZonedDateTime.now(ZoneId.of("CET"));
        // Create a formatter for the time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        // Format the time using the formatter
        String formattedTime = cetTime.format(formatter);
        // Set the text of the timelabel
        timeLabel.setText(formattedTime);
    }
}

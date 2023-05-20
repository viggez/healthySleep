package app;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This is the main class.
// Add new buttons here!
public class Main {
    public Main() {
    }public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Create a database connection
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sleep_environment_db", "root", "healthySleep!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }

            JFrame frame = new JFrame("Healthy Sleep");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            // TimePanel
            TimePanel timePanel = new TimePanel();
            frame.add(timePanel, "First");
            JPanel buttonPanel = new JPanel(new FlowLayout());


            // Daily Tip Button
            JButton dailyTipButton = new JButton("Daily Tip");
            dailyTipButton.setPreferredSize(new Dimension(300, 30));
            dailyTipButton.addActionListener(e -> {
                DailyTip dailyTip = new DailyTip();
                dailyTip.showDailyTip();
            });
            buttonPanel.add(dailyTipButton);


            // Sleep Review button
            // ...

// Sleep Review button
            DataBaseHandler databaseHandler = new DataBaseHandler(connection); // Create a database handler
            SleepReview sleepReview = new SleepReview(databaseHandler);
            JButton sleepReviewButton = new JButton("Review your sleep");
            Dimension buttonSize4 = new Dimension(300, 30);
            sleepReviewButton.setPreferredSize(buttonSize4);
            buttonPanel.add(sleepReviewButton);
            sleepReviewButton.addActionListener(e -> {
                sleepReview.showSleepReviewDialog();
            });


            // relaxingSounds Button
            JButton relaxingSoundsButton = new JButton("Relaxing Sounds to Fall Asleep");
            Dimension buttonSize = new Dimension(300, 30);
            relaxingSoundsButton.setPreferredSize(buttonSize);
            buttonPanel.add(relaxingSoundsButton);
            frame.add(buttonPanel, "Center");
            relaxingSoundsButton.addActionListener((e) -> {
                // Create a new frame for the relaxing sounds
                JFrame soundsFrame = new JFrame("Relaxing Sounds to Fall Asleep");
                soundsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                soundsFrame.setSize(400, 200);
// Add a window listener to stop the audio before closing the frame
                soundsFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        RelaxingSounds relaxingSounds = (RelaxingSounds) ((JFrame) e.getSource()).getContentPane().getComponent(0);
                        relaxingSounds.stopAudio(); // Stop the audio before closing the frame
                    }
                    @Override
                    public void windowClosed(WindowEvent e) {
                        frame.requestFocus();
                    }
                });

                // Add the RelaxingSounds panel to the frame
                soundsFrame.add(new RelaxingSounds(frame));
                soundsFrame.setLocationRelativeTo(null);
                soundsFrame.setVisible(true);
            });
            // sleepAnalysis Button
            JButton sleepAnalysisButton = new JButton("Sleep Environment Analysis");
            Dimension buttonSize3 = new Dimension(300, 30);
            sleepAnalysisButton.setPreferredSize(buttonSize3);
            sleepAnalysisButton.addActionListener((e) -> {
                // Create a new frame for the sleep environment analysis
                JFrame sleepAnalysisFrame = new JFrame("Sleep Environment Analysis");
                sleepAnalysisFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                sleepAnalysisFrame.setResizable(false);

                // Create a SleepEnvironmentAnalysis panel
                SleepEnvironmentAnalysis sleepEnvironmentAnalysis = new SleepEnvironmentAnalysis();
                sleepEnvironmentAnalysis.setPreferredSize(new Dimension(800, 370)); // Adjust the height here

                // Create the content pane and add the SleepEnvironmentAnalysis panel
                JPanel contentPane = new JPanel(new BorderLayout());
                contentPane.add(sleepEnvironmentAnalysis, BorderLayout.CENTER);

                // Create a button panel and add the compareButton
                JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Use FlowLayout with center alignment
                buttonPanel2.add(sleepEnvironmentAnalysis.getCompareButton()); // Use the existing compareButton
                contentPane.add(buttonPanel2, BorderLayout.SOUTH);

                // Set the content pane of the sleep analysis frame
                sleepAnalysisFrame.setContentPane(contentPane);
                sleepAnalysisFrame.pack(); // Pack the components to fit their preferred sizes
                sleepAnalysisFrame.setSize(800, sleepAnalysisFrame.getHeight()); // Set the desired width
                sleepAnalysisFrame.setLocationRelativeTo(null);
                sleepAnalysisFrame.setVisible(true);
            });

            // Add the sleepAnalysisButton to the buttonPanel
            buttonPanel.add(sleepAnalysisButton);

            // Main frame
            frame.pack();
            frame.setSize(750, 830);
            frame.setLocationRelativeTo((Component)null);
            frame.setVisible(true);
        });
    }
}



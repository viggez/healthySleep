package app;


import java.awt.*;
import javax.swing.*;

// This is the main class.
// Add new buttons here!

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

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

            // relaxingSounds Button
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

            // sleepAnalysis Button
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

            // Main frame
            frame.pack();
            frame.setSize(750, 830);
            frame.setLocationRelativeTo((Component)null);
            frame.setVisible(true);
        });
    }
}

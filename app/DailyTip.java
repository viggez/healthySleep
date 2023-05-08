package app;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


// This class handles the Daily Tip feature
public class DailyTip extends JPanel {
    private final JButton dailyTipButton;


    // An array of daily sleep improving tips
    private final String[] tips = {
        "Drink water first thing in the morning to stay hydrated.",
        "Get at least 7-9 hours of sleep every night.",
        "Avoid screen time at least 1 hour before bed.",
        "Exercise regularly to improve sleep quality.",
        "Maintain a consistent sleep schedule, even on weekends.",
        "Create a relaxing bedtime routine to wind down before sleep.",
    };

    private final Random random = new Random();


    // This method handles the layout for the Daily Tip button
    public DailyTip() {
        dailyTipButton = new JButton("Daily Tip");
        dailyTipButton.addActionListener(e -> showDailyTip());

        setLayout(new FlowLayout());
        add(dailyTipButton);
    }


    // Randomizes the message for each button press
    void showDailyTip() {
        int randomIndex = random.nextInt(tips.length);
        String dailyTip = tips[randomIndex];
        JOptionPane.showMessageDialog(this, dailyTip, "Daily Tip", JOptionPane.INFORMATION_MESSAGE);
    }
}

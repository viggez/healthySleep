package app;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

// This class handles the Daily Tip feature
public class DailyTip extends JPanel {
    private final JButton dailyTipButton;
    private final String[] tips = {
            "Drink water first thing in the morning to stay hydrated.",
            "Get at least 7-9 hours of sleep every night.",
            "Avoid screen time at least 1 hour before bed.",
            "Exercise regularly to improve sleep quality.",
            "Maintain a consistent sleep schedule, even on weekends.",
            "Create a relaxing bedtime routine to wind down before sleep."
    };
    private final Random random = new Random();
    private OptionPaneHandler optionPaneHandler;

    public DailyTip() {
        dailyTipButton = new JButton("Daily Tip");
        dailyTipButton.addActionListener(e -> showDailyTip());

        setLayout(new FlowLayout());
        add(dailyTipButton);
    }

    public void setOptionPaneHandler(OptionPaneHandler optionPaneHandler) {
        this.optionPaneHandler = optionPaneHandler;
    }

    public void showDailyTip() {
        String dailyTip = getRandomTip();
        showMessageDialog(dailyTip, "Daily Tip");
    }

    protected void showMessageDialog(String message, String title) {
        if (optionPaneHandler != null) {
            optionPaneHandler.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    protected String getRandomTip() {
        int randomIndex = random.nextInt(tips.length);
        return tips[randomIndex];
    }
}

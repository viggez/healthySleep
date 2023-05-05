package app;

import javax.swing.*;
import java.awt.*;

// This class handles the Daily Tip button and it's messages

public class DailyTip extends JPanel {
    private final JButton dailyTipButton;

    public DailyTip() {
        dailyTipButton = new JButton("Daily Tip");
        dailyTipButton.addActionListener(e -> showDailyTip());

        setLayout(new FlowLayout());
        add(dailyTipButton);
    }

    // This method currently only shows one message
    void showDailyTip() {
        String dailyTip = "Drink water first thing in the morning to stay hydrated.";
        JOptionPane.showMessageDialog(this, dailyTip, "Daily Tip", JOptionPane.INFORMATION_MESSAGE);
    }
}

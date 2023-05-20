package app;

import javax.swing.*;
import java.awt.*;

public class SleepReview extends JPanel {
    public final JButton sleepReviewButton;
    public final DataBaseHandler databaseHandler;
    public OptionPaneHandler optionPane; // Use OptionPaneHandler instead of JOptionPane

    public SleepReview(DataBaseHandler dataBaseHandler) {
        sleepReviewButton = new JButton("Sleep Review");
        sleepReviewButton.addActionListener(e -> showSleepReviewDialog());
        this.databaseHandler = dataBaseHandler;
        setLayout(new FlowLayout());
        add(sleepReviewButton);
        optionPane = new OptionPaneHandler.DefaultOptionPaneHandler(); // Use DefaultOptionPaneHandler
    }

    public void showSleepReviewDialog() {
        String[] options = {"Perfect! :D", "OK :│", "Not so good :("};
        int choice = optionPane.showOptionDialog( // Use optionPane instead of JOptionPane
                this,
                "How was your sleep?",
                "Sleep Review",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice != -1) {
            String selectedOption = options[choice];
            saveSleepReview(selectedOption);
        }
    }

    public void setOptionPane(OptionPaneHandler optionPane) {
        this.optionPane = optionPane;
    }

    public void saveSleepReview(String review) {
        try {
            int totalCaffeine = databaseHandler.getSavedCaffeine();
            int totalScreenTime = databaseHandler.getSavedScreenTime();
            databaseHandler.saveData(totalCaffeine, totalScreenTime, review);
            optionPane.showMessageDialog(this, "Sleep review saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            optionPane.showMessageDialog(this, "Error saving data to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

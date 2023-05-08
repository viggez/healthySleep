package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;


// This class handles the Sleep Review feature
public class SleepReview extends JPanel {
    private final JButton sleepReviewButton;


    // SleepReview Constructor
    public SleepReview() {
        sleepReviewButton = new JButton("Sleep Review");
        sleepReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSleepReviewDialog();
            }
        });

        setLayout(new FlowLayout());
        add(sleepReviewButton);
    }


    // This method handles the popup window when pressing the Sleep Review button
    void showSleepReviewDialog() {
        String[] options = {"Perfect! :D", "OK :│", "Not so good :("};
        int choice = JOptionPane.showOptionDialog(
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


    // This method saves your sleep review into a text file
    private void saveSleepReview(String review) {
        String fileName = "sleep_reviews.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(LocalDate.now() + ": " + review);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Sleep review saved!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving sleep review.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

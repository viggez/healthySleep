package app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SleepEnvironmentAnalysis extends JPanel implements ActionListener {
    // Database connection variables
    private Connection connection;
    String url = "jdbc:mysql://localhost:3306/sleep_environment_db";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "healthysleep!";

    // GUI components
    public JTextField screenTimeField;
    public JButton oneCoffee;
    public JButton twoCoffees;
    public JButton threeCoffees;
    public JButton fourCoffees;
    public JButton fiveCoffees;
    public JLabel caffeineLabel;
    public JLabel screenLabel;
    public JButton compareButton;
    public JLabel resultLabel;
    public JLabel resultLabel2;

    // Sleep analysis variables
    public int totalCaffeine = 0;
    public int totalScreenTime = 0;
    public final int MAX_SCREEN_TIME = 8;
    public final int MAX_CAFFEINE = 400;

    public SleepEnvironmentAnalysis() {
        // Set the layout of the panel
        setLayout(new BorderLayout());

        // Create a panel for the coffee buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        add(buttonPanel, BorderLayout.CENTER);

        // Create coffee buttons and add them to the button panel
        Dimension coffeeButtonSize = new Dimension(80, 40); // Adjust button size here
        oneCoffee = new JButton(new ImageIcon("fotor_2023-5-3_20_37_29.png"));
        oneCoffee.setPreferredSize(coffeeButtonSize);
        buttonPanel.add(oneCoffee);
        // Repeat the same for other coffee buttons (twoCoffees, threeCoffees, fourCoffees, fiveCoffees)

        // Create a panel for the screen time input
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        screenTimeField = new JTextField(10);
        inputPanel.add(new JLabel("Screen Time (hours):"));
        inputPanel.add(screenTimeField);
        add(inputPanel, BorderLayout.NORTH);

        // Create a panel for displaying results
        JPanel resultPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        add(resultPanel, BorderLayout.SOUTH);

        // Create labels for displaying caffeine and screen time information
        caffeineLabel = new JLabel("Total Caffeine: " + totalCaffeine + "mg");
        resultPanel.add(caffeineLabel);
        // Repeat the same for screenLabel

        // Create labels for displaying result messages
        resultLabel = new JLabel();
        resultPanel.add(resultLabel);
        // Repeat the same for resultLabel2

        // Create the compareButton
        compareButton = new JButton("Compare with recommended amount!");
        compareButton.setPreferredSize(new Dimension(280, 40)); // Adjust button size here
        compareButton.addActionListener(this);
        add(compareButton, BorderLayout.EAST);

        // Register action listeners for the coffee buttons
        oneCoffee.addActionListener(this);
        // Repeat the same for other coffee buttons (twoCoffees, threeCoffees, fourCoffees, fiveCoffees)
    }

    // Event handler for button clicks and compareButton
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == oneCoffee) {
            totalCaffeine += 50;
        } else if (e.getSource() == twoCoffees) {
            totalCaffeine += 150;
        } else if (e.getSource() == threeCoffees) {
            totalCaffeine += 200;
        } else if (e.getSource() == fourCoffees) {
            totalCaffeine += 250;
        } else if (e.getSource() == fiveCoffees) {
            totalCaffeine += 300;
        } else if(e.getSource() == compareButton) {
            saveInputToDatabase();
        }

        // Update the caffeine label based on totalCaffeine
        caffeineLabel.setText("Total Caffeine: " + totalCaffeine + "mg");

        // Check if totalCaffeine exceeds the maximum limit and display result accordingly
        if (totalCaffeine > MAX_CAFFEINE) {
            resultLabel.setText("<html><font color='red'>You should minimize caffeine intake! It disturbs your sleep pattern.</font></html>");
        } else {
            resultLabel.setText("<html><font color='green'>Great work! You have consumed a healthy amount of caffeine.</font></html>");
        }

        // Retrieve and update the totalScreenTime from the screenTimeField
        if (screenTimeField != null) {
            String text = screenTimeField.getText();
            if (!text.isEmpty()) {
                totalScreenTime = Integer.parseInt(text);
            }
        }

        // Update the screen time label based on totalScreenTime
        screenLabel.setText("Total Screen Time: " + totalScreenTime + " hours");

        // Check if totalScreenTime exceeds the maximum limit and display result accordingly
        if (totalScreenTime > MAX_SCREEN_TIME) {
            resultLabel2.setText("<html><font color='red'>You should minimize screen time! The blue light from your devices disturbs your sleep.</font></html>");
        } else {
            resultLabel2.setText("<html><font color='green'>Great work! Your screen time is within the recommended limit.</font></html>");
        }
    }

    // Save the input to the database
    private void saveInputToDatabase() {
        try {
            // Prepare the SQL statement
            String sql = "INSERT INTO sleep_data (total_caffeine, total_screen_time) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, totalCaffeine);
            statement.setInt(2, totalScreenTime);

            // Execute the statement
            statement.executeUpdate();

            // Close the statement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Component getCompareButton() {
        return compareButton;
    }
}

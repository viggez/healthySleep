package app;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


class SleepEnvironmentAnalysis extends JPanel implements ActionListener {
    private JTextField screenTimeField;
    private JButton oneCoffee = new JButton(new ImageIcon("fotor_2023-5-3_20_37_29.png"));
    private JButton twoCoffees = new JButton(new ImageIcon("fotor_2023-5-3_20_37_29.png"));
    private JButton threeCoffees = new JButton(new ImageIcon("fotor_2023-5-3_20_37_29.png"));
    private JButton fourCoffees = new JButton(new ImageIcon("fotor_2023-5-3_20_37_29.png"));
    private JButton fiveCoffees = new JButton(new ImageIcon("fotor_2023-5-3_20_37_29.png"));
    private JLabel caffeineLabel;
    private JButton compareButton;
    private int totalCaffeine = 0;
    private int totalScreenTime = 0;
    private JLabel resultLabel;
    private JLabel resultLabel2;
    private final int MAX_SCREEN_TIME = 8;
    private final int MAX_CAFFEINE = 400;


    public SleepEnvironmentAnalysis() {
        ButtonGroup options = new ButtonGroup();
        options.add(this.oneCoffee);
        options.add(this.twoCoffees);
        options.add(this.threeCoffees);
        options.add(this.fourCoffees);
        options.add(this.fiveCoffees);
        this.setLayout(new GridLayout(1, 5));
        this.add(this.oneCoffee);
        this.add(this.twoCoffees);
        this.add(this.threeCoffees);
        this.add(this.fourCoffees);
        this.add(this.fiveCoffees);
        this.oneCoffee.addActionListener(this);
        this.twoCoffees.addActionListener(this);
        this.threeCoffees.addActionListener(this);
        this.fourCoffees.addActionListener(this);
        this.fiveCoffees.addActionListener(this);
        this.caffeineLabel = new JLabel("Total Caffeine: " + this.totalCaffeine + "mg");
        this.add(this.caffeineLabel);
        this.screenTimeField = new JTextField(2);
        this.setPreferredSize(new Dimension(650, 250));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = 2;
        JLabel screenTimeLabel = new JLabel("Screen Time Today (hours): ");
        this.screenTimeField = new JTextField();
        this.add(screenTimeLabel);
        this.add(this.screenTimeField);
        this.compareButton = new JButton("Compare with recommended amount!");
        this.compareButton.addActionListener(this);
        this.add(this.compareButton);
        this.resultLabel = new JLabel();
        this.add(this.resultLabel);
        this.resultLabel2 = new JLabel();
        this.add(this.resultLabel2);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.oneCoffee) {
            this.totalCaffeine += 50;
        } else if (e.getSource() == this.twoCoffees) {
            this.totalCaffeine += 150;
        } else if (e.getSource() == this.threeCoffees) {
            this.totalCaffeine += 200;
        } else if (e.getSource() == this.fourCoffees) {
            this.totalCaffeine += 250;
        } else if (e.getSource() == this.fiveCoffees) {
            this.totalCaffeine += 300;
        }


        this.caffeineLabel.setText("Total Caffeine: " + this.totalCaffeine + "mg");
        if (this.totalCaffeine > MAX_SCREEN_TIME) {
            this.resultLabel.setText("<html><font color='red'>You should drink less caffeine during the day! It disturbs your sleep pattern. </font></html>");
        } else if () {
            this.resultLabel.setText("<html><font color='green'>You have consumed a healthy amount of caffeine, try not to drink any caffeine around 5-7 hours before bedtime! !</font></html>");
        }


        if (this.totalScreenTime > MAX_CAFFEINE) {
            this.resultLabel2.setText("<html><font color='red'>You should put your phone down! The blue light from your devices disturbs your sleep! . </font></html>");
        } else if (totalScreenTime <= MAX_SCREEN_TIME) {
            this.resultLabel2.setText("<html><font color='green'>Great work! Now try to not use any devices one hour before bed! . </font></html>");
        }


    }
}


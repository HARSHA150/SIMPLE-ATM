import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM_INTERFACE {
    private static int balance = 100000;
    private static final int pin = 1234;
    private static int AddAmount = 0;
    private static int TakeAmount = 0;
    private static String name;
    private static DefaultTableModel model;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        // Panel for PIN input
        JPanel pinPanel = new JPanel(new BorderLayout());
        JLabel pinLabel = new JLabel("Enter your pin number: ");
        JTextField pinField = new JTextField();
        JButton pinButton = new JButton("Submit");
        pinPanel.add(pinLabel, BorderLayout.WEST);
        pinPanel.add(pinField, BorderLayout.CENTER);
        pinPanel.add(pinButton, BorderLayout.EAST);
        
        // Panel for name input
        JPanel namePanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel("Enter your name: ");
        JTextField nameField = new JTextField();
        namePanel.add(nameLabel, BorderLayout.WEST);
        namePanel.add(nameField, BorderLayout.CENTER);
        
        // Table for transaction history
        model = new DefaultTableModel();
        model.addColumn("Transaction");
        model.addColumn("Amount");
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Panel for transaction options
        JPanel optionsPanel = new JPanel(new GridLayout(5, 1));
        JButton balanceButton = new JButton("Check Balance");
        JButton addButton = new JButton("Add Amount");
        JButton takeButton = new JButton("Take Amount");
        JButton receiptButton = new JButton("Receipt");
        JButton exitButton = new JButton("Exit");
        optionsPanel.add(balanceButton);
        optionsPanel.add(addButton);
        optionsPanel.add(takeButton);
        optionsPanel.add(receiptButton);
        optionsPanel.add(exitButton);
        
        // Panel for main content
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(namePanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(optionsPanel, BorderLayout.SOUTH);
        
        frame.setLayout(new BorderLayout());
        frame.add(pinPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
        
        // Disable main panel initially
        mainPanel.setVisible(false);
        
        // Action listener for pin button
        pinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int enteredPin = Integer.parseInt(pinField.getText());
                if (enteredPin == pin) {
                    name = nameField.getText();
                    JOptionPane.showMessageDialog(frame, "Welcome " + name);
                    mainPanel.setVisible(true);
                    pinPanel.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect PIN. Try again.");
                }
            }
        });

        // Action listener for balance button
        balanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Your current balance is " + balance);
            }
        });

        // Action listener for add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = JOptionPane.showInputDialog("How much money you want to add to your account:");
                AddAmount = Integer.parseInt(amount);
                balance += AddAmount;
                model.addRow(new Object[]{"Credit", AddAmount});
                JOptionPane.showMessageDialog(frame, "Your amount is credited.");
            }
        });

        // Action listener for take button
        takeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amount = JOptionPane.showInputDialog("How much money you want to take:");
                TakeAmount = Integer.parseInt(amount);
                if (TakeAmount <= balance) {
                    balance -= TakeAmount;
                    model.addRow(new Object[]{"Debit", TakeAmount});
                    JOptionPane.showMessageDialog(frame, "Your amount is debited.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Insufficient balance.");
                }
            }
        });

        // Action listener for receipt button
        receiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Printing receipt...");
                // Additional receipt functionality can be added here
            }
        });

        // Action listener for exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }
}

package com.mycompany.digital_contact_list;

import javax.swing.*;
import java.awt.*;

public class PhoneBookGUI extends JFrame {

    private BSTPhoneBook phonebook = new BSTPhoneBook();

    private JTextField nameField = new JTextField(15);
    private JTextField phoneField = new JTextField(15);
    private JTextField newNameField = new JTextField(15);

    private JTextArea outputArea = new JTextArea(14, 40);

    public PhoneBookGUI() {

        setTitle("Digital Contact List - BST");
        setSize(750, 650);
        setMinimumSize(new Dimension(700, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Digital Contact List System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Contact Information"));

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Phone:"));
        inputPanel.add(phoneField);

        inputPanel.add(new JLabel("New Name:"));
        inputPanel.add(newNameField);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Operations"));
        buttonPanel.setPreferredSize(new Dimension(600, 140));

        JButton addBtn = new JButton("Add");
        JButton removeBtn = new JButton("Remove");
        JButton searchBtn = new JButton("Search");
        JButton updatePhoneBtn = new JButton("Update Phone");
        JButton updateNameBtn = new JButton("Update Name");
        JButton displayBtn = new JButton("Display All");
        JButton sizeBtn = new JButton("Size");
        JButton isolateBtn = new JButton("Isolate 0100");
        JButton clearBtn = new JButton("Clear Fields");

        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(updatePhoneBtn);
        buttonPanel.add(updateNameBtn);
        buttonPanel.add(displayBtn);
        buttonPanel.add(sizeBtn);
        buttonPanel.add(isolateBtn);
        buttonPanel.add(clearBtn);

        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));
        scrollPane.setPreferredSize(new Dimension(600, 230));

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        addBtn.addActionListener(e -> addContact());
        removeBtn.addActionListener(e -> removeContact());
        searchBtn.addActionListener(e -> searchContact());
        updatePhoneBtn.addActionListener(e -> updatePhone());
        updateNameBtn.addActionListener(e -> updateName());
        displayBtn.addActionListener(e -> displayAll());
        sizeBtn.addActionListener(e -> showSize());
        isolateBtn.addActionListener(e -> isolate0100());
        clearBtn.addActionListener(e -> clearFields());

        setVisible(true);
    }

    private void addContact() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            outputArea.setText("Error: Name and phone cannot be blank.");
            return;
        }

        if (phonebook.exists(name)) {
            outputArea.setText("Error: Contact already exists.");
            return;
        }

        phonebook.insert(new Person(name, phone));
        outputArea.setText("Contact added successfully:\n" + name + "  " + phone);
    }

    private void removeContact() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            outputArea.setText("Error: Name cannot be blank.");
            return;
        }

        if (!phonebook.exists(name)) {
            outputArea.setText("Contact not found.");
            return;
        }

        phonebook.remove(name);
        outputArea.setText("Contact removed successfully.");
    }

    private void searchContact() {
        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            outputArea.setText("Error: Name cannot be blank.");
            return;
        }

        Person result = phonebook.searchPerson(name);

        if (result != null) {
            outputArea.setText("Contact Found:\n"
                    + result.getName() + "  " + result.getPhone());
        } else {
            outputArea.setText("Contact not found.");
        }
    }

    private void updatePhone() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty()) {
            outputArea.setText("Error: Name cannot be blank.");
            return;
        }

        if (!phonebook.exists(name)) {
            outputArea.setText("Contact not found.");
            return;
        }

        if (phone.isEmpty()) {
            outputArea.setText("Error: New phone cannot be blank.");
            return;
        }

        phonebook.updateExistingPhone(name, phone);
        outputArea.setText("Contact phone updated successfully:\n"
                + name + "  " + phone);
    }

    private void updateName() {
        String oldName = nameField.getText().trim();
        String newName = newNameField.getText().trim();

        if (oldName.isEmpty()) {
            outputArea.setText("Error: Current name cannot be blank.");
            return;
        }

        if (!phonebook.exists(oldName)) {
            outputArea.setText("Contact not found.");
            return;
        }

        if (newName.isEmpty()) {
            outputArea.setText("Error: New name cannot be blank.");
            return;
        }

        if (phonebook.exists(newName)) {
            outputArea.setText("Error: New contact name already exists.");
            return;
        }

        phonebook.updateExistingName(oldName, newName);
        outputArea.setText("Contact name updated successfully:\n"
                + oldName + " → " + newName);
    }

    private void displayAll() {
        outputArea.setText(phonebook.getAllContacts());
    }

    private void showSize() {
        outputArea.setText("Number of contacts = " + phonebook.getSize());
    }

    private void isolate0100() {
        outputArea.setText(phonebook.getContactsStarting0100());
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        newNameField.setText("");
        outputArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PhoneBookGUI());
    }
}
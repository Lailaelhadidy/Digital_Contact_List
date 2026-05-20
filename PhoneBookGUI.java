package com.mycompany.digital_contact_list;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PhoneBookGUI extends JFrame {

    private BSTPhoneBook phonebook = new BSTPhoneBook();

    private JComboBox<String> operationBox;

    private JLabel nameLabel = new JLabel("Name:");
    private JLabel phoneLabel = new JLabel("Phone:");
    private JLabel newNameLabel = new JLabel("New Name:");

    private JTextField nameField = new JTextField(18);
    private JTextField phoneField = new JTextField(18);
    private JTextField newNameField = new JTextField(18);

    private DefaultTableModel tableModel;
    private JTable contactsTable;

    private JLabel statusLabel = new JLabel("Choose an operation to start.");

    private final Color pink = new Color(255, 182, 193);
    private final Color lightPink = new Color(255, 240, 245);
    private final Color darkPink = new Color(219, 112, 147);

    public PhoneBookGUI() {

        setTitle("Digital Contact List - BST");
        setSize(850, 650);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout(12, 12));

        JLabel title = new JLabel("🌸 Digital Contact List System", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(12, 12));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        centerPanel.add(createControlPanel(), BorderLayout.NORTH);
        centerPanel.add(createTablePanel(), BorderLayout.CENTER);
        centerPanel.add(createStatusPanel(), BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        updateVisibleFields();

        setVisible(true);
    }

    private JPanel createControlPanel() {

        JPanel controlPanel = new JPanel(new BorderLayout(10, 10));
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(pink, 2),
                "Operations",
                0,
                0,
                new Font("Segoe UI", Font.BOLD, 15),
                darkPink
        ));

        String[] operations = {
                "➕ Add Contact",
                "🗑 Remove Contact",
                "🔍 Search Contact",
                "✏ Update Phone",
                "📝 Update Name",
                "📋 Display All",
                "🔢 Size",
                "📞 Isolate 0100"
        };

        operationBox = new JComboBox<>(operations);
        operationBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        operationBox.setBackground(Color.WHITE);
        operationBox.addActionListener(e -> updateVisibleFields());

        JPanel operationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        operationPanel.setBackground(Color.WHITE);
        operationPanel.add(new JLabel("Choose Operation:"));
        operationPanel.add(operationBox);

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBackground(Color.WHITE);

        styleLabel(nameLabel);
        styleLabel(phoneLabel);
        styleLabel(newNameLabel);

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);

        inputPanel.add(newNameLabel);
        inputPanel.add(newNameField);

        JButton executeBtn = createButton("✅ Execute Operation");
        JButton clearBtn = createButton("🧹 Clear");

        executeBtn.addActionListener(e -> executeOperation());
        clearBtn.addActionListener(e -> clearFields());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(executeBtn);
        buttonPanel.add(clearBtn);

        controlPanel.add(operationPanel, BorderLayout.NORTH);
        controlPanel.add(inputPanel, BorderLayout.CENTER);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);

        return controlPanel;
    }

    private JPanel createTablePanel() {

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(pink, 2),
                "Contacts Table",
                0,
                0,
                new Font("Segoe UI", Font.BOLD, 15),
                darkPink
        ));

        tableModel = new DefaultTableModel(new String[]{"Name", "Phone Number"}, 0);
        contactsTable = new JTable(tableModel);

        contactsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contactsTable.setRowHeight(28);
        contactsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        contactsTable.getTableHeader().setBackground(pink);
        contactsTable.getTableHeader().setForeground(Color.BLACK);
        contactsTable.setBackground(Color.WHITE);
        contactsTable.setForeground(Color.BLACK);
        contactsTable.setGridColor(lightPink);

        JScrollPane scrollPane = new JScrollPane(contactsTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private JPanel createStatusPanel() {

        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(lightPink);
        statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        statusLabel.setForeground(Color.BLACK);

        statusPanel.add(statusLabel, BorderLayout.CENTER);

        return statusPanel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(pink);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        return button;
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(Color.BLACK);
    }

    private void updateVisibleFields() {

        String operation = (String) operationBox.getSelectedItem();

        nameLabel.setVisible(false);
        nameField.setVisible(false);

        phoneLabel.setVisible(false);
        phoneField.setVisible(false);

        newNameLabel.setVisible(false);
        newNameField.setVisible(false);

        if (operation.contains("Add")) {
            nameLabel.setText("Name:");
            phoneLabel.setText("Phone:");
            nameLabel.setVisible(true);
            nameField.setVisible(true);
            phoneLabel.setVisible(true);
            phoneField.setVisible(true);
        }

        else if (operation.contains("Remove") || operation.contains("Search")) {
            nameLabel.setText("Name:");
            nameLabel.setVisible(true);
            nameField.setVisible(true);
        }

        else if (operation.contains("Update Phone")) {
            nameLabel.setText("Current Name:");
            phoneLabel.setText("New Phone:");
            nameLabel.setVisible(true);
            nameField.setVisible(true);
            phoneLabel.setVisible(true);
            phoneField.setVisible(true);
        }

        else if (operation.contains("Update Name")) {
            nameLabel.setText("Current Name:");
            newNameLabel.setText("New Name:");
            nameLabel.setVisible(true);
            nameField.setVisible(true);
            newNameLabel.setVisible(true);
            newNameField.setVisible(true);
        }

        revalidate();
        repaint();
    }

    private void executeOperation() {

        String operation = (String) operationBox.getSelectedItem();

        if (operation.contains("Add")) {
            addContact();
        } else if (operation.contains("Remove")) {
            removeContact();
        } else if (operation.contains("Search")) {
            searchContact();
        } else if (operation.contains("Update Phone")) {
            updatePhone();
        } else if (operation.contains("Update Name")) {
            updateName();
        } else if (operation.contains("Display All")) {
            displayAllContacts();
        } else if (operation.contains("Size")) {
            showSize();
        } else if (operation.contains("Isolate")) {
            isolate0100();
        }
    }

    private void addContact() {

        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            setStatus("❌ Name and phone cannot be blank.");
            return;
        }

        if (phonebook.exists(name)) {
            setStatus("⚠ Contact already exists.");
            return;
        }

        phonebook.insert(new Person(name, phone));
        setStatus("✅ Contact added successfully.");
        displayAllContacts();
    }

    private void removeContact() {

        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            setStatus("❌ Name cannot be blank.");
            return;
        }

        if (!phonebook.exists(name)) {
            setStatus("⚠ Contact not found.");
            return;
        }

        phonebook.remove(name);
        setStatus("✅ Contact removed successfully.");
        displayAllContacts();
    }

    private void searchContact() {

        String name = nameField.getText().trim();

        if (name.isEmpty()) {
            setStatus("❌ Name cannot be blank.");
            return;
        }

        Person person = phonebook.searchPerson(name);

        if (person == null) {
            clearTable();
            setStatus("⚠ Contact not found.");
            return;
        }

        clearTable();
        tableModel.addRow(new Object[]{person.getName(), person.getPhone()});
        setStatus("✅ Contact found.");
    }

    private void updatePhone() {

        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();

        if (name.isEmpty()) {
            setStatus("❌ Current name cannot be blank.");
            return;
        }

        if (!phonebook.exists(name)) {
            setStatus("⚠ Contact not found.");
            return;
        }

        if (phone.isEmpty()) {
            setStatus("❌ New phone cannot be blank.");
            return;
        }

        phonebook.updateExistingPhone(name, phone);
        setStatus("✅ Contact phone updated successfully.");
        displayAllContacts();
    }

    private void updateName() {

        String oldName = nameField.getText().trim();
        String newName = newNameField.getText().trim();

        if (oldName.isEmpty()) {
            setStatus("❌ Current name cannot be blank.");
            return;
        }

        if (!phonebook.exists(oldName)) {
            setStatus("⚠ Contact not found.");
            return;
        }

        if (newName.isEmpty()) {
            setStatus("❌ New name cannot be blank.");
            return;
        }

        if (phonebook.exists(newName)) {
            setStatus("⚠ New contact name already exists.");
            return;
        }

        phonebook.updateExistingName(oldName, newName);
        setStatus("✅ Contact name updated successfully.");
        displayAllContacts();
    }

    private void displayAllContacts() {

        String[][] contacts = phonebook.getAllContactsArray();

        clearTable();

        if (contacts.length == 0) {
            setStatus("⚠ No contacts were found to be displayed.");
            return;
        }

        for (String[] contact : contacts) {
            tableModel.addRow(new Object[]{contact[0], contact[1]});
        }

        setStatus("✅ Contacts displayed successfully.");
    }

    private void isolate0100() {

        String[][] contacts = phonebook.getContactsStarting0100Array();

        clearTable();

        if (phonebook.getSize() == 0) {
            setStatus("⚠ Phonebook is empty.");
            return;
        }

        if (contacts.length == 0) {
            setStatus("⚠ No contacts starting with 0100.");
            return;
        }

        for (String[] contact : contacts) {
            tableModel.addRow(new Object[]{contact[0], contact[1]});
        }

        setStatus("✅ Contacts starting with 0100 displayed successfully.");
    }

    private void showSize() {
        setStatus("🔢 Number of contacts = " + phonebook.getSize());
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        newNameField.setText("");
        clearTable();
        setStatus("Fields and table cleared.");
    }

    private void clearTable() {
        tableModel.setRowCount(0);
    }

    private void setStatus(String message) {
        statusLabel.setText(message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PhoneBookGUI());
    }
}

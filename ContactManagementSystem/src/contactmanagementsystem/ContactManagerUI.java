/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package contactmanagementsystem;

/**
 *
 * @author Suraj Senapati
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ContactManagerUI extends JFrame {
    private ContactManager contactManager;
    private JTextField txtName, txtPhone, txtEmail;
    private JTable table;
    private DefaultTableModel tableModel;

    public ContactManagerUI() {
        contactManager = new ContactManager();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Contact Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(20, 20, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(120, 20, 150, 25);
        add(txtName);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(20, 60, 100, 25);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(120, 60, 150, 25);
        add(txtPhone);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 100, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 100, 150, 25);
        add(txtEmail);

        JButton btnAdd = new JButton("Add Contact");
        btnAdd.setBounds(20, 140, 150, 25);
        add(btnAdd);

        JButton btnEdit = new JButton("Edit Contact");
        btnEdit.setBounds(180, 140, 150, 25);
        add(btnEdit);

        JButton btnDelete = new JButton("Delete Contact");
        btnDelete.setBounds(340, 140, 150, 25);
        add(btnDelete);

        JButton btnSave = new JButton("Save to File");
        btnSave.setBounds(20, 180, 150, 25);
        add(btnSave);

        JButton btnLoad = new JButton("Load from File");
        btnLoad.setBounds(180, 180, 150, 25);
        add(btnLoad);

        tableModel = new DefaultTableModel(new Object[]{"Name", "Phone", "Email"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 220, 540, 120);
        add(scrollPane);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String phone = txtPhone.getText();
                String email = txtEmail.getText();
                Contact contact = new Contact(name, phone, email);
                contactManager.addContact(contact);
                updateTable();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String name = txtName.getText();
                    String phone = txtPhone.getText();
                    String email = txtEmail.getText();
                    Contact contact = new Contact(name, phone, email);
                    contactManager.editContact(selectedRow, contact);
                    updateTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Select a contact to edit");
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    contactManager.deleteContact(selectedRow);
                    updateTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Select a contact to delete");
                }
            }
        });

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contactManager.saveContactsToFile("contacts.dat");
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contactManager.loadContactsFromFile("contacts.dat");
                updateTable();
            }
        });
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Contact contact : contactManager.getContacts()) {
            tableModel.addRow(new Object[]{contact.getName(), contact.getPhoneNumber(), contact.getEmail()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ContactManagerUI().setVisible(true);
            }
        });
    }
}


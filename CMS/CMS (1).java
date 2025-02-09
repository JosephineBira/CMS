import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

 class CMS {

    private ArrayList<Contact> contacts;
    private JFrame mainframe;
    private JList<String> contactList;
    private DefaultListModel<String> listModel;
    private JButton addNewContactButton;
    private JButton viewDetailsButton;
    private JButton deleteContactButton;
    private JButton editContactButton;
    private JLabel nameLabel;
    private JLabel phoneNumberLabel;
    private JLabel emailLabel;
    private JButton backButton;
    private JTextField nameField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JButton saveContactButton;
    private JButton cancelButton;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    public JPanel OrderPanel;




    public CMS() {
        JFrame mainframe = new JFrame("Contact Manager");

        contacts = new ArrayList<>();


        mainframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setTitle("Contact Management System");

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        deleteContactButton = new JButton("Delete Contact");
        deleteContactButton.setBackground(new Color(135,206,235));
        deleteContactButton.setFont(new Font("Arial", Font.BOLD, 30));
        deleteContactButton.setFocusPainted(false);
        editContactButton = new JButton("Edit Contact");
        editContactButton.setBackground(new Color(135,206,235));
        editContactButton.setFont(new Font("Arial", Font.BOLD, 30));
        editContactButton.setFocusPainted(false);


        createContactListView();
        createContactDetailsView();
        createContactCreationForm();
        updateContactList();

        mainframe.add(cardPanel);
        mainframe.setVisible(true);

    }

    private void createContactListView() {

        JPanel contactListPanel = new JPanel();
        contactListPanel.setLayout(new BorderLayout());


        listModel = new DefaultListModel<>();
        contactList = new JList<>(listModel);
        contactList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JScrollPane scrollPane = new JScrollPane(contactList);
        contactListPanel.add(scrollPane, BorderLayout.CENTER);


        addNewContactButton = new JButton("Add New Contact");
        addNewContactButton.setBackground(new Color(200,256,235));
        addNewContactButton.setFont(new Font("Arial", Font.BOLD, 30));
        addNewContactButton.setFocusPainted(false);
        addNewContactButton.addActionListener(new AddNewContactButtonListener());
        viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setBackground(new Color(135,206,235));
        viewDetailsButton.setFont(new Font("Arial", Font.BOLD, 30));
        viewDetailsButton.setFocusPainted(false);
        viewDetailsButton.addActionListener(new ViewDetailsButtonListener());
        deleteContactButton.addActionListener(new DeleteContactButtonListener());
        editContactButton.addActionListener(new EditContactButtonListener());


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 30));
        buttonPanel.add(addNewContactButton);
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(deleteContactButton);
        buttonPanel.add(editContactButton);
        contactListPanel.add(buttonPanel, BorderLayout.SOUTH);


        cardPanel.add(contactListPanel, "Contact List");
    }

    private void createContactDetailsView() {

        JPanel contactDetailsPanel = new JPanel();
        contactDetailsPanel.setLayout(new BorderLayout());


        nameLabel = new JLabel();
        phoneNumberLabel = new JLabel();
        emailLabel = new JLabel();


        JPanel detailsPanel = new JPanel();

        detailsPanel.setLayout(new GridLayout(3, 2, 0, 80));
        detailsPanel.add(new JLabel("Name:"));
        detailsPanel.add(nameLabel);
        detailsPanel.add(new JLabel("Phone Number:"));
        detailsPanel.add(phoneNumberLabel);
        detailsPanel.add(new JLabel("Email:"));
        detailsPanel.add(emailLabel);
        contactDetailsPanel.add(detailsPanel, BorderLayout.CENTER);

        backButton = new JButton("Back to List");
        backButton.setFont(new Font("Georgia", Font.BOLD, 35));
        backButton.setFocusPainted(false);
        backButton.addActionListener(new BackButtonListener());

        contactDetailsPanel.add(backButton, BorderLayout.SOUTH);

        cardPanel.add(contactDetailsPanel, "Contact Details");
    }

    private void createContactCreationForm() {
        JPanel contactCreationFormPanel = new JPanel();
        contactCreationFormPanel.setLayout(new BorderLayout());

        nameField = new JTextField();
        phoneNumberField = new JTextField();
        emailField = new JTextField();

        JPanel OrderPanel = new JPanel(new GridBagLayout());
        OrderPanel.setSize(new Dimension(200,200));
        OrderPanel.setLayout(new GridLayout(3, 2));
        OrderPanel.add(new JLabel("Name:"));
        OrderPanel.add(nameField);
        OrderPanel.add(new JLabel("Phone Number:"));
        OrderPanel.add(phoneNumberField);
        OrderPanel.add(new JLabel("Email:"));
        OrderPanel.add(emailField);



        contactCreationFormPanel.add(OrderPanel,BorderLayout.CENTER);

        saveContactButton = new JButton("Save Contact");
        saveContactButton.setFont(new Font("Arial", Font.BOLD, 30));
        saveContactButton.setFocusPainted(false);
        saveContactButton.addActionListener(new SaveContactButtonListener());

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 30));
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(new CancelButtonListener());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveContactButton);
        buttonPanel.add(cancelButton);
        contactCreationFormPanel.add(buttonPanel, BorderLayout.SOUTH);

        cardPanel.add(contactCreationFormPanel, "Contact Creation Form");
    }

    private void updateContactList() {
        listModel.clear();

        for (Contact contact : contacts) {
            listModel.addElement(contact.getName());
        }
    }

    private class AddNewContactButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            nameField.setText("");
            phoneNumberField.setText("");
            emailField.setText("");
            cardLayout.show(cardPanel, "Contact Creation Form");
        }
    }

    private class ViewDetailsButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = contactList.getSelectedIndex();
            if (selectedIndex != -1) {
                Contact selectedContact = contacts.get(selectedIndex);

                nameLabel.setText(selectedContact.getName());
                phoneNumberLabel.setText(selectedContact.getPhoneNumber());
                emailLabel.setText(selectedContact.getEmail());

                cardLayout.show(cardPanel, "Contact Details");
            }
        }
    }

    private class BackButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "Contact List");
        }
    }

    private class EditContactButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = contactList.getSelectedIndex();
            if (selectedIndex != -1) {
                Contact selectedContact = contacts.get(selectedIndex);
                nameField.setText(selectedContact.getName());
                phoneNumberField.setText(selectedContact.getPhoneNumber());
                emailField.setText(selectedContact.getEmail());
                cardLayout.show(cardPanel, "Contact Creation Form");
            }
        }
    }

    private class DeleteContactButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = contactList.getSelectedIndex();
            if (selectedIndex != -1) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this contact?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    contacts.remove(selectedIndex);
                    updateContactList();
                }
            }
        }
    }

    private class SaveContactButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String phoneNumber = phoneNumberField.getText();
            String email = emailField.getText();

            if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (contactList.getSelectedIndex() != -1) {
                Contact existingContact = contacts.get(contactList.getSelectedIndex());
                existingContact.setName(name);
                existingContact.setPhoneNumber(phoneNumber);
                existingContact.setEmail(email);
            } else {
                Contact newContact = new Contact(name, phoneNumber, email);
                contacts.add(newContact);
            }

            updateContactList();

            cardLayout.show(cardPanel, "Contact List");
        }
    }

    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "Contact List");
        }
    }
}

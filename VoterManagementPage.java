import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class VoterManagementPage extends JFrame {
    private JTextField nameField, rollField, birthdateField;
    private JButton addButton, backButton;
    private JTable voterTable;
    private DefaultTableModel tableModel;

    public VoterManagementPage(JFrame adminPanel) {
        setTitle("Voter Management");
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        JLabel nameLabel = new JLabel("Voter Name:");
        JLabel rollLabel = new JLabel("Roll Number:");
        JLabel birthdateLabel = new JLabel("Birthdate (DD-MM-YYYY):");

        nameField = new JTextField();
        rollField = new JTextField();
        birthdateField = new JTextField();
        addButton = new JButton("Add Voter");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(rollLabel);
        formPanel.add(rollField);
        formPanel.add(birthdateLabel);
        formPanel.add(birthdateField);
        formPanel.add(new JLabel());
        formPanel.add(addButton);

        tableModel = new DefaultTableModel(new String[]{"Voter Name", "Roll Number", "Birthdate", "Password"}, 0);
        voterTable = new JTable(tableModel);

        addButton.addActionListener(e -> addVoter());

        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.setVisible(false);
            adminPanel.setVisible(true);
        });

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(voterTable), BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void addVoter() {
        String name = nameField.getText();
        String roll = rollField.getText();
        String birthdate = birthdateField.getText();

        try {
            String password = birthdate.replaceAll("-", "");
            if (password.length() != 8) throw new Exception();

            tableModel.addRow(new Object[]{name, roll, birthdate, password});
            VoterDatabase.addVoter(new Voter(name, roll, birthdate, password));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid birthdate format. Use DD-MM-YYYY.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

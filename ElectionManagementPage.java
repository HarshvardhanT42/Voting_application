import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class ElectionManagementPage extends JFrame {
    private JTextField candidateNameField, candidateRollField;
    private JButton addButton, backButton, startElectionButton, endElectionButton;
    private JTable candidateTable;
    private DefaultTableModel tableModel;
    private boolean isElectionActive = false;

    public ElectionManagementPage(JFrame adminPanel) {
        setTitle("Election Management");
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        JLabel nameLabel = new JLabel("Candidate Name:");
        JLabel rollLabel = new JLabel("Roll Number:");
        candidateNameField = new JTextField();
        candidateRollField = new JTextField();
        addButton = new JButton("Add Candidate");

        formPanel.add(nameLabel);
        formPanel.add(candidateNameField);
        formPanel.add(rollLabel);
        formPanel.add(candidateRollField);
        formPanel.add(new JLabel());
        formPanel.add(addButton);

        tableModel = new DefaultTableModel(new String[]{"ID", "Candidate Name", "Roll Number", "Vote Count"}, 0);
        candidateTable = new JTable(tableModel);

        // Populate table with existing candidates
        for (Candidate candidate : CandidateDatabase.getAllCandidates()) {
            tableModel.addRow(new Object[]{candidate.getId(), candidate.getName(), candidate.getRollNo(), candidate.getVoteCount()});
        }

        addButton.addActionListener(e -> addCandidate());

        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            this.setVisible(false);
            adminPanel.setVisible(true);
        });

        startElectionButton = new JButton("Start Election");
        startElectionButton.addActionListener(e -> startElection());

        endElectionButton = new JButton("End Election");
        endElectionButton.addActionListener(e -> endElection());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startElectionButton);
        buttonPanel.add(endElectionButton);
        buttonPanel.add(backButton);

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(candidateTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void addCandidate() {
        if (isElectionActive) {
            JOptionPane.showMessageDialog(this, "Cannot add candidates while the election is active.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String name = candidateNameField.getText();
        String rollNo = candidateRollField.getText();

        if (name.isEmpty() || rollNo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = new Random().nextInt(90) + 10; // Generate a random 2-digit ID
        while (CandidateDatabase.getCandidateById(id) != null) {
            id = new Random().nextInt(90) + 10; // Ensure unique ID
        }

        Candidate candidate = new Candidate(name, rollNo, id);
        CandidateDatabase.addCandidate(candidate);

        tableModel.addRow(new Object[]{candidate.getId(), candidate.getName(), candidate.getRollNo(), candidate.getVoteCount()});
        candidateNameField.setText("");
        candidateRollField.setText("");
    }

    private void startElection() {
        if (isElectionActive) {
            JOptionPane.showMessageDialog(this, "Election is already active.", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            isElectionActive = true;
            JOptionPane.showMessageDialog(this, "Election has started!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void endElection() {
        if (!isElectionActive) {
            JOptionPane.showMessageDialog(this, "No election is currently active.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        isElectionActive = false;

        Candidate winner = null;
        int maxVotes = -1;
        for (Candidate candidate : CandidateDatabase.getAllCandidates()) {
            if (candidate.getVoteCount() > maxVotes) {
                maxVotes = candidate.getVoteCount();
                winner = candidate;
            }
        }

        String message = (winner != null)
            ? "Election has ended! Winner: " + winner.getName() + " with " + maxVotes + " votes."
            : "Election has ended! No votes were cast.";

        JOptionPane.showMessageDialog(this, message, "Election Result", JOptionPane.INFORMATION_MESSAGE);
    }
}

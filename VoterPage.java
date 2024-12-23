import java.awt.*;
import javax.swing.*;

class VoterPage extends JFrame {
    private JTextField candidateIdField;
    private JButton checkButton, voteButton;
    private JLabel candidateDetailsLabel;
    private Candidate selectedCandidate;

    public VoterPage(Voter voter) {
        setTitle("Voter Panel");

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome, " + voter.getName() + " (" + voter.getRollNumber() + ")", JLabel.CENTER);
        add(welcomeLabel, BorderLayout.NORTH);

        // Candidate list
        JPanel candidateListPanel = new JPanel(new GridLayout(0, 1));
        for (Candidate candidate : CandidateDatabase.getAllCandidates()) {
            candidateListPanel.add(new JLabel("ID: " + candidate.getId() + " | Name: " + candidate.getName() + " | Roll No: " + candidate.getRollNo()));
        }
        add(new JScrollPane(candidateListPanel), BorderLayout.CENTER);

        // Voting section
        JPanel votePanel = new JPanel(new GridLayout(3, 2));
        votePanel.add(new JLabel("Enter Candidate ID:"));
        candidateIdField = new JTextField();
        votePanel.add(candidateIdField);

        checkButton = new JButton("Check");
        votePanel.add(checkButton);

        candidateDetailsLabel = new JLabel("Candidate details will appear here", JLabel.CENTER);
        votePanel.add(candidateDetailsLabel);

        voteButton = new JButton("Vote");
        votePanel.add(voteButton);

        add(votePanel, BorderLayout.SOUTH);

        // Action Listeners
        checkButton.addActionListener(e -> checkCandidate());
        voteButton.addActionListener(e -> castVote(voter));

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void checkCandidate() {
        String candidateIdText = candidateIdField.getText();
        try {
            int candidateId = Integer.parseInt(candidateIdText);
            selectedCandidate = CandidateDatabase.getCandidateById(candidateId);
            if (selectedCandidate != null) {
                candidateDetailsLabel.setText("Name: " + selectedCandidate.getName() + " | Roll No: " + selectedCandidate.getRollNo());
            } else {
                candidateDetailsLabel.setText("Candidate not found.");
            }
        } catch (NumberFormatException e) {
            candidateDetailsLabel.setText("Invalid ID format.");
        }
    }

    private void castVote(Voter voter) {
        if (selectedCandidate != null) {
            selectedCandidate.incrementVoteCount();
            JOptionPane.showMessageDialog(this, "Vote cast successfully!");

            // Redirect to login page after voting
            this.setVisible(false);
            new LoginPage().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please check a candidate before voting.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

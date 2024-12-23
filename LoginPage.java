import java.awt.*;
import java.util.HashSet;
import javax.swing.*;

public class LoginPage extends JFrame {
    private JComboBox<String> userTypeDropdown;
    private JTextField userIdField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;

    // Store already logged-in voters
    private static HashSet<String> loggedInVoters = new HashSet<>();

    public LoginPage() {
        setTitle("Login Page");
        setLayout(new GridLayout(5, 2));

        JLabel userTypeLabel = new JLabel("Select User Type:");
        JLabel userIdLabel = new JLabel("User ID:");
        JLabel passwordLabel = new JLabel("Password:");

        userTypeDropdown = new JComboBox<>(new String[]{"Admin", "Voter"});
        userIdField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        messageLabel = new JLabel("", JLabel.CENTER);

        add(userTypeLabel);
        add(userTypeDropdown);
        add(userIdLabel);
        add(userIdField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(messageLabel);

        loginButton.addActionListener(e -> handleLogin());

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void handleLogin() {
        String userType = (String) userTypeDropdown.getSelectedItem();
        String userId = userIdField.getText();
        String password = new String(passwordField.getPassword());

        if ("Admin".equals(userType)) {
            if ("admin".equals(userId) && "admin123".equals(password)) {
                messageLabel.setText("Admin login successful!");
                new AdminPanel(this).setVisible(true);
                this.setVisible(false);
            } else {
                messageLabel.setText("Invalid Admin credentials.");
            }
        } else if ("Voter".equals(userType)) {
            if (loggedInVoters.contains(userId)) {
                messageLabel.setText("This voter has already logged in.");
            } else {
                Voter voter = VoterDatabase.getVoter(userId, password);
                if (voter != null) {
                    loggedInVoters.add(userId); // Mark voter as logged in
                    messageLabel.setText("Voter login successful!");
                    new VoterPage(voter).setVisible(true);
                    this.setVisible(false);
                } else {
                    messageLabel.setText("Invalid Voter credentials.");
                }
            }
        } else {
            messageLabel.setText("Select a valid user type.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}

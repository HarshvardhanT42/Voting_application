
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

class AdminPanel extends JFrame {
    private JButton electionManagementButton;
    private JButton voterManagementButton;
    private JButton logoutButton;

    public AdminPanel(JFrame loginPage) {
        setTitle("Admin Panel");
        setLayout(new GridLayout(3, 1));

        electionManagementButton = new JButton("Election Management");
        voterManagementButton = new JButton("Voter Management");
        logoutButton = new JButton("Logout");

        add(electionManagementButton);
        add(voterManagementButton);
        add(logoutButton);

        electionManagementButton.addActionListener(e -> new ElectionManagementPage(this).setVisible(true));
        voterManagementButton.addActionListener(e -> new VoterManagementPage(this).setVisible(true));
        logoutButton.addActionListener(e -> {
            this.setVisible(false);
            loginPage.setVisible(true);
        });

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}

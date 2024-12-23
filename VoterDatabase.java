import java.util.*;

class VoterDatabase {
    private static Map<String, Voter> voters = new HashMap<>();

    public static void addVoter(Voter voter) {
        voters.put(voter.getRollNumber(), voter);
    }

    public static Voter getVoter(String rollNumber, String password) {
        Voter voter = voters.get(rollNumber);
        return (voter != null && voter.getPassword().equals(password)) ? voter : null;
    }
}

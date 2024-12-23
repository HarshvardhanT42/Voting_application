public class Candidate {
    private String name;
    private String rollNo;
    private int id;
    private int voteCount;

    public Candidate(String name, String rollNo, int id) {
        this.name = name;
        this.rollNo = rollNo;
        this.id = id;
        this.voteCount = 0; // Initialize vote count to 0
    }

    public String getName() {
        return name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public int getId() {
        return id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void incrementVoteCount() {
        voteCount++;
    }
}

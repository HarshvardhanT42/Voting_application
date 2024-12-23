import java.util.*;

class CandidateDatabase {
    private static Map<Integer, Candidate> candidates = new HashMap<>();

    public static void addCandidate(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }

    public static Candidate getCandidateById(int id) {
        return candidates.get(id);
    }

    public static Collection<Candidate> getAllCandidates() {
        return candidates.values();
    }
}

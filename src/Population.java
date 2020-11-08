import java.util.ArrayList;

/**
 * Population class for problem 1.
 * Represents a population of value encoded candidates.
 * Author: Daniel Bartolini
 * Login: db666
 */
public class Population {
    private ArrayList<Candidate> candidates;

    /**
     * Creates empty population.
     */
    public Population() {
        candidates = new ArrayList<>();
    }

    /**
     * Creates population of the specified size.
     *
     * @param size population size
     */
    public Population(int size) {
        candidates = new ArrayList<>();
        for (int i = 0; i < size; i++) candidates.add(new Candidate());
    }

    public Candidate getCandidate(int index) {
        return candidates.get(index);
    }

    public ArrayList<Candidate> getCandidates() {
        return candidates;
    }

    /**
     * Get best (lowest fitness) candidate in the population.
     *
     * @return candidate with lowest fitness
     */
    public Candidate getBest() {
        double min =
                candidates.stream()
                        .mapToDouble(Candidate::getFitness)
                        .min()
                        .getAsDouble();

        return candidates.stream()
                .filter(candidate -> candidate.getFitness() == min)
                .findFirst()
                .get();
    }
}

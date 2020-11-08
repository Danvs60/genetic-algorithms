import java.util.ArrayList;

/**
 * Population class for problem 2.
 * Represents a population of bit encoded individuals.
 * Author: Daniel Bartolini
 * Login: db666
 */
public class Luggage {
    ArrayList<Individual> stuff;

    /**
     * Creates empty population.
     */
    public Luggage() {
        stuff = new ArrayList<>();
    }

    /**
     * Creates a population of the specified size.
     * @param size size of the population.
     */
    public Luggage(int size) {
        stuff = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            stuff.add(new Individual());
        }
    }

    public ArrayList<Individual> getStuff() {
        return stuff;
    }

    /**
     * Get best (highest fitness) individual in the population.
     *
     * @return individual with highest fitness.
     */
    public Individual getBest() {
        double max =
                stuff.stream()
                        .mapToDouble(Individual::getFitness)
                        .max()
                        .getAsDouble();

        return stuff.stream()
                .filter(candidate -> candidate.getFitness() == max)
                .findFirst()
                .get();

//        return stuff.stream() INEFFICIENT
//                .sorted((ind1, ind2) -> Double.compare(ind2.getFitness(), ind1.getFitness()))
//                .findFirst()
//                .get();
    }
}

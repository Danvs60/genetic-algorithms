/**
 * Genetic algorithm for problem 2.
 * Called luggage to not confuse it with my first Population for problem 1.
 * Author: Daniel Bartolini
 * Login: db666
 */
public class KnapsackAlgorithm {
    private static final double MUTATION_RATE = 0.01;
    private static final double CROSSOVER_RATE = 0.85;
    private static final int TOURNAMENT_SIZE = 3;
    private static final boolean ELITISM = true;
    public static double MAX_WEIGHT = 500.0;

    /**
     * Evolve a population(luggage) of bit encoded individuals.
     *
     * @param old population to be evolved
     * @return evolved population
     */
    public Luggage evolve(Luggage old) {
        Luggage evolution = new Luggage();
        int luggageSize = old.getStuff().size();

        //Elitism
        int elites = 0;
        if (ELITISM) {
            evolution.getStuff().add(0, old.getBest());
            elites = 1;
        }

        //Tournament + crossover (and elitism)
        for (int i = elites; i < luggageSize; i++) {
            Individual ind1 = tournament(old);
            Individual ind2 = tournament(old);
            evolution.getStuff().add(i, crossover(ind1, ind2));
        }

        //Mutation (and elitism)
        for (int i = elites; i < luggageSize; i++) {
            mutateIndividual(evolution.getStuff().get(i));
        }

        return evolution;
    }

    /**
     * Tournament selection.
     * Randomly select candidates from the given population and return the one with the best fitness.
     *
     * @param l population to extract individuals from.
     * @return best individual among the selected ones.
     */
    public Individual tournament(Luggage l) {
        Luggage tournament = new Luggage();
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            tournament.getStuff().add(l.getStuff().get((int) (Math.random() * l.getStuff().size())));
        }
        return tournament.getBest();
    }

    /**
     * Perform random point crossover on the input individuals.
     *
     * @param one first individual
     * @param two second individual
     * @return crossed over resulting individual.
     */
    private Individual crossover(Individual one, Individual two) {
        Individual res = new Individual();
        for (int i = 0; i < Individual.SIZE; i++) {
            res.set(i, one.getBits()[i]);
        }
        if (Math.random() <= CROSSOVER_RATE) {
            int randomPoint = (int) (Math.random() * Individual.SIZE);
            for (int i = 0; i < randomPoint; i++) {
                res.set(i, one.getBits()[i]);
            }
            for (int j = randomPoint; j < Individual.SIZE; j++) {
                res.set(j, two.getBits()[j]);
            }
        }
        return res;
    }

    /**
     * Perform bit flip mutation on the individual.
     * Flips a random number of bits.
     *
     * @param ind individual to be mutated.
     */
    private void mutateIndividual(Individual ind) {
        for (int i = 0; i < Individual.SIZE; i++) {
            if (Math.random() <= MUTATION_RATE) {
                ind.set(i, !(ind.getBits()[i]));
            }
        }
    }
}

import java.util.Random;

/**
 * Genetic Algorithm for problem 1.
 * Author: Daniel Bartolini
 * Login: db666
 */
public class GeneticAlgorithm {
    private static final double MUTATION_RATE = 0.8;
    private static final double CROSSOVER_RATE = 0.6;
    private static final int TOURNAMENT_SIZE = 5;
    private static final boolean ELITISM = true;

    private static Random rnd = new Random();

    /**
     * Evolve a population of value(double) encoded candidates.
     *
     * @param p Population to be evolved.
     * @return Offspring of the population input parameter.
     */
    public Population evolve(Population p) {
        int popSize = p.getCandidates().size();
        Population result = new Population();

        //Elitism
        int elites = 0;
        Candidate best = p.getBest();
        if (ELITISM) {
            result.getCandidates().add(0, best);
            elites = 1;
        }

        //Tournament + crossover (and elitism)
        double crossoverRate = CROSSOVER_RATE;
        //Higher crossover rate if close to global optimum
        crossoverRate += best.getFitness() < 0.001 ? 0.3 : 0.0;
        for (int i = elites; i < popSize; i++) {
            Candidate c1 = tournament(p);
            Candidate c2 = tournament(p);
            result.getCandidates().add(i, crossover(c1, c2, crossoverRate));
        }

        //Mutation (and elitism)
        double mutationRate = MUTATION_RATE;
        //Lower mutation rate if close to global optimum
        mutationRate -= best.getFitness() < 0.001 ? 0.79 : 0.0;
        for (int i = elites; i < popSize; i++) {
            mutateCandidate(result.getCandidate(i), mutationRate);
        }

        return result;
    }

    /**
     * Tournament selection.
     * Randomly select candidates from the given population and return the one with the best fitness.
     *
     * @param p population to extract candidates from.
     * @return best candidate among the selected ones.
     */
    public Candidate tournament(Population p) {
        Population t = new Population();
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            t.getCandidates().add(p.getCandidate(rnd.nextInt(p.getCandidates().size())));
        }
        return t.getBest();
    }

    /**
     * Single candidate mutation.
     * Add or subtract a small value to values of the candidate.
     *
     * @param c            candidate to be mutated.
     * @param mutationRate chance of mutating one value.
     */
    private void mutateCandidate(Candidate c, double mutationRate) {
        for (int i = 0; i < Candidate.DIALS_NO; i++) {
            if (Math.random() <= mutationRate) {
                double v = c.get(i);
                double m = (Math.random() / 1800) * Math.random();
                if (mutationRate < 0.8) //mutation rate has been lowered because close to global optimum
                    m /= 20; //hence we lower the factor to add/subtract to the current value.
                double newValue = Math.random() <= 0.5 ?
                        v + m
                        :
                        v - m;
                c.set(i, newValue);
            }

            //Combine previous mutation with swap mutation for better efficiency
            //Two values of the candidate (randomly chosen) are swapped
            if (Math.random() <= MUTATION_RATE + 0.3) {
                int index1 = rnd.nextInt(Candidate.DIALS_NO), index2 = rnd.nextInt(Candidate.DIALS_NO);
                c.set(index1, c.get(index2));
            }
        }
    }

    /**
     * Perform uniform rate crossover among two candidates.
     *
     * @param c1 first candidate
     * @param c2 second candidate
     * @param crossoverRate biased rate that decides which candidate is going to be preserved more.
     * @return crossed over candidate.
     */
    private Candidate crossover(Candidate c1, Candidate c2, double crossoverRate) {
        Candidate res = new Candidate();
        for (int i = 0; i < Candidate.DIALS_NO; i++) {
            double v = Math.random() <= crossoverRate ?
                    c1.get(i)
                    :
                    c2.get(i);
            res.set(i, v);
        }
        return res;
    }
}

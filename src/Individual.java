/**
 * Individual class for problem 2.
 * Represents an object with its weight and utility.
 * Author: Daniel Bartolini
 * Login: db666
 */
public class Individual {
    private boolean[] bits;
    public final static int SIZE = 100;

    /**
     * Constructor that returns a randomly generated individual of 0s and 1s.
     * Starting with more 0s for efficiency reasons.
     */
    public Individual() {
        bits = new boolean[SIZE];
        for (int i = 0; i < SIZE; i++) {
            bits[i] = (Math.random() > 0.8);
        }
    }

    public boolean[] getBits() {
        return bits;
    }

    public void set(int index, boolean b) {
        bits[index] = b;
    }

    /**
     * Assess the fitness of the object based on its weight and utility.
     * Since the maximum weight is 500, any individual with a weight less than or equal to that
     * will have a fitness value equal to their utility.
     * Individuals with a higher weight will have a fitness value penalised by the difference
     * of their weight and the maximum one.
     *
     * @return calculated fitness of the individual.
     */
    public double getFitness() {
        double[] assess = Assess.getTest2(bits);
        if (assess[0] > KnapsackAlgorithm.MAX_WEIGHT)
            return (assess[1] - (assess[0] - KnapsackAlgorithm.MAX_WEIGHT));
        return assess[1];
    }
}

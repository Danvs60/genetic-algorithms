import java.util.Arrays;

/**
 * Candidate class for problem 1.
 * Represents a value encoded candidate.
 * Author: Daniel Bartolini
 * Login: db666
 */
public class Candidate {
    private double[] values;
    public static final int DIALS_NO = 20;

    /**
     * Create candidate with random double values.
     */
    public Candidate() {
        values = new double[DIALS_NO];
        for (int j = 0; j < DIALS_NO; j++) {
            values[j] = Math.random() * Math.round(5.12 * (Math.random() - Math.random()));
//            values[j] = Math.random() * 10 - 5;
        }
    }

    public double[] getValues() {
        return values;
    }

    public void set(int index, double value) {
        values[index] = value;
    }

    public double get(int index) {
        return values[index];
    }

    public double getFitness() {
        return Assess.getTest1(values);
    }

    @Override
    public String toString() {
        return "Candidate {" + "\n" +
                "values = " + Arrays.toString(values) + "\n" +
                "fitness = " + this.getFitness() + "\n" +
                '}';
    }
}

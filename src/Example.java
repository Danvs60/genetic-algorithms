//This is my example Solution

import java.lang.Math;
import java.util.*;

class Example {
    public static void main(String[] args) {
        //Do not delete/alter the next line
        long startT = System.currentTimeMillis();

        //Edit this according to your name and login
        String name = "Daniel Bartolini";
        String login = "db666";

        System.out.println("These are the instructions of how to use the problem library.  Please make sure you follow the instructions carefully.");
        System.out.println("For the first problem, you need to use Assess.getTest1(double[]).");

        /**
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * FIRST PROBLEM
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         */

        GeneticAlgorithm ga = new GeneticAlgorithm();
        Population p = new Population(400);
        int genCount = 0;
        while (p.getBest().getFitness() > 0.0 && genCount < 250) {
            genCount++;
            System.out.println("Generation Number: " + genCount + "\n" +
                    "Best fitness: " + p.getBest().getFitness() + "\n");
            p = ga.evolve(p);
        }
        double sol1[] = p.getBest().getValues();

        //get the fitness for a candidate solution in problem 1 like so
        double fit = Assess.getTest1(sol1);


        System.out.println("The fitness of your example Solution is: " + fit);

        /**
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * SECOND PROBLEM
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         */

        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Now let us turn to the second problem:");
        System.out.println("A sample solution in this case is a boolean array of size 100.");
        System.out.println("I now create a random sample solution and get the weight and utility:");

        KnapsackAlgorithm ka = new KnapsackAlgorithm();
        Luggage luggage = new Luggage(200);
        int luggageCount = 0;
        while (luggage.getBest().getFitness() < 210.0 && luggageCount < 120) {
            luggageCount++;
            System.out.println("Generation Number: " + luggageCount + "\n" +
                    "Best fitness: " + luggage.getBest().getFitness() + "\n");
            luggage = ka.evolve(luggage);
        }

        //Now checking the fitness of the candidate solution
        boolean sol2[] = luggage.getBest().getBits();
        double[] tmp = (Assess.getTest2(sol2));

        //The index 0 of tmp gives the weight. Index 1 gives the utility
        System.out.println("The weight is: " + tmp[0]);
        System.out.println("The utility is: " + tmp[1]);

        /**
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * SUBMISSION
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         */

        //Once completed, your code must submit the results you generated, including your name and login:
        //Use and adapt  the function below:
        Assess.checkIn(name, login, sol1, sol2);

        //Do not delete or alter the next line
        long endT = System.currentTimeMillis();
        System.out.println("Total execution time was: " + ((endT - startT) / 1000.0) + " seconds");


    }


}

package ru.belgy.polshchikov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import static java.lang.Math.pow;

/**
 * Given a natural number n and a real number x, find the sum of n terms
 * of the series: S = 1 - (x^2)/2! + (x^4)/4! + _ + ((-1)^n) * (x^2n)/(2n)!.
 *
 * <p>The class uses the Log4j 2 library for logging, and logs several
 * messages throughout the program's execution. The logs are printed
 * to the console and to files named "logsStartingFromDebug.log"
 * and "logsStartingFromWarn.log".</p>
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class Amount {

    /**
     * The name of the file where the program writes the output.
     */
    private static final String FILENAME = "resultExercise7.txt";

    private static final Logger LOG
            = LogManager.getLogger(Amount.class.getName());

    /**
     * Calculates the factorial.
     * @param factorial the number whose factorial is to be calculated.
     * @return returns the factorial of an integer.
     */
    public static int getFactorial(int factorial) {
        int result = 1;

        LOG.debug("The factorial is calculated...");
        for (int i = 1; i <= factorial; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Calculates the sum of the series based on the given parameters.
     * Uses the method {@code getFactorial}.
     * @param n number of steps.
     * @param x algebraic variable.
     * @return returns the result of the sum of the series.
     */
    public static double getSumOfSeries(int n, double x) {
        double formula;
        double sumOfSeries = 0;

        LOG.debug("Calculated sum of series for the given n...");
        for (int i = 1; i <= n; i++)
        {
            formula = pow(-1, n) * (pow(x, 2 * n)) / getFactorial(2 * n);
            sumOfSeries = sumOfSeries + formula;
        }
        return sumOfSeries;
    }

    /**
     * Prompts the value of x and number steps, prints sum of series,
     * calls the {@code getSumOfSeries} method, and writes it to a file.
     * @param args the command-line arguments (ignored).
     */
    public static void main(String[] args) throws FileNotFoundException {
        LOG.info("Starting the program...");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of steps: ");
        int numberSteps = scanner.nextInt();

        System.out.println("Enter x: ");
        double valueX = scanner.nextInt();

        String outputString = "Sum of the series = "
                + getSumOfSeries(numberSteps, valueX);

        System.out.println(outputString);

        try (PrintWriter file = new PrintWriter(FILENAME)) {
            LOG.debug("Writing data to file {}...", FILENAME);
            file.write(outputString);
            LOG.debug("Data has been written to file {}.", FILENAME);
        } catch (FileNotFoundException ex) {
            LOG.error("Error creating or opening file {}:", FILENAME, ex);
            return;
        }

        LOG.info("Program has completed successfully.");

        // Test log to demonstrate code's functionality.
        LOG.warn("This is a test warning message.");
    }
}
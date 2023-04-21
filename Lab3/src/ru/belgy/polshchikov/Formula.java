package ru.belgy.polshchikov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * Calculate the value of the expression
 * using the formula: sin√(x+1)-sin√(x-1).
 *
 * <p>The class uses the Log4j 2 library for logging, and logs several
 * messages throughout the program's execution. The logs are printed
 * to the console and to files named "logsStartingFromDebug.log"
 * and "logsStartingFromWarn.log".</p>
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class Formula {

    /**
     * The name of the file where the program writes the output.
     */
    private static final String FILENAME = "resultExercise1.txt";

    private static final Logger LOG
            = LogManager.getLogger(Formula.class.getName());

    /**
     * Calculates the value of the expression using the formula.
     * @param x algebraic variable.
     * @return the value of the expression using the formula.
     */
    private static double calculateFormula(double x) {
        LOG.debug("The formula is calculated for the given x.");
        return sin(sqrt(x + 1)) - sin(sqrt(x - 1));
    }

    /**
     * Prompts the value of x, prints the formula's result calculation,
     * calls the {@code calculateFormula} method, and writes it to a file.
     * @param args the command-line arguments (ignored).
     */
    public static void main(String[] args) {
        LOG.info("Starting the program...");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the value of x: ");
        double valueX = scanner.nextDouble();

        String outputString = "Result of sin√(x+1)-sin√(x-1),"
                + " if x equals " + valueX + ": " + calculateFormula(valueX);

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
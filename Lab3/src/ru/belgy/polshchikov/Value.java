package ru.belgy.polshchikov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import static java.lang.Math.pow;

/**
 * Get the values -2x + 3(x^2) - 4(x^3) and 1 + 2x + 3(x^2) + 4(x^3).
 *
 * <p>The class uses the Log4j 2 library for logging, and logs several
 * messages throughout the program's execution. The logs are printed
 * to the console and to files named "logsStartingFromDebug.log"
 * and "logsStartingFromWarn.log".</p>
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class Value {

    /**
     * The name of the file where the program writes the output.
     */
    private static final String FILENAME = "resultExercise2.txt";

    private static final Logger LOG
            = LogManager.getLogger(Value.class.getName());

    /**
     * Calculates the first and second values from the problem statement.
     * @param x algebraic variable.
     * @return a string containing the computed values.
     */
    private static String getValue(double x) {
        double firstValue = -2 * x + 3 * pow(x, 2) - 4 * pow(x, 3);
        double secondValue = 1 + 2 * x + 3 * pow(x, 2) + 4 * pow(x, 3);

        LOG.debug("The values are calculated for the given x.");
        return "-2x + 3(x^2) - 4(x^3) = " + firstValue + "\n"
                + "1 + 2x + 3(x^2) + 4(x^3) = " + secondValue;
    }

    /**
     * Prompts the value of x, prints the results of calculating values,
     * calls the {@code calculateFormula} method, and writes it to a file.
     * @param args the command-line arguments (ignored).
     */
    public static void main(String[] args) {
        LOG.info("Starting the program...");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the value of x: ");
        double valueX = scanner.nextDouble();

        String outputString = "Results for x equal to "
                + valueX + ":" + "\n" + getValue(valueX);

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

        // Test log to demonstrate the code's functionality.
        LOG.warn("This is a test warning message.");
    }
}

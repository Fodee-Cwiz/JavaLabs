package ru.belgy.polshchikov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Determine which numbers a, b, c are divisible by a given number k.
 *
 * <p>The class uses the Log4j 2 library for logging, and logs several
 * messages throughout the program's execution. The logs are printed
 * to the console and to files named "logsStartingFromDebug.log"
 * and "logsStartingFromWarn.log".</p>
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class Divisor {

    /**
     * The name of the file where the program writes the output.
     */
    private static final String FILENAME = "resultExercise3.txt";

    private static final Logger LOG
            = LogManager.getLogger(Divisor.class.getName());

    /**
     * Determines for which numbers a specific divider is suitable.
     * @param number the entered number.
     * @param variableDivider the given divider value.
     * @return a string response,
     * which number is divisible by the given divider.
     * If there is no such number, it returns an empty string.
     */
    private static String determineForWhichNumbersIsDivisor
    (int number, int variableDivider, char nameVariable) {
        LOG.debug("Determine for which numbers is divisor for the given k.");
        if (((number % variableDivider) == 0) && (number != 0)) {
            return variableDivider + " is a divisor of " + number
                    + " (" + nameVariable + ")" + "\n";
        } else {
            return "";
        }
    }

    /**
     * Prompts the value of the divider (k), prints for which numbers
     * is divisor, calls the {@code determineForWhichNumbersIsDivisor} method,
     * and writes it to a file.
     * @param args the command-line arguments (ignored)
     */
    public static void main(String[] args) {
        LOG.info("Starting the program...");

        Scanner scanner = new Scanner(System.in);
        int[] value = new int[3];
        char wordForArray = 'a';
        StringBuilder outputString = new StringBuilder();

        System.out.println("Enter the value of the divider (k): ");
        int valueDivider = scanner.nextInt();

        for (int i = 0; i < 3; i++) {
            System.out.println("Enter the value " + wordForArray + ": ");
            value[i] = scanner.nextInt();

            outputString.append(determineForWhichNumbersIsDivisor(value[i],
                    valueDivider, wordForArray));

            wordForArray++;
        }

        System.out.println(outputString);

        try (PrintWriter file = new PrintWriter(FILENAME)) {
            LOG.debug("Writing data to file {}...", FILENAME);
            file.write(outputString.toString());
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
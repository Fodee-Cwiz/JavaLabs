package ru.belgy.polshchikov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A program that prompts the user to enter their name and surname,
 * and prints it 10 times.
 *
 * <p>The class uses the Log4j 2 library for logging, and logs several
 * messages throughout the program's execution. The logs are printed
 * to the console and to files named "logsStartingFromDebug.log"
 * and "logsStartingFromWarn.log".</p>
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class Name {

    /**
     * The name of the file where the program writes the output.
     */
    private static final String FILENAME = "resultExercise8.txt";

    private static final Logger LOG
            = LogManager.getLogger(Name.class.getName());

    /**
     * Prints the given name and surname to the console 10 times,
     * and writes it to a file.
     * @param name the name to print.
     * @param surname the surname to print.
     */
    private static void printNameSurname(String name, String surname) {
        LOG.debug("Printing name and surname...");
        String outputString = name + " " + surname;

        for (int i = 0; i < 10; i++) {
            System.out.println(outputString);
        }

        try (PrintWriter file = new PrintWriter(FILENAME)) {
            LOG.debug("Writing data to file {}...", FILENAME);
            for (int i = 0; i < 10; i++) {
                file.write(outputString + "\n");
            }
            LOG.debug("Data has been written to file {}.", FILENAME);
        } catch (FileNotFoundException ex) {
            LOG.error("Error creating or opening file {}:", FILENAME, ex);
        }
    }

    /**
     * Prompts the user for their name and surname,
     * and calls the {@code printNameSurname} method.
     * @param args the command-line arguments (ignored).
     */
    public static void main(String[] args) {
        LOG.info("Starting the program...");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your surname: ");
        String surname = scanner.nextLine();

        scanner.close();

        printNameSurname(name, surname);

        LOG.info("Program has completed successfully.");

        // Test log to demonstrate code's functionality
        LOG.warn("This is a test warning message.");
    }
}

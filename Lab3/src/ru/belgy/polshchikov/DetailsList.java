package ru.belgy.polshchikov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A numbered list of details is available: 1) screw, 2) nut, 3) bolt,
 * 4) nail, 5) washer. This program, by a detail number,
 * displays its name on the screen.
 *
 * <p>The class uses the Log4j 2 library for logging, and logs several
 * messages throughout the program's execution. The logs are printed
 * to the console and to files named "logsStartingFromDebug.log"
 * and "logsStartingFromWarn.log".</p>
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class DetailsList {

    /**
     * The name of the file where the program writes the output.
     */
    private static final String FILENAME = "resultExercise5.txt";

    private static final Logger LOG
            = LogManager.getLogger(DetailsList.class.getName());

    /**
     * Determines which number corresponds to a particular detail.
     * @param number detail number.
     * @return returns a string with the name of the detail.
     * If there is no detail under this number, it returns an error string.
     */
    public static String getNameDetail(int number) {
        LOG.debug("Getting detail's name for the given number...");

        switch (number) {
            case 1 -> {
                return "This is a screw.";
            }
            case 2 -> {
                return "This is a nut.";
            }
            case 3 -> {
                return "This is a bolt.";
            }
            case 4 -> {
                return "This is a nail.";
            }
            case 5 -> {
                return "This is a washer.";
            }
            default -> {
            }
        }
        return "No details under this number!";
    }

    /**
     * Prompts number of detail, prints detail's name, calls
     * the {@code getNameDetail} method, and writes it to a file.
     * @param args the command-line arguments (ignored).
     */
    public static void main(String[] args) throws FileNotFoundException {
        LOG.info("Starting the program...");

        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - screw, 2 - nut, 3 - bolt, 4 - nail, "
                + "5 - washer." + "\n"
                + "Enter the number of the required detail: ");
        int numberDetail = scanner.nextInt();

        System.out.println(getNameDetail(numberDetail));

        try (PrintWriter file = new PrintWriter(FILENAME)) {
            LOG.debug("Writing data to file {}...", FILENAME);
            file.write(getNameDetail(numberDetail));
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
package ru.belgy.polshchikov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.regex.*;
import java.util.Scanner;

/**
 * The RegistrationData class is used to read and write registration data.
 *
 * <p>The class uses the Log4j 2 library for logging, and logs several
 * messages throughout the program's execution. The logs are printed
 * to the console and to files named "logsStartingFromDebug.log"
 * and "logsStartingFromWarn.log".</p>
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class RegistrationData {

    /**
     * The name of the file where the program writes the output.
     */
    private static final String FILENAME = "registrationData.txt";

    private static final Logger LOG
            = LogManager.getLogger(RegistrationData.class.getName());

    /**
     * Reads registration data from the specified scanner using
     * the specified pattern.
     * @param scanner The scanner to read data from.
     * @param pattern The pattern to use for matching the data.
     */
    private static void readData(Scanner scanner, Pattern pattern) {
        LOG.info("Starting to read registration data.");

        boolean correctDataEntered = false;

        while (!correctDataEntered) {
            System.out.println("Enter registration data "
                    + "([number car] [manufacturer] "
                    + "[model] [year of manufacture]):");
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);

            if (matcher.matches()) {
                String number = "Car number - " + matcher.group(1);
                String manufacturer = "Manufacturer - " + matcher.group(2);
                String model = "Model - " + matcher.group(3);
                String year = "Year of manufacture - " + matcher.group(4);

                System.out.println(number + "\n" + manufacturer + "\n"
                        + model + "\n" + year);

                try (PrintWriter file = new PrintWriter(FILENAME)) {
                    LOG.debug("Writing data to file {}...", FILENAME);
                    file.write(number + "\n" + manufacturer + "\n" + model
                            + "\n" + year);
                    LOG.debug("Data has been written to file {}.", FILENAME);
                } catch (FileNotFoundException ex) {
                    LOG.error("Error creating or opening file {}:",
                            FILENAME, ex);
                }

                correctDataEntered = true;
            } else {
                LOG.error("Incorrect data!");
            }
        }

        LOG.info("Finished reading registration data.");
    }

    /**
     * The main method of the program.
     * @param args the command-line arguments (ignored).
     */
    public static void main(String[] args) {
        LOG.info("Starting the program...");

        Scanner scanner = new Scanner(System.in);
        String regex = "^([А-Я]\\d{3}[А-Я]{2}\\d{2,3})"
                + "\\s+([A-Za-z]+)\\s+([A-Za-z0-9]+)\\s+(\\d{4})$";

        Pattern pattern = null;
        try {
            pattern = Pattern.compile(regex);
        } catch (PatternSyntaxException e) {
            System.out.println("Invalid regular expression: "
                    + e.getMessage());
            System.exit(1);
        }

        readData(scanner, pattern);

        LOG.info("Program has completed successfully.");
    }
}

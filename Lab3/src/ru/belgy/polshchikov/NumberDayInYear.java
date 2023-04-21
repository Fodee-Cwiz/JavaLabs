package ru.belgy.polshchikov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This program outputs the date in the accepted
 * format based on the day number in a year.
 *
 * <p>The class uses the Log4j 2 library for logging, and logs several
 * messages throughout the program's execution. The logs are printed
 * to the console and to files named "logsStartingFromDebug.log"
 * and "logsStartingFromWarn.log".</p>
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class NumberDayInYear {

    /**
     * The name of the file where the program writes the output.
     */
    private static final String FILENAME = "resultExercise6.txt";

    private static final Logger LOG
            = LogManager.getLogger(NumberDayInYear.class.getName());

    /**
     * Calculates the date based on the day number in a year.
     * @param numberDay the day number in a year.
     * @return returns a string with the month and day of the month.
     * If the specified day does not exist in the year,
     * it returns an error message string.
     */
    public static String getDayAndMonth(int numberDay) {
        int[] dayInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] monthName = {"January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October",
                "November", "December"};
        int i;

        LOG.debug("The date is calculated for the given day...");
        if ((numberDay > 0) && (numberDay < 366)) {
            for (i = 0; numberDay > dayInMonth[i]; i++) {
                numberDay -= dayInMonth[i];
            }
            return numberDay + " " + monthName[i];
        }
        return "There is no " + numberDay + "th day in a non-leap year!";
    }

    /**
     * Prompts number day's in year, prints string with date (day, month)
     * for the given day, calls the {@code getDayAndMonth} method,
     * and writes it to a file.
     * @param args the command-line arguments (ignored).
     */
    public static void main(String[] args) throws FileNotFoundException {
        LOG.info("Starting the program...");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the day number in a year: ");
        int dayInYear = scanner.nextInt();

        System.out.println(getDayAndMonth(dayInYear));

        try (PrintWriter file = new PrintWriter(FILENAME)) {
            LOG.debug("Writing data to file {}...", FILENAME);
            file.write(getDayAndMonth(dayInYear));
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
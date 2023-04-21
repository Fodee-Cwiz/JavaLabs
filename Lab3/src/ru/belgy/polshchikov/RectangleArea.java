package ru.belgy.polshchikov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The coordinates of the rectangle vertices are given: (x1, y1), (x2, y2),
 * (x3, y3), (x4, y4). Determine the area of the part of the rectangle
 * located in the first quadrant of the coordinate plane.
 *
 * <p>The class uses the Log4j 2 library for logging, and logs several
 * messages throughout the program's execution. The logs are printed
 * to the console and to files named "logsStartingFromDebug.log"
 * and "logsStartingFromWarn.log".</p>
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class RectangleArea {

    /**
     * The name of the file where the program writes the output.
     */
    private static final String FILENAME = "resultExercise4.txt";

    private static final Logger LOG
            = LogManager.getLogger(RectangleArea.class.getName());

    /**
     * Checks if an object is a rectangle.
     * @param x horizontal axis coordinate.
     * @param y vertical axis coordinate.
     * @return returns {@code true} if it satisfies the conditions,
     * and {@code false} otherwise.
     */
    public static boolean checkItIsRectangle(int[] x, int[] y) {
        LOG.debug("Checks if an object is a rectangle.");
        return (x[0] == x[1]) && (x[2] == x[3]) && (y[0] == y[3])
                && (y[1] == y[2]) && (x[0] != x[3]);
    }

    /**
     * Checks if the top right corner lies in the first quadrant.
     * @param x horizontal axis coordinate.
     * @param y vertical axis coordinate.
     * @return returns {@code true} if it satisfies the conditions,
     * and {@code false} otherwise.
     */
    public static boolean checkHasPositivePoint(int[] x, int[] y) {
        LOG.debug("Checks if the top right corner lies in "
                + "the first quadrant.");
        return (x[0] > 0) && (y[0] > 0) && (x[0] > x[3]) && (y[0] > y[1]);
    }

    /**
     * Calculates the square.
     * @param x horizontal axis coordinate.
     * @param y vertical axis coordinate.
     * @return returns the string of the result of the square calculation.
     */
    public static String getSquare(int[] x, int[] y) {
        int horizontalSide;
        int verticalSide;
        int square;

        LOG.debug("Calculated square for the given x and y.");
        if (x[3] < 0) {
            horizontalSide = x[0];
        } else {
            horizontalSide = x[0] - x[3];
        }
        if (y[1] < 0) {
            verticalSide = y[0];
        } else {
            verticalSide = y[0] - y[1];
        }

        square = horizontalSide * verticalSide;

        return "The square of the part of the rectangle located "
                + "in the first quadrant of the coordinate plane = "
                + square;
    }

    /**
     * Prompts coordinates of the rectangle, prints the square of the part
     * of the rectangle located in the first quadrant of the
     * coordinate plane, calls the {@code checkItIsRectangle},
     * {@code checkHasPositivePoint}, and {@code getSquare} methods,
     * and writes it to a file.
     * @param args the command-line arguments (ignored).
     */
    public static void main(String[] args) throws FileNotFoundException {
        LOG.info("Starting the program...");

        Scanner scanner = new Scanner(System.in);
        int[] horizontalCoordinate = new int[4];
        int[] verticalCoordinate = new int[4];
        String outputString;

        System.out.println("Enter the coordinates of the four points, "
                + "starting with the top-right corner and going clockwise.");
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter the coordinates of point " + (i + 1)
                    + " (x, then y): ");
            horizontalCoordinate[i] = scanner.nextInt();
            verticalCoordinate[i] = scanner.nextInt();
        }

        if (checkItIsRectangle(horizontalCoordinate, verticalCoordinate)) {
            if (checkHasPositivePoint(horizontalCoordinate,
                    verticalCoordinate)) {
                outputString = getSquare(horizontalCoordinate,
                        verticalCoordinate);
            } else {
                outputString = "The top-right corner is not in "
                        + "the first quadrant!";
            }
        } else {
            outputString = "These points do not form a rectangle!";
        }

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

/**
 * This is a multithreaded application that emulates the operation
 * of the library. Several books are available for reading in the library.
 * Some of them can be handed out, some only to the reading room.
 * Visitors can take several books in their hands and into the reading room.
 *
 * <p>The classes use the Log4j 2 library for logging, and logs several
 * messages throughout the program's execution. The logs are printed
 * to the console and to file named "logs.log".</p>
 * @author Ilya Polshchikov
 * @since 1.5
 */

package ru.belgy.polshchikov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Represents a library with books and their locations.
 */
class Library {
    private final Map<String, BookLocation> bookLocations;
    private final PrintWriter fileWriter;
    private static final Logger LOG
            = LogManager.getLogger(Library.class.getName());

    /**
     * Constructs a Library object and initializes the bookLocations map
     * and fileWriter.
     * @throws IOException if an I/O error occurs while creating
     * the FileWriter.
     */
    public Library() throws IOException {
        bookLocations = new HashMap<>();
        fileWriter = new PrintWriter(new FileWriter("output.txt"), true);
        LOG.debug("Library instance created.");
    }

    /**
     * Adds a book to the library with the specified location.
     * If the book is "War and Peace" or "Crime and Punishment",
     * the location is randomly determined between HOME and READING_ROOM.
     * @param book the name of the book to add.
     * @param location the location of the book.
     */
    public synchronized void addBook(String book, BookLocation location) {
        if (book.equals("War and Peace")
                || book.equals("Crime and Punishment")) {
            int randomLocation = new Random().nextInt(2);
            if (randomLocation == 0) {
                location = BookLocation.HOME;
            } else {
                location = BookLocation.READING_ROOM;
            }
        }
        bookLocations.put(book, location);
        LOG.info("Added book '{}' to the library.", book);
        notifyAll(); // Notify waiting visitors about book availability
    }

    /**
     * Borrows a book from the library with the specified name for the given
     * visitor. If the book is not available, the method waits until the book
     * becomes available.
     * @param book the name of the book to borrow.
     * @param visitor the name of the visitor borrowing the book.
     * @return true if the book was successfully borrowed, false otherwise.
     */
    public synchronized boolean borrowBook(String book, String visitor) {
        while (!bookLocations.containsKey(book)) {
            try {
                LOG.warn("Book '{}' is not available in the library. "
                        + "'{}' waiting for availability...", book, visitor);
                wait(); // Wait until the book becomes available
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOG.error("Interrupted while waiting "
                        + "for book availability.", e);
                return false;
            }
        }

        BookLocation location = bookLocations.get(book);
        if (location == BookLocation.HOME) {
            bookLocations.remove(book);
            String messageBorrowedHome = visitor + " borrowed the book '"
                    + book + "' to HOME.";
            System.out.println(messageBorrowedHome);
            fileWriter.println(messageBorrowedHome);
            return true;
        } else if (location == BookLocation.READING_ROOM) {
            bookLocations.remove(book);
            String messageBorrowedReading = visitor + " borrowed the book '"
                    + book + "' to READING_ROOM.";
            System.out.println(messageBorrowedReading);
            fileWriter.println(messageBorrowedReading);
            return true;
        }

        LOG.warn("Unknown location for book '{}'.", book);
        return false; // Book location unknown
    }

    /**
     * Returns a borrowed book to the library with the specified name for
     * the given visitor. The book is returned to the READING_ROOM location.
     * @param book the name of the book to return.
     * @param visitor the name of the visitor returning the book.
     */
    public synchronized void returnBook(String book, String visitor) {
        bookLocations.put(book, BookLocation.READING_ROOM);
        String messageReturned = visitor
                + " returned the book '" + book + "'.";
        System.out.println(messageReturned);
        fileWriter.println(messageReturned);
        notifyAll(); // Notify waiting visitors about book availability
    }

    /**
     * Closes the library by closing the file writer.
     */
    public void close() {
        fileWriter.close();
        LOG.debug("Library closed.");
    }
}

/**
 * Represents the possible locations of a book in the library.
 */
enum BookLocation {
    READING_ROOM,
    HOME
}

/**
 * Represents a visitor who can borrow and return books from/to the library.
 */
class Visitor implements Runnable {
    private final String name;
    private final Library library;
    private final Random random;
    private static final Logger LOG
            = LogManager.getLogger(Visitor.class.getName());

    /**
     * Constructs a Visitor object with the specified name and library.
     * @param name the name of the visitor.
     * @param library the library the visitor will interact with.
     */
    public Visitor(String name, Library library) {
        this.name = name;
        this.library = library;
        this.random = new Random();
    }

    @Override
    public void run() {
        List<String> books = getRandomBooks();
        for (String book : books) {
            synchronized (library) {
                while (!library.borrowBook(book, name)) {
                    try {
                        library.wait(); // Wait until book becomes available
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        LOG.error("Interrupted while waiting "
                                + "to borrow a book.", e);
                        return;
                    }
                }
            }

            try {
                Thread.sleep(random.nextInt(3000) + 1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOG.error("Interrupted while reading a book.", e);
                return;
            }

            synchronized (library) {
                library.returnBook(book, name);
                library.notifyAll();
            }
        }
        LOG.debug("Visitor '{}' finished.", name);
    }

    /**
     * Generates a random list of books for the visitor to borrow.
     * @return a list of randomly selected books.
     */
    private List<String> getRandomBooks() {
        String[] availableBooks = {"War and Peace", "Crime and Punishment",
                "The Captain's Daughter", "Dead Souls"};
        int numBooks = random.nextInt(3) + 2;
        Set<String> selectedBooks = new HashSet<>();

        while (selectedBooks.size() < numBooks) {
            String book
                    = availableBooks[random.nextInt(availableBooks.length)];
            selectedBooks.add(book);
        }

        return new ArrayList<>(selectedBooks);
    }
}

/**
 * Represents the simulation of a library system.
 */
public class LibrarySimulation {
    private static final Logger LOG
            = LogManager.getLogger(LibrarySimulation.class.getName());

    /**
     * The main method that runs the library simulation.
     * @param args the command-line arguments (not used).
     * @throws IOException if an I/O error occurs while creating the library.
     */
    public static void main(String[] args) throws IOException {
        LOG.info("The program is running.");

        Library library = new Library();
        library.addBook("War and Peace", null);
        library.addBook("Crime and Punishment", null);
        library.addBook("The Captain's Daughter", BookLocation.READING_ROOM);
        library.addBook("Dead Souls", BookLocation.READING_ROOM);

        Thread ivanThread = new Thread(new Visitor("Ivan", library));
        Thread fedorThread = new Thread(new Visitor("Fedor", library));

        ivanThread.start();
        fedorThread.start();

        try {
            ivanThread.join();
            fedorThread.join();
        } catch (InterruptedException e) {
            LOG.error("Interrupted while waiting "
                    + "for visitors to finish.", e);
            Thread.currentThread().interrupt();
        }

        library.close();

        LOG.info("Program finished.");
    }
}
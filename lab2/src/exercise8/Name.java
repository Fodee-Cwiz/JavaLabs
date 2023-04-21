package exercise8;

import java.io.*;
import java.util.Scanner;

/**
 * Это программа, которая выводит на экран ваши имя и фамилию 10 раз.
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class Name {
    /**
     * Этот метод выводит на экран и в файл имя и фамилию 10 раз.
     * @param name - имя и фамилия.
     * @throws FileNotFoundException - исключение при не нахождении файла.
     * @see FileNotFoundException
     */
    public static void getNameTenTimes(String name)
            throws FileNotFoundException {
        PrintWriter file = new PrintWriter("resultExercise8.txt");

        for (int i = 0; i < 10; i++) {
            System.out.println(name);
            file.write(name + "\n");
        }
        file.close();
    }

    /**
     * Это основной метод, использующий метод {@code getNameTenTimes}.
     * @param args не используется.
     * @throws FileNotFoundException - исключение при не нахождении файла.
     * @see FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ваше имя и фамилию: ");
        String yourName = scanner.nextLine();

        getNameTenTimes(yourName);
    }
}

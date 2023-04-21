package exercise5;

import java.io.*;
import java.util.Scanner;

/**
 * Имеется пронумерованный список деталей: 1) шуруп, 2) гайка, 3) винт,
 * 4) гвоздь, 5) болт. Это программа, которая по номеру детали
 * выводит на экран ее название.
 */
public class DetailsList {
    /**
     * Этот метод определяет какому номеру соответсвует определенная деталь.
     * @param number - номер детали.
     * @return возвращает строку с наименованием детали.
     * Если детали под таким номером нет, возвращает строку с ошибкой.
     */
    public static String getNameDetail(int number) {
        switch (number) {
            case 1 -> {
                return "Это шуруп.";
            }
            case 2 -> {
                return "Это гайка.";
            }
            case 3 -> {
                return "Это винт.";
            }
            case 4 -> {
                return "Это гвоздь.";
            }
            case 5 -> {
                return "Это болт.";
            }
            default -> {
            }
        }
        return "Детали под таким номером нет!";
    }

    /**
     * Это основной метод, использующий метод {@code getNameDetail}.
     * @param args не используется.
     * @throws FileNotFoundException - исключение при не нахождении файла.
     * @see FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("resultExercise5.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - шуруп, 2 - гайка, 3 - винт, 4 - гвоздь, "
                + "5 - болт." + "\n" + "Введите номер нужной Вам детали: ");
        int numberDetail = scanner.nextInt();

        System.out.println(getNameDetail(numberDetail));
        file.write(getNameDetail(numberDetail));
        file.close();
    }
}

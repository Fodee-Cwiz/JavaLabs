package exercise3;

import java.io.*;
import java.util.Scanner;

/**
 * Определить, делителем каких чисел a, b, с является число k.
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class Divisor {
    /**
     * Этот метод определяет, для каких чисел
     * подходит один и тот же определенный делитель.
     * @param number - введеное число.
     * @param variableDivider - значение-делитель.
     * @return возвращает строку ответа,
     * какому числу приходится данный делитель.
     * Если такого числа нет - ничего не возвращает.
     */
    public static String determineForWhichNumbersIsDivisor
            (int number, int variableDivider) {
        if (((number % variableDivider) == 0) && (number != 0)) {
            return variableDivider
                    + " - является делителем числа " + number + "\n";
        } else
            return "";
    }

    /**
     * Это основной метод,
     * использующий метод {@code determineForWhichNumbersIsDivisor}.
     * @param args не используется.
     * @throws FileNotFoundException - исключение при не нахождении файла.
     * @see FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("resultExercise3.txt");
        Scanner scanner = new Scanner(System.in);
        int[] value = new int[3];
        char wordForArray = 'a';

        System.out.println("Введите значение-делитель (k): ");
        int valueDivider = scanner.nextInt();

        for (int i = 0; i < 3; i++) {
            System.out.println("Введите значение " + wordForArray + ": ");
            value[i] = scanner.nextInt();

            wordForArray++;

            String variableForOutput =
                    determineForWhichNumbersIsDivisor(value[i],
                            valueDivider);

            System.out.print(variableForOutput);
            file.write(variableForOutput);
        }
        file.close();
    }
}

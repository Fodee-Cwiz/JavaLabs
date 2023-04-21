package exercise4;

import java.util.Scanner;

/**
 * <h1> Факториал</h1>
 * Программа, вычисляющая факториал целого числа.
 * <p>
 * Факториал - это произведение натуральных чисел от единицы
 * до данного натурального числа n.
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class Factorial {
    /**
     * Этот метод вычисляет факториал.
     * @param factorial - число, факториал которого требуется вычислить.
     * @return возвращает факториал целого числа.
     */
    public static int getFactorial(int factorial) {
        int result = 1;

        for (int i = 1; i <= factorial; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Это основной метод, использующий метод {@code getFactorial}.
     * @param args не используется.
     */
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вычислить факториал числа: ");
        int number = scanner.nextInt();
        System.out.println("Факториал " + number
                + " = " + getFactorial(number));
    }
}


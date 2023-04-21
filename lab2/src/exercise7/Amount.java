package exercise7;

import java.io.*;
import java.util.Scanner;
import static java.lang.Math.*;

/**
 * Дано натуральное число n и действительное x. Найти сумму n членов ряда:
 * S = 1 - (x^2)/2! + (x^4)/4! + _ + ((-1)^n) * (x^2n)/(2n)!.
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class Amount {
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
     * Этот метод вычисляет сумму ряда уз условия.
     * Использует метод {@code getFactorial}.
     * @param n - количество шагов.
     * @param x - алгебраическая переменная.
     * @return возвращает строку результата вычисления суммы ряда.
     */
    public static String getSumOfSeries(int n, double x) {
        double formula;
        double sumOfSeries = 0;

        for (int i = 1; i <= n; i++)
        {
            formula = pow(-1, n) * (pow(x, 2 * n)) / getFactorial(2 * n);
            sumOfSeries = sumOfSeries + formula;
        }
        return "Сумма ряда = " + sumOfSeries;
    }

    /**
     * Это основной метод, использующий метод {@code getSumOfSeries}.
     * @param args не используется.
     * @throws FileNotFoundException - исключение при не нахождении файла.
     * @see FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("resultExercise7.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество шагов: ");
        int numberSteps = scanner.nextInt();
        System.out.println("Введите x: ");
        double valueX = scanner.nextInt();

        System.out.println(getSumOfSeries(numberSteps, valueX));
        file.write(getSumOfSeries(numberSteps, valueX));
        file.close();

    }
}

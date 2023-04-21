package exercise2;

import java.io.*;
import java.util.Scanner;
import static java.lang.Math.*;

/**
 * Получить значения -2x + З(х^2) – 4(х^3) и 1 + 2x + З(х^2) + 4(х^3).
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class Value {
    /**
     * Этот метод вычисляет первое и второе значения из условия.
     * @param x - алгебраическая переменная.
     * @return возвращает строку результата вычислений значений.
     */
    public static String getValue(double x) {
        double firstValue = -2 * x + 3 * pow(x, 2) - 4 * pow(x, 3);
        double secondValue = 1 + 2 * x + 3 * pow(x, 2) + 4 * pow(x, 3);

        return "-2x + З(х^2) – 4(х^3) = " + firstValue + "\n"
                + "1 + 2x + З(х^2) + 4(х^3) = " + secondValue;
    }

    /**
     * Это основной метод, использующий метод {@code getValue}.
     * @param args не используется.
     * @throws FileNotFoundException - исключение при не нахождении файла.
     * @see FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("resultExercise2.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите значение x: ");
        double valueX = scanner.nextDouble();

        String variableForOutput = "Результаты по x равному "
                + valueX + ":" + "\n" + getValue(valueX);

        System.out.println(variableForOutput);
        file.write(variableForOutput);
        file.close();
    }
}

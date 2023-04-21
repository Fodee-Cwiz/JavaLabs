package exercise1;

import java.io.*;
import java.util.Scanner;
import static java.lang.Math.*;

/**
 * Вычислить значения выражения по формуле: sin√(x+1)-sin√(x-1).
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class Formula {
    /**
     * Этот метод рассчитывает значения выражения по формуле.
     * @param x - алгебраическая переменная.
     * @return возвращает значения выражения по формуле.
     */
    public static double calculateFormula(double x) {
        return sin(sqrt(x + 1)) - sin(sqrt(x - 1));
    }

    /**
     * Это основной метод, использующий метод {@code calculateFormula}.
     * @param args не используется.
     * @throws FileNotFoundException - исключение при не нахождении файла.
     * @see FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("resultExercise1.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите значение x: ");
        double valueX = scanner.nextDouble();

        String variableForOutput = "Результат sin√(x+1)-sin√(x-1),"
                + " если x равен " + valueX + ": " + calculateFormula(valueX);

        System.out.println(variableForOutput);
        file.write(variableForOutput);
        file.close();
    }
}

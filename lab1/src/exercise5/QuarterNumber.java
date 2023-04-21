package exercise5;

import java.util.Scanner;

/**
 * <h1> Номер координатной четверти</h1>
 * Даны координаты точки, не лежащей на координатных осях OX и OY.
 * Определить номер координатной четверти, в которой находится данная точка.
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class QuarterNumber {
    /**
     * Этот метод определяет в какой координатной четверти находится точка.
     * @param x - координата горизотнальной оси.
     * @param y - координата вертикальной оси.
     * @return возвращает номер координатной четверти,
     * в виде массива типа String.
     * @see java.lang.String
     */
    public static String getQuarterNumber(int x, int y) {
        if (x > 0 && y > 0) {
            return "Точка находится в 1 четверти.";
        } else if (x < 0 && y > 0) {
            return "Точка находится во 2 четверти.";
        } else if (x < 0 && y < 0) {
            return "Точка находится в 3 четверти.";
        } else if (x > 0 && y < 0) {
            return "Точка находится в 4 четверти.";
        } else {
            return "Ошибка: x или y не должен быть ревен 0.";
        }
    }

    /**
     * Это основной метод, использующий метод {@code getQuarterNumber}.
     * @param args не используется.
     */
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите координату x: ");
        int horizontalCoordinate = scanner.nextInt();
        System.out.println("Введите координату y: ");
        int verticalCoordinate = scanner.nextInt();

        System.out.println(getQuarterNumber(horizontalCoordinate,
                verticalCoordinate));
    }
}

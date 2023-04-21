package exercise4;

import java.io.*;
import java.util.Scanner;

/**
 * Заданы координаты вершин прямоугольника: (x1, y1), (х2, у2), (x3, y3),
 * (x4, y4). Определить площадь части прямоугольника,
 * расположенной в 1-й координатной четверти.
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class RectangleArea {
    /**
     * Этот метод проверяет является ли объект прямоугольником.
     * @param x - координата горизотнальной оси.
     * @param y - координата вертикальной оси.
     * @return возращает {@code true} в случае соотвестсвия условиям,
     * а {@code false} в противном.
     */
    public static boolean checkItIsRectangle(int[] x, int[] y) {
        return (x[0] == x[1]) && (x[2] == x[3]) && (y[0] == y[3])
                && (y[1] == y[2]) && (x[0] != x[3]);
    }

    /**
     * Этот метод проверяет лежит ли правый верхний угол в 1-й четверти.
     * @param x - координата горизотнальной оси.
     * @param y - координата вертикальной оси.
     * @return возращает {@code true} в случае соотвестсвия условиям,
     * а {@code false} в противном.
     */
    public static boolean checkHasPositivePoint(int[] x, int[] y) {
        return (x[0] > 0) && (y[0] > 0) && (x[0] > x[3]) && (y[0] > y[1]);
    }

    /**
     * Этот метод вычисляет площадь.
     * @param x - координата горизотнальной оси.
     * @param y - координата вертикальной оси.
     * @return возвращает строку результата нахождения площади.
     */
    public static String getSquare(int[] x, int[] y) {
        int horizontalSide;
        int verticalSide;
        int square;

        if (x[3] < 0) {
            horizontalSide = x[0];
        } else horizontalSide = x[0] - x[3];
        if (y[1] < 0) {
            verticalSide = y[0];
        } else verticalSide = y[0] - y[1];

        square = horizontalSide * verticalSide;
        return "Площадь части прямоугольника, расположенной " +
                "в 1-й координатной четверти = " + square;
    }

    /**
     * Это основной метод, использующий метод {@code checkItIsRectangle},
     * {@code checkHasPositivePoint} и {@code getSquare}.
     * @param args не используется.
     * @throws FileNotFoundException - исключение при не нахождении файла.
     * @see FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("resultExercise4.txt");
        Scanner input = new Scanner(System.in);
        int[] horizontalCoordinate = new int[4];
        int[] verticalCoordinate = new int[4];
        String variableForOutput;

        System.out.println("Вводите координаты с учётом того, что "
                + "первая точка соответствует " + "\n" + "верхней правой "
                + "вершине прямоугольника, а остальные - по часовой стрелке.");
        for (int i = 0; i < 4; i++) {
            System.out.println("Введите коортинаты " + (i + 1)
                    + " точки (x, затем y): ");
            horizontalCoordinate[i] = input.nextInt();
            verticalCoordinate[i] = input.nextInt();
        }
        if (checkItIsRectangle(horizontalCoordinate, verticalCoordinate)) {
            if (checkHasPositivePoint(horizontalCoordinate,
                    verticalCoordinate)) {
                variableForOutput = getSquare(horizontalCoordinate,
                        verticalCoordinate);
            } else {
                variableForOutput = "Правый верхний угол не лежит в "
                        + "1-й четверти!";
            }
        } else {
            variableForOutput = "Это не прямоугольник!";
        }
        System.out.println(variableForOutput);
        file.write(variableForOutput);
        file.close();
    }
}

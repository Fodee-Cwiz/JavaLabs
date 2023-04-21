package exercise6;

import java.io.*;
import java.util.Scanner;

/**
 * Это программа, которая по номеру дня в году выводит число и месяц в
 * общепринятой форме.
 * @author Ilya Polshchikov
 * @since 1.0
 */
public class NumberDayInYear {
    /**
     * Этот метод по номеру дня в году рассчитывает число и месяц.
     * @param numberDay - номер дня в году.
     * @return возвращает строку с месяцем и днем этого месяца.
     * Если такого дня в году не существует, возвращает строку с ошибкой.
     */
    public static String getDayAndMonth(int numberDay) {
        int[] dayInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] monthName = {"января", "февраля", "марта", "апреля",
                "мая", "июня", "июля", "августа", "сентября", "октября",
                "ноября", "декабря"};
        int i;

        if ((numberDay > 0) && (numberDay < 366)) {
            for (i = 0; numberDay > dayInMonth[i]; i++) {
                numberDay -= dayInMonth[i];
            }
            return numberDay + " " + monthName[i];
        }
        return numberDay + " дня в не високосном году нет!";
    }

    /**
     * Это основной метод, использующий метод {@code getDayAndMonth}.
     * @param args не используется.
     * @throws FileNotFoundException - исключение при не нахождении файла.
     * @see FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter file = new PrintWriter("resultExercise6.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите номер дня в году: ");
        int dayInYear = scanner.nextInt();

        System.out.println(getDayAndMonth(dayInYear));
        file.write(getDayAndMonth(dayInYear));
        file.close();
    }
}

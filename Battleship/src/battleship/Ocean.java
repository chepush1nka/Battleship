package battleship;

/**
 * Класс океана(игрового поля) со свойствами <b>width</b>,
 * * <b>height</b> и <b>ocean</b>.
 */
public class Ocean {
    /**
     * Статическое поле, содержащее ширину океана.
     */
    public static int width;
    /**
     * Статическое поле, содержащее длину океана.
     */
    public static int height;
    /**
     * Статическое поле, где содержится модель океана, которую видит пользователь.
     */
    public static char[][] ocean;

    /**
     * Инициализация видимого океана.
     */
    static void createOcean() {
        ocean = new char[height][width];
        for (int i = 0; height > i; i++) {
            for (int j = 0; width > j; j++) {
                ocean[i][j] = '░';
            }
        }
        printOcean();
    }

    /**
     * Метод, выводящий в консоль вид океана.
     */
    static void printOcean() {
        System.out.print("|   ");
        for (int j = 1; width >= j; j++) {
            if (j > 9) {
                System.out.print("| " + j);
            } else {
                System.out.print("| " + j + " ");
            }
        }
        System.out.print("\n");
        for (int i = 0; height > i; i++) {
            int c = i + 1;
            if (c > 9) {
                System.out.print("| " + c);
            } else {
                System.out.print("| " + c + " ");
            }
            for (int j = 0; width > j; j++) {
                System.out.print("| ");
                if (ocean[i][j] == '░') {
                    System.out.print((char) 27 + "[34m" + ocean[i][j] + (char) 27 + "[0m");
                } else {
                    System.out.print((char) 27 + "[31m" + ocean[i][j] + (char) 27 + "[0m");
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}

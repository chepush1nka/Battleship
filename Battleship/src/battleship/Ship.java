package battleship;

import java.awt.*;

/**
 * Родительский класс, описывающий корабли со свойствами <b>health</b>,
 * * <b>devOcean</b> и <b>location</b>.
 */
public class Ship {
    /**
     * Поле, содержащее количество ячеек корабля, в которые не стреляли.
     */
    int health;
    /**
     * Статическое поле, содержащее модель океана, которую использует программа и не видит игрок.
     */
    public static int[][] devOcean;
    /**
     * Поле, содержащее координаты корабля в океане(на поле).
     */
    Point[] location;

    /**
     * Метод создает модель океана, которую использует программа и не видит игрок.
     */
    static void createDevOcean() {
        Ship.devOcean = new int[Ocean.height + 2][Ocean.width + 2];
        for (int i = 0; Ocean.height + 2 > i; i++) {
            for (int j = 0; Ocean.width + 2 > j; j++) {
                // Инициализируем границы океана единицей и пустые поля нулями.
                if (i == 0 || j == 0 || i == Ocean.height + 1 || j == Ocean.width + 1) {
                    devOcean[i][j] = 1;
                } else {
                    devOcean[i][j] = 0;
                }
            }
        }
        // printDevOcean();
    }

    /**
     * Генерируем x-значение для точки в океане.
     */
    public int randX() {
        return (int) ((Math.random() * (Ocean.height - 1)) + 1);
    }

    /**
     * Генерируем y-значение для точки в океане.
     */
    public int randY() {
        return (int) ((Math.random() * (Ocean.width - 1)) + 1);
    }

    /**
     * Метод проверяет рандомно сгенерированную точку на то, чтобы она не перекрывала или
     * не находилась по соседству с уже сгенерированными кораблями.
     *
     * @param point - сгенерированная точка.
     */
    static boolean checkPoint(Point point) {
        if (devOcean[point.x][point.y] == 0 &&
                devOcean[point.x + 1][point.y] < 3 && devOcean[point.x - 1][point.y] < 3 &&
                devOcean[point.x + 1][point.y - 1] < 3 && devOcean[point.x - 1][point.y - 1] < 3 &&
                devOcean[point.x + 1][point.y + 1] < 3 && devOcean[point.x - 1][point.y + 1] < 3 &&
                devOcean[point.x][point.y - 1] < 3 && devOcean[point.x][point.y + 1] < 3) {
            return true;
        }
        return false;
    }

    /**
     * Метод добавляет новый корабль в схему океана, используемую программой.
     *
     * @param location - точки расположения корабля.
     */
    static void changeDevOcean(Point[] location) {
        // Инициализируем двойками поля вокруг корабля.
        for (int i = 0; location.length > i; i++) {
            devOcean[location[i].x + 1][location[i].y] = 2;
            devOcean[location[i].x - 1][location[i].y] = 2;
            devOcean[location[i].x + 1][location[i].y - 1] = 2;
            devOcean[location[i].x - 1][location[i].y - 1] = 2;
            devOcean[location[i].x + 1][location[i].y + 1] = 2;
            devOcean[location[i].x - 1][location[i].y + 1] = 2;
            devOcean[location[i].x][location[i].y - 1] = 2;
            devOcean[location[i].x][location[i].y + 1] = 2;
        }
        // Инициализируем тройками расположение корабля.
        for (int i = 0; location.length > i; i++) {
            devOcean[location[i].x][location[i].y] = 3;
        }
    }
}

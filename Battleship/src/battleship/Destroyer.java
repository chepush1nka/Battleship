package battleship;

import java.awt.*;

/**
 * Класс 2-х клеточного корабля, наследующегося от класса Ship.
 */
public class Destroyer extends Ship {
    /**
     * Конструктор, инициализация полей.
     */
    public Destroyer() {
        health = 2;
        for (int i = 0; 255 > i; i++) {
            location = tryPutDestroyer();
            if (location != null) {
                changeDevOcean(location);
                break;
            }
        }
        if (location == null) {
            UserSettings.againOrExit();
        }
    }

    /**
     * Попытка сгенерировать корабль из случайной точки.
     */
    Point[] tryPutDestroyer() {
        Point point = new Point(randX(), randY());
        Point[] ans = new Point[2];
        if (checkPoint(point)) {
            String check = checkOneStraight(point);
            if (!check.equals("no")) {
                ans = putOne(check, point);
                return ans;
            }
            return null;
        }
        return null;
    }

    /**
     * Проверка на то, можно ли расположить еще одну точку над, под, или по бокам
     * от сгенерированной точки.
     *
     * @param point - сгенерированная точка.
     */
    static String checkOneStraight(Point point) {
        if (devOcean[point.x][point.y + 1] == 0) {
            return "right";
        }
        if (devOcean[point.x - 1][point.y] == 0) {
            return "up";
        }
        if (devOcean[point.x + 1][point.y] == 0) {
            return "down";
        }
        if (devOcean[point.x][point.y - 1] == 0) {
            return "left";
        }
        return "no";
    }

    /**
     * Проверка на то, можно ли расположить одну точку над, под, или по бокам
     * от сгенерированной точки и еще одну с противоположной стороны.
     *
     * @param point - сгенерированная точка.
     * @param check - значение проверочного метода.
     */
    static Point[] putOne(String check, Point point) {
        Point[] ans = new Point[2];
        switch (check) {
            case "up" -> {
                ans[0] = point;
                ans[1] = new Point(point.x - 1, point.y);
            }
            case "down" -> {
                ans[0] = point;
                ans[1] = new Point(point.x + 1, point.y);
            }
            case "left" -> {
                ans[0] = point;
                ans[1] = new Point(point.x, point.y - 1);
            }
            case "right" -> {
                ans[0] = point;
                ans[1] = new Point(point.x, point.y + 1);
            }
        }
        return ans;
    }
}
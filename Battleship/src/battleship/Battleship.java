package battleship;

import java.awt.*;

/**
 * Класс 4-х клеточного корабля, наследующегося от класса Ship.
 */
public class Battleship extends Ship {
    /**
     * Конструктор, инициализация полей.
     */
    public Battleship() {
        health = 4;
        for (int i = 0; 40 > i; i++) {
            location = tryPutBattleship();
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
    Point[] tryPutBattleship() {
        Point point = new Point(randX(), randY());
        Point[] ans = new Point[4];
        if (checkPoint(point)) {
            String check = checkThreeStraight(point);
            if (!check.equals("no")) {
                ans = putThree(check, point);
                return ans;
            }
            check = checkTwoStraight(point);
            if (!check.equals("no")) {
                ans = putTwo(check, point);
                return ans;
            }
            return null;
        }

        return null;
    }

    /**
     * Проверка на то, можно ли расположить еще три точки над, под, или по бокам
     * от сгенерированной точки.
     *
     * @param point - сгенерированная точка.
     */
    static String checkThreeStraight(Point point) {
        if (devOcean[point.x][point.y - 1] == 0 && devOcean[point.x][point.y - 2] == 0 &&
                devOcean[point.x][point.y - 3] == 0) {
            return "left";
        }
        if (devOcean[point.x - 1][point.y] == 0 && devOcean[point.x - 2][point.y] == 0 &&
                devOcean[point.x - 3][point.y] == 0) {
            return "up";
        }
        if (devOcean[point.x][point.y + 1] == 0 && devOcean[point.x][point.y + 2] == 0 &&
                devOcean[point.x][point.y + 3] == 0) {
            return "right";
        }
        if (devOcean[point.x + 1][point.y] == 0 && devOcean[point.x + 2][point.y] == 0 &&
                devOcean[point.x + 3][point.y] == 0) {
            return "down";
        }
        return "no";
    }

    /**
     * Проверка на то, можно ли расположить еще две точки над, под, или по бокам
     * от сгенерированной точки и еще одну с противоположной стороны.
     *
     * @param point - сгенерированная точка.
     */
    static String checkTwoStraight(Point point) {
        if (devOcean[point.x - 1][point.y] == 0 && devOcean[point.x - 2][point.y] == 0 &&
                devOcean[point.x + 1][point.y] == 0) {
            return "up";
        }
        if (devOcean[point.x][point.y - 1] == 0 && devOcean[point.x][point.y - 2] == 0 &&
                devOcean[point.x][point.y + 1] == 0) {
            return "left";
        }
        if (devOcean[point.x][point.y + 1] == 0 && devOcean[point.x][point.y + 2] == 0 &&
                devOcean[point.x][point.y - 1] == 0) {
            return "right";
        }
        if (devOcean[point.x + 1][point.y] == 0 && devOcean[point.x + 2][point.y] == 0 &&
                devOcean[point.x - 1][point.y] == 0) {
            return "down";
        }
        return "no";
    }

    /**
     * Метод устанавливает по переданному значению проверочных методов 4 точки в океане.
     *
     * @param point - сгенерированная точка.
     * @param check - значение проверочного метода.
     */
    static Point[] putThree(String check, Point point) {
        Point[] ans = new Point[4];
        switch (check) {
            case "up" -> {
                ans[0] = point;
                ans[1] = new Point(point.x - 1, point.y);
                ans[2] = new Point(point.x - 2, point.y);
                ans[3] = new Point(point.x - 3, point.y);
            }
            case "down" -> {
                ans[0] = point;
                ans[1] = new Point(point.x + 1, point.y);
                ans[2] = new Point(point.x + 2, point.y);
                ans[3] = new Point(point.x + 3, point.y);
            }
            case "left" -> {
                ans[0] = point;
                ans[1] = new Point(point.x, point.y - 1);
                ans[2] = new Point(point.x, point.y - 2);
                ans[3] = new Point(point.x, point.y - 3);
            }
            case "right" -> {
                ans[0] = point;
                ans[1] = new Point(point.x, point.y + 1);
                ans[2] = new Point(point.x, point.y + 2);
                ans[3] = new Point(point.x, point.y + 3);
            }
        }
        return ans;
    }

    /**
     * Метод устанавливает по переданному значению проверочных методов 4 точки в океане
     * (одна точка сгенерирована, две с одной стороны и одна с другой).
     *
     * @param point - сгенерированная точка.
     * @param check - значение проверочного метода.
     */
    static Point[] putTwo(String check, Point point) {
        Point[] ans = new Point[4];
        switch (check) {
            case "up" -> {
                ans[0] = new Point(point.x + 1, point.y);
                ans[1] = point;
                ans[2] = new Point(point.x - 1, point.y);
                ans[3] = new Point(point.x - 2, point.y);
            }
            case "down" -> {
                ans[0] = new Point(point.x - 1, point.y);
                ans[1] = point;
                ans[2] = new Point(point.x + 1, point.y);
                ans[3] = new Point(point.x + 2, point.y);
            }
            case "left" -> {
                ans[0] = new Point(point.x, point.y + 1);
                ans[1] = point;
                ans[2] = new Point(point.x, point.y - 1);
                ans[3] = new Point(point.x, point.y - 2);
            }
            case "right" -> {
                ans[0] = new Point(point.x, point.y - 1);
                ans[1] = point;
                ans[2] = new Point(point.x, point.y + 1);
                ans[3] = new Point(point.x, point.y + 2);
            }
        }
        return ans;
    }
}

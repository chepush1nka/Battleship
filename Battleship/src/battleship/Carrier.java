package battleship;


import java.awt.*;

/**
 * Класс 5-и клеточного корабля, наследующегося от класса Ship.
 */
public class Carrier extends Ship {
    /**
     * Конструктор, инициализация полей.
     */
    public Carrier() {
        health = 5;
        for (int i = 0; 35 > i; i++) {
            location = tryPutCarrier();
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
    Point[] tryPutCarrier() {
        Point point = new Point(randX(), randY());
        Point[] ans = new Point[5];
        if (checkPoint(point)) {
            String check = checkFourStraight(point);
            if (!check.equals("no")) {
                ans = putFour(check, point);
                return ans;
            }
            check = checkThreeStraight(point);
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
     * Проверка на то, можно ли расположить еще четыре точки над, под, или по бокам
     * от сгенерированной точки.
     *
     * @param point - сгенерированная точка.
     */
    static String checkFourStraight(Point point) {
        if (devOcean[point.x - 1][point.y] == 0 && devOcean[point.x - 2][point.y] == 0 &&
                devOcean[point.x - 3][point.y] == 0 && devOcean[point.x - 4][point.y] == 0) {
            return "up";
        }
        if (devOcean[point.x + 1][point.y] == 0 && devOcean[point.x + 2][point.y] == 0 &&
                devOcean[point.x + 3][point.y] == 0 && devOcean[point.x + 4][point.y] == 0) {
            return "down";
        }
        if (devOcean[point.x][point.y + 1] == 0 && devOcean[point.x][point.y + 2] == 0 &&
                devOcean[point.x][point.y + 3] == 0 && devOcean[point.x][point.y + 4] == 0) {
            return "right";
        }
        if (devOcean[point.x][point.y - 1] == 0 && devOcean[point.x][point.y - 2] == 0 &&
                devOcean[point.x][point.y - 3] == 0 && devOcean[point.x][point.y - 4] == 0) {
            return "left";
        }
        return "no";
    }

    /**
     * Проверка на то, можно ли расположить еще три точки над, под, или по бокам
     * от сгенерированной точки и еще одну с противоположной стороны.
     *
     * @param point - сгенерированная точка.
     */
    static String checkThreeStraight(Point point) {
        if (devOcean[point.x - 1][point.y] == 0 && devOcean[point.x - 2][point.y] == 0 &&
                devOcean[point.x - 3][point.y] == 0 && devOcean[point.x + 1][point.y] == 0) {
            return "up";
        }
        if (devOcean[point.x][point.y + 1] == 0 && devOcean[point.x][point.y + 2] == 0 &&
                devOcean[point.x][point.y + 3] == 0 && devOcean[point.x][point.y - 1] == 0) {
            return "right";
        }
        if (devOcean[point.x + 1][point.y] == 0 && devOcean[point.x + 2][point.y] == 0 &&
                devOcean[point.x + 3][point.y] == 0 && devOcean[point.x - 1][point.y] == 0) {
            return "down";
        }
        if (devOcean[point.x][point.y - 1] == 0 && devOcean[point.x][point.y - 2] == 0 &&
                devOcean[point.x][point.y - 3] == 0 && devOcean[point.x][point.y + 1] == 0) {
            return "left";
        }
        return "no";
    }

    /**
     * Проверка на то, можно ли расположить еще две точки над, под, или по бокам
     * от сгенерированной точки и еще две с противоположной стороны.
     *
     * @param point - сгенерированная точка.
     */
    static String checkTwoStraight(Point point) {
        if (devOcean[point.x - 1][point.y] == 0 && devOcean[point.x - 2][point.y] == 0 &&
                devOcean[point.x + 1][point.y] == 0 && devOcean[point.x + 2][point.y] == 0) {
            return "up-down";
        }
        if (devOcean[point.x][point.y - 1] == 0 && devOcean[point.x][point.y - 2] == 0 &&
                devOcean[point.x][point.y + 1] == 0 && devOcean[point.x][point.y + 2] == 0) {
            return "left-right";
        }
        return "no";
    }

    /**
     * Метод устанавливает по переданному значению проверочных методов 5 точек в океане.
     *
     * @param point - сгенерированная точка.
     * @param check - значение проверочного метода.
     */
    static Point[] putFour(String check, Point point) {
        Point[] ans = new Point[5];
        switch (check) {
            case "up" -> {
                ans[0] = point;
                ans[1] = new Point(point.x - 1, point.y);
                ans[2] = new Point(point.x - 2, point.y);
                ans[3] = new Point(point.x - 3, point.y);
                ans[4] = new Point(point.x - 4, point.y);
            }
            case "down" -> {
                ans[0] = point;
                ans[1] = new Point(point.x + 1, point.y);
                ans[2] = new Point(point.x + 2, point.y);
                ans[3] = new Point(point.x + 3, point.y);
                ans[4] = new Point(point.x + 4, point.y);
            }
            case "left" -> {
                ans[0] = point;
                ans[1] = new Point(point.x, point.y - 1);
                ans[2] = new Point(point.x, point.y - 2);
                ans[3] = new Point(point.x, point.y - 3);
                ans[4] = new Point(point.x, point.y - 4);
            }
            case "right" -> {
                ans[0] = point;
                ans[1] = new Point(point.x, point.y + 1);
                ans[2] = new Point(point.x, point.y + 2);
                ans[3] = new Point(point.x, point.y + 3);
                ans[4] = new Point(point.x, point.y + 4);
            }
        }
        return ans;
    }

    /**
     * Метод устанавливает по переданному значению проверочных методов 5 точек в океане.
     * (Три с одной стороны и одну с другой от сгенерированной)
     *
     * @param point - сгенерированная точка.
     * @param check - значение проверочного метода.
     */
    static Point[] putThree(String check, Point point) {
        Point[] ans = new Point[5];
        switch (check) {
            case "up" -> {
                ans[0] = new Point(point.x + 1, point.y);
                ans[1] = point;
                ans[2] = new Point(point.x - 1, point.y);
                ans[3] = new Point(point.x - 2, point.y);
                ans[4] = new Point(point.x - 3, point.y);
            }
            case "down" -> {
                ans[0] = new Point(point.x - 1, point.y);
                ans[1] = point;
                ans[2] = new Point(point.x + 1, point.y);
                ans[3] = new Point(point.x + 2, point.y);
                ans[4] = new Point(point.x + 3, point.y);
            }
            case "left" -> {
                ans[0] = new Point(point.x, point.y + 1);
                ans[1] = point;
                ans[2] = new Point(point.x, point.y - 1);
                ans[3] = new Point(point.x, point.y - 2);
                ans[4] = new Point(point.x, point.y - 3);
            }
            case "right" -> {
                ans[0] = new Point(point.x, point.y - 1);
                ans[1] = point;
                ans[2] = new Point(point.x, point.y + 1);
                ans[3] = new Point(point.x, point.y + 2);
                ans[4] = new Point(point.x, point.y + 3);
            }
        }
        return ans;
    }

    /**
     * Метод устанавливает по переданному значению проверочных методов 5 точек в океане.
     * (Две с каждой стороны от сгенерированной)
     *
     * @param point - сгенерированная точка.
     * @param check - значение проверочного метода.
     */
    static Point[] putTwo(String check, Point point) {
        Point[] ans = new Point[5];
        switch (check) {
            case "up-down" -> {
                ans[0] = new Point(point.x + 2, point.y);
                ans[1] = new Point(point.x + 1, point.y);
                ans[2] = point;
                ans[3] = new Point(point.x - 1, point.y);
                ans[4] = new Point(point.x - 2, point.y);
            }
            case "left-right" -> {
                ans[0] = new Point(point.x, point.y + 2);
                ans[1] = new Point(point.x, point.y + 1);
                ans[2] = point;
                ans[3] = new Point(point.x, point.y - 1);
                ans[4] = new Point(point.x, point.y - 2);
            }
        }
        return ans;
    }
}

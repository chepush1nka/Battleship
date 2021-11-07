package battleship;

import java.awt.*;

/**
 * Класс 1-клеточного корабля, наследующегося от класса Ship.
 */
public class Submarine extends Ship {
    /**
     * Конструктор, инициализация полей.
     */
    public Submarine() {
        health = 1;
        for (int i = 0; 250 > i; i++) {
            location = tryPutSubmarine();
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
     * Попытка сгенерировать корабль в случайной точке.
     */
    Point[] tryPutSubmarine() {
        Point point = new Point(randX(), randY());
        Point[] ans = new Point[1];
        if (checkPoint(point)) {
            ans[0] = point;
            return ans;
        }
        return null;
    }
}
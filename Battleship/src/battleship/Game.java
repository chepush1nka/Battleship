package battleship;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Класс, описывающий ход игры, со свойствами <b>ships</b> и <b>shots</b>.
 */
public class Game {
    /**
     * Статическое поле, содержащее все корабли в океане.
     */
    static ArrayList<Ship> ships = new ArrayList<Ship>();
    /**
     * Статическое поле, содержащее все произведенные выстрелы.
     */
    static ArrayList<Point> shots = new ArrayList<Point>();

    /**
     * Основной метод игры, запрашивающий координаты выстрела и производящий его.
     *
     * @param text - строка для вывода.
     */
    static void playGame(String text) {
        Ocean.printOcean();
        System.out.println((char) 27 + "[34m" + text + (char) 27 + "[0m");
        if (ships.size() == 0) {
            ExitInfo();
        }
        System.out.println((char) 27 + "[36mEnter the coordinates of your shot through the space bar" + (char) 27 + "[0m");
        System.out.println((char) 27 + "[37mfirst in height, then in width(Example: 1 4)" + (char) 27 + "[0m");
        if (UserSettings.torpedoes && UserSettings.torpedoesCount > 0) {
            printTorpedoInfo();
        }
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] coordinates = input.split(" ");
        if (coordinates.length == 2 && UserSettings.isNumeric(coordinates[0]) &&
                UserSettings.isNumeric(coordinates[1])) {
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            if (x > 0 && y > 0 && x <= Ocean.height && y <= Ocean.width) {
                sortShot(x, y);
            } else {
                playGame("TRY AGAIN");
            }
        } else if (coordinates.length == 3 && UserSettings.isNumeric(coordinates[1]) &&
                UserSettings.isNumeric(coordinates[2]) && Objects.equals(coordinates[0], "T")) {
            int x = Integer.parseInt(coordinates[1]);
            int y = Integer.parseInt(coordinates[2]);
            if (x > 0 && y > 0 && x <= Ocean.height && y <= Ocean.width) {
                sortTorpedoShot(x, y);
            } else {
                playGame("TRY AGAIN");
            }
        } else {
            playGame("TRY AGAIN");
        }
    }

    /**
     * Метод для вывода информации о наличии и количестве торпед при включенном режиме игры с ними.
     */
    static void printTorpedoInfo() {
        System.out.println((char) 27 + "[35mThere are also " + UserSettings.torpedoesCount + " torpedoes available to you.\n" +
                "To shoot them, enter the prefix \"T\" before the coordinates(Example: T 1 4)" + (char) 27 + "[0m");
    }

    /**
     * Метод сортировки обычного выстрела (мимо, попадание или разрушение).
     *
     * @param x - точка по высоте.
     * @param y - точка по ширине.
     */
    static void sortShot(int x, int y) {
        Point shot = new Point(x, y);
        if (shots.contains(shot)) {
            playGame("TRY AGAIN");
        } else if (Ship.devOcean[x][y] == 0 || Ship.devOcean[x][y] == 2) {
            shots.add(shot);
            Ocean.ocean[x - 1][y - 1] = '◯';
            playGame("MISSED");
        } else if (Ship.devOcean[x][y] == 3) {
            shots.add(shot);
            Ocean.ocean[x - 1][y - 1] = 'X';
            Ship ship = new Ship();
            for (int i = 0; ships.size() > i; i++) {
                ship = ships.get(i);
                for (int j = 0; ship.location.length > j; j++) {
                    if (ship.location[j].x == x && ship.location[j].y == y) {
                        ships.get(i).health -= 1;
                        i += ships.size();
                        j += 6;
                    }
                }
            }
            if (ship.health == 0) {
                ships.remove(ship);
                for (int k = 0; ship.location.length > k; k++) {
                    Ocean.ocean[ship.location[k].x - 1][ship.location[k].y - 1] = '█';
                }
                playGame("YOU JUST HAVE SUNK A " + (ship.getClass().getName()).toUpperCase());
            } else {
                playGame("HURT");
            }
        }
    }

    /**
     * Метод сортировки выстрела торпедой (мимо или разрушение).
     *
     * @param x - точка по высоте.
     * @param y - точка по ширине.
     */
    static void sortTorpedoShot(int x, int y) {
        Point shot = new Point(x, y);
        if (shots.contains(shot)) {
            playGame("TRY AGAIN");
        } else if (Ship.devOcean[x][y] == 0 || Ship.devOcean[x][y] == 2) {
            shots.add(shot);
            UserSettings.torpedoesCount -= 1;
            Ocean.ocean[x - 1][y - 1] = '◯';
            playGame("MISSED");
        } else if (Ship.devOcean[x][y] == 3) {
            shots.add(shot);
            UserSettings.torpedoesCount -= 1;
            Ship ship = new Ship();
            for (int i = 0; ships.size() > i; i++) {
                ship = ships.get(i);
                for (int j = 0; ship.location.length > j; j++) {
                    if (ship.location[j].x == x && ship.location[j].y == y) {
                        i += ships.size();
                        j += 6;
                    }
                }
            }
            ships.remove(ship);
            for (int k = 0; ship.location.length > k; k++) {
                Ocean.ocean[ship.location[k].x - 1][ship.location[k].y - 1] = '█';
            }
            playGame("YOU JUST HAVE SUNK A " + (ship.getClass().getName()).toUpperCase());
        }
    }

    /**
     * Метод, генерирующий корабли в океане и начинающий игру.
     *
     * @param numShip - заданное количество кораблей.
     */
    static void generateShips(int[] numShip) {
        for (int i = 0; numShip.length > i; i++) {
            for (int j = 0; numShip[i] > j; j++) {
                switch (i) {
                    case 0 -> {
                        ships.add(new Carrier());
                    }
                    case 1 -> {
                        ships.add(new Battleship());
                    }
                    case 2 -> {
                        ships.add(new Cruiser());
                    }
                    case 3 -> {
                        ships.add(new Destroyer());
                    }
                    case 4 -> {
                        ships.add(new Submarine());
                    }
                }
            }
        }
        if (UserSettings.torpedoes && ships.size() < UserSettings.torpedoesCount) {
            UserSettings.torpedoesCount = ships.size();
        }
        playGame("THE BATTLE HAS BEGUN!");
    }

    /**
     * Метод, выводящий информацию о прошедшей игре и завершающий ее.
     */
    static void ExitInfo() {
        System.out.println((char) 27 + "[38mGame over!" + (char) 27 + "[0m");
        System.out.println((char) 27 + "[38mTotal shots fired: " + shots.size() + (char) 27 + "[0m");
        System.exit(0);
    }
}

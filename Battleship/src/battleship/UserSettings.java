package battleship;

import java.util.Scanner;

/**
 * Класс настроек пользователя со свойствами <b>torpedoes</b>,
 * <b>recovery</b> и <b>torpedoesCount</b>.
 */
public class UserSettings {
    /**
     * Поле, отвечающее за модификацию игры с торпедами.
     */
    static boolean torpedoes;
    /**
     * Поле, отвечающее за модификацию игры с восстановлением кораблей.
     */
    static boolean recovery;
    /**
     * Поле, отвечающее за количество торпед
     */
    static int torpedoesCount;

    /**
     * Выбор модификаций игры и установка параметров на размеры поля, количество кораблей.
     */
    static void gameMods() {
        System.out.println((char) 27 + "[34mIn this game you can choose up to two modifications" + (char) 27 + "[0m");
        System.out.println((char) 27 + "[36m1) Play the standard game" + (char) 27 + "[0m");
        System.out.println((char) 27 + "[36m2) Choose modifications" + (char) 27 + "[0m");
        Scanner console = new Scanner(System.in);
        if (console.hasNextInt()) {
            int choose = console.nextInt();
            if (choose == 1) {
                UserSettings.torpedoes = false;
                UserSettings.recovery = false;
                oceanSettings();
                Ocean.createOcean();
                shipsSettings();
            } else if (choose == 2) {
                torpedoMod();
                recoveryMod();
                oceanSettings();
                Ocean.createOcean();
                shipsSettings();
            } else {
                System.out.println((char) 27 + "[31mplease enter the option number(1 or 2)" + (char) 27 + "[0m");
                gameMods();
            }
        } else {
            System.out.println((char) 27 + "[31mplease enter the option number" + (char) 27 + "[0m");
            gameMods();
        }
    }

    /**
     * При выборе режима торпед, этот метод отвечает за их число у игрока.
     * Если игрок укажет число, которое больше количества кораблей на поле, то
     * оно автоматически будет приравнено к числу кораблей далее по ходу игры.
     */
    static void torpedoMod() {
        System.out.println((char) 27 + "[36mEnter the desired number of torpedoes" + (char) 27 + "[0m");
        System.out.println((char) 27 + "[37menter 0 if you want to continue without them" + (char) 27 + "[0m");
        Scanner console = new Scanner(System.in);
        if (console.hasNextInt()) {
            int torpedoNumber = console.nextInt();
            if (torpedoNumber == 0) {
                UserSettings.torpedoes = false;
            } else if (torpedoNumber < 100 && torpedoNumber > 0) {
                torpedoesCount = torpedoNumber;
                UserSettings.torpedoes = true;
            } else {
                System.out.println((char) 27 + "[31mtry entering a 0<number<100" + (char) 27 + "[0m");
                torpedoMod();
            }
        } else {
            System.out.println((char) 27 + "[31mplease enter number" + (char) 27 + "[0m");
            torpedoMod();
        }
    }

    /**
     * Метод предлагает игроку включить режим восстановления кораблей и записывает его ответ.
     */
    static void recoveryMod() {
        System.out.println((char) 27 + "[36mDo you want to play a game with recovering ships?" + (char) 27 + "[0m");
        System.out.println((char) 27 + "[36m1) Yes" + (char) 27 + "[0m");
        System.out.println((char) 27 + "[36m2) No" + (char) 27 + "[0m");
        Scanner console = new Scanner(System.in);
        if (console.hasNextInt()) {
            int choose = console.nextInt();
            if (choose == 1) {
                UserSettings.recovery = true;
            } else if (choose == 2) {
                UserSettings.recovery = false;
            } else {
                System.out.println((char) 27 + "[31mplease enter the option number(1 or 2)" + (char) 27 + "[0m");
                recoveryMod();
            }
        } else {
            System.out.println((char) 27 + "[31mplease enter the option number" + (char) 27 + "[0m");
            recoveryMod();
        }
    }

    /**
     * Метод перенаправляет в методы для установки ширины и высоты поля.
     */
    static void oceanSettings() {
        setOceanWidth();
        setOceanHeight();
    }

    /**
     * Устанавливаем ширину поля.
     */
    static void setOceanWidth() {
        System.out.println((char) 27 + "[36mEnter the width of the ocean(from 10 to 30)" + (char) 27 + "[0m");
        Scanner console = new Scanner(System.in);
        if (console.hasNextInt()) {
            int choose = console.nextInt();
            if (choose > 9 && choose < 31) {
                Ocean.width = choose;
            } else {
                System.out.println((char) 27 + "[31mplease enter a number from 10 to 30" + (char) 27 + "[0m");
                setOceanWidth();
            }
        } else {
            System.out.println((char) 27 + "[31mplease enter a number" + (char) 27 + "[0m");
            setOceanWidth();
        }
    }

    /**
     * Устанавливаем высоту поля.
     */
    static void setOceanHeight() {
        System.out.println((char) 27 + "[36mEnter the height of the ocean(from 10 to 30)" + (char) 27 + "[0m");
        Scanner console = new Scanner(System.in);
        if (console.hasNextInt()) {
            int choose = console.nextInt();
            if (choose > 9 && choose < 31) {
                Ocean.height = choose;
            } else {
                System.out.println((char) 27 + "[31mplease enter a number from 10 to 30" + (char) 27 + "[0m");
                setOceanHeight();
            }
        } else {
            System.out.println((char) 27 + "[31mplease enter a number" + (char) 27 + "[0m");
            setOceanHeight();
        }
    }

    /**
     * Метод, устанавливающий количество кораблей каждого типа на поле.
     */
    static void shipsSettings() {
        Ship.createDevOcean();
        System.out.println((char) 27 + "[36mEnter the number of ships for each type " +
                "separated by a space(from five cells ship to one)" + (char) 27 + "[0m");
        System.out.println((char) 27 + "[37mExample: “0, 1, 2, 3, 4” would mean: " +
                "“no carrier, one battleship, two cruisers, three destroyers and four submarines”.)" + (char) 27 + "[0m");
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        String[] numbers = input.split(" ");
        if (numbers.length != 5) {
            againOrExit();
        } else {
            int[] numShip = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                if (isNumeric(numbers[i])) {
                    numShip[i] = Integer.parseInt(numbers[i]);
                } else {
                    sc.close();
                    againOrExit();
                }
            }
            /** При неудачной генерации предлагается ввести данные еще раз, либо завершить игру. */
            Game.generateShips(numShip);
        }
    }

    /**
     * Метод для проверки введенной строки на то, является ли она числом.
     *
     * @param str - строка для проверки.
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Метод, после неудачной установки кораблей на поле, предлагает игроку либо
     * попробовать еще раз, либо завершить игру.
     */
    static void againOrExit() {
        System.out.println((char) 27 + "[31mit is impossible to place such a number of ships on the field (((("
                + (char) 27 + "[0m");
        System.out.println((char) 27 + "[36m1) Try again" + (char) 27 + "[0m");
        System.out.println((char) 27 + "[36m2) Exit" + (char) 27 + "[0m");
        Scanner console = new Scanner(System.in);
        String chooseStr = new String();
        chooseStr = console.nextLine();
        if (isNumeric(chooseStr)) {
            int choose = Integer.parseInt(chooseStr);
            if (choose == 1) {
                Ship.createDevOcean();
                Game.ships.clear();
                shipsSettings();
            } else if (choose == 2) {
                System.exit(0);
            } else {
                System.out.println((char) 27 + "[31mplease enter the option number(1 or 2)" + (char) 27 + "[0m");
            }
        } else {
            System.out.println((char) 27 + "[31mplease enter the option number" + (char) 27 + "[0m");
            againOrExit();
        }
    }

}

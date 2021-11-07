package battleship;


import java.awt.*;

public class Main {
    /**
     * Начало работы программы
     */
    public static void main(String[] args) {
        System.out.println((char) 27 + "[36mHello, sailor! " + (char) 27 + "[0m");
        System.out.println((char) 27 + "[34mAre you ready for a deadly sea battle?\n " + (char) 27 + "[0m");
        UserSettings.gameMods();
    }
}

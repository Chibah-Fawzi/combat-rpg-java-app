package org.combat.game;

import java.util.List;
import java.util.Scanner;

public class GameInfo {
    public record PlayerNames(String player1, String player2) {}
    final static Scanner scanner = new Scanner(System.in);

    public static void printIntro() {
        System.out.println(
                "==================================================\n" +
                        "              WELCOME TO JAVA RPG COMBAT\n" +
                        "=================================================="
        );
    }

    public static PlayerNames getPlayerNames() {
        System.out.print("Player 1, please enter your name: ");
        Player player1 = new Player(scanner.nextLine());
        System.out.print("Player 2, please enter your name: ");
        Player player2 = new Player(scanner.nextLine());

        return new PlayerNames(player1.name, player2.name);
    }

    public static void printWelcome(Player player1, Player player2) {
        System.out.println("================================================================================");
        System.out.println("Welcome: " + player1.name + " & " + player2.name);
        System.out.print(
                "==================================================\n" +
                        "               AVAILABLE CHAMPIONS\n" +
                        "==================================================\n"
        );
    }

    public static void printChampions(List<Champion> champions) {
        for (Champion champion : champions) {
            System.out.println(champion.getId() + " - " + champion.getName() + " - " + champion.getRole());
        }
    }
}

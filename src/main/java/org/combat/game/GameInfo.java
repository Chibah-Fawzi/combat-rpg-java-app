package org.combat.game;

import java.util.List;
import java.util.Scanner;

public class GameInfo {
    public record PlayerNames(String player1, String player2) {}
    final static Scanner scanner = new Scanner(System.in);

    public static void printChampionDetails(Champion champion) {
        if (champion == null) {
            System.out.println("Champion not found!");
            return;
        }

        System.out.println("==================================================");
        System.out.println("                CHAMPION DETAILS                  ");
        System.out.println("==================================================");
        System.out.println("ID:          " + champion.getId());
        System.out.println("Name:        " + champion.getName());
        System.out.println("Class:       " + champion.getUnitClass());
        System.out.println("Role:        " + champion.getRole());
        System.out.println("--------------------------------------------------");
        System.out.println("BASE STATS");
        System.out.println("Health:      " + champion.getBaseStats().getMaxHealth());
        System.out.println("Mana:        " + champion.getBaseStats().getMaxMana());
        System.out.println("Base Attack: " + champion.getBaseStats().getBaseAttack());
        System.out.println("Mana Regen:  +" + champion.getBaseStats().getManaRegenPerAttack() + " / attack");
        System.out.println("--------------------------------------------------");
        System.out.println("AUTO ATTACK");
        System.out.println("Name:        " + champion.getAutoAttack().getName());
        System.out.println("Type:        " + champion.getAutoAttack().getType());
        System.out.println("Multiplier:  " + champion.getAutoAttack().getDamageMultiplier() + "x");
        System.out.println("--------------------------------------------------");
        System.out.println("SPELLS");

        if (champion.getSpells() != null) {
            for (Spell spell : champion.getSpells()) {
                System.out.println("* " + spell.getName() + " [" + spell.getType() + "]");
                System.out.println("  Mana Cost: " + spell.getManaCost() + " | Cooldown: " + spell.getCooldownTurns() + " turns");
                System.out.println("  Base Damage: " + spell.getBaseDamage());
                System.out.println();
            }
        }

        System.out.println("==================================================");
    }

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

    public static void printCommands() {
        System.out.print(
                "--------------------------------------------------\n" +
                        "COMMANDS:\n" +
                        "- Type 'info <champName|champId>' to view full stats & spells\n" +
                        "- Type 'select <champName|champId>' to choose your champion\n" +
                        "- Type 'show' to review all available champions\n" +
                        "--------------------------------------------------\n"
        );
    }

    public static void printUserSelection(String player, Champion chosenChamp) {
        System.out.print(
                "==================================================\n" +
                        "           CHAMPION LOCKED IN!\n" +
                        "==================================================\n" +
                        player + " has chosen: " + chosenChamp.getName() + " -- " + chosenChamp.getRole() + "\n" +
                        "--------------------------------------------------\n" +
                        "\n" +
                        "> " + chosenChamp.getName() + " has been removed from the pool.\n"
        );
    }
}

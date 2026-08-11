package org.combat.game;

import java.util.List;
import java.util.Scanner;

public class GameInfo {
    public record PlayerNames(String player1, String player2) {}
    final static Scanner scanner = new Scanner(System.in);
    public enum GameStatus { DRAFT_PHASE, COMBAT_PHASE, GAME_OVER}
    public static Player startingPlayer;

    public GameInfo(Player startingPlayer) {
        this.startingPlayer = startingPlayer;
    }


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

        public static void getStartingPlayer(Player player1, Player player2) {
            new GameInfo(Math.random() < 0.5 ? player1 : player2);
        }

        static public PlayerNames getPlayerNames(){
            System.out.print("Player 1, please enter your name: ");
            Player player1 = new Player(scanner.nextLine());
            System.out.print("Player 2, please enter your name: ");
            Player player2 = new Player(scanner.nextLine());

            return new PlayerNames(player1.name, player2.name);
        }

        public static void printWelcome(Player player1, Player player2) {
            getStartingPlayer(player1, player2);
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("                         COIN FLIP TO DETERMINE FIRST TURN...                   ");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("> %s wins the coin toss and gets first pick!%n", startingPlayer.name);
            System.out.println();
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
                            player + " has chosen: " +  chosenChamp.getName() + " -- " + chosenChamp.getRole() + "\n" +
                            "--------------------------------------------------\n" +
                            "\n" +
                            "> "+chosenChamp.getName() + " has been removed from the pool.\n"
            );
        }


        public static void printCombatStart(Player player1, Player player2) {
            Champion c1 = player1.getChosenChampion();
            Champion c2 = player2.getChosenChampion();

            if(c1 == null && c2 == null){
                System.out.println("================================================================================");
                System.out.println("Please select correct champion");
                System.out.println("================================================================================");
            }

            System.out.println("================================================================================");
            System.out.println("                                 BATTLE BEGINS!                                ");
            System.out.println("================================================================================");
            System.out.println();
            System.out.printf("   %-28s %-28s%n", "[ PLAYER 1 ]", "[ PLAYER 2 ]");
            System.out.printf("   %-28s %-28s%n", player1.name, player2.name);
            System.out.printf("   Champion: %-18s Champion: %-18s%n", c1.getName(), c2.getName());
            System.out.printf("   Role:     %-18s Role:     %-18s%n", c1.getRole(), c2.getRole());
            System.out.printf("   HP:       [%d/%-13d] HP:       [%d/%d]%n",
                    c1.getBaseStats().getMaxHealth(), c1.getBaseStats().getMaxHealth(),
                    c2.getBaseStats().getMaxHealth(), c2.getBaseStats().getMaxHealth());
            System.out.printf("   Mana:     [%d/%-13d] Mana:     [%d/%d]%n",
                    c1.getBaseStats().getMaxMana(), c1.getBaseStats().getMaxMana(),
                    c2.getBaseStats().getMaxMana(), c2.getBaseStats().getMaxMana());
            System.out.println();
        }

    public static void printUnitStatus(Player player) {
        Champion champ = player.getChosenChampion();
        BaseStats stats = champ.getBaseStats();

        int hp = stats.getCurrentHealth();
        int maxHp = stats.getMaxHealth();
        int mana = stats.getCurrentMana();
        int maxMana = stats.getMaxMana();

        // Generate visual bars (10 blocks long)
        String hpBar = generateBar(hp, maxHp, 10, "█", "░");
        String manaBar = generateBar(mana, maxMana, 10, "█", "░");

        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.printf("│ %-15s (%-32s) │%n", player.name, champ.getName());
        System.out.println("├────────────────────────────────────────────────────────┤");
        System.out.printf("│ HP   [%s] %3d / %-3d                            │%n", hpBar, hp, maxHp);
        System.out.printf("│ MANA [%s] %3d / %-3d                            │%n", manaBar, mana, maxMana);
        System.out.println("└────────────────────────────────────────────────────────┘");
    }

    // Helper method to draw dynamic visual progress bars
    private static String generateBar(int current, int max, int totalBlocks, String filledChar, String emptyChar) {
        if (max <= 0) return emptyChar.repeat(totalBlocks);
        int filledBlocks = Math.max(0, Math.min(totalBlocks, (int) Math.round((double) current / max * totalBlocks)));
        return filledChar.repeat(filledBlocks) + emptyChar.repeat(totalBlocks - filledBlocks);
    }
}

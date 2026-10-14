package org.combat.game;

import java.util.List;
import java.util.Scanner;

public class GameInfo {
    public record PlayerNames(String player1, String player2) {}
    final static Scanner scanner = new Scanner(System.in);
    public enum GameStatus { DRAFT_PHASE, COMBAT_PHASE, GAME_OVER}
    public static Player startingPlayer;


    private GameInfo(Player startingPlayer) {
        this.startingPlayer = startingPlayer;
    }
        static public PlayerNames getPlayerNames(){
        System.out.print("Player 1, please enter your name: ");
        Player player1 = new Player(scanner.nextLine());
        System.out.print("Player 2, please enter your name: ");
        Player player2 = new Player(scanner.nextLine());

        return new PlayerNames(player1.name, player2.name);
    }

        // INTRODUCTION LOGS
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
        public static void printWelcome(Player player1, Player player2) {
            getStartingPlayer(player1, player2);
            System.out.println();
            System.out.println("================================================================================");
            System.out.println("Welcome: " + player1.name + " & " + player2.name);
            System.out.print(
                    "==================================================\n" +
                            "               AVAILABLE CHAMPIONS\n" +
                            "==================================================\n"
            );

            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("                         COIN FLIP TO DETERMINE FIRST TURN...                   ");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("> %s wins the coin toss and gets first pick!%n", startingPlayer.name);
            System.out.println();
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

        // COMBAT INFO LOGS
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
        public static void printCombatCommands(Champion activeChamp){
            System.out.println("Choose your action:");
            System.out.println("  [1] Auto-Attack (" + activeChamp.getAutoAttack().getName() + ")");
            System.out.println("  [2] Cast Spell");
            System.out.print("\nEnter choice (1-2): ");
        }
        public static void printCombatStats(Player activePlayer, Player defendingPlayer, int combatTurns,int combatRounds){
            System.out.println("\n==================================================");
            System.out.println("                  TURN " + combatTurns +"                  ROUND " + combatRounds);
            System.out.println("==================================================");

            Champion activeChamp = activePlayer.getChosenChampion();
            Champion defenderChamp = defendingPlayer.getChosenChampion();

            // Display Active Player Status
            System.out.println("\n[ACTIVE PLAYER] " + activePlayer.name + " (" + activeChamp.getName() + ")");
            System.out.println("  HP: " + activeChamp.getBaseStats().getCurrentHealth() + " / " + activeChamp.getBaseStats().getMaxHealth());
            System.out.println("  Mana: " + activeChamp.getBaseStats().getCurrentMana() + " / " + activeChamp.getBaseStats().getMaxMana());

// Display Defending Player Status
            System.out.println("\n[OPPONENT] " + defendingPlayer.name + " (" + defenderChamp.getName() + ")");
            System.out.println("  HP: " + defenderChamp.getBaseStats().getCurrentHealth() + " / " + defenderChamp.getBaseStats().getMaxHealth());
            System.out.println("  Mana: " + defenderChamp.getBaseStats().getCurrentMana() + " / " + defenderChamp.getBaseStats().getMaxMana());

            System.out.println("--------------------------------------------------");
        }
        public static void printChampionStats(Player player) {
        Champion champ = player.getChosenChampion();
        BaseStats stats = champ.getBaseStats();

        int hp = stats.getCurrentHealth();
        int maxHp = stats.getMaxHealth();
        int mana = stats.getCurrentMana();
        int maxMana = stats.getMaxMana();

        // Generate visual bars (10 blocks long)
        String hpBar = generateStatsBar(hp, maxHp, 10, "█", "░");
        String manaBar = generateStatsBar(mana, maxMana, 10, "█", "░");

        System.out.println("┌────────────────────────────────────────────────────────┐");
        System.out.printf("│ %-15s (%-32s) │%n", player.name, champ.getName());
        System.out.println("├────────────────────────────────────────────────────────┤");
        System.out.printf("│ HP   [%s] %3d / %-3d                            │%n", hpBar, hp, maxHp);
        System.out.printf("│ MANA [%s] %3d / %-3d                            │%n", manaBar, mana, maxMana);
        System.out.println("└────────────────────────────────────────────────────────┘");
    }

        // COMBAT COMMANDS
        public static Spell handlePlayerSpellChoice(int playerSpellChoice,Champion activeChamp){
            return activeChamp.getOneSpell(playerSpellChoice - 1);
        }
        public static void handleCombatChoice(String initialChoice, Champion activeChamp, Champion defendingChampion) {
        boolean actionCompleted = false;

        while (!actionCompleted) {
            if (initialChoice.equals("1")) {
                System.out.println("You just auto attacked.");
                actionCompleted = true; // Turn finished successfully

            } else if (initialChoice.equals("2")) {
                handleSpellsDisplay(activeChamp);
                String input = scanner.nextLine().trim();

                int playerSpellChoice;
                try {
                    playerSpellChoice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    continue; // Re-prompt spell selection
                }

                // --- GO BACK (0) ---
                if (playerSpellChoice == 0) {
                    printCombatCommands(activeChamp);
                    initialChoice = scanner.nextLine().trim(); // Re-read main action choice (1 or 2)
                    continue; // Restart loop with the new initialChoice
                }

                // --- SPELL VALIDATION ---
                List<Spell> spells = activeChamp.getSpells();
                if (playerSpellChoice < 1 || playerSpellChoice > spells.size()) {
                    System.out.println("Invalid choice! Select a number between 1 and " + spells.size() + ".");
                    continue; // Re-prompt spell selection
                }

                // Get the used spell's details
                Spell usedSpellDetails = handlePlayerSpellChoice(playerSpellChoice, activeChamp);
                System.out.println("You've selected: " + usedSpellDetails.getName());

                // Get used spell's current cooldown
                int usedSpellCD = usedSpellDetails.getCurrentCooldown();

                // Check if spell cd is 0
                if(usedSpellCD > 0){
                    System.out.println("You can't use this spell yet try in " + usedSpellCD + " turn.");
                    printCombatCommands(activeChamp);
                    initialChoice = scanner.nextLine().trim(); // Re-read main action choice (1 or 2)
                    continue; // Restart loop with the new initialChoice
                } else {
                    // Reset spell's cooldown
                    usedSpellDetails.setCooldownTurns(activeChamp.getOneSpell(playerSpellChoice).getCooldownTurns());
                }

                int usedSpellManaCost = usedSpellDetails.getManaCost();
                int currentPlayerMana= activeChamp.getBaseStats().getCurrentMana();
                // check if player's current mana => spell's mana cost
                if(currentPlayerMana >= usedSpellManaCost){
                    // Deduct mana cost from player's current mana
                    activeChamp.getBaseStats().setCurrentMana(currentPlayerMana - usedSpellCD);
                    // Calculate spell damage
                    int spellDamage = usedSpellDetails.getBaseDamage();
                    // Get opponent's current health
                    int defendingChampionCurrentHp = defendingChampion.getBaseStats().getCurrentHealth();
                    // Inflict damage to opponent
                    defendingChampion.getBaseStats().setCurrentHealth(defendingChampionCurrentHp - spellDamage);
                    // apply the effects of the spell
                } else {
                    System.out.println("You don't have enough mana to use this spell.");
                    printCombatCommands(activeChamp);
                    initialChoice = scanner.nextLine().trim(); // Re-read main action choice (1 or 2)
                    continue; // Restart loop with the new initialChoice
                }

                actionCompleted = true; // Turn finished successfully

            } else {
                System.out.println("Invalid choice. Please choose 1 or 2.");
                printCombatCommands(activeChamp);
                initialChoice = scanner.nextLine().trim();
            }
        }
    }
        public static void handleSpellsDisplay(Champion activeChamp){
            System.out.println("\n--- Available Spells ---");
            List<Spell> spells = activeChamp.getSpells();

            for (int i = 0; i < spells.size(); i++) {
                Spell spell = spells.get(i);

                String status = "READY";
                if (spell.getCurrentCooldown() > 0) {
                    status = "COOLDOWN (" + spell.getCurrentCooldown() + " turns left)";
                } else if (activeChamp.getBaseStats().getCurrentMana() < spell.getManaCost()) {
                    status = "NOT ENOUGH MANA";
                }

                System.out.println("  [" + (i + 1) + "] " + spell.getName() +
                        " | Mana: " + spell.getManaCost() +
                        " | Status: " + status);
            }

            System.out.print("\nSelect a spell (1-" + spells.size() + ") or 0 to back: ");
        }

        // Helper method to draw dynamic visual progress bars
        private static String generateStatsBar(int current, int max, int totalBlocks, String filledChar, String emptyChar) {
            if (max <= 0) return emptyChar.repeat(totalBlocks);
            int filledBlocks = Math.max(0, Math.min(totalBlocks, (int) Math.round((double) current / max * totalBlocks)));
            return filledChar.repeat(filledBlocks) + emptyChar.repeat(totalBlocks - filledBlocks);
        }
}

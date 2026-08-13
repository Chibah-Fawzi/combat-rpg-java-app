package org.combat.game;

public class Player {
    String name;
    Champion chosenChampion;

    public Champion getChosenChampion() {
        return chosenChampion;
    }

    public void setChosenChampion(Champion chosenChampion) {
        this.chosenChampion = chosenChampion;
    }


    // Constructor
    public Player(String name) {
        this.name = name;
        this.chosenChampion = null;
    }

    // ASK DONT TELL
    public void initializeStats() {
        if (chosenChampion != null && chosenChampion.getBaseStats() != null) {
            BaseStats stats = chosenChampion.getBaseStats();
            stats.setCurrentHealth(stats.getMaxHealth());
            stats.setCurrentMana(stats.getMaxMana());
        }
    }

}

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

    public Player(String name) {
        this.name = name;
        this.chosenChampion = null;
    }
}

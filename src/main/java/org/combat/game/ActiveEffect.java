package org.combat.game;

public class ActiveEffect {
    private Effect effectTemplate;
    private int remainingTurns;

    public ActiveEffect(Effect effectTemplate) {
        this.effectTemplate = effectTemplate;
        this.remainingTurns = effectTemplate.getDurationTurns(); // Initializes countdown
    }

    public void decrementDuration() {
        this.remainingTurns--;
    }

    public boolean isExpired() {
        return this.remainingTurns <= 0;
    }

}
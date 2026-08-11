package org.combat.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Effect {
//    public enum EffectType {POISON, BURN, STUN, SHIELD, HEAL, BUFF, EXECUTE}
    @JsonProperty("type")
    private String effectType;
    private int damagePerTurn;
    private int durationTurns;
    private float value;
    private float bonusDamageMultiplier;
    private float thresholdHpPercentage;
    private float critMultiplier;
    private float manaRegenMultiplier;
    private int shieldAmount;
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getShieldAmount() {
        return shieldAmount;
    }

    public void setShieldAmount(int shieldAmount) {
        this.shieldAmount = shieldAmount;
    }




    public float getCritMultiplier() {
        return critMultiplier;
    }

    public void setCritMultiplier(float critMultiplier) {
        this.critMultiplier = critMultiplier;
    }

    public float getManaRegenMultiplier() {
        return manaRegenMultiplier;
    }

    public void setManaRegenMultiplier(float manaRegenMultiplier) {
        this.manaRegenMultiplier = manaRegenMultiplier;
    }


    public float getBonusDamageMultiplier() {
        return bonusDamageMultiplier;
    }

    public void setBonusDamageMultiplier(float bonusDamageMultiplier) {
        this.bonusDamageMultiplier = bonusDamageMultiplier;
    }

    public float getThresholdHpPercentage() {
        return thresholdHpPercentage;
    }

    public void setThresholdHpPercentage(float thresholdHpPercentage) {
        this.thresholdHpPercentage = thresholdHpPercentage;
    }



    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }



    public String getEffectType() {
        return effectType;
    }

    public void setEffectType(String effectType) {
        this.effectType = effectType;
    }

    public int getDamagePerTurn() {
        return damagePerTurn;
    }

    public void setDamagePerTurn(int damagePerTurn) {
        this.damagePerTurn = damagePerTurn;
    }

    public int getDurationTurns() {
        return durationTurns;
    }

    public void setDurationTurns(int durationTurns) {
        this.durationTurns = durationTurns;
    }

}

package org.combat.game;


import java.util.List;

public class Spell {

    private String name;
    private Champion.UnitClass type;
    private int manaCost;
    private int cooldownTurns;
    private int currentCooldown;


    private int baseDamage;
    private Effect effect;
    private int amount;
    private int shieldAmount;



    public int getCurrentCooldown() {
        return currentCooldown;
    }

    public void setCurrentCooldown(int currentCooldown) {
        this.currentCooldown = currentCooldown;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public Champion.UnitClass getType() {
        return type;
    }

    public void setType(Champion.UnitClass type) {
        this.type = type;
    }


    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public int getCooldownTurns() {
        return cooldownTurns;
    }

    public void setCooldownTurns(int cooldownTurns) {
        this.cooldownTurns = cooldownTurns;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

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



}

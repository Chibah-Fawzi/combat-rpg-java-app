package org.combat.game;

public class BaseStats {
    private int maxHealth;
    private int baseAttack;
    private int maxMana;
    private int manaRegenPerAttack;
    private int currentMana;
    private float currentHealth;
    private float baseArmor = 0;

//    public BaseStats(int maxHealth, int baseAttack, int maxMana, int manaRegenPerAttack, int currentMana, int currentHealth) {
//        this.maxHealth = maxHealth;
//        this.baseAttack = baseAttack;
//        this.maxMana = maxMana;
//        this.manaRegenPerAttack = manaRegenPerAttack;
//        this.currentMana = currentMana;
//        this.currentHealth = currentHealth;
//    }

    public int getMaxHealth() {return maxHealth;}

    public void setMaxHealth(int maxHealth) {this.maxHealth = maxHealth;}

    public int getBaseAttack() {return baseAttack;}

    public void setBaseAttack(int baseAttack) {this.baseAttack = baseAttack;}


    public int getMaxMana() {return maxMana;}

    public void setMaxMana(int maxMana) {this.maxMana = maxMana;}


    public int getManaRegenPerAttack() {return manaRegenPerAttack;}

    public void setManaRegenPerAttack(int manaRegenPerAttack) {this.manaRegenPerAttack = manaRegenPerAttack;}

    public float getBaseArmor() {return baseArmor;}

    public void setBaseArmor(float baseArmor) {this.baseArmor = baseArmor;}

    public int getCurrentMana() {return currentMana;}
    public float getCurrentHealth() {return currentHealth;}
    public void setCurrentMana(int currentMana) {this.currentMana = currentMana;}
    public void setCurrentHealth(float currentHealth) {this.currentHealth = currentHealth;}
}

package org.combat.game;

public class AutoAttack {
    private String name;
    private float damageMultiplier;
    private Champion.UnitClass type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDamageMultiplier() {
        return damageMultiplier;
    }

    public void setDamageMultiplier(float damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public Champion.UnitClass getType() {
        return type;
    }

    public void setType(Champion.UnitClass type) {
        this.type = type;
    }

}

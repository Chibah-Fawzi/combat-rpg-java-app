package org.combat.game;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Champion {

    public enum UnitClass {PHYSICAL,MAGICAL}

    private String id;
    private String name;
    private String role;

    @JsonProperty("unitClass")
    private UnitClass unitClass;
    private BaseStats baseStats;
    private AutoAttack autoAttack;
    @JsonProperty("spells")
    private List<Spell> spell;


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AutoAttack getAutoAttack() {
        return autoAttack;
    }

    public void setAutoAttack(AutoAttack autoAttack) {
        this.autoAttack = autoAttack;
    }

    public List<Spell> getSpells() {
        return spell;
    }

    public Spell getOneSpell(int index){
        return spell.get(index);
    }

    public void setSpell(List<Spell> spell) {
        this.spell = spell;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;}

    public void setName(String name) {this.name = name;}

    public UnitClass getUnitClass() {return unitClass;}

    public void setUnitClass(UnitClass unitClass) {this.unitClass = unitClass;}


    public BaseStats getBaseStats() {
        return baseStats;
    }

    public void setBaseStats(BaseStats baseStats) {
        this.baseStats = baseStats;
    }



}

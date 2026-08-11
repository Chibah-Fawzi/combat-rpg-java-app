package org.combat.game;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File jsonFile = new File("src/main/utils/champions.json");
        ChampionsWrapper wrapper = mapper.readValue(jsonFile, ChampionsWrapper.class);
        List<Champion> champions = wrapper.getChampions();

        for (Champion champion : champions) {
            System.out.println("==================================================");
            System.out.println(champion.getId() + " - " + champion.getName() + " (" + champion.getRole() + ")");
            System.out.println("Class: " + champion.getUnitClass());
            System.out.println("HP: " + champion.getBaseStats().getMaxHealth()
                    + " | Mana: " + champion.getBaseStats().getMaxMana()
                    + " | ATK: " + champion.getBaseStats().getBaseAttack());
            System.out.println("Auto: " + champion.getAutoAttack().getName()
                    + " [" + champion.getAutoAttack().getType() + "] x"
                    + champion.getAutoAttack().getDamageMultiplier());
            System.out.println("Spells:");
            if (champion.getSpells() != null) {
                for (Spell spell : champion.getSpells()) {
                    System.out.println("  - " + spell.getName()
                            + " [" + spell.getType() + "]"
                            + " | Mana: " + spell.getManaCost()
                            + " | CD: " + spell.getCooldownTurns()
                            + " | Dmg: " + spell.getBaseDamage());
                }
            }
            System.out.println();
        }
    }
}

package org.combat.game;

import java.util.List;
import java.util.Objects;

public class ChampionsWrapper {
    private List<Champion> champions;

    public List<Champion> getChampions() {
        return champions;
    }

    public void setChampions(List<Champion> newChampions) {
        champions = newChampions;
    }

    public Champion getChampion(String championId) {
        for (Champion champion : champions) {
            if (Objects.equals(champion.getId(), championId)
                    || Objects.equalsIgnoreCase(champion.getName(), championId)) {
                return champion;
            }
        }
        return null;
    }
}

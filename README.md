# Java Terminal RPG Combat Engine

A turn-based command-line combat game built in Java. Players compete in a champion draft before entering a tactical arena with turn upkeep, cooldown management, and status effects.

## Mechanics

- **Draft Phase:** Players take turns selecting unique champions from a JSON data file.
- **Upkeep Phase:** At the start of every turn, active status effects tick, cooldowns decrement, and death conditions resolve before player action.
- **Turn Actions:** Choose between auto-attacks to generate mana or specialized spells with distinct cost and cooldown constraints.
- **Stat Engine:** Tracks health pools, mana scaling, damage modifiers, and status effects (poison, burn, stun, shield, heal, execution thresholds).

## Architecture

The project separates game state management, utility wrappers, and UI formatting:

- `Main`: Coordinates phase transitions (`DRAFT_PHASE`, `COMBAT_PHASE`, `GAME_OVER`).
- `ChampionsWrapper`: Handles JSON parsing via Jackson, champion lookup, and player selection commands.
- `Champion` & `BaseStats`: Defines entity stats, auto-attacks, and available spell sets.
- `GameInfo`: Manages console outputs and turn status updates.

```mermaid
classDiagram
direction BT
class AutoAttack {
  - String name
  - UnitClass type
  - float damageMultiplier
}
class BaseStats {
  - int currentMana
  - int baseAttack
  - int maxHealth
  - int manaRegenPerAttack
  - int currentHealth
  - int maxMana
}
class Champion {
  - List~Spell~ spell
  - String id
  - String role
  - AutoAttack autoAttack
  - BaseStats baseStats
  - String name
  - UnitClass unitClass
}
class ChampionsWrapper {
  - String playerCommand
  ~ Scanner scanner
  - List~Champion~ champions
}
class Effect {
  - float thresholdHpPercentage
  - float value
  - float manaRegenMultiplier
  - int shieldAmount
  - int amount
  - String effectType
  - int durationTurns
  - int damagePerTurn
  - float bonusDamageMultiplier
  - float critMultiplier
}
class GameInfo {
  + Player startingPlayer
  ~ Scanner scanner
}
class Main
class Player {
  ~ String name
  ~ Champion chosenChampion
}
class Spell {
  - String name
  - int manaCost
  - int baseDamage
  - int cooldownTurns
  - int shieldAmount
  - Effect effect
  - UnitClass type
  - int amount
}

Champion "1" *--> "autoAttack 1" AutoAttack
Champion "1" *--> "baseStats 1" BaseStats
Champion "1" *--> "spell *" Spell
ChampionsWrapper "1" *--> "champions *" Champion
GameInfo "1" *--> "startingPlayer 1" Player
GameInfo  ..>  Player : «create»
Main  ..>  Player : «create»
Player "1" *--> "chosenChampion 1" Champion
Spell "1" *--> "effect 1" Effect

```

## Requirements

- Java 17 or higher
- Maven 3.6+
- Jackson Databind library (defined in `pom.xml`)

## Quick Start

1. **Clone the repository:**

   ```bash
   git clone [https://github.com/your-username/your-repo-name.git](https://github.com/Chibah-Fawzi/combat-rpg-java-app)
   cd combat-rpg-java-app
   ```

2. **Build the project:**

   ```bash
   mvn clean package
   ```

3. **Run the game:**
   ```bash
   java -cp target/your-built-jar-file.jar org.combat.game.Main
   ```

## Configuration

Champion stats, abilities, and multipliers are stored in `src/main/utils/champions.json`. You can modify base attributes or add new champions directly in that file without altering core game logic.

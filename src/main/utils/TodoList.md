# Todo
- [x] Create the interface for each object/sub-object property in `champions.json`
- [x] Create the other classes `autoAttack`, `spells`
- [x] Link the data in the json file with their classes
- [x] Test displaying all the champions / spells on the console

### Game requirements:
- Starters
    - [x] User1, then User2 chooses their name
    - [x] First user gets shown list of all the champs and their infos
    - [x] You can get more info of the champions using `info champName / champId`
    - [x] User chooses champ using `select champName / champId`
    - [x] Who starts first is defined by a random number between 1 and 1000 the one who gets a higher number wins, if equality it restarts.
    - [x] Each user needs to start the game using `start`
    - [x] Second player cannot chose the same champion as the first
- In game
    - [x] Instantiate selected units with full Health and starting Mana pools.
    - [ ] Initialize turn counters, cooldown trackers, and empty active status effect lists.

    - ### 1. Turn Start (Upkeep Phase):
        - [ ] Status Effect Ticks: Process active status effects on the current unit (e.g., apply Poison/Burn damage, tick down duration counters).
        - [ ] Cooldown Reduction: Decrement remaining cooldown timers by 1 turn for all spells on standby.
        - [ ] State Check: Verify if the active unit died from turn-start status damage before allowing actions.

    - ### 2. Action Selection Phase:
        - **Free Action: Cast Spells (Repeatable)**
            - [ ] Display available spells with current Mana costs and Cooldown states.
            - [ ] Validate player choices: Ensure unit has enough Mana and spell cooldown == 0.
            - [ ] Execute Spell: Deduct Mana, set spell cooldown, calculate/mitigate damage or apply buff/debuff/shield, and resolve effects on the target.
        - **Standard Action: Auto-Attack (Once per Turn)**
            - [ ] Execute standard physical/magical auto-attack.
            - [ ] Calculate and apply mitigated damage to the target.
            - [ ] Restore Mana: Add fixed Mana recovery (e.g., +15 to +25 Mana) to the attacking unit's current pool.
        - **Pass Turn**
            - [ ] Allow player to explicitly end their turn if no further actions are desired or possible.

    - ### 3. Turn End (Cleanup Phase):
        - [ ] Expire any status effects whose durations have reached 0 turns.
        - [ ] Pass turn control to the opposing unit.

- ### Combat Resolution & End Game:
    - [ ] Health & Defeat Tracking: After every damage event, check if target unit's Health falls to <= 0.
    - [ ] Victory Screen: Display match results (Winner / Defeated) and prompt to restart or return to champion selection.

# ShrineSpawn: Heaven/Hell Respawn System

## Overview
ShrineSpawn is a Minecraft Paper plugin that adds a fun death and respawn mechanic. When a player dies, they are transported to a "heaven" or "hell" world. To respawn, they must complete a challenge or another player can sacrifice items at a shrine. Features will eventually include configurable worlds, respawn costs, particle effects, and more.

---

## Roadmap & Feature List

### Phase 0 — Project Setup
**Tasks:**
- Set up a Paper 1.21.10 server locally.
- Create a ShrineSpawn plugin project with Maven/Gradle.
- Add dependencies: Paper API, Multiverse-Core API.
- Set up a `/shrine` command or basic logging for testing.

**Goals:**
- Plugin loads without errors.
- Verify Multiverse is installed and accessible.

---

### Phase 1 — Core World Management
**Tasks:**
- Check if `world_heaven` exists; create it if not using Multiverse-Core.
- Decide world type: superflat vs custom void world.
- Optionally create `world_hell`.

**Deliverables:**
- Heaven (and Hell) world exists automatically.
- Manual teleport to worlds works for testing.

---

### Phase 2 — Death Detection and Teleport
**Tasks:**
- Listen for `PlayerDeathEvent`.
- Teleport dead players to the appropriate world.
- Ensure teleport works from any world (`world`, `world_nether`, `world_end`).
- Decide respawn mechanics trigger: automatic vs button/portal.

**Deliverables:**
- Player deaths in any world move the player to the designated afterlife world.
- Player stays in the afterlife until they trigger respawn.

---

### Phase 3 — Respawn Mechanism
**Tasks:**
- Implement shrine/button/portal in the afterlife world.
- Detect player interaction (`PlayerInteractEvent`).
- Teleport player back to main world spawn or configurable location.
- Allow configuration via config file or `/dialog` menus.

**Deliverables:**
- Dead players can return to main world via interaction.

---

### Phase 4 — Player Death Data / Respawn Cost
**Tasks:**
- Track player deaths using PersistentDataContainer (PDC).
- Store “number of deaths” centrally (global PDC).
- Define respawn cost per death count (configurable).
- Implement item consumption at shrine for respawn.

**Deliverables:**
- Player deaths tracked globally.
- Respawn requires payment at shrine.

---

### Phase 5 — Optional: Puzzle / Parkour Respawn
**Tasks:**
- Create small parkour or puzzle challenge in heaven/hell.
- Allow completion to bypass sacrificial payment.
- Track completion state per player using PDC or memory.

**Deliverables:**
- Players can respawn via puzzle instead of item sacrifice.

---

### Phase 6 — Effects and Polish
**Tasks:**
- Add particle effects on death, respawn, or shrine interaction.
- Optional: sound effects / visual cues.
- Configurable options via `/dialog` menus.
- Admin commands to configure shrine location, world names, respawn costs.
- Validate server stability and multi-world teleport edge cases.

**Deliverables:**
- Visually engaging respawn system.
- Fully configurable plugin.

---

### Phase 7 — Multi-Server / Portability
**Tasks:**
- Ensure ShrineSpawn works on other servers with different world names.
- Robust Multiverse API calls (world missing/renamed).
- Configurable and portable plugin settings.

**Deliverables:**
- Plugin usable on multiple servers without code changes.

---

### Phase 8 — Future Enhancements (Optional)
- Support “hell” vs “heaven” with different mechanics.
- Achievements for completing challenges.
- More complex particle/environment effects.
- Integration with economy plugins for advanced respawn cost scaling.
- Admin tools: reset death counts, teleport all players, toggle mechanics.

---

## Recommended Development Flow
1. **Phase 0 → Phase 2:** Basic death detection and teleport.
2. **Phase 3 → Phase 4:** Shrine, PDC death tracking, respawn costs.
3. **Phase 5 → Phase 6:** Add puzzles, particle effects, polish.
4. **Phase 7:** Multi-server robustness and configuration.
5. **Phase 8:** Iterative fun features and advanced mechanics.

# OmniMixin

OmniMixin is a Fabric mixin library for Minecraft 1.21 that gives other mods a large, ready-made set of event hooks into common game behavior, so mod authors don't have to write, maintain, and debug their own mixins for everyday tasks. Instead of every mod injecting its own raw mixins into the same vanilla classes and risking conflicts with each other, OmniMixin exposes a single, stable, event-based API that multiple mods can safely build on at the same time. It is meant to be bundled inside the mods that use it, so players never install it separately — it just works quietly in the background whenever a mod that depends on it is present.

Below is a breakdown of every subsystem included in the library.

## Movement

This subsystem covers how entities move through the world. It includes a hook for reading and adjusting an entity's movement speed before it's applied, a hook for when an entity starts or stops sprinting, a hook that fires when an entity jumps, a hook for adjusting a player's flying speed, and a hook for when an entity gets physically pushed away by another entity. Together these let a mod add speed modifiers, custom sprint behavior, jump effects, custom flight speeds, or altered collision pushback without touching the base movement code itself.

## Combat

This subsystem covers damage, healing, and status effects. It includes a hook that fires whenever a living entity takes damage, letting a mod inspect, modify, or cancel the damage before it applies. There's a matching hook for knockback, so a mod can change how far or how hard an entity is thrown back when hit. Armor is covered as well, with hooks for how much damage armor absorbs and for when armor durability is worn down. Status effects (potion effects) have their own hooks for when an effect is added to an entity and when one is removed, both of which can be intercepted or cancelled. Rounding out combat are hooks for healing, for an entity's death event, and for changes to an entity's absorption hearts (the extra yellow hearts from effects like the Absorption status effect).

## World and blocks

This subsystem covers how blocks behave in the world. It includes a hook for when a player or other source right-clicks and interacts with a block, a hook for neighbor updates (the game's way of telling a block that something nearby changed, which drives things like redstone and physics reactions), and hooks for how much weak and strong redstone power a block outputs. Block placement and block breaking each get their own pair of hooks, with the breaking hooks split into a "before break" stage that can cancel the break entirely and an "after break" stage for reacting once it's done. There's also a hook for a player's block breaking speed, so a mod can adjust how fast specific blocks are mined under specific conditions.

## Items

This subsystem covers how items behave when used. It includes hooks for using an item in the air, using an item on a block, and finishing a use action (such as finishing eating or drinking something), each of which can be observed or overridden. There's a hook for clicking a slot in an inventory screen, useful for mods that want to react to or restrict specific inventory actions. Equipment changes are covered too, with a hook that fires whenever an entity's equipped item in a given slot changes. Finally, there's a hook for the items a block drops when broken, letting a mod change or add to a block's drop table based on context.

## Entities

This subsystem covers the general lifecycle and AI of entities. It includes a hook that fires every tick for every entity, a hook for when an entity is discarded or despawns, and a hook for whether a mob is allowed to spawn under the game's current rules, which a mod can use to add or restrict custom spawn conditions. There's a hook for when an entity is spawned into a server world, a hook for when a goal is added to a mob's AI goal selector (so a mod can inspect, block, or replace specific AI behaviors), and a hook for when a mob sets its attack or follow target.

## Data syncing

This subsystem lets mods attach their own custom data to any entity and have it automatically saved and loaded with that entity's normal save data. It works through a small registry where a mod registers a named, typed value it wants tracked; OmniMixin then handles writing that value into the entity's NBT data when it saves and reading it back out when it loads, so mods don't need to write their own NBT serialization code for simple per-entity data.

## Rendering (client-side)

This subsystem covers visual and screen-related hooks that only run on the client. It includes a hook into entity rendering, a hook for when the game opens or changes the current screen, a hook into the heads-up display's render pass, hooks for a screen's initialization and removal lifecycle, and a hook for the field-of-view calculation used by the camera. These let a mod adjust visuals, react to screen changes, draw custom HUD elements, or tweak FOV behavior without maintaining separate render mixins.

## Why it exists

Because every hook is centralized in one library, mods that use OmniMixin avoid duplicate mixins targeting the same vanilla methods, which is one of the more common sources of mod incompatibility. If you're a player and see OmniMixin required as a dependency by another mod, that's expected — install it alongside that mod and it will do its job automatically without any configuration needed.

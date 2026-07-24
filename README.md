# Template Mod

This is the test mod used to develop and verify [OmniMixin](https://github.com/goshi-hider/OminiMixin), a Fabric mixin library for Minecraft 1.21. It exists purely as a testbed — it bundles OmniMixin, loads it in a running game, and exercises its hooks so changes to the library can be checked end to end before being relied on by real mods.

This branch (`test-mod`) lives in the same repository as the OmniMixin library itself, on the `main` branch, as an unrelated history sharing one repo for convenience. See `main` for the library's own source and README.

## What this is for

OmniMixin is a library, not something players install on its own. Template Mod is a minimal Fabric mod that depends on it, so this repo has something concrete to build, launch, and test against while working on the library. If you're looking for the actual mixin hooks and API, check the `main` branch instead — this branch is scaffolding.

## Requirements

- Java 21
- Minecraft 1.21
- Fabric Loader 0.19.3 or newer
- Fabric API 0.102.0+1.21 or newer

## Project layout

- `src/main/java` — mod source, including the main and client entrypoints and the mixin config
- `src/main/resources` — `fabric.mod.json`, mixin config, and assets
- `libs/omnimixin-1.0.0.jar` — the built OmniMixin library jar, included directly into this mod's jar at build time (jar-in-jar), so anyone who installs this mod doesn't need to install OmniMixin separately

## Building

Clone the repo and check out this branch, then run:

```
./gradlew build
```

On Windows use `gradlew.bat build` instead. The built jar will be in `build/libs/`.

## Running in a dev environment

```
./gradlew runClient
```

This launches a development Minecraft client with the mod (and the bundled OmniMixin jar) loaded, for manual testing.

## Updating the bundled OmniMixin jar

When the OmniMixin library changes on `main`:

1. Build it from that branch with `./gradlew build`.
2. Copy the resulting jar from its `build/libs/omnimixin-1.0.0.jar` (not the `-dev` jar in `build/devlibs/`) into this project's `libs/` folder, replacing the old one.
3. Rebuild this mod and re-test.

## License

CC0-1.0, matching the Fabric example mod this project was based on.

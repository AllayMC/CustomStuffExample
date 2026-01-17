# CustomStuffExample

An example plugin that demonstrates how to register custom items, blocks, and entities in AllayMC.

## Features

This plugin showcases:

- **Custom Item Registration** - A custom edible item (`cse:fish_cake`) with golden apple effects
- **Custom Block Registration** - A custom block (`cse:test_block`) with custom geometry and collision shape
- **Creative Item Registration** - Adding custom items to the creative inventory
- **Resource Pack Integration** - Textures and models for custom content

## Prerequisites

- Java 21 or higher
- AllayMC server (API version 0.23.0-SNAPSHOT or compatible)

## Project Structure

```
src/main/java/org/allaymc/customstuffexample/
├── CustomStuffExample.java    # Main plugin class
└── item/
    └── ItemFishCakeStack.java # Custom item implementation

src/main/resources/assets/resource_pack/
├── manifest.json              # Resource pack manifest
├── blocks.json                # Block definitions
├── models/blocks/             # Block geometry files
└── textures/                  # Item and block textures
    ├── items/
    └── blocks/
```

## Building

```bash
./gradlew shadowJar
```

The compiled `.jar` file will be in `build/libs`. Copy it to your AllayMC server's `plugins` directory.

## Development

Run a local AllayMC server with the plugin:

```bash
./gradlew runServer
```

## Documentation

For more information about the AllayMC API, refer to the [official documentation](https://docs.allaymc.org).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
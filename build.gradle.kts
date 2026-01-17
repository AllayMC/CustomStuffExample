plugins {
    id("java-library")
    id("org.allaymc.gradle.plugin") version "0.2.1"
}

group = "org.allaymc.customstuffexample"
description = "An example plugin that shows how to register custom item/block/entity in AllayMC"
version = "0.1.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

allay {
    api = "0.23.0-SNAPSHOT"
    // Custom block/item/entity API are not public API and is in the server module, so we need to
    // set `apiOnly` to false
    apiOnly = false;

    plugin {
        entrance = ".CustomStuffExample"
        authors += "daoge_cmd"
        website = "https://github.com/AllayMC/CustomStuffExample"
    }
}

repositories {
    mavenLocal()
}

dependencies {
    compileOnly(group = "org.projectlombok", name = "lombok", version = "1.18.34")
    annotationProcessor(group = "org.projectlombok", name = "lombok", version = "1.18.34")
}

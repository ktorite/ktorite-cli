<div align="center">
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="https://raw.githubusercontent.com/ktorite/ktorite-cli/main/.github/images/logo.png">
    <img alt="Logo" src="https://raw.githubusercontent.com/ktorite/ktorite-cli/main/.github/images/logo.png" width="200" height="200">
  </picture>
  <br>
  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="https://raw.githubusercontent.com/ktorite/ktorite-cli/main/.github/images/cli-logo.png">
    <img alt="Logo" src="https://raw.githubusercontent.com/ktorite/ktorite-cli/main/.github/images/cli-logo.png" width="250" height="50">
  </picture>
</div>

# Ktorite CLI

Command line tool for scaffolding Ktorite projects. Requires Java 17+.

## Install

```bash
curl -sSL https://raw.githubusercontent.com/ktorite/ktorite-cli/main/install.sh | sh
```

This downloads the fat jar and adds a `ktorite` wrapper to your PATH. Start a new terminal or `source` your shell profile afterwards.

Manual install works too - grab `ktorite-all.jar` from [releases](https://github.com/ktorite/ktorite-cli/releases) and run it directly:

```bash
java -jar ktorite-all.jar --help
```

## Usage

### create-project

```bash
ktorite create-project myapp ./myapp
cd myapp
./gradlew run
```

Generates a complete Gradle project: build files, logback config, a `ktorite.properties` file with an auto-generated session secret and H2 database settings, and a sample model wired up with `Ktorite.start`. The project name must be a valid Kotlin identifier (letters and digits, starting with a letter).

After the project runs, create a superuser for the admin panel:

```bash
./gradlew createsuperuser -Pargs="admin mypassword"
```

## Commands

| Command | Description |
|---|---|
| `create-project <name> <dir>` | Scaffold a new project |

More commands (`create-app`, migrations) are planned but not implemented yet.

## Building from source

```bash
git clone https://github.com/ktorite/ktorite-cli.git
cd ktorite-cli
./gradlew :app:shadowJar
java -jar app/build/libs/ktorite-all.jar --help
```

## License

MIT

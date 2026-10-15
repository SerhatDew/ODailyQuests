# ODailyQuests

<img width="805" height="187" alt="image" src="https://github.com/user-attachments/assets/ecffe00c-7d0f-4b0b-a319-b428aae32f1e" />

### Dynamize your server & increase player retention !

O'DailyQuests is a powerful and easy-to-use **daily quests plugin** for Minecraft.
Every day, players receive new **random quests** to complete, each rewarding them with customizable prizes.
Perfect for increasing player engagement and server retention!

## 🚀 Features & Highlights (v26.2)

- **Java 25 Ready**: Built and optimized for modern Java 25 runtimes with up-to-date Gradle 9 toolchains.
- **Paper 26.2 & Modern Minecraft**: Fully compatible with modern Paper builds, utilizing up-to-date PersistentDataContainer types and clean chat component handling.
- **Multi-Server & Network Resilient**: Enhanced MySQL and SQLite storage management designed for multi-server networks:
  - Failsafe database handling preventing data corruption if a backend node is temporarily unreachable.
  - Safe plugin shutdown and reload protection to ensure player quest progression is never saved in an incomplete state.
- **High Performance**: Minimal overhead with optimized event listeners and cached data structures.

## 🌐 Multi-Server & Network Setup

ODailyQuests supports multi-server setups (Velocity / BungeeCord / Waterfall networks) where multiple Paper instances share player quest progress through a centralized MySQL/MariaDB database.

### 1. Database Configuration
In your `config.yml`, configure the database section to point to your shared database:

```yaml
database:
  type: MYSQL
  host: "127.0.0.1"
  port: 3306
  database: "odailyquests"
  user: "minecraft"
  password: "your_secure_password"
  table_prefix: "odq_"
```

### 2. Synchronization Flow
- **Player Join**: Player quests and progression are retrieved directly from the shared SQL database.
- **Player Switch / Quit**: On server switch or disconnect, current quest progress is safely persisted to the database before the player loads on the target server.
- **Network Fail-Safe**: If a database outage occurs, ODailyQuests enters a safe mode to prevent saving empty/corrupted records over existing player progress.

## ⚙️ Requirements & Compatibility

| Component | Recommended | Minimum |
| :--- | :--- | :--- |
| **Java** | **Java 25** (OpenJDK / GraalVM) | Java 21 |
| **Server Software** | **Paper 26.2+** / Purpur 1.21.x | Paper 1.20+ |
| **Database (Multi-Server)** | MySQL 8.0+ / MariaDB 10.6+ | SQLite (Single Server) |

### Java 25 JVM Flags
ODailyQuests is fully compiled with Java 25 bytecode target. Recommended startup flags for high-load multi-server instances:
```bash
java -Xms8G -Xmx8G -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -jar paper-26.2.jar --nogui
```

## 🧩 Supported Integrations

ODailyQuests natively hooks into popular modern plugins:

- **Custom Items & Blocks**: ItemsAdder, Oraxen, Nexo
- **Economy & Currencies**: Vault, CoinsEngine, EcoBits, PlayerPoints
- **Custom Mobs**: MythicMobs, EliteMobs, LevelledMobs
- **Crates & Rewards**: ExcellentCrates, GoldenCrates
- **Fishing**: PyroFishingPro, CustomFishing

## ⌨️ Commands & Permissions

| Command | Description | Permission |
| :--- | :--- | :--- |
| `/quests` | Opens the personal daily quests interface | `odailyquests.use` |
| `/quests show` | Shows active quests in chat/actionbar | `odailyquests.show` |
| `/qadmin reload` | Safely reloads configurations and database pool | `odailyquests.admin` |
| `/qadmin reset <player>` | Resets a specific player's daily quests | `odailyquests.admin` |
| `/qadmin complete <player> <id>` | Force completes a quest for a player | `odailyquests.admin` |

## 📖 Documentation
[Read the full Wiki](<https://ordwenplugins.gitbook.io/odailyquests/>) for setup, configuration, and examples.

## 💬 Community
Need help or want to share feedback? [Join our Discord](<https://discord.gg/Q8sw5KFvqg](https://discord.com/invite/NPAUE7kTgJ>).

## 🔗 Downloads
- 📥 **Spigot resource page**: [O'DailyQuests on SpigotMC](<https://www.spigotmc.org/resources/odailyquests-daily-quests-plugin-1-16-1-19.100990/>)
- 📥 **Modrinth resource page**: [O'DailyQuests on Modrinth](<https://modrinth.com/plugin/odailyquests>)
- 🧩 **Addons**:
  - [PyroFishingPro Support](<https://github.com/Ordwen/ODQ-PyroFishingPro/releases>)

## ☕ Support the project
If you enjoy O'DailyQuests and want to support future development, you can:

- [![ko-fi](https://ko-fi.com/img/githubbutton_sm.svg)](https://ko-fi.com/I2I41CRIJI)  
- [![PayPal](https://img.shields.io/badge/PayPal-Donate-blue?logo=paypal&style=flat-square)](https://www.paypal.com/paypalme/ordwen)  

⭐ Don’t forget to **leave a 5-star review on [SpigotMC](https://www.spigotmc.org/resources/odailyquests-daily-quests-plugin-1-16-1-19.100990/reviews/)** — it really helps the project grow!



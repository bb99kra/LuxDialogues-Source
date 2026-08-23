<div align="center">

![LuxDialogues Infographic](https://img.builtbybit.com/BfJVuWChy9oKZGYO_lAwtFKIMIJhDxc8SqlgHGSrWw8/resize:fill:2000/format:webp/aHR0cHM6Ly9pLmliYi5jby9qWnlXS1FWMC9rMWI3aXp4LnBuZw)

# 💬 LuxDialogues | Interactive Dialogues

<p align="center">
  <b>A fully customizable plugin for creating immersive interactive dialogs and cinematic NPC conversations</b>
</p>

[![Java](https://img.shields.io/badge/Java-17%20%7C%2021-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.17--1.21.x-35A853?style=for-the-badge&logo=minecraft&logoColor=white)](https://papermc.io/)
[![Platform](https://img.shields.io/badge/Platform-Bukkit%20%7C%20Spigot%20%7C%20Paper%20%7C%20Purpur%20%7C%20Folia-4285F4?style=for-the-badge)](https://papermc.io/)
[![BuiltByBit](https://img.shields.io/badge/BuiltByBit-60954-0084FF?style=for-the-badge)](https://builtbybit.com/resources/luxdialogues-interactive-dialogues.60954/)
[![Documentation](https://img.shields.io/badge/Wiki-Documentation-blue?style=for-the-badge&logo=gitbook&logoColor=white)](https://wiki.aselstudios.com/luxdialogues/first-install)
[![Discord](https://img.shields.io/badge/Discord-Join%20Support-5865F2?style=for-the-badge&logo=discord&logoColor=white)](https://discord.gg/ZmKvtBgftG)

---

[![Documentation Banner](https://img.builtbybit.com/22ZWrdul8E3x2jkErhY2U0en9evNYfxudJcKMQh64LM/resize:fill:2000/format:webp/aHR0cHM6Ly9pLmliYi5jby9ZRms1d1dURC85aDdkbXQ1LnBuZw)](https://wiki.aselstudios.com/luxdialogues/first-install)

---

[![Discord Banner](https://img.builtbybit.com/lUvvE7vps34S7LzgcjmOkziuaqviK3kUbX5ix9D5uus/resize:fill:2000/format:webp/aHR0cHM6Ly9pLmliYi5jby9OMjVLR01iTC9mYngwbHZpLnBuZw)](https://discord.gg/ZmKvtBgftG)

---

</div>

## 🌟 Overview & Key Features

**LuxDialogues** empowers server creators to build cinematic, story-driven gameplay experiences through interactive NPC dialogues, custom fonts, branching choices, and automated action triggers.

* 💬 **Interactive Dialogue Trees:** Create multi-branching stories with player choices, conditional paths, and action callbacks.
* 🎨 **Integrated Resource Pack Generator:** Automatically compiles custom font characters, speech bubble textures, and accented glyphs into your server's resource pack.
* 🎙️ **Voice Lines & Audio Synchrony:** Attach custom sound effects, typing sounds, and voice overs to dialogues.
* ⚡ **Folia & Multi-Threaded Engine:** Fully optimized for Folia's regional scheduler as well as standard Paper/Purpur/Spigot servers.
* 🔌 **Rich Plugin Ecosystem Hooks:** Seamless integration with **Citizens**, **MythicMobs**, **MMOCore**, **AuraSkills**, **CustomNameplates**, and **PlaceholderAPI**.
* 💻 **Public Developer API:** Clean event-driven API (`LuxDialoguesAPI`) for starting, redirecting, and responding to dialogue interactions.

---

## 📋 Commands & Permissions

| Command | Aliases | Description | Permission |
| :--- | :--- | :--- | :--- |
| `/luxdialogues` | `/dialogues`, `/ld` | Main plugin information and command menu | `luxdialogues.admin` |
| `/ld reload` | | Reload configuration, dialogues, and language files | `luxdialogues.admin` |
| `/ld start <player> <dialogue>` | | Start a dialogue sequence for a player | `luxdialogues.admin` |
| `/ld stop <player>` | | Stop the active dialogue for a player | `luxdialogues.admin` |
| `/ld test <dialogue>` | | Preview and test a dialogue sequence on yourself | `luxdialogues.admin` |

> 🔔 **Player Permission:** `luxdialogues.use` (Allows interacting with dialogue prompts)

---

<div align="center">

## 🔗 Official Links & Resources

* 📖 **Official Documentation:** [https://wiki.aselstudios.com/luxdialogues](https://wiki.aselstudios.com/luxdialogues/first-install)
* 💬 **Discord Community:** [https://discord.gg/ZmKvtBgftG](https://discord.gg/ZmKvtBgftG)
* 🛒 **BuiltByBit Resource:** [https://builtbybit.com/resources/luxdialogues-interactive-dialogues.60954/](https://builtbybit.com/resources/luxdialogues-interactive-dialogues.60954/)

---

### 💻 Build from Source (Maven)

```bash
# Clone the repository
git clone https://github.com/bb99kra/LuxDialogues-Source.git

# Enter repository directory
cd LuxDialogues-Source

# Build with Maven
mvn clean package
```

The output JAR file will be available in `target/LuxDialogues-3.0.5.jar`.

</div>

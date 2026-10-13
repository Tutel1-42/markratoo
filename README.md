# 🥓 Virtual Maakratoo

> Enjoy the soothing vibe of Mookrata right on your desk.

Virtual Maakratoo is a desktop grilling game made by two Mookrata enthusiasts. 

<!-- Add a banner or gameplay GIF here, e.g. ![Gameplay](docs/images/gameplay.gif) -->

## Table of Contents

- [Features](#features)
- [How to Play](#how-to-play)
- [Scoring](#scoring)
- [Screens](#screens)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Class Diagram](#class-diagram)
- [Authors](#authors)

## Features

- 🔥 Grill and boil a variety of ingredients on a Mookrata pan
- 🔊 Audio cues: listen for the sizzle to know when food is ready to flip or eat
- 🗑️ Burnt food can't be eaten, so you must throw it in the trash
- ⏱️ Live timer and score display for speedrunning
- 🧭 Main menu with tutorial and credits scenes

### Available Ingredients

Sliced Ribeye, Sliced Tongue, Sliced Pork, Bacon, Shrimp, Spinach, Chinese Cabbage, Sliced Salmon, Watermelon, French Fries

## How to Play

1. **Start the game.** Click **Start Game** on the main menu.
2. **Choose your items.** Click the 📍 pin icon on a tray and pick an ingredient from the dropdown.
3. **Start grilling.** Grab items and place them on the pan. The middle area of the pan is for grilling, and the two boiling areas are for boiling.
4. **Watch and listen.** When you hear the sizzle, the food is ready to be flipped or eaten. Press **Space** to flip.
5. **Eat.** Place edible food on the plate and it is eaten immediately, earning you points.
6. **Clean up burnt food.** If an item burns, it's no longer edible. Drag it to the 🗑️ trash icon to dispose of it.

The tray in the middle can be used for temporary food placement.

## Scoring

- ✅ You **gain points** for every edible item you eat.
- ❌ You **lose points** for every burnt item.

The timer keeps counting while you play, so you can track your score against the clock.

## Screens

| Scene | Description |
| --- | --- |
| **Main Menu** | Start game, tutorial, credits, and exit buttons |
| **Game** | The grill, ingredient trays, plate, trash, timer, and score |
| **Tutorial** | A quick guide to the controls and game elements |
| **Credits** | The game creators |

The tutorial and credits scenes each have a 🏠 home button in the top-left corner that returns you to the main menu.

<!-- Add screenshots, e.g.
![Main Menu](docs/images/main-menu.png)
![Gameplay](docs/images/gameplay.png)
-->

## Getting Started

### Prerequisites

<!-- Update these to match your project -->
- Java JDK (version used by the project)
- JavaFX SDK (if not bundled with your build tool)
- Maven or Gradle (if applicable)

### Installation

```bash
# Clone the repository
git clone https://github.com/YOUR-USERNAME/YOUR-REPO-NAME.git
cd YOUR-REPO-NAME
```

### Run the game

<!-- Replace with the real command for your build setup -->
```bash
# Example (Maven)
mvn clean javafx:run
```

## Project Structure

<!-- Update this tree to match your actual folders -->
```
.
├── src/            # Game source code
├── res/            # Images, fonts, and sound effects
├── docs/           # Documentation, screenshots, class diagram
└── README.md
```

## Class Diagram

The full UML class diagram of the project is available in [`docs/`](docs/).

<!-- ![Class Diagram](docs/images/class-diagram.png) -->

## Authors
 
- **Sajakorn Hiranwipas**
- **Rahut Nopnirapath**

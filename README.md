# Arkanoid-Breakout

A simple Arkanoid / Breakout style game written in Java using the biuoop GUI library.

## 1. Prerequisites
* JDK 8+ (javac and java on PATH)
* Apache Ant (optional but recommended – build.xml provided)

## 2. Compile
### Using Ant (recommended)
In the project root:
```
ant compile
```
Class files are emitted to `bin/`.

### Clean build
```
ant clean compile
```

### Manual (without Ant)
From the project root (Windows PowerShell):
```
mkdir bin
javac -cp biuoop-1.4.jar -d bin src\**\*.java
```

## 3. Run
### Using Ant
```
ant run
```
You can optionally pass arguments (if later extended) like:
```
ant run -Dargs="1 3 4"
```
The above would run only specified level numbers (example extension — adjust if you implement argument parsing in `Ass6Game`).

### Manual (without Ant)
```
java -cp bin;biuoop-1.4.jar Ass6Game
```
On Unix-like shells replace the classpath separator `;` with `:`.

## 4. Controls
* Left / Right arrows: Move paddle
* P: Pause (then press space to resume if pause screen uses space)
* Space / Enter: Start where applicable

## 5. Directory Structure
```
├── build.xml              # Ant build script
├── biuoop-1.4.jar         # External GUI/input library
├── src/                   # Source code
│   ├── Ass6Game.java      # Main entry point
│   ├── background/        # Level background sprites
│   ├── gamesetting/       # Core game engine classes (loop, environment, flow)
│   ├── geometry/          # Geometric primitives (Point, Line, Rectangle, Velocity, ...)
│   ├── interfaces/        # Abstractions (Sprite, Collidable, Animation, etc.)
│   ├── level/             # Level definitions (layout & configuration)
│   ├── listeners/         # Hit / score listeners
│   ├── screens/           # Animation screens (pause, countdown, game over, victory)
│   └── sprites/           # Concrete sprite implementations (Ball, Block, Paddle, etc.)
└── bin/                   # Compiled .class files (generated)
```

## 6. Level Overviews
Add screenshots later by placing image files (e.g., under `docs/images/`) and updating the markdown below.

### Level 1 – Remembering Sunday
![Level 1](pics/level%201.png)

### Level 2 – Hey Monday
![Level 2](pics/level%202.png)

mbv

### Level 3 – Charlotte And Tuesday
![Level 3](pics/level%203.png)

### Level 4 – Final Test
![Level 4](pics/level%204.png)

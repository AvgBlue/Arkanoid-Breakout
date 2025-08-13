package gamesetting;


import java.util.List;

import biuoop.GUI;
import interfaces.LevelInformation;
import screens.VictoryScreen;
import screens.GameOverScreen;
import biuoop.KeyboardSensor;
import screens.KeyPressStoppableAnimation;

/**
 * The type Game flow.
 *

 */
public class GameFlow {


    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter currentScore;
    private boolean isWin;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the ar
     * @param ks  the ks
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.gui = gui;
        this.currentScore = new Counter(0);
        this.isWin = true;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.currentScore);
            level.initialize();
            //we run if we have balls and block to destroy
            while (level.getBallCounter().getValue() != 0 && level.getBlockCounter().getValue() != 0) {
                level.run();
            }
            if (level.getBallCounter().getValue() == 0) {
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new GameOverScreen(this.currentScore)));
                this.isWin = false;
                break;
            }
        }
        if (this.isWin) {
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new VictoryScreen(this.currentScore)));
        }
        gui.close();
    }
}

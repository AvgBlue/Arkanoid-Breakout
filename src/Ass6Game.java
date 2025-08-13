import gamesetting.AnimationRunner;
import gamesetting.Const;
import gamesetting.GameFlow;
import biuoop.GUI;
import biuoop.Sleeper;
import interfaces.LevelInformation;
import biuoop.KeyboardSensor;
import level.Level1;
import level.Level2;
import level.Level3;
import level.Level4;

import java.util.ArrayList;
import java.util.List;

/**

 */
public class Ass6Game {

    /**
     * get a string and make it in to number(int).
     *
     * @param str the string
     * @return the number after it was converted
     */
    public static int strToInt(String str) throws Exception {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        List<LevelInformation> levelList = new ArrayList<>();
        for (String str : args) {
            try {
                int num = Integer.parseInt(str);
                if (num == 1) {
                    levelList.add(new Level1());
                }
                if (num == 2) {
                    levelList.add(new Level2());
                }
                if (num == 3) {
                    levelList.add(new Level3());
                }
                if (num == 4) {
                    levelList.add(new Level4());
                }
            } catch (Exception ignore) {
            }
        }
        if (levelList.size() == 0) {
            levelList.add(new Level1());
            levelList.add(new Level2());
            levelList.add(new Level3());
            levelList.add(new Level4());
        }
        GUI gui = new GUI("Alleyway", Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
        AnimationRunner ar = new AnimationRunner(gui, Const.FRAME_SPER_SECOND, new Sleeper());
        KeyboardSensor ky = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(ar, ky, gui);
        gameFlow.runLevels(levelList);

    }
}

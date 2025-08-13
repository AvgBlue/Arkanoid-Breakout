package level;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


import interfaces.LevelInformation;
import sprites.Block;
import geometry.Point;
import gamesetting.Const;


abstract class EmptyLevel implements LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    /**
     * return the blocks that make the borders of the game.
     *
     * @return the list
     */
    public List<Block> borders() {
        List<Block> list = new ArrayList<>();
        Block blockUP = new Block(Const.GAME_ORIGIN, Const.SCREEN_WIDTH, Const.BORDER_SIZE, Color.gray);
        Block blockLeft = new Block(new Point(Const.SCREEN_WIDTH - Const.BORDER_SIZE, Const.GAME_ORIGIN.getY()),
                Const.BORDER_SIZE, Const.SCREEN_HEIGHT, Color.gray);
        Block blockRight = new Block(Const.GAME_ORIGIN, Const.BORDER_SIZE, Const.SCREEN_HEIGHT, Color.gray);
        list.add(blockUP);
        list.add(blockLeft);
        list.add(blockRight);
        return list;
    }

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return the int
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    @Override
    public List<String> description() {
        List<String> list = new ArrayList<>();
        list.add("destroy all of the blocks");
        return list;
    }
}

package gamesetting;

import java.awt.Color;
import java.util.List;

import biuoop.DrawSurface;
import listeners.BlockRemover;
import listeners.BallRemover;
import listeners.ScoreTrackingListener;
import screens.CountdownAnimation;
import screens.PauseScreen;
import screens.KeyPressStoppableAnimation;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreIndicator;
import geometry.Point;
import geometry.Velocity;
import biuoop.KeyboardSensor;
import interfaces.Animation;
import interfaces.LevelInformation;
import interfaces.HitListener;
import interfaces.Collectables;

/**
 * The type Game.
 *

 */
public class GameLevel extends Collector implements Animation, Collectables {

    private static final int NUMBER_OF_SECONDS_COUNT_COUNTDOWN = 3;
    private static final int COUNT_FROM_COUNTDOWN = 3;

    private Paddle paddle;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter currentScore;

    private LevelInformation levelInfo;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private boolean running;
    private boolean isCheat = true;

    /**
     * Instantiates a new Game level.
     *
     * @param levelInfo    the level info
     * @param keyboard     the keyboard
     * @param runner       the runner
     * @param currentScore the current score
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboard,
                     AnimationRunner runner, Counter currentScore) {
        super();

        this.currentScore = currentScore;
        this.levelInfo = levelInfo;
        this.runner = runner;
        this.keyboard = keyboard;
        this.running = false;
    }


    /**
     * Initialize a new game: create the Blocks and gameObject.sprites.Ball (and gameObject.sprites.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        //add the borders
        List<Block> bordersList = levelInfo.borders();
        for (Block block : bordersList) {
            block.addToGame(this);
        }
        //add the Counters
        this.blockCounter = new Counter(levelInfo.numberOfBlocksToRemove());
        this.ballCounter = new Counter(levelInfo.numberOfBalls());
        //add the score Indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(Const.ORIGIN, Const.SCREEN_WIDTH,
                Const.BORDER_SIZE, Color.lightGray, currentScore, levelInfo.levelName());
        addSprite(scoreIndicator);
        //add the death deathRegion
        HitListener ballRemover = new BallRemover(this, ballCounter);
        Block deathRegion = new Block(new Point(Const.GAME_ORIGIN.getX(), Const.SCREEN_HEIGHT),
                Const.SCREEN_WIDTH, Const.BORDER_SIZE, Color.black);
        deathRegion.addHitListener(ballRemover);
        addCollidable(deathRegion);
        //add the blocks
        HitListener blockRemover = new BlockRemover(this, blockCounter);
        HitListener scoreIncreaser = new ScoreTrackingListener(currentScore);
        List<Block> blockList = levelInfo.blocks();
        for (Block block : blockList) {
            block.addHitListener(scoreIncreaser);
            block.addHitListener(blockRemover);
            block.addToGame(this);
        }
        //add the paddle
        this.paddle = new Paddle(new Point((double) (Const.SCREEN_WIDTH) / 2 - (double) (levelInfo.paddleWidth()) / 2,
                Const.PADDLE_Y), levelInfo.paddleWidth(), levelInfo.paddleSpeed(), Color.yellow);
        paddle.addToGame(this);
        //add the balls
        List<Velocity> ballVelocityList = levelInfo.initialBallVelocities();
        for (Velocity velocity : ballVelocityList) {
            Ball ball = new Ball(Const.BALL_START_POINT, Const.BALL_SIZE, Color.WHITE);
            ball.setVelocity(velocity);
            ball.addToGame(this);
            ball.setGameEnvironment(getEnvironment());
        }
    }


    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(NUMBER_OF_SECONDS_COUNT_COUNTDOWN, COUNT_FROM_COUNTDOWN,
                getSprites(), this.levelInfo.getBackground()));
        this.paddle.setKeyboard(this.keyboard);
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if ((this.keyboard.isPressed("c") || this.keyboard.isPressed("C")
                || this.keyboard.isPressed("ב")) & isCheat) {
            for (int i = 0; i < 360; i++) {
                Ball ball = new Ball(Const.CENTER_OF_THE_SCREEN, Const.BALL_SIZE,
                        Const.COLOR_WHEEL[i % Const.COLOR_WHEEL.length]);
                ball.setVelocity(Velocity.fromAngleAndSpeed(i, i % Const.COLOR_WHEEL.length + 3));
                ball.addToGame(this);
                ball.setGameEnvironment(getEnvironment());
                this.ballCounter.increase(1);
            }
            isCheat = false;
        }
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")
                || this.keyboard.isPressed("פ")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(getSprites(), this.levelInfo.getBackground(),
                            this.levelInfo.description())));
        }
        if (this.blockCounter.getValue() == 0) {
            currentScore.increase(Const.CLEAR_LEVEL_BONUS);
        }
        if (this.blockCounter.getValue() == 0 || this.ballCounter.getValue() == 0) {
            this.running = false;
        }
        //draw background
        Const.setGameKeyboard(this.keyboard);
        if (this.levelInfo.getBackground() != null) {
            this.levelInfo.getBackground().drawOn(d);
            this.levelInfo.getBackground().timePassed();
        }
        //draw level
        getSprites().drawAllOn(d);
        getSprites().notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Gets block counter.
     *
     * @return the block counter
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }

    /**
     * Gets ball counter.
     *
     * @return the ball counter
     */
    public Counter getBallCounter() {
        return ballCounter;
    }
}

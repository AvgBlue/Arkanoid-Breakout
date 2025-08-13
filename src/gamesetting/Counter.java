package gamesetting;

/**
 * The type Counter.
 *

 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Counter.
     *
     * @param counter the counter
     */
    public Counter(int counter) {
        this.counter = counter;
    }

    /**
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * get current count.
     *
     * @return the int
     */
    public int getValue() {
        return this.counter;
    }
}

package edu.up.swolemate;

/**
 * Created by Nathan on 1/21/2015.
 */
public class CardioWorkout extends BaseWorkout {
    /**
     * The duration of the workout in seconds
     */
    protected double duration;

    /**
     * The distance of the workout in miles
     */
    protected double distance;


    public CardioWorkout() {
        super();
    }


    public CardioWorkout(String name) {
        super(name);
    }

    public CardioWorkout(String name, double duration) {
        super(name);
        this.duration = duration;
    }
}

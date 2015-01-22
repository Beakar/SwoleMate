package edu.up.swolemate;

/**
 * Created by Nathan on 1/21/2015.
 */
public class Exercise {

    protected int id;

    protected String name;

    protected int numSets;

    protected int numReps;

    public Exercise() {

    }

    public Exercise(String exerciseName) {
        this.name = exerciseName;
    }

    public String getName() {
        return this.name;
    }
}

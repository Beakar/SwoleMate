package edu.up.swolemate;

/**
 * Created by Nathan on 1/21/2015.
 */
public class Exercise {

    /**
     * id of the exercise, for database usage
     */
    protected int id;

    /**
     * display name of the exercise
     */
    protected String displayName;

    /**
     * number of sets for the exercise
     */
    protected int numSets;

    /**
     * number of repetitions per set for the exercise
     */
    protected int numReps;

    /**
     * initializes an empty exercise object
     */
    public Exercise() {

    }

    /**
     * initializes an exercise with the specified name
     * @param exerciseName
     */
    public Exercise(String exerciseName) {
        this.displayName = exerciseName;
    }

    /**
     * returns the name of the exercise
     * @return
     */
    public String getName() {
        return this.displayName;
    }

    /**
     * Set the name of the exercise
     * @param name
     */
    public void setName(String name) {
        this.displayName = name;
    }

    /**
     * Get the number of sets in the exercise
     * @return
     */
    public int getNumSets() {
        return numSets;
    }

    /**
     * Set the number of sets in the exercise
     * @param numSets
     */
    public void setNumSets(int numSets) {
        this.numSets = numSets;
    }

    /**
     * Get the number of reps in the per set in the exercise
     * @return
     */
    public int getNumReps() {
        return numReps;
    }

    /**
     * Set the number of reps per set in the exercise
     * @param numReps
     */
    public void setNumReps(int numReps) {
        this.numReps = numReps;
    }
}

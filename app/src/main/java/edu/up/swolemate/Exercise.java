package edu.up.swolemate;

import java.util.List;

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
     * sets for the exercise
     */
    protected List<ExerciseSubset> sets;

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
     * Adds a set to the exercise
     * @param set
     */
    public void addSet(ExerciseSubset set) {
        this.sets.add(set);
    }

    /**
     * Gets the list of sets for this exercise.
     * @return
     */
    public List<ExerciseSubset> getSets() {
        return sets;
    }

    /**
     * Sets the list of sets for this exercise.
     * @param sets
     */
    public void setSets(List<ExerciseSubset> sets) {
        this.sets = sets;
    }

}

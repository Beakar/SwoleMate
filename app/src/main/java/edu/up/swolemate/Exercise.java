package edu.up.swolemate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 1/21/2015.
 */
public class Exercise {

     //id of the exercise, for database usage
    protected int id;
    // the number of sets in this exercise
    private int numSets = 0;
    //display name of the exercise
    protected String displayName;
    //sets for the exercise
    protected List<ExerciseSubset> sets;

    /**
     * initializes an empty exercise object
     */
    public Exercise() {
        sets = new ArrayList<ExerciseSubset>();
    }

    /**
     * initializes an exercise with the specified name
     * @param exerciseName
     */
    public Exercise(String exerciseName) {
        this.displayName = exerciseName;
        sets = new ArrayList<ExerciseSubset>();
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
        numSets++;
        set.setSetNum(numSets);
        this.sets.add(set);
    }


    public void deleteSet(ExerciseSubset set){
        numSets--;
       // set.setSetNum(numSets);
        this.sets.remove(set);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

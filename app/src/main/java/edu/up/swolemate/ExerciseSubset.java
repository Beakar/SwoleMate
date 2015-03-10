package edu.up.swolemate;

/**
 * Created by Nathan on 3/2/2015.
 */
public class ExerciseSubset {
    protected int id;

    /**
     * Weight for subset
     */
    protected double weight;

    protected int numReps;

    public ExerciseSubset(double weight, int numReps) {
        this.weight = weight;
        this.numReps = numReps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getNumReps() {
        return numReps;
    }

    public void setNumReps(int numReps) {
        this.numReps = numReps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

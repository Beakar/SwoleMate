package edu.up.swolemate;

/**
 * Created by Nathan on 3/2/2015.
 */
public class ExerciseSubset {
    protected int id;
    protected int setNum;  //what number this set is inside the exercise (i.e. first set, second set...)
    /**
     * Weight for subset
     */
    protected double weight;

    protected int numReps;

    public ExerciseSubset(double weight, int numReps) {
        this.weight = weight;
        this.numReps = numReps;
    }

    public void setSetNum(int num){
        setNum = num;
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
}

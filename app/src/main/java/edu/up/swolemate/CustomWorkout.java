package edu.up.swolemate;

/**
 * Created by Nathan on 1/21/2015.
 */
public class CustomWorkout extends BaseWorkout {

    /**
     * String containing information about this workout
     */
    protected String workoutData;

    /**
     * initializes an empty custom workout
     */
    public CustomWorkout() {

    }

    /**
     * Initializes a CustomWorkout object with the specified name
     * @param name
     */
    public CustomWorkout(String name) {
        super(name);
    }

    /**
     * returns the workout data for the workout object
     * @return
     */
    public String getWorkoutData() {
        return workoutData;
    }

    /**
     * sets the workout data for the workout object
     * @param workoutData
     */
    public void setWorkoutData(String workoutData) {
        this.workoutData = workoutData;
    }

    /**
     * Initializes a CustomWorkout object with the specified name and data string
     * @param name
     * @param workoutData
     */
    public CustomWorkout(String name, String workoutData) {
        super(name);

        this.workoutData = workoutData;
    }
}

package edu.up.swolemate;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Daniel on 4/2/2015.
 */
public class ExercisePresets {
    protected ArrayList<Exercise> aerobicPresets;
    protected ArrayList<Exercise> strengthPresets;
    protected ArrayList<Exercise> exercisePresets;

    public ExercisePresets(){
        strengthPresets = new ArrayList<Exercise>();
        aerobicPresets = new ArrayList<Exercise>();
        exercisePresets = new ArrayList<Exercise>();

        /*
         * Add the strength workout presets
         */
        Exercise arnoldPressSeated = new Exercise("Arnold Press (seated)");
        strengthPresets.add(arnoldPressSeated);

        Exercise arnoldPressStanding = new Exercise("Arnold Press (standing)");
        strengthPresets.add(arnoldPressStanding);

        Exercise benchPressBB = new Exercise("Bench Press (barbell)");
        strengthPresets.add(benchPressBB);

        Exercise benchPressDB = new Exercise("Bench Press (dumbbell)");
        strengthPresets.add(benchPressDB);

        Exercise benchPressMachine = new Exercise("Bench Press (machine)");
        strengthPresets.add(benchPressMachine);

        Exercise bentOverRowBB = new Exercise("Bent OVer Row (barbell)");
        strengthPresets.add(bentOverRowBB);

        Exercise bentOverRowBBSupinated = new Exercise("Bent Over Row (barbell, supinated grip)");
        strengthPresets.add(bentOverRowBBSupinated);

        Exercise bentOverRowBBWide = new Exercise("Bent Over Row (barbell, wide grip)");
        strengthPresets.add(bentOverRowBBWide);

        Exercise bentOverRowDB = new Exercise("Bent Over Row (dumbbell");
        strengthPresets.add(bentOverRowDB);

        Exercise bentOverRowDBSupinated = new Exercise("Bent Over Row (dumbbell, supinated grip");
        strengthPresets.add(bentOverRowDBSupinated);

        Exercise boxSquat = new Exercise("Box Squat");
        strengthPresets.add(boxSquat);

        Exercise cableFly = new Exercise("Cable Fly");
        strengthPresets.add(cableFly);

        Exercise cableFlyDecline = new Exercise("Cable Fly (decline)");
        strengthPresets.add(cableFlyDecline);

        Exercise cableFlyIncline = new Exercise("Cable Fly (incline)");
        strengthPresets.add(cableFlyIncline);

        Exercise cablePulldown = new Exercise("Cable Pulldown");
        strengthPresets.add(cablePulldown);

        Exercise cablePulldownClose = new Exercise("Cable Pulldown (close grip");
        strengthPresets.add(cablePulldownClose);

        Exercise cablePulldownSupinated = new Exercise("Cable Pulldown (supinated grip)");
        strengthPresets.add(cablePulldownSupinated);

        Exercise cablePulldownWide = new Exercise("Cable Pulldown (wide grip)");
        strengthPresets.add(cablePulldownWide);

        Exercise cableSideLateral = new Exercise("Cable Side Lateral");
        strengthPresets.add(cableSideLateral);

        Exercise cableSideLateralSingle = new Exercise("Cable Side Lateral (single-arm)");
        strengthPresets.add(cableSideLateralSingle);

        Exercise calfRaiseSeated = new Exercise("Calf Raise (seated)");
        strengthPresets.add(calfRaiseSeated);

        Exercise calfRaiseStanding = new Exercise("Calf Raise (standing");
        strengthPresets.add(calfRaiseStanding);

        Exercise cleanAndJerk = new Exercise("Clean and Jerk");
        strengthPresets.add(cleanAndJerk);

        Exercise cleanAndPressBB = new Exercise("Clean and Press (barbell)");
        strengthPresets.add(cleanAndPressBB);

        Exercise cleanAndPressDB = new Exercise("Clean and Press (dumbbell)");
        strengthPresets.add(cleanAndPressDB);

        Exercise curlsBB = new Exercise("Curls (barbell)");
        strengthPresets.add(curlsBB);

        Exercise curlsDB = new Exercise("Curls (dumbbell)");
        strengthPresets.add(curlsDB);

        Exercise curlsBBPronated = new Exercise("Curls (barbell, pronated grip)");
        strengthPresets.add(curlsBBPronated);

        Exercise curlsDBPronated = new Exercise("Curls (dumbbell, pronated grip)");
        strengthPresets.add(curlsDBPronated);

        Exercise curlsHammer = new Exercise("Curls (hammer grip)");
        strengthPresets.add(curlsHammer);

        Exercise curlsIncline = new Exercise("Curls (incline)");
        strengthPresets.add(curlsIncline);

        Exercise curlsInclinePronated = new Exercise("Curls (incline, pronated grip)");
        strengthPresets.add(curlsInclinePronated);

        Exercise curlsInclineHammer = new Exercise("Curls (incline, hammer grip)");
        strengthPresets.add(curlsInclineHammer);

        Exercise curlsTwisting = new Exercise("Curls (twisting)");
        strengthPresets.add(curlsTwisting);

        Exercise dbFly = new Exercise("Dumbbell Fly");
        strengthPresets.add(dbFly);

        Exercise dbFlyDecline = new Exercise("Dumbbell Fly (decline)");
        strengthPresets.add(dbFlyDecline);

        Exercise dbFlyIncline = new Exercise("Dumbbell Fly (incline)");
        strengthPresets.add(dbFlyIncline);

        Exercise dbRow = new Exercise("Dumbbell Row");
        strengthPresets.add(dbRow);

        Exercise deadliftBB = new Exercise("Deadlift (barbell)");
        strengthPresets.add(deadliftBB);

        Exercise deadliftDB = new Exercise("Deadlift (dumbbell)");
        strengthPresets.add(deadliftDB);

        Exercise declineBenchPressBB = new Exercise("Decline Bench Press (barbell)");
        strengthPresets.add(declineBenchPressBB);

        Exercise declineBenchPressDB = new Exercise("Decline Bench Press (dumbbell)");
        strengthPresets.add(declineBenchPressDB);

        Exercise declineBenchPressMachine = new Exercise("Decline Bench Press (machine)");
        strengthPresets.add(declineBenchPressMachine);

        Exercise frontSquat = new Exercise("Front Squat");
        strengthPresets.add(frontSquat);

        Exercise hackSquat = new Exercise("Hack Squat");
        strengthPresets.add(hackSquat);

        Exercise inclineBenchPressBB = new Exercise("Incline Bench Press (barbell)");
        strengthPresets.add(inclineBenchPressBB);

        Exercise inclineBenchPressDB = new Exercise("Incline Bench Press (dumbbell)");
        strengthPresets.add(inclineBenchPressDB);

        Exercise inclineBenchPressMachine = new Exercise("Incline Bench Press (machine)");
        strengthPresets.add(inclineBenchPressMachine);

        Exercise jerk = new Exercise("Jerk");
        strengthPresets.add(jerk);

        Exercise legCurlLying = new Exercise("Leg Curl (lying)");
        strengthPresets.add(legCurlLying);

        Exercise legCurlSeated = new Exercise("Leg Curl (seated)");
        strengthPresets.add(legCurlSeated);

        Exercise legCurlStanding = new Exercise("Leg Curl (standing)");
        strengthPresets.add(legCurlStanding);

        Exercise legExtension = new Exercise("Leg Extension");
        strengthPresets.add(legExtension);

        Exercise legPress = new Exercise("Leg Press");
        strengthPresets.add(legPress);

        Exercise militaryPressSeated = new Exercise("Military Press (seated)");
        strengthPresets.add(militaryPressSeated);

        Exercise militaryPressStanding = new Exercise("Military Press (standing)");
        strengthPresets.add(militaryPressStanding);

        Exercise overheadExtensionBB = new Exercise("Overhead Extension (barbell)");
        strengthPresets.add(overheadExtensionBB);

        Exercise overheadExtensionDB = new Exercise("Overhead Extension (dumbbell)");
        strengthPresets.add(overheadExtensionDB);

        Exercise overheadExtensionDBSingle = new Exercise("Overhead Extension (dumbbell, single-arm)");
        strengthPresets.add(overheadExtensionDBSingle);

        Exercise preacherCurlBB = new Exercise("Preacher Curl (barbell)");
        strengthPresets.add(preacherCurlBB);

        Exercise preacherCurlDB = new Exercise("Preacher Curl (dumbbell)");
        strengthPresets.add(preacherCurlDB);

        Exercise preacherCurlDBSingle = new Exercise("Preacher Curl (dumbbell, single-arm)");
        strengthPresets.add(preacherCurlDBSingle);

        Exercise pullUps = new Exercise("Pull Ups");
        strengthPresets.add(pullUps);

        Exercise pullUpsNeutral = new Exercise("Pull Ups (neutral grip)");
        strengthPresets.add(pullUpsNeutral);

        Exercise pullUpsSupinated = new Exercise("Pull Ups (supinated grip)");
        strengthPresets.add(pullUpsSupinated);

        Exercise pullUpsWide = new Exercise("Pull Ups (wide grip)");
        strengthPresets.add(pullUpsWide);

        Exercise pushUps = new Exercise("Push Ups");
        strengthPresets.add(pushUps);

        Exercise pushUpsDiamond = new Exercise("Push Ups (diamond)");
        strengthPresets.add(pushUpsDiamond);

        Exercise rearCableFly = new Exercise("Rear Cable Fly");
        strengthPresets.add(rearCableFly);

        Exercise rearLateralRaise = new Exercise("Rear Lateral Raise");
        strengthPresets.add(rearLateralRaise);

        Exercise romanianDeadliftBB = new Exercise("Romanian Deadlift (barbell)");
        strengthPresets.add(romanianDeadliftBB);

        Exercise romanianDeadliftDB = new Exercise("Romanian Deadlift (dumbbell)");
        strengthPresets.add(romanianDeadliftDB);

        Exercise ropeCurl = new Exercise("Rope Curl");
        strengthPresets.add(ropeCurl);

        Exercise ropeExtension = new Exercise("Rope Extension");
        strengthPresets.add(ropeExtension);

        Exercise seatedCableRow = new Exercise("Seated Cable Row");
        strengthPresets.add(seatedCableRow);

        Exercise seatedCableRowClose = new Exercise("Seated Cable Row (close grip)");
        strengthPresets.add(seatedCableRowClose);

        Exercise seatedCableRowWide = new Exercise("Seated Cable Row (wide grip)");
        strengthPresets.add(seatedCableRowWide);

        Exercise shoulderPressBBSeated = new Exercise("Shoulder Press (barbell, seated)");
        strengthPresets.add(shoulderPressBBSeated);

        Exercise shoulderPressBBStanding = new Exercise("Shoulder Press (barbell, standing)");
        strengthPresets.add(shoulderPressBBStanding);

        Exercise shoulderPressDBSeated = new Exercise("Shoulder Press (dumbbell, seated)");
        strengthPresets.add(shoulderPressDBSeated);

        Exercise shoulderPressDBStanding = new Exercise("Shoulder Press (dumbbell, standing)");
        strengthPresets.add(shoulderPressDBStanding);

        Exercise shrugsBB = new Exercise("Shrugs (barbell)");
        strengthPresets.add(shrugsBB);

        Exercise shrugsDB = new Exercise("Shrugs (dumbbell)");
        strengthPresets.add(shrugsDB);

        Exercise sideLateralSeated = new Exercise("Side Lateral (seated)");
        strengthPresets.add(sideLateralSeated);

        Exercise sideLateralStanding = new Exercise("Side Lateral (standing)");
        strengthPresets.add(sideLateralStanding);

        Exercise snatch = new Exercise("Snatch");
        strengthPresets.add(snatch);

        Exercise straightLeggedDeadliftBB = new Exercise("Straight-Legged Deadlift (barbell)");
        strengthPresets.add(straightLeggedDeadliftBB);

        Exercise straightLeggedDeadliftDB = new Exercise("Straight-Legged Deadlift (dumbbell)");
        strengthPresets.add(straightLeggedDeadliftDB);

        Exercise squatsBB = new Exercise("Squats (barbell)");
        strengthPresets.add(squatsBB);

        Exercise squatsDB = new Exercise("Squats (dumbbell)");
        strengthPresets.add(squatsDB);

        Exercise tBarRowLying = new Exercise("T-Bar Row (lying)");
        strengthPresets.add(tBarRowLying);

        Exercise tBarRowLyingClose = new Exercise("T-Bar Row (lying, close grip)");
        strengthPresets.add(tBarRowLyingClose);

        Exercise tBarRowLyingWide = new Exercise("T-Bar Row (lying, wide grip)");
        strengthPresets.add(tBarRowLyingWide);

        Exercise tBarRowStanding = new Exercise("T-Bar Row (standing)");
        strengthPresets.add(tBarRowStanding);

        Exercise tBarRowStandingClose = new Exercise("T-Bar Row (standing, close grip)");
        strengthPresets.add(tBarRowStandingClose);

        Exercise tBarRowStandingWide = new Exercise("T-Bar Row (standing, wide grip)");
        strengthPresets.add(tBarRowStandingWide);

        Exercise tricepExtensionBBLying = new Exercise("Tricep Extension (lying, barbell)");
        strengthPresets.add(tricepExtensionBBLying);

        Exercise tricepExtensionCable = new Exercise("Cable Tricep Extension");
        strengthPresets.add(tricepExtensionCable);

        Exercise tricepExtensionCableSupinated = new Exercise("Cable Tricep Extension (supinated)");
        strengthPresets.add(tricepExtensionCableSupinated);

        /*
         * Add the aerobic exercise presets
         */
        Exercise biking = new Exercise("Biking");
        aerobicPresets.add(biking);

        Exercise running = new Exercise("Running");
        aerobicPresets.add(running);

        Exercise sprinting = new Exercise("Sprinting");
        aerobicPresets.add(sprinting);

        Exercise swimming = new Exercise("Swimming");
        aerobicPresets.add(swimming);

        Exercise walking = new Exercise("Walking");
        aerobicPresets.add(walking);

        /*
         * Initialize the master exercises ArrayList
         */
        initExerciseList();
    }


    /**
     * Gets the exercise with the given name.
     * @param idx -- The index of the exercise to get.
     * @return -- The Exercise matching the index given.
     */
    protected Exercise getExercise(int idx){
        return exercisePresets.get(idx);
    }


    /**
     * Returns all of the exercise names
     *
     * @param list -- The ArrayList to get the names from.
     * @return -- the list of exercise names.
     */
    protected ArrayList<String> getExerciseNames(ArrayList<Exercise> list){
        ArrayList<String> names = new ArrayList<String>();
        for(Exercise e : list){
            names.add(e.getName());
        }

        return names;
    }


    /**
     * Initializes the master list of all exercises.
     */
    private void initExerciseList(){
        for(Exercise e : aerobicPresets){
            exercisePresets.add(e);
        }
        for(Exercise e : strengthPresets){
            exercisePresets.add(e);
        }
    }
}

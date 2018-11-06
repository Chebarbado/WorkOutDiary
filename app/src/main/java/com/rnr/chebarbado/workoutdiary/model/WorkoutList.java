package com.rnr.chebarbado.workoutdiary.model;




import com.rnr.chebarbado.workoutdiary.R;

import java.util.ArrayList;
import java.util.List;

public class WorkoutList {
    private static WorkoutList ourInstance = null;

    List<Workout> workouts;

    public static WorkoutList getInstance() {
        if (ourInstance == null) {
            ourInstance = new WorkoutList();
            return ourInstance;
        } else {
            return ourInstance;
        }
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    private WorkoutList() {
        initWorkoutList();
    }

    private void initWorkoutList() {
        workouts = new ArrayList<>();
        workouts.add(new Workout(R.string.pushups, R.string.pushups_description, 0));
        workouts.add(new Workout(R.string.pullups, R.string.pullups_description, 0));
        workouts.add(new Workout(R.string.twist_on_the_floor, R.string.twist_on_the_floor_description, 0));
        workouts.add(new Workout(R.string.twisting_with_turning, R.string.twisting_with_turning_description, 0));
        workouts.add(new Workout(R.string.twisting_on_the_bench, R.string.twisting_on_the_bench_description, 0));
        workouts.add(new Workout(R.string.twisting_on_the_block, R.string.twisting_on_the_block_description, 0));
        workouts.add(new Workout(R.string.lateral_twisting, R.string.lateral_twisting_description, 0));
        workouts.add(new Workout(R.string.back_torsion, R.string.back_torsion_description, 0));
    }

}

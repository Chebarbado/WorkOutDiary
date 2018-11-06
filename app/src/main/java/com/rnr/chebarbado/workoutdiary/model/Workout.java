package com.rnr.chebarbado.workoutdiary.model;

public class Workout {
    public static final String WORKOUT_TYPE_KEY = "WORKOUT_TYPE_KEY";
    public static final int PUSHUPS = 0;
    public static final int PULLUPS = 1;


    int title;
    int description;
    int imageResId; //Пока не используется.
    int repCount;

    public int getTitle() {
        return title;
    }

    public int getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public int getRepCount() {
        return repCount;
    }

    public void setRepCount(int repCount) {
        this.repCount = repCount;
    }


    public Workout(int title, int description, int repCount) {
        this.title = title;
        this.description = description;
        this.repCount = repCount;
    }
}

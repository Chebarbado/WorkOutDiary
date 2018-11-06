package com.rnr.chebarbado.workoutdiary.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.rnr.chebarbado.workoutdiary.R;
import com.rnr.chebarbado.workoutdiary.interfaces.OnWorkoutListItemSelectedListener;
import com.rnr.chebarbado.workoutdiary.model.WorkoutList;


public class WorkoutDetailFragment extends Fragment {
    public static final String WORKOUT_INDEX = "WORKOUT_INDEX";
    private static String FRAGMENT_INSTANCE_NAME = "fragment";
    TimerFragment fragment = null;
    OnWorkoutListItemSelectedListener callbackActivity;

    //Поля активности
    private ImageButton plusButton;
    private ImageButton minusButton;
    private Button completeButton;
    private TextView workoutTitleTextView;
    private TextView repeatCountTextView;
    private TextView descriptionTextView;

    //Счетчик отжиманий
    private int repeatCount = 0;
    private int workoutIndex;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbackActivity = (OnWorkoutListItemSelectedListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workoutIndex = getArguments().getInt(WORKOUT_INDEX);
    }

    public static WorkoutDetailFragment newInstance(int workoutIndex) {
        WorkoutDetailFragment fragment = new WorkoutDetailFragment();
        Bundle args = new Bundle();
        args.putInt(WORKOUT_INDEX, workoutIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_workout, container, false);
        initUI(root);
        initTimerFragment();
        return root;
    }

    private void initUI(View root) {
        repeatCount = WorkoutList.getInstance().getWorkouts().get(workoutIndex).getRepCount();

        plusButton = (ImageButton) root.findViewById(R.id.plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repeatCount++;
                repeatCountTextView.setText(String.valueOf(repeatCount));
            }
        });
        minusButton = (ImageButton) root.findViewById(R.id.minus_button);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeatCount >= 1) {
                    repeatCount--;
                    repeatCountTextView.setText(String.valueOf(repeatCount));
                } else {
                    Toast.makeText(getActivity(), "В минус залезть нельзя", Toast.LENGTH_SHORT).show();
                }
            }
        });

        workoutTitleTextView = (TextView) root.findViewById(R.id.workout_title_text_view);
        workoutTitleTextView.setText(WorkoutList.getInstance().getWorkouts().get(workoutIndex).getTitle());

        repeatCountTextView = (TextView) root.findViewById(R.id.repeat_count_text_view);
        repeatCountTextView.setText(String.valueOf(repeatCount));

        descriptionTextView = (TextView) root.findViewById(R.id.workout_description_text_view);
        descriptionTextView.setText(WorkoutList.getInstance().getWorkouts().get(workoutIndex).getDescription());

        completeButton = (Button) root.findViewById(R.id.completeButton);
        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkoutList.getInstance().getWorkouts().get(workoutIndex).setRepCount(repeatCount);
                callbackActivity.refreshList();
            }
        });
    }

    private void initTimerFragment() {
        FragmentManager fm = getChildFragmentManager();
        fragment = new TimerFragment();
        fm.beginTransaction().replace(R.id.timer_container, fragment, FRAGMENT_INSTANCE_NAME).commit();
    }
}

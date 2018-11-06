package com.rnr.chebarbado.workoutdiary.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rnr.chebarbado.workoutdiary.R;


public class TimerFragment extends Fragment {
    private final static String SECONDS_KEY = "seconds_key";
    private final static String RUNNING_KEY = "running_key";
    private static final String WAS_RUNNING_KEY = "was_running_key";

    //Поля таймера
    private Button resetButton;
    private Button startButton;
    private Button stopButton;
    private TextView clockText;

    private int seconds;
    private boolean isRunning;
    private boolean wasRunning;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(SECONDS_KEY);
            isRunning = savedInstanceState.getBoolean(RUNNING_KEY);
            wasRunning = savedInstanceState.getBoolean(WAS_RUNNING_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_timer, container, false);
        initView(root);
        if (!wasRunning) {
            runTimer();
        }
        return root;
    }

    private void initView(View root) {
        resetButton = (Button) root.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
                seconds = 0;
            }
        });
        startButton = (Button) root.findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = true;
            }
        });
        stopButton = (Button) root.findViewById(R.id.stop_button);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = false;
            }
        });
        clockText = (TextView) root.findViewById(R.id.clock_text_view);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (wasRunning) {
            isRunning = true;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        wasRunning = isRunning;
        isRunning = false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(SECONDS_KEY, seconds);
        outState.putBoolean(RUNNING_KEY, isRunning);
        outState.putBoolean(WAS_RUNNING_KEY, wasRunning);
        super.onSaveInstanceState(outState);
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String hourStr = hours > 9 ? String.valueOf(hours) : String.valueOf("0" + hours);
                String minutesStr = minutes > 9 ? String.valueOf(minutes) : String.valueOf("0" + minutes);
                String secsStr = secs > 9 ? String.valueOf(secs) : String.valueOf("0" + secs);

                String time = hourStr + ":" + minutesStr + ":" + secsStr;
                clockText.setText(time);
                if (isRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}

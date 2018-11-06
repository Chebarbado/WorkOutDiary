package com.rnr.chebarbado.workoutdiary.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.rnr.chebarbado.workoutdiary.R;
import com.rnr.chebarbado.workoutdiary.interfaces.OnWorkoutListItemSelectedListener;


public class MainActivity extends AppCompatActivity implements OnWorkoutListItemSelectedListener {
    FragmentManager fm;
    WorkoutListFragment listFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getSupportFragmentManager();
        listFragment = new WorkoutListFragment();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fm.beginTransaction().replace(R.id.container, listFragment).commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            WorkoutDetailFragment detailFragment = WorkoutDetailFragment.newInstance(0);
            fm.beginTransaction().replace(R.id.list_container, listFragment).commit();
            fm.beginTransaction().replace(R.id.detail_container, detailFragment).commit();
        }
    }

    @Override
    public void onWorkoutListItemSelected(int position) {
        WorkoutDetailFragment detailFragment = WorkoutDetailFragment.newInstance(position);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fm.beginTransaction().replace(R.id.container, detailFragment).addToBackStack(null).commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fm.beginTransaction().replace(R.id.detail_container, detailFragment).commit();
        }
    }

    @Override
    public void refreshList() {
        listFragment.refreshList();
    }
}

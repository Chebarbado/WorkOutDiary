package com.rnr.chebarbado.workoutdiary.ui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.rnr.chebarbado.workoutdiary.R;
import com.rnr.chebarbado.workoutdiary.interfaces.OnWorkoutListItemSelectedListener;
import com.rnr.chebarbado.workoutdiary.model.Workout;
import com.rnr.chebarbado.workoutdiary.model.WorkoutList;

import java.util.List;

public class WorkoutListFragment extends Fragment{
    List<Workout> workouts;
    RecyclerView workoutList;
    WorkoutAdapter adapter;
    OnWorkoutListItemSelectedListener callbackActivity;

    public void refreshList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbackActivity = (OnWorkoutListItemSelectedListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_workout_list, container, false);
        workouts = WorkoutList.getInstance().getWorkouts();

        workoutList = (RecyclerView) root.findViewById(R.id.workout_list);
        adapter = new WorkoutAdapter(workouts);
        workoutList.setLayoutManager(new LinearLayoutManager(getActivity()));
        workoutList.setAdapter(adapter);
        return root;
    }



    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private class WorkoutAdapter extends RecyclerView.Adapter<WorkoutHolder> {
        List<Workout> workouts;

        public WorkoutAdapter(List<Workout> workouts) {
            this.workouts = workouts;
            notifyDataSetChanged();
        }

        @Override
        public WorkoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_list_item, parent, false);

            return new WorkoutHolder(listItemView);
        }

        @Override
        public void onBindViewHolder(final WorkoutHolder holder, int position) {
            holder.workOutTitleTextView.setText(workouts.get(position).getTitle());
            holder.lastRepeatCountTextView.setText(String.valueOf(workouts.get(position).getRepCount()));
            holder.listItemCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    WorkoutDetailFragment.startActivity(WorkoutListFragment.this, holder.getAdapterPosition());
                    callbackActivity.onWorkoutListItemSelected(holder.getAdapterPosition());
                }
            });
        }

        @Override
        public int getItemCount() {
            return workouts.size();
        }
    }

    private class WorkoutHolder extends RecyclerView.ViewHolder {
        TextView workOutTitleTextView;
        TextView lastRepeatCountTextView;
        CardView listItemCardView;

        public WorkoutHolder(View itemView) {
            super(itemView);
            workOutTitleTextView = (TextView) itemView.findViewById(R.id.workout_list_item_title_text_view);
            lastRepeatCountTextView = (TextView) itemView.findViewById(R.id.workout_list_item_last_count);
            listItemCardView = (CardView) itemView.findViewById(R.id.workout_list_item_card_view);
        }
    }
}

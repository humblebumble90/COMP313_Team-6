package com.example.fat2fit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fat2fit.models.Challenge;
import com.example.fat2fit.models.Workout;

import java.util.List;

public class WorkoutAdaptor extends RecyclerView.Adapter<WorkoutAdaptor.MyViewHolder> {

    Workout[] workoutChallenges;
    Context context;

    public WorkoutAdaptor(Context ct, Workout[] workouts){
        context = ct;
        workoutChallenges = workouts;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.workout_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Workout currentWorkout = workoutChallenges[position];
        holder.workoutName.setText(currentWorkout.getWorkoutName());
        holder.workoutHyperlink.setText(context.getString(R.string.workout_hyperlink) + currentWorkout.getVideoHyperlink());
        holder.workoutRate.setText(context.getString(R.string.workout_reps) + currentWorkout.getRepetition());
    }

    @Override
    public int getItemCount() {
        return workoutChallenges.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView workoutName;
        TextView workoutRate;
        TextView workoutHyperlink;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            workoutName = itemView.findViewById(R.id.workoutName);
            workoutHyperlink = itemView.findViewById(R.id.workoutHyperlink);
            workoutRate = itemView.findViewById(R.id.workoutReps);
        }
    }
}

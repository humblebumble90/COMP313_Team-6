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

<<<<<<< HEAD
    public WorkoutAdaptor(Context ct, Workout[] workouts){
=======
    public WorkoutAdaptor(Context ct, List<Challenge> workouts) {
>>>>>>> ba02cb51e2a38eee1ec6cf3752493edf1da2c676
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
<<<<<<< HEAD
        Workout currentWorkout = workoutChallenges[position];
        holder.workoutName.setText(currentWorkout.getWorkoutName());
        holder.workoutHyperlink.setText(context.getString(R.string.workout_hyperlink) + currentWorkout.getVideoHyperlink());
        holder.workoutRate.setText(context.getString(R.string.workout_hyperlink));
=======
        Challenge currentWorkout = workoutChallenges.get(position);
        holder.workoutName.setText(currentWorkout.getTitle());
        holder.workoutInstruction.setText(context.getString(R.string.coach_instructions_text) + currentWorkout.getDescription());
        //holder.workoutRate.setText();
        //holder.workoutProgress.setText();
        //holder.workoutType.setText();
        holder.workoutList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WorkoutDetailsActivity.class);
                intent.putExtra("name", "this has worked");
                intent.putExtra("detail", "this has worked");
                context.startActivity(intent);

            }
        });
>>>>>>> ba02cb51e2a38eee1ec6cf3752493edf1da2c676
    }

    @Override
    public int getItemCount() {
        return workoutChallenges.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

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
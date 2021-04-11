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

import java.util.List;

public class WorkoutAdaptor extends RecyclerView.Adapter<WorkoutAdaptor.MyViewHolder> {

    List<Challenge> workoutChallenges;
    Context context;

    public WorkoutAdaptor(Context ct, List<Challenge> workouts) {
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
    }

    @Override
    public int getItemCount() {
        return workoutChallenges.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView workoutName;
        TextView workoutInstruction;
        TextView workoutType;
        TextView workoutRate;
        TextView workoutProgress;
        RelativeLayout workoutList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            workoutName = itemView.findViewById(R.id.workoutName);
            workoutInstruction = itemView.findViewById(R.id.workoutInstruction);
            workoutList = itemView.findViewById(R.id.workoutList);
            workoutType = itemView.findViewById(R.id.workoutType);
            workoutRate = itemView.findViewById(R.id.workoutRate);
            workoutProgress = itemView.findViewById(R.id.workoutProgress);
        }
    }
}
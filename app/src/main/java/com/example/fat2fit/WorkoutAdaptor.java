package com.example.fat2fit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class WorkoutAdaptor extends RecyclerView.Adapter<WorkoutAdaptor.MyViewHolder> {

    String name[], detail[];
    Context context;

    public WorkoutAdaptor(Context ct, String workoutName[], String workoutDetails[]){
        context = ct;
        name = workoutName;
        detail = workoutDetails;

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
        holder.workoutName.setText(name[position]);
        holder.workoutDetails.setText(detail[position]);

        holder.workoutList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WorkoutDetailsActivity.class);
                intent.putExtra("name", name[position]);
                intent.putExtra("detail", detail[position]);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView workoutName;
        TextView workoutDetails;
        ConstraintLayout workoutList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            workoutName = itemView.findViewById(R.id.workoutName);
            workoutDetails = itemView.findViewById(R.id.workoutDetails);
            workoutList = itemView.findViewById(R.id.workoutList);
        }
    }
}

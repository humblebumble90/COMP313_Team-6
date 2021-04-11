package com.example.fat2fit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fat2fit.models.Challenge;

import java.util.List;

public class ChallengeAdaptor extends RecyclerView.Adapter<ChallengeAdaptor.MyViewHolder> {

    List<Challenge> challengeList;
    Context context;

    public ChallengeAdaptor(Context ct, List<Challenge> challenges){
        context = ct;
        challengeList = challenges;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.challenge_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Challenge currentChallenge = challengeList.get(position);
        holder.challengeTitle.setText(currentChallenge.getTitle());
        holder.challengeDescription.setText(context.getString(R.string.challenge_description) + currentChallenge.getDescription());
        holder.challengeDistance.setText(context.getString(R.string.challenge_distance) + currentChallenge.getDescription());
        holder.challengeDescription.setText(context.getString(R.string.challenge_state) + currentChallenge.getDescription());

//        holder.challengeListLayout.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, WorkoutDetailsActivity.class);
//                intent.putExtra("name", "this has worked");
//                intent.putExtra("detail", "this has worked");
//                context.startActivity(intent);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return challengeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView challengeTitle;
        TextView challengeDescription;
        TextView challengeDistance;
        TextView challengeState;
        RelativeLayout challengeListLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            challengeTitle = itemView.findViewById(R.id.challenge_details_title);
            challengeDescription = itemView.findViewById(R.id.challengeDescription);
            challengeDistance = itemView.findViewById(R.id.challengeDistance);
            challengeState = itemView.findViewById(R.id.challengeState);
            challengeListLayout = itemView.findViewById(R.id.challengeList);
        }
    }
}

package com.example.fat2fit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fat2fit.helpers.StringHelper;
import com.example.fat2fit.models.GroupActivity;

import java.util.List;

public class GroupActivityAdaptor
        extends RecyclerView.Adapter<GroupActivityAdaptor.ActivityViewHolder> {

    private final Context context;
    private final List<GroupActivity> activities;

    public GroupActivityAdaptor(Context context, List<GroupActivity> activities) {
        this.context = context;
        this.activities = activities;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.group_activity_list, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int pos) {
        holder.setGroupActivity(activities.get(pos));
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public static class ActivityViewHolder extends RecyclerView.ViewHolder {

        private GroupActivity activity;

        private final TextView titleTextView;
        private final TextView descTextView;
        private final Button watchVideoBtn;

        public ActivityViewHolder(@NonNull View v) {
            super(v);
            titleTextView = v.findViewById(R.id.actTitleTextView);
            descTextView = v.findViewById(R.id.actDescTextView);
            watchVideoBtn = v.findViewById(R.id.watchActButton);
        }

        public void setGroupActivity(GroupActivity activity) {
            this.activity = activity;
            titleTextView.setText(activity.getTitle());
            descTextView.setText(activity.getDescription());
            if (StringHelper.isNullOrEmpty(activity.getHyperlink())) {
                watchVideoBtn.setVisibility(View.GONE);
            } else {
                watchVideoBtn.setVisibility(View.VISIBLE);
                watchVideoBtn.setOnClickListener(this::onWatchVideoClick);
            }
        }

        private void onWatchVideoClick(View v) {
            Toast.makeText(
                    itemView.getContext(),
                    String.format("Watch: %s", activity.getHyperlink()),
                    Toast.LENGTH_SHORT).show();
        }
    }
}

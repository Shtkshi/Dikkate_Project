package com.example.dikkate.Util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dikkate.R;
import com.repsly.library.timelineview.LineType;
import com.repsly.library.timelineview.TimelineView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private final int orientation;
    private final List<ListItem> items;
    private final ArrayList<String> Name = new ArrayList<>();
    private int nextPosition;

    public TimelineAdapter(int orientation, List<ListItem> items,int nextPosition) {
        this.orientation = orientation;
        this.nextPosition=nextPosition;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.timeline;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(Name.get(position));

        holder.timelineView.setLineType(getLineType(position));
        holder.timelineView.setNumber(position);

        holder.timelineView.setFillMarker(nextPosition >= position);

        if (position == nextPosition) {
            holder.timelineView.setDrawable(AppCompatResources
                    .getDrawable(holder.timelineView.getContext(),
                            R.drawable.ic_checked));
        } else {
            holder.timelineView.setDrawable(null);
        }

        // Set every third item active
        if (nextPosition < 5 && position==nextPosition+1)
            holder.timelineView.setActive(true);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private LineType getLineType(int position) {
        if (getItemCount() == 1) {
            return LineType.ONLYONE;

        } else {
            if (position == 0) {
                return LineType.BEGIN;

            } else if (position == getItemCount() - 1) {
                return LineType.END;

            } else {
                return LineType.NORMAL;
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TimelineView timelineView;
        TextView tvName;
        TextView tvAddress;

        ViewHolder(View view) {
            super(view);
            timelineView = (TimelineView) view.findViewById(R.id.timeline);
            tvName = (TextView) view.findViewById(R.id.tv_name);

            Name.add("Registered \n Successfully");
            Name.add("Call \nVerification");
            Name.add("Visit at \nHome");
            Name.add("Payment\n ");
            Name.add("Review \n ");
        }
    }

}

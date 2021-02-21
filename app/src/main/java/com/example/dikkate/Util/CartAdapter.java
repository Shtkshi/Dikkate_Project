package com.example.dikkate.Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dikkate.Activity.viewDetails;
import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Catergory_User_mapping;
import com.example.dikkate.RoomDataBase.Dao;
import com.zerobranch.layout.SwipeLayout;

import java.util.ArrayList;
import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.TasksViewHolder> {

    private Context mCtx;
    private int UserId;
    List<Catergory_User_mapping> arr;
    ArrayList<String> NameOfProblem = new ArrayList<>();
    ArrayList<Integer> icons = new ArrayList<>();
    Dao dao;

    public CartAdapter(Context mCtx, List<Catergory_User_mapping> arr, Dao dao, int UserId) {
        this.mCtx = mCtx;
        this.UserId = UserId;
        this.dao = dao;
        this.arr = arr;
    }


    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycler_cart1, parent, false);
        return new TasksViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        if (arr.get(position).getCategoryID() - 1 >= 0) {
            String name = NameOfProblem.get(arr.get(position).getCategoryID() - 1);
            holder.image.setImageResource(icons.get(arr.get(position).getCategoryID() - 1));
            holder.ProblemType.setText(name);
            holder.ViewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx, viewDetails.class);
                    intent.putExtra("PrimaryKeyID", arr.get(position).getI());
                    mCtx.startActivity(intent);
                }
            });
            holder.swipeLayout.setOnActionsListener(new SwipeLayout.SwipeActionsListener() {

                @Override
                public void onOpen(int direction, boolean isContinuous) {
                    holder.swipe.setVisibility(View.VISIBLE);
                    if (direction == SwipeLayout.RIGHT) {
                        // was executed swipe to the right
                    } else if (direction == SwipeLayout.LEFT) {
                        holder.swipe.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dao.RemoveCartItemsBeforeCall(arr.get(position).getI());
                                int a = arr.size();
                                if (a == 0) {
                                    arr.clear();
                                } else {
                                    arr.remove(position);
                                }
                                notifyDataSetChanged();
                            }
                        });

                        // was executed swipe to the left
                    }
                }

                @Override
                public void onClose() {
                    holder.swipe.setVisibility(View.INVISIBLE);

                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView ProblemType, Date, Time, ViewDetails, NoOfOrders;
        SwipeLayout swipeLayout;
        ImageView swipe = itemView.findViewById(R.id.right_view);


        public TasksViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            image = itemView.findViewById(R.id.recycler_image_cart1);
            ProblemType = itemView.findViewById(R.id.recycler_ProblemType_cart1);
            Date = itemView.findViewById(R.id.recycler_date_cart1);
            Time = itemView.findViewById(R.id.recycler_time_cart1);
            ViewDetails = itemView.findViewById(R.id.recycler_ViewDetails_cart1);
            swipeLayout = itemView.findViewById(R.id.swipe_layout);


            AddingItems();


        }

        @Override
        public void onClick(View view) {
            Toast.makeText(mCtx, "CLICKED WITH TEXT", Toast.LENGTH_LONG).show();
        }

    }

    void AddingItems() {
        //problem name
        NameOfProblem.add("AC Problems");
        NameOfProblem.add("Appliances");
        NameOfProblem.add("Painters");
        NameOfProblem.add("Cleaning and Disinfection");
        NameOfProblem.add("Electricians");
        NameOfProblem.add("Plumbers");
        NameOfProblem.add("Carpenter");
        NameOfProblem.add("Pest Control");


        //Images for problems
        icons.add(R.drawable.acservices);
        icons.add(R.drawable.appliancesservices);
        icons.add(R.drawable.painterservices);
        icons.add(R.drawable.cleaningservices);
        icons.add(R.drawable.electriansservices);
        icons.add(R.drawable.plumberservices);
        icons.add(R.drawable.carpenterservices);
        icons.add(R.drawable.pestservices);

    }
}
package com.example.dikkate.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dikkate.RoomDataBase.Complaint;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;

import com.example.dikkate.R;

import com.example.dikkate.Util.FeedbackAdapter;

import java.util.List;

public class Fragment5 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment5,container,false);
    }

    RecyclerView recyclerView;
    FeedbackAdapter adapter;
    Dao dao;
    List<Complaint> feedbacks;
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = database.getInstance(getContext()).dao();
        feedbacks=dao.getComplaintTotal();
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("Bill",Context.MODE_PRIVATE);
        int Bill=sharedPreferences.getInt("bill",-1);
        recyclerView=view.findViewById(R.id.feedback_total);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter=new FeedbackAdapter(getContext(),feedbacks,dao,Bill);
        recyclerView.setAdapter(adapter);

    }

}
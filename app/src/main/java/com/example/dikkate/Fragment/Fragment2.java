package com.example.dikkate.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Complaint;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;
import com.example.dikkate.Util.Constants;
import com.example.dikkate.Util.CustomAdapter;

import java.util.List;

public class Fragment2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2, container, false);
    }

    List<Complaint> queries;
    Dao dao;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    int UserId;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = database.getInstance(getContext()).dao();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("loggedIn", Context.MODE_PRIVATE);
        UserId = sharedPreferences.getInt("UserId", -1);
        queries = dao.ExtractComplaint(UserId, true);
        recyclerView = view.findViewById(R.id.fragment2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new CustomAdapter(this, queries, dao, UserId);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        queries.clear();
        queries.addAll(dao.ExtractComplaint(UserId, true));
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}
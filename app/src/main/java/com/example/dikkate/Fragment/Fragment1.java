package com.example.dikkate.Fragment;

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

import com.example.dikkate.Activity.MainUI;
import com.example.dikkate.Activity.dialog;
import com.example.dikkate.R;
import com.example.dikkate.RoomDataBase.Complaint;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;
import com.example.dikkate.Util.CustomAdapter;
import com.example.dikkate.Util.FloatingActionClicked;
import com.example.dikkate.Util.UpdateCompletion;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.dikkate.Util.Constants.REQUEST_CODE;

public class Fragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment1, container, false);
    }

    List<Complaint> queries, check;
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
        queries = dao.ExtractComplaint(UserId, false);
//        adapter.notifyDataSetChanged();
        recyclerView = view.findViewById(R.id.fragment1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new CustomAdapter(this, queries, dao, UserId);
        check = dao.Extract(UserId);
        recyclerView.setAdapter(adapter);

        ((MainUI) getActivity()).setFloatingActionClicked(new FloatingActionClicked() {
            @Override
            public void onFloatingActionClicked() {
                queries.clear();
                queries.addAll(dao.ExtractComplaint(UserId, false));
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            queries.clear();
            queries.addAll(dao.ExtractComplaint(UserId, false));
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);

        }
    }
}
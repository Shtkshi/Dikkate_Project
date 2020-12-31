package com.example.dikkate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dikkate.RoomDataBase.Complaint;
import com.example.dikkate.RoomDataBase.Dao;
import com.example.dikkate.RoomDataBase.database;

import java.util.List;

public class Fragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment1, container, false);
    }

    List<Complaint> queries;
    Dao dao;
    RecyclerView recyclerView;
    CustomAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = database.getInstance(getContext()).dao();
        queries = dao.ExtractComplaint();
        recyclerView = view.findViewById(R.id.fragment1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        adapter = new CustomAdapter(getContext(), queries);
        recyclerView.setAdapter(adapter);
        ((MainUI) getActivity()).setFloatingActionClicked(new FloatingActionClicked() {
            @Override
            public void onFloatingActionClicked() {
                queries.clear();
                queries.addAll(dao.ExtractComplaint());
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        }); // isse, samjha? nope
        /*FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Complaint user = new Complaint();
                CreateDialog(view);

            }
        });*/

    }

    /*public void CreateDialog(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.add_query, null, false);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        Dao dao = database.getInstance(view.getContext()).dao();
        Complaint user = new Complaint();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String email = pref.getString("email", "");
        user.setSubmitted_by(dao.UserId(email));
        EditText text = (EditText) view.findViewById(R.id.add_edittext);

        dialogView.findViewById(R.id.save_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setAssigned(false);
                dao.EnterComplaint(user);
                alertDialog.cancel();
            }
        });
        dao.EnterComplaint(user);
        alertDialog.show();
    }*/
}
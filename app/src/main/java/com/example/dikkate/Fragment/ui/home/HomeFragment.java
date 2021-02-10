package com.example.dikkate.Fragment.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dikkate.Fragment.ui.main.SectionsPagerAdapter;
import com.example.dikkate.R;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sectionsPagerAdapter = new SectionsPagerAdapter(getActivity(), getChildFragmentManager());
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        return  view;
    }

    private ViewPager viewPager;
    private TabLayout tabs;
    private SectionsPagerAdapter sectionsPagerAdapter;//viewpager null aa raha tha
/*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager.setCurrentItem(0);
    }
*/ // ho sakya hai some changes inverted it.

    @Override
    public void onResume() {
        super.onResume();
        if(viewPager != null){
            viewPager.setCurrentItem(0);
        } //viewpager null aa raha tha, sure? haan pehle tha
    }
}
//ye 4th frgement mai jakar hi kyu theek hota h? 2nd 3rd se kyu nhi? no clue
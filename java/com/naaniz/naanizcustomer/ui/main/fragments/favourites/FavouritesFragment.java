package com.naaniz.naanizcustomer.ui.main.fragments.favourites;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.naaniz.naanizcustomer.R;

public class FavouritesFragment extends Fragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);


        View groupRootView = inflater.inflate(R.layout.activity_favorites_fragment, container, false);
        viewPager = groupRootView.findViewById(R.id.viewpager);


        tabLayout = groupRootView.findViewById(R.id.tabs);


        return groupRootView;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SimpleFragmentPageAdapter simpleFragmentPageAdapter = new SimpleFragmentPageAdapter(getContext(), getChildFragmentManager());

        viewPager.setAdapter(simpleFragmentPageAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}

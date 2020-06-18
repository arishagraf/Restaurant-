package com.naaniz.naanizcustomer.ui.main.fragments.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.naaniz.naanizcustomer.R;

public class CollectionDemoFragment extends Fragment {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    DemoCollectionAdapter demoCollectionAdapter;
    ViewPager2 viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_demo_collection, container, false);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        demoCollectionAdapter = new DemoCollectionAdapter(this);
//        viewPager = view.findViewById(R.id.pager);
//        viewPager.setAdapter(demoCollectionAdapter);
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        demoCollectionAdapter = new DemoCollectionAdapter(this);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(demoCollectionAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if(position ==0){
                        tab.setText("New");
                    }
                    else{
                        tab.setText("Old");
                    }
                }
        ).attach();
    }

}

package com.naaniz.naanizcustomer.ui.main.fragments.favourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.naaniz.naanizcustomer.R;

public class
PastOrders extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View chatRootView = inflater.inflate(R.layout.past_order, container, false);
        return chatRootView;
    }
}

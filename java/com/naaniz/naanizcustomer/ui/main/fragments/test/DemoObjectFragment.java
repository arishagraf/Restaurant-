package com.naaniz.naanizcustomer.ui.main.fragments.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.naaniz.naanizcustomer.R;

import java.util.ArrayList;
import java.util.List;


public class DemoObjectFragment extends Fragment {
    public static final String ARG_OBJECT = "object";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View  view =  inflater.inflate(R.layout.fragment_demo_object, container, false);

//        Button button = view.findViewById(R.id.button3);
//        button.setOnClickListener(v -> {
//            Navigation.findNavController(v).navigate(R.id.action_collectionDemoFragment_to_menuFragment);
//        }
//        );


        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> list = new ArrayList<>();
        list.add("ABhishek");
        list.add("Panda");
        list.add("ABhishek");
        list.add("Panda");
        list.add("ABhishek");
        list.add("Panda");
        list.add("ABhishek");
        list.add("Panda");


        TestAdapter adapter = new TestAdapter(list);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
//        ((TextView) view.findViewById(android.R.id.text1))
//                .setText(Integer.toString(args.getInt(ARG_OBJECT)));

    }
}

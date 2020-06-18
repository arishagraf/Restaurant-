package com.naaniz.naanizcustomer.ui.main.fragments.recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.naaniz.naanizcustomer.R;

import java.util.ArrayList;
import java.util.List;

public class show_recipe_fragment extends Fragment {

    private TextView name;
    private TextView time;
    private TextView procedure;
    private TextView description;
    private TextView serving;
    public LinearLayout listOfIngredients;
    private List<Double> costOfIngredients = new ArrayList<>();
    private String dish;
    private String phone;
    private TextView recipeCost;
    private TextView Categories;
    private Double cost = 0.0;
    private Button likeButton, likedButton;
   // private Button addToMenu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show__recipe,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.recipe_name);
        time = view.findViewById(R.id.preparation_time);
        serving = view.findViewById(R.id.servings_no);
        recipeCost = view.findViewById(R.id.cost_amount);
        Categories=view.findViewById(R.id.categories_specified);
        description=view.findViewById(R.id.description_recipe);
        procedure = view.findViewById(R.id.firstly_in_);
        listOfIngredients =view.findViewById(R.id.listOfIngredients);
        //dish = getIntent().getStringExtra("dish");
        //phone = getIntent().getStringExtra("phone");
        //addToMenu = findViewById(R.id.addToMenu);

        view.findViewById(R.id.shareButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        likeButton = view.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                likedButton.setVisibility(View.VISIBLE);
                likedButton.setEnabled(true);

                likeButton.setVisibility(View.INVISIBLE);
                likeButton.setEnabled(false);
            }
        });

        likedButton = view.findViewById(R.id.likedButton);
        likedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                likedButton.setVisibility(View.INVISIBLE);
                likedButton.setEnabled(false);

                likeButton.setVisibility(View.VISIBLE);
                likeButton.setEnabled(true);
            }
        });

    }

}

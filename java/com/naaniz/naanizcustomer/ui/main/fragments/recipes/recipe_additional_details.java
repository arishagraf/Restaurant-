package com.naaniz.naanizcustomer.ui.main.fragments.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.badge.BadgeUtils;
import com.naaniz.naanizcustomer.R;

public class recipe_additional_details extends AppCompatActivity {
    private EditText category,no_of_serving,time_taken,region,tags;
    private Button Nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_additional_details);
        category=findViewById(R.id.recipe_category_edit_text);
        no_of_serving=findViewById(R.id.recipe_serving_edit_text);
        time_taken=findViewById(R.id.recipe_time_edit_text);
        region=findViewById(R.id.recipe_region_edit_text);
        tags=findViewById(R.id.recipe_tags_edit_text);
        Nextbtn=findViewById(R.id.next_additional_recipe_btn);
        Nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

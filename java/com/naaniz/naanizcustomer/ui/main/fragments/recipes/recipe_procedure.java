package com.naaniz.naanizcustomer.ui.main.fragments.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.naaniz.naanizcustomer.R;

public class recipe_procedure extends AppCompatActivity {
    private EditText recipe;
    private Button Nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_procedure);
        recipe=findViewById(R.id.recipe_procedure_edittext);
        Nextbtn=findViewById(R.id.next_recipe_procedure_btn);
        Nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

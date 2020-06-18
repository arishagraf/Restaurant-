package com.naaniz.naanizcustomer.ui.setup.signup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.naaniz.naanizcustomer.R;

public class SignUpCustomer3 extends AppCompatActivity {

    String name, phonenumber, uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupcustomer3);

        phonenumber=getIntent().getStringExtra("phonenumber");
        name = getIntent().getStringExtra("name");

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpCustomer4.class);
            intent.putExtra("name", name);
            intent.putExtra("phonenumber", phonenumber);
            startActivity(intent);
        });

    }
}

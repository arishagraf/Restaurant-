package com.naaniz.naanizcustomer.ui.setup.signup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.naaniz.naanizcustomer.R;

public class SignUpCustomer1 extends AppCompatActivity {
    private EditText enterphonenumber, nameInput;
    private Button sendotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupcustomer1);

        enterphonenumber=findViewById(R.id.cust_phone_login);
        sendotp=findViewById(R.id.button_verify);
        nameInput = findViewById(R.id.cust_name_login);



        sendotp.setOnClickListener(v -> {
            String number="+91"+"9116595075";

            String name = nameInput.getText().toString().trim();

            if(number.isEmpty()|| number.length()<11) {
                enterphonenumber.setError("Number is required");
                enterphonenumber.requestFocus();
                return;
            }
            else if(name.isEmpty()) {
                nameInput.setError("Name is required");
                nameInput.requestFocus();
                return;
            }

            Intent intent=new Intent(getApplicationContext(),SignUpCustomer2.class);
            intent.putExtra("phonenumber",number);
            intent.putExtra("name", name);
            startActivity(intent);
        });
    }
}

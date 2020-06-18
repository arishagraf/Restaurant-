package com.naaniz.naanizcustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.naaniz.naanizcustomer.R;
import com.naaniz.naanizcustomer.ui.main.MainActivity;
import com.naaniz.naanizcustomer.ui.setup.login.LoginCustomerActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();



        if(mAuth.getCurrentUser() == null){
            Intent intent =  new Intent(this, LoginCustomerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else{
            Intent intent =  new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}


//        Intent intent = new Intent(this, LoginCustomerActivity.class);
//        startActivity(intent);
//        finish();

//        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("JWT", MODE_PRIVATE);
//        String s = sharedPref.getString(getResources().getString(R.string.jwt_token), "None");
//
//        Log.d("TAG", s);
//
//        if(s.equals("None")){
//            Log.d("TAG", "here");
//
//            Intent intent =  new Intent(this, LoginCustomerActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
//        else{
//            Intent intent =  new Intent(this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }


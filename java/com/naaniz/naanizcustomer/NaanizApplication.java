package com.naaniz.naanizcustomer;

import android.app.Application;

public class NaanizApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}

//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        if(mAuth.getCurrentUser() == null){
//            Intent intent =  new Intent(this, LoginCustomerActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }
//        else{
//            Intent intent =  new Intent(this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//        }

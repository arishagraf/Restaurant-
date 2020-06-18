package com.naaniz.naanizcustomer.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.naaniz.naanizcustomer.R;

public class MainActivity extends AppCompatActivity implements NavController.OnDestinationChangedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bnv = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.main_activity_nav_host);

        NavigationUI.setupWithNavController(bnv, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);

        navController.addOnDestinationChangedListener(this);

    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        if(destination.getId() == R.id.profileFragment2) {
            getSupportActionBar().hide();
        } else {
            getSupportActionBar().show();
        }
    }
}

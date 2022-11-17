package com.example.passthepass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.passthepass.databinding.ActivityPtphomeBinding;

public class PTPHome extends AppCompatActivity {

    private ActivityPtphomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPtphomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (SaveSharedPreference.getUser() == null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_my_passwords, R.id.navigation_shared_passwords, R.id.navigation_my_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_ptphome);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void logout(View view) {
        SaveSharedPreference.setUser(null);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickCreate(View view) {
        Intent intent = new Intent(this, CreatePassword.class);
        startActivity(intent);
    }

    public void onClickCreateShared(View view) {
        Intent intent = new Intent(this, CreatePasswordShared.class);
        startActivity(intent);
    }

}
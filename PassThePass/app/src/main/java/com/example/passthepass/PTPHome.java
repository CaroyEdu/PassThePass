package com.example.passthepass;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.passthepass.ui.my_account.MyAccountFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.passthepass.databinding.ActivityPtphomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PTPHome extends AppCompatActivity {

    private ActivityPtphomeBinding binding;
    private FloatingActionButton floatingActionButton;
    private EditText editTextAppName, editTextAppPassword;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPtphomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bundle = getIntent().getExtras();

        floatingActionButton = findViewById(R.id.floatingActionButton);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_my_passwords, R.id.navigation_shared_passwords, R.id.navigation_my_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_ptphome);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public void createPassword(View view){
        SQLiteDatabase db = new DBHelper(this).getWritableDatabase();

        editTextAppName = findViewById(R.id.editTextAppName);
        editTextAppPassword = findViewById(R.id.editTextAppPassword);

        ContentValues values = new ContentValues();
        values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_APP, editTextAppName.getText().toString());
        values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_PASSWORD, editTextAppPassword.getText().toString());
        values.put(DBContract.PasswordEntry.COLUMN_SHARED, "0");
        long newRowId = db.insert(DBContract.PasswordEntry.TABLE_PASSWORD, null, values);

        ContentValues values2 = new ContentValues();
        values2.put(DBContract.UserPasswordEntry.COLUMN_USER_ID, bundle.get("id").toString());
        values2.put(DBContract.UserPasswordEntry.COLUMN_PASSWORD_ID, String.valueOf(newRowId));
        db.insert(DBContract.UserPasswordEntry.TABLE_USERPASSWORD, null, values2);
    }

    public void onClickCreate(View view){
        replaceFragment(new CreatePasswordFragment());
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_ptphome, fragment);
        fragmentTransaction.commit();
    }

}
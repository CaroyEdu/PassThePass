package com.example.passthepass;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdatePassword extends AppCompatActivity {

    private EditText editTextAppName, editTextAppPassword;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        bundle = getIntent().getExtras();
        editTextAppName = findViewById(R.id.editAppName);
        editTextAppName.setText(bundle.get("nameApp").toString());
        editTextAppPassword = findViewById(R.id.editPassword);
        editTextAppPassword.setText(bundle.get("password").toString());
    }

    public void onClick(View view) {
        editPasswordInDatabase();
        Toast.makeText(this, R.string.update_password_success, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PTPHome.class);
        startActivity(intent);
    }

    public void editPasswordInDatabase() {
        SQLiteDatabase db = new DBHelper(this).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_APP, editTextAppName.getText().toString());
        values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_PASSWORD, editTextAppPassword.getText().toString());

        db.update(DBContract.PasswordEntry.TABLE_PASSWORD, values, DBContract.PasswordEntry._ID + " =?", new String[]{bundle.get("idPassword").toString()});
    }

}
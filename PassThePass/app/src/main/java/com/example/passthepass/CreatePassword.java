package com.example.passthepass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePassword extends AppCompatActivity {

    private EditText editTextAppName, editTextAppPassword;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
        bundle = getIntent().getExtras();
    }

    public void onClick(View view){
        addPasswordInDatabase();
        Toast.makeText(this, R.string.create_application_succes, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PTPHome.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void addPasswordInDatabase(){
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
}
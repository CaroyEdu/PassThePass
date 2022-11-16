package com.example.passthepass;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreatePassword extends AppCompatActivity {

    private EditText editTextAppName, editTextAppPassword;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        if(SaveSharedPreference.getUser()==null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            user = SaveSharedPreference.getUser();
        }
    }

    public void onClick(View view) {
        addPasswordInDatabase();
    }

    private void addPasswordInDatabase() {
        SQLiteDatabase db = new DBHelper(this).getWritableDatabase();

        editTextAppName = findViewById(R.id.editAppName);
        editTextAppPassword = findViewById(R.id.editPassword);

        if(editTextAppName.getText().toString().isEmpty() || editTextAppPassword.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.create_application_failed_required, Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_APP, editTextAppName.getText().toString());
            values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_PASSWORD, editTextAppPassword.getText().toString());
            values.put(DBContract.PasswordEntry.COLUMN_SHARED, "0");
            long newRowId = db.insert(DBContract.PasswordEntry.TABLE_PASSWORD, null, values);

            ContentValues values2 = new ContentValues();
            values2.put(DBContract.UserPasswordEntry.COLUMN_USER_ID, user.getId());
            values2.put(DBContract.UserPasswordEntry.COLUMN_PASSWORD_ID, String.valueOf(newRowId));
            db.insert(DBContract.UserPasswordEntry.TABLE_USERPASSWORD, null, values2);

            Toast.makeText(this, R.string.create_application_succes, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PTPHome.class);
            startActivity(intent);
        }
    }
}
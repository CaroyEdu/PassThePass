package com.example.passthepass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePasswordShared extends AppCompatActivity {

    private EditText editTextAppNameShared, editTextAppPasswordShare, editTextUserEmail;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password_shared);
        bundle = getIntent().getExtras();
    }

    public void onClick(View view){
        addPasswordInDatabase();
    }

    @SuppressLint("Range")
    private void addPasswordInDatabase(){
        SQLiteDatabase db = new DBHelper(this).getWritableDatabase();

        editTextAppNameShared = findViewById(R.id.editTextPersonNameShared);
        editTextAppPasswordShare = findViewById(R.id.editTextAppPasswordShared);
        editTextUserEmail = findViewById(R.id.editTextUserEmail);

        String email = editTextUserEmail.getText().toString();

        final String MY_QUERY = "SELECT * FROM " + DBContract.UserEntry.TABLE_USER
                + " WHERE " + DBContract.UserEntry.COLUMN_USER_EMAIL + " LIKE '" + email + "'";

        Cursor cursor = db.rawQuery(MY_QUERY, new String[]{});
        String idUser = "";
        try {
            while (cursor.moveToNext()) {
                idUser = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry._ID));
            }
        } finally {
            cursor.close();
        }

        if(idUser.equals(bundle.get("id").toString())){
            Toast.makeText(this, R.string.create_application_failed_not_your_email, Toast.LENGTH_SHORT).show();
        } else if(editTextAppNameShared.getText().toString().isEmpty() || editTextAppPasswordShare.getText().toString().isEmpty()){
            Toast.makeText(this, R.string.create_application_failed_required, Toast.LENGTH_SHORT).show();
        } else if(!idUser.equals("")) {
            ContentValues values = new ContentValues();
            values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_APP, editTextAppNameShared.getText().toString());
            values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_PASSWORD, editTextAppPasswordShare.getText().toString());
            values.put(DBContract.PasswordEntry.COLUMN_SHARED, "1");
            long newRowId = db.insert(DBContract.PasswordEntry.TABLE_PASSWORD, null, values);

            ContentValues values2 = new ContentValues();
            values2.put(DBContract.UserPasswordEntry.COLUMN_USER_ID, bundle.get("id").toString());
            values2.put(DBContract.UserPasswordEntry.COLUMN_PASSWORD_ID, String.valueOf(newRowId));
            db.insert(DBContract.UserPasswordEntry.TABLE_USERPASSWORD, null, values2);

            ContentValues values3 = new ContentValues();
            values3.put(DBContract.UserPasswordEntry.COLUMN_USER_ID, idUser);
            values3.put(DBContract.UserPasswordEntry.COLUMN_PASSWORD_ID, String.valueOf(newRowId));
            db.insert(DBContract.UserPasswordEntry.TABLE_USERPASSWORD, null, values3);
            Toast.makeText(this, R.string.create_application_succes, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PTPHome.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, R.string.create_application_failed_user_not_found, Toast.LENGTH_SHORT).show();
        }
    }
}
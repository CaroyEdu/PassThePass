package com.example.passthepass;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Atributos para manejar la BD
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    // Atributos Login
    private EditText email, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicio la db
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.buttonLogin);

        createAccounts();
        createPasswords();
    }

    @SuppressLint("Range")
    public void onClick(View view) {
        hideSoftKeyboard(email);
        hideSoftKeyboard(password);

        String e = email.getText().toString();
        String p = password.getText().toString();
        if (e.isEmpty() || p.isEmpty()) {
            Toast.makeText(this, R.string.et_empty, Toast.LENGTH_LONG).show();
        } else {
            String[] columns = {
                    DBContract.UserEntry._ID,
                    DBContract.UserEntry.COLUMN_USER_EMAIL,
                    DBContract.UserEntry.COLUMN_USER_PASS
            };
            String where = DBContract.UserEntry.COLUMN_USER_EMAIL + " = ?";
            String[] whereEmail = {e};
            Cursor cursor = db.query(DBContract.UserEntry.TABLE_USER, columns, where, whereEmail, null, null, null);
            String wherePassword = "";
            Integer userId = 0;
            try {
                while (cursor.moveToNext()) {
                    wherePassword = cursor.getString(cursor.getColumnIndex(DBContract.UserEntry.COLUMN_USER_PASS));
                    userId = cursor.getInt(cursor.getColumnIndex(DBContract.UserEntry._ID));
                }
            } finally {
                cursor.close();
            }

            if (p.equals(wherePassword)) {
                Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, PTPHome.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", userId);
                bundle.putString("email", e);
                bundle.putString("password", p);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    private void createAccounts() {
        // Creo un par de cuentas para probar la aplicación
        ContentValues values = new ContentValues();
        values.put(DBContract.UserEntry.COLUMN_USER_EMAIL, "admin@ptp.com");
        values.put(DBContract.UserEntry.COLUMN_USER_PASS, "1234");
        db.insert(DBContract.UserEntry.TABLE_USER, null, values);

        values.put(DBContract.UserEntry.COLUMN_USER_EMAIL, "info@ptp.com");
        values.put(DBContract.UserEntry.COLUMN_USER_PASS, "1234");
        db.insert(DBContract.UserEntry.TABLE_USER, null, values);
    }

    private void createPasswords() {
        // Creo un par de contraseñas para probar la aplicación
        ContentValues values = new ContentValues();
        values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_USER, "37");
        values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_APP, "Netflix");
        values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_PASSWORD, "1234");
        db.insert(DBContract.PasswordEntry.TABLE_PASSWORD, null, values);
    }

    // Esta función esconde el teclado
    private void hideSoftKeyboard(View v) {
        InputMethodManager inputMethodManager;
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
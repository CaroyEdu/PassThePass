package com.example.passthepass;

import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {

    // Atributos para manejar la BD
    private UserHelper dbHelper;
    private SQLiteDatabase db;

    // Atributos Login
    private EditText email, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicio la db
        dbHelper = new UserHelper(getApplicationContext(), "mydb.db");
        db = dbHelper.getWritableDatabase();

        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.buttonLogin);

        createAccounts();
        createPasswords();
    }

    @SuppressLint("Range")
    public void onClick(View view){
        hideSoftKeyboard(email);
        hideSoftKeyboard(password);

        String e = email.getText().toString();
        String p = password.getText().toString();
        if (e.isEmpty() || p.isEmpty()) {
            Toast.makeText(this, R.string.et_empty, Toast.LENGTH_LONG).show();
        } else {
            String[] columns = {
                    UserContract.DbUser._ID,
                    UserContract.DbUser.COLUMN_NAME_KEY,
                    UserContract.DbUser.COLUMN_NAME_VAL
            };
            String where = UserContract.DbUser.COLUMN_NAME_KEY + " = ?";
            String[] whereEmail = { e };
            Cursor cursor = db.query(UserContract.DbUser.TABLE_NAME, columns, where, whereEmail, null, null, null);
            String wherePassword = "";
            try {
                while (cursor.moveToNext()) {
                    wherePassword = cursor.getString(cursor.getColumnIndex(UserContract.DbUser.COLUMN_NAME_VAL));
                }
            } finally {
                cursor.close();
            }

            if(p.equals(wherePassword)){
                Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, PTPHome.class);
                Bundle bundle = new Bundle();
                bundle.putString("email", e);
                bundle.putString("password", p);
                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    private void createAccounts(){
        // Creo un par de cuentas para probar la aplicación
        ContentValues values = new ContentValues();
        values.put(UserContract.DbUser.COLUMN_NAME_KEY, "admin@ptp.com");
        values.put(UserContract.DbUser.COLUMN_NAME_VAL, "1234");
        db.insert(UserContract.DbUser.TABLE_NAME, null, values);

        values.put(UserContract.DbUser.COLUMN_NAME_KEY, "info@ptp.com");
        values.put(UserContract.DbUser.COLUMN_NAME_VAL, "1234");
        db.insert(UserContract.DbUser.TABLE_NAME, null, values);
    }

    private void createPasswords(){
        // Creo un par de contraseñas para probar la aplicación
        ContentValues values = new ContentValues();
        values.put(PasswordContract.DbPassword.COLUMN_NAME_KEY, "admin@ptp.com");
        values.put(PasswordContract.DbPassword.COLUMN_NAME1, "Netflix");
        values.put(PasswordContract.DbPassword.COLUMN_NAME2, "1234");
        db.insert(PasswordContract.DbPassword.TABLE_NAME, null, values);
    }

    // Esta función esconde el teclado
    private void hideSoftKeyboard(View v) {
        InputMethodManager inputMethodManager;
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
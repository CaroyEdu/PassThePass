package com.example.passthepass;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PassThePass.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.UserEntry.SQL_CREATE_USER);
        db.execSQL(DBContract.PasswordEntry.SQL_CREATE_PASSWORD);
        db.execSQL(DBContract.UserPasswordEntry.SQL_CREATE_USERPASSWORD);

        //Creo cuentas y contraseñas de prueba
        this.createAccounts(db);
        this.createPasswords(db);
        this.createUserPassword(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(databaseExists()){
            db.execSQL(DBContract.UserEntry.SQL_DELETE_USER);
            db.execSQL(DBContract.PasswordEntry.SQL_DELETE_PASSWORD);
            db.execSQL(DBContract.UserPasswordEntry.SQL_DELETE_USERPASSWORD);
            onCreate(db);
        }
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private boolean databaseExists() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase("/data/data/com.example.passthepass/databases/PassThePass.db", null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
        }
        if(checkDB == null){
            return false;
        }else{
            return true;
        }
    }

    // TESTING

    private void createAccounts(SQLiteDatabase db) {
        // Creo un par de cuentas para probar la aplicación
        ContentValues values = new ContentValues();
        values.put(DBContract.UserEntry.COLUMN_USER_EMAIL, "admin@ptp.com");
        values.put(DBContract.UserEntry.COLUMN_USER_PASS, "1234");
        db.insert(DBContract.UserEntry.TABLE_USER, null, values);

        values.put(DBContract.UserEntry.COLUMN_USER_EMAIL, "info@ptp.com");
        values.put(DBContract.UserEntry.COLUMN_USER_PASS, "1234");
        db.insert(DBContract.UserEntry.TABLE_USER, null, values);
    }

    private void createPasswords(SQLiteDatabase db) {
        // Creo un par de contraseñas para probar la aplicación
        ContentValues values = new ContentValues();
        values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_APP, "Netflix");
        values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_PASSWORD, "1234");
        db.insert(DBContract.PasswordEntry.TABLE_PASSWORD, null, values);

        values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_APP, "Prime Video");
        values.put(DBContract.PasswordEntry.COLUMN_PASSWORD_PASSWORD, "1234");
        db.insert(DBContract.PasswordEntry.TABLE_PASSWORD, null, values);
    }

    private void createUserPassword(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(DBContract.UserPasswordEntry.COLUMN_USER_ID, "1");
        values.put(DBContract.UserPasswordEntry.COLUMN_PASSWORD_ID, "1");
        db.insert(DBContract.UserPasswordEntry.TABLE_USERPASSWORD, null, values);

        values.put(DBContract.UserPasswordEntry.COLUMN_USER_ID, "1");
        values.put(DBContract.UserPasswordEntry.COLUMN_PASSWORD_ID, "2");
        db.insert(DBContract.UserPasswordEntry.TABLE_USERPASSWORD, null, values);
    }

}

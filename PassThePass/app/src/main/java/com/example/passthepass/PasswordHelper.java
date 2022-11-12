package com.example.passthepass;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PasswordHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PasswordContract.DbPassword.TABLE_NAME + " (" +
                    PasswordContract.DbPassword._ID + " INTEGER PRIMARY KEY," +
                    PasswordContract.DbPassword.COLUMN_NAME_KEY + " TEXT UNIQUE," +
                    PasswordContract.DbPassword.COLUMN_NAME1 + " TEXT," +
                    PasswordContract.DbPassword.COLUMN_NAME2 + " TEXT" +
                    " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + PasswordContract.DbPassword.TABLE_NAME;

    public PasswordHelper(Context context, String database_name) {
        super(context, database_name, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Este método será invocado al establecer la conexión con la BD
        // en el caso de que la creación de la BD sea necesaria
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Este método será invocado al establecer la conexión con la BD
        // en el caso de que la versión de la BD almacenada sea inferior a
        // la versión de la BD que queremos abrir (especificada por
        // DATABASE_VERSION proporcionada en el constructor de la clase)
        //
        // Una política de actualización simple: eliminar los datos almacenados
        // y comenzar de nuevo con una BD vacía
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Este método será invocado al establecer la conexión con la BD
        // en el caso de que la versión de la BD almacenada sea superior a
        // la versión de la BD que queremos abrir (especificada por
        // DATABASE_VERSION proporcionada en el constructor de la clase)
        //
        // Una política de actualización simple: eliminar los datos almacenados
        // y comenzar de nuevo con una BD vacía
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
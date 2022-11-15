package com.example.passthepass;

import android.provider.BaseColumns;

public class DBContract {
    private DBContract() {
    }

    //USER TABLE
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_USER = "user";
        public static final String COLUMN_USER_EMAIL = "email";
        public static final String COLUMN_USER_PASS = "password";


        public static final String SQL_CREATE_USER =
                "CREATE TABLE " + UserEntry.TABLE_USER + " (" +
                        UserEntry._ID + " INTEGER PRIMARY KEY," +
                        UserEntry.COLUMN_USER_EMAIL + " TEXT," +
                        UserEntry.COLUMN_USER_PASS + " TEXT)";

        public static final String SQL_DELETE_USER =
                "DROP TABLE IF EXISTS " + UserEntry.TABLE_USER;
    }

    //PASSWORD TABLE
    public static class PasswordEntry implements BaseColumns {
        public static final String TABLE_PASSWORD = "password";
        public static final String COLUMN_PASSWORD_APP = "appName";
        public static final String COLUMN_PASSWORD_PASSWORD = "password";
        public static final String COLUMN_SHARED = "shared";


        public static final String SQL_CREATE_PASSWORD =
                "CREATE TABLE " + PasswordEntry.TABLE_PASSWORD + " (" +
                        PasswordEntry._ID + " INTEGER PRIMARY KEY," +
                        PasswordEntry.COLUMN_PASSWORD_APP + " TEXT," +
                        PasswordEntry.COLUMN_PASSWORD_PASSWORD + " TEXT," +
                        PasswordEntry.COLUMN_SHARED + " INTEGER )";

        public static final String SQL_DELETE_PASSWORD =
                "DROP TABLE IF EXISTS " + PasswordEntry.TABLE_PASSWORD;
    }

    //USERPASSWORD TABLE
    public static class UserPasswordEntry implements BaseColumns {
        public static final String TABLE_USERPASSWORD = "userpassword";
        public static final String COLUMN_USER_ID = "idUser";
        public static final String COLUMN_PASSWORD_ID = "idPassword";

        public static final String SQL_CREATE_USERPASSWORD =
                "CREATE TABLE " + UserPasswordEntry.TABLE_USERPASSWORD + " (" +
                        UserPasswordEntry.COLUMN_USER_ID + " INTEGER," +
                        UserPasswordEntry.COLUMN_PASSWORD_ID + " INTEGER, PRIMARY KEY (" +
                        UserPasswordEntry.COLUMN_USER_ID + ", " + UserPasswordEntry.COLUMN_PASSWORD_ID + "));";

        public static final String SQL_DELETE_USERPASSWORD =
                "DROP TABLE IF EXISTS " + UserPasswordEntry.TABLE_USERPASSWORD;
    }
}

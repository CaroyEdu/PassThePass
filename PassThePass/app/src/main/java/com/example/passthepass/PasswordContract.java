package com.example.passthepass;

import android.provider.BaseColumns;

public final class PasswordContract {
    private PasswordContract() {}

    public static abstract class DbPassword implements BaseColumns {
        public static final String TABLE_NAME = "Password";
        public static final String COLUMN_NAME_KEY = "userEmail";
        public static final String COLUMN_NAME1 = "applicationName";
        public static final String COLUMN_NAME2 = "applicationPassword";
    }
}
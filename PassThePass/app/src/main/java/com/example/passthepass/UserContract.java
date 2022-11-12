package com.example.passthepass;

import android.provider.BaseColumns;

public final class UserContract {
    private UserContract() {}

    public static abstract class DbUser implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_NAME_KEY = "email";
        public static final String COLUMN_NAME_VAL = "password";
    }
}
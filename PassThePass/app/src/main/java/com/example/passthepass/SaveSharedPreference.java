package com.example.passthepass;

public class SaveSharedPreference {
    static User user = null;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        SaveSharedPreference.user = user;
    }
}
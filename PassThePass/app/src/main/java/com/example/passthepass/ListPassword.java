package com.example.passthepass;

public class ListPassword {
    public String nameApp;
    public String password;

    public ListPassword(String nameApp, String password) {
        this.nameApp = nameApp;
        this.password = password;
    }

    public String getNameApp() {
        return nameApp;
    }

    public void setNameApp(String nameApp) {
        this.nameApp = nameApp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.example.passthepass;

public class ListPassword {
    public String idPassword;
    public String nameApp;
    public String password;

    public ListPassword(String idPassword, String nameApp, String password) {
        this.idPassword = idPassword;
        this.nameApp = nameApp;
        this.password = password;
    }

    public String getIdPassword() {
        return idPassword;
    }

    public void setIdPassword(String idPassword) {
        this.idPassword = idPassword;
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

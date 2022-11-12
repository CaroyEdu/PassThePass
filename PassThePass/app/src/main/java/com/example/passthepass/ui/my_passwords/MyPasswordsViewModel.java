package com.example.passthepass.ui.my_passwords;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyPasswordsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyPasswordsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
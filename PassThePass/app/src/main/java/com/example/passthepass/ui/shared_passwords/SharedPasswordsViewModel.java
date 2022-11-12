package com.example.passthepass.ui.shared_passwords;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedPasswordsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SharedPasswordsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
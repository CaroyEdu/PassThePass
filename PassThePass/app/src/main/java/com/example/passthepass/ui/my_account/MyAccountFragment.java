package com.example.passthepass.ui.my_account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.passthepass.MainActivity;
import com.example.passthepass.R;
import com.example.passthepass.SaveSharedPreference;
import com.example.passthepass.User;
import com.example.passthepass.databinding.FragmentMyAccountBinding;

public class MyAccountFragment extends Fragment {

    private FragmentMyAccountBinding binding;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_my_account, container, false);

        if(SaveSharedPreference.getUser()==null){
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }else{
            user = SaveSharedPreference.getUser();
        }

        TextView userEmail = vista.findViewById(R.id.userEmail);
        userEmail.setText(user.getEmail());

        return vista;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.passthepass.ui.my_account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.passthepass.R;
import com.example.passthepass.databinding.FragmentMyAccountBinding;

public class MyAccountFragment extends Fragment {

    private FragmentMyAccountBinding binding;
    private Bundle bundle;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_my_account, container, false);

        TextView userEmail = vista.findViewById(R.id.userEmail);
        bundle = getActivity().getIntent().getExtras();
        userEmail.setText((CharSequence) bundle.get("email"));

        return vista;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
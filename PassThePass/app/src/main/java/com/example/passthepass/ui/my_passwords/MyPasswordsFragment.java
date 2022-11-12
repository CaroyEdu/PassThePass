package com.example.passthepass.ui.my_passwords;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.passthepass.databinding.FragmentMyPasswordsBinding;

public class MyPasswordsFragment extends Fragment {

    private FragmentMyPasswordsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyPasswordsViewModel myPasswordsViewModel =
                new ViewModelProvider(this).get(MyPasswordsViewModel.class);

        binding = FragmentMyPasswordsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        myPasswordsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
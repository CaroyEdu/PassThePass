package com.example.passthepass.ui.shared_passwords;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.passthepass.databinding.FragmentSharedPasswordsBinding;

public class SharedPasswordsFragment extends Fragment {

    private FragmentSharedPasswordsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SharedPasswordsViewModel dashboardViewModel =
                new ViewModelProvider(this).get(SharedPasswordsViewModel.class);

        binding = FragmentSharedPasswordsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.passthepass.ui.my_passwords;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passthepass.ListAdapter;
import com.example.passthepass.ListPassword;
import com.example.passthepass.R;
import com.example.passthepass.databinding.FragmentMyPasswordsBinding;

import java.util.ArrayList;
import java.util.List;

public class MyPasswordsFragment extends Fragment {

    private List<ListPassword> passwordList;
    RecyclerView recyclerView;
    private FragmentMyPasswordsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_my_passwords, container, false);

        passwordList = new ArrayList<>();
        recyclerView = (RecyclerView) vista.findViewById(R.id.listRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarLista();

        ListAdapter adapter = new ListAdapter(passwordList);
        recyclerView.setAdapter(adapter);

        return vista;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void llenarLista() {
        passwordList = new ArrayList<>();
        passwordList.add(new ListPassword("Netflix", "1234"));
        passwordList.add(new ListPassword("Movistar", "movistar12"));
        passwordList.add(new ListPassword("Wifi", "abcdefghi"));
        passwordList.add(new ListPassword("UMA", "aprobar88"));
        passwordList.add(new ListPassword("HBO", "1234"));
    }


}
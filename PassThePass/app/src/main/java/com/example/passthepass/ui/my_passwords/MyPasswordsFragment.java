package com.example.passthepass.ui.my_passwords;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passthepass.DBContract;
import com.example.passthepass.DBHelper;
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

    @SuppressLint("Range")
    public void llenarLista() {
        SQLiteDatabase db = new DBHelper(getContext()).getReadableDatabase();

        String[] projection = {
                DBContract.PasswordEntry._ID,
                DBContract.PasswordEntry.COLUMN_PASSWORD_USER,
                DBContract.PasswordEntry.COLUMN_PASSWORD_APP,
                DBContract.PasswordEntry.COLUMN_PASSWORD_PASSWORD
        };

        String selection = DBContract.PasswordEntry.COLUMN_PASSWORD_USER + " = ?";
        Bundle bundle = getActivity().getIntent().getExtras();
        String[] selectionArgs = {bundle.get("id").toString()};
        Cursor cursor = db.query(DBContract.PasswordEntry.TABLE_PASSWORD, projection, selection, selectionArgs, null, null, null);
        try {
            while (cursor.moveToNext()) {
                String appName = cursor.getString(cursor.getColumnIndex(DBContract.PasswordEntry.COLUMN_PASSWORD_APP));
                String password = cursor.getString(cursor.getColumnIndex(DBContract.PasswordEntry.COLUMN_PASSWORD_PASSWORD));

                passwordList.add(new ListPassword(appName, password));
            }
        } finally {
            cursor.close();
        }

     /*   passwordList.add(new ListPassword("Netflix", "1234"));
        passwordList.add(new ListPassword("Movistar", "movistar12"));
        passwordList.add(new ListPassword("Wifi", "abcdefghi"));
        passwordList.add(new ListPassword("UMA", "aprobar88"));
        passwordList.add(new ListPassword("HBO", "1234"));*/
    }


}
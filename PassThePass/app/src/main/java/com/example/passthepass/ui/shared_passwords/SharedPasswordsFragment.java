package com.example.passthepass.ui.shared_passwords;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passthepass.DBContract;
import com.example.passthepass.DBHelper;
import com.example.passthepass.ListAdapter;
import com.example.passthepass.ListPassword;
import com.example.passthepass.R;
import com.example.passthepass.databinding.FragmentSharedPasswordsBinding;

import java.util.ArrayList;
import java.util.List;

public class SharedPasswordsFragment extends Fragment {

    private List<ListPassword> passwordList;
    RecyclerView recyclerView;
    private FragmentSharedPasswordsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_shared_passwords, container, false);

        passwordList = new ArrayList<>();
        recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerView);
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

        Bundle bundle = getActivity().getIntent().getExtras();

        final String MY_QUERY = "SELECT * FROM " + DBContract.PasswordEntry.TABLE_PASSWORD
                + " INNER JOIN " + DBContract.UserPasswordEntry.TABLE_USERPASSWORD
                + " ON " + DBContract.PasswordEntry._ID + "=" + DBContract.UserPasswordEntry.COLUMN_PASSWORD_ID
                + " WHERE " + DBContract.UserPasswordEntry.COLUMN_USER_ID + "=" + bundle.get("id").toString()
                + " AND " + DBContract.PasswordEntry.COLUMN_SHARED + "=1";

        Cursor cursor = db.rawQuery(MY_QUERY, new String[]{});
        try {
            while (cursor.moveToNext()) {
                String idPassword = cursor.getString(cursor.getColumnIndex(DBContract.PasswordEntry._ID));
                String appName = cursor.getString(cursor.getColumnIndex(DBContract.PasswordEntry.COLUMN_PASSWORD_APP));
                String password = cursor.getString(cursor.getColumnIndex(DBContract.PasswordEntry.COLUMN_PASSWORD_PASSWORD));

                passwordList.add(new ListPassword(idPassword, appName, password));
            }
        } finally {
            cursor.close();
        }
    }
}
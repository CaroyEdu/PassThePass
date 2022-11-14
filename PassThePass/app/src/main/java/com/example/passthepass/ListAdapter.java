package com.example.passthepass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListPassword> listPasswords;

    public ListAdapter(List<ListPassword> listPasswords) {
        this.listPasswords = listPasswords;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_password, null, false);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.nameApp.setText(listPasswords.get(position).getNameApp());
        holder.password.setText(listPasswords.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        return listPasswords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameApp, password;

        ViewHolder(View itemView) {
            super(itemView);
            nameApp = itemView.findViewById(R.id.appName);
            password = itemView.findViewById(R.id.password);
        }
        
    }
}

package com.example.passthepass;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.password.setText("******");
        if (listPasswords.get(position).getNameApp().equalsIgnoreCase("Netflix")) {
            holder.imageView.setImageResource(R.drawable.netflix);
        } else if (listPasswords.get(position).getNameApp().equalsIgnoreCase("Youtube")) {
            holder.imageView.setImageResource(R.drawable.youtube);
        } else if (listPasswords.get(position).getNameApp().equalsIgnoreCase("Prime Video")) {
            holder.imageView.setImageResource(R.drawable.primevideo);
        } else if (listPasswords.get(position).getNameApp().equalsIgnoreCase("Facebook")) {
            holder.imageView.setImageResource(R.drawable.facebook);
        } else if (listPasswords.get(position).getNameApp().equalsIgnoreCase("Twitter")) {
            holder.imageView.setImageResource(R.drawable.twitter);
        } else if (listPasswords.get(position).getNameApp().equalsIgnoreCase("Snapchat")) {
            holder.imageView.setImageResource(R.drawable.snapchat);
        } else if (listPasswords.get(position).getNameApp().equalsIgnoreCase("Instagram")) {
            holder.imageView.setImageResource(R.drawable.instagram);
        }
    }

    @Override
    public int getItemCount() {
        return listPasswords.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
        TextView nameApp, password;
        ImageButton imageButton;
        ImageView imageView;
        Bundle bundle;

        ViewHolder(View itemView) {
            super(itemView);
            nameApp = itemView.findViewById(R.id.appName);
            password = itemView.findViewById(R.id.password);
            imageButton = itemView.findViewById(R.id.more);
            imageView = itemView.findViewById(R.id.iconImageView);
            imageButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            showPopupMenu(view);
        }

        private void showPopupMenu(View view) {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            int position = getAbsoluteAdapterPosition();
            ListPassword selected = listPasswords.get(position);
            SQLiteDatabase db = new DBHelper(itemView.getContext()).getWritableDatabase();
            switch (menuItem.getItemId()) {
                case R.id.action_popup_show:
                    password.setText(selected.password);
                    return true;
                case R.id.action_popup_edit:
                    Intent intent = new Intent(itemView.getContext(), UpdatePassword.class);
                    bundle = new Bundle();
                    bundle.putString("idPassword", selected.getIdPassword());
                    bundle.putString("nameApp", selected.getNameApp());
                    bundle.putString("password", selected.getPassword());
                    intent.putExtras(bundle);
                    itemView.getContext().startActivity(intent);
                    return true;
                case R.id.action_popup_delete:
                    db.delete(DBContract.PasswordEntry.TABLE_PASSWORD, DBContract.PasswordEntry._ID + "=?", new String[]{selected.getIdPassword()});
                    db.delete(DBContract.UserPasswordEntry.TABLE_USERPASSWORD, DBContract.UserPasswordEntry.COLUMN_PASSWORD_ID + "=?", new String[]{selected.getIdPassword()});
                    listPasswords.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(itemView.getContext(), R.string.toast_delete, Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return false;
            }
        }
    }
}

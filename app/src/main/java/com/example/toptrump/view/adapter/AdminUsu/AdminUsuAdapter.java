package com.example.toptrump.view.adapter.AdminUsu;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.toptrump.model.room.pojo.Usuario;

public class AdminUsuAdapter extends ListAdapter<Usuario, AdminUsuViewHolder> {

    public AdminUsuAdapter( @NonNull DiffUtil.ItemCallback<Usuario> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public AdminUsuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AdminUsuViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminUsuViewHolder holder, int position) {
        Usuario current = getItem(position);
        holder.bind(current.getNombre());
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public static class UsuarioDiff extends DiffUtil.ItemCallback<Usuario> {

        @Override
        public boolean areItemsTheSame(@NonNull Usuario oldItem, @NonNull Usuario newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Usuario oldItem, @NonNull Usuario newItem) {
            return oldItem.getNombre().equals(newItem.getNombre());
        }
    }

}

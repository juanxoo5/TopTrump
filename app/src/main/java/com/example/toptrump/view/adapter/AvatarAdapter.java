package com.example.toptrump.view.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.toptrump.model.room.pojo.Usuario;

public class AvatarAdapter extends ListAdapter<Usuario, UsuariosViewHolder> {

    public AvatarAdapter(@NonNull DiffUtil.ItemCallback<Usuario> diffCallback) {
        super(diffCallback);

    }

    @NonNull
    @Override
    public UsuariosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return UsuariosViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosViewHolder holder, int position) {
        Usuario current = getItem(position);
        holder.bind(current.getNombre());
    }

    public static class AmigoDiff extends DiffUtil.ItemCallback<Usuario> {

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

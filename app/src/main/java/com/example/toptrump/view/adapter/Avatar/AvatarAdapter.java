package com.example.toptrump.view.adapter.Avatar;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.toptrump.model.room.pojo.Usuario;

public class AvatarAdapter extends ListAdapter<Usuario, AvatarViewHolder> {

    public AvatarAdapter(@NonNull DiffUtil.ItemCallback<Usuario> diffCallback) {
        super(diffCallback);

    }

    @NonNull
    @Override
    public AvatarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AvatarViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AvatarViewHolder holder, int position) {
        Usuario current = getItem(position);
        holder.bind(current.getNombre());
    }

    public static class AvatarDiff extends DiffUtil.ItemCallback<Usuario> {

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

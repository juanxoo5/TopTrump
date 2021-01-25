package com.example.toptrump.view.adapter.Usuarios;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.toptrump.R;
import com.example.toptrump.model.room.pojo.Usuario;

public class UsuariosAdapter extends ListAdapter<Usuario, UsuariosViewHolder> {

    public UsuariosAdapter( @NonNull DiffUtil.ItemCallback<Usuario> diffCallback) {
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
        holder.bind(current.getNombre(), current.getAvatar());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("Nombre", current.getNombre());
                bundle.putInt("Avatar", current.getAvatar());
                bundle.putInt("NumResp", current.getNumRes());
                bundle.putInt("RespCor", current.getResCor());

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.juegoFragment, bundle);
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

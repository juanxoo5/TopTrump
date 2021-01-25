package com.example.toptrump.view.adapter.AdminUsu;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.toptrump.R;
import com.example.toptrump.model.room.pojo.Usuario;
import com.example.toptrump.viewmodel.ViewModel;

public class AdminUsuAdapter extends ListAdapter<Usuario, AdminUsuViewHolder> {

    ViewModel viewModelActivity;

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
        holder.bind(current.getNombre(), current.getAvatar());
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModelActivity = new ViewModelProvider((ViewModelStoreOwner) AdminUsuAdapter.this).get(ViewModel.class);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setMessage("Que desea hacer")
                        .setTitle(current.getNombre());
                builder.setPositiveButton("Editar usuario", new DialogInterface.OnClickListener() {

                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Bundle bundle = new Bundle();
                        bundle.putLong("Id", current.getId());
                        bundle.putString("Nombre", current.getNombre());
                        bundle.putInt("Avatar", current.getAvatar());
                        bundle.putInt("NumResp", current.getNumRes());
                        bundle.putInt("RespCor", current.getResCor());

                        NavController navController = Navigation.findNavController(v);
                        navController.navigate(R.id.editUsuaFragment, bundle);
                    }
                });
                builder.setNeutralButton("Cancelar", null);

                builder.setNegativeButton("Eliminar usuario", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        viewModelActivity.deleteUsuario(current.getId());
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
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

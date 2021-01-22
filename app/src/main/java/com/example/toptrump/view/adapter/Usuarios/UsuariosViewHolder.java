package com.example.toptrump.view.adapter.Usuarios;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toptrump.R;

public class UsuariosViewHolder extends RecyclerView.ViewHolder{

    private final TextView tvNombUsu;
    public final ConstraintLayout layout;

    public UsuariosViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvNombUsu = itemView.findViewById(R.id.tvNombUsu);
        this.layout = itemView.findViewById(R.id.ConstraintLayoutItem);
    }

    @SuppressLint("ResourceType")
    public void bind(String text) {
        tvNombUsu.setText(text);
    }
    public static UsuariosViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_usuario, parent, false);
        return new UsuariosViewHolder(view);
    }

}

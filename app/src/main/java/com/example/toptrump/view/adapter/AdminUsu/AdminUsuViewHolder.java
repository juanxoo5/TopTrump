package com.example.toptrump.view.adapter.AdminUsu;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toptrump.R;

public class AdminUsuViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvNombUsu;
    public final ConstraintLayout parent_layout;

    public AdminUsuViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvNombUsu = itemView.findViewById(R.id.tvNombUsu);
        this.parent_layout = itemView.findViewById(R.id.ConstraintLayoutItem);
    }

    @SuppressLint("ResourceType")
    public void bind(String text) {
        tvNombUsu.setText(text);
    }
    public static AdminUsuViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_usuario, parent, false);
        return new AdminUsuViewHolder(view);
    }

}

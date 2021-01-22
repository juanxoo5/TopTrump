package com.example.toptrump.view.adapter.Avatar;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toptrump.R;

public class AvatarViewHolder extends RecyclerView.ViewHolder{

    private final TextView tvNombUsu;

    public AvatarViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvNombUsu = itemView.findViewById(R.id.tvNombUsu);
    }

    @SuppressLint("ResourceType")
    public void bind(String text) {
        tvNombUsu.setText(text);
    }
    static AvatarViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_usuario, parent, false);
        return new AvatarViewHolder(view);
    }

}

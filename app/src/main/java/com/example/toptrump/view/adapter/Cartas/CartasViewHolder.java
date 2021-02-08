package com.example.toptrump.view.adapter.Cartas;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.toptrump.R;

public class CartasViewHolder extends RecyclerView.ViewHolder{

    private final View view;

    private final ImageView Animal;
    private final TextView tvNombAnimal;
    private final TextView tvDescripAnimal;
    public final ConstraintLayout layout;

    public CartasViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        this.Animal = itemView.findViewById(R.id.imgAnimalCarta);
        this.tvNombAnimal = itemView.findViewById(R.id.tvNomAnimal);
        this.tvDescripAnimal = itemView.findViewById(R.id.tvDescripAnimal);
        this.layout = itemView.findViewById(R.id.ConstraintLayoutCarta);
    }

    @SuppressLint("ResourceType")
    public void bind(String animal, String nombre, String descripcion) {
        Glide.with(view.getContext()).load(animal).into(Animal);
        tvNombAnimal.setText(nombre);
        tvDescripAnimal.setText(descripcion);
    }
    public static CartasViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_carta, parent, false);
        return new CartasViewHolder(view);
    }

}

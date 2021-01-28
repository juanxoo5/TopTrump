package com.example.toptrump.view.adapter.Avatar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toptrump.R;

public class EditarAvatarAdapter extends RecyclerView.Adapter<EditarAvatarAdapter.AvatarViewHolder> {

    private int[] avatares;

    public EditarAvatarAdapter(int[] avatares) {
        this.avatares = avatares;

    }

    @NonNull
    @Override
    public EditarAvatarAdapter.AvatarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avatares, parent, false);
        EditarAvatarAdapter.AvatarViewHolder holder = new EditarAvatarAdapter.AvatarViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditarAvatarAdapter.AvatarViewHolder holder, int position) {

        holder.avatar.setImageResource(avatares[position]);
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("Imagen", avatares[position]);

                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.editUsuaFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return avatares.length;
    }

    public class AvatarViewHolder extends RecyclerView.ViewHolder{

        public ImageView avatar;
        public ConstraintLayout parent_layout;

        public AvatarViewHolder(@NonNull View itemView) {
            super(itemView);
            this.avatar = itemView.findViewById(R.id.imgListAvatar);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }

    }
}

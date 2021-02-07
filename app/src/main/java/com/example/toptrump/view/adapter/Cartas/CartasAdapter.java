package com.example.toptrump.view.adapter.Cartas;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.toptrump.R;
import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.view.MainActivity;

public class CartasAdapter extends ListAdapter<Carta, CartasViewHolder> {

    public CartasAdapter( @NonNull DiffUtil.ItemCallback<Carta> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CartasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CartasViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CartasViewHolder holder, int position) {
        Carta current = getItem(position);
        if(!current.getUrl().contains("https://")){
            String url = current.getUrl();
            current.setUrl("https://informatica.ieszaidinvergeles.org:9034/toptrump/images/"+url);
        }
        holder.bind(current.getUrl(), current.getNombre(), current.getDescripcion());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public static class UsuarioDiff extends DiffUtil.ItemCallback<Carta> {

        @Override
        public boolean areItemsTheSame(@NonNull Carta oldItem, @NonNull Carta newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Carta oldItem, @NonNull Carta newItem) {
            return oldItem.getNombre().equals(newItem.getNombre());
        }
    }

}

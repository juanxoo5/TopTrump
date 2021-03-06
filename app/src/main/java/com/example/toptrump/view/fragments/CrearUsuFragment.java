package com.example.toptrump.view.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.toptrump.R;
import com.example.toptrump.model.room.pojo.Usuario;
import com.example.toptrump.view.MainActivity;
import com.example.toptrump.viewmodel.ViewModel;
import com.google.android.material.navigation.NavigationView;

public class CrearUsuFragment extends Fragment {

    private ViewModel viewModelActivity;
    Bundle bundle;
    int avatar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_crear_usu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelActivity = new ViewModelProvider(this).get(ViewModel.class);
        try {
            avatar = getArguments().getInt("Imagen");
        } catch( NullPointerException e){

        }


        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        MainActivity mainActivity = (MainActivity) view.getContext();
        Toolbar toolbar = view.findViewById(R.id.tbCrearUsuFrgm);
        mainActivity.setSupportActionBar(toolbar);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

        Button btAvatar = view.findViewById(R.id.btAvatar);
        Button btGuardar = view.findViewById(R.id.btGuardar);
        EditText etNombre = view.findViewById(R.id.etNombreUsuario);
        ImageView imgAvatar = view.findViewById(R.id.imgAvatar);
        String avatares = String.valueOf(avatar);
        if(avatares.equals("0")){
            imgAvatar.setImageResource(R.drawable.avatar);
            Log.v("XYZ" ,"" +  avatar);
        } else {
            imgAvatar.setImageResource(avatar);
            Log.v("XYZ" ,"" +  avatar);
        }

        btAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.avatarFragment);
            }
        });

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etNombre.getText().toString().isEmpty()){
                    if(avatar == 0){
                        avatar = R.drawable.avatar;
                    }
                    String nombreUsuario = etNombre.getText().toString();
                    Usuario usuario = new Usuario(nombreUsuario, avatar,0,0);
                    Log.v("XYZ" ,"" +  avatar);
                    viewModelActivity.insert(usuario);

                    navController.navigate(R.id.admUsuaFragment);
                } else {
                    etNombre.setError("Este campo no puede quedar vacío");
                }
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this.getActivity(), R.id.nav_host_fragment);
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }
    
}
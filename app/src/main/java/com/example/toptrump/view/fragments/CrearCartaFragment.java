package com.example.toptrump.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.toptrump.R;
import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.view.MainActivity;
import com.example.toptrump.viewmodel.ViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CrearCartaFragment extends Fragment {

    private ImageView imgAnimal;
    private EditText etNombreAnimal;
    private EditText etDescripcion;
    private EditText etUrlImagen;
    private Button btImagen;
    private Button btEnviar;

    private ViewModel viewModelActivity;
    private List<Carta> cartas = new ArrayList<>();
    private boolean existe = false;
    private String imagen = "https://www.lifeder.com/wp-content/uploads/2018/10/question-mark-2123967_640.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_crear_carta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity = (MainActivity) view.getContext();
        viewModelActivity = new ViewModelProvider(this).get(ViewModel.class);
        cartas = viewModelActivity.getListaCartas();

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Toolbar toolbar = view.findViewById(R.id.tbCrearCartaFrgm);
        mainActivity.setSupportActionBar(toolbar);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

        imgAnimal = view.findViewById(R.id.imgAnimal);
        etNombreAnimal = view.findViewById(R.id.etNombreAnimal);
        etDescripcion = view.findViewById(R.id.etDescripcion);
        etUrlImagen = view.findViewById(R.id.etUrlImagen);
        btImagen = view.findViewById(R.id.btCambiarImagen);
        btEnviar = view.findViewById(R.id.btEnviarAnimal);

        Glide.with(view.getContext()).load(imagen).into(imgAnimal);

        btImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etUrlImagen.getText())){
                    etUrlImagen.setError("Introduzca una url");
                }else{
                    imagen = etUrlImagen.getText().toString();
                    Glide.with(view.getContext()).load(imagen).into(imgAnimal);
                }
            }
        });

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etNombreAnimal.getText()) ||
                        TextUtils.isEmpty(etDescripcion.getText())){

                }else{
                    for (int i=0; i<cartas.size(); i++){
                        if (etNombreAnimal.getText().toString().equalsIgnoreCase(cartas.get(i).getNombre())){
                            existe = true;
                            break;
                        }
                    }
                    if (existe){
                        Snackbar.make(v, "El animal ya existe", Snackbar.LENGTH_SHORT).show();
                    }else{
                        viewModelActivity.insert(new Carta(imagen, etNombreAnimal.getText().toString(),
                                etDescripcion.getText().toString()));

                        navController.navigate(R.id.action_crearCartaFragment_to_preguntasCartaFragment);
                    }
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
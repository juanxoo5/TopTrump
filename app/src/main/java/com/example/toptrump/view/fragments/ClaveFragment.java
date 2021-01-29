package com.example.toptrump.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
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
import android.widget.Toast;

import com.example.toptrump.R;
import com.example.toptrump.view.MainActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ClaveFragment extends Fragment {

    Button bt;
    TextInputEditText texto;
    TextInputLayout control;
    String clave;
    SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_clave, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navigation(view);
        
        bt = view.findViewById(R.id.btEnvClav2);
        texto = view.findViewById(R.id.tietClave2);
        control = view.findViewById(R.id.tilClave2);

        sp = getContext().getSharedPreferences("dato", Context.MODE_PRIVATE);
        String comprobarClave = sp.getString("clave","");

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // cuando tu ya tienes la clave y has pulsado "administración"
                if(texto.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(view.getContext(), " No puede poner una contraseña vacía ", Toast.LENGTH_LONG);
                    toast.getView().setBackgroundColor(Color.RED);
                    toast.show();
                } else if(texto.getText().toString().equals(comprobarClave)){
                        Toast.makeText(view.getContext(),"Clave verificada", Toast.LENGTH_LONG).show();
                        NavHostFragment.findNavController(ClaveFragment.this).navigate(R.id.action_claveFragment_to_adminFragment);
                    } else {
                        Toast toast = Toast.makeText(view.getContext()," La clave es incorrecta ", Toast.LENGTH_LONG);
                        toast.getView().setBackgroundColor(Color.RED);
                        toast.show();
                    }
            }
        });
    }

    public void navigation(View view){

        MainActivity mainActivity = (MainActivity) view.getContext();
        Toolbar toolbar = view.findViewById(R.id.tbClaveFrgm);
        mainActivity.setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = view.findViewById(R.id.drawerLayoutClave);
        NavigationView navigationView = view.findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        Log.v("navigation controler", navController.toString());
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).setOpenableLayout(drawerLayout).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupActionBarWithNavController(mainActivity, navController, appBarConfiguration);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.claveFragment:
                        navController.navigate(R.id.claveFragment);
                        return true;
                    case R.id.juegoFragment:
                        if(!mainActivity.usuarioActivo.isEmpty()){
                            navController.navigate(R.id.juegoFragment);
                        }else {
                            Toast.makeText(view.getContext(), "Selecciona antes un usuario", Toast.LENGTH_LONG).show();
                        }
                        return true;
                    case R.id.perfilFragment:
                        if(!mainActivity.usuarioActivo.isEmpty()){
                            navController.navigate(R.id.perfilFragment);
                        }else {
                            Toast.makeText(view.getContext(), "Selecciona antes un usuario", Toast.LENGTH_LONG).show();
                        }
                        return true;
                }
                return true;
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
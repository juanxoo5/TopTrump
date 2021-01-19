package com.example.toptrump.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
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
                    if(texto.getText().toString().equals(comprobarClave)){
                        Toast.makeText(view.getContext(),"Clave verificada", Toast.LENGTH_LONG).show();
                        NavHostFragment.findNavController(ClaveFragment.this).navigate(R.id.action_claveFragment_to_adminFragment);
                    } else {
                        control.setError("La clave es incorrecta");
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
        NavigationUI.setupWithNavController(navigationView, navController);

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
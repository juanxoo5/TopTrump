package com.example.toptrump.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.toptrump.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegistFragment extends Fragment {

    private TextWatcher twClave;
    Button bt;
    TextInputEditText texto;
    TextInputLayout control;
    String clave;
    SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_regist, container, false);
    }




    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt = view.findViewById(R.id.btEnvClav);
        control = view.findViewById(R.id.tilClave);
        texto = view.findViewById(R.id.tietClave);

        NavController navController = new NavController(view.getContext());

        sp = getContext().getSharedPreferences("dato", Context.MODE_PRIVATE);
        String comprobarClave = sp.getString("clave","");

        if(!comprobarClave.isEmpty()){
            // inicias app con clave existente te lleva a juego directamente
            NavHostFragment.findNavController(RegistFragment.this).navigate(R.id.action_FirstFragment_to_userFrament);
        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Log.v("XYZ",comprobarClave.toString());
                    clave = texto.getText().toString();
                    if(clave.isEmpty()){
                        // control de errores (preguntar) y json
                        Toast toast = Toast.makeText(view.getContext()," No puede poner una contraseña vacía ", Toast.LENGTH_LONG);
                        toast.getView().setBackgroundColor(Color.RED);
                        toast.show();



                    } else {
                        // guarda y lleva a administración
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("clave", clave);
                        editor.commit();

                        Toast.makeText(view.getContext(),"Su clave ha sido guardada", Toast.LENGTH_LONG).show();
                        NavHostFragment.findNavController(RegistFragment.this).navigate(R.id.action_FirstFragment_to_adminFragment);
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
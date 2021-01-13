package com.example.toptrump.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.toptrump.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ClaveFragment extends Fragment {

    Button bt;
    TextInputEditText texto;
    TextInputLayout control;
    String clave;
    SharedPreferences sp;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt = view.findViewById(R.id.btEnvClav);
        texto = view.findViewById(R.id.tietClave);
        control = view.findViewById(R.id.tilClave);



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
}
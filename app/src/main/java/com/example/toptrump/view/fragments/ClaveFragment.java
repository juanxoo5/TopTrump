package com.example.toptrump.view.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.toptrump.R;
import com.google.android.material.textfield.TextInputEditText;

public class ClaveFragment extends Fragment {

    Button bt;
    TextInputEditText texto;
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


        sp = getContext().getSharedPreferences("dato", Context.MODE_PRIVATE);
        String comprobarClave = sp.getString("clave","");

        if(!comprobarClave.isEmpty()){
            // inicias app con clave existente
            // te tiene que llevar a juego directamente
        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!comprobarClave.isEmpty()){
                    // cuando tu ya tienes la clave y has pulsado "administración"
                    if(texto.getText().toString().equals(comprobarClave)){
                        // direccionar a administración
                    }
                    Log.v("XYZ",comprobarClave.toString());
                } else {
                    clave = texto.getText().toString();
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("clave", clave);
                    editor.commit();
                    // lleva a administración.
                }
            }
        });
    }
}
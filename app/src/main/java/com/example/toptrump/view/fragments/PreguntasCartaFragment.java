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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.toptrump.R;
import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.model.room.pojo.Pregunta;
import com.example.toptrump.view.MainActivity;
import com.example.toptrump.viewmodel.ViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class PreguntasCartaFragment extends Fragment {

    private TextView tvPregunta1;
    private TextView tvPregunta2;
    private TextView tvPregunta3;
    private TextView tvPregunta4;
    private TextInputEditText tietPregunta1;
    private TextInputEditText tietPregunta2;
    private TextInputEditText tietPregunta3;
    private TextInputEditText tietPregunta4;
    private Button btCrear;

    private ViewModel viewModelActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preguntas_carta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity = (MainActivity) view.getContext();
        viewModelActivity = new ViewModelProvider(this).get(ViewModel.class);

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Toolbar toolbar = view.findViewById(R.id.tbPreguntasFrgm);
        mainActivity.setSupportActionBar(toolbar);

        tvPregunta1 = view.findViewById(R.id.tvPregunt1);
        tvPregunta2 = view.findViewById(R.id.tvPregunt2);
        tvPregunta3 = view.findViewById(R.id.tvPregunt3);
        tvPregunta4 = view.findViewById(R.id.tvPregunt4);
        tietPregunta1 = view.findViewById(R.id.tietPregunta1);
        tietPregunta2 = view.findViewById(R.id.tietPregunta2);
        tietPregunta3 = view.findViewById(R.id.tietPregunta3);
        tietPregunta4 = view.findViewById(R.id.tietPregunta4);
        btCrear = view.findViewById(R.id.btPreguntas);

        btCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(tietPregunta1.getText()) ||
                        TextUtils.isEmpty(tietPregunta2.getText()) ||
                        TextUtils.isEmpty(tietPregunta3.getText()) ||
                        TextUtils.isEmpty(tietPregunta4.getText())){
                    Snackbar.make(v, "Todos los campos deben estar rellenos", Snackbar.LENGTH_SHORT).show();
                }else{
                    int respuesta1 = Integer.parseInt(tietPregunta1.getText().toString());
                    int respuesta2 = Integer.parseInt(tietPregunta2.getText().toString());
                    int respuesta3 = Integer.parseInt(tietPregunta3.getText().toString());
                    int respuesta4 = Integer.parseInt(tietPregunta4.getText().toString());
                    if (respuesta4 <0 || respuesta4>10){
                        tietPregunta4.setError("El valor tiene que estar comprendido entre 0 y 10");
                    }else {
                        Long id = viewModelActivity.getListaCartas().get(viewModelActivity.getListaCartas().size()-1).getId();
                        Log.v("xyz", "id "+ id);
                        Pregunta pregunta1 = new Pregunta(id, tvPregunta1.getText().toString(), respuesta1);
                        Pregunta pregunta2 = new Pregunta(id, tvPregunta2.getText().toString(), respuesta2);
                        Pregunta pregunta3 = new Pregunta(id, tvPregunta3.getText().toString(), respuesta3);
                        Pregunta pregunta4 = new Pregunta(id, tvPregunta4.getText().toString(), respuesta4);
                        Log.v("xyz", pregunta1.toString());
                        viewModelActivity.insert(pregunta1);
                        viewModelActivity.insert(pregunta2);
                        viewModelActivity.insert(pregunta3);
                        viewModelActivity.insert(pregunta4);
                        Log.v("xyz", "Carta insertada");
                        navController.navigate(R.id.action_preguntasCartaFragment_to_admCartFragment);
                    }
                }
            }
        });

    }
}
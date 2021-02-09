package com.example.toptrump.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.toptrump.R;
import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.model.room.pojo.Pregunta;
import com.example.toptrump.view.MainActivity;
import com.example.toptrump.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MuestraPreguntasFragment extends Fragment {

    private TextView tvPregunta1;
    private TextView tvPregunta2;
    private TextView tvPregunta3;
    private TextView tvPregunta4;

    private ViewModel viewModelActivity;
    private List<Pregunta> listaPreguntas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_muestra_preguntas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity = (MainActivity) view.getContext();
        viewModelActivity = new ViewModelProvider(this).get(ViewModel.class);

        long id = getArguments().getLong("id");

        tvPregunta1 = view.findViewById(R.id.tvMostrarPregunta1);
        tvPregunta2 = view.findViewById(R.id.tvMostrarPregunta2);
        tvPregunta3 = view.findViewById(R.id.tvMostrarPregunta3);
        tvPregunta4 = view.findViewById(R.id.tvMostrarPregunta4);

        viewModelActivity.getListaPreguntas().observe(getViewLifecycleOwner(), new Observer<List<Pregunta>>() {
            @Override
            public void onChanged(List<Pregunta> preguntas) {
                int count = 0;
                int i = 0;
                do{
                    if(preguntas.get(i).getIdcarta()==id) {
                        listaPreguntas.add(preguntas.get(i));
                        count++;
                    }
                    i++;
                }while (count!=4);
                tvPregunta1.setText(listaPreguntas.get(0).getPregunta()+" "+listaPreguntas.get(0).getRespuesta());
                tvPregunta2.setText(listaPreguntas.get(1).getPregunta()+" "+listaPreguntas.get(1).getRespuesta());
                tvPregunta3.setText(listaPreguntas.get(2).getPregunta()+" "+listaPreguntas.get(2).getRespuesta());
                tvPregunta4.setText(listaPreguntas.get(3).getPregunta()+" "+listaPreguntas.get(3).getRespuesta());
            }
        });

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Toolbar toolbar = view.findViewById(R.id.tbMortrarPreguntasFrgm);
        mainActivity.setSupportActionBar(toolbar);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

    }

}
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

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.toptrump.R;
import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.model.room.pojo.Pregunta;
import com.example.toptrump.view.MainActivity;
import com.example.toptrump.viewmodel.ViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Random;

public class JuegoFragment extends Fragment {

    private ViewModel viewModel;
    private Carta carta;
    private Pregunta pregunta;
    private MainActivity mainActivity;

    private ImageView imgAnimal;
    private TextView tvNombre;
    private TextView tvDescripcion;
    private TextView tvPregunta;
    private EditText etRespuesta;
    private Button btRespuesta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_juego, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        mainActivity = (MainActivity) view.getContext();

        if (!viewModel.getListaCartas().toString().equals("[]")){
            seleccionCarta();

            imgAnimal = view.findViewById(R.id.imgCart);
            tvNombre = view.findViewById(R.id.tvNombre);
            tvDescripcion = view.findViewById(R.id.tvDescripcion);
            tvPregunta = view.findViewById(R.id.tvPregunta);
            etRespuesta = view.findViewById(R.id.etRespuesta);
            btRespuesta = view.findViewById(R.id.btEnviar);

            Glide.with(view.getContext()).load(carta.getUrl()).into(imgAnimal);
            tvNombre.setText(carta.getNombre());
            tvDescripcion.setText(carta.getDescripcion());
            tvPregunta.setText(pregunta.getPregunta());
        }

        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);

        btRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!viewModel.getListaCartas().toString().equals("[]")) {
                    if (etRespuesta.getText().toString().isEmpty()) {
                        Toast.makeText(v.getContext(), "Introduzca una respuesta", Toast.LENGTH_LONG).show();
                    } else if (etRespuesta.getText().toString().equalsIgnoreCase(pregunta.getRespuesta())) {
                        int numResp = mainActivity.usuarioActivo.get(0).getNumRes();
                        int restCor = mainActivity.usuarioActivo.get(0).getResCor();

                        numResp = numResp + 1;
                        restCor = restCor + 1;

                        mainActivity.usuarioActivo.get(0).setNumRes(numResp);
                        mainActivity.usuarioActivo.get(0).setResCor(restCor);
                    } else {
                        int numResp = mainActivity.usuarioActivo.get(0).getNumRes();

                        numResp = numResp + 1;

                        mainActivity.usuarioActivo.get(0).setNumRes(numResp);
                    }

                    viewModel.updateUsuario(mainActivity.usuarioActivo.get(0));
                    navController.navigate(R.id.juegoFragment);
                }
            }
        });

    }

    public void seleccionCarta(){

        ArrayList<Pregunta> preguntas = new ArrayList<>();

        Random r = new Random();

        int randomCarta = r.nextInt(viewModel.getListaCartas().size());
        carta = viewModel.getListaCartas().get(randomCarta);

        for(int i = 0; i <= viewModel.getListaPreguntas().size(); i++){

            if(viewModel.getListaPreguntas().get(i).getIdcarta() == carta.getId()){
                preguntas.add(viewModel.getListaPreguntas().get(i));
            }

        }

        int randomPregunta = r.nextInt(preguntas.size());
        pregunta = viewModel.getListaPreguntas().get(randomPregunta);

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
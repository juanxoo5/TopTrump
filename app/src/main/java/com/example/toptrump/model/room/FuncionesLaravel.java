package com.example.toptrump.model.room;

import android.util.Log;

import com.example.toptrump.model.room.dao.CartaDao;
import com.example.toptrump.model.room.dao.PreguntaDao;
import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.model.room.pojo.Pregunta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FuncionesLaravel {

    private String url = "https://informatica.ieszaidinvergeles.org:9038/laravel/miCocheApp/public/api/";
    private List<Carta> listaCartas = new ArrayList<>();
    private List<Pregunta> listaPreguntas = new ArrayList<>();

    public void insertCarta(Carta carta){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartaDao cartaDao = retrofit.create(CartaDao.class);

        Call<Carta> request = cartaDao.postCarta(carta);
        request.enqueue(new Callback<Carta>() {
            @Override
            public void onResponse(Call<Carta> call, Response<Carta> response) {
            }

            @Override
            public void onFailure(Call<Carta> call, Throwable t) {
                Log.v("XYZ",t.getMessage());
            }
        });
    }

    public void editCarta(long id, Carta carta){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartaDao cartaDao = retrofit.create(CartaDao.class);

        Call<Boolean> request = cartaDao.putCarta(id, carta);
        request.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.v("XYZ",t.getLocalizedMessage());
                Log.v("XYZCoche", carta.toString());
            }
        });
    }

    public void deleteCarta(long id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartaDao cartaDao = retrofit.create(CartaDao.class);

        Call<Boolean> request = cartaDao.deleteCarta(id);

        request.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.v("XYZ", t.getMessage());
            }
        });
    }

    public List<Carta> mostrarCartas(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartaDao cartaDao = retrofit.create(CartaDao.class);

        Call<ArrayList<Carta>> request = cartaDao.getCartas();
        request.enqueue(new Callback<ArrayList<Carta>>() {
            @Override
            public void onResponse(Call<ArrayList<Carta>> call, Response<ArrayList<Carta>> response) {
                Log.v("XYZresponse", response.body().toString());
                listaCartas = response.body();
                Log.v("XYZlista", listaCartas.toString());
            }

            @Override
            public void onFailure(Call<ArrayList<Carta>> call, Throwable t) {
                Log.v("XYZ",t.getMessage());
            }
        });
        Log.v("XYZlista2", listaCartas.toString());
        return listaCartas;
    }

    public void insertPregunta(Pregunta pregunta){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PreguntaDao preguntaDao = retrofit.create(PreguntaDao.class);

        Call<Pregunta> request = preguntaDao.postPregunta(pregunta);
        request.enqueue(new Callback<Pregunta>() {
            @Override
            public void onResponse(Call<Pregunta> call, Response<Pregunta> response) {
            }

            @Override
            public void onFailure(Call<Pregunta> call, Throwable t) {
                Log.v("XYZ",t.getMessage());
            }
        });
    }

    public void editPregunta(long id, Pregunta pregunta){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PreguntaDao preguntaDao = retrofit.create(PreguntaDao.class);

        Call<Boolean> request = preguntaDao.putPregunta(id, pregunta);
        request.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.v("XYZ",t.getLocalizedMessage());
                Log.v("XYZCoche", pregunta.toString());
            }
        });
    }

    public void deletePregunta(long id){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PreguntaDao preguntaDao = retrofit.create(PreguntaDao.class);

        Call<Boolean> request = preguntaDao.deletePregunta(id);

        request.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.v("XYZ", t.getMessage());
            }
        });
    }

    public List<Pregunta> mostrarPreguntas(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PreguntaDao preguntaDao = retrofit.create(PreguntaDao.class);

        Call<ArrayList<Pregunta>> request = preguntaDao.getPreguntas();
        request.enqueue(new Callback<ArrayList<Pregunta>>() {
            @Override
            public void onResponse(Call<ArrayList<Pregunta>> call, Response<ArrayList<Pregunta>> response) {
                Log.v("XYZresponse", response.body().toString());
                listaPreguntas = response.body();
                Log.v("XYZlista", listaPreguntas.toString());
            }

            @Override
            public void onFailure(Call<ArrayList<Pregunta>> call, Throwable t) {
                Log.v("XYZ",t.getMessage());
            }
        });
        Log.v("XYZlista2", listaPreguntas.toString());
        return listaPreguntas;
    }

}

package com.example.toptrump.model.room;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

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

    private String url = "https://informatica.ieszaidinvergeles.org:9034/laravel/toptrump/public/api/";
    public MutableLiveData<List<Carta>> listaCartas = new MutableLiveData<>();
    public MutableLiveData<List<Pregunta>> listaPreguntas = new MutableLiveData<>();

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

    public void mostrarCartas(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CartaDao cartaDao = retrofit.create(CartaDao.class);

        Call<ArrayList<Carta>> request = cartaDao.getCartas();
        request.enqueue(new Callback<ArrayList<Carta>>() {
            @Override
            public void onResponse(Call<ArrayList<Carta>> call, Response<ArrayList<Carta>> response) {
                try{
                    Log.v("XYZresponse", response.body().toString());
                    listaCartas.setValue(response.body());
                    Log.v("XYZlista", listaCartas.toString());
                }catch (NullPointerException e){
                    Log.v("xyz", "Ha habido un error");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Carta>> call, Throwable t) {
                Log.v("XYZ",t.getMessage());
            }
        });
        Log.v("XYZlista2", listaCartas.toString());
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

    public void mostrarPreguntas(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PreguntaDao preguntaDao = retrofit.create(PreguntaDao.class);

        Call<ArrayList<Pregunta>> request = preguntaDao.getPreguntas();
        request.enqueue(new Callback<ArrayList<Pregunta>>() {
            @Override
            public void onResponse(Call<ArrayList<Pregunta>> call, Response<ArrayList<Pregunta>> response) {
                try{
                    Log.v("XYZresponse", response.body().toString());
                    listaPreguntas.setValue(response.body());
                    Log.v("XYZlista", listaCartas.toString());
                }catch (NullPointerException e){
                    Log.v("xyz", "Ha habido un error");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pregunta>> call, Throwable t) {
                Log.v("XYZ",t.getMessage());
            }
        });
        Log.v("XYZlista2", listaPreguntas.toString());
    }

}

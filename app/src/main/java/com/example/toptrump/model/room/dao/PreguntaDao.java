package com.example.toptrump.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.model.room.pojo.Pregunta;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PreguntaDao {

    @DELETE("coche/{id}")
    Call<Boolean> deletePregunta(@Path("id") long id);

    @GET("coche")
    Call<ArrayList<Pregunta>> getPreguntas();

    @GET("coche")
    Call<ResponseBody> getString();

    @POST("coche")
    Call<Pregunta> postPregunta(@Body Pregunta pregunta);

    @PUT("coche/{id}")
    Call<Boolean> putPregunta(@Path("id") long id, @Body Pregunta pregunta);
}

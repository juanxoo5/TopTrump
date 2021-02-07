package com.example.toptrump.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.toptrump.model.room.pojo.Carta;

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


public interface CartaDao {

    @DELETE("carta/{id}")
    Call<Boolean> deleteCarta(@Path("id") long id);

    @GET("carta")
    Call<ArrayList<Carta>> getCartas();

    @GET("carta")
    Call<ResponseBody> getString();

    @POST("carta")
    Call<Carta> postCarta(@Body Carta carta);

    @PUT("carta/{id}")
    Call<Boolean> putCarta(@Path("id") long id, @Body Carta carta);
}

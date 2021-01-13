package com.example.toptrump.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.model.room.pojo.Pregunta;

import java.util.List;

@Dao
public interface PreguntaDao {

    @Query("delete from pregunta where id = :id")
    int delete(long id);

    @Query("select * from pregunta where id = :id")
    Pregunta get(long id);

    @Query("select * from pregunta order by id")
    LiveData<List<Pregunta>> getAll();

    @Insert
    long insert(Pregunta pregunta);

    @Update
    int update(Pregunta pregunta);
}

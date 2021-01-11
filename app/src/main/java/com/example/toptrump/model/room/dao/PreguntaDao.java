package com.example.toptrump.model.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.model.room.pojo.Pregunta;

@Dao
public interface PreguntaDao {

    @Query("delete from pregunta where id = :id")
    int delete(long id);

    @Query("select * from pregunta where id = :id")
    Pregunta get(long id);

    @Insert
    long insert(Pregunta pregunta);

    @Update
    int update(Pregunta pregunta);
}

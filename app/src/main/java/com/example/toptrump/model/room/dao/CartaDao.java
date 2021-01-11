package com.example.toptrump.model.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.toptrump.model.room.pojo.Carta;

@Dao
public interface CartaDao {

    @Query("delete from carta where id = :id")
    int delete(long id);

    @Query("select * from carta where id = :id")
    Carta get(long id);

    @Insert
    long insert(Carta carta);

    @Update
    int update(Carta carta);
}

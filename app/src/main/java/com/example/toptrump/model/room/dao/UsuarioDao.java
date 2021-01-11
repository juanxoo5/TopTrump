package com.example.toptrump.model.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.model.room.pojo.Usuario;

@Dao
public interface UsuarioDao {

    @Query("delete from usuario where id = :id")
    int delete(long id);

    @Query("select * from usuario where id = :id")
    Usuario get(long id);

    @Insert
    long insert(Usuario usuario);

    @Update
    int update(Usuario usuario);
}

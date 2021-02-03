package com.example.toptrump.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.toptrump.model.room.dao.CartaDao;
import com.example.toptrump.model.room.dao.PreguntaDao;
import com.example.toptrump.model.room.dao.UsuarioDao;
import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.model.room.pojo.Pregunta;
import com.example.toptrump.model.room.pojo.Usuario;

@Database(entities = {Usuario.class}, version = 1, exportSchema = false)
public abstract class DBTrump extends RoomDatabase {

    public abstract UsuarioDao getUsuarioDao();

    private static volatile DBTrump INSTANCE;


    public static synchronized DBTrump getDB(final Context context){
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DBTrump.class, "dbtrump").build();
        }
        return INSTANCE;
    }

}

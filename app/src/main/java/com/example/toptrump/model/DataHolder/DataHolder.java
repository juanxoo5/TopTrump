package com.example.toptrump.model.DataHolder;

import com.example.toptrump.model.room.pojo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {
    public final List<Usuario> usuarioactivo = new ArrayList<Usuario>();

    private DataHolder() {}

    public static DataHolder getInstance() {
        if( instance == null ) {
            instance = new DataHolder();
        }
        return instance;
    }

    private static DataHolder instance;
}

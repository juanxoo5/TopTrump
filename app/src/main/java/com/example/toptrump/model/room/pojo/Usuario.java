package com.example.toptrump.model.room.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuario")
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @NonNull
    @ColumnInfo(name = "avatar")
    private int avatar;

    @NonNull
    @ColumnInfo(name = "numRes")
    private int numRes;

    @NonNull
    @ColumnInfo(name = "resCor")
    private int resCor;

    public Usuario(String nombre, int avatar, @NonNull int numRes, @NonNull int resCor) {
        this.nombre = nombre;
        this.avatar = avatar;
        this.numRes = numRes;
        this.resCor = resCor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    @NonNull
    public int getNumRes() {
        return numRes;
    }

    public void setNumRes(@NonNull int numRes) {
        this.numRes = numRes;
    }

    @NonNull
    public int getResCor() {
        return resCor;
    }

    public void setResCor(@NonNull int resCor) {
        this.resCor = resCor;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", avatar=" + avatar +
                ", numRes='" + numRes + '\'' +
                ", resCor='" + resCor + '\'' +
                '}';
    }
}

package com.example.toptrump.model.room.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

public class Pregunta {

    private long id;

    private long idcarta;

    private String pregunta;

    private int respuesta;

    public Pregunta(long idcarta, String pregunta, int respuesta) {
        this.idcarta = idcarta;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdcarta() {
        return idcarta;
    }

    public void setIdcarta(long idcarta) {
        this.idcarta = idcarta;
    }

    @NonNull
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @NonNull
    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "id=" + id +
                ", idcarta=" + idcarta +
                ", pregunta='" + pregunta + '\'' +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }

}

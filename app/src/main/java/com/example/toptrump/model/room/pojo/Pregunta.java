package com.example.toptrump.model.room.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "pregunta",
        foreignKeys = @ForeignKey(
                entity = Carta.class,
                parentColumns = "id",
                childColumns = "idcarta",
                onDelete = ForeignKey.RESTRICT))
public class Pregunta {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "idcarta")
    private int idcarta;

    @NonNull
    @ColumnInfo(name = "pregunta")
    private String pregunta;

    @NonNull
    @ColumnInfo(name = "respuesta")
    private int respuesta;

    public Pregunta(int idcarta, @NonNull String pregunta, @NonNull int respuesta) {
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

    public int getIdcarta() {
        return idcarta;
    }

    public void setIdcarta(int idcarta) {
        this.idcarta = idcarta;
    }

    @NonNull
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(@NonNull String pregunta) {
        this.pregunta = pregunta;
    }

    @NonNull
    public int getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(@NonNull int respuesta) {
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

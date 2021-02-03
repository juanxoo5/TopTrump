package com.example.toptrump.model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.toptrump.model.room.DBTrump;
import com.example.toptrump.model.room.FuncionesLaravel;
import com.example.toptrump.model.room.dao.CartaDao;
import com.example.toptrump.model.room.dao.PreguntaDao;
import com.example.toptrump.model.room.dao.UsuarioDao;
import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.model.room.pojo.Pregunta;
import com.example.toptrump.model.room.pojo.Usuario;
import com.example.toptrump.model.util.UtilThread;

import java.util.List;

public class Repository {

    private FuncionesLaravel funcionesLaravel;
    private UsuarioDao usuarioDao;

    private List<Carta> listaCartas;
    private List<Pregunta> listaPreguntas;
    private LiveData<List<Usuario>> liveUsuarioList;

    public Repository(Context context) {
        DBTrump db = DBTrump.getDB(context);
        usuarioDao = db.getUsuarioDao();
        this.funcionesLaravel = new FuncionesLaravel();
    }

    public List<Carta> getListaCartas() {
        listaCartas = funcionesLaravel.mostrarCartas();
        return listaCartas;
    }

    public List<Pregunta> getListaPreguntas() {
        listaPreguntas = funcionesLaravel.mostrarPreguntas();
        return listaPreguntas;
    }

    public LiveData<List<Usuario>> getListaUsuarios() {
        liveUsuarioList = usuarioDao.getAll();
        return liveUsuarioList;
    }

    public void insert(Carta c) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                funcionesLaravel.insertCarta(c);
            }
        });
    }

    public void insert(Pregunta p) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                funcionesLaravel.insertPregunta(p);
            }
        });
    }

    public void insert(Usuario u) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                usuarioDao.insert(u);
            }
        });
    }

    public void deleteCarta(long id){
        new Thread(){
            @Override
            public void run() {
                funcionesLaravel.deleteCarta(id);
            }
        }.start();
    }

    public void deletePregunta(long id){
        new Thread(){
            @Override
            public void run() {
                funcionesLaravel.deletePregunta(id);
            }
        }.start();
    }

    public void deleteUsuario(long id){
        new Thread(){
            @Override
            public void run() {
                usuarioDao.delete(id);
            }
        }.start();
    }

    // faltan updates
    public void updateUsuario(Usuario usuario){
        new Thread(){
            @Override
            public void run() {
                usuarioDao.update(usuario);
            }
        }.start();
    }

}

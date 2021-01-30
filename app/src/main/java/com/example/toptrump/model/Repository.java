package com.example.toptrump.model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.toptrump.model.room.DBTrump;
import com.example.toptrump.model.room.dao.CartaDao;
import com.example.toptrump.model.room.dao.PreguntaDao;
import com.example.toptrump.model.room.dao.UsuarioDao;
import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.model.room.pojo.Pregunta;
import com.example.toptrump.model.room.pojo.Usuario;
import com.example.toptrump.model.util.UtilThread;

import java.util.List;

public class Repository {

    private CartaDao cartaDao;
    private PreguntaDao preguntaDao;
    private UsuarioDao usuarioDao;

    private LiveData<List<Carta>> liveCartaList;
    private LiveData<List<Pregunta>> livePreguntaList;
    private LiveData<List<Usuario>> liveUsuarioList;

    private MutableLiveData<Long> liveCartaInsertId = new MutableLiveData<>();
    private MutableLiveData<Long> livePreguntaInsertId = new MutableLiveData<>();
    private MutableLiveData<Long> liveUsuarioInsertId = new MutableLiveData<>();

    public Repository(Context context) {
        DBTrump db = DBTrump.getDB(context);
        cartaDao = db.getCartaDao();
        preguntaDao = db.getPreguntaDao();
        usuarioDao = db.getUsuarioDao();

    }

    public LiveData<List<Carta>> getListaCartas() {
        liveCartaList = cartaDao.getAll();
        return liveCartaList;
    }

    public LiveData<List<Pregunta>> getListaPreguntas() {
        livePreguntaList = preguntaDao.getAll();
        return livePreguntaList;
    }

    public LiveData<List<Usuario>> getListaUsuarios() {
        liveUsuarioList = usuarioDao.getAll();
        return liveUsuarioList;
    }

    public void insert(Carta c) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    long id = cartaDao.insert(c);
                    liveCartaInsertId.postValue(id);
                } catch (Exception e) {
                    liveCartaInsertId.postValue(0l);
                }
            }
        });
    }

    public void insert(Pregunta p) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    long id = preguntaDao.insert(p);
                    livePreguntaInsertId.postValue(id);
                } catch (Exception e) {
                    livePreguntaInsertId.postValue(0l);
                }
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
                cartaDao.delete(id);
            }
        }.start();
    }

    public void deletePregunta(long id){
        new Thread(){
            @Override
            public void run() {
                preguntaDao.delete(id);
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

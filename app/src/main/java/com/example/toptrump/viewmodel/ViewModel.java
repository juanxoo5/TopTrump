package com.example.toptrump.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.toptrump.model.Repository;
import com.example.toptrump.model.room.pojo.Carta;
import com.example.toptrump.model.room.pojo.Pregunta;
import com.example.toptrump.model.room.pojo.Usuario;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public List<Carta> getListaCartas() {
        return repository.getListaCartas();
    }

    public List<Pregunta> getListaPreguntas() {
        return repository.getListaPreguntas();
    }

    public LiveData<List<Usuario>> getListaUsuarios() {
        return repository.getListaUsuarios();
    }

    public void insert(Carta c) {
        repository.insert(c);
    }

    public void insert(Pregunta p) {
        repository.insert(p);
    }

    public void insert(Usuario u) {
        repository.insert(u);
    }

    public void deleteCarta(long id) {
        repository.deleteCarta(id);
    }
    public void deletePregunta(long id) {
        repository.deletePregunta(id);
    }
    public void deleteUsuario(long id) {
        repository.deleteUsuario(id);
    }
    public void updateUsuario(Usuario usuario){
        repository.updateUsuario(usuario);
    }
}

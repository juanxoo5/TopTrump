package com.example.toptrump.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.toptrump.R;
import com.example.toptrump.model.Broadcast;
import com.example.toptrump.model.DataHolder.DataHolder;
import com.example.toptrump.model.room.pojo.Usuario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int PERMISO_PHONE_STATE = 1;
    private IntentFilter bateriabaja;
    private Broadcast bc;
    public List<Usuario> usuarioActivo = DataHolder.getInstance().usuarioactivo;
    public ArrayList<Usuario> editarUsuario = new ArrayList<>();

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISO_PHONE_STATE: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                    }
                } else {
                    obtenerPermisoTelefono();
                } return;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bateriabaja = new IntentFilter();
        bateriabaja.addAction(Intent.ACTION_BATTERY_LOW);
        bc = new Broadcast();

        setContentView(R.layout.activity_main);
        obtenerPermisoTelefono();
    }

    private void obtenerPermisoTelefono() {
        int result = PackageManager.PERMISSION_GRANTED;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            result = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
        }
        if(result == PackageManager.PERMISSION_DENIED) {
            obtenerEstadoPermisos();
        }
    }

    private void obtenerEstadoPermisos() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){
        } else {
            if(shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
                mostrarExplicacionDetalladaPermPhoneState();
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISO_PHONE_STATE);
            }
        }
    }

    private void mostrarExplicacionDetalladaPermPhoneState() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_permiso_estado);
        builder.setMessage(R.string.mensaje_permiso_contactos);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISO_PHONE_STATE);
            }
        });
        builder.setNegativeButton(R.string.cancelar, null);
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(bc,bateriabaja);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(bc);
    }

    public View.OnClickListener botones = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
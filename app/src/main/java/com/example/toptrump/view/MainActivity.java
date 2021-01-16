package com.example.toptrump.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.toptrump.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private final int PERMISO_PHONE_STATE = 1;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISO_PHONE_STATE) {
            int StateConcedido = grantResults[0];
            if (StateConcedido == PackageManager.PERMISSION_GRANTED) {
                Log.v("xyzyxPermisos", "obteniendo permisos");
            } else {
                obtenerEstadoPermisos();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void obtenerEstadoPermisos() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){
            Log.v("xyzPermisos", "obteniendo permisos");
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

}
package com.example.toptrump.model;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String accion = intent.getAction();
        if(accion.equals(Intent.ACTION_BATTERY_LOW)){
            Toast.makeText(context, "Top Trump - Batería baja, por favor conecte el cargador", Toast.LENGTH_LONG).show();
            Log.v("XYZ", "BATTERY LOW!!");
        }
    }
}



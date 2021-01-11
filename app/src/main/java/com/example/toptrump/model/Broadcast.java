package com.example.toptrump.model;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Battery's dying!!", Toast.LENGTH_LONG).show();
        Log.e("", "BATTERY LOW!!");

    }
}

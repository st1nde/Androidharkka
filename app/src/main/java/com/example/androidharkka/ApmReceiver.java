package com.example.androidharkka;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ApmReceiver extends BroadcastReceiver {

public static final String TAG = "ApmMode";

    @Override
    public void onReceive(Context context, Intent intent) {

        int duration = Toast.LENGTH_LONG;
        String offtext = context.getResources().getString(R.string.text_apm_off);
        String ontext = context.getResources().getString(R.string.text_apm_on);
        boolean state = intent.getBooleanExtra("state", true);
        Log.d( TAG,"state" + state);

        Toast toast;
        if (state)
        {
            toast = Toast.makeText(context, ontext, duration);
        }
        else {
            toast = Toast.makeText(context, offtext, duration);
        }
        toast.show();


    }
}
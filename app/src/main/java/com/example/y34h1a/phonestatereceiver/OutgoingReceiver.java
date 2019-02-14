package com.example.y34h1a.phonestatereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;


public class OutgoingReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("sami", "outgoing receiver");
        Intent intentStartService = new Intent(context, CallLocationService.class);
        intentStartService.putExtra("phone", intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER));
        context.startService(intentStartService);

    }
}

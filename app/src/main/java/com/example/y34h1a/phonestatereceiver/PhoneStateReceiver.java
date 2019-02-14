package com.example.y34h1a.phonestatereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;


public class PhoneStateReceiver extends BroadcastReceiver {
    static boolean offhookCalled = false;
    static boolean idleCalled = false;
    static boolean isRining = false;
    static String number;
    @Override
    public void onReceive(Context context, Intent intent) {

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);


        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            isRining = true;
            Log.i("sami", "ringing");
            number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

        }

        if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))){
            Log.i("sami", "outside offhook");
            if (!offhookCalled && isRining){
                offhookCalled = true;
                idleCalled = false;

                Intent intentStartService = new Intent(context, CallLocationService.class);
                intentStartService.putExtra("state", intent.getStringExtra(TelephonyManager.EXTRA_STATE));
                intentStartService.putExtra("phone", number);
                context.startService(intentStartService);
            }

        }

        if (!idleCalled && isRining){
            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                offhookCalled = false;
                idleCalled = true;
                isRining = false;
                Intent intentStartService = new Intent(context, CallLocationService.class);
                intentStartService.putExtra("state", intent.getStringExtra(TelephonyManager.EXTRA_STATE));
                intentStartService.putExtra("phone", number);
                context.startService(intentStartService);

            }
        }
    }
}



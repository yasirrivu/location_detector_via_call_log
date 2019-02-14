package com.example.y34h1a.phonestatereceiver;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.widget.Toast;



public class CallLocationService extends Service {
    private DbManager dbManager;
    private Context context;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context  = getApplicationContext();
        dbManager = CommonObjClass.getDatabaseHelper(context);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String state = intent.getStringExtra("state");
        final String incomingNumber = intent.getStringExtra("phone");

        Toast.makeText(getApplicationContext(), "Inside Service", Toast.LENGTH_SHORT).show();

        LocationListener mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location loc) {
                try {

                    Toast.makeText(context,"Call Received State",Toast.LENGTH_SHORT).show();
                    final CallInfo callInfo = new CallInfo();
                    callInfo.setPhnNumber(incomingNumber);
                    callInfo.setLatitute(loc.getLatitude() + "");
                    callInfo.setLongitude(loc.getLongitude() + "");
                    dbManager.setCallInfo(callInfo);
                    stopSelf();

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        if (state != null){
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                Toast.makeText(context,"Ringing",Toast.LENGTH_SHORT).show();
                stopSelf();
            }

            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)){
                Toast.makeText(context,"IDLE",Toast.LENGTH_SHORT).show();
                stopSelf();
            }

            if ((state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))){

                LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000,
                        1000, mLocationListener, Looper.getMainLooper());
            }
        }else {

            LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000,
                    1000, mLocationListener, Looper.getMainLooper());
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Service Stopped", Toast.LENGTH_SHORT).show();
    }
}

package com.example.android_life_cycle;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


public class LifeCycleService extends Service {
    private int startMode;
    private boolean allowRebind;
    private final IBinder binder = new LocalBinder();
    static final String SERVICE_LOG = "Life cycle service";

    public class LocalBinder extends Binder {
        LifeCycleService getService() {
            return LifeCycleService.this;
        }
    }

    @Override
    public void onCreate() {
        Log.d(SERVICE_LOG, "On Create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(SERVICE_LOG, "onStartCommand");
        return startMode;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(SERVICE_LOG, "onBind");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(SERVICE_LOG, "onUnbind");
        return allowRebind;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(SERVICE_LOG, "On rebind");
    }

    @Override
    public void onDestroy() {
        Log.d(SERVICE_LOG, "On destroy");
    }
}
package com.example.android_life_cycle;

import android.content.ComponentName;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;


public class MainActivity extends AppCompatActivity {
    static final String MAIN_ACTIVITY_LOG = "MainActivityLifeCycle";
    private Button startService;
    private Button bindService;
    private Button unbindService;
    private Button stopService;
    private LifeCycleService _service;
    private ServiceConnection connection;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("state","On create");

        startService = findViewById(R.id.startService);
        bindService = findViewById(R.id.bindService);
        stopService = findViewById(R.id.stopService);
        unbindService = findViewById(R.id.unbindService);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LifeCycleService.class);
                startService(intent);
            }
        });

        bindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LifeCycleService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
        });

        unbindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBound) return;
                unbindService(connection);
                mBound = false;
            }
        });

        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LifeCycleService.class);
                stopService(intent);
            }
        });

         connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName className,
                                           IBinder service) {
                LifeCycleService.LocalBinder binder = (LifeCycleService.LocalBinder) service;
                _service = binder.getService();
                mBound = true;
            }
            @Override
            public void onServiceDisconnected(ComponentName arg0) {
                mBound = false;
            }
        };
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(MAIN_ACTIVITY_LOG, "On start");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(MAIN_ACTIVITY_LOG, "On Resume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(MAIN_ACTIVITY_LOG, "On Pause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(MAIN_ACTIVITY_LOG, "On Stop");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(MAIN_ACTIVITY_LOG, "On Restart");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(MAIN_ACTIVITY_LOG, "On Destroy");
    }
}
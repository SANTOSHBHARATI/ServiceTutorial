package com.service.tutorial.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.service.tutorial.R;
import com.service.tutorial.localbinderservice.LocalService;

public class LocalBinderServiceActivity extends AppCompatActivity {

    private boolean isBound = false;
    private LocalService localService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this,LocalService.class);
        bindService(intent,connection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
       if (isBound){
           // Unbind from the service
           unbindService(connection);
           isBound = false;
       }
    }

    public void onClickNumerGenerator(View view){
    if (isBound){
        int num = localService.getRandomNum();
        Toast.makeText(LocalBinderServiceActivity.this,"Number : "+num,Toast.LENGTH_SHORT).show();
    }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBound){
            // Unbind from the service
            unbindService(connection);
            isBound = false;
        }
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            LocalService.LocalBinder localBinder = (LocalService.LocalBinder)service;
            localService = localBinder.getService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
}

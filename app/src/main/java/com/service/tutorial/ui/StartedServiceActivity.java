package com.service.tutorial.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.service.tutorial.R;
import com.service.tutorial.localbinderservice.LocalService;
import com.service.tutorial.startedservice.StartedServiceTutorial;

public class StartedServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);
    }


    public void onClickStart(View view){
        Intent intent = new Intent(this,StartedServiceTutorial.class);
        startService(intent);
    }
    public void onClickStop(View view){
        Intent intent = new Intent(this,StartedServiceTutorial.class);
        stopService(intent);
    }

}

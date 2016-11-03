package com.service.tutorial.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.service.tutorial.R;
import com.service.tutorial.startedservice.StartedServiceTutorial;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickStart(View view){
        Intent intent = new Intent(this,StartedServiceActivity.class);
        startActivity(intent);
    }

    public void onClickBinder(View view){
        Intent intent = new Intent(this,LocalBinderServiceActivity.class);
        startActivity(intent);
    }

    public void onClickMessenger(View view){
        Intent intent = new Intent(this,MessengerServiceActivity.class);
        startActivity(intent);
    }
}

package com.service.tutorial.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.service.tutorial.R;
import com.service.tutorial.localbinderservice.LocalService;
import com.service.tutorial.messengerservice.MessengerService;

public class MessengerServiceActivity extends AppCompatActivity {

    private boolean isBound;

    /** Messenger for communicating with the service. */
    private Messenger messengerService;

    /**
     * Class for interacting with the main interface of the service.
     */

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            messengerService = new Messenger(service);
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            messengerService = null;
            isBound = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this,MessengerService.class);
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
        Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO,0,0);
        try {
            messengerService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }else {
        return;
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



}

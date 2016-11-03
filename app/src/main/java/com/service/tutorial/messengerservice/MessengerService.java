package com.service.tutorial.messengerservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;


/**
 * Created by santosh.bharati on 11/3/2016.
 */

public class MessengerService extends Service {

    /** Command to the service to display a message */
   public static final int MSG_SAY_HELLO = 1;

    /**
     * Target we publish for clients to send messages to Messenger Service.
     */
    private Messenger  messenger = new Messenger(new MessageHandler());


    /**
     * Handler of messages from clients.
     */
    class MessageHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(), "Messenger Service", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }


    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

}

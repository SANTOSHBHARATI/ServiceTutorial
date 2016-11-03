package com.service.tutorial.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;

/**
 * Created by santosh.bharati on 11/2/2016.
 */

public class ServiceTutorial extends Service {

    private Looper mLoopService;
    private ServiceHandler mServiceHandler;

    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler{

        public ServiceHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        HandlerThread handlerThread = new HandlerThread("ServiceHandler", Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();
        mLoopService = handlerThread.getLooper();
        mServiceHandler = new ServiceHandler(mLoopService);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showMsg("Service Started");
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showMsg("Service Destroyed");
    }

    /**
     * This method is used for showing Toast message
     * @param msg
     */
    private void showMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}

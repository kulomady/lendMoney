package com.arm.hackbri.landmoney.services;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;


/**
 * LOMFCMListenerService
 * Created by anggaprasetiyo on 9/11/16.
 */
public class LOMFCMListenerService extends FirebaseMessagingService {

    private static final String TAG = LOMFCMListenerService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
            Log.d(TAG, "Data key: " + entry.getKey() + " Data value: " + entry.getValue());
        }
        super.onMessageReceived(remoteMessage);
    }


}

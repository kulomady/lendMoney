package com.arm.hackbri.landmoney.services;

import android.content.Intent;

import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * LOMInstanceIDListenerService
 * Created by anggaprasetiyo on 9/11/16.
 */
public class LOMInstanceIDListenerService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationIDIntentService.class);
        startService(intent);
    }
}

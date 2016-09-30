package com.arm.hackbri.landmoney.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.arm.hackbri.landmoney.interactor.PreferencesInteractorImpl;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * RegistrationIDIntentService
 * Created by anggaprasetiyo on 9/11/16.
 */
public class RegistrationIDIntentService extends IntentService {
    private static final String TAG = RegistrationIDIntentService.class.getSimpleName();

    public RegistrationIDIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, "FCM Registration Token: " + token);
        new PreferencesInteractorImpl().storeDeviceId(this, token);
    }
}

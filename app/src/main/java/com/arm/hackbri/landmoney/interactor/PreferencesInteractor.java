package com.arm.hackbri.landmoney.interactor;

import android.content.Context;

import com.arm.hackbri.landmoney.model.response.Profile;

/**
 * PreferencesInteractor
 * Created by anggaprasetiyo on 9/30/16.
 */

public interface PreferencesInteractor {

    void storeUserData(Context context, Profile profile);

    Profile getUserData(Context context);

    void clearAllUserData(Context context);

    void storeDeviceId(Context context, String deviceId);

    String getDeviceId(Context context);

    boolean isLogin(Context context);
}

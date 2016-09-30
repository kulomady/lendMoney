package com.arm.hackbri.landmoney.interactor;

import android.content.Context;
import android.content.SharedPreferences;

import com.arm.hackbri.landmoney.model.response.Profile;
import com.google.gson.Gson;

/**
 * PreferencesInteractorImpl
 * Created by anggaprasetiyo on 9/30/16.
 */

public class PreferencesInteractorImpl implements PreferencesInteractor {
    private static final String PREFERENCE_USER_DATA = "PREFERENCE_USER_DATA";
    private static final String USER_PROFILE = "USER_DATA";
    private static final String USER_DEVICE_ID = "USER_DEVICE_ID";
    private Gson gson;

    public PreferencesInteractorImpl() {
        this.gson = new Gson();
    }

    @Override
    public void storeUserData(Context context, Profile profile) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_USER_DATA,
                Context.MODE_PRIVATE);
        sharedPref.edit().putString(USER_PROFILE, gson.toJson(profile)).apply();
    }

    @Override
    public Profile getUserData(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_USER_DATA,
                Context.MODE_PRIVATE);
        String strUserData = sharedPref.getString(USER_PROFILE, "");
        return strUserData.isEmpty() ? null : gson.fromJson(strUserData, Profile.class);
    }

    @Override
    public void clearAllUserData(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_USER_DATA,
                Context.MODE_PRIVATE);
        sharedPref.edit().putString(USER_PROFILE, "").apply();
        sharedPref.edit().putString(USER_DEVICE_ID, "").apply();
    }

    @Override
    public void storeDeviceId(Context context, String deviceId) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_USER_DATA,
                Context.MODE_PRIVATE);
        sharedPref.edit().putString(USER_DEVICE_ID, deviceId).apply();
    }

    @Override
    public String getDeviceId(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_USER_DATA,
                Context.MODE_PRIVATE);
        return sharedPref.getString(USER_DEVICE_ID, null);
    }

    @Override
    public boolean isLogin(Context context) {
        return getUserData(context) != null;
    }
}

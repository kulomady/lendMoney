package com.arm.hackbri.landmoney.network.interceptor;

import android.util.Log;

import com.arm.hackbri.landmoney.network.exception.HttpErrorException;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * LMInterceptor
 * Created by anggaprasetiyo on 9/29/16.
 */

public class LMInterceptor implements Interceptor {
    private static final String TAG_LOG = LMInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        int count = 0;
        while (!response.isSuccessful() && count < 2) {
            Log.d(TAG_LOG, "Request is not successful - " + count + " Error code : " + response.code());
            count++;
            response = chain.proceed(chain.request());
        }
        if (!response.isSuccessful()) {
            throw new HttpErrorException(response.code());
        }
        return response;
    }
}

package com.arm.hackbri.landmoney.network.converter;

import com.arm.hackbri.landmoney.network.LMResponse;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * LMResponseConverter
 * Created by anggaprasetiyo on 9/29/16.
 */

public class LMResponseConverter extends Converter.Factory {
    private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        if (LMResponse.class == type) {
            return new Converter<ResponseBody, LMResponse>() {
                @Override
                public LMResponse convert(ResponseBody value) throws IOException {
                    return LMResponse.factory(value.string());
                }
            };
        }
        return null;
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        if (LMResponse.class == type) {
            return new Converter<LMResponse, RequestBody>() {
                @Override
                public RequestBody convert(LMResponse value) throws IOException {
                    return RequestBody.create(MEDIA_TYPE, value.getStrResponse());
                }
            };
        }
        return null;
    }
}

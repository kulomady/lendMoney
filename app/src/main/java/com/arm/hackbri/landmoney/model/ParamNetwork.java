package com.arm.hackbri.landmoney.model;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * ParamNetwork
 * Created by anggaprasetiyo on 9/29/16.
 */

public class ParamNetwork {
    private Map<String, String> paramMap = new HashMap<>();

    private ParamNetwork(Builder builder) {
        paramMap = builder.paramMap;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void put(@NonNull String key, @NonNull String value) {
        paramMap.put(key, value);
    }

    public static final class Builder {
        private Map<String, String> paramMap;

        public Builder() {
            paramMap = new HashMap<>();
        }

        public Builder put(@NonNull String key, @NonNull String value) {
            paramMap.put(key, value);
            return this;
        }

        public ParamNetwork build() {
            return new ParamNetwork(this);
        }
    }
}

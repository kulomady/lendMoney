package com.arm.hackbri.landmoney.network;

import com.arm.hackbri.landmoney.network.exception.AuthUserException;
import com.arm.hackbri.landmoney.network.exception.GeneralErrorException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * LMResponse
 * Created by anggaprasetiyo on 9/29/16.
 */

public class LMResponse {

    private String message;
    private boolean hasToken;
    private boolean isSuccess;
    private String strResponse;
    private String stringData;
    private JsonElement jsonData;
    private String token;
    private Object objData;

    private Gson gson = new GsonBuilder().disableHtmlEscaping()
            .setPrettyPrinting().create();

    public static LMResponse factory(String strResponse) throws AuthUserException, GeneralErrorException {
        JsonElement jsonElement = new JsonParser().parse(strResponse);
        JsonObject jsonResponse = jsonElement.getAsJsonObject();
        int status = jsonResponse.get("status").getAsInt();
        String message = "";
        if (jsonResponse.has("message")) message = jsonResponse.get("message").getAsString();
        switch (status) {
            case 2:
                throw new AuthUserException();
            case 0:
                throw new GeneralErrorException(message);
        }
        String strData = null;
        if (jsonResponse.has("data") && jsonResponse.get("data").isJsonObject()) {
            strData = jsonResponse.get("data").getAsJsonObject().toString();
        } else if (jsonResponse.has("data") && jsonResponse.get("data").isJsonArray()) {
            strData = jsonResponse.get("data").getAsJsonArray().toString();
        }
        boolean hasToken = false;
        String strToken = "";
        if (jsonResponse.has("token")) {
            hasToken = true;
            strToken = jsonResponse.get("token").getAsString();
        }
        LMResponse response = new LMResponse();
        response.setJsonData(jsonResponse.get("data"));
        response.setMessage(message);
        response.setHasToken(hasToken);
        response.setToken(strToken);
        response.setStringData(strData);
        response.setStrResponse(strResponse);
        return response;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHasToken() {
        return hasToken;
    }

    public void setHasToken(boolean hasToken) {
        this.hasToken = hasToken;
    }


    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getStrResponse() {
        return strResponse;
    }

    public void setStrResponse(String strResponse) {
        this.strResponse = strResponse;
    }

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }

    public JsonElement getJsonData() {
        return jsonData;
    }

    public void setJsonData(JsonElement jsonData) {
        this.jsonData = jsonData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @SuppressWarnings("unchecked")
    public <T> T convertDataObj(Class<T> clazz) {
        if (objData == null) {
            try {
                this.objData = gson.fromJson(stringData, clazz);
                return (T) objData;
            } catch (ClassCastException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return (T) objData;
        }
    }
}

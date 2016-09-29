package com.arm.hackbri.landmoney.network.exception;

import java.io.IOException;

/**
 * Created by Angga.Prasetiyo on 6/2/2016.
 */
public class HttpErrorException extends IOException {

    private static final long serialVersionUID = 2934454037601874L;

    public HttpErrorException() {
    }

    public HttpErrorException(int errorCode) {
        super("HTTP Error ==> Error code : " + errorCode);
    }

    public HttpErrorException(String detailMessage) {
        super(detailMessage);
    }
}

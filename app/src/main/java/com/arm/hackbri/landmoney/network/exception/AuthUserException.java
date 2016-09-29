package com.arm.hackbri.landmoney.network.exception;

import java.io.IOException;

/**
 * Created by Angga.Prasetiyo on 6/3/2016.
 */
public class AuthUserException extends IOException {
    private static final long serialVersionUID = 1900686662007280326L;

    public AuthUserException() {
        super("Expired Session, Please login");
    }

    public AuthUserException(String detailMessage) {
        super(detailMessage);
    }
}

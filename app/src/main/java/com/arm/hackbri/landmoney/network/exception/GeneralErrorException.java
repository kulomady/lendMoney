package com.arm.hackbri.landmoney.network.exception;

import java.io.IOException;

/**
 * GeneralErrorException
 * Created by anggaprasetiyo on 9/10/16.
 */
public class GeneralErrorException extends IOException {
    private static final long serialVersionUID = -321608302149655820L;

    public GeneralErrorException() {
        super("General Error");
    }

    public GeneralErrorException(String detailMessage) {
        super(detailMessage);
    }
}

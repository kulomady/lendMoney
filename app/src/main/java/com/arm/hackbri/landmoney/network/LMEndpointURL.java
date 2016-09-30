package com.arm.hackbri.landmoney.network;

/**
 * LMEndpointURL
 * Created by anggaprasetiyo on 9/29/16.
 */

public interface LMEndpointURL {

    String BASE_URL = "https://hack-bri-dev.mybluemix.net/v0/";

    String PATH_LOGIN = "login";
    String PATH_REGISTER = "register";
    String PATH_GET_DEBIT_LIST = "listdebt";
    String PATH_GET_CREDIT_LIST = "listcredit";
    String PATH_GET_DIALOG_NEW_CREDIT = "dialognewcredit";
    String PATH_GET_DIALOG_NEW_DEBIT = "dialognewdebt";
    String PATH_GET_TBANK_SALDO = "bri/tbank/saldo";
    String PATH_CREATE_DEBIT = "createnewdebt";
    String PATH_CREATE_CREDIT = "createnewcredit";
    String PATH_INVITE = "invite";
    String PATH_ACCEPT_DEBIT = "acceptdebt";
    String PATH_FCM_TOKEN = "fcm";
    String PATH_GET_DEPOSIT = "";
    String PATH_REDEEM_LIST = "";
    String PATH_TOKEN = "bri/bri/tbank/token";
    String PATH_TRANSFER = "bri/tbank/transfer";
}

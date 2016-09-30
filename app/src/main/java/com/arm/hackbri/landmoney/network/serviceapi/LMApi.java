package com.arm.hackbri.landmoney.network.serviceapi;

import com.arm.hackbri.landmoney.network.LMEndpointURL;
import com.arm.hackbri.landmoney.network.LMResponse;

import java.util.Map;

import retrofit.Response;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * LMApi
 * Created by anggaprasetiyo on 9/29/16.
 */

public interface LMApi {

    @FormUrlEncoded
    @POST(LMEndpointURL.PATH_LOGIN)
    Observable<LMResponse> login(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(LMEndpointURL.PATH_REGISTER)
    Observable<LMResponse> register(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(LMEndpointURL.PATH_TRANSFER)
    Observable<Response> transfer(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(LMEndpointURL.PATH_GET_TBANK_SALDO)
    Observable<LMResponse> getTBankSaldo(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(LMEndpointURL.PATH_GET_DEBIT_LIST)
    Observable<LMResponse> getDebitList(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(LMEndpointURL.PATH_GET_CREDIT_LIST)
    Observable<LMResponse> getCreditList(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(LMEndpointURL.PATH_GET_DIALOG_NEW_CREDIT)
    Observable<LMResponse> getDialogNewCredit(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(LMEndpointURL.PATH_GET_DIALOG_NEW_DEBIT)
    Observable<LMResponse> getDialogNewDebit(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(LMEndpointURL.PATH_GET_CREDIT_LIST)
    Observable<LMResponse> generateToken(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(LMEndpointURL.PATH_CREATE_DEBIT)
    Observable<LMResponse> createDebit(@FieldMap Map<String, String> params);

    @FormUrlEncoded
    @POST(LMEndpointURL.PATH_CREATE_CREDIT)
    Observable<LMResponse> createCredit(@FieldMap Map<String, String> params);

    //======================================
    //BUSUK GAK KEPAKE
    //=======================================
    @GET(LMEndpointURL.PATH_GET_CREDIT_LIST)
    Observable<LMResponse> getDetailPinjaman(@QueryMap Map<String, String> params);

    @GET(LMEndpointURL.PATH_GET_CREDIT_LIST)
    Observable<LMResponse> getDetailHutang(@QueryMap Map<String, String> params);

    @GET(LMEndpointURL.PATH_REDEEM_LIST)
    Observable<LMResponse> getRedeemList(@QueryMap Map<String, String> params);
}

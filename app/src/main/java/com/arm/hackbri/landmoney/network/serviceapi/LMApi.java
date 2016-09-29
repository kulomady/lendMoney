package com.arm.hackbri.landmoney.network.serviceapi;

import com.arm.hackbri.landmoney.network.LMEndpointURL;
import com.arm.hackbri.landmoney.network.LMResponse;

import java.util.Map;

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

    @GET(LMEndpointURL.PATH_GET_DETAIL_HUTANG)
    Observable<LMResponse> getHutangList(@QueryMap Map<String, String> params);

    @GET(LMEndpointURL.PATH_GET_PINJAMAN_LIST)
    Observable<LMResponse> getPinjamanList(@QueryMap Map<String, String> params);

    @GET(LMEndpointURL.PATH_GET_DETAIL_PINJAMAN)
    Observable<LMResponse> getDetailPinjaman(@QueryMap Map<String, String> params);

    @GET(LMEndpointURL.PATH_GET_DETAIL_HUTANG)
    Observable<LMResponse> getDetailHutang(@QueryMap Map<String, String> params);

    @GET(LMEndpointURL.PATH_REDEEM_LIST)
    Observable<LMResponse> getRedeemList(@QueryMap Map<String, String> params);
}

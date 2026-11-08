package com.bytedance.sdk.adnet.game.http.cb;

import com.bytedance.sdk.adnet.game.http.req.base.NetBuilder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class NetCallback<T, R extends NetBuilder> {
    public static final int ERROR_CODE_DATA_CONVERT = 10005;
    public static final String ERROR_CODE_DATA_CONVERT_DESC = "Network error when data convert";
    public static final int ERROR_CODE_HTTP_CODE_ERROR = 10006;
    public static final String ERROR_CODE_HTTP_CODE_ERROR_DESC = "Network error 200 < Http code < 300";
    public static final int ERROR_CODE_IO_EXCEPTION = 10003;
    public static final String ERROR_CODE_IO_EXCEPTION_DESC = "Network error when connected, IOException";
    public static final int ERROR_CODE_NO_NETWORK = 10002;
    public static final String ERROR_CODE_NO_NETWORK_DESC = "No network available";
    public static final int ERROR_CODE_PARSE_BODY_EXCEPTION = 10004;
    public static final String ERROR_CODE_PARSE_BODY_EXCEPTION_DESC = "Network error when parse body";
    public static final int ERROR_CODE_PRE_REQUEST = 10001;
    public static final String ERROR_CODE_PRE_REQUEST_DESC = "Net request error before call okHttpClient.newCall(request).enqueue(callback)";
    public static final int ERROR_CODE_REQUEST_SUCCESS = 10000;
    public static final String ERROR_CODE_REQUEST_SUCCESS_DESC = "Net request success";
    public static final int ERROR_CODE_SERVER_ERROR = 10007;
    public static final String ERROR_CODE_SERVER_ERROR_DESC = "Request success but server return error";

    public void onNetEnd(R r) {
    }

    public void onNetError(R r, NetResponse<T, R> netResponse) {
    }

    public void onNetProgress(R r, float f, float f2, float f3) {
    }

    public void onNetStart(R r) {
    }

    public void onNetSuccess(R r, NetResponse<T, R> netResponse) {
    }
}

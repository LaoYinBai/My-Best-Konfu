package com.ss.union.game.sdk.common.net.http.base.in;

import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class ICoreNetCallback<T, R extends ICoreNetRequest> {
    public static final int ERROR_CODE_DATA_CONVERT = 10005;
    public static final String ERROR_CODE_DATA_CONVERT_DESC = "网络请求失败，请重试(10005)";
    public static final int ERROR_CODE_HTTP_CODE_ERROR = 10006;
    public static final String ERROR_CODE_HTTP_CODE_ERROR_DESC = "网络请求失败，请重试(10006)";
    public static final int ERROR_CODE_IO_EXCEPTION = 10003;
    public static final String ERROR_CODE_IO_EXCEPTION_DESC = "网络请求失败，请重试(10003)";
    public static final int ERROR_CODE_NO_NETWORK = 10002;
    public static final String ERROR_CODE_NO_NETWORK_DESC = "网络异常，请保持网络连接畅通，再重新操作";
    public static final int ERROR_CODE_PARSE_BODY_EXCEPTION = 10004;
    public static final String ERROR_CODE_PARSE_BODY_EXCEPTION_DESC = "网络请求失败，请重试(10004)";
    public static final int ERROR_CODE_PRE_REQUEST = 10001;
    public static final String ERROR_CODE_PRE_REQUEST_DESC = "网络请求失败，请重试(10001)";
    public static final int ERROR_CODE_REQUEST_SUCCESS = 10000;
    public static final String ERROR_CODE_REQUEST_SUCCESS_DESC = "网络请求成功";
    public static final int ERROR_CODE_SERVER_ERROR = 10007;
    public static final String ERROR_CODE_SERVER_ERROR_DESC = "网络请求失败，请重试(100017)";

    public void onNetEnd(R r) {
    }

    public void onNetError(R r, CoreNetResponse<T, R> coreNetResponse) {
    }

    public void onNetProgress(R r, float f, float f2, float f3) {
    }

    public void onNetStart(R r) {
    }

    public void onNetSuccess(R r, CoreNetResponse<T, R> coreNetResponse) {
    }
}

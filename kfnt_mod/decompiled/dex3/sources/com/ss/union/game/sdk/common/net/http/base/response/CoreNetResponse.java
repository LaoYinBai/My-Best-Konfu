package com.ss.union.game.sdk.common.net.http.base.response;

import android.text.TextUtils;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CoreNetResponse<T, R extends ICoreNetRequest> {
    private int a;
    private String b;
    public T data;
    public Throwable error;
    public Map<String, String> headers;
    public String httpBody;
    public int httpCode;
    public String httpMessage;
    public R request;
    public int serverErrorCode;
    public String serverErrorLogId;
    public String serverErrorMessage;
    public long serverTimestampS;

    public CoreNetResponse(T t, int i, String str, String str2, int i2, String str3, Throwable th, Map<String, String> map, R r) {
        this.data = t;
        this.a = i2;
        this.b = str3;
        this.httpCode = i;
        this.httpMessage = str;
        this.httpBody = str2;
        this.error = th;
        this.headers = map;
        this.request = r;
    }

    @Deprecated
    public String header(String str) {
        if (TextUtils.isEmpty(str) || this.headers == null) {
            return null;
        }
        return this.headers.get(str);
    }

    public String headerIgnoreCase(String str) {
        if (TextUtils.isEmpty(str) || this.headers == null) {
            return null;
        }
        for (String str2 : this.headers.keySet()) {
            if (str.equalsIgnoreCase(str2)) {
                return this.headers.get(str2);
            }
        }
        return null;
    }

    public boolean isSuccess() {
        return this.a == 10000;
    }

    public CoreNetResponse serverError(int i, String str, String str2) {
        this.a = ICoreNetCallback.ERROR_CODE_SERVER_ERROR;
        this.serverErrorCode = i;
        this.serverErrorMessage = str;
        this.serverErrorLogId = str2;
        return this;
    }

    public boolean isServerError() {
        return this.a == 10007;
    }

    public boolean isHttpError() {
        return this.a == 10006;
    }

    public String message() {
        String str;
        if (this.a == 10000) {
            str = "success";
        } else if (this.a == 10007) {
            str = TextUtils.isEmpty(this.serverErrorMessage) ? ICoreNetCallback.ERROR_CODE_SERVER_ERROR_DESC : this.serverErrorMessage;
        } else if (this.a == 10006) {
            str = TextUtils.isEmpty(this.httpMessage) ? ICoreNetCallback.ERROR_CODE_HTTP_CODE_ERROR_DESC : this.httpMessage;
        } else if (this.a == 10002) {
            str = "网络异常，请保持网络连接畅通，再重新操作";
        } else if (this.a == 10001) {
            str = ICoreNetCallback.ERROR_CODE_PRE_REQUEST_DESC;
        } else if (this.a == 10003) {
            str = ICoreNetCallback.ERROR_CODE_IO_EXCEPTION_DESC;
        } else if (this.a == 10004) {
            str = ICoreNetCallback.ERROR_CODE_PARSE_BODY_EXCEPTION_DESC;
        } else {
            str = this.a == 10005 ? ICoreNetCallback.ERROR_CODE_DATA_CONVERT_DESC : null;
        }
        return TextUtils.isEmpty(str) ? this.b : str;
    }

    public int code() {
        if (this.a == 10007) {
            return this.serverErrorCode;
        }
        if (this.a == 10006) {
            return this.httpCode;
        }
        return this.a;
    }
}

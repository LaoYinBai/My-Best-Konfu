package com.ss.union.game.sdk.common.net.http.base.in;

import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest;
import com.ss.union.game.sdk.common.net.http.base.response.CoreHttpResponse;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ICoreNetRequest<T extends ICoreNetRequest> {
    T allowResponseNull(boolean z);

    void api(ICoreNetCallback<JSONObject, T> iCoreNetCallback);

    void api2(ICoreNetApi2Callback iCoreNetApi2Callback);

    T enableCommonHeader(boolean z);

    T enableCommonParam(boolean z);

    T header(String str, String str2);

    Map<String, String> headers();

    boolean isEnableCommonHeader();

    boolean isEnableCommonParam();

    CoreNetResponse<JSONObject, T> json();

    void json(ICoreNetCallback<JSONObject, T> iCoreNetCallback);

    CoreNetResponse<JSONArray, T> jsonArray();

    void jsonArray(ICoreNetCallback<JSONArray, T> iCoreNetCallback);

    String method();

    CoreNetResponse<CoreHttpResponse, T> response();

    void response(ICoreNetCallback<CoreHttpResponse, T> iCoreNetCallback);

    CoreNetResponse<String, T> string();

    void string(ICoreNetCallback<String, T> iCoreNetCallback);

    T tag(Object obj);

    Object tag();

    T timeoutConnSecond(int i);

    T timeoutReadSecond(int i);

    T timeoutWriteSecond(int i);

    T url(String str);

    String url();

    T urlParam(String str, String str2);

    Map<String, String> urlParam();
}

package com.ss.union.game.sdk.core.base.event;

import android.text.TextUtils;
import com.ss.android.download.api.config.DownloadNetworkFactory;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.net.http.base.in.ACoreRequestPost;
import com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback;
import com.ss.union.game.sdk.common.net.http.base.response.CoreNetResponse;
import com.umeng.message.MsgConstant;
import com.umeng.message.util.HttpRequest;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NoneDidUploadStrategy implements INetUploadStrategy {
    private static NoneDidUploadStrategy a = new NoneDidUploadStrategy();

    public interface SendLogCallback {
        void onFailure();

        void onSuccess();
    }

    @Override // com.ss.union.game.sdk.core.base.event.INetUploadStrategy
    public int handle() {
        return -1;
    }

    public static NoneDidUploadStrategy getInstance() {
        return a;
    }

    public void onEvent(String str, String str2) {
        onEvent(str, str2, null);
    }

    public void onEvent(String str, String str2, SendLogCallback sendLogCallback) {
        a(str, str2, null, 0, 0L, null, null, sendLogCallback);
    }

    private void a(String str, String str2, String str3, int i, long j, String str4, Map<String, Object> map, SendLogCallback sendLogCallback) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("value", j);
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("event_type_value", str2);
            }
            if (i != 0) {
                jSONObject.put("error_code", i);
            }
            if (!TextUtils.isEmpty(str4)) {
                jSONObject.put("logid", str4);
            }
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put("event_value", str3);
            }
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(MsgConstant.INAPP_LABEL, str);
            }
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (!TextUtils.isEmpty(key) && value != null) {
                        jSONObject.put(key, value);
                    }
                }
            }
        } catch (Throwable unused) {
        }
        a(new c(new b(str2, jSONObject)), sendLogCallback);
    }

    private void a(c cVar, SendLogCallback sendLogCallback) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(INetUploadStrategy.KEY_MAGIC, "ss_app_log");
            if (!EventJSONHeaders.getInstance().isInitSuccess()) {
                EventJSONHeaders.getInstance().init();
            }
            jSONObject.put("header", EventJSONHeaders.getInstance().getJSONObject());
            jSONObject.put(INetUploadStrategy.KEY_EVENT, cVar.c());
            a(APP_EVENT_LOG_URL + "?timestamp=" + System.currentTimeMillis(), jSONObject.toString(), sendLogCallback);
        } catch (Throwable unused) {
            if (sendLogCallback != null) {
                sendLogCallback.onFailure();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(String str, String str2, final SendLogCallback sendLogCallback) {
        ((ACoreRequestPost) ((ACoreRequestPost) ((ACoreRequestPost) ((ACoreRequestPost) CoreNetClient.post(str).enableCommonHeader(true)).header(HttpRequest.HEADER_CONTENT_ENCODING, HttpRequest.ENCODING_GZIP)).header("Content-Type", DownloadNetworkFactory.ContentType.JSON)).enableCommonParam(false)).enableGzip(true).upJson(str2).json(new ICoreNetCallback<JSONObject, ACoreRequestPost>() { // from class: com.ss.union.game.sdk.core.base.event.NoneDidUploadStrategy.1
            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onNetError(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetError(aCoreRequestPost, coreNetResponse);
                if (sendLogCallback != null) {
                    sendLogCallback.onFailure();
                }
            }

            @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onNetSuccess(ACoreRequestPost aCoreRequestPost, CoreNetResponse<JSONObject, ACoreRequestPost> coreNetResponse) {
                super.onNetSuccess(aCoreRequestPost, coreNetResponse);
                JSONObject jSONObject = coreNetResponse.data;
                if (jSONObject != null && "ss_app_log".equals(jSONObject.optString(INetUploadStrategy.KEY_MAGIC)) && "success".equals(jSONObject.optString("message"))) {
                    if (sendLogCallback != null) {
                        sendLogCallback.onSuccess();
                    }
                } else if (sendLogCallback != null) {
                    sendLogCallback.onFailure();
                }
            }
        });
    }
}

package com.tachikoma.core.component.network;

import com.tachikoma.core.api.IRequestCallbackInner;
import com.tachikoma.core.utility.UIThreadUtil;
import java.io.IOException;
import java.util.HashMap;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RequestOKHttpDelegate {
    private OkHttpClient mOkHttpClient;

    public RequestOKHttpDelegate(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
    }

    private void sendHttpPostRequest(String str, String str2, int i, HashMap<String, Object> map, HashMap<String, Object> map2, final IRequestCallbackInner iRequestCallbackInner) {
        HashMap map3;
        if (map != null) {
            map3 = new HashMap();
            for (String str3 : map.keySet()) {
                map3.put(str3, String.valueOf(map.get(str3)));
            }
        } else {
            map3 = null;
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (String str4 : map2.keySet()) {
            builder.add(str4, String.valueOf(map2.get(str4)));
        }
        this.mOkHttpClient.newCall(new Request.Builder().url(str).headers(map3 == null ? new Headers.Builder().build() : Headers.of(map3)).post(builder.build()).build()).enqueue(new Callback() { // from class: com.tachikoma.core.component.network.RequestOKHttpDelegate.1
            public void onFailure(Call call, IOException iOException) {
                if (iRequestCallbackInner != null) {
                    UIThreadUtil.runOnUiThread(new Runnable() { // from class: com.tachikoma.core.component.network.RequestOKHttpDelegate.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            iRequestCallbackInner.onError(null);
                        }
                    });
                }
            }

            public void onResponse(Call call, final Response response) {
                if (iRequestCallbackInner == null) {
                    return;
                }
                UIThreadUtil.runOnUiThread(response.code() != 200 ? new Runnable() { // from class: com.tachikoma.core.component.network.RequestOKHttpDelegate.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        iRequestCallbackInner.onError(null);
                    }
                } : new Runnable() { // from class: com.tachikoma.core.component.network.RequestOKHttpDelegate.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                        iRequestCallbackInner.onComplete(response.toString());
                    }
                });
            }
        });
    }

    public void send(String str, String str2, int i, HashMap<String, Object> map, HashMap<String, Object> map2, IRequestCallbackInner iRequestCallbackInner) {
        sendHttpPostRequest(str, str2, i, map, map2, iRequestCallbackInner);
    }
}

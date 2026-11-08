package com.ss.union.game.sdk.common.net.tnc;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.Headers;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.HttpUrl;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Interceptor;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;
import com.bytedance.sdk.adtnc.sdk.a.a;
import com.bytedance.sdk.adtnc.sdk.a.b;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Ok3TNCInterceptor implements Interceptor {
    private String a;

    public Ok3TNCInterceptor(String str) {
        this.a = str;
    }

    public Response intercept(Interceptor.Chain chain) {
        Request requestA = a(chain);
        try {
            Response responseProceed = chain.proceed(requestA);
            if (responseProceed.isSuccessful()) {
                a(requestA, responseProceed);
            } else {
                a(requestA, new IOException());
            }
            return responseProceed;
        } catch (Throwable th) {
            a(requestA, th);
            throw th;
        }
    }

    private Request a(Interceptor.Chain chain) {
        Request request = chain.request();
        HttpUrl httpUrlUrl = request.url();
        String string = httpUrlUrl.url().toString();
        String strFilterUrl = VTncUtils.filterUrl(this.a, string);
        if (string.equals(strFilterUrl)) {
            return request;
        }
        LogUtils.log("TNCInterceptor", " oldUrl = %s \n Replace \n new Url = %s", string, strFilterUrl);
        return request.newBuilder().url(httpUrlUrl.newBuilder(strFilterUrl).build()).build();
    }

    private void a(Request request, Response response) {
        VTncUtils.onResponse(this.a, new a(request.url().toString()), new b(response.code(), a(response.headers())));
    }

    private void a(Request request, Throwable th) {
        VTncUtils.onError(this.a, new a(request.url().toString()), th);
    }

    private Map<String, String> a(Headers headers) {
        HashMap map = new HashMap();
        if (headers != null && headers.size() > 0) {
            for (int i = 0; i < headers.size(); i++) {
                map.put(headers.name(i), headers.value(i));
            }
        }
        return map;
    }
}

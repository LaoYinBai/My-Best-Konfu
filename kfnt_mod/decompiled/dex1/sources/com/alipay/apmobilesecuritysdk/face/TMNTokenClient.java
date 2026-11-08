package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.alipay.sdk.cons.b;
import com.alipay.security.mobile.module.a.a;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TMNTokenClient {

    /* renamed from: a, reason: collision with root package name */
    private static TMNTokenClient f2517a;

    /* renamed from: b, reason: collision with root package name */
    private Context f2518b;

    public interface InitResultListener {
        void onResult(String str, int i);
    }

    private TMNTokenClient(Context context) {
        this.f2518b = null;
        if (context == null) {
            throw new IllegalArgumentException("TMNTokenClient initialization error: context is null.");
        }
        this.f2518b = context;
    }

    public static TMNTokenClient getInstance(Context context) {
        if (f2517a == null) {
            synchronized (TMNTokenClient.class) {
                if (f2517a == null) {
                    f2517a = new TMNTokenClient(context);
                }
            }
        }
        return f2517a;
    }

    public void intiToken(final String str, String str2, String str3, final InitResultListener initResultListener) {
        if (a.a(str) && initResultListener != null) {
            initResultListener.onResult("", 2);
        }
        if (a.a(str2) && initResultListener != null) {
            initResultListener.onResult("", 3);
        }
        final HashMap map = new HashMap();
        map.put(b.g, UtdidWrapper.getUtdid(this.f2518b));
        map.put("tid", "");
        map.put("userId", "");
        map.put(DispatchConstants.APP_NAME, str);
        map.put("appKeyClient", str2);
        map.put("appchannel", "openapi");
        map.put("sessionId", str3);
        map.put("rpcVersion", "8");
        com.alipay.apmobilesecuritysdk.f.b.a().a(new Runnable() { // from class: com.alipay.apmobilesecuritysdk.face.TMNTokenClient.1
            @Override // java.lang.Runnable
            public void run() {
                int iA = new com.alipay.apmobilesecuritysdk.a.a(TMNTokenClient.this.f2518b).a(map);
                if (initResultListener == null) {
                    return;
                }
                if (iA != 0) {
                    initResultListener.onResult("", iA);
                } else {
                    initResultListener.onResult(com.alipay.apmobilesecuritysdk.a.a.a(TMNTokenClient.this.f2518b, str), 0);
                }
            }
        });
    }
}

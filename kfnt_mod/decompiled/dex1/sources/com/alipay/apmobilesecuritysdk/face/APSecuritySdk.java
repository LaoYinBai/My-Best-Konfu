package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.alipay.apmobilesecuritysdk.a.a;
import com.alipay.apmobilesecuritysdk.e.d;
import com.alipay.apmobilesecuritysdk.e.g;
import com.alipay.apmobilesecuritysdk.e.h;
import com.alipay.apmobilesecuritysdk.e.i;
import com.alipay.apmobilesecuritysdk.otherid.UmidSdkWrapper;
import com.alipay.apmobilesecuritysdk.otherid.UtdidWrapper;
import com.alipay.sdk.cons.b;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class APSecuritySdk {

    /* renamed from: a, reason: collision with root package name */
    private static APSecuritySdk f2510a;

    /* renamed from: c, reason: collision with root package name */
    private static Object f2511c = new Object();

    /* renamed from: b, reason: collision with root package name */
    private Context f2512b;

    public interface InitResultListener {
        void onResult(TokenResult tokenResult);
    }

    public class TokenResult {
        public String apdid;
        public String apdidToken;
        public String clientKey;
        public String umidToken;

        public TokenResult() {
        }
    }

    private APSecuritySdk(Context context) {
        this.f2512b = context;
    }

    public static APSecuritySdk getInstance(Context context) {
        if (f2510a == null) {
            synchronized (f2511c) {
                if (f2510a == null) {
                    f2510a = new APSecuritySdk(context);
                }
            }
        }
        return f2510a;
    }

    public static String getUtdid(Context context) {
        return UtdidWrapper.getUtdid(context);
    }

    public String getApdidToken() {
        String strA = a.a(this.f2512b, "");
        if (com.alipay.security.mobile.module.a.a.a(strA)) {
            initToken(0, new HashMap(), null);
        }
        return strA;
    }

    public String getSdkName() {
        return "APPSecuritySDK-ALIPAYSDK";
    }

    public String getSdkVersion() {
        return "3.4.0.201910161639";
    }

    public synchronized TokenResult getTokenResult() {
        TokenResult tokenResult;
        tokenResult = new TokenResult();
        try {
            tokenResult.apdidToken = a.a(this.f2512b, "");
            tokenResult.clientKey = h.f(this.f2512b);
            tokenResult.apdid = a.a(this.f2512b);
            tokenResult.umidToken = UmidSdkWrapper.getSecurityToken(this.f2512b);
            if (com.alipay.security.mobile.module.a.a.a(tokenResult.apdid) || com.alipay.security.mobile.module.a.a.a(tokenResult.apdidToken) || com.alipay.security.mobile.module.a.a.a(tokenResult.clientKey)) {
                initToken(0, new HashMap(), null);
            }
        } catch (Throwable unused) {
        }
        return tokenResult;
    }

    public void initToken(int i, Map<String, String> map, final InitResultListener initResultListener) {
        com.alipay.apmobilesecuritysdk.b.a.a().a(i);
        String strB = h.b(this.f2512b);
        String strC = com.alipay.apmobilesecuritysdk.b.a.a().c();
        if (com.alipay.security.mobile.module.a.a.b(strB) && !com.alipay.security.mobile.module.a.a.a(strB, strC)) {
            com.alipay.apmobilesecuritysdk.e.a.a(this.f2512b);
            d.a(this.f2512b);
            g.a(this.f2512b);
            i.h();
        }
        if (!com.alipay.security.mobile.module.a.a.a(strB, strC)) {
            h.c(this.f2512b, strC);
        }
        String strA = com.alipay.security.mobile.module.a.a.a(map, b.g, "");
        String strA2 = com.alipay.security.mobile.module.a.a.a(map, "tid", "");
        String strA3 = com.alipay.security.mobile.module.a.a.a(map, "userId", "");
        if (com.alipay.security.mobile.module.a.a.a(strA)) {
            strA = UtdidWrapper.getUtdid(this.f2512b);
        }
        final HashMap map2 = new HashMap();
        map2.put(b.g, strA);
        map2.put("tid", strA2);
        map2.put("userId", strA3);
        map2.put(DispatchConstants.APP_NAME, "");
        map2.put("appKeyClient", "");
        map2.put("appchannel", "");
        map2.put("rpcVersion", "8");
        com.alipay.apmobilesecuritysdk.f.b.a().a(new Runnable() { // from class: com.alipay.apmobilesecuritysdk.face.APSecuritySdk.1
            @Override // java.lang.Runnable
            public void run() {
                new a(APSecuritySdk.this.f2512b).a(map2);
                if (initResultListener != null) {
                    initResultListener.onResult(APSecuritySdk.this.getTokenResult());
                }
            }
        });
    }
}

package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.data.a;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.k;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.platform.onekey.OnekeyLoginConfig;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class OpenAuthTask {
    public static final int Duplex = 5000;
    public static final int NOT_INSTALLED = 4001;
    public static final int OK = 9000;
    public static final int SYS_ERR = 4000;

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, Callback> f2534a = new ConcurrentHashMap();

    /* renamed from: b, reason: collision with root package name */
    private static long f2535b = -1;

    /* renamed from: c, reason: collision with root package name */
    private static final int f2536c = 122;
    private final Activity e;
    private Callback f;
    private volatile boolean d = false;
    private final Handler g = new Handler(Looper.getMainLooper());

    public interface Callback {
        void onResult(int i, String str, Bundle bundle);
    }

    public enum BizType {
        Invoice("20000920"),
        AccountAuth("20000067"),
        Deduct("60000157");

        private String appId;

        BizType(String str) {
            this.appId = str;
        }
    }

    public OpenAuthTask(Activity activity) {
        this.e = activity;
        com.alipay.sdk.sys.b.a().a(activity);
    }

    public void execute(String str, BizType bizType, Map<String, String> map, Callback callback, boolean z) {
        com.alipay.sdk.sys.a aVar = new com.alipay.sdk.sys.a(this.e, String.valueOf(map), "oa-" + bizType);
        this.f = callback;
        if (a(aVar, str, bizType, map, z)) {
            com.alipay.sdk.app.statistic.a.b(this.e, aVar, "", aVar.q);
        }
    }

    private boolean a(com.alipay.sdk.sys.a aVar, String str, BizType bizType, Map<String, String> map, boolean z) throws JSONException {
        if (this.d) {
            this.g.post(new a(this, SYS_ERR, "该 OpenAuthTask 已在执行", null, null));
            return true;
        }
        this.d = true;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - f2535b <= OnekeyLoginConfig.OVER_TIME) {
            this.g.post(new a(this, 5000, "3s 内重复支付", null, null));
            return true;
        }
        f2535b = jElapsedRealtime;
        com.alipay.sdk.app.a.a("");
        String strA = k.a(32);
        HashMap map2 = new HashMap(map);
        map2.put("mqpPkgName", this.e.getPackageName());
        map2.put("mqpScene", "sdk");
        List<a.C0078a> listO = com.alipay.sdk.data.a.p().o();
        if (!com.alipay.sdk.data.a.p().f2585a || listO == null) {
            listO = com.alipay.sdk.app.a.f2557a;
        }
        k.a aVarA = k.a(aVar, this.e, listO);
        if (aVarA == null || aVarA.a(aVar) || aVarA.a() || aVarA.f2671a == null || aVarA.f2671a.versionCode < f2536c) {
            if (z) {
                map2.put("mqpScheme", String.valueOf(str));
                map2.put("mqpNotifyName", strA);
                map2.put("mqpScene", "landing");
                String strA2 = a(bizType, map2);
                Intent intent = new Intent(this.e, (Class<?>) H5OpenAuthActivity.class);
                intent.putExtra("url", String.format("https://render.alipay.com/p/s/i?scheme=%s", Uri.encode(strA2)));
                a.C0080a.a(aVar, intent);
                this.e.startActivity(intent);
                return false;
            }
            this.g.post(new a(this, NOT_INSTALLED, "支付宝未安装或签名错误", null, null));
            return true;
        }
        try {
            try {
                HashMap<String, String> mapA = com.alipay.sdk.sys.a.a(aVar);
                mapA.put("ts_scheme", String.valueOf(SystemClock.elapsedRealtime()));
                map2.put("mqpLoc", new JSONObject(mapA).toString());
            } catch (Throwable th) {
                com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, "OpenAuthLocEx", th);
            }
            String strA3 = a(bizType, map2);
            f2534a.put(strA, this.f);
            String strA4 = null;
            try {
                strA4 = a(jElapsedRealtime, strA, bizType, strA3);
            } catch (JSONException e) {
                com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.al, e);
            }
            if (TextUtils.isEmpty(strA4)) {
                this.g.post(new a(this, SYS_ERR, "参数错误", null, null));
                return true;
            }
            Intent intent2 = new Intent("android.intent.action.VIEW", new Uri.Builder().scheme("alipays").authority("platformapi").path("startapp").appendQueryParameter("appId", "20001129").appendQueryParameter("payload", strA4).build());
            intent2.addFlags(268435456);
            intent2.setPackage(aVarA.f2671a.packageName);
            try {
                com.alipay.sdk.app.statistic.a.b(aVar, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.S, "" + jElapsedRealtime);
                a.C0080a.a(aVar, strA);
                this.e.startActivity(intent2);
            } catch (Throwable th2) {
                com.alipay.sdk.app.statistic.a.a(aVar, com.alipay.sdk.app.statistic.b.f2572b, "StartWalletEx", th2);
            }
            return false;
        } catch (Throwable unused) {
            this.g.post(new a(this, SYS_ERR, "业务参数错误", null, null));
            return true;
        }
    }

    private String a(BizType bizType, Map<String, String> map) {
        if (bizType != null) {
            Uri.Builder builderAppendQueryParameter = new Uri.Builder().scheme("alipays").authority("platformapi").path("startapp").appendQueryParameter("appId", bizType.appId);
            if (AnonymousClass1.f2537a[bizType.ordinal()] == 1) {
                builderAppendQueryParameter.appendQueryParameter("appClearTop", "false").appendQueryParameter("startMultApp", "YES");
            }
            for (Map.Entry<String, String> entry : map.entrySet()) {
                builderAppendQueryParameter.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            return builderAppendQueryParameter.build().toString();
        }
        throw new RuntimeException("missing bizType");
    }

    /* renamed from: com.alipay.sdk.app.OpenAuthTask$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2537a = new int[BizType.values().length];

        static {
            try {
                f2537a[BizType.Deduct.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2537a[BizType.AccountAuth.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2537a[BizType.Invoice.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private String a(long j, String str, BizType bizType, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("startTime", String.valueOf(j));
        jSONObject.put("session", str);
        jSONObject.put("package", this.e.getPackageName());
        if (bizType != null) {
            jSONObject.put("appId", bizType.appId);
        }
        jSONObject.put(AccountMonitorConstants.NEW_SDK_VERSION_KEY, "h.a.3.7.9");
        jSONObject.put("mqpURL", str2);
        return Base64.encodeToString(jSONObject.toString().getBytes(Charset.forName("UTF-8")), 2);
    }

    static void a(String str, int i, String str2, Bundle bundle) {
        Callback callbackRemove = f2534a.remove(str);
        if (callbackRemove != null) {
            try {
                callbackRemove.onResult(i, str2, bundle);
            } catch (Throwable th) {
                com.alipay.sdk.util.c.a(th);
            }
        }
    }

    private final class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final int f2538a;

        /* renamed from: b, reason: collision with root package name */
        final String f2539b;

        /* renamed from: c, reason: collision with root package name */
        final Bundle f2540c;

        /* synthetic */ a(OpenAuthTask openAuthTask, int i, String str, Bundle bundle, AnonymousClass1 anonymousClass1) {
            this(i, str, bundle);
        }

        private a(int i, String str, Bundle bundle) {
            this.f2538a = i;
            this.f2539b = str;
            this.f2540c = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (OpenAuthTask.this.f != null) {
                OpenAuthTask.this.f.onResult(this.f2538a, this.f2539b, this.f2540c);
            }
        }
    }
}

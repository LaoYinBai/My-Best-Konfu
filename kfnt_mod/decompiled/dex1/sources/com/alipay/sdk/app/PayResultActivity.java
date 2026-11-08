package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class PayResultActivity extends Activity {

    /* renamed from: a, reason: collision with root package name */
    public static final String f2541a = "{\"isLogin\":\"false\"}";

    /* renamed from: b, reason: collision with root package name */
    public static final HashMap<String, Object> f2542b = new HashMap<>();

    /* renamed from: c, reason: collision with root package name */
    public static final String f2543c = "hk.alipay.wallet";
    public static final String d = "phonecashier.pay.hash";
    public static final String e = "orderSuffix";
    public static final String f = "externalPkgName";
    public static final String g = "phonecashier.pay.result";
    public static final String h = "phonecashier.pay.resultOrderHash";
    private com.alipay.sdk.sys.a i = null;

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public static volatile String f2545a;

        /* renamed from: b, reason: collision with root package name */
        public static volatile String f2546b;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            if (!TextUtils.isEmpty(intent.getStringExtra(e))) {
                a.f2545a = intent.getStringExtra(d);
                String stringExtra = intent.getStringExtra(e);
                String stringExtra2 = intent.getStringExtra(f);
                this.i = a.C0080a.a(intent);
                if (this.i == null) {
                    finish();
                }
                a(this, a.f2545a, stringExtra, stringExtra2);
                a(this, TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX);
                return;
            }
            if (this.i == null) {
                finish();
            }
            String stringExtra3 = intent.getStringExtra(g);
            int intExtra = intent.getIntExtra(h, 0);
            if (intExtra != 0 && TextUtils.equals(a.f2545a, String.valueOf(intExtra))) {
                if (!TextUtils.isEmpty(stringExtra3)) {
                    a(stringExtra3, a.f2545a);
                } else {
                    a(a.f2545a);
                }
                a.f2545a = "";
                a(this, TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX);
                return;
            }
            com.alipay.sdk.app.statistic.a.a(this.i, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.af, "Expected " + a.f2545a + ", got " + intExtra);
            a(a.f2545a);
            a(this, TTAdConstant.DEFAULT_LIVE_SHOW_TIME_MAX);
        } catch (Throwable unused) {
            finish();
        }
    }

    private static void a(Activity activity, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        Intent intent = new Intent();
        try {
            intent.setPackage(f2543c);
            intent.setData(Uri.parse("alipayhk://platformapi/startApp?appId=20000125&schemePaySession=" + URLEncoder.encode(str, "UTF-8") + "&orderSuffix=" + URLEncoder.encode(str2, "UTF-8") + "&packageName=" + URLEncoder.encode(str3, "UTF-8") + "&externalPkgName=" + URLEncoder.encode(str3, "UTF-8")));
        } catch (UnsupportedEncodingException e2) {
            com.alipay.sdk.util.c.a(e2);
        }
        if (activity != null) {
            try {
                activity.startActivity(intent);
            } catch (Throwable unused) {
                activity.finish();
            }
        }
    }

    private static void a(String str) {
        a.f2546b = b.c();
        a(f2542b, str);
    }

    private static void a(String str, String str2) {
        a.f2546b = str;
        a(f2542b, str2);
    }

    private static void a(final Activity activity, int i) {
        new Handler().postDelayed(new Runnable() { // from class: com.alipay.sdk.app.PayResultActivity.1
            @Override // java.lang.Runnable
            public void run() {
                activity.finish();
            }
        }, i);
    }

    private static boolean a(HashMap<String, Object> map, String str) {
        Object obj;
        if (map == null || str == null || (obj = map.get(str)) == null) {
            return false;
        }
        synchronized (obj) {
            obj.notifyAll();
        }
        return true;
    }
}

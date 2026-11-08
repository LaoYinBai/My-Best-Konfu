package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.i;
import com.baidu.mobads.sdk.internal.bh;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AlipayResultActivity extends Activity {

    /* renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<String, a> f2525a = new ConcurrentHashMap<>();

    public interface a {
        void a(int i, String str, String str2);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        com.alipay.sdk.sys.a aVarA;
        super.onCreate(bundle);
        try {
            Intent intent = getIntent();
            try {
                String stringExtra = intent.getStringExtra("session");
                Bundle bundleExtra = intent.getBundleExtra("result");
                String stringExtra2 = intent.getStringExtra("scene");
                aVarA = a.C0080a.a(stringExtra);
                try {
                    if (aVarA == null) {
                        finish();
                        return;
                    }
                    com.alipay.sdk.app.statistic.a.b(aVarA, com.alipay.sdk.app.statistic.b.f2572b, "BSPSession", stringExtra + "|" + SystemClock.elapsedRealtime());
                    if (TextUtils.equals("mqpSchemePay", stringExtra2)) {
                        a(stringExtra, bundleExtra);
                        return;
                    }
                    if ((TextUtils.isEmpty(stringExtra) || bundleExtra == null) && intent.getData() != null) {
                        try {
                            JSONObject jSONObject = new JSONObject(new String(Base64.decode(intent.getData().getQuery(), 2), "UTF-8"));
                            JSONObject jSONObject2 = jSONObject.getJSONObject("result");
                            String string = jSONObject.getString("session");
                            try {
                                com.alipay.sdk.app.statistic.a.b(aVarA, com.alipay.sdk.app.statistic.b.f2572b, "BSPUriSession", string);
                                Bundle bundle2 = new Bundle();
                                try {
                                    Iterator<String> itKeys = jSONObject2.keys();
                                    while (itKeys.hasNext()) {
                                        String next = itKeys.next();
                                        bundle2.putString(next, jSONObject2.getString(next));
                                    }
                                    bundleExtra = bundle2;
                                    stringExtra = string;
                                } catch (Throwable th) {
                                    th = th;
                                    bundleExtra = bundle2;
                                    stringExtra = string;
                                    com.alipay.sdk.app.statistic.a.a(aVarA, com.alipay.sdk.app.statistic.b.f2572b, "BSPResEx", th);
                                    com.alipay.sdk.app.statistic.a.a(aVarA, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.an, th);
                                    if (!TextUtils.isEmpty(stringExtra)) {
                                    }
                                    com.alipay.sdk.app.statistic.a.b(this, aVarA, "", aVarA.q);
                                    finish();
                                    return;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                    if (!TextUtils.isEmpty(stringExtra) || bundleExtra == null) {
                        com.alipay.sdk.app.statistic.a.b(this, aVarA, "", aVarA.q);
                        finish();
                        return;
                    }
                    try {
                        com.alipay.sdk.app.statistic.a.b(aVarA, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.P, "" + SystemClock.elapsedRealtime());
                        com.alipay.sdk.app.statistic.a.b(aVarA, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.Q, bundleExtra.getInt("endCode", -1) + "|" + bundleExtra.getString(i.f2664b, "-"));
                        OpenAuthTask.a(stringExtra, OpenAuthTask.OK, bh.k, bundleExtra);
                        com.alipay.sdk.app.statistic.a.b(this, aVarA, "", aVarA.q);
                        finish();
                    } catch (Throwable th4) {
                        com.alipay.sdk.app.statistic.a.b(this, aVarA, "", aVarA.q);
                        finish();
                        throw th4;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    com.alipay.sdk.app.statistic.a.a(aVarA, com.alipay.sdk.app.statistic.b.f2572b, "BSPSerError", th);
                    com.alipay.sdk.app.statistic.a.a(aVarA, com.alipay.sdk.app.statistic.b.f2572b, com.alipay.sdk.app.statistic.b.am, th);
                    finish();
                }
            } catch (Throwable th6) {
                th = th6;
                aVarA = null;
            }
        } catch (Throwable unused) {
            finish();
        }
    }

    private void a(String str, Bundle bundle) {
        a aVarRemove = f2525a.remove(str);
        if (aVarRemove == null) {
            return;
        }
        try {
            aVarRemove.a(bundle.getInt("endCode"), bundle.getString(i.f2664b), bundle.getString("result"));
        } finally {
            finish();
        }
    }
}

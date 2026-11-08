package com.alibaba.sdk.android.httpdns.d;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.sdk.android.utils.AlicloudTracker;
import com.alibaba.sdk.android.utils.AlicloudTrackerManager;
import com.alibaba.sdk.android.utils.crashdefend.SDKMessageCallback;
import com.bytedance.b.a.a.c.g;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: b, reason: collision with root package name */
    private static volatile b f2336b;

    /* renamed from: a, reason: collision with root package name */
    private AlicloudTracker f2337a;

    /* renamed from: a, reason: collision with other field name */
    private AlicloudTrackerManager f18a;
    private boolean l = true;

    /* renamed from: b, reason: collision with other field name */
    private a f19b = new a();

    private b(Context context) {
        this.f2337a = null;
        this.f18a = null;
        if (context == null || !(context.getApplicationContext() instanceof Application)) {
            return;
        }
        this.f18a = AlicloudTrackerManager.getInstance((Application) context.getApplicationContext());
        if (this.f18a != null) {
            this.f2337a = this.f18a.getTracker("httpdns", "1.2.5");
        }
    }

    public static b a() {
        return f2336b;
    }

    public static b a(Context context) {
        if (f2336b == null) {
            synchronized (b.class) {
                if (f2336b == null) {
                    f2336b = new b(context);
                }
            }
        }
        return f2336b;
    }

    public void a(String str, int i, int i2, int i3) {
        String str2;
        String str3;
        if (!this.l) {
            str2 = "HttpDns:ReportManager";
            str3 = "report is disabled";
        } else {
            if (this.f2337a == null) {
                Log.e("HttpDns:ReportManager", "report http dns succes failed due to tacker is null");
                return;
            }
            if (!TextUtils.isEmpty(str) && ((i == 0 || i == 1) && ((i2 == 0 || i2 == 1) && (i3 == 0 || i3 == 1)))) {
                final HashMap map = new HashMap();
                map.put(com.alipay.sdk.cons.c.f, str);
                map.put("success", String.valueOf(i));
                map.put("ipv6", String.valueOf(i2));
                map.put("cacheOpen", String.valueOf(i3));
                this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.5
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            b.this.f2337a.sendCustomHit("perf_getip", map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            str2 = "HttpDns:ReportManager";
            str3 = "report http dns success failed due to invalid params";
        }
        Log.e(str2, str3);
    }

    public void a(String str, long j, int i) {
        String str2;
        String str3;
        if (!this.l) {
            str2 = "HttpDns:ReportManager";
            str3 = "report is disabled";
        } else {
            if (this.f2337a == null) {
                Log.e("HttpDns:ReportManager", "report sc request time cost failed due to tacker is null");
                return;
            }
            if (!TextUtils.isEmpty(str) && j > 0 && (i == 0 || i == 1)) {
                if (j > g.e) {
                    j = 30000;
                }
                final HashMap map = new HashMap();
                map.put("scAddr", str);
                map.put("cost", String.valueOf(j));
                map.put("ipv6", String.valueOf(i));
                this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            b.this.f2337a.sendCustomHit("perf_sc", map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            str2 = "HttpDns:ReportManager";
            str3 = "report sc request time cost failed due to invalid params";
        }
        Log.e(str2, str3);
    }

    public void a(String str, String str2, String str3) {
        String str4;
        String str5;
        if (!this.l) {
            str4 = "HttpDns:ReportManager";
            str5 = "report is disabled";
        } else {
            if (this.f2337a == null) {
                Log.e("HttpDns:ReportManager", "report sniffer failed due to tracker is null");
                return;
            }
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                final HashMap map = new HashMap();
                map.put(com.alipay.sdk.cons.c.f, str);
                map.put("scAddr", str2);
                map.put("srvAddr", str3);
                this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.8
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            b.this.f2337a.sendCustomHit("biz_sniffer", map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            str4 = "HttpDns:ReportManager";
            str5 = "report sniffer failed due to missing params";
        }
        Log.e(str4, str5);
    }

    public void a(String str, String str2, String str3, int i) {
        String str4;
        String str5;
        if (!this.l) {
            str4 = "HttpDns:ReportManager";
            str5 = "report is disabled";
        } else {
            if (this.f2337a == null) {
                Log.e("HttpDns:ReportManager", "report error sc failed due to tacker is null");
                return;
            }
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && (i == 0 || i == 1)) {
                final HashMap map = new HashMap();
                map.put("scAddr", str);
                map.put("errCode", str2);
                map.put("errMsg", str3);
                map.put("ipv6", String.valueOf(i));
                this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.13
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            b.this.f2337a.sendCustomHit("err_sc", map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            str4 = "HttpDns:ReportManager";
            str5 = "report error sc failed, due to invalid params";
        }
        Log.e(str4, str5);
    }

    public void a(String str, String str2, String str3, int i, int i2) {
        try {
            if (!this.l) {
                Log.e("HttpDns:ReportManager", "report is disabled");
                return;
            }
            if (this.f2337a == null) {
                Log.e("HttpDns:ReportManager", "report error http dns request failed due to tacker is null");
                return;
            }
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && ((i == 0 || i == 1) && (i2 == 0 || i2 == 1))) {
                final HashMap map = new HashMap();
                map.put("srvAddr", str);
                map.put("errCode", str2);
                map.put("errMsg", str3);
                map.put("ipv6", String.valueOf(i));
                map.put("ipv6_srv", String.valueOf(i2));
                this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.14
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            b.this.f2337a.sendCustomHit("err_srv", map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            Log.e("HttpDns:ReportManager", "report error http dns request failed, due to invalid params");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str, String str2, String str3, long j, long j2, int i) {
        String str4;
        String str5;
        if (!this.l) {
            str4 = "HttpDns:ReportManager";
            str5 = "report is disabled";
        } else {
            if (this.f2337a == null) {
                Log.e("HttpDns:ReportManager", "report ip selection failed due to tacker is null");
                return;
            }
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3) && i > 0) {
                if (j > 5000) {
                    j = 5000;
                }
                if (j2 > 5000) {
                    j2 = 5000;
                }
                final HashMap map = new HashMap();
                map.put(com.alipay.sdk.cons.c.f, str);
                map.put("defaultIp", str2);
                map.put("selectedIp", str3);
                map.put("defaultIpCost", String.valueOf(j));
                map.put("selectedIpCost", String.valueOf(j2));
                map.put("ipCount", String.valueOf(i));
                this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.7
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            b.this.f2337a.sendCustomHit("perf_ipselection", map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            str4 = "HttpDns:ReportManager";
            str5 = "report ip selection failed due to invalid params";
        }
        Log.e(str4, str5);
    }

    public boolean a(SDKMessageCallback sDKMessageCallback) {
        try {
            if (this.f18a != null) {
                return this.f18a.registerCrashDefend("httpdns", "1.2.5", 2, 7, sDKMessageCallback);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void b(String str, int i, int i2, int i3) {
        String str2;
        String str3;
        if (!this.l) {
            str2 = "HttpDns:ReportManager";
            str3 = "report is disabled";
        } else {
            if (this.f2337a == null) {
                Log.e("HttpDns:ReportManager", "report http dns succes failed due to tacker is null");
                return;
            }
            if (!TextUtils.isEmpty(str) && ((i == 0 || i == 1) && ((i2 == 0 || i2 == 1) && (i3 == 0 || i3 == 1)))) {
                final HashMap map = new HashMap();
                map.put(com.alipay.sdk.cons.c.f, str);
                map.put("success", String.valueOf(i));
                map.put("ipv6", String.valueOf(i2));
                map.put("cacheOpen", String.valueOf(i3));
                this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.6
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            b.this.f2337a.sendCustomHit("perf_user_getip", map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            str2 = "HttpDns:ReportManager";
            str3 = "report http dns success failed due to invalid params";
        }
        Log.e(str2, str3);
    }

    public void b(String str, long j, int i) {
        String str2;
        String str3;
        if (!this.l) {
            str2 = "HttpDns:ReportManager";
            str3 = "report is disabled";
        } else {
            if (this.f2337a == null) {
                Log.e("HttpDns:ReportManager", "report http dns request time cost failed due to tacker is null");
                return;
            }
            if (!TextUtils.isEmpty(str) && j > 0 && (i == 0 || i == 1)) {
                if (j > g.e) {
                    j = 30000;
                }
                final HashMap map = new HashMap();
                map.put("srvAddr", str);
                map.put("cost", String.valueOf(j));
                map.put("ipv6", String.valueOf(i));
                this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.4
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            b.this.f2337a.sendCustomHit("perf_srv", map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            str2 = "HttpDns:ReportManager";
            str3 = "report http dns request time cost failed due to invalid param";
        }
        Log.e(str2, str3);
    }

    public void b(String str, String str2, String str3) {
        String str4;
        String str5;
        if (!this.l) {
            str4 = "HttpDns:ReportManager";
            str5 = "report is disabled";
        } else {
            if (this.f2337a == null) {
                Log.e("HttpDns:ReportManager", "report local disable failed due to tracker is null");
                return;
            }
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                final HashMap map = new HashMap();
                map.put(com.alipay.sdk.cons.c.f, str);
                map.put("scAddr", str2);
                map.put("srvAddr", str3);
                this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.9
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            b.this.f2337a.sendCustomHit("biz_local_disable", map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            str4 = "HttpDns:ReportManager";
            str5 = "report local disable failed due to missing params";
        }
        Log.e(str4, str5);
    }

    public void c(int i) {
        String str;
        String str2;
        if (!this.l) {
            str = "HttpDns:ReportManager";
            str2 = "report is disabled";
        } else {
            if (this.f2337a == null) {
                Log.e("HttpDns:ReportManager", "report cache failed due to tracker is null");
                return;
            }
            if (i == 0 || i == 1) {
                final HashMap map = new HashMap();
                map.put("enable", String.valueOf(i));
                this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.10
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            b.this.f2337a.sendCustomHit("biz_cache", map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            str = "HttpDns:ReportManager";
            str2 = "report cache failed, due to invalid param enable, enable can only be 0 or 1";
        }
        Log.e(str, str2);
    }

    public void d(int i) {
        String str;
        String str2;
        if (!this.l) {
            str = "HttpDns:ReportManager";
            str2 = "report is disabled";
        } else {
            if (this.f2337a == null) {
                Log.e("HttpDns:ReportManager", "report set expired ip enabled failed due to tracker is null");
                return;
            }
            if (i == 0 || i == 1) {
                final HashMap map = new HashMap();
                map.put("enable", String.valueOf(i));
                this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.11
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            b.this.f2337a.sendCustomHit("biz_expired_ip", map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            str = "HttpDns:ReportManager";
            str2 = "report set expired ip enabled failed, due to invalid param enable, enable can only be 0 or 1";
        }
        Log.e(str, str2);
    }

    public void e(int i) {
        try {
            if (!this.l) {
                Log.e("HttpDns:ReportManager", "report is disabled");
                return;
            }
            if (this.f2337a == null) {
                Log.e("HttpDns:ReportManager", "report ipv6 failed due to tracker is null");
                return;
            }
            if (i != 0 && i != 1) {
                Log.e("HttpDns:ReportManager", "report ipv6 failed, due to invalid param enable, enable can only be 0 or 1");
                return;
            }
            final HashMap map = new HashMap();
            map.put("enable", String.valueOf(i));
            this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.12
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        b.this.f2337a.sendCustomHit("biz_ipv6_enable", map);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void e(boolean z) {
        synchronized (b.class) {
            this.l = z;
        }
    }

    public void i() {
        if (!this.l) {
            Log.e("HttpDns:ReportManager", "report is disabled");
        } else if (this.f2337a != null) {
            this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        b.this.f2337a.sendCustomHit("biz_active", null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            Log.e("HttpDns:ReportManager", "report sdk start failed due to tracker is null");
        }
    }

    public void k(String str) {
        String str2;
        String str3;
        if (!this.l) {
            str2 = "HttpDns:ReportManager";
            str3 = "report is disabled";
        } else if (this.f2337a == null) {
            Log.e("HttpDns:ReportManager", "report uncaught exception failed due to tacker is null");
            return;
        } else {
            if (!TextUtils.isEmpty(str)) {
                final HashMap map = new HashMap();
                map.put("exception", str);
                this.f19b.b().submit(new Runnable() { // from class: com.alibaba.sdk.android.httpdns.d.b.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            b.this.f2337a.sendCustomHit("err_uncaught_exception", map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            str2 = "HttpDns:ReportManager";
            str3 = "report uncaught exception failed due to exception msg is null";
        }
        Log.e(str2, str3);
    }

    public void setAccountId(String str) {
        if (this.f2337a != null) {
            this.f2337a.setGlobalProperty("accountId", str);
        } else {
            Log.e("HttpDns:ReportManager", "tracker null, set global properties failed");
        }
    }
}

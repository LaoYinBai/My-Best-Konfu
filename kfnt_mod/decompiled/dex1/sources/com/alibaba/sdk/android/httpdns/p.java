package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.common.utility.date.DateDef;
import java.net.SocketTimeoutException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class p {

    /* renamed from: a, reason: collision with root package name */
    private static volatile p f2387a = null;
    private static long e = 0;
    private static String f = "https://";

    /* renamed from: f, reason: collision with other field name */
    private static boolean f32f = false;
    private static String g;

    /* renamed from: e, reason: collision with other field name */
    private int f34e = 0;

    /* renamed from: a, reason: collision with other field name */
    private SharedPreferences f33a = null;

    /* renamed from: f, reason: collision with other field name */
    private long f35f = 0;

    private p() {
    }

    public static p a() {
        if (f2387a == null) {
            synchronized (p.class) {
                if (f2387a == null) {
                    f2387a = new p();
                }
            }
        }
        return f2387a;
    }

    private void a(String str, long j) {
        try {
            com.alibaba.sdk.android.httpdns.d.b bVarA = com.alibaba.sdk.android.httpdns.d.b.a();
            if (bVarA != null) {
                bVarA.a(str, j, com.alibaba.sdk.android.httpdns.d.c.b());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void d() {
        this.f34e = this.f34e < f.f2371c.length + (-1) ? this.f34e + 1 : 0;
    }

    private void d(Throwable th) {
        try {
            com.alibaba.sdk.android.httpdns.d.b bVarA = com.alibaba.sdk.android.httpdns.d.b.a();
            if (bVarA != null) {
                int iA = com.alibaba.sdk.android.httpdns.d.c.a(th);
                bVarA.a(f(), String.valueOf(iA), th.getMessage(), com.alibaba.sdk.android.httpdns.d.c.b());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    synchronized void a(Context context) {
        try {
            if (!f32f) {
                synchronized (p.class) {
                    if (!f32f) {
                        if (context != null) {
                            this.f33a = context.getSharedPreferences("httpdns_config_cache", 0);
                        }
                        g = this.f33a.getString("httpdns_server_ips", null);
                        if (g != null) {
                            f.a(g.split(com.alipay.sdk.util.f.f2657b));
                        }
                        e = this.f33a.getLong("schedule_center_last_request_time", 0L);
                        if (e == 0 || System.currentTimeMillis() - e >= DateDef.DAY) {
                            s.a().c(false);
                            c();
                        }
                        f32f = true;
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    synchronized void a(q qVar, long j) {
        try {
            a(f(), j);
            this.f34e = 0;
            HttpDns.switchDnsService(qVar.isEnabled());
            if (a(qVar.c())) {
                i.d("Scheduler center update success");
                this.f35f = System.currentTimeMillis();
                t.g();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    synchronized boolean a(String[] strArr) {
        try {
            if (!f.a(strArr)) {
                return false;
            }
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str);
                sb.append(com.alipay.sdk.util.f.f2657b);
            }
            sb.deleteCharAt(sb.length() - 1);
            if (this.f33a == null) {
                return false;
            }
            SharedPreferences.Editor editorEdit = this.f33a.edit();
            editorEdit.putString("httpdns_server_ips", sb.toString());
            editorEdit.putLong("schedule_center_last_request_time", System.currentTimeMillis());
            editorEdit.commit();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    synchronized void c() {
        if (System.currentTimeMillis() - this.f35f >= 300000) {
            i.d("update server ips from schedule center.");
            this.f34e = 0;
            c.a().submit(new n(f.f2371c.length - 1));
        } else {
            i.d("update server ips from schedule center too often, give up. ");
            t.h();
        }
    }

    synchronized void c(Throwable th) {
        try {
            d(th);
            if (th instanceof SocketTimeoutException) {
                d();
                if (this.f34e == 0) {
                    this.f35f = System.currentTimeMillis();
                    i.f("Scheduler center update failed");
                    t.h();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    synchronized String f() {
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
        return f + f.f2371c[this.f34e] + "/sc/httpdns_config?account_id=" + f.f20a + "&platform=android&sdk_version=1.2.5";
    }
}

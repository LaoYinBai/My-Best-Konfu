package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.common.utility.date.DateDef;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import java.net.SocketTimeoutException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class t {

    /* renamed from: a, reason: collision with root package name */
    private static SharedPreferences f2401a = null;

    /* renamed from: a, reason: collision with other field name */
    private static a f40a = a.ENABLE;
    private static long e = 0;
    private static volatile int f = 0;

    /* renamed from: f, reason: collision with other field name */
    private static boolean f41f = false;
    private static volatile int g = 0;
    private static boolean h = false;

    enum a {
        ENABLE,
        PRE_DISABLE,
        DISABLE
    }

    static synchronized String a(o oVar) {
        try {
            if (oVar != o.QUERY_HOST && oVar != o.SNIFF_HOST) {
                return (oVar == o.QUERY_SCHEDULE_CENTER || oVar == o.SNIFF_SCHEDULE_CENTER) ? null : null;
            }
            if (f40a != a.ENABLE && f40a != a.PRE_DISABLE) {
                if (oVar == o.QUERY_HOST) {
                    return null;
                }
                return f.f22b[f];
            }
            return f.f22b[f];
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    static synchronized void a(Context context) {
        if (!f41f) {
            synchronized (t.class) {
                if (!f41f) {
                    if (context != null) {
                        f2401a = context.getSharedPreferences("httpdns_config_cache", 0);
                    }
                    h = f2401a.getBoolean("status", false);
                    f = f2401a.getInt("activiate_ip_index", 0);
                    g = f;
                    e = f2401a.getLong("disable_modified_time", 0L);
                    if (System.currentTimeMillis() - e >= DateDef.DAY) {
                        d(false);
                    }
                    f40a = h ? a.DISABLE : a.ENABLE;
                    f41f = true;
                }
            }
        }
    }

    static synchronized void a(String str, String str2, long j) {
        try {
            b(str, str2, j);
            reportHttpDnsSuccess(str, 1);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (f40a != a.ENABLE && str2 != null && str2.equals(f.f22b[f])) {
            StringBuilder sb = new StringBuilder();
            sb.append(f40a == a.DISABLE ? "Disable " : "Pre_disable ");
            sb.append("mode finished. Enter enable mode.");
            i.f(sb.toString());
            f40a = a.ENABLE;
            d(false);
            s.a().e();
            g = f;
        }
    }

    static synchronized void a(String str, String str2, Throwable th) {
        try {
            a(str2, th);
            if (a(th) && str2 != null && str2.equals(f.f22b[f])) {
                f();
                if (g == f) {
                    s.a().c(false);
                    p.a().c();
                }
                if (f40a == a.ENABLE) {
                    f40a = a.PRE_DISABLE;
                    i.f("enter pre_disable mode");
                } else if (f40a == a.PRE_DISABLE) {
                    f40a = a.DISABLE;
                    i.f("enter disable mode");
                    d(true);
                    h(str);
                    s.a().g(str);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void a(String str, Throwable th) {
        try {
            com.alibaba.sdk.android.httpdns.d.b bVarA = com.alibaba.sdk.android.httpdns.d.b.a();
            if (bVarA != null) {
                int iA = com.alibaba.sdk.android.httpdns.d.c.a(th);
                bVarA.a(str, String.valueOf(iA), com.alibaba.sdk.android.httpdns.d.c.m22a(th), com.alibaba.sdk.android.httpdns.d.c.b(), com.alibaba.sdk.android.httpdns.net64.a.a().h() ? 1 : 0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static boolean a(Throwable th) {
        if (th instanceof SocketTimeoutException) {
            return true;
        }
        if (th instanceof h) {
            h hVar = (h) th;
            if (hVar.getErrorCode() == 403 && hVar.getMessage().equals("ServiceLevelDeny")) {
                return true;
            }
        }
        return false;
    }

    static void b(int i) {
        if (f2401a == null || i < 0 || i >= f.f22b.length) {
            return;
        }
        f = i;
        SharedPreferences.Editor editorEdit = f2401a.edit();
        editorEdit.putInt("activiate_ip_index", i);
        editorEdit.putLong("activiated_ip_index_modified_time", System.currentTimeMillis());
        editorEdit.commit();
    }

    private static void b(String str, String str2, long j) {
        try {
            com.alibaba.sdk.android.httpdns.d.b bVarA = com.alibaba.sdk.android.httpdns.d.b.a();
            if (bVarA != null) {
                bVarA.b(str2, j, com.alibaba.sdk.android.httpdns.d.c.b());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    static synchronized void d(boolean z) {
        if (h != z) {
            h = z;
            if (f2401a != null) {
                SharedPreferences.Editor editorEdit = f2401a.edit();
                editorEdit.putBoolean("status", h);
                editorEdit.putLong("disable_modified_time", System.currentTimeMillis());
                editorEdit.commit();
            }
        }
    }

    static synchronized boolean e() {
        return h;
    }

    private static void f() {
        f = f == f.f22b.length + (-1) ? 0 : f + 1;
        b(f);
    }

    static void g() {
        b(0);
        g = f;
        s.a().c(true);
    }

    static void h() {
        s.a().c(true);
    }

    private static void h(String str) {
        try {
            com.alibaba.sdk.android.httpdns.d.b bVarA = com.alibaba.sdk.android.httpdns.d.b.a();
            if (bVarA != null) {
                String strF = p.a().f();
                int length = f;
                if (length == 0) {
                    length = f.f22b.length;
                }
                int i = length - 1;
                int length2 = i == 0 ? f.f22b.length - 1 : i - 1;
                if (i < 0 || i >= f.f22b.length || length2 < 0 || length2 >= f.f22b.length) {
                    return;
                }
                String str2 = f.f22b[i];
                bVarA.b(str, strF, f.f22b[length2] + IWeiboService.Scope.EMPTY_SCOPE + str2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void reportHttpDnsSuccess(String str, int i) {
        try {
            com.alibaba.sdk.android.httpdns.d.b bVarA = com.alibaba.sdk.android.httpdns.d.b.a();
            if (bVarA != null) {
                bVarA.a(str, i, com.alibaba.sdk.android.httpdns.d.c.b(), com.alibaba.sdk.android.httpdns.b.b.m7a() ? 1 : 0);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}

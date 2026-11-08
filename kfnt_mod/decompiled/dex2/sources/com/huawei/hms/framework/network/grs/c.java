package com.huawei.hms.framework.network.grs;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.b.h;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static final String a = "c";
    private static ExecutorService b = ExecutorsUtils.newCachedThreadPool("GRS_GrsClient-Init");
    private GrsBaseInfo c;
    private boolean d;
    private final Object e;
    private Context f;
    private h g;
    private com.huawei.hms.framework.network.grs.a.a h;
    private com.huawei.hms.framework.network.grs.a.c i;
    private a j;
    private Future<Boolean> k;

    c(Context context, GrsBaseInfo grsBaseInfo) {
        this.d = false;
        this.e = new Object();
        this.f = context.getApplicationContext();
        this.g = new h();
        this.i = new com.huawei.hms.framework.network.grs.a.c(this.f);
        this.h = new com.huawei.hms.framework.network.grs.a.a(this.i, this.g);
        a(grsBaseInfo);
        this.j = new a(this.c, this.h, this.g);
        if (this.d) {
            return;
        }
        synchronized (this.e) {
            if (!this.d) {
                final GrsBaseInfo grsBaseInfo2 = this.c;
                final Context context2 = this.f;
                this.k = b.submit(new Callable<Boolean>() { // from class: com.huawei.hms.framework.network.grs.c.1
                    @Override // java.util.concurrent.Callable
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public Boolean call() throws NumberFormatException {
                        new com.huawei.hms.framework.network.grs.local.b(context2).a(grsBaseInfo2);
                        c.this.a(c.this.i.c());
                        c.this.h.a(grsBaseInfo2, context2);
                        return Boolean.valueOf(c.this.d = true);
                    }
                });
            }
        }
    }

    c(GrsBaseInfo grsBaseInfo) {
        this.d = false;
        this.e = new Object();
        a(grsBaseInfo);
    }

    private void a(GrsBaseInfo grsBaseInfo) {
        try {
            this.c = grsBaseInfo.m1clone();
        } catch (CloneNotSupportedException e) {
            Logger.w(a, "GrsClient catch CloneNotSupportedException", e);
            this.c = grsBaseInfo.copy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Map<String, ?> map) throws NumberFormatException {
        if (map == null || map.isEmpty()) {
            Logger.v(a, "sp's content is empty.");
            return;
        }
        for (String str : map.keySet()) {
            if (str.endsWith("time")) {
                String strA = this.i.a(str, "");
                long j = 0;
                if (!TextUtils.isEmpty(strA) && strA.matches("\\d+")) {
                    try {
                        j = Long.parseLong(strA);
                    } catch (NumberFormatException e) {
                        Logger.w(a, "convert expire time from String to Long catch NumberFormatException.", e);
                    }
                }
                if (!a(j)) {
                    Logger.i(a, "init interface auto clear some invalid sp's data.");
                    this.i.a(str.substring(0, str.length() - "time".length()));
                    this.i.a(str);
                }
            }
        }
    }

    private boolean a(long j) {
        return System.currentTimeMillis() - j <= 604800000;
    }

    private boolean c() {
        try {
            if (this.k != null) {
                return this.k.get(10L, TimeUnit.SECONDS).booleanValue();
            }
            return false;
        } catch (InterruptedException e) {
            Logger.w(a, "init compute task interrupted.", e);
            return false;
        } catch (CancellationException unused) {
            Logger.i(a, "init compute task canceled.");
            return false;
        } catch (ExecutionException e2) {
            Logger.w(a, "init compute task failed.", e2);
            return false;
        } catch (TimeoutException unused2) {
            Logger.w(a, "init compute task timed out");
            return false;
        } catch (Exception e3) {
            Logger.w(a, "init compute task occur unknown Exception", e3);
            return false;
        }
    }

    String a(String str, String str2) {
        if (this.c == null || str == null || str2 == null) {
            Logger.w(a, "invalid para!");
            return null;
        }
        if (c()) {
            return this.j.a(str, str2, this.f);
        }
        return null;
    }

    Map<String, String> a(String str) {
        if (this.c != null && str != null) {
            return c() ? this.j.a(str, this.f) : new HashMap();
        }
        Logger.w(a, "invalid para!");
        return new HashMap();
    }

    void a(String str, IQueryUrlsCallBack iQueryUrlsCallBack) {
        if (iQueryUrlsCallBack == null) {
            Logger.w(a, "IQueryUrlsCallBack is must not null for process continue.");
            return;
        }
        if (this.c == null || str == null) {
            iQueryUrlsCallBack.onCallBackFail(-6);
        } else if (c()) {
            this.j.a(str, iQueryUrlsCallBack, this.f);
        }
    }

    void a(String str, String str2, IQueryUrlCallBack iQueryUrlCallBack) {
        if (iQueryUrlCallBack == null) {
            Logger.w(a, "IQueryUrlCallBack is must not null for process continue.");
            return;
        }
        if (this.c == null || str == null || str2 == null) {
            iQueryUrlCallBack.onCallBackFail(-6);
        } else if (c()) {
            this.j.a(str, str2, iQueryUrlCallBack, this.f);
        }
    }

    boolean a() {
        if (!c() || this.c == null || this.f == null) {
            return false;
        }
        this.h.b(this.c, this.f);
        return true;
    }

    boolean a(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && (obj instanceof c)) {
            return this.c.compare(((c) obj).c);
        }
        return false;
    }

    void b() {
        if (c()) {
            String grsParasKey = this.c.getGrsParasKey(false, true, this.f);
            this.i.a(grsParasKey);
            this.i.a(grsParasKey + "time");
            this.g.a(grsParasKey);
        }
    }
}

package com.meizu.cloud.pushsdk.c.e;

import android.content.Context;
import com.meizu.cloud.pushsdk.c.f.e;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static final String a = "a";
    private String b;
    private String c;
    private String d;
    private int e;
    private final String f = "SQLITE";
    private final AtomicBoolean g = new AtomicBoolean(false);
    private long h;
    private final long i;
    private final long j;
    private final Context k;

    public a(long j, long j2, TimeUnit timeUnit, Context context) {
        this.c = null;
        this.e = 0;
        this.i = timeUnit.toMillis(j);
        this.j = timeUnit.toMillis(j2);
        this.k = context;
        Map mapF = f();
        if (mapF == null) {
            this.b = e.b();
        } else {
            try {
                String string = mapF.get("userId").toString();
                String string2 = mapF.get("sessionId").toString();
                int iIntValue = ((Integer) mapF.get("sessionIndex")).intValue();
                this.b = string;
                this.e = iIntValue;
                this.c = string2;
            } catch (Exception e) {
                com.meizu.cloud.pushsdk.c.f.c.a(a, "Exception occurred retrieving session info from file: %s", e.getMessage());
            }
        }
        d();
        g();
        com.meizu.cloud.pushsdk.c.f.c.c(a, "Tracker Session Object created.", new Object[0]);
    }

    private void d() {
        this.d = this.c;
        this.c = e.b();
        this.e++;
        com.meizu.cloud.pushsdk.c.f.c.b(a, "Session information is updated:", new Object[0]);
        com.meizu.cloud.pushsdk.c.f.c.b(a, " + Session ID: %s", this.c);
        com.meizu.cloud.pushsdk.c.f.c.b(a, " + Previous Session ID: %s", this.d);
        com.meizu.cloud.pushsdk.c.f.c.b(a, " + Session Index: %s", Integer.valueOf(this.e));
        e();
    }

    private boolean e() {
        return com.meizu.cloud.pushsdk.c.f.a.a("snowplow_session_vars", c(), this.k);
    }

    private Map f() {
        return com.meizu.cloud.pushsdk.c.f.a.a("snowplow_session_vars", this.k);
    }

    private void g() {
        this.h = System.currentTimeMillis();
    }

    public com.meizu.cloud.pushsdk.c.a.b a() {
        com.meizu.cloud.pushsdk.c.f.c.c(a, "Getting session context...", new Object[0]);
        g();
        return new com.meizu.cloud.pushsdk.c.a.b("client_session", c());
    }

    public void b() {
        com.meizu.cloud.pushsdk.c.f.c.b(a, "Checking and updating session information.", new Object[0]);
        if (e.a(this.h, System.currentTimeMillis(), this.g.get() ? this.j : this.i)) {
            return;
        }
        d();
        g();
    }

    public Map c() {
        HashMap map = new HashMap(8);
        map.put("userId", this.b);
        map.put("sessionId", this.c);
        map.put("previousSessionId", this.d);
        map.put("sessionIndex", Integer.valueOf(this.e));
        getClass();
        map.put("storageMechanism", "SQLITE");
        return map;
    }
}

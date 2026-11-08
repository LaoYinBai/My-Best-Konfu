package com.bytedance.b.a.a;

import android.content.Context;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements com.bytedance.b.a.a.d.b {

    /* renamed from: a, reason: collision with root package name */
    private static final String f3587a = "LogReportManager";
    private c e;
    private String f;
    private Context g;
    private JSONObject h;
    private long k;
    private long l;

    /* renamed from: b, reason: collision with root package name */
    private long f3588b = 0;

    /* renamed from: c, reason: collision with root package name */
    private int f3589c = 120;
    private int d = 100;
    private volatile boolean i = true;
    private volatile boolean j = false;

    @Override // com.bytedance.b.a.a.d.b
    public void a(long j) {
        if (this.l > 0 && j - this.k > this.l) {
            e();
        }
        a(false);
    }

    public b(Context context, String str) {
        this.g = context;
        this.e = c.a(context);
        this.f = str;
    }

    public void a() {
        com.bytedance.b.a.a.d.c.a().a(this);
        com.bytedance.b.a.a.c.d.a(this.f, new com.bytedance.b.a.a.c.a(this.g, this.f));
    }

    private List<com.bytedance.b.a.a.b.a> a(int i) throws NumberFormatException {
        return this.e.a(Integer.parseInt(this.f), i);
    }

    private long g() {
        return this.e.a(this.f);
    }

    public boolean a(boolean z) {
        if (!this.i || this.j) {
            return true;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jG = g();
        if (jG <= 0) {
            return true;
        }
        if (!z && jG <= this.d && (jCurrentTimeMillis - this.f3588b) / 1000 <= this.f3589c) {
            return false;
        }
        this.f3588b = jCurrentTimeMillis;
        return c();
    }

    private int c(long j) {
        return this.e.a(this.f, j);
    }

    public int b() {
        return c(2147483647L);
    }

    public boolean c() throws NumberFormatException {
        List<com.bytedance.b.a.a.b.a> listA = a(this.d);
        if (com.bytedance.b.a.a.e.d.a(listA)) {
            return true;
        }
        long j = -1;
        try {
            JSONArray jSONArray = new JSONArray();
            LinkedList linkedList = new LinkedList();
            for (com.bytedance.b.a.a.b.a aVar : listA) {
                long j2 = aVar.f3590a;
                if (j2 > j) {
                    j = j2;
                }
                String str = aVar.e;
                linkedList.add(aVar);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    jSONObject.put("log_id", j2);
                    jSONObject.put("d_s_t", System.currentTimeMillis());
                    jSONArray.put(jSONObject);
                } catch (Throwable unused) {
                }
            }
            boolean zA = a(jSONArray, false);
            if (!zA) {
                return zA;
            }
            try {
                c(j);
                return zA;
            } catch (Throwable unused2) {
                return zA;
            }
        } catch (Throwable unused3) {
            return false;
        }
    }

    private boolean a(JSONArray jSONArray, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (com.bytedance.b.a.a.e.c.a(jSONArray)) {
                return false;
            }
            jSONObject.put("data", jSONArray);
            if (this.h == null) {
                return true;
            }
            jSONObject.put("header", this.h);
            return com.bytedance.b.a.a.c.d.a(this.f, jSONObject.toString());
        } catch (Throwable unused) {
            return false;
        }
    }

    public void d() {
        b(com.bytedance.b.a.a.a.c.a(this.f));
        c(com.bytedance.b.a.a.a.c.b(this.f));
        a(com.bytedance.b.a.a.a.c.c(this.f));
        com.bytedance.b.a.a.c.b bVarB = com.bytedance.b.a.a.c.d.b(this.f);
        if (bVarB instanceof com.bytedance.b.a.a.c.a) {
            ((com.bytedance.b.a.a.c.a) bVarB).b((String) null);
        }
    }

    private void b(int i) {
        if (i <= 0) {
            return;
        }
        this.f3589c = i;
    }

    private void c(int i) {
        if (i <= 0) {
            return;
        }
        this.d = i;
    }

    private void a(JSONObject jSONObject) {
        this.h = jSONObject;
    }

    public void b(long j) {
        this.i = false;
        this.k = System.currentTimeMillis();
        this.l = j;
    }

    public void e() {
        this.i = true;
        this.l = 0L;
    }

    public void f() {
        this.j = true;
    }
}

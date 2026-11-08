package com.bytedance.b.a.a;

import android.content.Context;
import com.bytedance.b.a.a.c.g;
import java.util.LinkedList;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e implements com.bytedance.b.a.a.d.b {

    /* renamed from: a, reason: collision with root package name */
    private static final String f3636a = "MonitorManager";
    private static final int d = 200;

    /* renamed from: b, reason: collision with root package name */
    private c f3637b;
    private String h;

    /* renamed from: c, reason: collision with root package name */
    private final LinkedList<com.bytedance.b.a.a.b.a> f3638c = new LinkedList<>();
    private final int e = 5;
    private long f = 0;
    private final int g = 120000;
    private boolean i = false;

    public e(Context context, String str) {
        this.f3637b = c.a(context);
        this.h = str;
    }

    public void a() {
        com.bytedance.b.a.a.d.a.a().a(this);
    }

    public void a(String str, String str2, JSONObject jSONObject) {
        if (this.i || jSONObject == null) {
            return;
        }
        a(new com.bytedance.b.a.a.b.a(this.h, str, str2, jSONObject.toString(), System.currentTimeMillis()));
    }

    @Override // com.bytedance.b.a.a.d.b
    public void a(long j) {
        if (this.i) {
            return;
        }
        a(j, false);
    }

    protected void a(com.bytedance.b.a.a.b.a aVar) {
        if (this.f3638c.size() >= 200) {
            a(System.currentTimeMillis(), true);
        }
        this.f3638c.add(aVar);
    }

    public boolean a(long j, boolean z) {
        LinkedList linkedList;
        int size = this.f3638c.size();
        if (size <= 0) {
            return false;
        }
        if (!z && size < 5 && j - this.f <= g.g) {
            return false;
        }
        this.f = j;
        synchronized (this.f3638c) {
            linkedList = new LinkedList(this.f3638c);
            this.f3638c.clear();
        }
        if (com.bytedance.b.a.a.e.d.a(linkedList)) {
            return true;
        }
        try {
            this.f3637b.a(this.h, linkedList);
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    public void b() {
        synchronized (this.f3638c) {
            this.f3638c.clear();
        }
    }

    public void a(boolean z) {
        this.i = z;
    }
}

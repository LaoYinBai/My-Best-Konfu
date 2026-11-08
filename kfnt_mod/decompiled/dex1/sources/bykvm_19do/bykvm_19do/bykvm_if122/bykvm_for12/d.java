package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    protected String f1952a;

    /* renamed from: b, reason: collision with root package name */
    protected String f1953b;

    /* renamed from: c, reason: collision with root package name */
    protected String f1954c;
    protected String d;
    protected String e;
    protected String f;
    protected String g;
    protected String h;
    protected long i;
    protected String j;
    protected String k;
    protected String l;
    protected String m;
    protected int n;
    protected String o;
    protected String p;
    protected int q;
    protected long r;
    protected String u;
    protected int s = -1;
    protected int t = -1;
    protected Map<String, Object> v = new HashMap();
    private long w = -1;

    protected d() {
    }

    public static d b() {
        return new d();
    }

    public long a() {
        return this.w;
    }

    public d a(int i) {
        this.m = String.valueOf(i);
        return this;
    }

    public d a(long j) {
        this.r = j;
        return this;
    }

    public d a(String str) {
        this.d = str;
        return this;
    }

    public d a(String str, Object obj) {
        if (!TextUtils.isEmpty(str) && obj != null) {
            this.v.put(str, obj);
        }
        return this;
    }

    public d a(Map<String, Object> map) {
        if (map != null && map.size() > 0) {
            this.v.putAll(map);
        }
        return this;
    }

    public d b(int i) {
        this.q = i;
        return this;
    }

    public d b(long j) {
        this.w = j;
        return this;
    }

    public d b(String str) {
        this.f1954c = str;
        return this;
    }

    public d c(int i) {
        this.n = i;
        return this;
    }

    public d c(long j) {
        this.i = j;
        return this;
    }

    public d c(String str) {
        this.f = str;
        return this;
    }

    public d d(int i) {
        this.o = String.valueOf(i);
        return this;
    }

    public d d(String str) {
        this.k = str;
        return this;
    }

    public d e(int i) {
        this.s = i;
        return this;
    }

    public d e(String str) {
        this.f1952a = str;
        return this;
    }

    public d f(int i) {
        this.p = String.valueOf(i);
        return this;
    }

    public d f(String str) {
        this.u = str;
        return this;
    }

    public d g(int i) {
        this.t = i;
        return this;
    }

    public d g(String str) {
        this.f1953b = str;
        return this;
    }

    public d h(String str) {
        this.e = str;
        return this;
    }

    public d i(String str) {
        this.h = str;
        return this;
    }

    public d j(String str) {
        this.g = str;
        return this;
    }

    public d k(String str) {
        this.j = str;
        return this;
    }
}

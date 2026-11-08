package com.meizu.cloud.pushsdk.b.a;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import com.meizu.cloud.pushsdk.b.a.b;
import com.meizu.cloud.pushsdk.b.c.b;
import com.meizu.cloud.pushsdk.b.c.c;
import com.meizu.cloud.pushsdk.b.c.f;
import com.meizu.cloud.pushsdk.b.c.g;
import com.meizu.cloud.pushsdk.b.c.h;
import com.meizu.cloud.pushsdk.b.c.j;
import com.meizu.cloud.pushsdk.b.c.k;
import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b<T extends b> {
    private static final String a = "b";
    private static final g w = g.a("application/json; charset=utf-8");
    private static final g x = g.a("text/x-markdown; charset=utf-8");
    private static final Object z = new Object();
    private com.meizu.cloud.pushsdk.b.c.a A;
    private int B;
    private boolean C;
    private int D;
    private com.meizu.cloud.pushsdk.b.d.a E;
    private Bitmap.Config F;
    private int G;
    private int H;
    private ImageView.ScaleType I;
    private final Executor J;
    private String K;
    private Type L;
    private final int b;
    private final com.meizu.cloud.pushsdk.b.a.d c;
    private final int d;
    private final String e;
    private int f;
    private final Object g;
    private e h;
    private final HashMap<String, String> i;
    private HashMap<String, String> j;
    private HashMap<String, String> k;
    private HashMap<String, String> l;
    private final HashMap<String, String> m;
    private final HashMap<String, String> n;
    private HashMap<String, File> o;
    private String p;
    private String q;
    private JSONObject r;
    private JSONArray s;
    private String t;
    private byte[] u;
    private File v;
    private g y;

    public static class a<T extends a> {
        private final String b;
        private Object c;
        private final String g;
        private final String h;
        private Executor j;
        private String k;
        private com.meizu.cloud.pushsdk.b.a.d a = com.meizu.cloud.pushsdk.b.a.d.MEDIUM;
        private final HashMap<String, String> d = new HashMap<>();
        private final HashMap<String, String> e = new HashMap<>();
        private final HashMap<String, String> f = new HashMap<>();
        private int i = 0;

        public a(String str, String str2, String str3) {
            this.b = str;
            this.g = str2;
            this.h = str3;
        }

        public b a() {
            return new b(this);
        }
    }

    /* renamed from: com.meizu.cloud.pushsdk.b.a.b$b, reason: collision with other inner class name */
    public static class C0153b<T extends C0153b> {
        private final String c;
        private Object d;
        private Bitmap.Config e;
        private int f;
        private int g;
        private ImageView.ScaleType h;
        private Executor l;
        private String m;
        private com.meizu.cloud.pushsdk.b.a.d a = com.meizu.cloud.pushsdk.b.a.d.MEDIUM;
        private final HashMap<String, String> i = new HashMap<>();
        private final HashMap<String, String> j = new HashMap<>();
        private final HashMap<String, String> k = new HashMap<>();
        private final int b = 0;

        public C0153b(String str) {
            this.c = str;
        }

        public T a(HashMap<String, String> map) {
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    this.j.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public b a() {
            return new b(this);
        }
    }

    public static class c<T extends c> {
        private final String b;
        private Object c;
        private Executor j;
        private String k;
        private String l;
        private com.meizu.cloud.pushsdk.b.a.d a = com.meizu.cloud.pushsdk.b.a.d.MEDIUM;
        private final HashMap<String, String> d = new HashMap<>();
        private final HashMap<String, String> e = new HashMap<>();
        private final HashMap<String, String> f = new HashMap<>();
        private final HashMap<String, String> g = new HashMap<>();
        private final HashMap<String, File> h = new HashMap<>();
        private int i = 0;

        public c(String str) {
            this.b = str;
        }

        public T a(String str, File file) {
            this.h.put(str, file);
            return this;
        }

        public T a(HashMap<String, String> map) {
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    this.e.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public b a() {
            return new b(this);
        }
    }

    public static class d<T extends d> {
        private final String c;
        private Object d;
        private Executor o;
        private String p;
        private String q;
        private com.meizu.cloud.pushsdk.b.a.d a = com.meizu.cloud.pushsdk.b.a.d.MEDIUM;
        private JSONObject e = null;
        private JSONArray f = null;
        private String g = null;
        private byte[] h = null;
        private File i = null;
        private final HashMap<String, String> j = new HashMap<>();
        private final HashMap<String, String> k = new HashMap<>();
        private final HashMap<String, String> l = new HashMap<>();
        private final HashMap<String, String> m = new HashMap<>();
        private final HashMap<String, String> n = new HashMap<>();
        private final int b = 1;

        public d(String str) {
            this.c = str;
        }

        public T a(HashMap<String, String> map) {
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    this.k.put(entry.getKey(), entry.getValue());
                }
            }
            return this;
        }

        public b a() {
            return new b(this);
        }
    }

    public b(a aVar) {
        this.j = new HashMap<>();
        this.k = new HashMap<>();
        this.l = new HashMap<>();
        this.o = new HashMap<>();
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.y = null;
        this.D = 0;
        this.L = null;
        this.d = 1;
        this.b = 0;
        this.c = aVar.a;
        this.e = aVar.b;
        this.g = aVar.c;
        this.p = aVar.g;
        this.q = aVar.h;
        this.i = aVar.d;
        this.m = aVar.e;
        this.n = aVar.f;
        this.D = aVar.i;
        this.J = aVar.j;
        this.K = aVar.k;
    }

    public b(C0153b c0153b) {
        this.j = new HashMap<>();
        this.k = new HashMap<>();
        this.l = new HashMap<>();
        this.o = new HashMap<>();
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.y = null;
        this.D = 0;
        this.L = null;
        this.d = 0;
        this.b = c0153b.b;
        this.c = c0153b.a;
        this.e = c0153b.c;
        this.g = c0153b.d;
        this.i = c0153b.i;
        this.F = c0153b.e;
        this.H = c0153b.g;
        this.G = c0153b.f;
        this.I = c0153b.h;
        this.m = c0153b.j;
        this.n = c0153b.k;
        this.J = c0153b.l;
        this.K = c0153b.m;
    }

    public b(c cVar) {
        this.j = new HashMap<>();
        this.k = new HashMap<>();
        this.l = new HashMap<>();
        this.o = new HashMap<>();
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.y = null;
        this.D = 0;
        this.L = null;
        this.d = 2;
        this.b = 1;
        this.c = cVar.a;
        this.e = cVar.b;
        this.g = cVar.c;
        this.i = cVar.d;
        this.m = cVar.f;
        this.n = cVar.g;
        this.l = cVar.e;
        this.o = cVar.h;
        this.D = cVar.i;
        this.J = cVar.j;
        this.K = cVar.k;
        if (cVar.l != null) {
            this.y = g.a(cVar.l);
        }
    }

    public b(d dVar) {
        this.j = new HashMap<>();
        this.k = new HashMap<>();
        this.l = new HashMap<>();
        this.o = new HashMap<>();
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.y = null;
        this.D = 0;
        this.L = null;
        this.d = 0;
        this.b = dVar.b;
        this.c = dVar.a;
        this.e = dVar.c;
        this.g = dVar.d;
        this.i = dVar.j;
        this.j = dVar.k;
        this.k = dVar.l;
        this.m = dVar.m;
        this.n = dVar.n;
        this.r = dVar.e;
        this.s = dVar.f;
        this.t = dVar.g;
        this.v = dVar.i;
        this.u = dVar.h;
        this.J = dVar.o;
        this.K = dVar.p;
        if (dVar.q != null) {
            this.y = g.a(dVar.q);
        }
    }

    public com.meizu.cloud.pushsdk.b.a.c a() {
        this.h = e.STRING;
        return com.meizu.cloud.pushsdk.b.e.c.a(this);
    }

    public com.meizu.cloud.pushsdk.b.a.c a(k kVar) {
        com.meizu.cloud.pushsdk.b.a.c<Bitmap> cVarA;
        switch (this.h) {
            case JSON_ARRAY:
                try {
                    return com.meizu.cloud.pushsdk.b.a.c.a(new JSONArray(com.meizu.cloud.pushsdk.b.g.g.a(kVar.b().a()).h()));
                } catch (Exception e) {
                    return com.meizu.cloud.pushsdk.b.a.c.a(com.meizu.cloud.pushsdk.b.i.b.b(new com.meizu.cloud.pushsdk.b.b.a(e)));
                }
            case JSON_OBJECT:
                try {
                    return com.meizu.cloud.pushsdk.b.a.c.a(new JSONObject(com.meizu.cloud.pushsdk.b.g.g.a(kVar.b().a()).h()));
                } catch (Exception e2) {
                    return com.meizu.cloud.pushsdk.b.a.c.a(com.meizu.cloud.pushsdk.b.i.b.b(new com.meizu.cloud.pushsdk.b.b.a(e2)));
                }
            case STRING:
                try {
                    return com.meizu.cloud.pushsdk.b.a.c.a(com.meizu.cloud.pushsdk.b.g.g.a(kVar.b().a()).h());
                } catch (Exception e3) {
                    return com.meizu.cloud.pushsdk.b.a.c.a(com.meizu.cloud.pushsdk.b.i.b.b(new com.meizu.cloud.pushsdk.b.b.a(e3)));
                }
            case BITMAP:
                synchronized (z) {
                    try {
                        try {
                            cVarA = com.meizu.cloud.pushsdk.b.i.b.a(kVar, this.G, this.H, this.F, this.I);
                        } catch (Exception e4) {
                            return com.meizu.cloud.pushsdk.b.a.c.a(com.meizu.cloud.pushsdk.b.i.b.b(new com.meizu.cloud.pushsdk.b.b.a(e4)));
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return cVarA;
            case PREFETCH:
                return com.meizu.cloud.pushsdk.b.a.c.a("prefetch");
            default:
                return null;
        }
    }

    public com.meizu.cloud.pushsdk.b.b.a a(com.meizu.cloud.pushsdk.b.b.a aVar) {
        try {
            if (aVar.a() != null && aVar.a().b() != null && aVar.a().b().a() != null) {
                aVar.b(com.meizu.cloud.pushsdk.b.g.g.a(aVar.a().b().a()).h());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aVar;
    }

    public void a(com.meizu.cloud.pushsdk.b.c.a aVar) {
        this.A = aVar;
    }

    public void a(String str) {
        this.K = str;
    }

    public com.meizu.cloud.pushsdk.b.a.c b() {
        this.h = e.BITMAP;
        return com.meizu.cloud.pushsdk.b.e.c.a(this);
    }

    public com.meizu.cloud.pushsdk.b.a.c c() {
        return com.meizu.cloud.pushsdk.b.e.c.a(this);
    }

    public int d() {
        return this.b;
    }

    public String e() {
        String strReplace = this.e;
        for (Map.Entry<String, String> entry : this.n.entrySet()) {
            strReplace = strReplace.replace("{" + entry.getKey() + "}", String.valueOf(entry.getValue()));
        }
        f.a aVarG = f.c(strReplace).g();
        for (Map.Entry<String, String> entry2 : this.m.entrySet()) {
            aVarG.a(entry2.getKey(), entry2.getValue());
        }
        return aVarG.b().toString();
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public e f() {
        return this.h;
    }

    public int g() {
        return this.d;
    }

    public String h() {
        return this.K;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public com.meizu.cloud.pushsdk.b.d.a i() {
        return new com.meizu.cloud.pushsdk.b.d.a() { // from class: com.meizu.cloud.pushsdk.b.a.b.1
            @Override // com.meizu.cloud.pushsdk.b.d.a
            public void a(long j, long j2) {
                b.this.B = (int) ((100 * j) / j2);
                if (b.this.E == null || b.this.C) {
                    return;
                }
                b.this.E.a(j, j2);
            }
        };
    }

    public String j() {
        return this.p;
    }

    public String k() {
        return this.q;
    }

    public com.meizu.cloud.pushsdk.b.c.a l() {
        return this.A;
    }

    public j m() {
        if (this.r != null) {
            return this.y != null ? j.a(this.y, this.r.toString()) : j.a(w, this.r.toString());
        }
        if (this.s != null) {
            return this.y != null ? j.a(this.y, this.s.toString()) : j.a(w, this.s.toString());
        }
        if (this.t != null) {
            return this.y != null ? j.a(this.y, this.t) : j.a(x, this.t);
        }
        if (this.v != null) {
            return this.y != null ? j.a(this.y, this.v) : j.a(x, this.v);
        }
        if (this.u != null) {
            return this.y != null ? j.a(this.y, this.u) : j.a(x, this.u);
        }
        b.a aVar = new b.a();
        try {
            for (Map.Entry<String, String> entry : this.j.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                    aVar.a(entry.getKey(), entry.getValue());
                }
            }
            for (Map.Entry<String, String> entry2 : this.k.entrySet()) {
                if (!TextUtils.isEmpty(entry2.getKey()) && !TextUtils.isEmpty(entry2.getValue())) {
                    aVar.b(entry2.getKey(), entry2.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aVar.a();
    }

    public j n() {
        h.a aVarA = new h.a().a(h.e);
        try {
            for (Map.Entry<String, String> entry : this.l.entrySet()) {
                aVarA.a(com.meizu.cloud.pushsdk.b.c.c.a("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""), j.a((g) null, entry.getValue()));
            }
            for (Map.Entry<String, File> entry2 : this.o.entrySet()) {
                if (entry2.getValue() != null) {
                    String name = entry2.getValue().getName();
                    aVarA.a(com.meizu.cloud.pushsdk.b.c.c.a("Content-Disposition", "form-data; name=\"" + entry2.getKey() + "\"; filename=\"" + name + "\""), j.a(g.a(com.meizu.cloud.pushsdk.b.i.b.a(name)), entry2.getValue()));
                    if (this.y != null) {
                        aVarA.a(this.y);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aVarA.a();
    }

    public com.meizu.cloud.pushsdk.b.c.c o() {
        c.a aVar = new c.a();
        try {
            for (Map.Entry<String, String> entry : this.i.entrySet()) {
                aVar.a(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aVar.a();
    }

    public String toString() {
        return "ANRequest{sequenceNumber='" + this.f + ", mMethod=" + this.b + ", mPriority=" + this.c + ", mRequestType=" + this.d + ", mUrl=" + this.e + '}';
    }
}

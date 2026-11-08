package com.xiaomi.push;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.Constants;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class gg {
    private static long a;
    private static String c;

    /* renamed from: a, reason: collision with other field name */
    private gk f428a;

    /* renamed from: a, reason: collision with other field name */
    private List<gd> f429a;

    /* renamed from: a, reason: collision with other field name */
    private final Map<String, Object> f430a;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;

    /* renamed from: a, reason: collision with other field name */
    protected static final String f426a = Locale.getDefault().getLanguage().toLowerCase();
    private static String b = null;

    /* renamed from: a, reason: collision with other field name */
    public static final DateFormat f427a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    static {
        f427a.setTimeZone(TimeZone.getTimeZone("UTC"));
        c = gr.a(5) + Constants.ACCEPT_TIME_SEPARATOR_SERVER;
        a = 0L;
    }

    public gg() {
        this.d = b;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.f429a = new CopyOnWriteArrayList();
        this.f430a = new HashMap();
        this.f428a = null;
    }

    public gg(Bundle bundle) {
        this.d = b;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.f429a = new CopyOnWriteArrayList();
        this.f430a = new HashMap();
        this.f428a = null;
        this.f = bundle.getString("ext_to");
        this.g = bundle.getString("ext_from");
        this.h = bundle.getString("ext_chid");
        this.e = bundle.getString("ext_pkt_id");
        Parcelable[] parcelableArray = bundle.getParcelableArray("ext_exts");
        if (parcelableArray != null) {
            this.f429a = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                gd gdVarA = gd.a((Bundle) parcelable);
                if (gdVarA != null) {
                    this.f429a.add(gdVarA);
                }
            }
        }
        Bundle bundle2 = bundle.getBundle("ext_ERROR");
        if (bundle2 != null) {
            this.f428a = new gk(bundle2);
        }
    }

    public static synchronized String i() {
        StringBuilder sb;
        sb = new StringBuilder();
        sb.append(c);
        long j = a;
        a = 1 + j;
        sb.append(Long.toString(j));
        return sb.toString();
    }

    public static String q() {
        return f426a;
    }

    public Bundle a() {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(this.d)) {
            bundle.putString("ext_ns", this.d);
        }
        if (!TextUtils.isEmpty(this.g)) {
            bundle.putString("ext_from", this.g);
        }
        if (!TextUtils.isEmpty(this.f)) {
            bundle.putString("ext_to", this.f);
        }
        if (!TextUtils.isEmpty(this.e)) {
            bundle.putString("ext_pkt_id", this.e);
        }
        if (!TextUtils.isEmpty(this.h)) {
            bundle.putString("ext_chid", this.h);
        }
        if (this.f428a != null) {
            bundle.putBundle("ext_ERROR", this.f428a.a());
        }
        if (this.f429a != null) {
            Bundle[] bundleArr = new Bundle[this.f429a.size()];
            int i = 0;
            Iterator<gd> it = this.f429a.iterator();
            while (it.hasNext()) {
                Bundle bundleA = it.next().a();
                if (bundleA != null) {
                    bundleArr[i] = bundleA;
                    i++;
                }
            }
            bundle.putParcelableArray("ext_exts", bundleArr);
        }
        return bundle;
    }

    public gd a(String str) {
        return a(str, null);
    }

    public gd a(String str, String str2) {
        for (gd gdVar : this.f429a) {
            if (str2 == null || str2.equals(gdVar.b())) {
                if (str.equals(gdVar.m351a())) {
                    return gdVar;
                }
            }
        }
        return null;
    }

    /* renamed from: a, reason: collision with other method in class */
    public gk m355a() {
        return this.f428a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized Object m356a(String str) {
        if (this.f430a == null) {
            return null;
        }
        return this.f430a.get(str);
    }

    /* renamed from: a */
    public abstract String mo354a();

    /* renamed from: a, reason: collision with other method in class */
    public synchronized Collection<gd> m357a() {
        if (this.f429a == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList(this.f429a));
    }

    public void a(gd gdVar) {
        this.f429a.add(gdVar);
    }

    public void a(gk gkVar) {
        this.f428a = gkVar;
    }

    public synchronized Collection<String> b() {
        if (this.f430a == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(new HashSet(this.f430a.keySet()));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        gg ggVar = (gg) obj;
        if (this.f428a == null ? ggVar.f428a != null : !this.f428a.equals(ggVar.f428a)) {
            return false;
        }
        if (this.g == null ? ggVar.g != null : !this.g.equals(ggVar.g)) {
            return false;
        }
        if (!this.f429a.equals(ggVar.f429a)) {
            return false;
        }
        if (this.e == null ? ggVar.e != null : !this.e.equals(ggVar.e)) {
            return false;
        }
        if (this.h == null ? ggVar.h != null : !this.h.equals(ggVar.h)) {
            return false;
        }
        if (this.f430a == null ? ggVar.f430a != null : !this.f430a.equals(ggVar.f430a)) {
            return false;
        }
        if (this.f == null ? ggVar.f != null : !this.f.equals(ggVar.f)) {
            return false;
        }
        if (this.d != null) {
            if (this.d.equals(ggVar.d)) {
                return true;
            }
        } else if (ggVar.d == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((((((this.d != null ? this.d.hashCode() : 0) * 31) + (this.e != null ? this.e.hashCode() : 0)) * 31) + (this.f != null ? this.f.hashCode() : 0)) * 31) + (this.g != null ? this.g.hashCode() : 0)) * 31) + (this.h != null ? this.h.hashCode() : 0)) * 31) + this.f429a.hashCode()) * 31) + this.f430a.hashCode()) * 31) + (this.f428a != null ? this.f428a.hashCode() : 0);
    }

    public String j() {
        if ("ID_NOT_AVAILABLE".equals(this.e)) {
            return null;
        }
        if (this.e == null) {
            this.e = i();
        }
        return this.e;
    }

    public String k() {
        return this.h;
    }

    public void k(String str) {
        this.e = str;
    }

    public String l() {
        return this.f;
    }

    public void l(String str) {
        this.h = str;
    }

    public String m() {
        return this.g;
    }

    public void m(String str) {
        this.f = str;
    }

    public String n() {
        return this.i;
    }

    public void n(String str) {
        this.g = str;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:34|(2:90|35)|(6:92|36|85|37|77|38)|79|39|98|57) */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f0 A[EXC_TOP_SPLITTER, PHI: r4
  0x00f0: PHI (r4v7 java.io.ByteArrayOutputStream) = (r4v5 java.io.ByteArrayOutputStream), (r4v8 java.io.ByteArrayOutputStream), (r4v8 java.io.ByteArrayOutputStream) binds: [B:55:0x010c, B:73:0x00f0, B:38:0x00ed] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0109 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x010f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected synchronized java.lang.String o() {
        /*
            Method dump skipped, instructions count: 304
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.gg.o():java.lang.String");
    }

    public void o(String str) {
        this.i = str;
    }

    public String p() {
        return this.d;
    }
}

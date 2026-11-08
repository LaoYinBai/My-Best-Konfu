package com.bytedance.a;

import android.os.Handler;
import android.text.TextUtils;
import com.bytedance.common.utility.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class bz {
    public bz d;
    public Handler e;

    public class a implements h<String> {
        public a() {
        }

        @Override // com.bytedance.a.bz.h
        public void a(String str) {
            bz.this.a("openudid", str);
        }

        @Override // com.bytedance.a.bz.h
        public boolean b(String str) {
            return dj.a(str);
        }

        @Override // com.bytedance.a.bz.h
        public String a() {
            return bz.this.a("openudid");
        }

        @Override // com.bytedance.a.bz.h
        public boolean a(String str, String str2) {
            return dj.a(str, str2);
        }

        @Override // com.bytedance.a.bz.h
        public String a(String str, String str2, bz bzVar) {
            String str3 = str;
            return bzVar == null ? str3 : bzVar.b(str3, str2);
        }
    }

    public class b implements h<String> {
        public b() {
        }

        @Override // com.bytedance.a.bz.h
        public void a(String str) {
            bz.this.a("clientudid", str);
        }

        @Override // com.bytedance.a.bz.h
        public boolean b(String str) {
            return dj.a(str);
        }

        @Override // com.bytedance.a.bz.h
        public String a() {
            return bz.this.a("clientudid");
        }

        @Override // com.bytedance.a.bz.h
        public boolean a(String str, String str2) {
            return dj.a(str, str2);
        }

        @Override // com.bytedance.a.bz.h
        public String a(String str, String str2, bz bzVar) {
            String str3 = str;
            return bzVar == null ? str3 : bzVar.c(str3, str2);
        }
    }

    public class c implements h<String> {
        public c() {
        }

        @Override // com.bytedance.a.bz.h
        public void a(String str) {
            bz.this.a("serial_number", str);
        }

        @Override // com.bytedance.a.bz.h
        public boolean b(String str) {
            String str2 = str;
            return (TextUtils.isEmpty(str2) || TextUtils.equals(str2, "unknown")) ? false : true;
        }

        @Override // com.bytedance.a.bz.h
        public String a() {
            return bz.this.a("serial_number");
        }

        @Override // com.bytedance.a.bz.h
        public boolean a(String str, String str2) {
            return dj.a(str, str2);
        }

        @Override // com.bytedance.a.bz.h
        public String a(String str, String str2, bz bzVar) {
            String str3 = str;
            return bzVar == null ? str3 : bzVar.d(str3, str2);
        }
    }

    public class d implements h<String[]> {
        public d() {
        }

        @Override // com.bytedance.a.bz.h
        public void a(String[] strArr) {
            bz.this.a("sim_serial_number", strArr);
        }

        @Override // com.bytedance.a.bz.h
        public boolean b(String[] strArr) {
            String[] strArr2 = strArr;
            return strArr2 != null && strArr2.length > 0;
        }

        @Override // com.bytedance.a.bz.h
        public String[] a() {
            return bz.this.b("sim_serial_number");
        }

        @Override // com.bytedance.a.bz.h
        public boolean a(String[] strArr, String[] strArr2) {
            String[] strArr3 = strArr;
            String[] strArr4 = strArr2;
            if (strArr3 == strArr4) {
                return true;
            }
            if (strArr3 != null && strArr4 != null && strArr3.length == strArr4.length) {
                for (String str : strArr3) {
                    boolean z = false;
                    for (String str2 : strArr4) {
                        z = dj.a(str2, str) || z;
                    }
                    if (z) {
                    }
                }
                return true;
            }
            return false;
        }

        @Override // com.bytedance.a.bz.h
        public String[] a(String[] strArr, String[] strArr2, bz bzVar) {
            String[] strArr3 = strArr;
            return bzVar == null ? strArr3 : bzVar.a(strArr3, strArr2);
        }
    }

    public class e implements h<String> {
        public e() {
        }

        @Override // com.bytedance.a.bz.h
        public void a(String str) {
            bz.this.a("udid", str);
        }

        @Override // com.bytedance.a.bz.h
        public boolean b(String str) {
            return dj.a(str);
        }

        @Override // com.bytedance.a.bz.h
        public String a() {
            return bz.this.a("udid");
        }

        @Override // com.bytedance.a.bz.h
        public boolean a(String str, String str2) {
            return dj.a(str, str2);
        }

        @Override // com.bytedance.a.bz.h
        public String a(String str, String str2, bz bzVar) {
            String str3 = str;
            return bzVar == null ? str3 : bzVar.e(str3, str2);
        }
    }

    public class f implements h<String> {
        public f() {
        }

        @Override // com.bytedance.a.bz.h
        public void a(String str) {
            bz.this.a("udid_list", str);
        }

        @Override // com.bytedance.a.bz.h
        /* renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public boolean b(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    return m.a(new JSONArray(str));
                } catch (JSONException e) {
                    de.a("", e);
                }
            }
            return false;
        }

        @Override // com.bytedance.a.bz.h
        public String a() {
            return bz.this.a("udid_list");
        }

        @Override // com.bytedance.a.bz.h
        public boolean a(String str, String str2) {
            return StringUtils.equal(str, str2);
        }

        @Override // com.bytedance.a.bz.h
        public String a(String str, String str2, bz bzVar) {
            String str3 = str;
            return bzVar == null ? str3 : bzVar.f(str3, str2);
        }
    }

    public class g implements h<String> {
        public g() {
        }

        @Override // com.bytedance.a.bz.h
        public void a(String str) {
            bz.this.a(com.bytedance.b.a.c.h.x, str);
        }

        @Override // com.bytedance.a.bz.h
        public boolean b(String str) {
            return !TextUtils.isEmpty(str);
        }

        @Override // com.bytedance.a.bz.h
        public String a() {
            return bz.this.a(com.bytedance.b.a.c.h.x);
        }

        @Override // com.bytedance.a.bz.h
        public boolean a(String str, String str2) {
            return dj.a(str, str2);
        }

        @Override // com.bytedance.a.bz.h
        public String a(String str, String str2, bz bzVar) {
            String str3 = str;
            return bzVar == null ? str3 : bzVar.g(str3, str2);
        }
    }

    public interface h<L> {
        L a();

        L a(L l, L l2, bz bzVar);

        void a(L l);

        boolean a(L l, L l2);

        boolean b(L l);
    }

    public abstract String a(String str);

    public abstract void a(String str, String str2);

    public abstract void a(String str, String[] strArr);

    public String[] a(String[] strArr, String[] strArr2) {
        return (String[]) a(strArr, strArr2, new d());
    }

    public String b(String str, String str2) {
        return (String) a(str, str2, new a());
    }

    public abstract String[] b(String str);

    public String c(String str, String str2) {
        return (String) a(str, str2, new b());
    }

    public void c(String str) {
        bz bzVar = this.d;
        if (bzVar != null) {
            bzVar.c(str);
        }
    }

    public String d(String str, String str2) {
        return (String) a(str, str2, new c());
    }

    public String e(String str, String str2) {
        return (String) a(str, str2, new e());
    }

    public String f(String str, String str2) {
        return (String) a(str, str2, new f());
    }

    public String g(String str, String str2) {
        return (String) a(str, str2, new g());
    }

    public final <T> T a(T t, T t2, h<T> hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("agent == null");
        }
        bz bzVar = this.d;
        T tA = hVar.a();
        boolean zB = hVar.b(t);
        boolean zB2 = hVar.b(tA);
        if (!zB && zB2) {
            t = tA;
        }
        if (bzVar != null) {
            T tA2 = hVar.a(t, t2, bzVar);
            if (!hVar.a(tA2, tA)) {
                hVar.a(tA2);
            }
            return tA2;
        }
        boolean z = false;
        if (!zB && !zB2) {
            z = true;
            t = t2;
        }
        if ((z && hVar.b(t)) || (zB && !hVar.a(t, tA))) {
            hVar.a(t);
        }
        return t;
    }

    public void a(Handler handler) {
        bz bzVar = this.d;
        if (bzVar != null) {
            bzVar.a(handler);
        }
        this.e = handler;
    }
}

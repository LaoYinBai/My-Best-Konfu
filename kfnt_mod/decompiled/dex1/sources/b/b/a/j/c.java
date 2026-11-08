package b.b.a.j;

import android.os.Handler;
import android.text.TextUtils;
import b.b.a.k.x;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class c {
    public c d;
    public Handler e;

    public interface e<L> {
        L a();

        L a(L l, L l2, c cVar);

        void a(L l);

        boolean a(L l, L l2);

        boolean b(L l);
    }

    public abstract String a(String str);

    public abstract void a(String str, String str2);

    public abstract void a(String str, String[] strArr);

    public String b(String str, String str2) {
        return (String) a(str, str2, new a());
    }

    public abstract String[] b(String str);

    public String c(String str, String str2) {
        return (String) a(str, str2, new b());
    }

    public void c(String str) {
        c cVar = this.d;
        if (cVar != null) {
            cVar.c(str);
        }
    }

    public String d(String str, String str2) {
        return (String) a(str, str2, new d());
    }

    public class a implements e<String> {
        public a() {
        }

        @Override // b.b.a.j.c.e
        public void a(String str) {
            c.this.a("openudid", str);
        }

        @Override // b.b.a.j.c.e
        public boolean b(String str) {
            return x.a(str);
        }

        @Override // b.b.a.j.c.e
        public boolean a(String str, String str2) {
            return x.a(str, str2);
        }

        @Override // b.b.a.j.c.e
        public String a(String str, String str2, c cVar) {
            String str3 = str;
            return cVar == null ? str3 : cVar.b(str3, str2);
        }

        @Override // b.b.a.j.c.e
        public String a() {
            return c.this.a("openudid");
        }
    }

    public class b implements e<String> {
        public b() {
        }

        @Override // b.b.a.j.c.e
        public void a(String str) {
            c.this.a("clientudid", str);
        }

        @Override // b.b.a.j.c.e
        public boolean b(String str) {
            return x.a(str);
        }

        @Override // b.b.a.j.c.e
        public boolean a(String str, String str2) {
            return x.a(str, str2);
        }

        @Override // b.b.a.j.c.e
        public String a(String str, String str2, c cVar) {
            String str3 = str;
            return cVar == null ? str3 : cVar.c(str3, str2);
        }

        @Override // b.b.a.j.c.e
        public String a() {
            return c.this.a("clientudid");
        }
    }

    /* renamed from: b.b.a.j.c$c, reason: collision with other inner class name */
    public class C0025c implements e<String[]> {
        public C0025c() {
        }

        @Override // b.b.a.j.c.e
        public void a(String[] strArr) {
            c.this.a("sim_serial_number", strArr);
        }

        @Override // b.b.a.j.c.e
        public boolean b(String[] strArr) {
            String[] strArr2 = strArr;
            return strArr2 != null && strArr2.length > 0;
        }

        @Override // b.b.a.j.c.e
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
                        z = x.a(str2, str) || z;
                    }
                    if (z) {
                    }
                }
                return true;
            }
            return false;
        }

        @Override // b.b.a.j.c.e
        public String[] a(String[] strArr, String[] strArr2, c cVar) {
            String[] strArr3 = strArr;
            return cVar == null ? strArr3 : cVar.a(strArr3, strArr2);
        }

        @Override // b.b.a.j.c.e
        public String[] a() {
            return c.this.b("sim_serial_number");
        }
    }

    public class d implements e<String> {
        public d() {
        }

        @Override // b.b.a.j.c.e
        public void a(String str) {
            c.this.a(com.bytedance.b.a.c.h.x, str);
        }

        @Override // b.b.a.j.c.e
        public boolean b(String str) {
            return !TextUtils.isEmpty(str);
        }

        @Override // b.b.a.j.c.e
        public boolean a(String str, String str2) {
            return x.a(str, str2);
        }

        @Override // b.b.a.j.c.e
        public String a(String str, String str2, c cVar) {
            String str3 = str;
            return cVar == null ? str3 : cVar.d(str3, str2);
        }

        @Override // b.b.a.j.c.e
        public String a() {
            return c.this.a(com.bytedance.b.a.c.h.x);
        }
    }

    public final <T> T a(T t, T t2, e<T> eVar) {
        if (eVar != null) {
            c cVar = this.d;
            T tA = eVar.a();
            boolean zB = eVar.b(t);
            boolean zB2 = eVar.b(tA);
            if (!zB && zB2) {
                t = tA;
            }
            if (cVar != null) {
                T tA2 = eVar.a(t, t2, cVar);
                if (!eVar.a(tA2, tA)) {
                    eVar.a(tA2);
                }
                return tA2;
            }
            boolean z = false;
            if (!zB && !zB2) {
                z = true;
                t = t2;
            }
            if ((z && eVar.b(t)) || (zB && !eVar.a(t, tA))) {
                eVar.a(t);
            }
            return t;
        }
        throw new IllegalArgumentException("agent == null");
    }

    public void a(Handler handler) {
        c cVar = this.d;
        if (cVar != null) {
            cVar.a(handler);
        }
        this.e = handler;
    }

    public String[] a(String[] strArr, String[] strArr2) {
        return (String[]) a(strArr, strArr2, new C0025c());
    }
}

package cp.bd.c;

import cp.bd.c.d;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class g extends d.a {
    private static g a;

    public static class a {
        private long a;
        private long b;
        private String c;
        private String d;

        public final void a(String str) {
            this.c = str;
            this.d = null;
            this.a = System.currentTimeMillis();
        }

        public final void a(String str, int i) {
            g gVarA = g.a();
            if (gVarA == null) {
                return;
            }
            this.b = System.currentTimeMillis() - this.a;
            gVarA.a(this.b, this.a, this.c, this.d, str, i);
        }

        public final void b(String str, int i) {
            g gVarA = g.a();
            if (gVarA == null) {
                return;
            }
            this.b = System.currentTimeMillis() - this.a;
            gVarA.b(this.b, this.a, this.c, this.d, str, i);
        }
    }

    public static synchronized g a() {
        return a;
    }

    public static synchronized void a(g gVar) {
        a = gVar;
    }

    @Override // cp.bd.c.d.a
    public final Object a(int i, int i2, long j, String str, Object obj) {
        if (i == 131073) {
            if (str == null || !a(str)) {
                return null;
            }
            return "OK";
        }
        if (i == 131074) {
            String[] strArr = (String[]) obj;
            if (str != null && strArr != null && strArr.length != 0) {
                a(str, new JSONObject(strArr[0]), new JSONObject(strArr[1]), new JSONObject(strArr[2]));
            }
        }
        return null;
    }

    public abstract void a(long j, long j2, String str, String str2, String str3, int i);

    public abstract void a(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);

    public abstract boolean a(String str);

    public abstract void b(long j, long j2, String str, String str2, String str3, int i);
}

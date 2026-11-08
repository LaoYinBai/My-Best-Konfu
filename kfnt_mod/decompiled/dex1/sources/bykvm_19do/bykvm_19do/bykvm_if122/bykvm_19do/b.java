package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do;

import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.e0;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.s;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.u;
import com.bytedance.msdk.api.UserInfoForSegment;
import com.bytedance.msdk.api.v2.GMConfigUserInfoForSegment;
import com.bytedance.msdk.api.v2.GMPrivacyConfig;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {
    private static final b z = new b();

    /* renamed from: c, reason: collision with root package name */
    private String[] f1771c;
    private String d;
    private String e;
    private String i;
    private boolean j;
    private String k;
    private Map<String, String> l;
    private String m;
    private String n;

    @Deprecated
    private UserInfoForSegment r;
    private GMConfigUserInfoForSegment s;
    private Map<String, Object> w;
    private boolean x;
    private int[] y;

    /* renamed from: a, reason: collision with root package name */
    private int f1769a = 0;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1770b = false;
    private boolean f = true;
    private boolean g = false;
    private boolean h = false;
    private Set<String> o = new HashSet();
    private Map<String, Map<String, String>> p = new HashMap();
    private Map<String, Map<String, String>> q = new HashMap();
    private int t = 2;
    private long u = -1;
    private GMPrivacyConfig v = new GMPrivacyConfig();

    private b() {
    }

    public static b D() {
        return z;
    }

    private static void g(String str) {
        s.a(str, "name不能为空");
    }

    public boolean A() {
        return this.h;
    }

    public boolean B() {
        return this.f1770b;
    }

    public boolean C() {
        return ("com.header.app.untext".equals(a.d().getPackageName()) && "5001121".equals(this.d)) || ("com.bytedance.mediation_demo".equals(a.d().getPackageName()) && "5001121".equals(this.d));
    }

    public void a(long j) {
        try {
            u uVarA = u.a("tt_sdk_settings", a.d());
            if (uVarA.a("first_install_time", -1L) == -1) {
                this.u = j;
                uVarA.b("first_install_time", j);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b(boolean z2) {
        this.j = z2;
    }

    public String c() {
        return this.d;
    }

    public Map<String, String> d() {
        return this.l;
    }

    public long e() {
        long j = this.u;
        if (j != -1) {
            return j;
        }
        try {
            u uVarA = u.a("tt_sdk_settings", a.d());
            long jA = uVarA.a("first_install_time", -1L);
            if (jA != -1) {
                this.u = jA;
                return jA;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            uVarA.b("first_install_time", jCurrentTimeMillis);
            this.u = jCurrentTimeMillis;
            return jCurrentTimeMillis;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public void f(boolean z2) {
        this.f1770b = z2;
    }

    public String h() {
        return this.e;
    }

    public Map<String, Map<String, String>> i() {
        return this.p;
    }

    public String j() {
        return this.k;
    }

    public String k() {
        return this.m;
    }

    public String[] l() {
        return this.f1771c;
    }

    public int m() {
        return this.t;
    }

    public int n() {
        return this.f1769a;
    }

    public int[] o() {
        return this.y;
    }

    public String p() {
        if (("com.header.app.untext".equals(a.d().getPackageName()) && "5001121".equals(this.d)) || ("com.bytedance.mediation_demo".equals(a.d().getPackageName()) && "5001121".equals(this.d))) {
            try {
                return u.a("tt_mediation_ppe_info", a.d()).d("tt_ppe_content");
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public GMPrivacyConfig q() {
        return this.v;
    }

    public String r() {
        return this.i;
    }

    public Map<String, Map<String, String>> s() {
        return this.q;
    }

    @Deprecated
    public UserInfoForSegment t() {
        return this.r;
    }

    public boolean u() {
        return "5001121".equals(this.d) || "5001121".equals(this.d);
    }

    public boolean v() {
        String strA = e0.a();
        return "com.header.app.untext".equals(strA) || "com.bytedance.mediation_demo".equals(strA);
    }

    public boolean w() {
        return this.x;
    }

    public boolean x() {
        return this.j;
    }

    public boolean y() {
        return this.f;
    }

    public boolean z() {
        return this.g;
    }

    private static void f(String str) {
        s.a(str, "appid不能为空");
    }

    public void b(String str) {
        g(str);
        this.e = str;
    }

    public void c(String str) {
        this.k = str;
    }

    public void d(boolean z2) {
        this.g = z2;
    }

    public Map<String, Object> g() {
        return this.w;
    }

    public void c(boolean z2) {
        this.f = z2;
    }

    public void d(String str) {
        this.m = str;
    }

    public GMConfigUserInfoForSegment f() {
        return this.s;
    }

    public void b(int i) {
        this.f1769a = i;
    }

    public void c(Map<String, Map<String, String>> map) {
        if (map != null) {
            this.p.putAll(map);
        }
    }

    public void d(Map<String, Map<String, String>> map) {
        if (map != null) {
            this.q.putAll(map);
        }
    }

    public String b() {
        if (!TextUtils.isEmpty(this.n)) {
            return this.n;
        }
        this.n = u.a((String) null, a.d()).b();
        if (!TextUtils.isEmpty(this.n)) {
            return this.n;
        }
        String strValueOf = String.valueOf(System.currentTimeMillis());
        u.a((String) null, a.d()).e(strValueOf);
        this.n = strValueOf;
        return this.n;
    }

    public void a(String str) {
        f(str);
        this.d = str;
    }

    public void a(Map<String, String> map) {
        this.l = map;
    }

    public void a(int... iArr) {
        this.y = iArr;
    }

    public void a(String[] strArr) {
        this.f1771c = strArr;
    }

    public Set<String> a() {
        return this.o;
    }

    public void a(Set<String> set) {
        if (set != null) {
            this.o.addAll(set);
        }
    }

    @Deprecated
    public void a(UserInfoForSegment userInfoForSegment, boolean z2) {
        boolean zEquals;
        if (!z2) {
            if (this.r == null && userInfoForSegment == null) {
                zEquals = true;
            } else {
                UserInfoForSegment userInfoForSegment2 = this.r;
                zEquals = (userInfoForSegment2 == null || userInfoForSegment == null) ? false : userInfoForSegment2.equals(userInfoForSegment);
            }
            this.r = userInfoForSegment;
            if (zEquals) {
                return;
            }
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.a(a.f()).d().a();
            return;
        }
        this.r = userInfoForSegment;
    }

    public void b(Map<String, Object> map) {
        this.w = map;
    }

    public void e(boolean z2) {
        this.h = z2;
    }

    public void e(String str) {
        this.i = str;
    }

    public void e(Map<String, String> map) {
        if (this.l == null) {
            this.l = new HashMap();
        }
        if (map == null || map.size() <= 0) {
            return;
        }
        this.l.putAll(map);
    }

    public void a(GMConfigUserInfoForSegment gMConfigUserInfoForSegment, boolean z2) {
        boolean zEquals;
        if (!z2) {
            if (this.s == null && gMConfigUserInfoForSegment == null) {
                zEquals = true;
            } else {
                GMConfigUserInfoForSegment gMConfigUserInfoForSegment2 = this.s;
                zEquals = (gMConfigUserInfoForSegment2 == null || gMConfigUserInfoForSegment == null) ? false : gMConfigUserInfoForSegment2.equals(gMConfigUserInfoForSegment);
            }
            this.s = gMConfigUserInfoForSegment;
            if (zEquals) {
                return;
            }
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_else10.b.a(a.f()).d().a();
            return;
        }
        this.s = gMConfigUserInfoForSegment;
    }

    public void a(int i) {
        this.t = i;
    }

    public void a(GMPrivacyConfig gMPrivacyConfig) {
        if (gMPrivacyConfig != null) {
            this.v = gMPrivacyConfig;
        }
    }

    public void a(boolean z2) {
        this.x = z2;
    }
}

package com.apm.applog;

import android.accounts.Account;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.f;
import b.b.a.g;
import b.b.b.v;
import com.bytedance.mpaas.IEncryptor;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {
    public Map<String, String> A;
    public Account B;
    public boolean C;
    public b.b.a.h.b E;
    public boolean G;
    public String M;
    public List<String> S;
    public String V;
    public boolean W;

    /* renamed from: a, reason: collision with root package name */
    public String f2741a;

    /* renamed from: c, reason: collision with root package name */
    public String f2743c;
    public String d;
    public IEncryptor e;
    public String f;
    public String g;
    public c h;
    public String i;
    public String j;
    public boolean k;
    public boolean m;
    public String o;
    public boolean p;
    public String q;
    public e r;
    public String s;
    public String t;
    public int u;
    public int v;
    public int w;
    public String x;
    public String y;
    public String z;

    /* renamed from: b, reason: collision with root package name */
    public boolean f2742b = true;
    public boolean l = false;
    public int n = 0;
    public b.b.a.h.b D = new b.b.a.k.d();
    public boolean F = true;
    public boolean H = false;
    public boolean I = false;
    public boolean J = false;
    public boolean K = true;
    public boolean L = true;
    public String N = null;
    public boolean O = false;
    public boolean P = false;
    public boolean Q = false;
    public boolean R = false;
    public boolean T = false;
    public boolean U = false;
    public String X = null;
    public String Y = null;

    public interface a {
    }

    public d(@NonNull String str, @NonNull String str2) {
        this.f2741a = str;
        this.f2743c = str2;
        if (TextUtils.isEmpty(str2)) {
            Log.e("InitConfig", "channel is empty, please check!!!");
        }
    }

    public String A() {
        return this.t;
    }

    public int B() {
        return this.u;
    }

    public String C() {
        return this.X;
    }

    public int D() {
        return this.v;
    }

    public int E() {
        return this.w;
    }

    public String F() {
        return this.x;
    }

    public String G() {
        return this.y;
    }

    public String H() {
        return this.z;
    }

    public boolean I() {
        return this.G;
    }

    public boolean J() {
        return this.F;
    }

    public boolean K() {
        return this.H;
    }

    public boolean L() {
        return this.I;
    }

    public boolean M() {
        return this.K;
    }

    public boolean N() {
        return this.L;
    }

    public String O() {
        String str = this.M;
        if (str != null) {
            return str;
        }
        StringBuilder sbA = b.a.a.a.a.a("bd_tea_agent_");
        sbA.append(h());
        return sbA.toString();
    }

    public String P() {
        String str = this.N;
        if (str != null) {
            return str;
        }
        StringBuilder sbA = b.a.a.a.a.a("applog_stats_");
        sbA.append(this.f2741a);
        return sbA.toString();
    }

    public v Q() {
        return null;
    }

    public g R() {
        return null;
    }

    public boolean S() {
        return this.O;
    }

    public boolean T() {
        return this.T;
    }

    public boolean U() {
        return this.Q;
    }

    public boolean V() {
        return this.R;
    }

    @Nullable
    public List<String> W() {
        return this.S;
    }

    public boolean X() {
        return this.U;
    }

    public d Y() {
        this.T = false;
        return this;
    }

    public boolean Z() {
        return this.P;
    }

    @NonNull
    public d a(int i) {
        this.n = i;
        return this;
    }

    public d a(Account account) {
        this.B = account;
        return this;
    }

    @NonNull
    public d a(f fVar) {
        return this;
    }

    public d a(b.b.a.h.b bVar) {
        this.E = bVar;
        com.apm.applog.a.a(bVar);
        return this;
    }

    public d a(v vVar) {
        return this;
    }

    public d a(c cVar) {
        this.h = cVar;
        return this;
    }

    public d a(a aVar) {
        return this;
    }

    public d a(IEncryptor iEncryptor) {
        this.e = iEncryptor;
        return this;
    }

    public d a(String str) {
        this.Y = str;
        return this;
    }

    public d a(List<String> list) {
        this.S = list;
        return this;
    }

    public d a(Map<String, String> map) {
        this.A = map;
        return this;
    }

    public d a(boolean z) {
        this.f2742b = z;
        return this;
    }

    public String a() {
        return this.Y;
    }

    public void a(g gVar) {
        com.apm.applog.a.a(gVar);
    }

    public String aa() {
        return this.V;
    }

    public boolean ab() {
        return this.W;
    }

    public a ac() {
        return null;
    }

    public c b() {
        return this.h;
    }

    public d b(int i) {
        this.r = e.a(i);
        return this;
    }

    public d b(boolean z) {
        this.p = z;
        return this;
    }

    public void b(@NonNull String str) {
        this.f2743c = str;
    }

    public d c(int i) {
        this.u = i;
        return this;
    }

    @NonNull
    public d c(String str) {
        this.o = str;
        return this;
    }

    @NonNull
    public d c(boolean z) {
        this.m = z;
        return this;
    }

    public boolean c() {
        return this.f2742b;
    }

    public d d(int i) {
        this.v = i;
        return this;
    }

    @NonNull
    public d d(String str) {
        this.f = str;
        return this;
    }

    public String d() {
        return this.o;
    }

    public void d(boolean z) {
        this.J = z;
    }

    public d e(int i) {
        this.w = i;
        return this;
    }

    @NonNull
    public d e(String str) {
        this.g = str;
        return this;
    }

    public d e(boolean z) {
        this.k = z;
        return this;
    }

    public boolean e() {
        return this.p;
    }

    @NonNull
    public d f(String str) {
        this.i = str;
        return this;
    }

    public d f(boolean z) {
        this.l = z;
        return this;
    }

    public Map<String, String> f() {
        return this.A;
    }

    public d g(String str) {
        this.j = str;
        return this;
    }

    public void g(boolean z) {
        this.G = z;
    }

    public boolean g() {
        return this.m;
    }

    public String h() {
        return this.f2741a;
    }

    public void h(String str) {
        this.C = true;
        this.d = str;
    }

    public void h(boolean z) {
        this.F = z;
    }

    public d i(String str) {
        this.q = str;
        return this;
    }

    public String i() {
        return this.f2743c;
    }

    public void i(boolean z) {
        this.H = z;
    }

    public d j(String str) {
        this.s = str;
        return this;
    }

    public String j() {
        return this.f;
    }

    public void j(boolean z) {
        this.I = z;
    }

    public d k(String str) {
        this.t = str;
        return this;
    }

    public String k() {
        return this.g;
    }

    public void k(boolean z) {
        this.K = z;
    }

    public d l(String str) {
        this.X = str;
        return this;
    }

    public String l() {
        return this.i;
    }

    public void l(boolean z) {
        this.L = z;
    }

    public d m(String str) {
        this.x = str;
        return this;
    }

    public String m() {
        return this.j;
    }

    public void m(boolean z) {
        this.O = z;
    }

    public int n() {
        return this.n;
    }

    public d n(String str) {
        this.y = str;
        return this;
    }

    public d n(boolean z) {
        this.Q = z;
        return this;
    }

    public f o() {
        return null;
    }

    public d o(String str) {
        this.z = str;
        return this;
    }

    public d o(boolean z) {
        this.R = z;
        return this;
    }

    public d p(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.M = str;
        }
        return this;
    }

    public d p(boolean z) {
        this.U = z;
        return this;
    }

    public boolean p() {
        return this.J;
    }

    public d q(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.N = str;
        }
        return this;
    }

    public d q(boolean z) {
        this.P = z;
        return this;
    }

    public e q() {
        return this.r;
    }

    public IEncryptor r() {
        return this.e;
    }

    public void r(String str) {
        this.V = str;
    }

    public void r(boolean z) {
        this.W = z;
    }

    public boolean s() {
        return this.k;
    }

    public boolean t() {
        return this.l;
    }

    public Account u() {
        return this.B;
    }

    public boolean v() {
        return this.C;
    }

    public String w() {
        return this.d;
    }

    public b.b.a.h.b x() {
        b.b.a.h.b bVar = this.E;
        return bVar != null ? bVar : this.D;
    }

    public String y() {
        return this.q;
    }

    public String z() {
        return this.s;
    }

    public d a(e eVar) {
        this.r = eVar;
        return this;
    }
}

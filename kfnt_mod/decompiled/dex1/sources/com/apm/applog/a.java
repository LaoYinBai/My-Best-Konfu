package com.apm.applog;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.HandlerThread;
import android.text.TextUtils;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.d.f;
import b.b.a.f.g;
import b.b.a.f.h;
import b.b.a.f.j;
import b.b.a.i;
import b.b.a.k.t;
import b.b.a.k.v;
import com.bytedance.sdk.account.platform.base.AuthorizeMonitorUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f2738a = true;

    /* renamed from: b, reason: collision with root package name */
    public static int f2739b = 1;

    /* renamed from: c, reason: collision with root package name */
    public static volatile b.b.a.c.a f2740c = null;
    public static Application d = null;
    public static volatile boolean e = false;
    public static Integer f = null;
    public static volatile b.b.a.e.a g = null;
    public static ConcurrentHashMap<String, a> h = new ConcurrentHashMap<>();
    public static b.b.a.h.b i = new b.b.a.k.d();
    public static b.b.a.h.b j = null;
    public static final String k = "event_v1";

    @SuppressLint({"StaticFieldLeak"})
    public volatile g l;

    @SuppressLint({"StaticFieldLeak"})
    public volatile h m;
    public b.b.a.d.b n;

    public a() {
        v.a((Throwable) null);
    }

    public static Context a() {
        return d;
    }

    public static a a(@NonNull Context context, @NonNull d dVar) {
        a aVar = h.get(dVar.h());
        return aVar != null ? aVar : new a(context, dVar);
    }

    public static a a(String str) {
        return h.get(str);
    }

    public static void a(int i2) {
        f = Integer.valueOf(i2);
    }

    public static void a(long j2) {
        f.f1057a = j2;
    }

    public static void a(Context context, boolean z) {
        j.a(context, z);
    }

    public static void a(b.b.a.a aVar) {
    }

    public static void a(b.b.a.b bVar) {
        b.b.a.k.j.a().a(bVar);
    }

    public static void a(b.b.a.c cVar) {
        b.b.a.i.b.f1098a = cVar;
    }

    public static void a(b.b.a.d dVar) {
    }

    @AnyThread
    public static void a(@Nullable b.b.a.e eVar) {
        b.b.b.f.a(eVar);
    }

    public static void a(b.b.a.g gVar) {
    }

    public static void a(b.b.a.h.b bVar) {
        j = bVar;
    }

    public static void a(b.b.a.h hVar) {
        t.a().a(hVar);
    }

    public static void a(b.b.a.j.b bVar) {
        ConcurrentHashMap<String, a> concurrentHashMap = h;
        if (concurrentHashMap == null || concurrentHashMap.size() <= 0) {
            return;
        }
        Iterator<a> it = h.values().iterator();
        while (it.hasNext()) {
            b.b.a.d.b bVar2 = it.next().n;
            if (bVar2 != null) {
                bVar2.b(bVar);
            }
        }
    }

    public static void a(String str, int i2) {
        if (f2740c != null) {
            f2740c.a(str, i2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.util.List<java.lang.String> r4, boolean r5) {
        /*
            r0 = 0
            if (r4 == 0) goto L3c
            boolean r1 = r4.isEmpty()
            if (r1 != 0) goto L3c
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.Iterator r4 = r4.iterator()
        L12:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L28
            java.lang.Object r2 = r4.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L12
            r1.add(r2)
            goto L12
        L28:
            boolean r4 = r1.isEmpty()
            if (r4 != 0) goto L3c
            if (r5 == 0) goto L36
            b.b.a.e.c r4 = new b.b.a.e.c
            r4.<init>(r1, r0)
            goto L3d
        L36:
            b.b.a.e.b r4 = new b.b.a.e.b
            r4.<init>(r1, r0)
            goto L3d
        L3c:
            r4 = r0
        L3d:
            com.apm.applog.a.g = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apm.applog.a.a(java.util.List, boolean):void");
    }

    public static void a(boolean z) {
        f2738a = z;
    }

    public static boolean a(Context context) {
        j.a(context);
        return false;
    }

    public static b.b.a.h.b b() {
        b.b.a.h.b bVar = j;
        return bVar != null ? bVar : i;
    }

    public static void b(Context context) {
        if (context instanceof Activity) {
            a(context.getClass().getName(), context.hashCode());
        }
    }

    public static void b(b.b.a.b bVar) {
        b.b.a.k.j.a().b(bVar);
    }

    public static void b(@Nullable b.b.a.e eVar) {
        b.b.b.f.b(eVar);
    }

    public static void b(b.b.a.h hVar) {
        t.a().b(hVar);
    }

    public static b.b.a.g c() {
        return null;
    }

    public static void c(Context context) {
        if (context instanceof Activity) {
            i();
        }
    }

    public static b.b.a.a d() {
        return null;
    }

    public static boolean e() {
        return e;
    }

    public static boolean f() {
        return f2738a;
    }

    public static String g() {
        return String.valueOf(f.f1057a);
    }

    public static boolean h() {
        if (!e()) {
            return false;
        }
        j.a();
        return false;
    }

    public static void i() {
        if (f2740c != null) {
            f2740c.onActivityPaused(null);
        }
    }

    public static b.b.a.d j() {
        return null;
    }

    public static String k() {
        return "0.0.5";
    }

    public String A() {
        return this.m != null ? this.m.d.optString("openudid", "") : "";
    }

    public void B() {
        b.b.a.k.c.a(r()).f1126b.clear();
    }

    public int C() {
        if (this.l != null) {
            return this.l.e.getInt("bav_monitor_rate", 0);
        }
        return 0;
    }

    @Nullable
    public JSONObject D() {
        if (this.m != null) {
            return this.m.a();
        }
        v.a(new RuntimeException("init come first"));
        return null;
    }

    public boolean E() {
        if (this.m != null) {
            return this.m.i;
        }
        return false;
    }

    public int F() {
        Integer num = f;
        if (num != null) {
            return num.intValue();
        }
        if (this.l != null) {
            return this.l.e.getInt("http_monitor_port", 0);
        }
        return 0;
    }

    public Map<String, String> G() {
        if (this.l == null) {
            return Collections.emptyMap();
        }
        String string = this.l.e.getString("device_token", "");
        HashMap map = new HashMap();
        if (string == null) {
            string = "";
        }
        map.put("x-tt-dt", string);
        return map;
    }

    public String H() {
        f fVar = this.n.k;
        if (fVar != null) {
            return fVar.b();
        }
        return null;
    }

    public JSONObject I() {
        b.b.a.d.b bVar = this.n;
        return bVar == null ? new JSONObject() : bVar.d.e();
    }

    public <T> T a(String str, T t, Class<T> cls) {
        if (this.m != null) {
            return (T) b.b.a.i.b.a(this.m.d, str, t, cls);
        }
        return null;
    }

    public String a(Context context, String str, boolean z, i iVar) {
        return b.b.a.i.b.a(context, this.m != null ? this.m.a() : null, str, z, iVar);
    }

    public void a(Account account) {
        if (this.m != null) {
            v.a("setAccount " + account, (Throwable) null);
            this.m.a(account);
        }
    }

    public void a(Context context, Map<String, String> map, boolean z, i iVar) {
        b.b.a.i.b.a(context, this.m != null ? this.m.a() : null, z, map, iVar);
    }

    public void a(b bVar) {
        b.b.a.k.c.a(r()).a(bVar);
    }

    public void a(e eVar) {
        if (this.n != null) {
            StringBuilder sbA = b.a.a.a.a.a("setUriRuntime ");
            sbA.append(eVar.a());
            v.a(sbA.toString(), (Throwable) null);
            b.b.a.d.b bVar = this.n;
            bVar.l = eVar;
            bVar.a(bVar.i);
            if (bVar.d.f1070b.J()) {
                bVar.a(true);
            }
        }
    }

    public void a(String str, String str2) {
        boolean z;
        b.b.a.d.b bVar = this.n;
        if (bVar != null) {
            h hVar = bVar.g;
            boolean z2 = false;
            if (hVar.a("app_language", str)) {
                b.a.a.a.a.a(hVar.f1074c.e, "app_language", str);
                z = true;
            } else {
                z = false;
            }
            h hVar2 = bVar.g;
            if (hVar2.a("app_region", str2)) {
                b.a.a.a.a.a(hVar2.f1074c.e, "app_region", str2);
                z2 = true;
            }
            if (z || z2) {
                bVar.a(bVar.i);
            }
        }
    }

    public void a(@NonNull String str, @NonNull String str2, String str3, long j2, long j3, JSONObject jSONObject) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            v.a("category or tag is empty", (Throwable) null);
        } else {
            this.n.b(new b.b.a.j.e(str, str2, str3, j2, j3, jSONObject != null ? jSONObject.toString() : null));
        }
    }

    public void a(@NonNull String str, @Nullable JSONObject jSONObject, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            v.a("both second appid and second app name is empty, return", (Throwable) null);
            return;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String str5 = "second_app_" + str;
        try {
            jSONObject.put(AuthorizeMonitorUtil.Param.PARAMS_FOR_SPECIAL, "second_app");
            jSONObject.put("second_appid", str2);
            jSONObject.put("second_appname", str3);
            jSONObject.put("product_type", str4);
        } catch (Throwable th) {
            v.a(th);
        }
        a(str5, jSONObject);
    }

    public void a(HashMap<String, Object> map) {
        if (this.m != null) {
            this.m.a(map);
        }
    }

    public void a(Map<String, String> map) {
        String strU = u();
        if (!TextUtils.isEmpty(strU)) {
            map.put(com.bytedance.b.a.c.h.x, strU);
        }
        String strW = w();
        if (!TextUtils.isEmpty(strW)) {
            map.put("install_id", strW);
        }
        String strA = A();
        if (!TextUtils.isEmpty(strA)) {
            map.put("openudid", strA);
        }
        String strZ = z();
        if (TextUtils.isEmpty(strZ)) {
            return;
        }
        map.put("clientudid", strZ);
    }

    public void a(JSONObject jSONObject) {
        if (this.m != null) {
            this.m.a("tracer_data", jSONObject);
        }
    }

    public void a(JSONObject jSONObject, b.b.a.b.a aVar) {
        b.b.a.d.b bVar = this.n;
        if (bVar == null || bVar.h == null) {
            return;
        }
        b.b.a.b.b.a(bVar, 0, jSONObject, aVar, bVar.h, false);
    }

    public void a(boolean z, Context context) {
        b.b.a.d.b bVar = this.n;
        if (bVar != null) {
            bVar.a(z, context);
        }
    }

    public void a(boolean z, String str) {
        b.b.a.d.b bVar = this.n;
        if (bVar != null) {
            bVar.h.removeMessages(15);
            bVar.h.obtainMessage(15, new Object[]{Boolean.valueOf(z), str}).sendToTarget();
        }
    }

    public a b(@NonNull Context context, @NonNull d dVar) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        v.a(context, dVar.b());
        v.a("Inited Begin", (Throwable) null);
        if (d == null) {
            d = (Application) context.getApplicationContext();
        }
        h.put(dVar.h(), this);
        this.l = new g(d, dVar);
        this.m = new h(d, this.l);
        this.n = new b.b.a.d.b(d, this.l, this.m);
        dVar.o();
        f2740c = new b.b.a.c.a();
        if (dVar.M()) {
            d.registerActivityLifecycleCallbacks(f2740c);
        }
        try {
            Class.forName("com.bytedance.applog.metasec.MetaSecHelper").getMethod("init", Context.class).invoke(null, context);
        } catch (Exception unused) {
        }
        e = e || dVar.c();
        v.a("Inited End", (Throwable) null);
        return this;
    }

    @Nullable
    public <T> T b(String str, T t) throws JSONException {
        if (this.m == null) {
            return null;
        }
        h hVar = this.m;
        JSONObject jSONObjectOptJSONObject = hVar.f1074c.e().optJSONObject(str);
        if (jSONObjectOptJSONObject != null) {
            String strOptString = jSONObjectOptJSONObject.optString("vid");
            T t2 = (T) jSONObjectOptJSONObject.opt("val");
            hVar.g(strOptString);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ab_sdk_version", strOptString);
                a(hVar.f1074c.a()).a("abtest_exposure", jSONObject);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            if (t2 == null) {
                t2 = null;
            }
            if (t2 != null) {
                return t2;
            }
        }
        return t;
    }

    public void b(b bVar) {
        b.b.a.k.c.a(r()).b(bVar);
    }

    public void b(String str) {
        if (this.m != null) {
            h hVar = this.m;
            if (hVar.a("google_aid", str)) {
                b.a.a.a.a.a(hVar.f1074c.e, "google_aid", str);
            }
        }
    }

    public void b(@NonNull String str, @Nullable JSONObject jSONObject) {
        if (TextUtils.isEmpty(str) || jSONObject == null || jSONObject.length() <= 0) {
            v.a("call onEventData with invalid params, return", (Throwable) null);
            return;
        }
        try {
            this.n.b(new b.b.a.j.f(str, jSONObject));
        } catch (Exception e2) {
            v.a("call onEventData get exception: ", e2);
        }
    }

    public void b(JSONObject jSONObject) {
        if (jSONObject == null || this.m == null) {
            return;
        }
        h hVar = this.m;
        if (hVar.a("app_track", jSONObject)) {
            g gVar = hVar.f1074c;
            b.a.a.a.a.a(gVar.f1071c, "app_track", jSONObject.toString());
        }
    }

    public void b(JSONObject jSONObject, b.b.a.b.a aVar) {
        b.b.a.d.b bVar = this.n;
        if (bVar == null || bVar.h == null) {
            return;
        }
        b.b.a.b.b.a(bVar, 1, jSONObject, aVar, bVar.h, false);
    }

    public void b(boolean z) {
        if (this.m == null) {
            throw new IllegalStateException("Applog还未init()");
        }
        h hVar = this.m;
        hVar.j = z;
        if (hVar.i()) {
            return;
        }
        hVar.a("sim_serial_number", null);
    }

    public void c(String str) {
        b.b.a.d.b bVar = this.n;
        if (bVar != null) {
            bVar.a(str);
        }
    }

    public void c(JSONObject jSONObject) {
        if (this.n == null || jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        this.n.a(jSONObject);
    }

    public void d(String str) {
        a("touch_point", (Object) str);
    }

    public void d(JSONObject jSONObject) {
        if (this.n == null || jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        this.n.b(jSONObject);
    }

    public void e(String str) {
        if (this.m == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.m.c(str);
    }

    public void e(JSONObject jSONObject) {
        if (this.n == null || jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        try {
            if (!b.b.a.a.b.a(jSONObject, (Class<?>[]) new Class[]{Integer.class}, (Class<?>[]) null)) {
                v.a("only support Int", new Exception());
                return;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.n.d(jSONObject);
    }

    public void f(String str) {
        if (this.m != null) {
            this.m.b(str);
        }
    }

    public void f(JSONObject jSONObject) {
        if (this.n == null || jSONObject == null || jSONObject.length() == 0) {
            return;
        }
        try {
            if (!b.b.a.a.b.a(jSONObject, (Class<?>[]) new Class[]{String.class, Integer.class}, (Class<?>[]) new Class[]{String.class})) {
                v.a("only support String、Int、String Array！", new Exception());
                return;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.n.e(jSONObject);
    }

    public void g(String str) {
        if (this.m != null) {
            h hVar = this.m;
            if (hVar.a(com.alipay.sdk.cons.b.f2578b, str)) {
                b.a.a.a.a.a(hVar.f1074c.e, com.alipay.sdk.cons.b.f2578b, str);
            }
        }
    }

    public void i(@NonNull String str) {
        a(str, (JSONObject) null);
    }

    public void j(String str) throws NoSuchMethodException, SecurityException {
        b.b.a.d.b bVar = this.n;
        if (bVar != null) {
            b.b.a.d.a aVar = bVar.p;
            if (aVar != null) {
                aVar.a(true);
            }
            try {
                Constructor<?> constructor = Class.forName("com.bytedance.applog.picker.DomSender").getConstructor(b.b.a.d.b.class, String.class);
                new HandlerThread("bd_tracker_d").start();
                bVar.p = (b.b.a.d.a) constructor.newInstance(bVar, str);
                bVar.h.sendMessage(bVar.h.obtainMessage(9, bVar.p));
            } catch (Exception e2) {
                v.a(e2);
            }
        }
    }

    public void k(String str) throws JSONException {
        if (this.n == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(str, "");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.n.c(jSONObject);
    }

    public void l() {
        if (e) {
            return;
        }
        e = true;
        b.b.a.d.b bVar = this.n;
        if (bVar.o) {
            return;
        }
        bVar.o = true;
        bVar.m.sendEmptyMessage(1);
    }

    public d m() {
        if (this.l != null) {
            return this.l.f1070b;
        }
        return null;
    }

    public void n() {
        b.b.a.d.b bVar = this.n;
        if (bVar != null) {
            bVar.a((String[]) null, true);
        }
    }

    public boolean o() {
        return m() != null && m().U();
    }

    public boolean p() {
        return m() != null && m().V();
    }

    public String q() {
        if (this.m == null) {
            return null;
        }
        h hVar = this.m;
        if (hVar.f1072a) {
            return hVar.d.optString("ab_sdk_version", "");
        }
        g gVar = hVar.f1074c;
        return gVar != null ? gVar.c() : "";
    }

    public String r() {
        return this.m != null ? this.m.f() : "";
    }

    public boolean s() {
        b.b.a.d.b bVar = this.n;
        if (bVar != null) {
            return bVar.a(false);
        }
        return false;
    }

    public boolean t() {
        return this.m.i();
    }

    public String u() {
        return this.m != null ? this.m.d.optString("bd_did", "") : "";
    }

    public String v() {
        return this.m != null ? this.m.d.optString("udid", "") : "";
    }

    public String w() {
        return this.m != null ? this.m.d.optString("install_id", "") : "";
    }

    public String x() {
        return this.m != null ? this.m.g() : "";
    }

    public String y() {
        return this.m != null ? this.m.e() : "";
    }

    public String z() {
        return this.m != null ? this.m.d.optString("clientudid", "") : "";
    }

    public void a(@NonNull String str, @Nullable JSONObject jSONObject) {
        if (TextUtils.isEmpty(str)) {
            v.a("event name is empty", (Throwable) null);
        } else {
            this.n.b(new b.b.a.j.g(str, false, jSONObject != null ? jSONObject.toString() : null));
        }
    }

    public a(@NonNull Context context, @NonNull d dVar) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        b(context, dVar);
    }

    public void a(String str, Object obj) {
        if (this.m == null || TextUtils.isEmpty(str)) {
            return;
        }
        HashMap map = new HashMap();
        map.put(str, obj);
        this.m.a(map);
    }

    public void a(@NonNull String str, @Nullable Bundle bundle) {
        JSONObject jSONObject = null;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        for (String str2 : bundle.keySet()) {
                            jSONObject2.put(str2, bundle.get(str2));
                        }
                        jSONObject = jSONObject2;
                    } catch (Throwable th) {
                        th = th;
                        jSONObject = jSONObject2;
                        v.a(th);
                        a(str, jSONObject);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        a(str, jSONObject);
    }

    public void a(String str, String str2, String str3, long j2, long j3) {
        a(str, str2, str3, j2, j3, null);
    }

    public void b(String str, String str2) {
        a("event_v1", str, str2, 0L, 0L, null);
    }

    public void h(String str) {
        a("event_v1", str, null, 0L, 0L, null);
    }

    public void a(@NonNull String str, @Nullable Bundle bundle, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        JSONObject jSONObject = null;
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            v.a("both second appid and second app name is empty, return", (Throwable) null);
            return;
        }
        String str5 = "second_app_" + str;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        for (String str6 : bundle.keySet()) {
                            jSONObject2.put(str6, bundle.get(str6));
                        }
                        jSONObject2.put(AuthorizeMonitorUtil.Param.PARAMS_FOR_SPECIAL, "second_app");
                        jSONObject2.put("second_appid", str2);
                        jSONObject2.put("second_appname", str3);
                        jSONObject2.put("product_type", str4);
                        jSONObject = jSONObject2;
                    } catch (Throwable th) {
                        th = th;
                        jSONObject = jSONObject2;
                        v.a(th);
                        a(str5, jSONObject);
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        a(str5, jSONObject);
    }
}

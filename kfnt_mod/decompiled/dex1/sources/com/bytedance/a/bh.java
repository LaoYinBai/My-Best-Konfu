package com.bytedance.a;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;
import com.bytedance.a.ai;
import com.bytedance.a.bm;
import com.bytedance.applog.IOaidObserver;
import com.bytedance.common.utility.DeviceUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class bh {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3404a = bh.class.getSimpleName() + "#";

    /* renamed from: b, reason: collision with root package name */
    public static final String f3405b = f3404a;

    /* renamed from: c, reason: collision with root package name */
    public static final List<IOaidObserver> f3406c = new ArrayList();
    public static String d;
    public final bm f;
    public final boolean g;
    public final bw h;
    public final Context i;
    public Map<String, String> k;
    public Long l;
    public final ReentrantLock e = new ReentrantLock();
    public final AtomicBoolean j = new AtomicBoolean(false);

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            bh.this.c();
        }
    }

    public bh(Context context) throws IOException {
        boolean zContains;
        this.i = context.getApplicationContext();
        bm aiVar = null;
        if (cp.e()) {
            aiVar = new cg(new df());
        } else if (df.a()) {
            aiVar = new df();
        } else if (cb.a()) {
            aiVar = new cb(context);
        } else if (cp.f().toUpperCase().contains("HUAWEI") || cp.c()) {
            aiVar = new ai();
        } else if ("OnePlus".equalsIgnoreCase(Build.MANUFACTURER)) {
            aiVar = new cg(null);
        } else {
            String str = Build.BRAND;
            if (str == null ? false : str.toLowerCase(Locale.ENGLISH).contains("meizu")) {
                aiVar = new ax();
            } else if (Build.VERSION.SDK_INT > 28) {
                if (DeviceUtils.ROM_SAMSUNG.equalsIgnoreCase(Build.BRAND) || DeviceUtils.ROM_SAMSUNG.equalsIgnoreCase(Build.MANUFACTURER)) {
                    aiVar = new cv();
                } else if (cp.f().toUpperCase().contains("NUBIA")) {
                    aiVar = new bc();
                } else {
                    String str2 = Build.FINGERPRINT;
                    if (TextUtils.isEmpty(str2)) {
                        String strA = cp.a("ro.build.version.incremental");
                        zContains = !TextUtils.isEmpty(strA) && strA.contains("VIBEUI_V2");
                    } else {
                        zContains = str2.contains("VIBEUI_V2");
                    }
                    aiVar = zContains ? new as() : cp.f().toUpperCase().contains("ASUS") ? new Cdo() : new j();
                }
            } else if (!cp.d() && ai.b(context)) {
                aiVar = new ai();
            }
        }
        this.f = aiVar;
        bm bmVar = this.f;
        if (bmVar != null) {
            this.g = bmVar.c(context);
        } else {
            this.g = false;
        }
        this.h = new bw(context);
    }

    public static void a(@Nullable IOaidObserver.Oaid oaid, Object[] objArr) {
        if (oaid == null || objArr == null) {
            return;
        }
        for (Object obj : objArr) {
            ((IOaidObserver) obj).onOaidLoaded(oaid);
        }
    }

    @AnyThread
    public static void a(@Nullable IOaidObserver iOaidObserver) {
        synchronized (f3406c) {
            f3406c.add(iOaidObserver);
        }
        String str = d;
        if (str != null) {
            a(new IOaidObserver.Oaid(str), new Object[]{iOaidObserver});
        }
    }

    public static <K, V> void a(Map<K, V> map, K k, V v) {
        if (k == null || v == null) {
            return;
        }
        map.put(k, v);
    }

    public static void a(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (TextUtils.isEmpty(str) || obj == null) {
            return;
        }
        try {
            jSONObject.put(str, obj);
        } catch (JSONException e) {
            de.a("", e);
        }
    }

    public static Object[] a() {
        Object[] array;
        synchronized (f3406c) {
            array = f3406c.size() > 0 ? f3406c.toArray() : null;
        }
        return array;
    }

    public static void b(IOaidObserver iOaidObserver) {
        synchronized (f3406c) {
            f3406c.remove(iOaidObserver);
        }
    }

    public void b() {
        if (this.j.compareAndSet(false, true)) {
            a aVar = new a();
            String strA = com.bytedance.a.a.a(new StringBuilder(), f3405b, "-query");
            if (TextUtils.isEmpty(strA)) {
                strA = "TrackerDr";
            }
            new Thread(new ad(aVar, strA), strA).start();
        }
    }

    public final void c() {
        String str;
        Boolean boolValueOf;
        br brVar;
        String string;
        int iIntValue;
        bm.a aVarA;
        t.a(f3405b, "Oaid#initOaid", null);
        try {
            this.e.lock();
            t.a(f3405b, "Oaid#initOaid exec", null);
            br brVarA = this.h.a();
            t.a(f3405b, "Oaid#initOaid fetch=" + brVarA, null);
            if (brVarA != null) {
                d = brVarA.f3425a;
                this.k = brVarA.a();
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            Context context = this.i;
            bm bmVar = this.f;
            if (bmVar == null || (aVarA = bmVar.a(context)) == null) {
                str = null;
                boolValueOf = null;
            } else {
                str = aVarA.f3415b;
                boolValueOf = Boolean.valueOf(aVarA.f3416c);
                if (aVarA instanceof ai.b) {
                    this.l = Long.valueOf(((ai.b) aVarA).f3351a);
                }
            }
            Pair pair = new Pair(str, boolValueOf);
            long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
            if (pair.first != null) {
                if (brVarA != null) {
                    string = brVarA.f3426b;
                    iIntValue = brVarA.f.intValue() + 1;
                } else {
                    string = null;
                    iIntValue = -1;
                }
                if (TextUtils.isEmpty(string)) {
                    string = UUID.randomUUID().toString();
                }
                String str2 = string;
                if (iIntValue <= 0) {
                    iIntValue = 1;
                }
                brVar = new br((String) pair.first, str2, (Boolean) pair.second, Long.valueOf(jElapsedRealtime2), Long.valueOf(System.currentTimeMillis()), Integer.valueOf(iIntValue), this.l);
                this.h.a(brVar);
            } else {
                brVar = null;
            }
            if (brVar != null) {
                d = brVar.f3425a;
                this.k = brVar.a();
            }
            t.a(f3405b, "Oaid#initOaid oaidModel=" + brVar, null);
        } finally {
            this.e.unlock();
            a(new IOaidObserver.Oaid(d), a());
        }
    }
}

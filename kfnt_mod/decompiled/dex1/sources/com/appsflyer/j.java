package com.appsflyer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class j {

    /* renamed from: ʼ, reason: contains not printable characters */
    private static volatile j f211;

    /* renamed from: ˋॱ, reason: contains not printable characters */
    private boolean f217;

    /* renamed from: ˎ, reason: contains not printable characters */
    final Handler f218;

    /* renamed from: ˏ, reason: contains not printable characters */
    boolean f219;

    /* renamed from: ˏॱ, reason: contains not printable characters */
    private final SensorManager f220;

    /* renamed from: ʻ, reason: contains not printable characters */
    private static final BitSet f210 = new BitSet(6);

    /* renamed from: ॱॱ, reason: contains not printable characters */
    private static final Handler f212 = new Handler(Looper.getMainLooper());

    /* renamed from: ˋ, reason: contains not printable characters */
    final Object f216 = new Object();

    /* renamed from: ᐝ, reason: contains not printable characters */
    private final Map<f, f> f222 = new HashMap(f210.size());

    /* renamed from: ˊॱ, reason: contains not printable characters */
    private final Map<f, Map<String, Object>> f215 = new HashMap(f210.size());

    /* renamed from: ˊ, reason: contains not printable characters */
    final Runnable f214 = new AnonymousClass1();

    /* renamed from: ॱ, reason: contains not printable characters */
    final Runnable f221 = new AnonymousClass2();

    /* renamed from: ʽ, reason: contains not printable characters */
    final Runnable f213 = new Runnable() { // from class: com.appsflyer.j.3
        @Override // java.lang.Runnable
        public final void run() {
            synchronized (j.this.f216) {
                if (j.this.f219) {
                    j.this.f218.removeCallbacks(j.this.f221);
                    j.this.f218.removeCallbacks(j.this.f214);
                    j.this.m179();
                    j.this.f219 = false;
                }
            }
        }
    };

    static {
        f210.set(1);
        f210.set(2);
        f210.set(4);
    }

    /* renamed from: com.appsflyer.j$1, reason: invalid class name */
    final class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (j.this.f216) {
                j.this.m179();
                j.this.f218.postDelayed(j.this.f221, 1800000L);
            }
        }

        AnonymousClass1() {
        }

        /* renamed from: ˋ, reason: contains not printable characters */
        static boolean m181(Context context, String str) {
            int iCheckSelfPermission = ContextCompat.checkSelfPermission(context, str);
            StringBuilder sb = new StringBuilder("is Permission Available: ");
            sb.append(str);
            sb.append("; res: ");
            sb.append(iCheckSelfPermission);
            AFLogger.afRDLog(sb.toString());
            return iCheckSelfPermission == 0;
        }
    }

    /* renamed from: com.appsflyer.j$2, reason: invalid class name */
    final class AnonymousClass2 implements Runnable {

        /* renamed from: ˋ, reason: contains not printable characters */
        private static String f224;

        /* renamed from: ˏ, reason: contains not printable characters */
        private static String f225;

        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (j.this.f216) {
                j.this.m180();
                j.this.f218.postDelayed(j.this.f214, 500L);
                j.this.f219 = true;
            }
        }

        AnonymousClass2() {
        }

        /* renamed from: ˏ, reason: contains not printable characters */
        static void m183(String str) {
            f224 = str;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                if (i == 0 || i == str.length() - 1) {
                    sb.append(str.charAt(i));
                } else {
                    sb.append("*");
                }
            }
            f225 = sb.toString();
        }

        /* renamed from: ˊ, reason: contains not printable characters */
        static void m182(String str) {
            if (f224 == null) {
                m183(AppsFlyerProperties.getInstance().getString(AppsFlyerProperties.AF_KEY));
            }
            if (f224 == null || !str.contains(f224)) {
                return;
            }
            AFLogger.afInfoLog(str.replace(f224, f225));
        }
    }

    private j(@NonNull SensorManager sensorManager, Handler handler) {
        this.f220 = sensorManager;
        this.f218 = handler;
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    static j m177(Context context) {
        return m176((SensorManager) context.getApplicationContext().getSystemService("sensor"), f212);
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private static j m176(SensorManager sensorManager, Handler handler) {
        if (f211 == null) {
            synchronized (j.class) {
                if (f211 == null) {
                    f211 = new j(sensorManager, handler);
                }
            }
        }
        return f211;
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    final void m180() {
        try {
            for (Sensor sensor : this.f220.getSensorList(-1)) {
                int type = sensor.getType();
                if (type >= 0 && f210.get(type)) {
                    f fVarM155 = f.m155(sensor);
                    if (!this.f222.containsKey(fVarM155)) {
                        this.f222.put(fVarM155, fVarM155);
                    }
                    this.f220.registerListener(this.f222.get(fVarM155), sensor, 0);
                }
            }
        } catch (Throwable unused) {
        }
        this.f217 = true;
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    final void m179() {
        try {
            if (!this.f222.isEmpty()) {
                for (f fVar : this.f222.values()) {
                    this.f220.unregisterListener(fVar);
                    fVar.m159(this.f215);
                }
            }
        } catch (Throwable unused) {
        }
        this.f217 = false;
    }

    @NonNull
    /* renamed from: ˊ, reason: contains not printable characters */
    final List<Map<String, Object>> m178() {
        synchronized (this.f216) {
            if (!this.f222.isEmpty() && this.f217) {
                Iterator<f> it = this.f222.values().iterator();
                while (it.hasNext()) {
                    it.next().m158(this.f215);
                }
            }
            if (this.f215.isEmpty()) {
                return Collections.emptyList();
            }
            return new ArrayList(this.f215.values());
        }
    }
}

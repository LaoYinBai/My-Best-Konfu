package com.appsflyer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class f implements SensorEventListener {

    /* renamed from: ʻ, reason: contains not printable characters */
    private double f180;

    /* renamed from: ʼ, reason: contains not printable characters */
    private final int f181;

    /* renamed from: ˊ, reason: contains not printable characters */
    @NonNull
    private final String f182;

    /* renamed from: ˎ, reason: contains not printable characters */
    @NonNull
    private final String f184;

    /* renamed from: ॱ, reason: contains not printable characters */
    private final int f186;

    /* renamed from: ॱॱ, reason: contains not printable characters */
    private long f187;

    /* renamed from: ˏ, reason: contains not printable characters */
    private final float[][] f185 = new float[2][];

    /* renamed from: ˋ, reason: contains not printable characters */
    private final long[] f183 = new long[2];

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    private f(int i, @Nullable String str, @Nullable String str2) {
        this.f186 = i;
        this.f184 = str == null ? "" : str;
        this.f182 = str2 == null ? "" : str2;
        this.f181 = ((((i + 31) * 31) + this.f184.hashCode()) * 31) + this.f182.hashCode();
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    static f m155(Sensor sensor) {
        return new f(sensor.getType(), sensor.getName(), sensor.getVendor());
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private static double m151(@NonNull float[] fArr, @NonNull float[] fArr2) {
        int iMin = Math.min(fArr.length, fArr2.length);
        double dPow = 0.0d;
        for (int i = 0; i < iMin; i++) {
            dPow += StrictMath.pow(fArr[i] - fArr2[i], 2.0d);
        }
        return Math.sqrt(dPow);
    }

    @NonNull
    /* renamed from: ॱ, reason: contains not printable characters */
    private static List<Float> m156(@NonNull float[] fArr) {
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float f : fArr) {
            arrayList.add(Float.valueOf(f));
        }
        return arrayList;
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent == null || sensorEvent.values == null) {
            return;
        }
        Sensor sensor = sensorEvent.sensor;
        if ((sensor == null || sensor.getName() == null || sensor.getVendor() == null) ? false : true) {
            int type = sensorEvent.sensor.getType();
            String name = sensorEvent.sensor.getName();
            String vendor = sensorEvent.sensor.getVendor();
            long j = sensorEvent.timestamp;
            float[] fArr = sensorEvent.values;
            if (m154(type, name, vendor)) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                float[] fArr2 = this.f185[0];
                if (fArr2 == null) {
                    this.f185[0] = Arrays.copyOf(fArr, fArr.length);
                    this.f183[0] = jCurrentTimeMillis;
                    return;
                }
                float[] fArr3 = this.f185[1];
                if (fArr3 == null) {
                    float[] fArrCopyOf = Arrays.copyOf(fArr, fArr.length);
                    this.f185[1] = fArrCopyOf;
                    this.f183[1] = jCurrentTimeMillis;
                    this.f180 = m151(fArr2, fArrCopyOf);
                    return;
                }
                if (50000000 <= j - this.f187) {
                    this.f187 = j;
                    if (Arrays.equals(fArr3, fArr)) {
                        this.f183[1] = jCurrentTimeMillis;
                        return;
                    }
                    double dM151 = m151(fArr2, fArr);
                    if (dM151 > this.f180) {
                        this.f185[1] = Arrays.copyOf(fArr, fArr.length);
                        this.f183[1] = jCurrentTimeMillis;
                        this.f180 = dM151;
                    }
                }
            }
        }
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    final void m159(@NonNull Map<f, Map<String, Object>> map) {
        m153(map, true);
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    public final void m158(Map<f, Map<String, Object>> map) {
        m153(map, false);
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private boolean m154(int i, @NonNull String str, @NonNull String str2) {
        return this.f186 == i && this.f184.equals(str) && this.f182.equals(str2);
    }

    @NonNull
    /* renamed from: ˊ, reason: contains not printable characters */
    private Map<String, Object> m152() {
        HashMap map = new HashMap(7);
        map.put("sT", Integer.valueOf(this.f186));
        map.put("sN", this.f184);
        map.put("sV", this.f182);
        float[] fArr = this.f185[0];
        if (fArr != null) {
            map.put("sVS", m156(fArr));
        }
        float[] fArr2 = this.f185[1];
        if (fArr2 != null) {
            map.put("sVE", m156(fArr2));
        }
        return map;
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    private void m157() {
        for (int i = 0; i < 2; i++) {
            this.f185[i] = null;
        }
        for (int i2 = 0; i2 < 2; i2++) {
            this.f183[i2] = 0;
        }
        this.f180 = 0.0d;
        this.f187 = 0L;
    }

    public final int hashCode() {
        return this.f181;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return m154(fVar.f186, fVar.f184, fVar.f182);
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private void m153(@NonNull Map<f, Map<String, Object>> map, boolean z) {
        if (this.f185[0] != null) {
            map.put(this, m152());
            if (z) {
                m157();
                return;
            }
            return;
        }
        if (map.containsKey(this)) {
            return;
        }
        map.put(this, m152());
    }
}
